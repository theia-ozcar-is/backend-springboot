package fr.theialand.insitu.dataportal.repository.mongo.configuration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.observation.PhysicalSensor;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.observation.Sensor;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.observation.VirtualSensor;
import org.springframework.boot.jackson.JsonObjectDeserializer;

import java.io.IOException;

//@Deprecated(forRemoval = true) //("use jackson 2.12 Auto Deduction feature")
public class SensorCustomDeserializer extends JsonObjectDeserializer<Sensor> {

//    @Override
//    public Sensor deserialize(com.fasterxml.jackson.core.JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
//        JsonNode jn = jsonParser.getCodec().readTree(jsonParser);
//        if (jn.has("name") && !jn.get("name").isNull()) {
//            return objectMapper.treeToValue(jn, VirtualSensor.class);
//        } else {
//            return objectMapper.treeToValue(jn, PhysicalSensor.class);
//        }
//    }

    @Override
    protected Sensor deserializeObject(JsonParser jsonParser, DeserializationContext context, ObjectCodec codec, JsonNode tree) throws IOException {
        if(tree.hasNonNull("name")) {
            return jsonParser.getCodec().treeToValue(tree, VirtualSensor.class);
        } else {
            return jsonParser.getCodec().treeToValue(tree, PhysicalSensor.class);
        }
    }
}
