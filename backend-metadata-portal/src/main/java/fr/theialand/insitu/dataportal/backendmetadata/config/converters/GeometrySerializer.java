package fr.theialand.insitu.dataportal.backendmetadata.config.converters;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.mongodb.client.model.geojson.Geometry;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

/**
 * This Bean will be used automatically by Spring when looking for serialization of a {@link Geometry}
 * -> will be used automagically by the {@link org.springframework.web.bind.annotation.RestController}
 */
@JsonComponent
public class GeometrySerializer extends JsonSerializer<Geometry> {
    @Override
    public void serialize(Geometry geom, JsonGenerator jsonGenerator, SerializerProvider serializers) throws IOException {
        jsonGenerator.writeRawValue(geom.toJson() ) ;
    }
}
