/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.observation;

import java.util.Objects;

/**
 * Class representing the hierchical concepts of the GCMD keywords that can be
 * associated to a variable
 *
 * @author coussotc
 */
public class GcmdKeyword {

    private String category;
    private String topic;
    private String term;
    private String variableLevel1;
    private String variableLevel2;
    private String variableLevel3;
    private String uuid;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getVariableLevel1() {
        return variableLevel1;
    }

    public void setVariableLevel1(String variableLevel1) {
        this.variableLevel1 = variableLevel1;
    }

    public String getVariableLevel2() {
        return variableLevel2;
    }

    public void setVariableLevel2(String variableLevel2) {
        this.variableLevel2 = variableLevel2;
    }

    public String getVariableLevel3() {
        return variableLevel3;
    }

    public void setVariableLevel3(String variableLevel3) {
        this.variableLevel3 = variableLevel3;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }


    // For equality, the only relevant field is UUID
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GcmdKeyword)) return false;
        GcmdKeyword that = (GcmdKeyword) o;
        return uuid.equals(that.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }
}
