/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.theialand.insitu.dataportal.model.pivot.observation;

import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.List;

@JsonSubTypes({
        @JsonSubTypes.Type(PhysicalSensor.class),
        @JsonSubTypes.Type(VirtualSensor.class)
})
@JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION)  // Jackson > 2.12 auto deserialize to the correct class ! no need for JsonDeserialiser
public abstract class Sensor {

    // fixme : was a single document in code and a list in docs !
    @JsonPropertyDescription("related publications and notices")
    private List<Document> documents;

    public List<Document> getDocuments() {
        return documents;
    }
}
