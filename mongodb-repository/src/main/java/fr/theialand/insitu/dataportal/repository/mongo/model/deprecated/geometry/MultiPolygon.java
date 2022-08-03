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
public class MultiPolygon extends GeometryGeoJSON {

    //private String type = "MultiPolygon";

    private List<List<List<List<Double>>>> coordinates;

//    public MultiPolygon(List<List<List<List<Double>>>> coordinates) {
//        ListIterator<List<List<List<Double>>>> listIterator = coordinates.listIterator();
//        while (listIterator.hasNext()) {
//            ListIterator<List<List<Double>>> listIterator1 = listIterator.next().listIterator();
//            while (listIterator1.hasNext()) {
//                ListIterator<List<Double>> listIterator2 = listIterator1.next().listIterator();
//                while (listIterator2.hasNext()) {
//                    ListIterator<Double> listIterator3 = listIterator2.next().listIterator();
//                    while (listIterator3.hasNext()) {
//                        Double d = listIterator3.next().doubleValue();
//                        listIterator3.set(d);
//                    }
//                }
//            }
//        }
//        this.coordinates = coordinates;
//
//    }

    public List<List<List<List<Double>>>> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<List<List<List<Double>>>> coordinates) {
        this.coordinates = coordinates;
    }

    public void setType() {
        this.type = "MultiPolygon";
    }
}
