/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.theialand.insitu.dataportal.importmetadata.service;

import fr.theialand.insitu.dataportal.importmetadata.Exceptions.ImportFileException;
import org.apache.commons.io.FilenameUtils;
import org.springframework.lang.NonNull;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author coussotc
 */
public class FileUtils {

    /**
     * masking constrictor, this is an Util class, no to be ibstantiated
     */
    private FileUtils() {}

    /**
     * list all files of a given extension of a folder
     *
     * @param folder path of the folder containing the json files
     * @param extension the extension of the files to look for
     * @return the list of the zip files of the folder
     */
    public static ArrayList<File> listFiles(String folder, String extension) {
        File[] files = new File(folder).listFiles();
        ArrayList<File> textFiles = new ArrayList<>();
        for (File f : files) {
            if (FilenameUtils.getExtension(f.getName()).equals(extension)) {
                textFiles.add(f);
            }
        }
        return textFiles;
    }

    /**
     * list all files of a given extension of a folder
     * nio.Path is the new IO API, replacing io.File. but we stick to File as it's support is more extensive
     * @param folder Path of the folder to search
     * @param extension the extension of the files to look for (make it @{@link org.springframework.lang.Nullable} someday ?)
     * @return the list of Files found
     */
    public static List<File> listFiles(@NonNull File folder, @NonNull String extension) {
        return Arrays.asList(
                Optional.ofNullable(folder.listFiles((d, name) -> name.endsWith("."+extension)))
                .orElse(new File[0]));
    }


    /**
     * Read a json File and return the parsed json object
     *
     * @param file File a json file
     * @return JsonObject a json object
     */
//    public static JsonObject readJsonFile(File file) throws IOException {
//        JsonObject json = new JsonObject();
//        String jsonString = FileUtils.readFile(file.getPath(), StandardCharsets.UTF_8);
//        Gson gson = new Gson();
//        json = gson.fromJson(jsonString, JsonObject.class);
//        return json;
//    }

    public static String readFile(String path, Charset encoding)
            throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }


    public static String getProducerId( @NonNull File producerDropDir ) throws ImportFileException {
        if( ! producerDropDir.exists() )
            throw new ImportFileException("Directory "+producerDropDir.getAbsolutePath()+" does not exists");
        return producerDropDir.getParentFile().getParentFile().getParentFile().getName();
    }










}
