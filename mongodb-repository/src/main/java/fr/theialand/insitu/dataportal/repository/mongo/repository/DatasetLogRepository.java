package fr.theialand.insitu.dataportal.repository.mongo.repository;

import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.entity.DatasetLog;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface DatasetLogRepository extends MongoRepository<DatasetLog, ObjectId> {

    List<DatasetLog> findByDatasetIdOrderByDate(String datasetId);
}
