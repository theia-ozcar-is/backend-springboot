/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.theialand.insitu.dataportal.model.pivot.dataset;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

import java.net.URI;

/**
 *  Class representing the data constraints for a given dataset
 * @author coussotc
 */
public class DataConstraint {

    // todo: it should be an enum according to the producer doc. but definition is not clear (other languages ?).
    @JsonProperty(required = true, defaultValue = "no conditions to access and use" )
    @JsonPropertyDescription("String mentionning the use constraint of a given dataset. either free text in the file language describing the conditions, or some predefined values")
    private String accessUseConstraint;

    @JsonPropertyDescription("data policy of a given dataset")
    private URI urlDataPolicy;

    @JsonPropertyDescription("Licence of a given dataset")
    private Licence licence;

    @JsonPropertyDescription("embargo on the data of a given dataset")
    private Embargo embargo;

    public String getAccessUseConstraint() {
        return accessUseConstraint;
    }

    public URI getUrlDataPolicy() {
        return urlDataPolicy;
    }

    public Licence getLicence() {
        return licence;
    }

    public Embargo getEmbargo() {
        return embargo;
    }
}
