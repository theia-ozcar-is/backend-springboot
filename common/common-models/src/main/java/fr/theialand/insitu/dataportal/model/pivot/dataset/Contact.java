/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.theialand.insitu.dataportal.model.pivot.dataset;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Abstract class Contact
 * @author coussotc
 */
@JsonSubTypes({
        @JsonSubTypes.Type(Person.class),
        @JsonSubTypes.Type(Organisation.class)
})
@JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION)  // Jackson > 2.12 auto deserialize to the correct class ! no need for JsonDeserialiser
public abstract class Contact {

}