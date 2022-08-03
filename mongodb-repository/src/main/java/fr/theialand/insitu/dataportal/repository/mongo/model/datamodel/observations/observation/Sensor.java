/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.observation;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.dataset.TemporalExtent;

import java.util.List;

/**
 *
 * @author coussotc
 */
@JsonSubTypes({
        @JsonSubTypes.Type(VirtualSensor.class),
        @JsonSubTypes.Type(PhysicalSensor.class)
})
@JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION)
public abstract class Sensor {

    private List<TemporalExtent> activityPeriods;

    public List<TemporalExtent> getActivityPeriods() {
        return activityPeriods;
    }

    public void setActivityPeriods(List<TemporalExtent> activityPeriods) {
        this.activityPeriods = activityPeriods;
    }
    
}
