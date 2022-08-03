/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.theialand.insitu.dataportal.repository.mongo.model.view.popup;

import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.observation.FeatureOfInterest;

/**
 *
 * @author coussotc
 */
public class ObservationLitePopup {

    /**
     * The feature of interest is an abstraction of a real-world object. It's the spatial object sample by the
     * Observation
     */
    private FeatureOfInterest featureOfInterest;
    /**
     * The observed property is a characteristic of the feature of interest. It identifies or describes the phenomenon
     * for which the observation is made.
     */
    private ObservedPropertyLitePopup observedProperty;

    /**
     * The id of the observation
     */
    private String observationId;

    public String getObservationId() {
        return observationId;
    }

    public void setObservationId(String observationId) {
        this.observationId = observationId;
    }
    
    
    
    public FeatureOfInterest getFeatureOfInterest() {
        return featureOfInterest;
    }
    
    public void setFeatureOfInterest(FeatureOfInterest featureOfInterest) {
        this.featureOfInterest = featureOfInterest;
    }

    public ObservedPropertyLitePopup getObservedProperty() {
        return observedProperty;
    }

    public void setObservedProperty(ObservedPropertyLitePopup observedProperty) {
        this.observedProperty = observedProperty;
    }
    
    

}
