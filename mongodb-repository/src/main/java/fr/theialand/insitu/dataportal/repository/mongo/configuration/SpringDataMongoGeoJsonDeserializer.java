package fr.theialand.insitu.dataportal.repository.mongo.configuration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.data.mongodb.core.geo.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@JsonComponent
public class SpringDataMongoGeoJsonDeserializer extends StdDeserializer<GeoJson> {

    public SpringDataMongoGeoJsonDeserializer(){
        this(GeoJson.class);
    }
    public SpringDataMongoGeoJsonDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public GeoJson deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        return deserializeGeoJson(jsonParser.getCodec().readTree(jsonParser));
    }

    private <T extends GeoJson> T deserializeGeoJson(JsonNode node) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        om.registerModule(new GeoJsonModule());
        switch (node.get("type").asText()) {
            case "Point":
                return (T) om.treeToValue(node, GeoJsonPoint.class);
            case "MultiPoint":
                return (T) om.treeToValue(node, GeoJsonMultiPoint.class);
            case "LineString":
                return (T) om.treeToValue(node, GeoJsonLineString.class);
            case "MultiLineString":
                return (T) om.treeToValue(node,GeoJsonMultiLineString.class);
            case "Polygon":
                return (T) om.treeToValue(node,GeoJsonPolygon.class);
            case "MultiPolygon":
                return (T) om.treeToValue(node,GeoJsonMultiPolygon.class);
            case "GeometryCollection":
                List<GeoJson<?>> geometries = new ArrayList<>();
                for (JsonNode geom: node.get("geometries")){
                    geometries.add(this.deserializeGeoJson(geom));
                }
                return (T) new GeoJsonGeometryCollection(geometries);
        }
        return null;
    }
}

