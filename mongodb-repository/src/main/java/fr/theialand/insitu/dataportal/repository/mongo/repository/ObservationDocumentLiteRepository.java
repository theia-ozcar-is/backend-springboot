package fr.theialand.insitu.dataportal.repository.mongo.repository;

import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.entity.ObservationDocumentLite;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ObservationDocumentLiteRepository extends MongoRepository<ObservationDocumentLite, ObjectId>, CustomObservationDocumentLiteRepository {
    /**
     * Delete the document of the collection matching producer.producerId
     * @param producerId String of the producer id (ex: "CATC")
     * @return The number of document deleted
     */
    Long deleteObservationDocumentLiteByProducerProducerId(String producerId);

    /**
     * find all ObsLites by a producerID like CATC, TOUR ...
     * @param producerId String of the producer id (ex: "CATC")
     * @return list of obslites
     */
    List<ObservationDocumentLite> findByProducerProducerId(String producerId);
}
