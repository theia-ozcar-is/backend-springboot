/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.theialand.insitu.dataportal.repository.mongo.model.deprecated.geometry;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing Polygon geometry according to GeoJSON Specification
 *
 * @author coussotc
 */
public class Polygon extends GeometryGeoJSON {

    /**
     * GeoJSON mandatory field
     */
   // private String type = "Polygon";

    /**
     * Ordered list of ordered list of double[2] or double[3] that represent coordinates of Polygon according to GeoJSON
     * specification The ordered list must contain only 1 ArrayList<double[]> object. The ArrayList<double[]> object
     * must contain at least 4 double[]. The first and the last index of the ArrayList<double[]> must be the same object
     */
    private List<List<List<Double>>> coordinates = new ArrayList<>();

//    public Polygon(List<List<List<Double>>> coordinates) {
//        ListIterator<List<List<Double>>> listIterator = coordinates.listIterator();
//        while (listIterator.hasNext()) {
//            ListIterator<List<Double>> listIterator1 = listIterator.next().listIterator();
//            while (listIterator1.hasNext()) {
//                ListIterator<Double> listIterator2 = listIterator1.next().listIterator();
//                while (listIterator2.hasNext()) {
//                    Double d = listIterator2.next().doubleValue();
//                    listIterator2.set(d);
//                }
//            }
//        }
//        this.coordinates = coordinates;
//    }

    public List<List<List<Double>>> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<List<List<Double>>> coordinates) {
        this.coordinates = coordinates;
    }

    public void setType() {
        this.type = "Polygon";
    }
}
