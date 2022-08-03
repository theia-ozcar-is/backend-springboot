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
public class QualityFlag {

    @JsonProperty(required = true)
    private String code;

    @JsonProperty(required = true)
    private String description;

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
