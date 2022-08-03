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
 * Class representing a licence on a dataset
 * @author coussotc
 */
public class Licence {

    @JsonProperty(required = true)
    @JsonPropertyDescription("Name of the licence")
    private String title;

    @JsonProperty(required = true)
    @JsonPropertyDescription("url of the description of the licence")
    private URI url;

    public String getTitle() {
        return title;
    }

    public URI getUrl() {
        return url;
    }
}
