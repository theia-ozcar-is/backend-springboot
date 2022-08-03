package fr.theialand.insitu.dataportal.repository.mongo.service.search.metadata;


import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.entity.ObservationDocument;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.producer.Producer;
import fr.theialand.insitu.dataportal.repository.mongo.repository.MongoDbUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.ReplaceRootOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

@Service
public class DetailServiceImpl implements DetailService {
    private final MongoDbUtils mongoDbUtils;

    @Autowired
    public DetailServiceImpl(MongoDbUtils mongoDbUtils) {
        this.mongoDbUtils = mongoDbUtils;
    }




    /**
     * Get the detailed producer object to be displayed in info panel
     *
     * @param producerId id of hte producer to be displayed
     * @return Producer object
     */
    @Override
    public Producer getProducerDetailed(String producerId) {
        AggregationOperation m1 = Aggregation.match(Criteria.where("producer.producerId").is(producerId));
        AggregationOperation p1 = Aggregation.project("producer");
        AggregationOperation g1 = Aggregation.group("producer");
        ReplaceRootOperation r1 = Aggregation.replaceRoot("_id");
        return this.mongoDbUtils.aggregateToUnique("observations",Producer.class, true, m1,p1,g1,r1);

    }

    /**
     * Get the detailed dataset object to be displayed iin hte info panel
     *
     * @param datasetId id of the dataset
     * @return ObservationDocument object with Producer and Dataset object properties
     */
    @Override
    public ObservationDocument getDatasetDetailed(String datasetId)  {
        AggregationOperation m1 = Aggregation.match(Criteria.where("dataset.datasetId").is(datasetId));
        AggregationOperation p1 = Aggregation.project("producer", "dataset");
        AggregationOperation g1 = Aggregation.group("producer", "dataset");
        ReplaceRootOperation r1 = Aggregation.replaceRoot("_id");
        ObservationDocument observationDocument = this.mongoDbUtils.aggregateToUnique("observations",ObservationDocument.class, true, m1,p1,g1,r1);
        return this.mongoDbUtils.aggregateToUnique("observations",ObservationDocument.class, true, m1,p1,g1,r1);
    }
}
