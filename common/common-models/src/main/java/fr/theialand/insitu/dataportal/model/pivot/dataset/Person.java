/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.theialand.insitu.dataportal.model.pivot.dataset;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import fr.theialand.insitu.dataportal.model.pivot.enumerations.EnumContactPersonRoles;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

/**
 * Class representing a COntact of a dataset
 * @author coussotc
 */
public class Person extends Contact {

    @JsonProperty(required = true)
    private String firstName;

    @JsonProperty(required = true)
    private String lastName;

    @Email
    @JsonProperty(required = true)
    private String email;

    @JsonProperty(required = true)
    @JsonPropertyDescription("The role of this person, such as Project Leadr or Data Managr")
    private EnumContactPersonRoles role;

    @Pattern(regexp = "\\w{4}-\\w{4}-\\w{4}-\\w{4}")
    @JsonPropertyDescription("The ORC ID (Open Researcher and Contributor ID) of the Person if it has one")
    private String orcId;

    @JsonPropertyDescription("the organisation where the Person belongs if it is relevant. If so, one of name, idScanR, or acronym must be populated")
    private Organisation organisation;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public EnumContactPersonRoles getRole() {
        return role;
    }

    public String getOrcId() {
        return orcId;
    }

    public Organisation getOrganisation() {
        return organisation;
    }

    @Override
    public String toString() {
        return this.firstName + this.lastName + this.email ;
    }
}
