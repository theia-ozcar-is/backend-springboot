/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.dataset;

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
@JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION)
public abstract class Contact {

}