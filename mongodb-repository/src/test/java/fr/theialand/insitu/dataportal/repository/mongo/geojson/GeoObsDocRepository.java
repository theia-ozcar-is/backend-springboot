package fr.theialand.insitu.dataportal.repository.mongo.geojson;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.geo.GeoJsonPolygon;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface GeoObsDocRepository extends MongoRepository<TestObsDoc, ObjectId> , CustomGeoObsDocRepository {

    void deleteByDocumentIdStartingWith(String regex);

    List<TestObsDoc> findByGeomWithin(GeoJsonPolygon box);


}
