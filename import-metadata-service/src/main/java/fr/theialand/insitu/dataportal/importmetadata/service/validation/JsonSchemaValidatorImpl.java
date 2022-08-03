package fr.theialand.insitu.dataportal.importmetadata.service.validation;

import com.fasterxml.jackson.databind.JsonNode;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;
import fr.theialand.insitu.dataportal.importmetadata.Exceptions.ImportException;
import fr.theialand.insitu.dataportal.importmetadata.service.JsonUtils;
import fr.theialand.insitu.dataportal.model.pivot.Pivot;
import fr.theialand.insitu.dataportal.model.pivot.dataset.Dataset;
import fr.theialand.insitu.dataportal.model.pivot.observation.Observation;
import fr.theialand.insitu.dataportal.model.pivot.producer.Producer;
import fr.theialand.insitu.dataportal.model.service.JsonSchemaGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Map;
import java.util.Set;

@Service
public class JsonSchemaValidatorImpl implements JsonSchemaValidator {

    private final Map<Object, JsonNode> jsonSchemas;

    private final JsonSchemaGenerator schemaGenerator;

    private static final Logger LOG = LoggerFactory.getLogger(JsonSchemaValidatorImpl.class);

    @Autowired
    JsonSchemaValidatorImpl( JsonSchemaGenerator schemaGenerator) {
        this.schemaGenerator = schemaGenerator;

        // kind of pre-build schemas...
        this.jsonSchemas = Map.of(
                Producer.class, this.schemaGenerator.getJsonSchema(Producer.class),
                Dataset.class , this.schemaGenerator.getJsonSchema(Dataset.class),
                Observation.class, this.schemaGenerator.getJsonSchema(Observation.class)) ;
    }

    @Override
    public Set<ValidationMessage> validateFile(@NonNull File producerFile) throws ImportException {
        LOG.info("Trying to validate {} against the Pivot schema", producerFile.getName());
        return this.validateNode( JsonUtils.getNodeFromFile(producerFile), Pivot.class );
    }

    @Override
    public Set<ValidationMessage> validateNode(@NonNull JsonNode node) throws ImportException {
        return this.validateNode(node,Pivot.class);
    }

    @Override
    public Set<ValidationMessage> validateNode(@NonNull JsonNode node, @NonNull Class<?> againstClass) throws ImportException {
        // try to get the pre-built schema. otherwise make a new one.
        JsonNode schemaNode = jsonSchemas.getOrDefault(againstClass, this.schemaGenerator.getJsonSchema(againstClass) );

        LOG.info("Trying to validate against the {} schema", againstClass.getSimpleName());
        JsonSchema schemaValidator = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V201909).getSchema(schemaNode);
        return schemaValidator.validate(node);
    }
}
