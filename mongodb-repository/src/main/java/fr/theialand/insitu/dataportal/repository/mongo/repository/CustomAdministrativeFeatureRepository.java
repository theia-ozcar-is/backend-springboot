package fr.theialand.insitu.dataportal.repository.mongo.repository;

import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.geojsonproperties.AdministrativeFeatureProperties;
import org.springframework.data.mongodb.core.geo.GeoJson;

public interface CustomAdministrativeFeatureRepository {
    AdministrativeFeatureProperties findFirstByGeometryIntersectionAndAdminLevel(GeoJson geoJson, int adminLevel);
}
