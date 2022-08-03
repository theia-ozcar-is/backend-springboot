package fr.theialand.insitu.dataportal.importmetadata.service.validation;

import fr.theialand.insitu.dataportal.importmetadata.Exceptions.*;
import fr.theialand.insitu.dataportal.model.pivot.Pivot;
import org.springframework.lang.NonNull;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface JsonFileValidationService  {
    void validateJsonFileNames(String folderPath) throws InvalidJsonFileNameException, JsonFileNotFoundException, ImportException;
    Pivot validateJsonSchema(String jsonPath) throws ImportException;
    void validateJsonFileIds(@NonNull Pivot pivot) throws IOException, InvalidIdsException;
    void validateJsonInternationalisation(List<File> jsonFiles) throws IOException, InvalidJsonInternationalisationException;
    boolean isValidCategory(String uri) throws TheiaCategoriesException;
    boolean isATheiaVariable(String uri);
    List<String> getBroaderCategoriesOfVariable(String uri);
    String getPrefLabel(String uri);
    boolean isADeprecatedCategory(String uri);
    String getIsReplacedByUri(String uri);
}
