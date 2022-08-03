package fr.theialand.insitu.dataportal.utils;

import fr.theialand.insitu.configurations.rabbitmq.RabbitMQConfig;
import fr.theialand.insitu.dataportal.api.sparql.query.RDFQueryClient;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.entity.ObservationDocument;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.entity.TheiaCategory;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.entity.VariableAssociation;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.observation.TheiaVariable;
import fr.theialand.insitu.dataportal.repository.mongo.repository.*;
import fr.theialand.insitu.dataportal.repository.mongo.service.associate.variable.AssociationOperationService;
import fr.theialand.insitu.dataportal.repository.mongo.service.insert.metadata.ImportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
@ComponentScan(basePackages = {"fr.theialand.insitu.dataportal","fr.theialand.insitu.configurations"},
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.REGEX, pattern = "fr.theialand.insitu.dataportal.api.geonetwork.*"),
                @ComponentScan.Filter(type = FilterType.REGEX, pattern = "fr.theialand.insitu.dataportal.etl.*"),
                @ComponentScan.Filter(type = FilterType.REGEX, pattern = "fr.theialand.insitu.dataportal.api.sparql.update.*")
        })
@EnableMongoRepositories("fr.theialand.insitu.dataportal.repository.mongo")
public class PatchMongoAfterThesaurusUpdate implements ApplicationRunner, ApplicationContextAware {

    private static final Logger logger = LoggerFactory.getLogger(PatchMongoAfterThesaurusUpdate.class);

    private VariableCategoryRepository variableCategoryRepository;
    private VariableAssociationRepository variableAssociationRepository;
    private ObservationDocumentRepository observationDocumentRepository;
    private ObservationDocumentLiteRepository observationDocumentLiteRepository;
    private MapItemRepository mapItemRepository;
    private RDFQueryClient rdfQueryUtils;
    private ImportService mongoImportService;
    private AssociationOperationService mongoAssociationOperationService;
    //Configuration class that create the necessary beans configure the AMQP broker
    private final RabbitMQConfig rabbitMQConfig;
    //Spring template used to send message using AMQP
    private final RabbitTemplate rabbitTemplate;

    private CachingConnectionFactory cachingConnectionFactory;

    private ApplicationContext context;


    //Name of the AMQP queue where to route the message triggerring import of ISO19155 file in geonetwork
    @Value("${amqp.queue.name}")
    private String routingKey;
    //Name of the AMQP exchange where to route the message triggerring import of ISO19155 file in geonetwork
    @Value("${amqp.exchange.name}")
    private String exchangeName;

    @Value("${spring.rabbitmq.username}")
    private String rabbitUser;


