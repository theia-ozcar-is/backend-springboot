/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.enumerations;

/**
 *
 * @author coussotc
 */
// Use com.mongodb.client.model.geojson.GeoJsonObjectType.class
public enum EnumGeoJSONTypes {
    FEATURECOLLECTION("FeatureCollection"),
    FEATURE("Feature"),
    POINT("Point"),
    MULTIPOINT("MultiPoint"),
    LINESTRING("LineString"),
    MULTILINESTRING("MultiLineString"),
    POLYGON("Polygon"),
    MULTIPOLYGON("MultiPolygon");
    
    private String enumGeoJSONType;

    private EnumGeoJSONTypes(String enumGeoJSONType) {
        this.enumGeoJSONType = enumGeoJSONType;
    }
    
    @Override
    public String toString() {
        return this.enumGeoJSONType;
    }
    
}
