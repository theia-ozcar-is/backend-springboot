/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.theialand.insitu.dataportal.model.pivot.dataset;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

import java.net.URI;

/**
 * Class representig a webservice for a dataset
 * @author coussotc
 */
@JsonClassDescription("Class representing a webservice for a dataset")
public class Webservice {

    @JsonProperty(required = true)
    @JsonPropertyDescription("Description of the service name")
    private String description;

    @JsonProperty(required = true)
    @JsonPropertyDescription("url of the service")
    private URI url;

    public String getDescription() {
        return description;
    }

    public URI getUrl() {
        return url;
    }
}
