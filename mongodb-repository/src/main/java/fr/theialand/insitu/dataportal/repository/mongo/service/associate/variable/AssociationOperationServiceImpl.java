package fr.theialand.insitu.dataportal.repository.mongo.service.associate.variable;

import fr.theialand.insitu.dataportal.repository.mongo.model.DTO.association.AssociationDTO;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.entity.VariableAssociation;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.observation.TheiaVariable;
import fr.theialand.insitu.dataportal.repository.mongo.repository.MongoDbUtils;
import fr.theialand.insitu.dataportal.repository.mongo.repository.VariableAssociationRepository;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
@Service
public class AssociationOperationServiceImpl implements AssociationOperationService {

    private MongoDbUtils mongoDbUtils;
    private VariableAssociationRepository variableAssociationRepository;

    private static final Logger LOG = LoggerFactory.getLogger(AssociationOperationServiceImpl.class);

    @Autowired
    public AssociationOperationServiceImpl(MongoDbUtils mongoDbUtils, VariableAssociationRepository variableAssociationRepository) {
        this.mongoDbUtils = mongoDbUtils;
        this.variableAssociationRepository = variableAssociationRepository;
    }

    @Override
    public void updateVariableSemanticPropertiesForOtherObservations(TheiaVariable theiaVariable ) {
        /**
         * Aggregation operations finding all the observations with theiaVariable prefLabel different to prefLabel
         * method param. The aggregation pipeline return a list of Document containing the documentId of the
         * observations and the index corresponding to the lang parameter in the prefLabel array - example of prefLabel
         * (prefLabel: [{"lang":"en", "text": "Air pressure"}, {"lang":"fr", "text": "Pression de l'air"}]
         */
        MatchOperation m1 = Aggregation.match(where("observation.observedProperty.theiaVariable.uri").is(theiaVariable.getUri()));
        MatchOperation m2 = Aggregation.match(new Criteria().orOperator(
                where("observation.observedProperty.theiaVariable.prefLabel").ne(theiaVariable.getPrefLabel()),
                where("observation.observedProperty.theiaVariable.simplifiedLabel").ne(theiaVariable.getSimplifiedLabel()),
                where("observation.observedProperty.theiaCategories").ne(theiaVariable.getBroaderCategories())
        ));
        //ProjectionOperation p1 = Aggregation.project("documentId").and(ArrayOperators.IndexOfArray.arrayOf("observation.observedProperty.theiaVariable.prefLabel.lang").indexOf(lang)).as("index");
        LOG.debug("Looking for documents to be updated in 'observations' collection for variable: {}", theiaVariable.getUri());
        List<Document> responses = this.mongoDbUtils.aggregateToList("observations",Document.class, false,m1, m2);
        LOG.debug("{} documents to be updated", responses.size());

        /**
         * If the aggregation pipeline find observation satisflying the match operation, those observations are updated
         * with the new prefLabel for the given language
         */
        if (responses.size() > 0) {
            for (Document obs : responses) {
                LOG.debug("Updating document of the observation collection: {}", obs.getString("documentId"));
                Query query = Query.query(new Criteria("documentId").is(obs.getString("documentId")));
                Update update = new Update();
                update.set("observation.observedProperty.theiaVariable.prefLabel",theiaVariable.getPrefLabel())
                .set("observation.observedProperty.theiaVariable.simplifiedLabel",theiaVariable.getSimplifiedLabel())
                .set("observation.observedProperty.theiaCategories",theiaVariable.getBroaderCategories());
                //Update update = Update.update("observation.observedProperty.theiaVariable.prefLabel." + obs.getInteger("index") + ".text", prefLabel);
                this.mongoDbUtils.updateFirst(query, update, "observations");
            }
            /**
             * The "variableAssociations" document saving association of the "observations" collection matching document
             * are also updated.
             */
            LOG.debug("Updating document of the variableAssociations collection");
            MatchOperation m3 = Aggregation.match(new Criteria("theiaVariable.uri").is(theiaVariable.getUri()));
            List<Document> responsesVariableAssociations = this.mongoDbUtils.aggregateToList("variableAssociations", Document.class, false,m3);
            for (Document asso : responsesVariableAssociations) {
                Query query = Query.query(new Criteria("_id").is(asso.getObjectId("_id")));
                Update update = Update.update("theiaVariable", theiaVariable);
                this.mongoDbUtils.updateFirst(query, update, "variableAssociations");
            }
        }
    }

