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
 * Class representing keyword of the dataset
 * @author coussotc
 */
public class Keyword {

    @JsonPropertyDescription("The keyword")
    private String keyword;

    @JsonPropertyDescription("URI of the keyword in a published thesaurus")
    private URI uri;

    public String getKeyword() {
        return keyword;
    }

    public URI getUri() {
        return uri;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public void setUri(URI uri) {
        this.uri = uri;
    }
}
