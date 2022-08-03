/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.theialand.insitu.dataportal.repository.mongo.model.deprecated.geometry;


import java.util.List;

/**
 *
 * @author coussotc
 */
public class MultiPoint extends GeometryGeoJSON {
    
    private List<List<Double>> coordinates;

    public List<List<Double>> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<List<Double>> coordinates) {
        this.coordinates = coordinates;
    }

    public void setType() {
        this.type = "MultiPoint";
    }
}
