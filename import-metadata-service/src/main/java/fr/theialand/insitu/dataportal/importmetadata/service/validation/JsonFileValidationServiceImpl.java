/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.theialand.insitu.dataportal.importmetadata.service.validation;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.networknt.schema.ValidationMessage;
import fr.theialand.insitu.dataportal.api.sparql.query.RDFQueryClient;
import fr.theialand.insitu.dataportal.importmetadata.Exceptions.*;
import fr.theialand.insitu.dataportal.importmetadata.service.FileUtils;
import fr.theialand.insitu.dataportal.model.pivot.Pivot;
import fr.theialand.insitu.dataportal.model.pivot.dataset.Dataset;
import fr.theialand.insitu.dataportal.model.pivot.observation.Observation;
import org.apache.commons.collections4.IteratorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static fr.theialand.insitu.dataportal.importmetadata.service.FileUtils.readFile;

/**
 *
 * @author coussotc
 */
@Service
public class JsonFileValidationServiceImpl implements JsonFileValidationService{

    @Value("${json.schema.url}")
    private String jsonSchemaUrl;

    private final RDFQueryClient rdfDao;

    private static final Logger logger = LoggerFactory.getLogger(JsonFileValidationServiceImpl.class);
    private static final Marker USER = MarkerFactory.getMarker("USER");

    private final ObjectMapper schemaObjectMapper;

    private final JsonSchemaValidator schemaValidator;

    private List<String> categoryUris;

    @Autowired
    private JsonFileValidationServiceImpl(RDFQueryClient rdfDao,
                                          @Qualifier("jsonSchemaObjectMapper") ObjectMapper schemaObjectMapper,
                                          JsonSchemaValidator schemaValidator) {
        this.rdfDao = rdfDao;
        this.schemaObjectMapper = schemaObjectMapper;
        this.schemaValidator = schemaValidator;
        this.categoryUris =  this.rdfDao.findCategoryUri();
    }

    /**
     *
     * Check if there is JSON files in the folder validate the JSON file names
     *
     * @param folderPath the folder containing the JSON file
     * @throws InvalidJsonFileNameException if the name of a JSON file is wrong
     * @throws JsonFileNotFoundException if the folder does not exist
     */
    public void validateJsonFileNames(String folderPath) throws ImportException {

        /**
         * folder = path to the folder where the files to be validated before to be imported are located
         * /data/producerId/tmp/{date}/unzip
         */
        String producerId = FileUtils.getProducerId(new File(folderPath)); //new File(new File(new File(new File(folderPath).getParent()).getParent()).getParent()).getName();

        List<File> jsonFiles = FileUtils.listFiles(folderPath, "json");
        /**
         * Check for json file presence
         */
        if (jsonFiles.isEmpty()) {
            throw new JsonFileNotFoundException("No Json in folder "+folderPath);
        }

        /**
         * check the json file name
         */
        String regexJsonFileName = producerId + "_(en|fr|es)\\.json";
        Pattern jsonFileNamePattern = Pattern.compile(regexJsonFileName);
        for (File jsonFile : jsonFiles) {
            Matcher m = jsonFileNamePattern.matcher(jsonFile.getName());
            if (!m.find()) {
                throw new InvalidJsonFileNameException(jsonFile.getName(), jsonFileNamePattern);
            }
        }
    }

    /**
     * Validate the fields of the JSON file using a JSON schema
     *
     * @param jsonPath aboslute path of the file to validate
     * @throws ImportFileException if tthe JSON file cant be read
     * @throws InvalidJsonException if the format of the JSON file is not in accordance with the JSON schema
     */
    public Pivot validateJsonSchema(String jsonPath) throws ImportException {
        JsonNode producerFileNode;
        Set<ValidationMessage> errors;
        Pivot producerPivot;
        try {
            producerFileNode = schemaObjectMapper.readTree(Path.of(jsonPath).toFile());

            // we skip the remove ampty/null thing, it mess up with the schema.
            // as "", null, and <absent> are treated the same, it may not influence schema validation
            // see issue #4 in doc-producer repo.
            errors = this.schemaValidator.validateNode( /* JsonUtils.removeEmptyStringAndNullField */(producerFileNode ) );

            // read the node to return a Pivot
            producerPivot = this.schemaObjectMapper.treeToValue(producerFileNode, Pivot.class);
        } catch ( JsonProcessingException jacksonEx) {
            logger.error(USER, "Can't read the JSON file, please check JSON syntax near {} : {}", jacksonEx.getLocation() , jacksonEx.getMessage());
            throw new InvalidJsonException(jacksonEx);
        } catch (IOException ioException) {
            throw new ImportFileException(ioException.getMessage());
        }

        if( ! errors.isEmpty() ) {
            ValidationMessage firstError = errors.iterator().next();
            logger.error(USER, "The file {} is not compliant with our schema: {}",  Paths.get(jsonPath).getFileName() , firstError.getMessage());
            throw new InvalidJsonException(firstError.getMessage());
        }
        logger.debug(USER,"The file {} is valid",  Paths.get(jsonPath).getFileName() );

        return producerPivot;
    }

