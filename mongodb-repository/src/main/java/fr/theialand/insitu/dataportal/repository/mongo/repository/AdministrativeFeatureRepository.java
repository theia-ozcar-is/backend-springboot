package fr.theialand.insitu.dataportal.repository.mongo.repository;

import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.entity.AdministrativeFeature;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AdministrativeFeatureRepository  extends MongoRepository<AdministrativeFeature, ObjectId>,CustomAdministrativeFeatureRepository {

}
