package fr.theialand.insitu.dataportal.repository.mongo.geojson;

import com.mongodb.client.model.geojson.Geometry;
import com.mongodb.client.model.geojson.codecs.GeoJsonCodecProvider;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.json.JsonReader;
import org.springframework.lang.NonNull;

/**
 * To be moved elswhere
 */
public class MongoHelper {

    private MongoHelper() {}

    /**
     * it is a parse() equivalent.
     * transform a String into a Geometry
     * @param geojsonAsString non null string
     * @return fully extended instance, like LineString
     */
    public static Geometry convertStringToGeometry(@NonNull String geojsonAsString) {
        CodecRegistry cr = CodecRegistries.fromProviders(new GeoJsonCodecProvider());
        return cr.get(Geometry.class).decode(new JsonReader(geojsonAsString), DecoderContext.builder().build() );
    }
}
