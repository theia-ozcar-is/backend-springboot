/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.theialand.insitu.dataportal.repository.mongo.model.view.popup;

import java.util.List;

/**
 *
 * @author coussotc
 */
public class PopupDocument {

    private ProducerLitePopup producer;
    private List<ObservationLitePopup> observations;

    public ProducerLitePopup getProducer() {
        return producer;
    }

    public void setProducer(ProducerLitePopup producer) {
        this.producer = producer;
    }

    public List<ObservationLitePopup> getObservations() {
        return observations;
    }

    public void setObservations(List<ObservationLitePopup> observations) {
        this.observations = observations;
    }
}
