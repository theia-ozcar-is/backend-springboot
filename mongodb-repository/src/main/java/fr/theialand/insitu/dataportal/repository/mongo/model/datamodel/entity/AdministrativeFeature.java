package fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.entity;

import com.mongodb.client.model.geojson.Geometry;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.geojsonproperties.AdministrativeFeatureProperties;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "administrativeFeatures")
public class AdministrativeFeature  {
    private String type ="Feature";

    @GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE)
    private Geometry geometry;

    private AdministrativeFeatureProperties properties;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = "Feature";
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    public AdministrativeFeatureProperties getProperties() {
        return properties;
    }

    public void setProperties(AdministrativeFeatureProperties properties) {
        this.properties = properties;
    }
}
