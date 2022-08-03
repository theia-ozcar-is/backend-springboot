package fr.theialand.insitu.dataportal.model.configuration.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.mongodb.client.model.geojson.Geometry;
import com.mongodb.client.model.geojson.codecs.GeoJsonCodecProvider;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.json.JsonReader;
import org.springframework.boot.jackson.JsonObjectDeserializer;

import java.io.IOException;

public class MongoGeometryDeserializer extends JsonObjectDeserializer<Geometry> {

    @Override
    protected Geometry deserializeObject(JsonParser jsonParser, DeserializationContext context, ObjectCodec codec, JsonNode tree) throws IOException {
        CodecRegistry cr = CodecRegistries.fromProviders(new GeoJsonCodecProvider()); // might not be needed in real code
        return cr.get(Geometry.class).decode(new JsonReader(tree.toString()), DecoderContext.builder().build() );
    }

}