    /**
     * Check that the ids of the json file correspond to the parent folder name as specified in the documentation
     *
     * @param pivot  representation of the json file to be validated
     * @throws InvalidIdsException
     */
    public void validateJsonFileIds(@NonNull Pivot pivot) throws InvalidIdsException {

        String producerId = pivot.getProducer().getProducerId().name();
        String regexdatasetId = producerId + "_DAT_(.*)";
        String regexObservationId = producerId + "_OBS_(.*)";

        for(Dataset dataset: pivot.getDatasets()) {

            if (!dataset.getDatasetId().matches(regexdatasetId)) {
                throw new InvalidIdsException(dataset.getDatasetId(), regexdatasetId);
            }

            for(Observation obs: dataset.getObservations()) {
                if (!obs.getObservationId().matches(regexObservationId)) {
                    throw new InvalidIdsException(obs.getObservationId(),regexObservationId);
                }
            }
        }
    }

    /**
     * Check that key/value pair between internationalised json files are coherent
     *
     * @param jsonFiles Internationalised josn files
     * @throws IOException
     * @throws InvalidJsonInternationalisationException
     */
    public void validateJsonInternationalisation(List<File> jsonFiles) throws IOException, InvalidJsonInternationalisationException {
        /**
         * Load the json config file
         */
        logger.info("====================== {} ========================", this.jsonSchemaUrl);

        URL url = new URL(this.jsonSchemaUrl + "configInternationalisation.json");
//         todo, use a higher level web client, this is not the job of a validation service
//        HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
//        con.setRequestMethod("GET");
//
//        StringBuilder content = new StringBuilder();
//        try (BufferedReader in = new BufferedReader(
//                new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8 ))) {
//            String inputLine;
//            while ((inputLine = in.readLine()) != null) {
//                content.append(inputLine);
//            }
//        }
        ObjectNode configJson = (ObjectNode) schemaObjectMapper.readTree(url);
        logger.debug("ConfigJson: {}",configJson);

        List<JsonNode> jsonObjectList = new ArrayList<>();
        for (File j : jsonFiles) {
            jsonObjectList.add(schemaObjectMapper.readTree(
                    readFile(j.getAbsolutePath(), StandardCharsets.UTF_8)));
        }
        /**
         * Remove all key/value pair that is subject to internationalisation
         */
        for (JsonNode j : jsonObjectList) {
            removeMultiLangKey(j, configJson);
        }

        /**
         * We check if the json file are equals after the removal of the fields subject to internationalisation
         */
        for (int i = 1; i < jsonObjectList.size(); i++) {
            Object diff = jsonsEqual(jsonObjectList.get(i - 1), jsonObjectList.get(i));
            if (diff != null && !diff.toString().equalsIgnoreCase("{}")) {
                if (diff instanceof ObjectNode) {
                    throw new InvalidJsonInternationalisationException(diff.toString(),
                            jsonFiles.get(i - 1).getName(), jsonFiles.get(i).getName());
                } else {
                    throw new InvalidJsonInternationalisationException(diff.toString(), jsonFiles.get(i - 1).getName(), jsonFiles.get(i).getName());
                }
            }
        }
    }

