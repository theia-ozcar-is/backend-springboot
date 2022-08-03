package fr.theialand.insitu.dataportal.repository.mongo.repository;

import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.entity.DatasetLog;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * complement the official {@link DatasetLogRepository} with some methods for deleting test stuff
 * not meaningful for prod use
 */
public interface DatasetLogTestRepository extends MongoRepository<DatasetLog, ObjectId> {

    void deleteDatasetLogsByDatasetId( String datasetId );

}
