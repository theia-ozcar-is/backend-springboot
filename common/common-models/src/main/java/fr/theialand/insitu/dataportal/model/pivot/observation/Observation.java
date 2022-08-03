/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.theialand.insitu.dataportal.model.pivot.observation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import fr.theialand.insitu.dataportal.model.pivot.dataset.TemporalExtent;
import fr.theialand.insitu.dataportal.model.pivot.enumerations.EnumProcessingLevels;
import fr.theialand.insitu.dataportal.model.pivot.enumerations.EnumResultDataTypes;

import javax.validation.constraints.Pattern;


/**
 * Class representing an Observation. Each datasets in composed of observations.
 An observation is an action whose the NumericalResult is a value of a Property of the 
 FeatureOfInterest at a given time, obtained following a Procedure.
 * @author coussotc
 */
public class Observation {

    @JsonProperty(required = true)
    @Pattern(regexp = "^[A-Z]{4}_OBS_[^\\s]{1,}$")
    @JsonPropertyDescription("Id of the observation. Corresponds to the trigramme of the provider, the number of the dataset, and the number of the observation of the dataset separated by underscores (ex: CATC_2_36")
    private String observationId;

    @JsonProperty(required = true)
    @JsonPropertyDescription("characteristic of the feature of interest. It identifies or describes the phenomenon for which the observation is made")
    private ObservedProperty observedProperty;

    @JsonProperty(required = true)
    @JsonPropertyDescription("The feature of interest is an abstraction of a real-world object. It's the spatial object sample by the Observation")
    private FeatureOfInterest featureOfInterest;

    @JsonPropertyDescription("the description of the processus used to produce the result from an observed property of the feature of interest")
    private Procedure procedure;

    @JsonProperty(required = true)
    @JsonPropertyDescription("The result of an observation is an estimate value of a property of the feature of interest")
    private NumericalResult result;

    @JsonProperty(required = true)
    @JsonPropertyDescription("The type of the result")
    private EnumResultDataTypes dataType;

    @JsonProperty(required = true)
    @JsonPropertyDescription("boolean in order to know if the result is a time serie or not")
    private boolean timeSerie;

    /**
     * The temporalextent of the result. For result that are not time series, the date acquisition of the
     * observation is precised. For results that are not temporal (e.g. geological map the date of acquisition of
     * the observation is precised followed by a 31-12-9999 00:00:00
     */
    @JsonProperty(required = true)
    @JsonPropertyDescription("For result that are not time series, the date acquisition of the observation is precised. For results that are not temporal (e.g. geological map the date of acquisition of the observation is precised followed by a 31-12-9999 00:00:00")
    private TemporalExtent temporalExtent;

    //@JsonProperty(required = true) // fixme : doc says it is required, but it is missing in CATC
    @JsonPropertyDescription("The level of processing for the data of the observation")
    private EnumProcessingLevels processingLevel;

    public String getObservationId() {
        return observationId;
    }

    public ObservedProperty getObservedProperty() {
        return observedProperty;
    }

    public FeatureOfInterest getFeatureOfInterest() {
        return featureOfInterest;
    }

    public Procedure getProcedure() {
        return procedure;
    }

    public NumericalResult getResult() {
        return result;
    }

    public EnumResultDataTypes getDataType() {
        return dataType;
    }

    public boolean isTimeSerie() {
        return timeSerie;
    }

    public TemporalExtent getTemporalExtent() {
        return temporalExtent;
    }

    public EnumProcessingLevels getProcessingLevel() {
        return processingLevel;
    }
}

