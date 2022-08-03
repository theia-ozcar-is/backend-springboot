package fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.entity;


import com.mongodb.client.model.geojson.Geometry;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.geojsonproperties.KoppenClassifcationFeatureProperties;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "koppenClassificationFeatures")
public class KoppenClassifcationFeature {

    private String type ="Feature";

    @GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE)
    private Geometry geometry;
    private KoppenClassifcationFeatureProperties properties;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = "feature";
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    public KoppenClassifcationFeatureProperties getProperties() {
        return properties;
    }

    public void setProperties(KoppenClassifcationFeatureProperties properties) {
        this.properties = properties;
    }
}
