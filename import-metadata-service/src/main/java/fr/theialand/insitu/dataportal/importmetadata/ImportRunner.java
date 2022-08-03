package fr.theialand.insitu.dataportal.importmetadata;

import com.fasterxml.jackson.databind.JsonNode;
import fr.theialand.insitu.configurations.rabbitmq.RabbitMQConfig;
import fr.theialand.insitu.dataportal.importmetadata.Exceptions.ImportException;
import fr.theialand.insitu.dataportal.importmetadata.service.FileUtils;
import fr.theialand.insitu.dataportal.importmetadata.service.ImportConfig;
import fr.theialand.insitu.dataportal.importmetadata.service.JsonUtils;
import fr.theialand.insitu.dataportal.importmetadata.service.creation.EnrichmentService;
import fr.theialand.insitu.dataportal.importmetadata.service.creation.InternationalisationService;
import fr.theialand.insitu.dataportal.importmetadata.service.creation.ObservationDocumentCreationService;
import fr.theialand.insitu.dataportal.importmetadata.service.insert.InsertService;
import fr.theialand.insitu.dataportal.importmetadata.service.validation.InputValidationService;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.entity.ObservationDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.RemoteInvocationAwareMessageConverterAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.List;

/**
 * Is launched automatically by the Spring App {@link ImportMetadataApplication}
 * Because it implements {@link ApplicationRunner}
 */
@Component
@ComponentScan("fr.theialand.insitu.configurations.rabbitmq")
public class ImportRunner implements ApplicationRunner {


    private static final String LINKEDFILES_NAME = "files_linked.json";

    /**
     * name of the option on the command line
     * --producerPath=/tmp/TOUR/123
     * also see logback XML conf
     */
    public static final String OPTION_PRODUCERPATH = "prodDir";

    //Name of the AMQP queue where to route the message triggerring import of ISO19155 file in geonetwork
    @Value("${amqp.queue.name}")
    private String routingKey;
    //Name of the AMQP exchange where to route the message triggerring import of ISO19155 file in geonetwork
    @Value("${amqp.exchange.name}")
    private String exchangeName;

    @Value("${spring.rabbitmq.username}")
    private String rabbitUser;

    private final InputValidationService inputValidationService;
    private final InternationalisationService internationalisationService;
    private final EnrichmentService enrichmentService;
    private final ObservationDocumentCreationService observationDocumentCreationService;
    private final InsertService insertService;
    //Configuration class that create the necessary beans configure the AMQP broker
    private final RabbitMQConfig rabbitMQConfig;
    //Spring template used to send message using AMQP
    private final RabbitTemplate rabbitTemplate;


    private static final Logger logger = LoggerFactory.getLogger(ImportRunner.class);

    /**
     * must be correctly handled by the appender downstream
     * Used to separate user logs of dev logs, really an appender job
     */
    private static final Marker USER = MarkerFactory.getMarker("USER");

    private File producerDir;
    private String producerId;

    @Autowired
    private ImportRunner(InputValidationService inputValidationService, InternationalisationService internationalisationService, EnrichmentService enrichmentService, ObservationDocumentCreationService observationDocumentCreationService, InsertService insertService, RabbitMQConfig rabbitMQConfig, RabbitTemplate rabbitTemplate) {
        this.inputValidationService = inputValidationService;
        this.internationalisationService = internationalisationService;
        this.enrichmentService = enrichmentService;
        this.observationDocumentCreationService = observationDocumentCreationService;
        this.insertService = insertService;
        this.rabbitMQConfig = rabbitMQConfig;
        this.rabbitTemplate = rabbitTemplate;
        this.rabbitTemplate.setMessageConverter(new RemoteInvocationAwareMessageConverterAdapter());
    }

    @Autowired
    Environment env;

    @Override
    public void run(ApplicationArguments args) throws ImportException {

        if (args == null || !args.containsOption(OPTION_PRODUCERPATH))
            throw new ImportException("Option --" + OPTION_PRODUCERPATH + " is mandatory, with the path to the producer files");

        String producerPath = args.getOptionValues(OPTION_PRODUCERPATH).get(0);
        if (producerPath == null || producerPath.isBlank())
            throw new ImportException("Option --" + OPTION_PRODUCERPATH + " cannot be empty, please provide the path to the producer files");

        // wrapping around try clause to separate workflow fr.theialand.insitu.dataportal.api.geonetwork.exceptions, from bad stuff
        try {
            Pair<ImportConfig, JsonNode> validPair = this.validationPhase(producerPath);
            logger.info(USER, "Validation phase successful");
            this.persistPhase(validPair.getFirst(), validPair.getSecond());
        } catch (ImportException importEx) {
            // Log with the "tag" USER, so logback can recognize it and write it to a special log file, in addition to the usual
            logger.error(USER, "Error while importing, {}. Please ask TheiaOzcar team to investigate",
                    importEx.getMessage(),
                    importEx);
            throw importEx; // ImportEx has ExitCodes embedded, so we can re-throw safely
        } catch (Exception badStuffEx) {
            logger.error("System exception received", badStuffEx);
            System.exit(1);
        }
        logger.info(USER, "validation and import successful");
    }