    @Autowired
    public PatchMongoAfterThesaurusUpdate(VariableCategoryRepository variableCategoryRepository, ObservationDocumentRepository observationDocumentRepository, VariableAssociationRepository variableAssociationRepository, ObservationDocumentLiteRepository observationDocumentLiteRepository, MapItemRepository mapItemRepository, RDFQueryClient rdfQueryUtils, ImportService mongoImportService, AssociationOperationService mongoAssociationOperationService, RabbitMQConfig rabbitMQConfig, RabbitTemplate rabbitTemplate, CachingConnectionFactory cachingConnectionFactory) {
        this.variableCategoryRepository = variableCategoryRepository;
        this.variableAssociationRepository = variableAssociationRepository;
        this.observationDocumentRepository = observationDocumentRepository;
        this.observationDocumentLiteRepository = observationDocumentLiteRepository;
        this.mapItemRepository = mapItemRepository;
        this.rdfQueryUtils = rdfQueryUtils;
        this.mongoImportService = mongoImportService;
        this.mongoAssociationOperationService = mongoAssociationOperationService;
        this.rabbitMQConfig = rabbitMQConfig;
        this.rabbitTemplate = rabbitTemplate;
        this.cachingConnectionFactory = cachingConnectionFactory;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    public static void main(String[] args) {
        SpringApplication.run(PatchMongoAfterThesaurusUpdate.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        /**
         * 1 - Update the variableCategories collection with the theia categories of the thesaurus
         */
        logger.info("Update the variableCategories collection with the theia categories of the thesaurus");
        variableCategoryRepository.deleteAll();
        List<TheiaCategory> vcs = this.rdfQueryUtils.getAllVariableCategories();
        vcs.forEach(vc -> {
            variableCategoryRepository.insert(vc);
        });

        /**
         * The patch is run for each producer
         */
        List<String> producerIds = this.observationDocumentRepository.getProducerIds();
        for (String producerId : producerIds) {
            logger.info("Find associations of producer : {}", producerId);
            //Find all associations corresponding to the producer
            List<VariableAssociation> associations = this.variableAssociationRepository.findByProducerId(producerId);
            //For each association of the producer the variable association is unset in the corresponding observation documents
            logger.info("Found {} associations for producer {}", associations.size(), producerId);
            for (VariableAssociation association : associations) {
                logger.info("Unset the TheiaVariable and update the original producer theia categories in the Observation collection for association {}", association.get_id());
                /**
                 *  1 - Unset the TheiaVariable and update the original producer theia categories in the Observation collection
                 */
                //Get the corresponding observation documents
                List<String> documentIds = this.observationDocumentRepository
                        .findByProducerProducerIdAndObservationObservedPropertyNameTextInAndObservationObservedPropertyUnitTextInAndObservationObservedPropertyTheiaVariableUri(
                                producerId, association.getProducerVariableNameEn(), association.getUnitNameEn(), association.getTheiaVariable().getUri())
                        .stream().map(ObservationDocument::getDocumentId).collect(Collectors.toList());
                //Unset theiaVariable and update theiaCategories with the categories originally selected by the producer
                this.mongoAssociationOperationService.unsetThieaVariableInMultiObservations(documentIds, association.getProducerTheiaCategories());

                /**
                 *  2 - Update the association
                 */
                //Is the variable uri deprecated
                String uri = association.getTheiaVariable().getUri();
                logger.info("Checking for {}", uri);
                if (this.rdfQueryUtils.isDeprecatedConcept(uri)) {
                    logger.warn("{} is a deprecated concept", uri);
                    //If it is a deprectated concept, check if there is a dct:isReplacedBy relation
                    uri = this.rdfQueryUtils.getIsReplacedByUri(uri);
                    if (uri == null || (!this.rdfQueryUtils.isAVariableURI(uri) && !this.rdfQueryUtils.getSimplifiedLabels(uri).isEmpty())) {
                        //If the uri is null or is not a IADOPT Variable the association is removed
                        uri = null;
                        this.variableAssociationRepository.deleteById(association.get_id());
                        logger.warn("{} is a does not correspond to a Variable anymore. The association {} is deleted", uri, association.get_id());
                    }
                }
                if (uri != null) {
                    logger.info(" Update association {} with information from the variable {}", association.get_id(), uri);
                    // if the uri is a not deprecated or is replaced by a valid Variable uri the association document is updated
                    TheiaVariable theiaVariable = new TheiaVariable();
                    theiaVariable.setUri(uri);
                    theiaVariable.setSimplifiedLabel(this.rdfQueryUtils.getSimplifiedLabels(uri));
                    theiaVariable.setPrefLabel(this.rdfQueryUtils.getPrefLabels(uri));
                    theiaVariable.setBroaderCategories(this.rdfQueryUtils.findFirstBroaderCategories(uri));
                    association.setTheiaVariable(theiaVariable);
                    this.variableAssociationRepository.deleteById(association.get_id());
                    this.variableAssociationRepository.insert(association);
                }
            }

            /**
             * 3 - Update the Variable category collection
             */
            this.mongoImportService.enrichObservationsWithVariableAssociationsCollection(producerId);
            this.observationDocumentLiteRepository.deleteObservationDocumentLiteByProducerProducerId(producerId);
            this.mongoImportService.groupObservationDocumentsByVariableAtGivenLocationAndInsertObservationDocumentLiteCollection(producerId);
            this.mapItemRepository.deleteMapItemByProducerId(producerId);
            this.mongoImportService.groupObservationDocumentLiteByLocationAndInsertMapItemCollection(producerId);
            /**
             * Update datasets in Geonetwork by sending a message to the geonetwork ETL queue
             */
            logger.info("Updating CSW dataset files for producer {}", producerId);
            this.rabbitTemplate.convertAndSend(exchangeName, routingKey, producerId);
        }
        this.mongoImportService.aggregateObservationDocumentLiteToNoFilterFacetsCollection();

        cachingConnectionFactory.resetConnection();
        ((ConfigurableApplicationContext) context).close();
        cachingConnectionFactory.destroy();
    }


}
