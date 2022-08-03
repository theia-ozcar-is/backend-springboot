package fr.theialand.insitu.dataportal.repository.mongo.configuration;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.observation.SamplingFeature;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.observation.SamplingGeometry;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.observation.SamplingSpecimen;
import org.springframework.boot.jackson.JsonObjectDeserializer;

import java.io.IOException;

//@Deprecated(forRemoval = true) //("use jackson 2.12 Auto Deduction feature")
public class SamplingFeatureDeserializer extends JsonObjectDeserializer<SamplingFeature> {

//    @Override
//    public SamplingFeature deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
//        JsonNode jn = jsonParser.getCodec().readTree(jsonParser);
//        if (!jn.has("geometry") || jn.get("geometry").isNull()) {
//            return objectMapper.treeToValue(jn, SamplingSpecimen.class);
//        } else {
//            return objectMapper.treeToValue(jn, SamplingGeometry.class);
//        }
//    }

    @Override
    protected SamplingFeature deserializeObject(JsonParser jsonParser, DeserializationContext context, ObjectCodec codec, JsonNode tree) throws IOException {
        if(tree.hasNonNull("geometry") ) {
            return jsonParser.getCodec().treeToValue(tree, SamplingGeometry.class);
        } else {
            return jsonParser.getCodec().treeToValue(tree, SamplingSpecimen.class);
        }
    }
}
