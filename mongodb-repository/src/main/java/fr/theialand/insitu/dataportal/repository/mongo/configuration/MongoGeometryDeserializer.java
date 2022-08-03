package fr.theialand.insitu.dataportal.repository.mongo.configuration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.mongodb.client.model.geojson.Geometry;
import com.mongodb.client.model.geojson.codecs.GeoJsonCodecProvider;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.json.JsonReader;

import java.io.IOException;

public class MongoGeometryDeserializer extends JsonDeserializer<Geometry>{

    @Override
    public Geometry deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        CodecRegistry cr = CodecRegistries.fromProviders(new GeoJsonCodecProvider()); // might not be needed in real code
        return cr.get(Geometry.class).decode(new JsonReader(jsonParser.readValueAsTree().toString()), DecoderContext.builder().build() );
    }
}
