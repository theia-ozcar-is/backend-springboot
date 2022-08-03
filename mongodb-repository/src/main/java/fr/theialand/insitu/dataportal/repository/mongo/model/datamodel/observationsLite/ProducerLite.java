/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observationsLite;

import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.observation.I18n;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.producer.Funding;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author coussotc
 */
public class ProducerLite {
    /**
     * ID of the Producer as defined by Theia/OZCAR comity
     */
    private String producerId;
    /**
     * Acronym of the Producer. ex AMMA-CATCH
     */
    private List<I18n> name;
    
    private List<Funding> fundings = new ArrayList<>();


    public String getProducerId() {
        return producerId;
    }

    public List<I18n> getName() {
        return name;
    } 

    public List<Funding> getFundings() {
        return fundings;
    }

    public void setProducerId(String producerId) {
        this.producerId = producerId;
    }

    public void setName(List<I18n> name) {
        this.name = name;
    }

    public void setFundings(List<Funding> fundings) {
        this.fundings = fundings;
    }
    
    
    
}
