package fr.theialand.insitu.dataportal.repository.mongo.configuration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.geojsonproperties.AdministrativeFeatureProperties;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.geojsonproperties.Properties;
import org.springframework.boot.jackson.JsonObjectDeserializer;

import java.io.IOException;

//@Deprecated(forRemoval = true) //("use jackson 2.12 Auto Deduction feature")
public class FeatureOfInterestPropertiesDeserializer extends JsonObjectDeserializer<Properties> {

//    @Override
//    public Properties deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
//        JsonNode jn = p.getCodec().readTree(p);
//        if (jn.has("name") && jn.has("admin_level") && !jn.get("name").isNull()) {
//            return objectMapper.treeToValue(jn, AdministrativeFeatureProperties.class);
//        } else {
//            return null;
//        }
//    }

    @Override
    protected Properties deserializeObject(JsonParser jsonParser, DeserializationContext context, ObjectCodec codec, JsonNode tree) throws IOException {
        if(tree.hasNonNull("name") && tree.has("admin_level")) {
            return jsonParser.getCodec().treeToValue(tree, AdministrativeFeatureProperties.class);
        } else {
            return null;
        }
    }
}
