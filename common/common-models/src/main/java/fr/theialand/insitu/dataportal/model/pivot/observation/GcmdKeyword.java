/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.theialand.insitu.dataportal.model.pivot.observation;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class representing the hierchical concepts of the GCMD keywords that can be
 * associated to a variable
 *
 * @author coussotc
 */
@JsonClassDescription("the hierchical concepts of the GCMD keywords that can be associated to a variable")
public class GcmdKeyword {

    @JsonProperty(required = true)
    private String category;

    @JsonProperty(required = true)
    private String topic;

    private String term;
    private String variableLevel1;
    private String variableLevel2;
    private String variableLevel3;
    private String uuid;

    public String getCategory() {
        return category;
    }

    public String getTopic() {
        return topic;
    }

    public String getTerm() {
        return term;
    }

    public String getVariableLevel1() {
        return variableLevel1;
    }

    public String getVariableLevel2() {
        return variableLevel2;
    }

    public String getVariableLevel3() {
        return variableLevel3;
    }

    public String getUuid() {
        return uuid;
    }
}
