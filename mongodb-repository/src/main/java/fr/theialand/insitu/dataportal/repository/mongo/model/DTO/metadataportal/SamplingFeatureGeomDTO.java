package fr.theialand.insitu.dataportal.repository.mongo.model.DTO.metadataportal;

import com.mongodb.client.model.geojson.Geometry;

import java.util.List;

public class SamplingFeatureGeomDTO {
    List<String> documentIds;
    Geometry geometry;

    public List<String> getDocumentIds() {
        return documentIds;
    }

    public void setDocumentIds(List<String> documentIds) {
        this.documentIds = documentIds;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }
}
