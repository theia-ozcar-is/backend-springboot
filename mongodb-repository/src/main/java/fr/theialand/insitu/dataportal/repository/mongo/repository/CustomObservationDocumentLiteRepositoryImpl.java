package fr.theialand.insitu.dataportal.repository.mongo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeospatialIndex;
import org.springframework.data.mongodb.core.index.Index;
import org.springframework.data.mongodb.core.index.TextIndexDefinition;

import java.util.HashMap;
import java.util.Map;

public class CustomObservationDocumentLiteRepositoryImpl implements CustomObservationDocumentLiteRepository {

    private final MongoDbUtils mongoDbUtils;

    @Autowired
    public CustomObservationDocumentLiteRepositoryImpl(MongoDbUtils mongoDbUtils) {
        this.mongoDbUtils = mongoDbUtils;
    }

    @Override
    public void insertIndexes() {
        if (!mongoDbUtils.getMongoTemplate().indexOps("observationsLite").getIndexInfo().stream().anyMatch(indexInfo -> indexInfo.getName().equals("observationsLite-text-search-index"))) {
            TextIndexDefinition textIndex = new TextIndexDefinition.TextIndexDefinitionBuilder().named("observationsLite-text-search-index")
                    .onField("dataset.metadata.description.text", 1F)
                    .onField("dataset.metadata.inspireTheme", 5F)
                    .onField("dataset.metadata.keywords.keyword.text", 5F)
                    .onField("dataset.metadata.title.text", 2F)
                    .onField("featureOfInterest.samplingFeature.name.text", 5F)
                    .onField("observations.observedProperty.description.text", 1F)
                    .onField("observations.observedProperty.gcmdKeywords.term", 2F)
                    .onField("observations.observedProperty.gcmdKeywords.topic", 2F)
                    .onField("observations.observedProperty.gcmdKeywords.variableLevel1", 1F)
                    .onField("observations.observedProperty.gcmdKeywords.variableLevel2", 1F)
                    .onField("observations.observedProperty.gcmdKeywords.variableLevel3", 1F)
                    .onField("observations.observedProperty.name.text", 20F)
                    .onField("theiaVariable.prefLabel.text", 20F)
                    .onField("producer.fundings.acronym", 2F)
                    .onField("producer.fundings.name.text", 2F)
                    .onField("producer.name.text", 20F)
                    .onField("producer.title.text", 5F)
                    .build();
            mongoDbUtils.getMongoTemplate().indexOps("observationsLite").ensureIndex(textIndex);
        }

        Map<String,String> simpleIndexes  = new HashMap<>();
        simpleIndexes.put("observationLite-climates-index","dataset.metadata.portalSearchCriteria.climates");
        simpleIndexes.put("observationLite-geologies-index","dataset.metadata.portalSearchCriteria.geologies");
        simpleIndexes.put("observationLite-producer-index","producer.name.text");
        simpleIndexes.put("observationLite-fundings-index","producer.fundings.name.text");
        simpleIndexes.put("observationLite-theiaCategories-index","theiaCategories.uri");
        simpleIndexes.put("observationLite-theiaVariable-index","theiaVariable.uri");
        simpleIndexes.put("observationLite-administrativeFeatures-index","featureOfInterest.sampledFeatures.administrativeFeatures.name");

        simpleIndexes.forEach((name, field) -> {
            if (!mongoDbUtils.getMongoTemplate().indexOps("observationsLite").getIndexInfo().stream().anyMatch(indexInfo -> indexInfo.getName().equals(name))) {
                mongoDbUtils.getMongoTemplate().indexOps("observationsLite").ensureIndex(new Index().named(name).on(field, Sort.Direction.ASC));
            }
        });

        if (!mongoDbUtils.getMongoTemplate().indexOps("observationsLite").getIndexInfo().stream().anyMatch(indexInfo -> indexInfo.getName().equals("observationLite-sampling-geometry-index"))) {
            GeospatialIndex geospatialIndex = new GeospatialIndex("featureOfInterest.samplingFeature.geometry")
                    .typed(GeoSpatialIndexType.GEO_2DSPHERE)
                    .named("observationLite-sampling-geometry-index");
            mongoDbUtils.getMongoTemplate().indexOps("observationsLite").ensureIndex(geospatialIndex);
        }

        //Hashed compound index cannot be ensured using spring-data-mongodb. This index was positionned manually in mongoshell
        // use theia-in-situ
        //db.observationsLite.createIndex( { "theiaVariableNameEn" : 1, "producerVariableNameEn" : "hashed" } , { name: "observationLite-variable-name-compound-index" })


        /*
if (!mongoDbUtils.getMongoTemplate().indexOps("observationsLite").getIndexInfo().stream().anyMatch(indexInfo -> indexInfo.getName().equals("observationLite-variable-name-compound-index"))) {
            mongoDbUtils.getMongoTemplate().indexOps("observationsLite").ensureIndex(new Index().on("theiaVariableNameEn", Sort.Direction.ASC).on("producerVariableNameEn", Sort.Direction.ASC).named("observationLite-variable-name-compound-index"));
        }*/
    }
}
