/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.theialand.insitu.dataportal.model.pivot.observation;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

import java.util.List;

/**
 * Class representing the numerical result of an observation. 
 * The result of an observation is an estimate value of a property of the feature of interest
 * @author coussotc
 */
@JsonClassDescription("Class representing the numerical result of an observation. The result of an observation is an estimate value of a property of the feature of interest")
public class NumericalResult extends Result {
    
    @JsonPropertyDescription("character String of missing data value in the output file")
    private String missingValue;
    
    @JsonPropertyDescription("List of QualityFlag object describing the quality flag of the observation")
    private List<QualityFlag> qualityFlags;

    @JsonPropertyDescription("List of additional value that can be set for each time step. For exemple, incertitude calculation, error,sensor params")
    private List<AdditionalValue> additionalValues;

    @JsonProperty(required = true)
    @JsonPropertyDescription("DataFile object representing the file containing the data of the observation")
    private DataFile dataFile;


    public String getMissingValue() {
        return missingValue;
    }

    public List<QualityFlag> getQualityFlags() {
        return qualityFlags;
    }

    public List<AdditionalValue> getAdditionalValues() {
        return additionalValues;
    }

    public DataFile getDataFile() {
        return dataFile;
    }
}
