/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.theialand.insitu.dataportal.repository.mongo.geojson;

import com.mongodb.client.model.geojson.Geometry;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.entity.ObservationDocument;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 *
 * @author coussotc
 */
@Document(collection = "observations")
public class TestObsDoc extends ObservationDocument implements Serializable {


    /**
     * Equivalent to running creatIndex(2dsphere) on the db ?
     */
    @GeoSpatialIndexed
    private Geometry geom;

    public Geometry getGeom() {
        return geom;
    }

    public void setGeom(Geometry geom) {
        this.geom = geom;
    }



}
