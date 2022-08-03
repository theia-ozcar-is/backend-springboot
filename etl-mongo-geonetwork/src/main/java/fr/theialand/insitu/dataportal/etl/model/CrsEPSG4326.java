package fr.theialand.insitu.dataportal.etl.model;

import org.apache.sis.metadata.iso.DefaultIdentifier;
import org.opengis.metadata.Identifier;
import org.opengis.referencing.crs.GeographicCRS;
import org.opengis.referencing.cs.EllipsoidalCS;
import org.opengis.referencing.datum.GeodeticDatum;

/**
 * Attempt to get rid of the SIS referencing dependencies,
 * At least we don't need this *huge* lib for simple EPSG4326 handling...
 */
public class CrsEPSG4326 implements GeographicCRS {

    /**
     * need to be rather complete, it is used by GeoToolkit / JTS for geojson conversion
     * SIS itself dont need this
     */
    @Override
    public EllipsoidalCS getCoordinateSystem() {
        return null; // not used for XML generation,
    }

    @Override
    public GeodeticDatum getDatum() {
        return null;  // not used for XML generation,
    }

    @Override
    public Identifier getName() {
        return new DefaultIdentifier("EPSG", "4326",null);
    }
}
