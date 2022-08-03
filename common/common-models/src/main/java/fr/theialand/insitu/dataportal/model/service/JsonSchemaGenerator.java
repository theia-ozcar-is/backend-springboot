package fr.theialand.insitu.dataportal.model.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

public interface JsonSchemaGenerator {

    /**
     * When we just want the schema (to store it somewhere? publish it ?)
     * @return Json pretty printed
     */
    String getJsonSchemaPrettyPrint(Class<?> classToGenerateFrom) throws JsonProcessingException;

    /**
     * get the schema of the given class as a {@link JsonNode} , useful for later validation
     * @param classToGenerateFrom {@link fr.theialand.insitu.dataportal.model.pivot.Pivot}, {@link fr.theialand.insitu.dataportal.model.pivot.dataset.Dataset} ...
     */
    JsonNode getJsonSchema(Class<?> classToGenerateFrom);
}
