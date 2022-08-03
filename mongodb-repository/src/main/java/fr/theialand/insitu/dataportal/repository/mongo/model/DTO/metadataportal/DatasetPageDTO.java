package fr.theialand.insitu.dataportal.repository.mongo.model.DTO.metadataportal;

import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.dataset.Keyword;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.dataset.SpatialExtent;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.dataset.TemporalExtent;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.observation.I18n;

import java.util.List;

public class DatasetPageDTO {
    private String datasetId, producerId;
    private List<I18n> description, objective, producerName, title;
    private List<Keyword> keywords;
    private SpatialExtent spatialExtent;
    private TemporalExtent temporalExtent;

    public String getDatasetId() {
        return datasetId;
    }

    public void setDatasetId(String datasetId) {
        this.datasetId = datasetId;
    }

    public String getProducerId() {
        return producerId;
    }

    public void setProducerId(String producerId) {
        this.producerId = producerId;
    }

    public List<I18n> getDescription() {
        return description;
    }

    public void setDescription(List<I18n> description) {
        this.description = description;
    }

    public List<I18n> getObjective() {
        return objective;
    }

    public void setObjective(List<I18n> objective) {
        this.objective = objective;
    }

    public List<I18n> getProducerName() {
        return producerName;
    }

    public void setProducerName(List<I18n> producerName) {
        this.producerName = producerName;
    }

    public List<I18n> getTitle() {
        return title;
    }

    public void setTitle(List<I18n> title) {
        this.title = title;
    }

    public List<Keyword> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<Keyword> keywords) {
        this.keywords = keywords;
    }

    public SpatialExtent getSpatialExtent() {
        return spatialExtent;
    }

    public void setSpatialExtent(SpatialExtent spatialExtent) {
        this.spatialExtent = spatialExtent;
    }

    public TemporalExtent getTemporalExtent() {
        return temporalExtent;
    }

    public void setTemporalExtent(TemporalExtent temporalExtent) {
        this.temporalExtent = temporalExtent;
    }
}
