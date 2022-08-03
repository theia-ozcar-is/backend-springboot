/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.theialand.insitu.dataportal.repository.mongo.repository;

import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.entity.MapItem;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 *
 * @author coussotc
 */
public interface MapItemRepository extends MongoRepository<MapItem, ObjectId>, CustomMapItemRepository{
    //List<MapItem> findByDatasetId(String datasetId);
    List<MapItem> findByObservationIdsIn(List<String> ids);
    /**
     * Delete the document of the collection matching producer.producerId
     * @param producerId String of the producer id (ex: "CATC")
     * @return The number of document deleted
     */
    Long deleteMapItemByProducerId(String producerId);
}
