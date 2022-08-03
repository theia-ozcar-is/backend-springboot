package fr.theialand.insitu.dataportal.repository.mongo.geojson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class CustomGeoObsDocRepositoryImpl implements CustomGeoObsDocRepository {

    MongoTemplate mongoTemplate;

    @Autowired
    private CustomGeoObsDocRepositoryImpl( MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<TestObsDoc> findGeometryIsInside(GeoJsonPoint pt) {

        Query q = new Query(Criteria.where("geom").intersects(pt));
        return mongoTemplate.find(q , TestObsDoc.class);
    }

}