    /**
     * update or insert or remove a new association in "variableAssociations" collection
     *
     * @param producerId id of the producer
     * @param association AssociationDTO. if "association.prefLabel" is empty and "association.uri" is null, the
     * association is removed from the collection
     */
    public void updateOneVariableAssociation(String producerId, AssociationDTO association) {

        LOG.info("Update variable association for the producer variable {} ",association.getProducerVariable().getName().get(0).toString());
        /**
         * Storing matching value into lists to find corresponding observation
         */
        String producerVariableNameEn = association.getProducerVariable().getName().stream()
                .filter(label -> "en".equals(label.getLang())).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No english Label in producer variable name")).getText();

        String unitNameEn = association.getProducerVariable().getUnit().stream()
                .filter(label -> "en".equals(label.getLang())).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No english Label in producer unit name")).getText();

        Query query = Query.query(Criteria.where("producerId").is(producerId)
                .and("producerVariableNameEn").is(producerVariableNameEn)
                .and("unitNameEn").is(unitNameEn)
                .and("producerTheiaCategories").in(association.getProducerVariable().getTheiaCategories()));
        //if the URI is null in the association DTO, the association has been deleted so it is removed from the collection
        if (association.getPrefLabel().isEmpty() && association.getUri() == null) {
            this.mongoDbUtils.remove(query, "variableAssociations");
        //Else, the association is created or updated.
        } else {

            TheiaVariable variable = new TheiaVariable();
            variable.setUri(association.getUri());
            variable.setPrefLabel(association.getPrefLabel());
            variable.setSimplifiedLabel(association.getSimplifiedLabel());
            VariableAssociation variableAssociation = this.variableAssociationRepository.findFirstByProducerIdAndTheiaVariableUriAndProducerVariableNameEn(producerId, association.getUri(),producerVariableNameEn);
            // If it is a creation, the association does not exit inthe variableAssociation collection meaning that the observedProperty.theiaCategories
            // in the observations collection corresponds to the categories associated by the producer.
            // Else, the association exists and it is an update of the association. It means that the observedProperty.theiaCategories in the
            // observation collection as already been modified by the categories from the thesaurus. Hence, we need to find the corresponding association
            // to be modified in order to recover the categories originally associated by the producer
            if(variableAssociation != null) {
                association.getProducerVariable().setTheiaCategories(variableAssociation.getProducerTheiaCategories());
            }
            variable.setBroaderCategories(association.getBroaderCategories());
            Update update = Update.update("isActive", true)
                    .set("theiaVariable", variable)
                    .set("producerId", producerId)
                    .set("producerVariableNameEn", producerVariableNameEn)
                    .set("unitNameEn", unitNameEn)
                    .set("producerTheiaCategories", association.getProducerVariable().getTheiaCategories());
            this.mongoDbUtils.upsert(query, update, "variableAssociations");
        }
    }

    @Override
    public void unsetThieaVariableInMultiObservations(List<String> documentIds, List<String> categorieUrisFromProducer) {
        Query query = Query.query(new Criteria("documentId").in(documentIds));
        Update update = new Update();
        update.set("observation.observedProperty.theiaCategories", categorieUrisFromProducer);
        update.unset("observation.observedProperty.theiaVariable");
        this.mongoDbUtils.updateMulti(query,update,"observations");
    }

    @Override
    public void updateOneObservationObservedPropertyWithTheiaVariable(String documentId, TheiaVariable theiaVariable) {
        Query query = Query.query(new Criteria("documentId").is(documentId));
        Update update = new Update();
        update.set("observation.observedProperty.theiaCategories", theiaVariable.getBroaderCategories());
        List<String> categories = theiaVariable.getBroaderCategories();
        //theiaVariable.setBroaderCategories(null);
        update.set("observation.observedProperty.theiaVariable",
                theiaVariable);
        this.mongoDbUtils.updateFirst(query, update, "observations");
        theiaVariable.setBroaderCategories(categories);
    }
}
