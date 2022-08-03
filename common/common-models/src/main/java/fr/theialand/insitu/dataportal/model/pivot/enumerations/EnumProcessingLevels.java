/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.theialand.insitu.dataportal.model.pivot.enumerations;

import com.fasterxml.jackson.annotation.JsonClassDescription;

/**
 * The processing status characterizes	the general processing steps that the data have been subjected to. Depending on
 * the parameters being controlled, several categories of data are defined:
 *
 * @author coussotc
 */
@JsonClassDescription("The processing status characterizes the general processing steps that the data have been subjected to. Depending on the parameters being controlled, several categories of data are defined")
public enum EnumProcessingLevels {
    RAW("Raw data"),
    QUALITY_CONTROLLED("Quality-controlled data"),
    DERIVED("Derived products");

    private final String level;

    /**
     * EnumProcessingLevels constructors
     * @param level String
     */
    EnumProcessingLevels(String level) {
        this.level = level;
    }

    /**
     * String returning the processing levels
     * @return String 
     */
    @Override
    public String toString() {
        return this.level;
    }

}
