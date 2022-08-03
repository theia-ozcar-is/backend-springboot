package fr.theialand.insitu.dataportal.repository.mongo.configuration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.dataset.Contact;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.dataset.Organisation;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.dataset.Person;
import org.springframework.boot.jackson.JsonObjectDeserializer;

import java.io.IOException;

//@Deprecated(forRemoval = true) //("use jackson 2.12 Auto Deduction feature")
public class ContactsDeserializer extends JsonObjectDeserializer<Contact> {

    //    @Override
//    public Contact deserialize(com.fasterxml.jackson.core.JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
//        JsonNode jn = jsonParser.getCodec().readTree(jsonParser);
//        if (!jn.has("firstName") || jn.get("firstName").isNull()) {
//            return objectMapper.treeToValue(jn, Organisation.class);
//        } else {
//            return objectMapper.treeToValue(jn, Person.class);
//        }
//    }

    @Override
    protected Contact deserializeObject(JsonParser jsonParser, DeserializationContext context, ObjectCodec codec, JsonNode tree) throws IOException {
        if (tree.hasNonNull("firstName")) {
            return jsonParser.getCodec().treeToValue(tree, Person.class);
        } else {
            return jsonParser.getCodec().treeToValue(tree, Organisation.class);
        }
    }
}

