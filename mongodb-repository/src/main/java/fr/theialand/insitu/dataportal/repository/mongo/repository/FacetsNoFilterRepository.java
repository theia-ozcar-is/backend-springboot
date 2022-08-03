package fr.theialand.insitu.dataportal.repository.mongo.repository;

import fr.theialand.insitu.dataportal.repository.mongo.model.view.facet.FacetClassification;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FacetsNoFilterRepository extends MongoRepository<FacetClassification, ObjectId>{
    FacetClassification findFirstBy();
}
