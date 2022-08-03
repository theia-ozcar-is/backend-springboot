/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.observation;

import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.entity.AdministrativeFeature;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.geojsonproperties.AdministrativeFeatureProperties;

import java.util.List;

/**
 * The feature of interest is an abstraction of a real-world object. It's the spatial object
 * from which one characteristic is sampled by the observation
 * @author coussotc
 */
public class FeatureOfInterest {
    /**
     * Spatial representation of the feature of interest
     */
    private SamplingFeature samplingFeature;
    private SampledFeatures sampledFeatures;
    private String climateFeature;
    private String geologyFeature;
    private List<AdministrativeFeatureProperties> administrativeFeatures;

    public SamplingFeature getSamplingFeature() {
        return samplingFeature;
    }

    public void setSamplingFeature(SamplingFeature samplingFeature) {
        this.samplingFeature = samplingFeature;
    }

    public SampledFeatures getSampledFeatures() {
        return sampledFeatures;
    }

    public void setSampledFeatures(SampledFeatures sampledFeatures) {
        this.sampledFeatures = sampledFeatures;
    }

    public String getClimateFeature() {
        return climateFeature;
    }

    public void setClimateFeature(String climateFeature) {
        this.climateFeature = climateFeature;
    }

    public String getGeologyFeature() {
        return geologyFeature;
    }

    public void setGeologyFeature(String geologyFeature) {
        this.geologyFeature = geologyFeature;
    }

    public List<AdministrativeFeatureProperties> getAdministrativeFeatures() {
        return administrativeFeatures;
    }

    public void setAdministrativeFeatures(List<AdministrativeFeatureProperties> administrativeFeatures) {
        this.administrativeFeatures = administrativeFeatures;
    }
}
