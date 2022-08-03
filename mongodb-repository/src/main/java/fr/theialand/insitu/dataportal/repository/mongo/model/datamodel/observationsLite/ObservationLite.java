/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observationsLite;

import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.dataset.TemporalExtent;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.observation.FeatureOfInterest;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.observation.ObservedProperty;

/**
 *
 * @author coussotc
 */
public class ObservationLite {

    /**
     * Id of the observation. Corresponds to the trigramme of the provider, the number of the dataset, and the number of
     * the observation of the dataset separated by underscores (ex: AMA_2_36);
     */
    private String observationId;
    /**
     * The feature of interest is an abstraction of a real-world object. It's the spatial object sample by the
     * Observation
     */
    private FeatureOfInterest featureOfInterest;
    /**
     * The observed property is a characteristic of the feature of interest. It identifies or describes the phenomenon
     * for which the observation is made.
     */
    private ObservedProperty observedProperty;
    /**
     * Temporalextent of the observation. For result that are not time series, the date acquisition of the observation
     * is precised. For results that are not temporal (e.g. geological map the date of acquisition of the observation is
     * precised followed by a 31-12-9999 00:00:00
     */
    private TemporalExtent temporalExtent;

    /**
     * Data type of the observation
     */
    private String dataType;

    /**
     * Wheither the observation is a time serie
     */
    private String timeSerie;

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getTimeSerie() {
        return timeSerie;
    }

    public void setTimeSerie(String timeSerie) {
        this.timeSerie = timeSerie;
    }

    public FeatureOfInterest getFeatureOfInterest() {
        return featureOfInterest;
    }

    public String getObservationId() {
        return observationId;
    }

    public void setObservationId(String observationId) {
        this.observationId = observationId;
    }

    public TemporalExtent getTemporalExtent() {
        return temporalExtent;
    }

    public void setTemporalExtent(TemporalExtent temporalExtent) {
        this.temporalExtent = temporalExtent;
    }

    public ObservedProperty getObservedProperty() {
        return observedProperty;
    }

    public void setObservedProperty(ObservedProperty observedProperty) {
        this.observedProperty = observedProperty;
    }

    public void setFeatureOfInterest(FeatureOfInterest featureOfInterest) {
        this.featureOfInterest = featureOfInterest;
    }
}
