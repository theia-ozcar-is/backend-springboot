package fr.theialand.insitu.dataportal.etl.transformer;

import org.apache.sis.metadata.iso.extent.DefaultGeographicBoundingBox;
import org.apache.sis.referencing.CommonCRS;
import org.geotoolkit.data.geojson.binding.GeoJSONObject;
import org.geotoolkit.data.geojson.utils.GeoJSONParser;
import org.geotoolkit.geometry.jts.JTSEnvelope2D;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.opengis.referencing.operation.TransformException;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

class GeotoolkitBugsTest {

    /**
     * Dont use WGS84.geographic() !
     * use "normalizedgeographic()" !
     */
    @Test
    void inversionOfCoordinatesTrapTest() throws TransformException {

        JTSEnvelope2D envJTS = new JTSEnvelope2D(
                5, 6,
                45, 46, CommonCRS.WGS84.normalizedGeographic());

        DefaultGeographicBoundingBox bbox = new DefaultGeographicBoundingBox();

        bbox.setBounds(envJTS);

        assertThat(bbox.getEastBoundLongitude()).isEqualTo(6);
        assertThat(bbox.getWestBoundLongitude()).isEqualTo(5);
        assertThat(bbox.getNorthBoundLatitude()).isEqualTo(46);
        assertThat(bbox.getSouthBoundLatitude()).isEqualTo(45);
    }

    @Test
    @Disabled("'type' should come before 'coordinates', or else the parser fails. issue posted to geotoolkit folks. Hopefully, we managed to get type first.")
    void parsingOrderBugTest() throws IOException {
        String geoJsonPolygonString = "" +
                "         {\n" +
                "           \"coordinates\": [\n" +
                "             [ [100.0, 0.0], [101.0, 0.0], [101.0, 1.0],\n" +
                "               [100.0, 1.0], [100.0, 0.0] ]\n" +
                "             ]\n" +
                "           \"type\": \"Polygon\"\n" +
                "         }" ;
        GeoJSONObject obj = GeoJSONParser.parse(new ByteArrayInputStream( geoJsonPolygonString.getBytes()));
        assertThat(obj).isNotNull();
    }
}
