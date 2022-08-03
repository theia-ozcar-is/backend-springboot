package fr.theialand.insitu.dataportal.repository.mongo.repository;

import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.geojsonproperties.AdministrativeFeatureProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.LimitOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ReplaceRootOperation;
import org.springframework.data.mongodb.core.geo.GeoJson;
import org.springframework.data.mongodb.core.query.Criteria;

public class CustomAdministrativeFeatureRepositoryImpl implements CustomAdministrativeFeatureRepository {
    private final  MongoDbUtils mongoDbUtils;

    @Autowired
    public CustomAdministrativeFeatureRepositoryImpl(MongoDbUtils mongoDbUtils) {
        this.mongoDbUtils = mongoDbUtils;
    }

    @Override
    public AdministrativeFeatureProperties findFirstByGeometryIntersectionAndAdminLevel(GeoJson geoJson, int adminLevel) {
        MatchOperation m1 = Aggregation.match(Criteria.where("properties.admin_level").is(adminLevel).and("geometry").intersects(geoJson));
        LimitOperation l1 = Aggregation.limit(1);
        ReplaceRootOperation p1 = Aggregation.replaceRoot("properties");
        return this.mongoDbUtils.aggregateToUnique("administrativeFeatures", AdministrativeFeatureProperties.class, false, m1,l1,p1);
    }
}
