package fr.theialand.insitu.dataportal.repository.mongo.model.DTO.metadataportal;

import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.entity.TheiaCategory;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.geojsonproperties.Properties;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.observation.TheiaVariable;
import org.springframework.data.mongodb.core.geo.GeoJsonPolygon;
import java.time.Instant;
import java.util.List;

public class FacetFiltersDTO {
    private List<String> climates;
    private List<String> fullText;
    private List<String> fundingNames;
    private List<String> geologies;
    private List<String> producerNames;
    private FacetFiltersDTO.FeatureCollectionFilter spatialExtent;
    private List<FacetFiltersDTO.TemporalExtentFilter> temporalExtents;
    private List<TheiaCategory> theiaCategories;
    private List<TheiaVariable> theiaVariables;
    private List<Properties> featureOfInterests;

    public <T extends Properties> List<T> getFeatureOfInterests() {
        return (List<T>) featureOfInterests;
    }

    public <T extends Properties> void setFeatureOfInterests(List<T> featureOfInterests) {
        this.featureOfInterests = (List<Properties>) featureOfInterests;
    }

    public List<String> getClimates() {
        return climates;
    }

    public void setClimates(List<String> climates) {
        this.climates = climates;
    }

    public List<String> getFullText() {
        return fullText;
    }

    public void setFullText(List<String> fullText) {
        this.fullText = fullText;
    }

    public List<String> getFundingNames() {
        return fundingNames;
    }

    public void setFundingNames(List<String> fundingNames) {
        this.fundingNames = fundingNames;
    }

    public List<String> getGeologies() {
        return geologies;
    }

    public void setGeologies(List<String> geologies) {
        this.geologies = geologies;
    }

    public List<String> getProducerNames() {
        return producerNames;
    }

    public void setProducerNames(List<String> producerNames) {
        this.producerNames = producerNames;
    }

    public FeatureCollectionFilter getSpatialExtent() {
        return spatialExtent;
    }

    public void setSpatialExtent(FeatureCollectionFilter spatialExtent) {
        this.spatialExtent = spatialExtent;
    }

    public List<TemporalExtentFilter> getTemporalExtents() {
        return temporalExtents;
    }

    public void setTemporalExtents(List<TemporalExtentFilter> temporalExtents) {
        this.temporalExtents = temporalExtents;
    }

    public List<TheiaCategory> getTheiaCategories() {
        return theiaCategories;
    }

    public void setTheiaCategories(List<TheiaCategory> theiaCategories) {
        this.theiaCategories = theiaCategories;
    }

    public List<TheiaVariable> getTheiaVariables() {
        return theiaVariables;
    }

    public void setTheiaVariables(List<TheiaVariable> theiaVariables) {
        this.theiaVariables = theiaVariables;
    }

    public static class FeatureCollectionFilter {
        private String type = "FeatureCollection";
        private List<GeoJsonPolygonFeature> features;
        public FeatureCollectionFilter() {
        }

        public String getType() {
            return type;
        }

        public List<GeoJsonPolygonFeature> getFeatures() {
            return features;
        }

        public void setFeatures(List<GeoJsonPolygonFeature> features) {
            this.features = features;
        }

        public static class GeoJsonPolygonFeature {
            private String type = "Feature";
            private Object properties;
            private GeoJsonPolygon geometry;

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public Object getProperties() {
                return properties;
            }

            public void setProperties(Object properties) {
                this.properties = properties;
            }

            public GeoJsonPolygon getGeometry() {
                return geometry;
            }

            public void setGeometry(GeoJsonPolygon geometry) {
                this.geometry = geometry;
            }
        }
    }


    public static class TemporalExtentFilter {
        private int position;
        private Instant fromDate = Instant.parse("1900-01-01T01:00:00.000Z");
        private Instant toDate = Instant.now();


        public int getPosition() {
            return position;
        }

        public void setPosition(int position) {
            this.position = position;
        }

        public Instant getFromDate() {
            return fromDate;
        }

        public void setFromDate(Instant fromDate) {
            if(fromDate != null) {
                this.fromDate = fromDate;
            }
        }

        public Instant getToDate() {
            return toDate;
        }

        public void setToDate(Instant toDate) {
            if (toDate != null){
                this.toDate = toDate;
            }
        }
    }

}
