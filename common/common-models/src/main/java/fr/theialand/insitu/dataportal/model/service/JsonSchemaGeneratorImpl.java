package fr.theialand.insitu.dataportal.model.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.victools.jsonschema.generator.SchemaGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service("jsonSchemaGenerator")
@Scope(value="prototype")
public class JsonSchemaGeneratorImpl implements JsonSchemaGenerator {

    /**
     * Simple OM for pretty print, nothing else
     */
    private final ObjectMapper om = new ObjectMapper();

    /**
     * the jsonschema lib from victools, see @{@link fr.theialand.insitu.dataportal.model.configuration.JsonSchemaGeneratorConfiguration}
     */
    private final SchemaGenerator generator;

    private static final Logger LOG = LoggerFactory.getLogger(JsonSchemaGeneratorImpl.class);

    @Autowired
    public JsonSchemaGeneratorImpl(@Value("${json.schema.version}")String schemaVersion, ApplicationContext context) {
        this.generator = (SchemaGenerator) context.getBean(SchemaGenerator.class, schemaVersion);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getJsonSchemaPrettyPrint(Class<?> classToGenerateFrom) throws JsonProcessingException {
        JsonNode jsonSchema = this.getJsonSchema(classToGenerateFrom);
        return om.writerWithDefaultPrettyPrinter().writeValueAsString(jsonSchema);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JsonNode getJsonSchema(Class<?> classToGenerateFrom) {
        LOG.info("construct Schema, using {} as parent class for definitions", classToGenerateFrom.getSimpleName());
        return generator.generateSchema(classToGenerateFrom);
    }

}
