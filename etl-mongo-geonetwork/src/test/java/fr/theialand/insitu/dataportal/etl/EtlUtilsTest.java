package fr.theialand.insitu.dataportal.etl;

import org.junit.jupiter.api.Test;
import org.opengis.geometry.Envelope;
import org.springframework.data.geo.Box;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.geo.GeoJsonMultiPolygon;
import org.springframework.data.mongodb.core.geo.GeoJsonPolygon;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class EtlUtilsTest {
/*
    @Test
    void bboxCreationShouldCorrectlyParsePolygonTest(){
        GeoJsonPolygon testPolygon = new GeoJsonPolygon(
                new Point(-11, 1),
                new Point(-11, 10),
                new Point(10, 10),
                new Point(10,1),
                new Point(1,1));

        Box bbox = TheiaOzcarUtils.getBBoxFromGeoJson( testPolygon ) ;

        assertThat( bbox ).isNotNull();
        assertThat( bbox.getFirst() ).isNotNull();
        assertThat( bbox.getSecond() ).isNotNull();
        assertThat( bbox.getFirst().getX() ).isEqualTo(-11);
        assertThat( bbox.getFirst().getY() ).isEqualTo(1);
        assertThat( bbox.getSecond().getX() ).isEqualTo(10);
        assertThat( bbox.getSecond().getY() ).isEqualTo(10);


    }

    @Test
    void bboxCreationShouldCorrectlyParseMultiPolygonTest(){
        GeoJsonPolygon testPolygon1 = new GeoJsonPolygon(
                new Point(-11, 11),
                new Point(-31, 1),
                new Point(10, 10),
                new Point(10,1),
                new Point(1,1));
        GeoJsonPolygon testPolygon2 = new GeoJsonPolygon(
                new Point(41, 1),
                new Point(51, 10),
                new Point(10, 10),
                new Point(10,61),
                new Point(1,33));
        GeoJsonMultiPolygon multiPolygon = new GeoJsonMultiPolygon(List.of(testPolygon1,testPolygon2)) ;

        Box bbox = TheiaOzcarUtils.getBBoxFromGeoJson( multiPolygon ) ;

        assertThat( bbox ).isNotNull();
        assertThat( bbox.getFirst() ).isNotNull();
        assertThat( bbox.getSecond() ).isNotNull();
        assertThat( bbox.getFirst().getX() ).isEqualTo(-31);
        assertThat( bbox.getFirst().getY() ).isEqualTo(1);
        assertThat( bbox.getSecond().getX() ).isEqualTo(51);
        assertThat( bbox.getSecond().getY() ).isEqualTo(61);

    }
*/
    @Test
    void bboxCreationShouldCorrectlyParsePolygonTest(){
        GeoJsonPolygon testPolygon = new GeoJsonPolygon(
                new Point(-11, 1),
                new Point(-11, 10),
                new Point(10, 10),
                new Point(10,1),
                new Point(1,1));
/*
        Box bbox = TheiaOzcarUtils.getBBoxFromGeoJson( testPolygon ) ;

        assertThat( bbox ).isNotNull();
        assertThat( bbox.getFirst() ).isNotNull();
        assertThat( bbox.getSecond() ).isNotNull();
        assertThat( bbox.getFirst().getX() ).isEqualTo(-11);
        assertThat( bbox.getFirst().getY() ).isEqualTo(1);
        assertThat( bbox.getSecond().getX() ).isEqualTo(10);
        assertThat( bbox.getSecond().getY() ).isEqualTo(10);
*/

    }

    @Test
    void bboxCreationShouldCorrectlyParseMultiPolygonTest(){

        GeoJsonPolygon testPolygon1 = new GeoJsonPolygon(
                new Point(-11, 11),
                new Point(-31, 1),
                new Point(10, 10),
                new Point(10,1),
                new Point(1,1));
        GeoJsonPolygon testPolygon2 = new GeoJsonPolygon(
                new Point(41, 1),
                new Point(51, 10),
                new Point(10, 10),
                new Point(10,61),
                new Point(1,33));
        GeoJsonMultiPolygon multiPolygon = new GeoJsonMultiPolygon(List.of(testPolygon1,testPolygon2)) ;
/*
        Box bbox = TheiaOzcarUtils.getBBoxFromGeoJson( multiPolygon ) ;

        assertThat( bbox ).isNotNull();
        assertThat( bbox.getFirst() ).isNotNull();
        assertThat( bbox.getSecond() ).isNotNull();
        assertThat( bbox.getFirst().getX() ).isEqualTo(-31);
        assertThat( bbox.getFirst().getY() ).isEqualTo(1);
        assertThat( bbox.getSecond().getX() ).isEqualTo(51);
        assertThat( bbox.getSecond().getY() ).isEqualTo(61);
*/
    }

}