    /*
     * Creation of the ImportConfig object in order to know what importation pattern follow
     * args = path to the folder where the files to be validated before to be imported are located
     * /data/producerId/tmp/{date}/unzip
     */
    private Pair<ImportConfig, JsonNode> validationPhase(String pathToFilesAsString) throws ImportException, IOException {
        logger.info(USER, "starting importing {} ", pathToFilesAsString);

        this.producerDir = new File(pathToFilesAsString);
        this.producerId = FileUtils.getProducerId(producerDir);

        /**
         * Configuration resuming what datafiles are present in the .json
         */
        ImportConfig importConfig = inputValidationService.validateAndGetConfig(producerDir);

        /*
         * Internationalisation of the the relevant json fields
         *  including remove empty strings
         */
        JsonNode internationalisedJsonWithoutEmptyString = internationalisationService.setInternationalisation(pathToFilesAsString, importConfig);
//        /*
//        Check if the category corresponds to a deprecated category or a VariableConcept
//        - deperecated category: we use dct:isReplacedBy relation to retrieve the new category uri
//        - Variable URI: we find the closest broader category URI of the variable and add the variable observedProperty.keywords
//         */
//        inputValidationService.validateTheiaCategoriesOfJson(internationalisedJsonWithoutEmptyString);

        logger.info(USER, "input files are validated");

        // some kind of dirty way to return a Tuple.... usually a code smell
        return Pair.of(importConfig, internationalisedJsonWithoutEmptyString);
    }

    void persistPhase(ImportConfig importConfig, JsonNode jsonElement) throws IOException, ImportException, URISyntaxException {
        logger.info(USER, "Storing information in database...");

        /*
         * Creation of the MongoDB Observation documents according to the importConfig object
         */
        List<ObservationDocument> observationDocuments = observationDocumentCreationService.createObservationDocument(jsonElement, importConfig);

        /*
        Check if the category corresponds to a deprecated category or a VariableConcept
        - deperecated category: we use dct:isReplacedBy relation to retrieve the new category uri
        - Variable URI: we find the closest broader category URI of the variable and add the variable observedProperty.keywords
         */
        for(int i = 0; i < observationDocuments.size() ; i++) {
            observationDocuments.get(i).getObservation().setObservedProperty(inputValidationService.validateTheiaCategoriesOfJson(observationDocuments.get(i).getObservation().getObservedProperty()));
        }


        /**
         *  - Enrich observation document with elevation if the sampling feature elevation is not indicated
         *  - Enrich observation document with climates using Koppen climate classification
         *  - Enrich observation document with geologies using Commission for the Geological Map of the World Bedrock
         *  and structural geology onshore
         */
        logger.info(USER, "Enrich observations with geologies, climates, and optionally elevation");
        for (int i = 0; i < observationDocuments.size(); i++) {
            this.enrichmentService.enrichObservationWithElevation(observationDocuments.get(i).getObservation());
            this.enrichmentService.enrichObservationsWithKoppenClassification(observationDocuments.get(i).getObservation());
            this.enrichmentService.enrichObservationWithCGMWGeology(observationDocuments.get(i).getObservation());
        }



        /*
         * Import of the newly created documents in MongoDB "theia-in-situ" database
         */

        // Before the purge, keep the datasets ID in hand, for later
        List<String> previousDatasets = this.insertService.getDatasetsId(producerId);
        this.insertService.deleteProducerDocuments(producerId);
        this.insertService.insertObservationDocuments(observationDocuments);
        logger.info(USER,"Enriching with theia variable if some association have already been created by the Theia administrators");
        this.enrichmentService.enrichObservationsWithVariableAssociationsCollection(producerId);
        logger.info(USER,"Enriching with administrative features");
        this.enrichmentService.enrichObservationsWithAdministrativeFeaturesCollection(producerId);
        this.insertService.groupObservationDocumentsAndInsertInObservationDocumentLiteCollection(producerId);
        this.insertService.groupObservationDocumentLiteByLocationAndInsertMapItemCollection(producerId);
        this.insertService.aggregateObservationDocumentLiteToNoFilterFacetsCollection();
        this.insertService.refreshVariableAssociations(producerId);

        /*
         * Create the files_linked.json file on /data/producerId/tmp/{date}/ folder
         */
        String filesLinked = JsonUtils.listLinkedFiles(this.producerDir);
        File linkedFilesFile = new File(this.producerDir.getParentFile(), LINKEDFILES_NAME);
        logger.info("Gathering linked files to {}", linkedFilesFile.getAbsolutePath()); // no USER Marker, not to appear in userlog
        Files.writeString(linkedFilesFile.toPath(), filesLinked, StandardOpenOption.CREATE);

        /*
        Insert ISO115 files in geonetwork for each dataset of the producer
         */

        // persist the history of datasets, with the help of the old ones that we saved before the Purge
        // It allows to remember if an insterted dataset is a new dataset or if it is just an update
        this.insertService.updateDatasetHistory(producerId, previousDatasets);

        /**
         * Update datasets in Geonetwork by sending a message to the geonetwork ETL queue
         */
        logger.info(USER,"Updating CSW dataset files");
        this.rabbitTemplate.convertAndSend(exchangeName, routingKey, producerId);

        logger.info(USER, "synchronize {} obsDocs from producer {} to Geonetwork", observationDocuments.size(), producerId);
    }

}
