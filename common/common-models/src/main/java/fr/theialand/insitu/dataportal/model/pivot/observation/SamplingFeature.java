/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.theialand.insitu.dataportal.model.pivot.observation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import fr.theialand.insitu.dataportal.model.pivot.geojsonproperties.Properties;


/**
 * Abstract class representing the different form of feature that can be sampled by an observation
 * @author coussotc
 */
@JsonSubTypes({
        @JsonSubTypes.Type(SamplingGeometry.class),
        @JsonSubTypes.Type(SamplingSpecimen.class)
})
@JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION)  // Jackson > 2.12 auto deserialize to the correct class ! no need for JsonDeserialiser
public abstract class SamplingFeature {

    @JsonProperty(required = true)
    @JsonPropertyDescription("The name of the feature. i.e. the name of the station")
    private String name;

    // ?? not in hte docs
    private String type;

    // ?? not in te docs
    private Properties properties;

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public Properties getProperties() {
        return properties;
    }
}
