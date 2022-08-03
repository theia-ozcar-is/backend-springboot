/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.theialand.insitu.dataportal.model.pivot.observation;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

/**
 * The feature of interest is an abstraction of a real-world object. It's the spatial object
 * from which one characteristic is sampled by the observation
 * @author coussotc
 */
@JsonClassDescription("The feature of interest is an abstraction of a real-world object. It's the spatial object sample by the Observation")
public class FeatureOfInterest {

    @JsonProperty(required = true)
    @JsonPropertyDescription("Spatial representation of the feature of interest")
    private SamplingFeature samplingFeature;

    // ?? todo , not in the docs
//    private SampledFeatures sampledFeatures;

    public SamplingFeature getSamplingFeature() {
        return samplingFeature;
    }

//    public SampledFeatures getSampledFeatures() {
//        return sampledFeatures;
//    }
}
