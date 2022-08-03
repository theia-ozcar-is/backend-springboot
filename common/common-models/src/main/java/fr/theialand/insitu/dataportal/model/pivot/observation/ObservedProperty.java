/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.theialand.insitu.dataportal.model.pivot.observation;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import fr.theialand.insitu.dataportal.model.pivot.dataset.Keyword;

import java.net.URI;
import java.util.List;

/**
 * The observed property is a characteristic of the feature of interest. It identifies or describes the phenomenon for
 * which the observation is made.
 *
 * @author coussotc
 */
@JsonClassDescription("The observed property is a characteristic of the feature of interest. It identifies or describes the phenomenon for which the observation is made.\n")
public class ObservedProperty {

    @JsonProperty(required = true)
    @JsonPropertyDescription("Name of the variable")
    private String name;

    @JsonProperty(required = true)
    @JsonPropertyDescription("Unit of the variable, or 'N/A' for Not Applicable")
    private String unit;

    @JsonPropertyDescription("Short description of the variable")
    private String description;

    @JsonPropertyDescription("Gcmd Keywords that the scientist wants to associate to the variable. All the association made by the Theia/OZCAR team will be associated later")
    private List<GcmdKeyword> gcmdKeywords;

    @JsonProperty(required = true)
    @JsonPropertyDescription("A list of URI of Theia categories to be associated")
    private List<URI> theiaCategories;

    @JsonPropertyDescription("Keywords that the scientist wants to associate to the variable. All the association made by the Theia/OZCAR team will be associated later")
    private List<Keyword> keywords;


    public String getName() {
        return name;
    }

    public String getUnit() {
        return unit;
    }

    public String getDescription() {
        return description;
    }

    public List<GcmdKeyword> getGcmdKeywords() {
        return gcmdKeywords;
    }

    public List<URI> getTheiaCategories() {
        return theiaCategories;
    }

    public List<Keyword> getKeywords() {
        return keywords;
    }
}
