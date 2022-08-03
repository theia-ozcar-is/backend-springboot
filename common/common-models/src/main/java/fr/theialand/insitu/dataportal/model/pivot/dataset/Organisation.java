/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.theialand.insitu.dataportal.model.pivot.dataset;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import fr.theialand.insitu.dataportal.model.pivot.enumerations.EnumContactOrganisationRoles;

import javax.validation.constraints.Pattern;


public class Organisation extends Contact {

    @JsonProperty(required = true)
    @JsonPropertyDescription("Currently, the only available role is Research Group")
    private EnumContactOrganisationRoles role;

    // TODO below is code repeated 4 times : also in Funding and Dao Organisation and Dao Funding...

    @JsonPropertyDescription("Complete name of the organisation")
    private String name;

    @JsonPropertyDescription("Acronym of the organism")
    private String acronym;

    @Pattern(regexp = "[0-9]+[A-Z]?")
    private String idScanR;

    @JsonProperty(required = true)
    @Pattern(regexp = "[a-zA-Z]{2}")
    @JsonPropertyDescription("2-letter code of this organisation's country, according to iso3166")
    private String iso3166;


    public String getName() {
        return name;
    }

    public String getAcronym() {
        return acronym;
    }

    public String getIdScanR() {
        return idScanR;
    }

    public String getIso3166() {
        return iso3166;
    }

    public EnumContactOrganisationRoles getRole() {
        return role;
    }

    //  according to the contract PDF, a Organisation must be provided with either a name OR a scanrID OR an acronym
    // see @Funding

    public static class OrganisationWithName extends Organisation {
        @JsonProperty(required = true)
        @JsonPropertyDescription("Complete name of the organisation")
        private String name;
    }

    public static class OrganisationWithAcronym extends Organisation {
        @JsonProperty(required = true)
        @JsonPropertyDescription("Acronym of the organism")
        private String acronym;
    }

    public static class OrganisationWithScanRiD extends Organisation {
        @JsonProperty(required = true)
        @Pattern(regexp = "[0-9]+[A-Z]?")
        private String idScanR;
    }
}
