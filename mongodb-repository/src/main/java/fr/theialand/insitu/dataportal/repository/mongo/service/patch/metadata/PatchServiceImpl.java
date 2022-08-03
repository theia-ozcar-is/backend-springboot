package fr.theialand.insitu.dataportal.repository.mongo.service.patch.metadata;

import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.entity.ObservationDocument;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.entity.VariableAssociation;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.observation.I18n;
import fr.theialand.insitu.dataportal.repository.mongo.repository.MongoDbUtils;
import fr.theialand.insitu.dataportal.repository.mongo.repository.ObservationDocumentRepository;
import fr.theialand.insitu.dataportal.repository.mongo.repository.VariableAssociationRepository;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatchServiceImpl implements PatchService {
    MongoDbUtils mongoDbUtils;
    ObservationDocumentRepository observationDocumentRepository;
    VariableAssociationRepository variableAssociationRepository;

    @Autowired
    public PatchServiceImpl(MongoDbUtils mongoDbUtils, ObservationDocumentRepository observationDocumentRepository, VariableAssociationRepository variableAssociationRepository) {
        this.mongoDbUtils = mongoDbUtils;
        this.observationDocumentRepository = observationDocumentRepository;
        this.variableAssociationRepository = variableAssociationRepository;
    }

    @Override
    public List<String> getProducerImpactedByTheiaCategoryChange(List<String> categoryUris) {
        UnwindOperation u1 = Aggregation.unwind("observation.observedProperty.theiaCategories");
        MatchOperation m1 = Aggregation.match(Criteria.where("observation.observedProperty.theiaVariable").exists(true));
        MatchOperation m2 = Aggregation.match(Criteria.where("observation.observedProperty.theiaCategories").not().in(categoryUris));
        GroupOperation g1 = Aggregation.group("producer.producerId");
        return this.mongoDbUtils.aggregateToList("observations", Document.class,false,u1,m1,m2,g1).stream().map((t) -> {
            return t.get("_id", String.class);
        }).collect(Collectors.toList());
    }

    @Override
    public List<String> getProducerImpactedByTheiaVariableUriChange(List<String> variableUris) {
        MatchOperation m1 = Aggregation.match(Criteria.where("observation.observedProperty.theiaVariable").exists(true));
        ProjectionOperation p1 = Aggregation.project().and("producer.producerId").as("producer.producerId").and("observation.observedProperty.theiaVariable.uri").as("observation.observedProperty.theiaVariable.uri");
        MatchOperation m2 = Aggregation.match(Criteria.where("observation.observedProperty.theiaVariable.uri").not().in(variableUris));
        GroupOperation g1 = Aggregation.group("producer.producerId");
        return this.mongoDbUtils.aggregateToList( "observations", Document.class, true,m1,p1,m2,g1).stream().map((t) -> {
            return t.get("_id", String.class);
        }).collect(Collectors.toList());
    }

    @Override
    public void removeDummyCategoriesOfObservationsCollection(List<String> categoryUris) {
        UnwindOperation u1 = Aggregation.unwind("observation.observedProperty.theiaCategories");
        MatchOperation m1 = Aggregation.match(Criteria.where("observation.observedProperty.theiaVariable").exists(true));
        MatchOperation m2 = Aggregation.match(Criteria.where("observation.observedProperty.theiaCategories").in(categoryUris));
        GroupOperation g1 = Aggregation.group("_id")
                .first("documentId").as("documentId")
                .first("version").as("version")
                .push("observation.observedProperty.theiaCategories").as("theiaCategories")
                .first("producer").as("producer")
                .first("dataset").as("dataset")
                .first("observation").as("observation");
        AddFieldsOperation a1 = Aggregation.addFields().addFieldWithValue("observation.observedProperty.theiaCategories","$theiaCategories").build();
        List<ObservationDocument> documents = this.mongoDbUtils.aggregateToList("observations",ObservationDocument.class,true,u1,m1,m2,g1,a1);

        this.mongoDbUtils.dropCollection("observations");
        this.observationDocumentRepository.saveAll(documents);
    }

    @Override
    public void removeDummyCategoriesOfVariableAssociationCollection(List<String> categoryUris) {
        ProjectionOperation p1 = Aggregation.project().and("theiaVariable.broaderCategories").as("tmpCategories");
        UnwindOperation u1 = Aggregation.unwind("tmpCategories");
        MatchOperation m1 = Aggregation.match(Criteria.where("tmpCategories").in(categoryUris));
        GroupOperation g1 = Aggregation.group("_id")
                .first("theiaVariable").as("theiaVariable")
                .first("producerId").as("producerId")
                .push("tmpCategories").as("tmpCategories")
                .first("producerVariableNameEn").as("producerVariableNameEn")
                .first("isActive").as("isActive");
        AddFieldsOperation a1 = Aggregation.addFields().addFieldWithValue("theiaVariable.broaderCategories","$tmpCategories").build();
        //OutOperation o1 = Aggregation.out("variableAssociations");
        List<VariableAssociation> variableAssociations =  this.mongoDbUtils.aggregateToList("variableAssociations", VariableAssociation.class,true,p1,u1,m1,g1,a1);
        this.mongoDbUtils.dropCollection("variableAssociations");
        this.variableAssociationRepository.saveAll(variableAssociations);
    }

    @Override
    public void removeDummyVariablesOfVariableAssociationCollection(List<String> theiaVariablesUris) {
        MatchOperation m1 = Aggregation.match(Criteria.where("theiaVariable.uri").in(theiaVariablesUris));
        //OutOperation o1 = Aggregation.out("variableAssociations");
        List<VariableAssociation> variableAssociations = this.mongoDbUtils.aggregateToList("variableAssociations", VariableAssociation.class,false,m1);
        this.mongoDbUtils.dropCollection("variableAssociations");
        this.variableAssociationRepository.saveAll(variableAssociations);
    }

    @Override
    public void updatePrefLabelInVariableAssociationCollection(String uri, List<I18n> preflabelFuseki) {
        Query query = Query.query(Criteria.where("theiaVariable.uri").is(uri));
        List<Document> prefLabelUpdated = new ArrayList<>();
        for (I18n pref : preflabelFuseki) {
            Document d = new Document();
            d.append("lang", pref.getLang());
            d.append("text", pref.getText());
            prefLabelUpdated.add(d);
        }
        Update update = Update.update("theiaVariable.prefLabel", prefLabelUpdated);
        this.mongoDbUtils.updateMulti(query, update, "variableAssociations");

    }

}
