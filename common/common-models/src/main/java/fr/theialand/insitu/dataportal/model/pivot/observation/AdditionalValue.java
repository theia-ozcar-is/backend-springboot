/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.theialand.insitu.dataportal.model.pivot.observation;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author coussotc
 */
public class AdditionalValue {

    @JsonProperty(required = true)
    private String columnName;

    @JsonProperty(required = true)
    private String name;

    private String unit;

    private String description;

    public String getColumnName() {
        return columnName;
    }

    public String getName() {
        return name;
    }

    public String getUnit() {
        return unit;
    }

    public String getDescription() {
        return description;
    }
}
