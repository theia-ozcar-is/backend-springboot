package fr.theialand.insitu.dataportal.repository.mongo.geojson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.model.geojson.GeoJsonObjectType;
import com.mongodb.client.model.geojson.Geometry;
import com.mongodb.client.model.geojson.LineString;
import com.mongodb.client.model.geojson.Position;
import com.mongodb.client.model.geojson.codecs.GeoJsonCodecProvider;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.json.JsonReader;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.data.mongodb.core.geo.*;
import org.springframework.lang.NonNull;

import static org.assertj.core.api.Assertions.assertThat;

@Disabled("kind of a PoC test, not really a test")
class GeoJsonTest {

    // ##############################################################
    // SPRING ! mongoDB geojon package , TOO MUCH HASSLE
    // #################################################

    /**
     * FAIL , for documentation only.
     */
    @Test
    @Disabled("kept for better understanding of the issue number 1 with S-D-MDB : no concrete class => need a dirty switch Case !")
    void testSPRINGManuallyDeserializeJsonShouldProduceGeoJson() throws JsonProcessingException {
        //1. prepare
        String sampleLineString = "{" +
                "    \"type\": \"LineString\", " +
                "    \"coordinates\": [" +
                "        [30, 10], [10, 30], [40, 40]" +
                "    ]" +
                "}";
        ObjectMapper om = new ObjectMapper();
        om.registerModule(new GeoJsonModule());

        //2. run the Thing
        GeoJson<?> geoJson = om.readValue(sampleLineString, GeoJson.class);
        // FAIL because GeoJson is an interface, should be a concrete type
        // so we need a function or module wrapper. see at end of class
        // would work: GeoJson<?> geoJson = om.readValue(sampleLineString, GeoJsonLineString.class); // but too narrow
        // we don't want to write unwanted boilerplate code unless forced to

        //3. assert stuff, not executed
        assertThat(geoJson)
                .isNotNull()
                .isInstanceOf(GeoJsonLineString.class);
    }

    /**
     * FAIL , for documentation only.
     */
    @Test
    @Disabled(" Fail, issue number 2 with S-D-MDB: it persist x,y Position and unwanted stuff and structure" )
    void testSPRINGManuallySerializeGeoJsonObjectShouldProduceJsonString() throws JsonProcessingException {
        //1. prepare a spring geojsonLineString
        String sampleLineString = "{" +
                "    \"type\": \"LineString\", " +
                "    \"coordinates\": [" +
                "        [30, 10], [10, 30], [40, 40]" +
                "    ]" +
                "}" ;
        ObjectMapper om = new ObjectMapper();
        om.registerModule(new GeoJsonModule());
        GeoJsonLineString gjls = om.readValue(sampleLineString,  GeoJsonLineString.class);

        //2. run the Thing
        String jsonString = om.writeValueAsString(gjls);

        //3. assert stuff
        assertThat(jsonString)
                .isNotNull()
                .contains("coordinates")
                .contains("\"type\":\"LineString\"")
                .doesNotContain("\"x\":") //fail
                .doesNotContain("\"y\":") //fail
                .doesNotContain("Position") //fail
                .doesNotContain("Point"); // fail
    }

    // #################################################
    // mongoDB CORE geojon package , MIGHT WORK
    // #################################################

    /**
     *  Test of a linestring, STRONGLY typed
     *  Kept for documentation, it would need some wrapper code like a swith/case getType ...
     *  In next test, we get rid of that need.
     */
    @Test
    void testParseLineStringJsonStringShouldReturnLineStringObject() {
        //1. prepare
        String sampleLineString = "{" +
                "    \"type\": \"LineString\", " +
                "    \"coordinates\": [" +
                "        [30, 10], [10, 30], [40, 40]" +
                "    ]" +
                "}" ;

        //2. run the Thing
        CodecRegistry cr = CodecRegistries.fromProviders(new GeoJsonCodecProvider()); // might not be necessary in RL
        LineString ls = cr.get(LineString.class).decode(new JsonReader(sampleLineString), DecoderContext.builder().build() );

        //3. assert stuff
        assertThat(ls)
                .isNotNull()
                .isInstanceOf(LineString.class);
        assertThat(ls.getType())
                .isEqualTo(GeoJsonObjectType.LINE_STRING); // nice ! an enum, at last !
        assertThat(ls.getCoordinates())
                .hasSize(3)
                .hasOnlyElementsOfType(Position.class);
    }

    /**
     *  Test of a "geometry'", loosely typed
     *  THAT is what we want.
     *  we won't force people to give us Polygons or LS, they can give us ANY valid geometry
     */
    @Test
    void testParseJsonStringShouldReturnGeomObject() {
        //1. prepare
        String anyGeoJson = "{\n" +
                "        \"type\": \"Polygon\",\n" +
                "        \"coordinates\": [\n" +
                "          [\n" +
                "            [\n" +
                "              2.63671875,\n" +
                "              42.74701217318067\n" +
                "            ],\n" +
                "            [\n" +
                "              5.185546875,\n" +
                "              42.74701217318067\n" +
                "            ],\n" +
                "            [\n" +
                "              5.185546875,\n" +
                "              47.69497434186282\n" +
                "            ],\n" +
                "            [\n" +
                "              2.63671875,\n" +
                "              47.69497434186282\n" +
                "            ],\n" +
                "            [\n" +
                "              2.63671875,\n" +
                "              42.74701217318067\n" +
                "            ]\n" +
                "          ]\n" +
                "        ]\n" +
                "      }";

        //2. run the Thing , Geometry is an abstract of all GeoJson
        CodecRegistry cr = CodecRegistries.fromProviders(new GeoJsonCodecProvider()); // might not be needed in real code
        Geometry unknownGeom = cr.get(Geometry.class).decode(new JsonReader(anyGeoJson), DecoderContext.builder().build() );

        //3. assert stuff
        assertThat(unknownGeom)
                .isNotNull()
                .isExactlyInstanceOf(LineString.class); // <--  this is what matters
        assertThat(unknownGeom.getType())
                .isEqualTo(GeoJsonObjectType.POLYGON);
        assertThat(unknownGeom.toJson())  // dirty check of a json ;)
                .isNotEmpty()
                .startsWith("{").endsWith("}")
                .contains("coordinates")
                .contains("\"type\": \"LineString\"")
                .contains("[30.0, 10.0]") // it converted 30 to 30.0  .... okay
                .doesNotContain("\"x\":")
                .doesNotContain("\"y\":")
                .doesNotContain("Position")
                .doesNotContain("Point");
        // good enough ...
    }

    /**
     * @deprecated only needed with the S-D-MDB solution, NOT needed with CORE mongo packages
     */
    @Deprecated
    private GeoJson<?> myDeserizzer(@NonNull JsonNode unknownGeoJsonNode)  {
        ObjectMapper om = new ObjectMapper();
        om.registerModule(new GeoJsonModule());
        switch( unknownGeoJsonNode.get("type").textValue() ) {
            case "Point":
                return om.convertValue(unknownGeoJsonNode, GeoJsonPoint.class);
            case "LineString":
                return om.convertValue(unknownGeoJsonNode, GeoJsonLineString.class);
            case "MultiPolygon":
                return om.convertValue(unknownGeoJsonNode, GeoJsonMultiPolygon.class);
            case "GeometryCollection":
                return om.convertValue(unknownGeoJsonNode, GeoJsonGeometryCollection.class);
            //...
            default:
                throw new IllegalArgumentException("not yet implemented, mate!") ;
        }






    }
}
