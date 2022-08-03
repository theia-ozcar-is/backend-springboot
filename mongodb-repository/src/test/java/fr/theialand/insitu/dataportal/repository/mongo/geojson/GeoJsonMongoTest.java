package fr.theialand.insitu.dataportal.repository.mongo.geojson;

import com.mongodb.client.model.geojson.*;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.entity.ObservationDocument;
import fr.theialand.insitu.dataportal.repository.mongo.repository.ObservationDocumentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.geo.GeoJsonPolygon;

import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Disabled("kind of a PoC test, not really a test")
class GeoJsonMongoTest {

    @Autowired
    private ObservationDocumentRepository obsRepo;

    @Autowired
    private GeoObsDocRepository geoRepo;

    @BeforeEach
    @AfterEach
    void getRidOfGarbage() {
        geoRepo.deleteByDocumentIdStartingWith("TestObs");
    }

    @Test
    void testCOREmongoJsonShouldConsumeAndProduceGeoJson()  {
        //1. prepare
        String sampleLineString = "{" +
                "    \"type\": \"LineString\", " +
                "    \"coordinates\": [" +
                "        [30, 10], [10, 30], [40, 40]" +
                "    ]" +
                "}";
        TestObsDoc obsDoc = new TestObsDoc();
        obsDoc.setGeom(MongoHelper.convertStringToGeometry(sampleLineString));
        String randomID = "TestObsDocGJ"+ new Random().nextInt();
        obsDoc.setDocumentId(randomID);

        //2. run the Thing
        obsRepo.insert(obsDoc);
        ObservationDocument recupDoc = obsRepo.findByDocumentId(randomID);

        //3. assert stuff, not executed
        assertThat(recupDoc)
                .isNotNull()
                .isInstanceOf(TestObsDoc.class); // en bonus, spring-M-db gere l'heritage : cool !
        TestObsDoc obsDocWithGeom = (TestObsDoc) recupDoc;
        assertThat(obsDocWithGeom).isNotNull();

        assertThat(obsDocWithGeom.getGeom()).isNotNull();
        assertThat(obsDocWithGeom.getGeom().getType()).isEqualTo(GeoJsonObjectType.LINE_STRING);
        assertThat(obsDocWithGeom.getGeom()).isInstanceOf(LineString.class);

        LineString ls = (LineString) obsDocWithGeom.getGeom();
        assertThat(ls.getCoordinates()).hasSizeGreaterThan(2); // numerous points
        assertThat(ls.getCoordinates()).hasOnlyElementsOfType(Position.class);

    }

    @Test
    void testCOREmongoJsonShouldInsertAndProduceMultiPolyGeoJson()  {
        //1. prepare
        String sampleMultipoly = "{\n" +
                "    \"type\": \"MultiPolygon\", \n" +
                "    \"coordinates\": [\n" +
                "        [\n" +
                "            [[40, 40], [20, 45], [45, 30], [40, 40]]\n" +
                "        ], \n" +
                "        [\n" +
                "            [[20, 35], [10, 30], [10, 10], [30, 5], [45, 20], [20, 35]], \n" +
                "            [[30, 20], [20, 15], [20, 25], [30, 20]]\n" +
                "        ]\n" +
                "    ]\n" +
                "}";
        TestObsDoc obsDoc = new TestObsDoc();
        obsDoc.setGeom(MongoHelper.convertStringToGeometry(sampleMultipoly));
        String randomID = "TestObsDocGJ"+ new Random().nextInt();
        obsDoc.setDocumentId(randomID);

        //2. run the Thing, an delete
        obsRepo.insert(obsDoc);
        ObservationDocument recupDoc = obsRepo.findByDocumentId(randomID);

        //3. assert stuff, not executed
        assertThat(recupDoc)
                .isNotNull()
                .isInstanceOf(TestObsDoc.class); // en bonus, spring-M-db gere l'heritage : cool !
        TestObsDoc obsDocWithGeom = (TestObsDoc) recupDoc;
        assertThat(obsDocWithGeom).isNotNull();

        assertThat(obsDocWithGeom.getGeom()).isNotNull();
        assertThat(obsDocWithGeom.getGeom().getType()).isEqualTo(GeoJsonObjectType.MULTI_POLYGON);
        assertThat(obsDocWithGeom.getGeom()).isInstanceOf(MultiPolygon.class);

        MultiPolygon mp = (MultiPolygon) obsDocWithGeom.getGeom();
        assertThat(mp.getCoordinates()).hasSize(2); // 2 poly dns ce multipoly
        assertThat(mp.getCoordinates()).hasOnlyElementsOfType(PolygonCoordinates.class);
    }


