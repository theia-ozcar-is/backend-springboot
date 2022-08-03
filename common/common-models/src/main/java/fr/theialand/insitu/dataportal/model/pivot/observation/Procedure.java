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
 *
 * @author coussotc
 */
@JsonClassDescription("The procedure, or observation process, is the description of the processus used to produce the result from an observed property of the feature of interest")
public class Procedure {

    @JsonPropertyDescription("object describing the dataProduction process of the observation")
    private DataProduction dataProduction;

    @JsonPropertyDescription("List of the LineageInformation object describing the diffrent processes the observation underwent before to present the final result")
    private List<LineageInformation> lineageInformations;

    public DataProduction getDataProduction() {
        return dataProduction;
    }

    public List<LineageInformation> getLineageInformations() {
        return lineageInformations;
    }
}
