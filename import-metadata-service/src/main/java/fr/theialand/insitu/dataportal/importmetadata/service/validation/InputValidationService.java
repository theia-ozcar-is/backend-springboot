package fr.theialand.insitu.dataportal.importmetadata.service.validation;

import fr.theialand.insitu.dataportal.importmetadata.Exceptions.ImportException;
import fr.theialand.insitu.dataportal.importmetadata.Exceptions.TheiaCategoriesException;
import fr.theialand.insitu.dataportal.importmetadata.service.ImportConfig;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.observation.ObservedProperty;
import org.springframework.lang.NonNull;

import java.io.File;
import java.io.IOException;

/**
 * Provide methods to validate the files dropped by a producer
 * Should be the entrypoint to validation of said files
 */
public interface InputValidationService {

    /**
     * Validate files format of jthe json and zip files on a given path
     *
     * returns an ImportConfing if it lokks good and the workflow can continue
     * @param producerDataPath A directory where the producer stuff has been unzipped
     * @return @{@link ImportConfig}
     * @throws ImportException problem in our process
     * @throws IOException problem on IO
     */
    ImportConfig validateAndGetConfig(@NonNull File producerDataPath) throws ImportException, IOException;

//
//    /**
//     * Verify that the Theia Categs in there match the one we have on our thesaurus online.
//     * Validate Theia category uris to be leaf of the Theia Category tree.
//     * @param jsonInternationalised I18n-ed and empty strings cleaned !
//     * @throws TheiaCategoriesException in case smthg dont match
//     */
//    void validateTheiaCategoriesOfJson(JsonNode jsonInternationalised) throws TheiaCategoriesException;


    /**
     * Validate the observedProperty.theiaCategories field. 3 possibilities are checked.
     *  - The theiaCategories correspond to a valid category from the terminologic service
     *  - The theiaCategories correspond to a deprecated category from the terminologic service
     *  - The theiaCategories correspond to a valid variable
     *
     * @throws TheiaCategoriesException if the uri is not valid
     */
    ObservedProperty validateTheiaCategoriesOfJson(ObservedProperty observedProperty) throws TheiaCategoriesException;
}
