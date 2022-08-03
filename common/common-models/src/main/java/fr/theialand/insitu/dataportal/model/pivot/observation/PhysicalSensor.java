/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.theialand.insitu.dataportal.model.pivot.observation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import fr.theialand.insitu.dataportal.model.pivot.dataset.TemporalExtent;

import java.util.List;

/**
 *
 * @author coussotc
 */
public class PhysicalSensor extends Sensor {

    @JsonPropertyDescription("model of the sensor")
    private String model;

    @JsonPropertyDescription("manufacturer of the sensor")
    private String manufacturer;

    @JsonPropertyDescription("serial number of the sensor")
    private String serialNumber;

    @JsonProperty(required = true)
    @JsonPropertyDescription("type of the sensor")
    private String sensorType;

    private String operationalMode;

    @JsonPropertyDescription("calibration description of the sensor")
    private String calibration;

    private List<TemporalExtent> activityPeriods;


    public String getModel() {
        return model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public String getSensorType() {
        return sensorType;
    }

    public String getOperationalMode() {
        return operationalMode;
    }

    public String getCalibration() {
        return calibration;
    }

    public List<TemporalExtent> getActivityPeriods() {
        return activityPeriods;
    }
}