    /**
     * Recursive method that remove all key/value pair that is subject to internationalisation
     *
     * @param jsonToValidate
     * @param configJson
     * @return JsonElement without internationalised key/value pair
     */
    private JsonNode removeMultiLangKey(JsonNode jsonToValidate, ObjectNode configJson) {

        String type = null;

        type = configJson.get("type").asText();

        switch (type) {
            case "object": {

                ObjectNode properties = (ObjectNode) configJson.get("properties");
                for (Iterator<String> it = properties.fieldNames(); it.hasNext(); ) {
                    String key = it.next();
                    if (jsonToValidate.has(key)) {
                        JsonNode tmpObject = jsonToValidate.get(key);
                        tmpObject = removeMultiLangKey(jsonToValidate.get(key), (ObjectNode) configJson.get("properties").get(key));
                        ObjectNode jsonToValidateTmp = (ObjectNode) jsonToValidate;
                        jsonToValidateTmp.remove(key);
                        jsonToValidate = jsonToValidateTmp.set(key, tmpObject);
                    }
                }
                break;
            }
            case "array": {
                for (int i = 0; i < jsonToValidate.size(); i++) {
                    JsonNode tmpArray = jsonToValidate.get(i);
                    tmpArray = removeMultiLangKey(jsonToValidate.get(i),
                            (ObjectNode) configJson.get("items"));
                    ((ArrayNode)jsonToValidate).set(i, tmpArray);
                }
                break;
            }
            case "string":
                jsonToValidate = null;
        }
        return jsonToValidate;
    }

    public boolean isValidCategory(String uri) {
        return this.categoryUris.contains(uri);
    }

    @Override
    public boolean isATheiaVariable(String uri) {
        return this.rdfDao.isAVariableURI(uri);
    }

    @Override
    public List<String> getBroaderCategoriesOfVariable(String uri) {
        return this.rdfDao.findFirstBroaderCategories(uri);
    }

    @Override
    public String getPrefLabel(String uri) {
        return this.rdfDao.getEnPrefLabel(uri);
    }

    @Override
    public boolean isADeprecatedCategory(String uri) {
        return this.rdfDao.isDeprecatedCategory(uri);
    }

    @Override
    public String getIsReplacedByUri(String uri) {
        return this.rdfDao.getIsReplacedByUri(uri);
    }

