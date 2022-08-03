/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.theialand.insitu.dataportal.model.pivot.dataset;


import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import fr.theialand.insitu.dataportal.model.pivot.enumerations.EnumClimates;
import fr.theialand.insitu.dataportal.model.pivot.enumerations.EnumGeologies;

import java.util.List;

/**
 *  Class representing Portal search critera parameter to be used for the portal for dataset discovery
 * @author coussotc
 */
@JsonClassDescription("representing Portal search critera parameter to be used for the portal for dataset discovery")
public class PortalSearchCriteria {

    @JsonPropertyDescription("Ordered list of climate according to EnumClimates values")
    private List<EnumClimates> climates;

    @JsonPropertyDescription("Ordered list of geologies according to EnumGeologies values")
    private List<EnumGeologies> geologies;

    public List<EnumClimates> getClimates() {
        return climates;
    }

    public List<EnumGeologies> getGeologies() {
        return geologies;
    }
}
