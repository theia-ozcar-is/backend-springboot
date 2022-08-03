package fr.theialand.insitu.dataportal.importmetadata.service.validation;

import com.fasterxml.jackson.databind.JsonNode;
import com.networknt.schema.ValidationMessage;
import fr.theialand.insitu.dataportal.importmetadata.Exceptions.ImportException;
import org.springframework.lang.NonNull;

import java.io.File;
import java.util.Set;

public interface JsonSchemaValidator {

    Set<ValidationMessage> validateFile(@NonNull File producerFile) throws ImportException;
    Set<ValidationMessage> validateNode(@NonNull JsonNode node ) throws ImportException;

    Set<ValidationMessage> validateNode(@NonNull JsonNode node, @NonNull Class<?> againstClass ) throws ImportException;


    }