    /**
     * Check for the differences between two JSON object and return a pattern descibing the difference
     *
     * @param obj1 a JSONObject
     * @param obj2 a JSONObject
     * @return a Object that can be an instance of String of JSONObject describing the difference between the two
     * objects an empty JSON object state that there is not differences between the two objects
     */
    private Object jsonsEqual(JsonNode obj1, JsonNode obj2) {

//        Gson gson = new GsonBuilder().setPrettyPrinting().create();
//        JsonObject diff = new JsonObject();
        ObjectNode diff = schemaObjectMapper.createObjectNode();
        /**
         * Check if the the two object are instance of the same class Return a String is the two object are not of the
         * same class
         */
        if (!obj1.getClass().equals(obj2.getClass())) {
            return "The two object does not have the same class";
        }

        /**
         * Check if the objects are instance of JSONObject return a JSONObject if the two JSONObject differ
         */
        if (obj1.isObject() && obj2.isObject()) {

            ObjectNode jsonObj1 = (ObjectNode)obj1;
            ObjectNode jsonObj2 = (ObjectNode)obj2;
            List<String> names = null;
            List<String> names2 = null;

            /**
             * if one object is equal to an empty JSON object the fields are not stored into the a variable
             */
            if (!jsonObj1.toString().equalsIgnoreCase("{}")) {
                //names = new ArrayList<String>((Collection<? extends String>) jsonObj1.fieldNames());
                names = IteratorUtils.toList(jsonObj1.fieldNames());
            }
            if (!jsonObj2.toString().equalsIgnoreCase("{}")) {
                names2 = IteratorUtils.toList(jsonObj2.fieldNames());
            }

            /**
             * Return an empty JSON object if both JSONObject are emtpy JSON object
             */
            if (names == null && names2 == null) {

                return schemaObjectMapper.createObjectNode();
                /**
                 * Check if only on of the JSON object is an empty object
                 */
            } else if (names == null || names2 == null) {
                return "One of the two object is an empty JSON object";
                /**
                 * check for the difference between the JSON object
                 */
            } else {
                /**
                 * Check that the name of the fields are similar for both of the JSONObject
                 */
                if (!(names.containsAll(names2) && names2.containsAll(names))) {
                    List<String> names3 = IteratorUtils.toList(jsonObj2.fieldNames());
                    names2.removeAll(names);
                    if (!names2.isEmpty()) {
                        for (String fieldName : names2) {
                            if (jsonObj1.has(fieldName) || jsonObj2.has(fieldName)) {
                                diff.put(fieldName, "field name not present in the first file");
                            }
                        }
                    }
                    names.removeAll(names3);
                    if (!names.isEmpty()) {
                        for (String fieldName : names) {
                            if (jsonObj1.has(fieldName) || jsonObj2.has(fieldName)) {
                                diff.put(fieldName, "field name not present in the second file");
                            }
                        }
                    }
                    names = IteratorUtils.toList(jsonObj1.fieldNames());
                    names2 = IteratorUtils.toList(jsonObj2.fieldNames());

                }
                /**
                 * Check that the value of the JSON object fields' are similar
                 */
                if (names.containsAll(names2) && names.size() == names2.size()) {
                    /**
                     * Recurcively call the function of the each field of the JSONObject
                     */
                    for (String fieldName : names) {
                        JsonNode obj1FieldValue = jsonObj1.get(fieldName);
                        JsonNode obj2FieldValue = jsonObj2.get(fieldName);
                        Object obj = jsonsEqual(obj1FieldValue, obj2FieldValue);
                        if (obj != null && !checkObjectIsEmpty(obj)) {
                            if (obj instanceof String) {
                                diff.put(fieldName, obj.toString());
                            } else if (obj instanceof JsonNode) {
                                diff.set(fieldName, (JsonNode) obj);
                            }
                        }
                    }
                } else {
                    for (String fieldName : names) {
                        if (names2.contains(fieldName) && names.contains(fieldName)) {
                            JsonNode obj1FieldValue = jsonObj1.get(fieldName);
                            JsonNode obj2FieldValue = jsonObj2.get(fieldName);
                            Object obj = jsonsEqual(obj1FieldValue, obj2FieldValue);
                            if (obj != null && !checkObjectIsEmpty(obj)) {
                                if (obj instanceof String) {
                                    diff.put(fieldName, obj.toString());
                                } else if (obj instanceof JsonNode) {
                                    diff.set(fieldName, (JsonNode) obj);
                                }
                            }
                        }
                    }
                }
                return diff;
            }
            /**
             * Check if hte objects are instance of JSONArray return a JSONObject if the two JSONArray differ
             */
        } else if (obj1.isArray() && obj2.isArray()) {

            ArrayNode obj1Array = (ArrayNode) obj1;
            ArrayNode obj2Array = (ArrayNode) obj2;
            /**
             * if the string values of the array are not equal we are looking for the differences
             */
            if (!obj1Array.toString().equals(obj2Array.toString())) {
                ArrayNode diffArray = schemaObjectMapper.createArrayNode();
                /**
                 * Check if there is the same number of item in the two JSONArray
                 */
                if (obj1Array.size() != obj2Array.size()) {
                    diffArray.add("The lengths of the arrays are not equals ");
                } else {
                    /**
                     * Recurcively call the function for each item of the JSONArray
                     */
                    Object obj = null;
                    for (int i = 0; i < obj1Array.size(); i++) {
                        obj = jsonsEqual(obj1Array.get(i), obj2Array.get(i));
                        ObjectNode tmp = schemaObjectMapper.createObjectNode();
                        if (obj instanceof ObjectNode && !obj.toString().equalsIgnoreCase("{}")) {
                            tmp.set(String.valueOf(i), (ObjectNode) obj);
                            diffArray.add(tmp);
                        } else if ((!obj.toString().equalsIgnoreCase("{}")) || obj instanceof String) {
                            tmp.put(String.valueOf(i), (String) obj);
                            diffArray.add(tmp);
                        }
                    }
                }
                /**
                 * return a JSONArray describing the difference between the two JSONArray
                 */
                if (diffArray.size() > 0) {
                    return diffArray;
                }
            }
        } else {
            /**
             * if the two object are neither JSONObject nor JSONArray object, the two object are instance on JSON
             * primitive types we check for direct equality between the two object
             */
            if (!obj1.equals(obj2)) {
                return "the two objects does not have the same value";
            }
        }
        /**
         * The two object are of privitive type and equal. An empty JSON object is returned.
         */
        return schemaObjectMapper.createObjectNode();
    }

    /**
     * Check if the object returned by the jsonsEqual method correponds to an emty object
     *
     * @param obj Object return by the jsonsEqual method
     * @return boolean true if the object correspond to an empty
     */
    private boolean checkObjectIsEmpty(Object obj) {
        if (obj == null) {
            return true;
        }
        String objData = obj.toString();
        if (objData.length() == 0) {
            return true;
        }
        if (objData.equalsIgnoreCase("{}")) {
            return true;
        }
        return false;
    }

}
