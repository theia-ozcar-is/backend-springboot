/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.theialand.insitu.dataportal.model.pivot.observation;


import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import fr.theialand.insitu.dataportal.model.pivot.enumerations.EnumDocumentTypes;

import java.net.URI;

/**
 * Class representing the documents associated to the dataset
 * @author coussotc
 */
@JsonClassDescription("one of the documents associated to the dataset")
public class Document {

    @JsonProperty(required = true)
    @JsonPropertyDescription("The type of the document")
    public EnumDocumentTypes type;

    @JsonProperty(required = true)
    @JsonPropertyDescription("Where the document can be found")
    public URI url;

    public EnumDocumentTypes getType() {
        return type;
    }

    public URI getUrl() {
        return url;
    }
}
