package fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.entity;

import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.observation.TheiaVariable;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "variableAssociations")
public class VariableAssociation {
    private ObjectId _id;
    TheiaVariable theiaVariable;
    String producerId;
    List<String> producerTheiaCategories;
    String producerVariableNameEn;
    String unitNameEn;
    boolean isActive;

    public ObjectId get_id() {
        return _id;
    }

    public TheiaVariable getTheiaVariable() {
        return theiaVariable;
    }

    public void setTheiaVariable(TheiaVariable theiaVariable) {
        this.theiaVariable = theiaVariable;
    }

    public String getProducerId() {
        return producerId;
    }

    public void setProducerId(String producerId) {
        this.producerId = producerId;
    }

    public List<String> getProducerTheiaCategories() {
        return producerTheiaCategories;
    }

    public void setProducerTheiaCategories(List<String> producerTheiaCategories) {
        this.producerTheiaCategories = producerTheiaCategories;
    }

    public String getProducerVariableNameEn() {
        return producerVariableNameEn;
    }

    public void setProducerVariableNameEn(String producerVariableNameEn) {
        this.producerVariableNameEn = producerVariableNameEn;
    }

    public String getUnitNameEn() {
        return unitNameEn;
    }

    public void setUnitNameEn(String unitNameEn) {
        this.unitNameEn = unitNameEn;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
