/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.theialand.insitu.dataportal.repository.mongo.repository;


import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.entity.ObservationDocument;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.lang.NonNull;

import java.util.List;

/**
 * Interface used by Spring IoC to inject ObservationDocumentRepository implementation when needed
 * simple CRUD operations are defined in MongoRepository interface
 * Spring data will defined ObservationDocumentRepositoryImpl.class that will be injected and instanciated by classes 
 * needing it
 */
public interface ObservationDocumentRepository extends MongoRepository<ObservationDocument, ObjectId>, CustomObservationDocumentRepository {
    
    /**
     * 
     * @param documentIds String - Id of the document such as '["id","id","id"]'
     * @return ObservationDocument.class
     */
    ObservationDocument findByDocumentId(String documentIds);

    /**
     * Get first Observation found for a given ProducerID
     * @param producerId , such as "TOUR"
     * @return ObservationDocument
     */
    ObservationDocument findFirstByProducerProducerId ( @NonNull String producerId );

    /**
     * Get first Observation found for a given DatasetID
     * @param datasetId , such as "TOUR_DAT_frn-ges-eddycovariance"
     * @return ObservationDocument
     */
    ObservationDocument findFirstByDatasetDatasetId ( @NonNull String datasetId );

    /**
     * Get all Observations found for a given DatasetID
     * @param datasetId , such as "TOUR_DAT_frn-ges-eddycovariance"
     * @return List of ObservationDocument
     */
    List<ObservationDocument> findByDatasetDatasetId ( @NonNull String datasetId );

    /**
     * find all Obs for a given producer
     * @param producerId such as "TOUR"
     * @return List of {@link ObservationDocument}
     */
    //@Query("producer.producerId: requestedProducerId")
    //List<ObservationDocument> findAllObservationsMatchingProducer(@Param("requestedProducerId") String producerId) ;
    List<ObservationDocument> findByProducerProducerId(String producerId);

    /**
     * Delete the document of the collection matching producer.producerId
     * @param producerId String of the producer id (ex: "CATC")
     * @return The number of document deleted
     */
    Long deleteObservationDocumentByProducerProducerId(String producerId);

    List<ObservationDocument> findByProducerProducerIdAndObservationObservedPropertyNameTextInAndObservationObservedPropertyTheiaCategoriesIn(String producerId, String producerVariableNameEn, List<String> theiaCategoryUri);
    List<ObservationDocument> findByProducerProducerIdAndObservationObservedPropertyNameTextInAndObservationObservedPropertyTheiaVariableUri(String producerId, String producerVariableNameEn, String uri);
    List<ObservationDocument> findByProducerProducerIdAndObservationObservedPropertyNameTextInAndObservationObservedPropertyUnitTextInAndObservationObservedPropertyTheiaVariableUri(String producerId, String producerVariableNameEn, String unitNameEn, String uri);
    List<ObservationDocument> findByProducerProducerIdAndObservationObservedPropertyNameTextInAndObservationObservedPropertyUnitTextInAndObservationObservedPropertyTheiaCategoriesIn(String producerId, String producerVariableNameEn, String unitNameEn, List<String> theiaCategoryUri);
    boolean existsByProducerProducerIdAndObservationObservedPropertyNameTextInAndObservationObservedPropertyTheiaCategoriesInAndObservationObservedPropertyTheiaVariableExists(String producerId, String producerVariableNameEn, List<String> theiaCategoryUri, boolean exist);


    @Query(value="{ 'dataset.datasetId' : ?0 }", fields="{ 'observation.observedProperty' : 1, '_id' : 0}")
    List<ObservationDocument> findObservedPropertiesOfDataset(String datasetId);

    ObservationDocument findFirstByDatasetDatasetIdOrderByObservationTemporalExtentDateBegAsc(String datasetId);
}
