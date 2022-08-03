package fr.theialand.insitu.dataportal.repository.mongo.configuration;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.mongodb.client.model.geojson.Geometry;

import java.io.IOException;

public class MongoGeometrySerializer extends JsonSerializer<Geometry> {
    @Override
    public void serialize(Geometry geometry, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeRawValue(geometry.toJson());
    }
}
