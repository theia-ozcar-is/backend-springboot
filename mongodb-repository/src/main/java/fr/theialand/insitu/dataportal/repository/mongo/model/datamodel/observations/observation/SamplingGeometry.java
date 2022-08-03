/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.observation;

import com.mongodb.client.model.geojson.Geometry;

/**
 *
 * @author coussotc
 */
public class SamplingGeometry extends SamplingFeature{
    
    private Geometry geometry;

    public Geometry getGeometry() {
        return geometry;
    }
    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }
}
