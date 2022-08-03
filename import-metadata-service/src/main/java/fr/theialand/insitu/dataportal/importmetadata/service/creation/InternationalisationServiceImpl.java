/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.theialand.insitu.dataportal.importmetadata.service.creation;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fr.theialand.insitu.dataportal.importmetadata.Exceptions.ImportException;
import fr.theialand.insitu.dataportal.importmetadata.service.FileUtils;
import fr.theialand.insitu.dataportal.importmetadata.service.ImportConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 *
 * @author coussotc
 */
@Service
public class InternationalisationServiceImpl implements InternationalisationService {

    @Value("${json.schema.url}")
    private String jsonSchemaUrl;

    private final EnrichmentService enrichmentService;

    private static final Logger LOG = LoggerFactory.getLogger(InternationalisationServiceImpl.class);

    private final ObjectMapper objectMapper;

    @Autowired
    public InternationalisationServiceImpl(EnrichmentService enrichmentService,
                                           @Qualifier("jsonSchemaObjectMapper") // it doesnt matter really. here we only use it for *Nodes.
                                           ObjectMapper objectMapper) {
        this.enrichmentService = enrichmentService;
        this.objectMapper = objectMapper;
    }

    /**
     * recursive call browsing the json in order to update fields that are subject to internationalisation pattern
     *
     * @param jsonToUpdate the json document to update
     * @param jsonInAnotherlang the json used to update the json to update (i.e. json whith fields in another language)
     * @param configJson the json fr.theialand.insitu.dataportal.backendassociation.configuration file that describing the fields subject to internationalisation pattern
     * @param fieldName the name of the field to update
     * @param language the langaguge of the fields in ISO
     * @param importConfig Import config object
     * @return a JsonElement instance of JsonObject witht the multi language fields upadated
     */
    private JsonNode updateMultiLangFields(JsonNode jsonToUpdate, JsonNode jsonInAnotherlang,
            ObjectNode configJson, String fieldName, String language, ImportConfig importConfig) {

        String type = configJson.get("type").asText();

        //LOG.debug("Analyzing {} , fieldname {}", type,fieldName);

        switch (type) {
            case "object": {

                ObjectNode properties = (ObjectNode) configJson.get("properties");
                for (Iterator<String> it = properties.fieldNames(); it.hasNext(); ) {
                    String key = it.next();
                    if (jsonInAnotherlang.has(key)) {
                        JsonNode tmpObject = jsonToUpdate.get(key);
                        tmpObject = updateMultiLangFields(jsonToUpdate.get(key),
                                jsonInAnotherlang.get(key),
                                (ObjectNode) configJson.get("properties").get(key),
                                key, language, importConfig);
                        ObjectNode jsonToUpdateTmp = (ObjectNode) jsonToUpdate;
                        jsonToUpdateTmp.remove(key);
                        jsonToUpdate = jsonToUpdateTmp.set(key, tmpObject);
                    }
                }
                break;
            }

            case "array": {
                LOG.debug("JsonOtherLang {} , type {}", jsonInAnotherlang, jsonInAnotherlang.getClass().getSimpleName());
                for (int i = 0; i < jsonInAnotherlang.size(); i++) {
                    JsonNode tmpArray = jsonToUpdate.get(i);
                    tmpArray = updateMultiLangFields(jsonToUpdate.get(i),
                            jsonInAnotherlang.get(i),
                            (ObjectNode) configJson.get("items"), null, language, importConfig);
                    ((ArrayNode) jsonToUpdate).set(i,tmpArray);
                }
                break;
            }
            case "string": {
                jsonToUpdate = this.updateOneMultiLangField(jsonToUpdate, jsonInAnotherlang.asText(),
                        language, importConfig);
            }

        }
        return jsonToUpdate;
    }

    private ArrayNode updateOneMultiLangField(JsonNode fieldToUpdate, String valueToBeAdded, String lang,
            ImportConfig importConfig) {

        /**
         * The JsonObject to be added for internationalisation
         */
        ObjectNode objectToBeAdded = JsonNodeFactory.instance.objectNode();
        objectToBeAdded.put("lang", lang);
        objectToBeAdded.put("text", valueToBeAdded);

        /**
         * If the field to update is already an JsonArray, it has already been updated with a lang fields
         */
        if (fieldToUpdate.isArray()) {
            ArrayNode fieldToUpdateArray = (ArrayNode) fieldToUpdate;

            /**
             * if the field for the language already exist in the array (should not occur) nothing happens
             */
            try {
                fieldToUpdateArray.forEach(item -> {
                    ObjectNode multiLangObject = (ObjectNode)item;
                    if (multiLangObject.get("lang").asText().equals(lang)) {
                        throw new IllegalStateException();
                    }
                });
            } catch (IllegalStateException ex) {
                return fieldToUpdateArray;
            }
            /**
             * One field for another language already exist, the object is updated to add a new fields Fisrt the objects
             * already present are checked to be coherent with ImportConfig object
             */
            fieldToUpdateArray.forEach(item -> {
                ObjectNode multiLangObject = (ObjectNode)item;
                if (!Arrays.asList(importConfig.getLanguages()).stream().anyMatch(t -> {
                    return multiLangObject.get("lang").asText().equals(t);
                })) {
                    throw new IllegalStateException("Wrong language object found in a internaionalised array according to "
                            + "the ImportConfig object");
                }
            });
            /**
             * Adding the jsonObject to be added to the Array of internationalised element
             */
            fieldToUpdateArray.add(objectToBeAdded);
            return fieldToUpdateArray;

            /**
             * if the filed to update is a String. The Json Array needs to be created
             */
        } else if (fieldToUpdate.isTextual()) {
            ArrayNode fieldToUpdateArray = JsonNodeFactory.instance.arrayNode();
            fieldToUpdateArray.add(objectToBeAdded);
            return fieldToUpdateArray;
        } else {
            throw new IllegalStateException("The field to update for internationalisation is neither an JsonArray nor a String object.");
        }
    }

