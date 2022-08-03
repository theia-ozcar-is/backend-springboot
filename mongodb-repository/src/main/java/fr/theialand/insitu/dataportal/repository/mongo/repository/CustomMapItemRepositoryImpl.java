package fr.theialand.insitu.dataportal.repository.mongo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.index.Index;


public class CustomMapItemRepositoryImpl implements CustomMapItemRepository{
    private MongoDbUtils mongoDbUtils;

    @Autowired
    public CustomMapItemRepositoryImpl(MongoDbUtils mongoDbUtils) {
        this.mongoDbUtils = mongoDbUtils;
    }

    @Override
    public void insertIndexes() {
        if (!this.mongoDbUtils.getMongoTemplate().indexOps("mapItems").getIndexInfo().stream().anyMatch(indexInfo -> indexInfo.getName().equals("mapItems-observationsIds-index"))) {
            this.mongoDbUtils.getMongoTemplate().indexOps("mapItems").ensureIndex(new Index().on("observationIds", Sort.Direction.ASC).named("mapItems-observationsIds-index"));
        }
    }
}
