/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.theialand.insitu.dataportal.model.pivot.producer;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import fr.theialand.insitu.dataportal.model.pivot.enumerations.EnumFundingTypes;

import javax.validation.constraints.Pattern;

public class Funding {

    @JsonProperty(required = true)
    @JsonPropertyDescription("The organism type according to EnumFundingTypes enumeration")
    private EnumFundingTypes type;

    @JsonProperty(required = true)
    @Pattern(regexp = "[a-zA-Z]{2}")
    @JsonPropertyDescription("2-letter code of this organisation's country, according to iso3166")
    private String iso3166;

    @JsonPropertyDescription("Complete name of the affiliation")
    private String name;

    @JsonPropertyDescription("Acronym of the organism affiliated")
    private String acronym;

    @Pattern(regexp = "[0-9]+[A-Z]?|^$") // todo : we permit empty values here, as some producers CATC gives empy string. is it ok ?
    private String idScanR;

    public EnumFundingTypes getType() {
        return type;
    }

    public String getIso3166() {
        return iso3166;
    }

    public String getName() {
        return name;
    }

    public String getAcronym() {
        return acronym;
    }

    public String getIdScanR() {
        return idScanR;
    }


    //  according to the contract PDF, a Funding must be provided with either a name OR a scanrID OR an acronym
    // see @fr.theialand.insitu.dataportal.model.pivot.dataset.Organisation

    public static class FundingWithName extends Funding {
        @JsonProperty(required = true)
        @JsonPropertyDescription("Complete name of the affiliation")
        private String name;
    }

    public static class FundingWithAcronym extends Funding {
        @JsonProperty(required = true)
        @JsonPropertyDescription("Acronym of the organism affiliated")
        private String acronym;
    }

    public static class FundingWithScanRiD extends Funding {
        @JsonProperty(required = true)
        @Pattern(regexp = "[0-9]+[A-Z]?|^$") // todo : we permit empty values here, as some producers CATC gives empy string. is it ok ?
        private String idScanR;
    }

}
