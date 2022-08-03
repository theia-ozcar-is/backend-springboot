package fr.theialand.insitu.dataportal.importmetadata.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fr.theialand.insitu.dataportal.importmetadata.Exceptions.ImportFileException;
import fr.theialand.insitu.dataportal.importmetadata.Exceptions.InvalidJsonException;
import org.apache.commons.io.FilenameUtils;
import org.springframework.lang.NonNull;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import static fr.theialand.insitu.dataportal.importmetadata.service.FileUtils.listFiles;

public class JsonUtils {

    private static final ObjectMapper om = new ObjectMapper();

    /** masking constructior, this is not to be instanciated */
    private JsonUtils() {}

    public static String getLangFromJsonFile(File jsonFile) {
        String regexLang = "(?:[A-Z]{4}_)(en|fr|es)(?:\\.json)";
        String lang = null;
        Pattern languagesPattern = Pattern.compile(regexLang);
        Matcher m = languagesPattern.matcher(jsonFile.getName());
        if (m.find()) {
            lang = m.group(1);
        }
        return lang;
    }

    public static JsonNode getNodeFromFile(@NonNull File file ) throws InvalidJsonException, ImportFileException {
        try {
            return om.readTree(file);
        } catch (JsonProcessingException jackEx) {
            throw new InvalidJsonException(jackEx);
        } catch (IOException ioException) {
            throw new ImportFileException("can't read file", ioException, file);
        }
    }

    /**
     * List all the file contained a valid json file and compare them to the file existing in a given folder. The method
     * produces a json file in which file and zip entries are associated to boolean value. True if they are realy
     * present in the json file, false if not
     *
     * @param folderPath the folder where the link between files and json are checked
     * @return String that can be parsed into JSON
     * @throws ImportFileException
     */
    public static String listLinkedFiles(File folderPath) throws ImportFileException, InvalidJsonException {

        /**
         * The Json Array to store information from
         */
        ArrayNode files_linked = JsonNodeFactory.instance.arrayNode();

        /**
         * Folder containing the json files and the zip files
         */
        List<File> folderFiles = Arrays.asList(folderPath.listFiles());

        /**
         * Create a List of zip files and datafile linked to the json files in order to compare to what is really
         * present in the folder
         */
        Map<String, List<String>> fileFromJson = new HashMap<>();
        //Gson builder to build JSON from String object
        List<File> jsonFiles = listFiles(folderPath, "json");
        for (File jsonFile : jsonFiles) {
            // using our static method so it remaps exceptions
            JsonNode json = getNodeFromFile(jsonFile);
            Iterator<JsonNode> dsIterator = json.withArray("datasets").elements();
            while ( dsIterator.hasNext() ) {
                JsonNode dataset = dsIterator.next();
                String zipFileName = folderPath.getAbsolutePath() + "\\" + dataset.get("datasetId").asText() + ".zip";
                if (folderFiles.stream().anyMatch(file -> file.getAbsolutePath().equals(zipFileName))) {
                    List<String> dataFiles = new ArrayList<>();
                    Iterator<JsonNode> obsIterator = json.withArray("observations").elements();
                    while (obsIterator.hasNext()) {
                        dataFiles.add(obsIterator.next().get("result").get("dataFile").get("name").asText());
                    }
                    fileFromJson.put(zipFileName, dataFiles);
                }
            }
        }
//            }
//            for (JsonElement el : datasets) {
//                JsonObject dataset = (JsonObject) el;
//                String zipFileName = folderPath.getAbsolutePath() + "\\" + dataset.get("datasetId").getAsString() + ".zip";
//                if (folderFiles.stream().filter(t -> t.getAbsolutePath().equals(zipFileName)).findFirst().orElse(null) != null) {
//                    List<String> dataFiles = new ArrayList<>();
//                    dataset.getAsJsonArray("observations").forEach((t) -> {
//                        JsonObject observation = (JsonObject) t;
//                        dataFiles.add(observation.getAsJsonObject("result").getAsJsonObject("dataFile").get("name").getAsString());
//                    });
//                    fileFromJson.put(zipFileName, dataFiles);
//                }
//            }

        /**
         * List the file that are present in the folder and compare them to fileFromJson Map object.
         */
        for (File file : folderFiles) {
            //Exclude the json file
            if (!"json".equals(FilenameUtils.getExtension(file.getAbsolutePath()))) {
                //check the zip files
                if ("zip".equals(FilenameUtils.getExtension(file.getAbsolutePath()))) {
                    //check if the zip file frrom the folder was found in the json
                    if (fileFromJson.containsKey(file.getAbsolutePath())) {
                        //if the file is found we check that the entry of the file are found in the json
                        //zip entries are checked and stored in a JsonArray object
                        ArrayNode jsonZipEntries = JsonNodeFactory.instance.arrayNode();
                        List<String> jsonDataFiles = fileFromJson.get(file.getAbsolutePath());
                        try (ZipFile zip = new ZipFile(file.getAbsolutePath());) {
                            //List all the entries of the zip file
                            Enumeration<? extends ZipEntry> entries = zip.entries();
                            while (entries.hasMoreElements()) {
                                ZipEntry zipEntry = entries.nextElement();
                                if (jsonDataFiles.stream().anyMatch(t -> t.equals(zipEntry.getName() ) ) ) {
                                    //If the entry file of the zip file is found in the json we add the entry file name (key)
                                    // as "true" (value) in the the file_linked.json return file
                                    ObjectNode tmp = JsonNodeFactory.instance.objectNode();
                                    tmp.put(zipEntry.getName(), Boolean.TRUE); // why not "true" ? do we need this bool to be an object ? => can it have null value ?
                                    jsonZipEntries.add(tmp);
                                } else {
                                    //If the entry file of the zip file is not found in the json we add the entry file name (key)
                                    // as "false" (value) in the the file_linked.json return file
                                    ObjectNode tmp = JsonNodeFactory.instance.objectNode();
                                    tmp.put(zipEntry.getName(), Boolean.FALSE); // why not "false" ? do we need this bool to be an object ? => can it have null value ?
                                    jsonZipEntries.add(tmp);
                                }
                            }
                            //Store the jsonZipEntries array in a json object named according the zip file name
                            ObjectNode tmp2 = JsonNodeFactory.instance.objectNode();
                            tmp2.putArray(file.getAbsolutePath()).addAll( jsonZipEntries);
                            files_linked.add(tmp2);
                        } catch (IOException ex) {
                            //if the zip file cannot be opened, the zip file name is set to false in the file_linked.json return file
                            ObjectNode tmp = JsonNodeFactory.instance.objectNode();
                            tmp.put(file.getAbsolutePath(), Boolean.FALSE);
                            files_linked.add(tmp);
                        }
                    } else {
                        //if the zip file name is not found in the json the zip file name is set to false in the file_linked.json return file
                        ObjectNode tmp = JsonNodeFactory.instance.objectNode();
                        tmp.put(file.getAbsolutePath(), Boolean.FALSE);
                        files_linked.add(tmp);
                    }
                } else {
                    //if the file name is not a zip file, the file name is set to false in the file_linked.json return file
                    ObjectNode tmp = JsonNodeFactory.instance.objectNode();
                    tmp.put(file.getAbsolutePath(), Boolean.FALSE);
                    files_linked.add(tmp);
                }
            }
        }
        return files_linked.toString();
    }

}
