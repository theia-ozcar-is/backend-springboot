/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.theialand.insitu.dataportal.model.pivot.observation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;


/**
 * Class describing a virtual Sensor. This sensor can be a fr.theia_land.in_situ.dataportal.model used to produce simulated data
 * @author coussotc
 */
public class VirtualSensor extends Sensor {

    @JsonProperty(required = true)
    @JsonPropertyDescription("The name of the model")
    private String name;

    @JsonPropertyDescription("The descirption of the paramter and different forcing for the observation")
    private String parametrisationDescription ;

    public String getName() {
        return name;
    }

    public String getParametrisationDescription() {
        return parametrisationDescription;
    }
}

