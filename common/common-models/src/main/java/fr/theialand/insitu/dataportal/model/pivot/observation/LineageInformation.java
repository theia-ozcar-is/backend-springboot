/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.theialand.insitu.dataportal.model.pivot.observation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

import java.util.Date;

/**
 *
 * @author coussotc
 */
public class LineageInformation {

    @JsonProperty(required = true)
    @JsonPropertyDescription("Description of the process applied to the observation")
    private String processingDescription;

    @JsonPropertyDescription("Date when the process has been applid to the observation")
    private Date processingDate;


    public String getProcessingDescription() {
        return processingDescription;
    }

    public Date getProcessingDate() {
        return processingDate;
    }
}
