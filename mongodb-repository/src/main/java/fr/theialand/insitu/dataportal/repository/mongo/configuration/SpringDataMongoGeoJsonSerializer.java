package fr.theialand.insitu.dataportal.repository.mongo.configuration;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.geo.GeoJson;
import org.springframework.data.mongodb.core.geo.GeoJsonLineString;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.geo.GeoJsonPolygon;

import java.io.IOException;
import java.util.List;

@JsonComponent
public class SpringDataMongoGeoJsonSerializer extends JsonSerializer<GeoJson> {

    @Override
    public void serialize(GeoJson geoJson, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("type", geoJson.getType());
        switch (geoJson.getType()) {
            case "Point": {
                jsonGenerator.writeFieldName("coordinates");
                Point point = (GeoJsonPoint)geoJson;
                jsonGenerator.writeObject(new double[]{point.getX(), point.getY()});
            }
            break;
            case "MultiPoint":
            case "LineString": {
                jsonGenerator.writeArrayFieldStart("coordinates");
                for (Point p : (List<Point>) geoJson.getCoordinates()) {
                    jsonGenerator.writeObject(new double[]{p.getX(), p.getY()});
                }
                jsonGenerator.writeEndArray();
            }
            break;
            case "MultiLineString":
            case "Polygon": {
                jsonGenerator.writeArrayFieldStart("coordinates");
                for (GeoJsonLineString ls : (List<GeoJsonLineString>) geoJson.getCoordinates()) {
                    jsonGenerator.writeStartArray();
                    for (Point p : ls.getCoordinates()) {
                        jsonGenerator.writeObject(new double[]{p.getX(), p.getY()});
                    }
                    jsonGenerator.writeEndArray();
                }
                jsonGenerator.writeEndArray();
            }
            break;
            case "MultiPolygon": {
                jsonGenerator.writeArrayFieldStart("coordinates");
                for (GeoJsonPolygon pol : (List<GeoJsonPolygon>) geoJson.getCoordinates()) {
                    jsonGenerator.writeStartArray();
                    for (GeoJsonLineString ls : pol.getCoordinates()) {
                        jsonGenerator.writeStartArray();
                        for (Point p : ls.getCoordinates()) {
                            jsonGenerator.writeObject(new double[]{p.getX(), p.getY()});
                        }
                        jsonGenerator.writeEndArray();
                    }
                    jsonGenerator.writeEndArray();
                }
                jsonGenerator.writeEndArray();
            }
            break;
            case "GeometryCollection": {
                jsonGenerator.writeArrayFieldStart("geometries");
                for (GeoJson<?> geo : (List<GeoJson<?>>) geoJson.getCoordinates()) {
                    this.serialize(geo, jsonGenerator, serializerProvider);
                }
                jsonGenerator.writeEndArray();
            }
        }
        jsonGenerator.writeEndObject();
    }
}
