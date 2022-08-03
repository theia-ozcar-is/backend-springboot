/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.theialand.insitu.dataportal.model.pivot.dataset;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.mongodb.client.model.geojson.Geometry;
import fr.theialand.insitu.dataportal.model.pivot.enumerations.EnumGeoJSONTypes;
import fr.theialand.insitu.dataportal.model.pivot.geojsonproperties.Properties;


/**
 * Class representing the spatial extent of a dataset. This spatial extent is either a 
 * bounding box or several bounding boxes
 * @author coussotc
 */
@JsonClassDescription("This spatial extent is either a bounding box or several bounding boxes")
public class SpatialExtent {

    @JsonProperty(required = true)
    @JsonPropertyDescription("a GeoJson mandatory field. only Feature is currently supported")
    private EnumGeoJSONTypes type;

    @JsonProperty(required = true)
    @JsonPropertyDescription("a GeoJson mandatory field. can(must?) be empty")
    private Properties properties;

    @JsonProperty(required = true)
    @JsonPropertyDescription("a GeoJson mandatory field")
    private Geometry geometry;




    public EnumGeoJSONTypes getType() {
        return type;
    }

    public Properties getProperties() {
        return properties;
    }

    public Geometry getGeometry() {
        return geometry;
    }
}