    /**
     *
     * @param folderPath String - absolute path of the folder containing the json files in different language
     * @param importConfig ImportConfig - ImportConfig object describing the method of import
     * @return JsonObject - with the internationlaisationpattern of all the file in different language present in the
     * folder
     * @throws IOException - if the url of the json fr.theialand.insitu.dataportal.backendassociation.configuration file is not found
     * @throws ImportException
     */
    public JsonNode setInternationalisation(String folderPath, ImportConfig importConfig) throws IOException, ImportException {
        /**
         * list the previously validated json files of the folder.
         */
        List<File> jsonFiles = FileUtils.listFiles(folderPath,"json");

        /**
         * Create a new jsonObject for multiLang pattern
         */
        //JsonElement internationalisedJson = new JsonObject();
        JsonNode internationalisedJson = null;
        /**
         * Load the json config file
         */
        //Gson gson = new GsonBuilder().setPrettyPrinting().create();
//        InputStream in = new URL(jsonSchemaUrl + "configInternationalisation.json").openStream();
//        byte[] encoded = IOUtils.toByteArray(in);
        //JsonObject configJson = gson.fromJson(new String(encoded, StandardCharsets.UTF_8), JsonElement.class).getAsJsonObject();
        JsonNode configJson = objectMapper.readTree(new URL(jsonSchemaUrl + "configInternationalisation.json"));

        for (int i = 0; i < importConfig.getLanguages().length; i++) {

            /**
             * Read one json file sent by the producer to create a JsonObject
             */
            String lang = importConfig.getLanguages()[i];
            /**
             * folder = path to the folder where the files to be validated before to be imported are located
             * /data/producerId/tmp/{date}/unzip
             */
            String producerId = new File(new File(new File(new File(folderPath).getParent()).getParent()).getParent()).getName();
            String regex = producerId + "_" + lang + "\\.json";
            List<File> jsonListTmp = jsonFiles.stream().filter((File p) -> {
                Pattern jsonFileNamePattern = Pattern.compile(regex);
                Matcher m = jsonFileNamePattern.matcher(p.getName());
                return m.find();
            }).collect(Collectors.toList());
            if (jsonListTmp.size() != 1) {
                throw new IllegalStateException();
            }
            //JsonObject json = FileUtils.readJsonFile(jsonListTmp.get(0));
            ObjectNode json = (ObjectNode)objectMapper.readTree(jsonListTmp.get(0));

            /**
             * Prefix producer name using OZCAR-RI
             */
            String[] producerIds = {"AGRH", "AURA", "BVET", "CATC", "CRYO", "DRAI", "ERUN", "HPLU", "HYBA", "KARS", "MSEC", "OBSE", "OHGE", "OHMC", "OMER", "OPEA", "ORAC", "OSRC", "REAL", "TOUR", "YZER","MTRO"};
            //if (Arrays.stream(producerIds).anyMatch(json.getAsJsonObject("producer").get("producerId").getAsString()::equals)) {
            if (Arrays.stream(producerIds).anyMatch(json.get("producer").get("producerId").asText()::equals)) {
                LOG.info("Prefixing producer name {} with OZCAR-IR", producerId);
                ((ObjectNode)json.get("producer")).put("name", "OZCAR-RI " + json.get("producer").get("name").asText());
            }
            /**
             * Populate fundings using scanR API
             */
            LOG.info("Populating producer fundings with scanR API");
            ArrayNode fundings =  (ArrayNode) this.enrichmentService.setFundingsUsingScanR((ArrayNode)json.get("producer").get("fundings"));
            ObjectNode producer =(ObjectNode) json.get("producer");
            producer.remove("fundings");
            producer.set("fundings", fundings);

            /**
             * Populate contact.organisation using scanR API
             */
            LOG.info("Populating producer contact organisations with scanR API");
            producer.set("contacts",  this.enrichmentService.setContactsUsingScanR((ArrayNode)producer.get("contacts")));

            /**
             * Update producer metdata with newly enriched metadata
             */
            json.set("producer", producer);

            /**
             * Populate dataset contacts using scanR API
             */
            LOG.info("Populating producer dataset contact organisations with scanR API");
            ArrayNode datasets = (ArrayNode)json.get("datasets");
            for (int j = 0; j < datasets.size(); j++) {
                ObjectNode datasetModif = (ObjectNode) datasets.get(j);
                ObjectNode metadata = (ObjectNode)datasets.get(j).get("metadata");
                metadata.set("contacts", this.enrichmentService.setContactsUsingScanR((ArrayNode) datasets.get(j).get("metadata").get("contacts")));
                datasetModif.set("metadata",metadata);
                datasets.set(j,datasetModif);

            }

            /**
             * Initialisation of the first internationalised observation document is created using the first json object
             */
            if (i == 0) {
                internationalisedJson = json.deepCopy();
            }
            LOG.info("Internationalising the json fields by updating the traductible fields");
            internationalisedJson = updateMultiLangFields(internationalisedJson, json, (ObjectNode)configJson, null, lang,
                    importConfig);

        }

        return internationalisedJson;
    }

}
