/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.theialand.insitu.dataportal.model.pivot.observation;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

import java.util.List;

/**
 * The acquisition procedure
 *
 * @author coussotc
 */
@JsonClassDescription("DataProduction object describing the dataProduction process of the observation")
public class DataProduction {

    @JsonPropertyDescription("Descritpion of the method used for the acquisition of the data")
    private String method;

    @JsonPropertyDescription("Descritpion of the sensors used for the acquisition of the data")
    private List<Sensor> sensors;

    public String getMethod() {
        return method;
    }

    public List<Sensor> getSensors() {
        return sensors;
    }
}
