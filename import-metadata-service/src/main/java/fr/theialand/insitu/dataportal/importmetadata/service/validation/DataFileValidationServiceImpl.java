package fr.theialand.insitu.dataportal.importmetadata.service.validation;

import fr.theialand.insitu.dataportal.importmetadata.Exceptions.ImportFileException;
import fr.theialand.insitu.dataportal.importmetadata.Exceptions.InvalidTextFileFormatException;
import fr.theialand.insitu.dataportal.importmetadata.Exceptions.InvalidZipException;
import fr.theialand.insitu.dataportal.model.pivot.Pivot;
import fr.theialand.insitu.dataportal.model.pivot.dataset.Dataset;
import fr.theialand.insitu.dataportal.model.pivot.observation.Observation;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.*;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

@Service
public class DataFileValidationServiceImpl implements DataFileValidationService{

    private static final Logger logger = LoggerFactory.getLogger(DataFileValidationServiceImpl.class);
    private static final Marker USER = MarkerFactory.getMarker("USER");

    /**
     * Validate the zip file names according to the dataset names found in a valid JSON file. The text file entries of
     * the ZIP files are then validated using DataFileValidation.validateTextFileFormat method
     *
     * this is the entrypoint for data validation
     *
     * @param pivot object
     * @param zipFiles the list of ZIP files (data files)
     * @return true if all the zip files and there entries are validated
     * @throws ImportFileException if unrecoverable error
     */
    public Map<String,Boolean> validateZipFiles(Pivot pivot, List<File> zipFiles) throws ImportFileException {

        Map<String, Boolean> obsLinkToData = new LinkedHashMap<>();

        /*
         * Verify that the zip file name is the same than in the JSON file for all dataset
         */
        logger.info("Verifing that the zip file name is the same as in the JSON file for each dataset");
        for (Dataset dataset : pivot.getDatasets()) {

            String datasetId = dataset.getDatasetId();
            List<Observation> obss = dataset.getObservations();

            Optional<File> currentZipOptional = zipFiles.stream()
                    .filter(zip -> (FilenameUtils.getBaseName(zip.getName()).equals(datasetId)))
                    .findAny();

            if (currentZipOptional.isPresent()) {
                File currentZip = currentZipOptional.get();
                try (ZipFile zipFile = new ZipFile(currentZip) ) {
                    /*
                     * Since the zip file name is valid we verify that the text files inside are also valid
                     */
                    Map<String, Boolean> dsObsLinkToData = validateObservationsFile(obss, zipFile);
                    obsLinkToData.putAll(dsObsLinkToData);
                } catch (ZipException ex) {
                    logger.warn(USER, "File {}.zip is not readable or is not a valid zip file, all its {} observations are considered invalid", datasetId, obss.size());
                    obss.forEach(item -> obsLinkToData.put(
                            item.getObservationId(),
                            Boolean.FALSE));
                } catch (IOException ioEx) {
                    throw new ImportFileException("IO error when reading "+currentZip.getName(), ioEx, currentZip);
                }
            } else {
                logger.warn(USER, "File {}.zip not found, all its {} observations are considered invalid", datasetId, obss.size());
                obss.forEach(item -> obsLinkToData.put(
                        item.getObservationId(),
                        Boolean.FALSE));
            }
        }
        return obsLinkToData;
    }

