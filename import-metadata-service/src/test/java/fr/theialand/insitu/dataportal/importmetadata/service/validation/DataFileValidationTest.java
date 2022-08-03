package fr.theialand.insitu.dataportal.importmetadata.service.validation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fr.theialand.insitu.dataportal.importmetadata.Exceptions.InvalidTextFileFormatException;
import fr.theialand.insitu.dataportal.importmetadata.service.FileUtils;
import fr.theialand.insitu.dataportal.importmetadata.service.validation.DataFileValidationServiceImpl;
import fr.theialand.insitu.dataportal.model.configuration.ObjectMapperConfiguration;
import fr.theialand.insitu.dataportal.model.pivot.Pivot;
import org.junit.*;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

import static org.junit.Assert.assertEquals;

/**
 *
 * @author coussotc
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { DataFileValidationServiceImpl.class, ObjectMapperConfiguration.class })
public class DataFileValidationTest {

    @Autowired
    DataFileValidationServiceImpl dataFileValidationService;
    @Autowired
    @Qualifier("jsonSchemaObjectMapper")
    ObjectMapper objectMapper;

    public DataFileValidationTest() {
    }

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @BeforeClass
    public static void setUpClass() {

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * -- Valid text dataFile Test of validateTextFileFormat method, of class DataFileValidation. For a Zip file
     * containing text datafile with a valid format
     */
    @Test
    public void testValidateTextFileFormat() throws Exception {
        System.out.println("validateTextFileFormat");

        File tmpCATCFolder = folder.newFolder("CATC");

        File tmpFile = new File(tmpCATCFolder, "validDataFiles.zip");
        InputStream in = DataFileValidationTest.class.getClassLoader().getResourceAsStream("datafile/CATC_DAT_CE.WChem_Od - noAdditionalValue.zip");
        inputStreamToFile(in, tmpFile);
        ZipFile zipFile = new ZipFile(tmpFile);
        ZipEntry txtFile = zipFile.getEntry("CATC_OBS_CE.WChem_Od_1.txt");
        InputStreamReader insr = new InputStreamReader(zipFile.getInputStream(txtFile), StandardCharsets.UTF_8);
        this.dataFileValidationService.validateTextFileFormat(zipFile, txtFile);
        tmpCATCFolder.delete();
    }

