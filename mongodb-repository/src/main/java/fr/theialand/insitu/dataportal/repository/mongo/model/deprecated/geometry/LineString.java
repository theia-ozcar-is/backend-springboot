/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.theialand.insitu.dataportal.repository.mongo.model.deprecated.geometry;


import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.enumerations.EnumGeoJSONTypes;

import java.util.List;

/**
 * Class representing the geometrical object LineString according to GeoJSON
 * specification
 *
 * @author coussotc
 */
public class LineString extends GeometryGeoJSON {
    /**
     * GeoJSON mandatory fields
     *
     * @see EnumGeoJSONTypes
     */
    //private String type = "LineString";

    /**
     * Ordered list of coordinates, the list must at least contain 2 double[2]
     * or 2 double[3] objects after being instanciated.
     * double[2] or double[3] are similar to Point coordinates and must follow the same specification
     * @see Point
     */
    private List<List<Double>> coordinates;

//    public LineString(List<List<Double>> coordinates) {
//        this.coordinates = coordinates;
//        //this.type = type;
//    }
    
//    public LineString(List<List<Double>> coordinates) {
//        ListIterator<List<Double>> listIterator = coordinates.listIterator();
//        while (listIterator.hasNext()) {
//            ListIterator<Double> listIterator1 = listIterator.next().listIterator();
//            while (listIterator1.hasNext()) {
//                Double d = listIterator1.next().doubleValue();
//                listIterator1.set(d);
//            }
//        }
//        this.coordinates = coordinates;
//    }

    public List<List<Double>> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<List<Double>> coordinates) {
        this.coordinates = coordinates;
    }

    public void setType() {
        this.type = "LineString";
    }
}
