/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.observation;

import java.util.List;

/**
 *
 * @author coussotc
 */
public class QualityFlag {
    
    private List<I18n> code, description;

    public List<I18n> getCode() {
        return code;
    }

    public void setCode(List<I18n> code) {
        this.code = code;
    }

    public List<I18n> getDescription() {
        return description;
    }

    public void setDescription(List<I18n> description) {
        this.description = description;
    }

    
}