//    /**
//     * -- wrong text dataFile encoding Test of validateTextFileFormat method, of class DataFileValidation. For a Zip
//     * file containing text datafile with a wrong encoding. i.e. not utf8.
//     */
//    @Test(expected = InvalidEncodingException.class)
//    public void testValidateTextFileWrongEncoding() throws Exception {
//        System.out.println("validateTextFileFormat");
//
//        File tmpCATCFolder = folder.newFolder("CATC");
//        File tmpFile = new File(tmpCATCFolder, "validDataFiles.zip");
//        InputStream in = DataFileValidationTest.class.getClassLoader().getResourceAsStream("datafile/CATC_DAT_CE.WChem_Od.zip");
//        inputStreamToFile(in, tmpFile);
//        ZipFile zipFile = new ZipFile(tmpFile);
//        ZipEntry txtFile = zipFile.getEntry("CATC_OBS_CE.WChem_Od_1.txt");
//        InputStreamReader insr = new InputStreamReader(zipFile.getInputStream(txtFile), StandardCharsets.ISO_8859_1);
//        DataFileValidation.validateTextFileFormat(zipFile, txtFile, insr);
//    }
    /**
     * -- valid text datafile with additional value Test of validateTextFileFormat method, of class DataFileValidation.
     * For a ZipFile containing datafile with a valid format
     */
    @Test
    public void testValidateTextFileFormatAdditionalValue() throws Exception {
        System.out.println("validateTextFileFormat");
        File tmpCATCFolder = folder.newFolder("CATC");
        File tmpFile = new File(tmpCATCFolder, "validDataFiles.zip");
        InputStream in = DataFileValidationTest.class.getClassLoader().getResourceAsStream("datafile/CATC_DAT_CE.WChem_Od - additionalValue.zip");
        inputStreamToFile(in, tmpFile);
        ZipFile zipFile = new ZipFile(tmpFile);
        ZipEntry txtFile = zipFile.getEntry("CATC_OBS_CE.WChem_Od_1.txt");
        InputStreamReader insr = new InputStreamReader(zipFile.getInputStream(txtFile), StandardCharsets.UTF_8);
        this.dataFileValidationService.validateTextFileFormat(zipFile, txtFile);
        tmpCATCFolder.delete();
    }

    /**
     * -- wrong text datafile. wrong header format Test of validateTextFileFormat method, of class DataFileValidation.
     * For a ZipFile containing datafile with a wrong header format
     */
    @Test(expected = InvalidTextFileFormatException.class)
    public void testInValidateTextFileFormat1() throws Exception {
        System.out.println("validateTextFileFormat");

        File tmpCATCFolder = folder.newFolder("CATC");
        File tmpFile = new File(tmpCATCFolder, "invalidDataFiles.zip");
        InputStream in = DataFileValidationTest.class.getClassLoader().getResourceAsStream("datafile/CATC_DAT_CE.WChem_Od - invalid1.zip");
        inputStreamToFile(in, tmpFile);
        ZipFile zipFile = new ZipFile(tmpFile);
        ZipEntry txtFile = zipFile.getEntry("CATC_OBS_CE.WChem_Od_1.txt");
        InputStreamReader insr = new InputStreamReader(zipFile.getInputStream(txtFile), StandardCharsets.UTF_8);
        this.dataFileValidationService.validateTextFileFormat(zipFile, txtFile);
        tmpCATCFolder.delete();
    }

    /**
     * -- wrong text datafile: wrong line, too much digits for elevation precision Test of validateTextFileFormat
     * method, of class DataFileValidation. For a ZipFile containing datafile with a wrong data line format
     */
    @Test(expected = InvalidTextFileFormatException.class)
    public void testInValidateTextFileFormat2() throws Exception {
        System.out.println("validateTextFileFormat");

        File tmpCATCFolder = folder.newFolder("CATC");
        File tmpFile = new File(tmpCATCFolder, "invalidDataFiles.zip");
        InputStream in = DataFileValidationTest.class.getClassLoader().getResourceAsStream("datafile/CATC_DAT_CE.WChem_Od - invalid2.zip");
        inputStreamToFile(in, tmpFile);
        ZipFile zipFile = new ZipFile(tmpFile);
        ZipEntry txtFile = zipFile.getEntry("CATC_OBS_CE.WChem_Od_1.txt");
        InputStreamReader insr = new InputStreamReader(zipFile.getInputStream(txtFile), StandardCharsets.UTF_8);
        this.dataFileValidationService.validateTextFileFormat(zipFile, txtFile);
        tmpCATCFolder.delete();
    }

    /**
     * -- wrong text datafile: wrong date Test of validateTextFileFormat method, of class DataFileValidation. For a
     * ZipFile containing datafile with a wrong data line format
     */
    @Test(expected = InvalidTextFileFormatException.class)
    public void testInValidateTextFileFormat3() throws Exception {
        System.out.println("validateTextFileFormat");

        File tmpCATCFolder = folder.newFolder("CATC");
        File tmpFile = new File(tmpCATCFolder, "invalidDataFiles.zip");
        InputStream in = DataFileValidationTest.class.getClassLoader().getResourceAsStream("datafile/CATC_DAT_CE.WChem_Od - invalid3.zip");
        inputStreamToFile(in, tmpFile);
        ZipFile zipFile = new ZipFile(tmpFile);
        ZipEntry txtFile = zipFile.getEntry("CATC_OBS_CE.WChem_Od_1.txt");
        InputStreamReader insr = new InputStreamReader(zipFile.getInputStream(txtFile), StandardCharsets.UTF_8);
        this.dataFileValidationService.validateTextFileFormat(zipFile, txtFile);
        tmpCATCFolder.delete();
    }

    /**
     * -- valid json and zip file Test of validateZipFiles method, of class DataFileValidation. For a valid json and
     * zipFile
     */
    @Test
    public void testValidateZipFiles() throws Exception {
        System.out.println("validateZipFiles");
        File tmpCATCFolder = folder.newFolder("CATC");
        File trash = folder.newFolder("trash");
        File tmpFileCP1252 = new File(trash, "inputStreamCP1252.zip");
        InputStream zipIn = DataFileValidationTest.class.getClassLoader().getResourceAsStream("datafile/CATC_DAT_CE.WChem_Od.zip");
        inputStreamToFile(zipIn, tmpFileCP1252);

        File jsonIn = new ClassPathResource("jsonlight/CATC_en.json").getFile();
        Pivot json = objectMapper.readValue(jsonIn , Pivot.class);

        File tmpFileUTF8 = new File(tmpCATCFolder, "CATC_DAT_CE.WChem_Od.zip");
        // ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(tmpFileUTF8),StandardCharsets.UTF_8);
        ZipFile zipCP1252 = new ZipFile(tmpFileCP1252);

        //ZipFile zipUTF8 =  new ZipFile(tmpFileUTF8);
        ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(tmpFileUTF8), StandardCharsets.UTF_8);
        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(zos, StandardCharsets.UTF_8));
        Enumeration<? extends ZipEntry> entries = zipCP1252.entries();
        while (entries.hasMoreElements()) {
            ZipEntry entryCP1252 = entries.nextElement();
            File txtCP1252 = new File(trash, entryCP1252.getName());
            InputStream zipCP1252in = zipCP1252.getInputStream(entryCP1252);
            inputStreamToFile(zipCP1252in, txtCP1252);
            ZipEntry entryUTF8 = new ZipEntry(entryCP1252.getName());
            BufferedReader reader = new BufferedReader(new FileReader(txtCP1252));
            zos.putNextEntry(entryUTF8);
            String line = null;
            while ((line = reader.readLine()) != null) {
                writer.append(line).append('\n');
            }
            writer.flush();
            reader.close();
            zos.closeEntry();
        }
        writer.close();

        List<File> zipFiles = FileUtils.listFiles(tmpCATCFolder.getPath(), "zip");
        Map<String, Boolean> expectedResult = new LinkedHashMap<>();
        json.getDatasets().forEach(dataset -> {
            dataset.getObservations().forEach(obs -> {
                expectedResult.put(obs.getObservationId(), Boolean.TRUE);
            });
        });
        Map<String, Boolean> result = this.dataFileValidationService.validateZipFiles(json, zipFiles);
        tmpCATCFolder.delete();
        assertEquals(expectedResult, result);
    }

    /**
     * -- valid json and not a zip file Test of validateZipFiles method, of class DataFileValidation. For a valid json
     * and another non-zip file
     */
    @Test
    public void testNotAZipFile() throws Exception {
        File tmpCATCFolder = folder.newFolder("CATC");
        File tmpFile = new File(tmpCATCFolder, "CATC_DAT_CE.WChem_Od.zip");
        InputStream zipIn = DataFileValidationTest.class.getClassLoader().getResourceAsStream("json/CATC_en.json");
        inputStreamToFile(zipIn, tmpFile);
//        InputStream jsonIn = DataFileValidationTest.class.getClassLoader().getResourceAsStream("json/CATC_en.json");
//        ObjectNode json = inputStreamToJsonObject(jsonIn);

        File jsonIn = new ClassPathResource("json/CATC_en.json").getFile();
        Pivot json = objectMapper.readValue(jsonIn , Pivot.class);


        List<File> zipFiles = FileUtils.listFiles(tmpCATCFolder.getPath(), "zip");
        Map<String, Boolean> result = this.dataFileValidationService.validateZipFiles(json, zipFiles);
        Map<String, Boolean> expectedResult = new LinkedHashMap<>();
        json.getDatasets().forEach(dataset -> {
            dataset.getObservations().forEach(obs -> {
                expectedResult.put(obs.getObservationId(), Boolean.FALSE); // false ? null ? see @Boolean
            });
        });
        tmpCATCFolder.delete();
        assertEquals(expectedResult, result);
    }

    /**
     * -- wrong zip file: zip file name does not correspond to dataset name Test of validateZipFiles method, of class
     * DataFileValidation. For a invalid zip file name
     */
    @Test
    public void testInvalidateZipFiles() throws Exception {
        System.out.println("validateZipFiles");
        File tmpCATCFolder = folder.newFolder("CATC");
        File tmpFile = new File(tmpCATCFolder, "CATC_CE.Sap_Odc_14.zip");

        InputStream zipIn = DataFileValidationTest.class.getClassLoader().getResourceAsStream("datafile/CATC_DAT_CE.WChem_Od.zip");
        inputStreamToFile(zipIn, tmpFile);
//        InputStream jsonIn = DataFileValidationTest.class.getClassLoader().getResourceAsStream("json/CATC_en.json");
//        ObjectNode json = inputStreamToJsonObject(jsonIn);
        File jsonIn = new ClassPathResource("json/CATC_en.json").getFile();
        Pivot json = objectMapper.readValue(jsonIn , Pivot.class);

        List<File> zipFiles = FileUtils.listFiles(tmpCATCFolder.getPath(), "zip");
        Map<String, Boolean> result = this.dataFileValidationService.validateZipFiles(json, zipFiles);
        Map<String, Boolean> expectedResult = new LinkedHashMap<>();
        json.getDatasets().forEach(dataset -> {
            dataset.getObservations().forEach(obs -> {
                expectedResult.put(obs.getObservationId(), Boolean.FALSE);
            });
        });
        tmpCATCFolder.delete();
        assertEquals(expectedResult, result);
    }

    /**
     * -- wrong zip file: zip file corresponds to dataset name but text datafile names do not correponds to
     * observationId Test of validateZipFiles method, of class DataFileValidation. For a valid zip file name but with
     * wrong text file name
     */
    @Test
    public void testValidateZipFilesInvalidTextFileName() throws Exception {
        System.out.println("validateZipFiles");
        File tmpCATCFolder = folder.newFolder("CATC");
        File tmpFile = new File(tmpCATCFolder, "CATC_DAT_CE.WChem_Od.zip");

        InputStream zipIn = DataFileValidationTest.class.getClassLoader().getResourceAsStream("datafile/wrongTxt/CATC_DAT_CE.WChem_Od.zip");
        inputStreamToFile(zipIn, tmpFile);
//        InputStream jsonIn = DataFileValidationTest.class.getClassLoader().getResourceAsStream("jsonlight/CATC_en.json");
//        ObjectNode json = inputStreamToJsonObject(jsonIn);
        File jsonIn = new ClassPathResource("jsonlight/CATC_en.json").getFile();
        Pivot json = objectMapper.readValue(jsonIn , Pivot.class);

        List<File> zipFiles = FileUtils.listFiles(tmpCATCFolder.getPath(), "zip");
        Map<String, Boolean> expectedResult = new LinkedHashMap<>();
        json.getDatasets().forEach(dataset -> {
            dataset.getObservations().forEach(obs -> {
                expectedResult.put(obs.getObservationId(), Boolean.TRUE);
            });
        });
        expectedResult.replace("CATC_OBS_CE.WChem_Od_1", Boolean.FALSE);
        Map<String, Boolean> result = this.dataFileValidationService.validateZipFiles(json, zipFiles);
        tmpCATCFolder.delete();
        assertEquals(expectedResult, result);
    }

    // huh. see @Files , Jackson signature, or anything else. this is begging for problems.
    private static void inputStreamToFile(InputStream in, File file) {

        try (OutputStream out = new FileOutputStream(file)) {
            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = in.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
        } catch (IOException e) {
            System.out.println("coucou!");
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    System.out.println("coucou!");
                    e.printStackTrace();
                }
            }
        }
    }

    //    private static JSONObject inputStreamToJSONObject(InputStream in) throws IOException {
//
//        StringBuilder builder = new StringBuilder();
//        try (Reader reader = new BufferedReader(new InputStreamReader((in)));) {
//            char[] chars = new char[1024];
//            int read;
//            while ((read = reader.read(chars)) != -1) {
//                builder.append(chars, 0, read);
//            }
//        }
//        JSONObject json = new JSONObject(new JSONTokener(builder.toString()));
//        return json;
//    }
    private ObjectNode inputStreamToJsonObject(InputStream in) throws IOException {
        //Gson gson = new GsonBuilder().setPrettyPrinting().create();
        StringBuilder builder = new StringBuilder();
        try (Reader reader = new BufferedReader(new InputStreamReader((in)));) {
            char[] chars = new char[1024];
            int read;
            while ((read = reader.read(chars)) != -1) {
                builder.append(chars, 0, read);
            }
        }
        ObjectNode json = (ObjectNode) objectMapper.readTree(builder.toString());
        return json;
    }
}
