/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.theialand.insitu.dataportal.repository.mongo.model.view.popup;

import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.observation.I18n;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.observation.TheiaVariable;

import java.util.List;

/**
 *
 * @author coussotc
 */
public class ObservedPropertyLitePopup {

    /**
     * Name of the variable
     */
    private List<I18n> name;
    private TheiaVariable theiaVariable ;

    public TheiaVariable getTheiaVariable() {
        return theiaVariable;
    }

    public void setTheiaVariable(TheiaVariable theiaVariable) {
        this.theiaVariable = theiaVariable;
    }


    public List<I18n> getName() {
        return name;
    }

    public void setName(List<I18n> name) {
        this.name = name;
    }
    
    
    
}
