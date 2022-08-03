package fr.theialand.insitu.dataportal.backendassociation.service;

import fr.theialand.insitu.configurations.rabbitmq.RabbitMQConfig;
import fr.theialand.insitu.dataportal.api.sparql.query.RDFQueryClient;
import fr.theialand.insitu.dataportal.repository.mongo.model.DTO.association.AssociationInformationDTO;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.entity.VariableAssociation;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.observation.I18n;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.observation.TheiaVariable;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.entity.ObservationDocument;
import fr.theialand.insitu.dataportal.repository.mongo.repository.MapItemRepository;
import fr.theialand.insitu.dataportal.repository.mongo.repository.ObservationDocumentLiteRepository;
import fr.theialand.insitu.dataportal.repository.mongo.repository.ObservationDocumentRepository;
import fr.theialand.insitu.dataportal.repository.mongo.repository.VariableAssociationRepository;
import fr.theialand.insitu.dataportal.repository.mongo.service.associate.variable.AssociationOperationService;
import fr.theialand.insitu.dataportal.repository.mongo.service.insert.metadata.ImportService;
import org.apache.jena.sparql.ARQException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AssociationServiceImpl implements AssociationService{
    private RDFQueryClient rdfQueryUtils;
    private ObservationDocumentRepository observationDocumentRepository;
    private AssociationOperationService mongoAssociationOperationService;
    private VariableAssociationRepository mongoVariableAssociationRepository;
    private ImportService mongoImportService;
    private ObservationDocumentLiteRepository observationDocumentLiteRepository;
    private MapItemRepository mapItemRepository;
    //Configuration class that create the necessary beans configure the AMQP broker
    private final RabbitMQConfig rabbitMQConfig;
    //Spring template used to send message using AMQP
    private final RabbitTemplate rabbitTemplate;

    //Name of the AMQP queue where to route the message triggerring import of ISO19155 file in geonetwork
    @Value("${amqp.queue.name}")
    private String routingKey;
    //Name of the AMQP exchange where to route the message triggerring import of ISO19155 file in geonetwork
    @Value("${amqp.exchange.name}")
    private String exchangeName;

    @Value("${spring.rabbitmq.username}")
    private String rabbitUser;


    private static final Logger logger = LoggerFactory.getLogger(AssociationServiceImpl.class);

    @Autowired
    public AssociationServiceImpl(
            RDFQueryClient rdfQueryUtils, ObservationDocumentRepository observationDocumentRepository, AssociationOperationService mongoAssociationOperationService, VariableAssociationRepository mongoVariableAssociationRepository, ImportService mongoImportService, ObservationDocumentLiteRepository observationDocumentLiteRepository, MapItemRepository mapItemRepository, RabbitMQConfig rabbitMQConfig, RabbitTemplate rabbitTemplate) {
        this.rdfQueryUtils = rdfQueryUtils;
        this.observationDocumentRepository = observationDocumentRepository;
        this.mongoAssociationOperationService = mongoAssociationOperationService;
        this.mongoVariableAssociationRepository = mongoVariableAssociationRepository;
        this.mongoImportService = mongoImportService;
        this.observationDocumentLiteRepository = observationDocumentLiteRepository;
        this.mapItemRepository = mapItemRepository;
        this.rabbitMQConfig = rabbitMQConfig;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void submitAssociation(AssociationInformationDTO info) {


        /**
         * For each assocation, Aggregation operations finding all the observations having
         * "observation.observedProperty.name" equal to the association variable name, and
         * "observation.observedProperty.theiaCategories" being a subset of the assoation variable theia categories
         */
        info.getAssociations().forEach(association -> {
            /**
             * Get the category uris of the variable from the thesaurus associated
             * If the submited association correspond to a delete operation , assocation.uri is null and the uri of the
             * variable previously associated can be found in association.producerVariable.theiaVariable.uri
             */
            if(association.getUri() != null) {
                association.setBroaderCategories(this.rdfQueryUtils.findFirstBroaderCategories(association.getUri()));
            } else {
                association.setBroaderCategories(this.rdfQueryUtils.findFirstBroaderCategories(association.getProducerVariable().getTheiaVariable().getUri()));
            }
            /**
             * Get the simplified label of the variable
             * If the submited association correspond to a delete operation , assocation.uri is null and the uri of the
             * variable previously associated can be found in association.producerVariable.theiaVariable.uri
             */
            if(association.getUri() != null) {
            association.setSimplifiedLabel(this.rdfQueryUtils.getSimplifiedLabels(association.getUri()));
            } else {
                association.setSimplifiedLabel(this.rdfQueryUtils.getSimplifiedLabels(association.getProducerVariable().getTheiaVariable().getUri()));
            }
            /**
             * Mongodb "observations" collection update. The corresponding documents are updated with
             * "observations.observedProperty.theiaVariable" field.
             */
            /**
             * Storing matching value into lists to find corresponding observation
             */
            /**
             * Store the english producer variable name
             */
            String producerVariableNameEn = association.getProducerVariable().getName().stream()
                    .filter(name -> "en".equals(name.getLang())).findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("No english label for the producer variable")).getText();
            /**
             * Store all unit name
             */
            String unitNameEn = association.getProducerVariable().getUnit().stream()
                    .filter(name -> "en".equals(name.getLang())).findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("No english label for the producer unit")).getText();


            /**
             * Find the documents that match the association in "observations" collection
             * Matching criteria : producerId, variable name, unit , theia categories
             * !!! Keep in mind that when the variable is associated in set in observation collection, the field
             * theiaCategories correspond to the categories from the thesaurus and not from the association made by the producer anymore
             */
            List<ObservationDocument> observationDocumentList = new ArrayList();

                //In case of an update of an already existing association, we will look for the document associated to a thiea variable using theia variable uri
                if (association.getProducerVariable().getTheiaVariable() != null) {
                    observationDocumentList = this.observationDocumentRepository
                            .findByProducerProducerIdAndObservationObservedPropertyNameTextInAndObservationObservedPropertyUnitTextInAndObservationObservedPropertyTheiaVariableUri(
                                    info.getProducerId(), producerVariableNameEn, unitNameEn, association.getProducerVariable().getTheiaVariable().getUri());
                // Else, the association corresponds to a creation, and the documents to be updated in observation collection
                // can be identified using the english producerVariableName the unit and the theiaCategories selected by the producer
                } else {
                    observationDocumentList = this.observationDocumentRepository
                            .findByProducerProducerIdAndObservationObservedPropertyNameTextInAndObservationObservedPropertyUnitTextInAndObservationObservedPropertyTheiaCategoriesIn(
                                    info.getProducerId(), producerVariableNameEn, unitNameEn, association.getProducerVariable().getTheiaCategories());
                }
            /**
             * ---------------------------------------------------
             * Case 1 : DELETE ASSOCIATION
             * ---------------------------------------------------
             * if "prefLabel" and "uri" fields are null the operation is a "delete association". Mongodb "observations"
             * and "variableAssociation" collections are updated.
             */
            if(association.getPrefLabel().isEmpty() && association.getUri() == null ) {
                List<String> documentIds = new ArrayList<>();
                for (ObservationDocument doc : observationDocumentList) {
                    documentIds.add(doc.getDocumentId());
                }
                // A - Remove TheiaVariable fields from the documents of "observations" collection matching the association
                // and set theia categories to the categories originally set by the producer
                VariableAssociation variableAssociation = this.mongoVariableAssociationRepository.findFirstByProducerIdAndTheiaVariableUriAndProducerVariableNameEn(info.getProducerId(), association.getProducerVariable().getTheiaVariable().getUri(), producerVariableNameEn);
                association.getProducerVariable().setTheiaCategories(variableAssociation.getProducerTheiaCategories());
                this.mongoAssociationOperationService.unsetThieaVariableInMultiObservations(documentIds,association.getProducerVariable().getTheiaCategories());

                /**
                 * The association is removed from the variableAssociations collection if this association does not
                 * correspond to other observation for the same producer
                 */
                // B - Remove the association from variable association collection
                if (!this.observationDocumentRepository.existsByProducerProducerIdAndObservationObservedPropertyNameTextInAndObservationObservedPropertyTheiaCategoriesInAndObservationObservedPropertyTheiaVariableExists(info.getProducerId(), producerVariableNameEn, association.getBroaderCategories(), true)) {
                    this.mongoAssociationOperationService.updateOneVariableAssociation(info.getProducerId(), association);
                } else {
                    throw new IllegalStateException("this association should not correspond to other observation for the same producer");
                }
            /**
             * If the theiaVariable/TheiaCategory association don't exists any more in other associations of the
             * variableAssociations collection, the semantic links are removed from the thesaurus for each
             * categories of the deleted association. Otherwise, if other associations exist, wee check that each
             * categories of the deleted association exists among the remaining association of the
             * variableAssociations collection. If one theia categories does not exist any more among the remaining
             * association, the semantic link theiaVariable/TheiaCategory is removed
             */
            // C - Update the thesaurus by removing semantic link between theiaVariable and theiaCategory if there is no more occurance of the couple in association collection
            } else {
                /**
                 * ---------------------------------------------------
                 * Case 2 : UPDATE ASSOCIATION
                 * The operation made is an "association creation" or "association update"
                 * ---------------------------------------------------
                 */
                /**
                 * Each document of the observations collection is updated by adding "observation.observedProperty.theiaVariables" object
                 * and by replacing theiaCategories uri set by the producer by categories retrieve from the thesaurus correponding to the borader categories
                 * of the variable in the thesaurus.
                 */
                TheiaVariable variable = new TheiaVariable();
                variable.setUri(association.getUri());
                variable.setPrefLabel(association.getPrefLabel());
                variable.setSimplifiedLabel(association.getSimplifiedLabel());
                variable.setBroaderCategories(association.getBroaderCategories());
                for (ObservationDocument doc : observationDocumentList) {
                    this.mongoAssociationOperationService.updateOneObservationObservedPropertyWithTheiaVariable(doc.getDocumentId(),variable);
                }
                /**
                 * If the theia variable correspond to an existing uri with an updated prefLabel, other previously
                 * associated document need to be updated
                 */
                this.mongoAssociationOperationService.updateVariableSemanticPropertiesForOtherObservations(variable);
                /**
                 * Update the "variableAssociation" collection with one association
                 */
                this.mongoAssociationOperationService.updateOneVariableAssociation(info.getProducerId(), association);
            }
        });


        /**
         * the documents of the collection "observations" are grouped to "observationsLite" and "mapItems" collection
         * for the given producer.
         */
        this.observationDocumentLiteRepository.deleteObservationDocumentLiteByProducerProducerId(info.getProducerId());
        this.mapItemRepository.deleteMapItemByProducerId(info.getProducerId());
        this.mongoImportService.groupObservationDocumentsByVariableAtGivenLocationAndInsertObservationDocumentLiteCollection(info.getProducerId());
        this.mongoImportService.groupObservationDocumentLiteByLocationAndInsertMapItemCollection(info.getProducerId());
        this.mongoImportService.aggregateObservationDocumentLiteToNoFilterFacetsCollection();

        /**
         * Update datasets in Geonetwork by sending a message to the geonetwork ETL queue
         */
        logger.info("Updating CSW dataset files");
        this.rabbitTemplate.convertAndSend(exchangeName, routingKey, info.getProducerId());

    }


    @Override
    public ResponseEntity<List<I18n>> getPrefLabelUsingUri(String uri) {
        try {
            List<I18n> prefLabels = new ArrayList<>();
            I18n i18n = new I18n();
            i18n.setLang("en");
            i18n.setText(this.rdfQueryUtils.getEnPrefLabel(uri));
            prefLabels.add(i18n);
            return new ResponseEntity<>(prefLabels, HttpStatus.ACCEPTED);
        } catch (ARQException ex) {
            logger.error(ex.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @Override
    public ResponseEntity<List<String>> getBroaderCategoryUsingUri(String uri) {
        try{
            return new ResponseEntity<>(this.rdfQueryUtils.findFirstBroaderCategories(uri), HttpStatus.ACCEPTED);
        }
    catch (ARQException ex) {
            logger.error(ex.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<Boolean> IsAVariableURI(String uri) {
        try{
            return new ResponseEntity<>(this.rdfQueryUtils.isAVariableURI(uri) && !this.rdfQueryUtils.getSimplifiedLabels(uri).isEmpty(), HttpStatus.ACCEPTED);
        }
        catch (ARQException ex) {
            logger.error(ex.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