    /**
     * Validate the format of text file zip entries using REGEX
     *
     * package visibility to allow it to be tested (may be private otherwise)
     * @param zipFile The Zip file containing the text file
     * @param txtFile the text file containing the data
     * @throws InvalidTextFileFormatException if the validation failed
     * @throws InvalidZipException {@link IOException} if worse
     */
    public void validateTextFileFormat(ZipFile zipFile, ZipEntry txtFile) throws InvalidTextFileFormatException,  ImportFileException {

        String[] regexFirstLines = {
                "#Date_of_extraction;[0-9]{4}-(1|0)[0-9]{1}-[0-3]{1}[0-9]{1}T[0-9]{2}:[0-9]{2}:[0-9]{2}Z;$",
                "^#Observation_ID;([^;]+;)$",
                "^#Dataset_title;([^;]+;)$",
                "^#Variable_name;([^;]+;)$",
                "date_begin;date_end;latitude;longitude;altitude;value;qualityFlags;((.*)?;)*"
        };

        String regexDataLine = "([0-9]{4}-(((0[13578]|(10|12))-(0[1-9]|[1-2][0-9]|3[0-1]))|(02-(0[1-9]|[1-2][0-9]))|((0[469]|11)-(0[1-9]|[1-2][0-9]|30)))T(20|21|22|23|[01]\\d|\\d)((:[0-5]\\d){1,2})((:[0-5]\\d){1,2})Z)?;"
                + "[0-9]{4}-(((0[13578]|(10|12))-(0[1-9]|[1-2][0-9]|3[0-1]))|(02-(0[1-9]|[1-2][0-9]))|((0[469]|11)-(0[1-9]|[1-2][0-9]|30)))T(20|21|22|23|[01]\\d|\\d)((:[0-5]\\d){1,2})((:[0-5]\\d){1,2})Z;"
                + "[-]?(([1-8]?[0-9])(\\.[0-9]{1,6})?|90(\\.[0-9]{1,6})?);"
                + "[-]?((([1-9]?[0-9]|1[0-7][0-9])(\\.[0-9]{1,6})?)|180(\\.[0-9]{1,6})?);"
                + "([-]?[0-9]{1,4}(\\.[0-9]{1,6})?)?;"
                + "((.*)?;){2,}";

        logger.info("analyzing data file {} in zip {}", txtFile.getName(), zipFile.getName());
        try ( LineNumberReader lineReader = new LineNumberReader(new InputStreamReader( zipFile.getInputStream(txtFile) ) ) ) {
            String line;

            // HEADER checking , 5 lines
            for (int i = 0; i < 5; i++) {
                line = lineReader.readLine();
                Pattern firstLinesPattern = Pattern.compile(regexFirstLines[i]);
                if (!firstLinesPattern.matcher(line).find())
                    throw new InvalidTextFileFormatException(line, i, txtFile, zipFile);
            }

            // DATA checking, the remaining lines
            Pattern dataLinesPattern = Pattern.compile(regexDataLine);
            while ((line = lineReader.readLine()) != null) {
                if (!dataLinesPattern.matcher(line).find())
                    throw new InvalidTextFileFormatException(line, lineReader.getLineNumber(), txtFile, zipFile);
            }
            logger.info("{} in ZIP {} is valid.", txtFile.getName(), zipFile.getName());
        } catch ( ZipException zipEx ) {
            throw new InvalidZipException(zipFile.getName()+ "is not readable or is not a valid zip file", zipEx);
        } catch ( IOException ioEx ) {
            throw new ImportFileException("IO error when reading "+zipFile.getName(), ioEx );
        }
    }

    /**
     * Validate Observations against their zip file
     * it is a sort of wrapper around the validateTextFileFormat
     * log warning for each obs in case of errors,
     * or throw Exception if worse
     * @param observations array of json obs
     * @param zipFile containing the obs data
     * @return map of observations, and whether they have validated or not
     * @throws ImportFileException if unrecovverable error
     */
    private Map<String, Boolean> validateObservationsFile(List<Observation> observations, ZipFile zipFile) throws  ImportFileException {

        Map<String, Boolean> obsLinkToData = new HashMap<>() ;
        for (Observation obs :observations) {

            String observationId   = obs.getObservationId();
            String datafileName    = obs.getResult().getDataFile().getName();

            /*
             * Verfify that the observationId corresponds to a textFile in the zip of the dataset
             */
            ZipEntry txtFile = zipFile.getEntry(observationId + ".txt");

            if (txtFile == null) {
                logger.warn(USER, "The file {}.txt was not found in the zip {}", observationId, zipFile.getName());
                obsLinkToData.put(observationId, Boolean.FALSE);
                continue;
            }
            if ( ! txtFile.getName().equals(datafileName) ) {
                logger.warn(USER, "The file {} does not corresponds to Json Datafile name {}", txtFile.getName(), datafileName);
                obsLinkToData.put(observationId, Boolean.FALSE);
                continue;
            }

            /*
             * Since the text file name is valid we verify the format of the text file
             */
            try {
                validateTextFileFormat(zipFile, txtFile);
                // not rising Exception means isValid here
                obsLinkToData.put(observationId, Boolean.TRUE);
            } catch (InvalidTextFileFormatException ex2) {
                logger.warn(USER, "The line {} of file {} in zip {} does not match the expected syntax : {}",
                        ex2.getLineNumber(), txtFile.getName(), zipFile.getName(), ex2.getLine());
                obsLinkToData.put(observationId, Boolean.FALSE);
            }
        }

        return obsLinkToData;
    }
}