    @Test
    void testObsDocShouldBeFindableViaGeoQuery () {
        // prepare Geom that will match the GeoQuery
        String insideLS = "{" +
                "    \"type\": \"LineString\", " +
                "    \"coordinates\": [" +
                "        [3, 1], [1, 3], [4, 4]" +
                "    ]" +
                "}";
        TestObsDoc obsDoc = new TestObsDoc();
        obsDoc.setGeom( MongoHelper.convertStringToGeometry(insideLS) );
        String randomID = "TestObsDocGJ"+ new Random().nextInt();
        obsDoc.setDocumentId(randomID);
        obsRepo.insert(obsDoc);
        // prepare Geom that will NOT match the GeoQuery
        String outsideLS = "{" +
                "    \"type\": \"LineString\", " +
                "    \"coordinates\": [" +
                "        [30, 1], [1, 3], [4, 40]" +  // <-- think out of the box !
                "    ]" +
                "}";
        TestObsDoc obsDocOut = new TestObsDoc();
        obsDoc.setGeom( MongoHelper.convertStringToGeometry(outsideLS) );
        String randomIDout = "TestObsDocGJ"+ new Random().nextInt();
        obsDoc.setDocumentId(randomIDout);
        obsRepo.insert(obsDocOut);

        List<TestObsDoc> result = geoRepo.findByGeomWithin(new GeoJsonPolygon(
                new Point(0,0), new org.springframework.data.geo.Point(0,20), new Point(20,20), new Point(20,0), new Point(0,0) ) );

        assertThat(result)
                .isNotEmpty()
                .anyMatch( d -> d.getDocumentId().equals(randomID) )
                .noneMatch( d -> d.getDocumentId().equals(randomIDout) );
    }


    @Test
    void testDocumentToStringIsNotToJson() {
        // prepare multi Geom with polygon hole
        String sampleMultipoly = "{\n" +
                "    \"type\": \"MultiPolygon\", \n" +
                "    \"coordinates\": [\n" +
                "        [\n" +
                "            [[0, 40], [20, 85], [45, 30], [0, 40]]\n" +
                "        ], \n" +
                "        [\n" +
                "            [[20, 35], [10, 30], [10, 10], [30, 5], [45, 20], [20, 35]], \n" +
                "            [[30, 20], [20, 15], [20, 25], [30, 20]]\n" +
                "        ]\n" +
                "    ]\n" +
                "}";
        TestObsDoc obsDoc = new TestObsDoc();
        obsDoc.setGeom( MongoHelper.convertStringToGeometry(sampleMultipoly) );
        String randomID = "TestObsDocGJ"+ new Random().nextInt();
        obsDoc.setDocumentId(randomID);
        obsRepo.insert(obsDoc);

        // inside the polygon
        List<TestObsDoc> ptMatchList = geoRepo.findGeometryIsInside(new GeoJsonPoint(6,45));
        assertThat(ptMatchList)
                .isNotEmpty()
                .anyMatch( d -> d.getDocumentId().equals(randomID) );

        // in the polygon hole
        List<TestObsDoc> ptnotmatchList = geoRepo.findGeometryIsInside(new GeoJsonPoint(22,20));
        assertThat(ptnotmatchList)
                .isEmpty() ;
    }
}
