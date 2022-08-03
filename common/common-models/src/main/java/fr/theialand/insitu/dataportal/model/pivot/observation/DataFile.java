/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.theialand.insitu.dataportal.model.pivot.observation;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

/**
 * A file containing Result
 * @author coussotc
 */
@JsonClassDescription(" A file containing Result")
public class DataFile {

    @JsonPropertyDescription("The name of the file with the extension, example: temperature_time_serie.csv")
    private String name;

    public String getName() {
        return name;
    }
}
