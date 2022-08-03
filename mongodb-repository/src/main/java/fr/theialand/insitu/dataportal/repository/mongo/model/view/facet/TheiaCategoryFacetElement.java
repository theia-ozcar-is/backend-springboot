/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.theialand.insitu.dataportal.repository.mongo.model.view.facet;

import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.entity.TheiaCategory;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.observation.TheiaVariable;

import java.util.List;

/**
 *
 * @author coussotc
 */
public class TheiaCategoryFacetElement extends TheiaCategory {
    //private String uri;
    //private List<String> broaders;
    //private List<String> narrowers;
    private int count;
    //private List<I18n> prefLabel;
    private List<TheiaVariable> theiaVariables;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<TheiaVariable> getTheiaVariables() {
        return theiaVariables;
    }

    public void setTheiaVariables(List<TheiaVariable> theiaVariables) {
        this.theiaVariables = theiaVariables;
    }
}
