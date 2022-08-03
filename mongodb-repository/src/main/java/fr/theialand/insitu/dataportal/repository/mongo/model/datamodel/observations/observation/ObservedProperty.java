/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.observation;

import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.dataset.Keyword;

import java.util.List;
import java.util.Objects;

/**
 * The observed property is a characteristic of the feature of interest. It identifies or describes the phenomenon for
 * which the observation is made.
 *
 * @author coussotc
 */
public class ObservedProperty {

    /**
     * Name of the variable
     */
    private List<I18n> name; // todo: it is in fact a Set<langEnum, string>
    /**
     * Unit of the variable
     */
    private List<I18n> unit;
    /**
     * Short description of the variable
     */
    private List<I18n> description;
    /**
     * Gcmd Keywords that the scientist wants to associate to the variable. All the association made by the Theia/OZCAR
     * team will be associated later.
     */
    private List<Keyword> keywords;

    /**
     * A list of URI of Theia categories to be associated
     */
    private List<String> theiaCategories;
    
    /**
     * Theia Variable 
     */
    private TheiaVariable theiaVariable;

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

    public List<I18n> getUnit() {
        return unit;
    }

    public void setUnit(List<I18n> unit) {
        this.unit = unit;
    }

    public List<I18n> getDescription() {
        return description;
    }

    public void setDescription(List<I18n> description) {
        this.description = description;
    }

    public List<Keyword> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<Keyword> keywords) {
        this.keywords = keywords;
    }

    public List<String> getTheiaCategories() {
        return theiaCategories;
    }

    public void setTheiaCategories(List<String> theiaCategories) {
        this.theiaCategories = theiaCategories;
    }

    // see what makes 2 ObsProperties equals, not the description, and not the unit
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ObservedProperty)) return false;
        ObservedProperty that = (ObservedProperty) o;
        return          name.equals(that.name)
                        && Objects.equals(theiaCategories, that.theiaCategories)
                        && Objects.equals(theiaVariable, that.theiaVariable);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, unit, keywords, theiaCategories, theiaVariable);
    }
}
