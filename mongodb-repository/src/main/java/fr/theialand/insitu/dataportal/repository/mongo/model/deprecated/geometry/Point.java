
package fr.theialand.insitu.dataportal.repository.mongo.model.deprecated.geometry;

import java.util.List;


/**
 *
 * @author coussotc
 */
public class Point extends GeometryGeoJSON {


    private List<Double> coordinates;

    public List<Double> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<Double> coordinates) {
        this.coordinates = coordinates;
    }

    public void setType() {
        this.type = "Point";
    }
}
