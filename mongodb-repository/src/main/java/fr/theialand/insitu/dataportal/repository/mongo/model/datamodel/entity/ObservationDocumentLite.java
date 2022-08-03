/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.entity;

import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.observation.FeatureOfInterest;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.observation.TheiaVariable;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observationsLite.DatasetLite;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observationsLite.ObservationLite;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observationsLite.ProducerLite;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Entity class to be mapped with MongoDB DOcument object
 * @author coussotc
 */
@Document(collection = "observationsLite")
public class ObservationDocumentLite {

    private ProducerLite producer;
    private DatasetLite dataset;
    private List<ObservationLite> observations;
    private String theiaVariableNameEn;
    private String producerVariableNameEn;
    private TheiaVariable theiaVariable;
    private List<TheiaCategory> theiaCategories;
    private FeatureOfInterest featureOfInterest;

    public TheiaVariable getTheiaVariable() {
        return theiaVariable;
    }

    public void setTheiaVariable(TheiaVariable theiaVariable) {
        this.theiaVariable = theiaVariable;
    }

    public String getTheiaVariableNameEn() {
        return theiaVariableNameEn;
    }

    public void setTheiaVariableNameEn(String theiaVariableNameEn) {
        this.theiaVariableNameEn = theiaVariableNameEn;
    }

    public String getProducerVariableNameEn() {
        return producerVariableNameEn;
    }

    public void setProducerVariableNameEn(String producerVariableNameEn) {
        this.producerVariableNameEn = producerVariableNameEn;
    }

    public List<TheiaCategory> getTheiaCategories() {
        return theiaCategories;
    }

    public void setTheiaCategories(List<TheiaCategory> theiaCategories) {
        this.theiaCategories = theiaCategories;
    }

    public FeatureOfInterest getFeatureOfInterest() {
        return featureOfInterest;
    }

    public void setFeatureOfInterest(FeatureOfInterest featureOfInterest) {
        this.featureOfInterest = featureOfInterest;
    }

    public ProducerLite getProducer() {
        return producer;
    }

    public DatasetLite getDataset() {
        return dataset;
    }

    public List<ObservationLite> getObservations() {
        return observations;
    }

    public void setObservations(List<ObservationLite> observations) {
        this.observations = observations;
    }


    public void setDataset(DatasetLite dataset) {
        this.dataset = dataset;
    }

    public void setProducer(ProducerLite producer) {
        this.producer = producer;
    }
}
