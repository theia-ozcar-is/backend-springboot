/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.dataset;

import com.mongodb.client.model.geojson.Geometry;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.geojsonproperties.Properties;


/**
 * Class representing the spatial extent of a dataset. This spatial extent is either a 
 * bounding box or several bounding boxes
 * @author coussotc
 */
public class SpatialExtent {

    private Geometry geometry;
    /**
     * a GeoJSON mandatoruy field
     */
    private Properties properties;
    
    /**
     * a GeoJSON mandatoruy field
     */
    private String type;

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
