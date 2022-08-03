package fr.theialand.insitu.dataportal.importmetadata.service.validation;

import fr.theialand.insitu.dataportal.importmetadata.Exceptions.ImportException;
import fr.theialand.insitu.dataportal.importmetadata.Exceptions.TheiaCategoriesException;
import fr.theialand.insitu.dataportal.importmetadata.service.FileUtils;
import fr.theialand.insitu.dataportal.importmetadata.service.ImportConfig;
import fr.theialand.insitu.dataportal.importmetadata.service.JsonUtils;
import fr.theialand.insitu.dataportal.model.pivot.Pivot;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.dataset.Keyword;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.observation.I18n;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.observation.ObservedProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.zip.ZipFile;

@Service
public class InputValidationServiceImpl implements InputValidationService {

    private final JsonFileValidationService jsonFileValidationService;
    private final DataFileValidationService dataFileValidationService;

    private static final Logger LOG = LoggerFactory.getLogger(InputValidationServiceImpl.class);
    private static final Marker USER = MarkerFactory.getMarker("USER");


    @Autowired
    InputValidationServiceImpl(JsonFileValidationService jsonFileValidationService,
                               DataFileValidationService dataFileValidationService) {
        this.jsonFileValidationService = jsonFileValidationService;
        this.dataFileValidationService = dataFileValidationService;
    }


    @Override
    public ImportConfig validateAndGetConfig(@NonNull File producerDataPath) throws ImportException, IOException {
        if( !producerDataPath.isDirectory() )
            throw new ImportException(producerDataPath.getAbsolutePath()+" should be a directory" );

        String pathToFilesAsString = producerDataPath.getName();

        /*
         * list all the json files of the folder (****_en.json + ****_fr.json ...) list all the zip files of the folder
         */
        List<File> jsonFiles = FileUtils.listFiles(producerDataPath, "json");
        List<File> zipFiles  = FileUtils.listFiles(producerDataPath, "zip");

        if ( zipFiles.isEmpty() )
            LOG.warn(USER, "ZIP data files not found in the folder {}, no validation possible. continuing...", pathToFilesAsString);
        else {
            LOG.info(USER, "Validate the json file names -> should have the form PROD_en.json and additionally PROD_fr.json ");
            this.jsonFileValidationService.validateJsonFileNames(producerDataPath.getAbsolutePath());
        }

        // Map<Language, Map<ObservationID, boolean>>
        Map<String, Map<String, Boolean>> linkedToDataFile = new HashMap<>();

        /*
         * Validation of the JSON files
         */
        for (File jsonFile : jsonFiles) {

            /*
             * Check the json format using JSON schema
             */
            LOG.info(USER, "Validate the json file formats against the json schema at https://in-situ.theia-land.fr/json-schema/");
            Pivot pivot = this.jsonFileValidationService.validateJsonSchema(jsonFile.getAbsolutePath());
            LOG.info(USER, "Schema validation successfull for {}", jsonFile.getName());


            /*
             * Check that the ids of the json file correspond to the parent folder name as specified in the
             * documentation
             */
            LOG.info(USER, "Validate producer, datasets and observations IDs");
            this.jsonFileValidationService.validateJsonFileIds(pivot);

            /*
             * Get the language of the Json File
             * More like a static in a helper/util class than a service contractor
             */
            String lang = JsonUtils.getLangFromJsonFile(jsonFile);

            /*
             * if the validation of the JSON file is a success, then we check that the file name contained in the JSON
             * object are equals to those contained in the zip file
             */
            /*
             * Validation of the ZIP files
             */

            if (!zipFiles.isEmpty()) {
                try {
                    linkedToDataFile.put(lang, this.dataFileValidationService.validateZipFiles(pivot, zipFiles));
                } catch (IOException ioEx) {
                    throw new ImportException("can't validate zip file with json objcet", ioEx);
                }
                try (ZipFile notUsed = new ZipFile(zipFiles.get(0))){
                    // missing a "Do.nothingWith(Object) here
                    // need to use a try-with-resource to properly close the zip (done)
                } catch (IOException ioEx) {
                    throw new ImportException("can't zip "+zipFiles.get(0).getName(), ioEx);
                }
            } else {
                LOG.warn(USER, "No zip files containing the data files");
                Map<String, Boolean> tmp = new LinkedHashMap<>();
                pivot.getDatasets().forEach(dataset
                        -> dataset.getObservations().forEach(obs -> tmp.put(obs.getObservationId(), Boolean.FALSE))
                );
                linkedToDataFile.put(lang, tmp);
            }
        }

        ImportConfig importConfig = new ImportConfig(linkedToDataFile,jsonFiles);

        /*
         * if there is several files due to internationalisation we check that the fields that are not subjected to
         * internationalisation are identicals between the files
         */
        if (importConfig.getLanguages().length > 1)
            LOG.info("Validating the coherence of the non traduced information between the two json files");
            this.jsonFileValidationService.validateJsonInternationalisation(jsonFiles);

        return importConfig;
    }

    @Override
    public ObservedProperty validateTheiaCategoriesOfJson(ObservedProperty observedProperty) throws TheiaCategoriesException {
        List<String> theiaCategories = new ArrayList<>();
        theiaCategories.addAll(observedProperty.getTheiaCategories());

        /**
         * Check if the uri correspond to a theia Variable
         */
        if(theiaCategories.size() == 1 && jsonFileValidationService.isATheiaVariable(theiaCategories.get(0))) {
            /*
            The URI is a Theia Variable
            1 - We retrieve the corresponding Theia Categories using the terminologic service
            2 - The Theia variable associated by the producer is stored in the observedProperty.keywords.
             */
            observedProperty.setTheiaCategories(jsonFileValidationService.getBroaderCategoriesOfVariable(theiaCategories.get(0)));
            Keyword keyword = new Keyword();
            keyword.setUri(theiaCategories.get(0));
            I18n i18n = new I18n();
            i18n.setLang("en");
            i18n.setText(this.jsonFileValidationService.getPrefLabel(theiaCategories.get(0)));
            keyword.setKeyword(List.of(i18n));
            List<Keyword> producerKeyword = new ArrayList<>();
            if(observedProperty.getKeywords() != null)
            producerKeyword.addAll(observedProperty.getKeywords());
            producerKeyword.add(keyword);
            observedProperty.setKeywords(producerKeyword);
        } else {
            for(int j = 0; j < theiaCategories.size(); j++){
                /*
                Check whether the uri corresponds to a valid category (not deprecated)
                 */
                if(!jsonFileValidationService.isValidCategory(theiaCategories.get(j))){
                    /*
                    If the category is not valid, check whether it correponds to a deprecated one
                     */
                    if (jsonFileValidationService.isADeprecatedCategory(theiaCategories.get(j))) {
                        LOG.warn(theiaCategories.get(j) + " is a deprecated category");
                        /*
                        If the uri corresponds to a deprecated category, this uri is replaced by a valid category using the
                        dct:isReplacedBy relation
                        */
                        String replacedUri = jsonFileValidationService.getIsReplacedByUri(theiaCategories.get(j));
                        if(replacedUri != null) {
                            theiaCategories.set(j, replacedUri);
                        } else {
                            LOG.error(theiaCategories.get(j) + "is not a valid category");
                            throw new TheiaCategoriesException(theiaCategories.get(j));
                        }
                    } else {
                        LOG.error(theiaCategories.get(j) + "is not a valid category");
                        throw new TheiaCategoriesException(theiaCategories.get(j));
                    }

                }
            }
            observedProperty.setTheiaCategories(theiaCategories);
        }
        return observedProperty;
    }
}
