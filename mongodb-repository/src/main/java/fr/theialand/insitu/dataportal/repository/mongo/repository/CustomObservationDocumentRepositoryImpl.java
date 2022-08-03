package fr.theialand.insitu.dataportal.repository.mongo.repository;

import fr.theialand.insitu.dataportal.repository.mongo.model.DTO.metadataportal.SamplingFeatureGeomDTO;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.index.Index;
import org.springframework.data.mongodb.core.query.Criteria;

import java.util.List;
import java.util.stream.Collectors;

public class CustomObservationDocumentRepositoryImpl implements CustomObservationDocumentRepository {
    private MongoDbUtils mongoDbUtils;

    @Autowired
    public CustomObservationDocumentRepositoryImpl(MongoDbUtils mongoDbUtils) {
        this.mongoDbUtils = mongoDbUtils;
    }

    @Override
    public List<String> getProducerIds() {
        GroupOperation g1 = Aggregation.group("producer.producerId");
        return this.mongoDbUtils.aggregateToList("observations", Document.class, false, g1).stream().map((t) -> {
            return t.get("_id", String.class);
        }).collect(Collectors.toList());
    }

    @Override
    public List<SamplingFeatureGeomDTO> getByProducerIdGroupBySamplingFeatureGeometry(String producerId) {
        GroupOperation g1 = Aggregation.group("observation.featureOfInterest.samplingFeature.geometry").push("documentId").as("documentIds");
        MatchOperation m1 = Aggregation.match(Criteria.where("producer.producerId").is(producerId));
        ProjectionOperation p1 = Aggregation.project().and("_id").as("geometry").and("documentIds").as("documentIds").andExclude("_id");
        //ReplaceRootOperation r1 = Aggregation.replaceRoot("_id");
        return this.mongoDbUtils.aggregateToList("observations", SamplingFeatureGeomDTO.class, true, m1, g1, p1);
    }

    @Override
    public void insertIndexes() {
        if (!this.mongoDbUtils.getMongoTemplate().indexOps("observations").getIndexInfo().stream().anyMatch(indexInfo -> indexInfo.getName().equals("observations-documentId-index"))) {
            this.mongoDbUtils.getMongoTemplate().indexOps("observations").ensureIndex(new Index().on("documentId", Sort.Direction.ASC).named("observations-documentId-index"));
        }
    }
}
