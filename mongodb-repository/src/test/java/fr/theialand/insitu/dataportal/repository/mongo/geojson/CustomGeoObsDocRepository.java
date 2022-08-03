package fr.theialand.insitu.dataportal.repository.mongo.geojson;

import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import java.util.List;

public interface CustomGeoObsDocRepository {

    List<TestObsDoc> findGeometryIsInside(GeoJsonPoint geom);

}
