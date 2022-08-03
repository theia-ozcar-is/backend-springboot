/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.theialand.insitu.dataportal.importmetadata.FileValidation;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fr.theialand.insitu.dataportal.api.sparql.query.RDFQueryClientImpl;
import fr.theialand.insitu.dataportal.importmetadata.Exceptions.*;
import fr.theialand.insitu.dataportal.importmetadata.TestUtils;
import fr.theialand.insitu.dataportal.importmetadata.service.validation.JsonFileValidationServiceImpl;
import fr.theialand.insitu.dataportal.importmetadata.service.validation.JsonSchemaValidatorImpl;
import fr.theialand.insitu.dataportal.model.configuration.JsonSchemaGeneratorConfiguration;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author coussotc
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {
        JsonFileValidationServiceImpl.class,
//        JacksonConfiguration.class , // the OM we want is from common-model
        JsonSchemaValidatorImpl.class,
        JsonSchemaGeneratorConfiguration.class
})
//@SpringBootTest(classes = { JsonFileValidationServiceImpl.class } ,webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class JsonFileValidationServiceImplTest {

    /**
     * To have some control on the behavior of this Bean it should be a mock/spy
     * ...as we still go get stuff online, it is not a mock.
     * ... but we don't use this Bean to get the stuff online, so it is not a Spy neither..
     * anyway, let's say it's a @{@link org.mockito.Spy} and get done with it
     */
    @SpyBean
    private RDFQueryClientImpl rdfDaoSpy;

    @Autowired
    private JsonFileValidationServiceImpl jsonFileValidationService;

    /**
     * No need for a bean. we don't go beyond *Node here.
     */
    private ObjectMapper objectMapper = new ObjectMapper();

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();


    /**
     * -- valid json file name Test of validateJsonFileNames method, of class JsonFileValidation. For one valid file
     * name in the folder
     */
    @Test(expected = Test.None.class)
    public void testValidateJsonFileNames() throws Exception {
        System.out.println("validateJsonFileNames");
        File tmpCATCFolder = folder.newFolder("CATC");
        File unzipFolder = new File(tmpCATCFolder.getAbsolutePath() + "/tmp/123/unizp/");
        unzipFolder.mkdirs();
        File tmpFile = new File(unzipFolder, "CATC_en.json");
        InputStream in = JsonFileValidationServiceImplTest.class.getClassLoader().getResourceAsStream("json/CATC_en.json");
        inputStreamToFile(in, tmpFile);
        String folderPath = unzipFolder.getPath();
        this.jsonFileValidationService.validateJsonFileNames(folderPath);

    }

    /**
     * Test of validateJsonFileNames method, of class JsonFileValidation. For two valid file names in the folder
     */
    @Test(expected = Test.None.class)
    public void testValidateJsonFileNames2Files() throws Exception {
        System.out.println("validateJsonFileNames");
        File tmpCATCFolder = folder.newFolder("CATC");
        File unzipFolder = new File(tmpCATCFolder.getAbsolutePath() + "/tmp/123/unizp/");
        unzipFolder.mkdirs();
        File tmpFile = new File(unzipFolder, "CATC_en.json");
        InputStream in = JsonFileValidationServiceImplTest.class.getClassLoader().getResourceAsStream("json/CATC_en.json");
        inputStreamToFile(in, tmpFile);
        File tmpFile2 = new File(unzipFolder, "CATC_fr.json");
        InputStream in2 = JsonFileValidationServiceImplTest.class.getClassLoader().getResourceAsStream("json/CATC_en.json");
        inputStreamToFile(in2, tmpFile2);
        String folderPath = unzipFolder.getPath();
        this.jsonFileValidationService.validateJsonFileNames(folderPath);

    }

    /**
     * Test of validateJsonFileNames method, of class JsonFileValidation. For one file in the folder with an invalid
     * name
     */
    @Test(expected = InvalidJsonFileNameException.class)
    public void testInvalidateJsonFileNames() throws Exception {
        System.out.println("validateJsonFileNames");
        File tmpCATCFolder = folder.newFolder("CATC");
        File unzipFolder = new File(tmpCATCFolder.getAbsolutePath() + "/tmp/123/unizp/");
        unzipFolder.mkdirs();
        File tmpFile = new File(unzipFolder, "CATCH_en.json");
        InputStream in = JsonFileValidationServiceImplTest.class.getClassLoader().getResourceAsStream("json/CATC_en.json");
        inputStreamToFile(in, tmpFile);
        String folderPath = unzipFolder.getPath();
        this.jsonFileValidationService.validateJsonFileNames(folderPath);
    }

    /**
     * -- wrong json file name: does not correspond to the folder name Test of validateJsonFileNames method, of class
     * JsonFileValidation. For one file in the folder with an invalid name
     */
    @Test(expected = InvalidJsonFileNameException.class)
    public void testInvalidateJsonFileNamesDiffFolderName() throws Exception {
        System.out.println("validateJsonFileNames");
        File tmpCATCFolder = folder.newFolder("CATC");
        File unzipFolder = new File(tmpCATCFolder.getAbsolutePath() + "/tmp/123/unizp/");
        unzipFolder.mkdirs();
        File tmpFile = new File(unzipFolder, "MSEC_en.json");
        InputStream in = JsonFileValidationServiceImplTest.class.getClassLoader().getResourceAsStream("json/CATC_en.json");
        inputStreamToFile(in, tmpFile);
        String folderPath = unzipFolder.getPath();
        this.jsonFileValidationService.validateJsonFileNames(folderPath);
    }

    /**
     * -- wrong json file name: wrong name for one out of two json Test of validateJsonFileNames method, of class
     * JsonFileValidation. For one out of two files in the folder with an invalid name
     */
    @Test(expected = InvalidJsonFileNameException.class)
    public void testInvalidateJson2FileNames() throws Exception {
        System.out.println("validateJsonFileNames");
        File tmpCATCFolder = folder.newFolder("CATC");
        File unzipFolder = new File(tmpCATCFolder.getAbsolutePath() + "/tmp/123/unizp/");
        unzipFolder.mkdirs();
        File tmpFile = new File(unzipFolder, "CATCH_en.json");
        InputStream in = JsonFileValidationServiceImplTest.class.getClassLoader().getResourceAsStream("json/CATC_en.json");
        inputStreamToFile(in, tmpFile);
        File tmpFile2 = new File(unzipFolder, "CATC_en.json");
        InputStream in2 = JsonFileValidationServiceImplTest.class.getClassLoader().getResourceAsStream("json/CATC_en.json");
        inputStreamToFile(in2, tmpFile2);
        String folderPath = unzipFolder.getPath();
        this.jsonFileValidationService.validateJsonFileNames(folderPath);
    }

    /**
     * -- empty folder Test of validateJsonFileNames method, of class JsonFileValidation. For an empty folder
     */
    @Test(expected = JsonFileNotFoundException.class)
    public void testInvalidateJsonFileNamesEmpty() throws Exception {
        System.out.println("validateJsonFileNames");
        File tmpCATCFolder = folder.newFolder("CATC");
        File unzipFolder = new File(tmpCATCFolder.getAbsolutePath() + "/tmp/123/unizp/");
        unzipFolder.mkdirs();
        String folderPath = unzipFolder.getPath();
        this.jsonFileValidationService.validateJsonFileNames(folderPath);
    }

    /**
     * -- valid json schema Test of validateSchema method, of class SchemaValidator. for a valid json file with the
     * valid format expected result: nothing
     */
    @Test(expected = Test.None.class)
    public void testValidateSchemaForValidFile() throws Exception {
        //System.out.println(url);
        System.out.println("validateSchema");
        File tmpCATCFolder = folder.newFolder("CATCH");
        File unzipFolder = new File(tmpCATCFolder.getAbsolutePath() + "/tmp/123/unizp/");
        unzipFolder.mkdirs();

        File srcFile = new ClassPathResource("json/CATC_en.json").getFile();
        File tmpFile = new File(tmpCATCFolder, "CATC_en.json");
        Files.copy(srcFile.toPath(), tmpFile.toPath());

        String pathToJson = tmpFile.getPath();
        this.jsonFileValidationService.validateJsonSchema(pathToJson);
    }

    /**
     * -- Wrong JSON format according to RFC8259 Test of validateSchema method, of class SchemaValidator. for a invalid
     * json file expected result the method throw a JSONException
     */
    @Test(expected = InvalidJsonException.class)
    public void testValidateSchemaForInvalidJsonFile() throws Exception {
        System.out.println("validateSchema");
        File tmpCATCFolder = folder.newFolder("CATC");
        File unzipFolder = new File(tmpCATCFolder.getAbsolutePath() + "/tmp/123/unizp/");
        unzipFolder.mkdirs();
////        File tmpFile = new File(tmpCATCFolder, "CATC_en.json");
//        InputStream in = JsonFileValidationServiceImplTest.class.getClassLoader().getResourceAsStream("json/CATC_en_invalidJsonFile.json");
//        inputStreamToFile(in, tmpFile);
        File srcFile = new ClassPathResource("json/CATC_en_invalidJsonFile.json").getFile();
        File tmpFile = new File(tmpCATCFolder, "CATC_en.json");
        Files.copy(srcFile.toPath(), tmpFile.toPath());

        String pathToJson = tmpFile.getPath();
        this.jsonFileValidationService.validateJsonSchema(pathToJson);
    }

    /**
     * --valid JSON format but wrong Json schema Test of validateSchema method, of class SchemaValidator. for a valid
     * json file with invalid JsonFormat expected result the method throw a InvalidJsonException
     */
    @Test(expected = InvalidJsonException.class)
    public void testValidateSchemaForInvalidJsonFormat() throws Exception {
        System.out.println("validateSchema");
        File tmpCATCFolder = folder.newFolder("CATCH");
        File unzipFolder = new File(tmpCATCFolder.getAbsolutePath() + "/tmp/123/unizp/");
        unzipFolder.mkdirs();
        File tmpFile = new File(unzipFolder, "CATC_en.json");
        InputStream in = JsonFileValidationServiceImplTest.class.getClassLoader().getResourceAsStream("json/CATC_en_invalidFormat.json");
        inputStreamToFile(in, tmpFile);
        String pathToJson = tmpFile.getPath();
        this.jsonFileValidationService.validateJsonSchema(pathToJson);
    }

    /**
     * -- Valid json files for internationalisation Test of validateJsonInternationalisation method, of class
     * JsonFileValidation. Expected result: nothing
     */
    @Test(expected = Test.None.class)
    public void testValidateJsonInternationalisation() throws Exception {
        System.out.println("validateJsonInternationalisation");
        File tmpCATCFolder = folder.newFolder("CATCH");
        File unzipFolder = new File(tmpCATCFolder.getAbsolutePath() + "/tmp/123/unizp/");
        unzipFolder.mkdirs();
        File tmpFile1 = new File(unzipFolder, "CATC_en.json");
        InputStream in1 = JsonFileValidationServiceImplTest.class.getClassLoader().getResourceAsStream("json/CATC_en.json");
        inputStreamToFile(in1, tmpFile1);
        File tmpFile2 = new File(unzipFolder, "CATC_fr.json");
        InputStream in2 = JsonFileValidationServiceImplTest.class.getClassLoader().getResourceAsStream("json/CATC_fr.json");
        inputStreamToFile(in2, tmpFile2);
        List<File> jsonFiles = new ArrayList<>();
        jsonFiles.add(tmpFile1);
        jsonFiles.add(tmpFile2);
        this.jsonFileValidationService.validateJsonInternationalisation(jsonFiles);
    }

    /**
     * -- wrong json files : the two json files do not have the same values Test of validateJsonInternationalisation
     * method, of class JsonFileValidation. The two json do not have the same value Expected result: throw a
     * InvalidJsonInternationalisationException
     */
    @Test(expected = InvalidJsonInternationalisationException.class)
    public void testInvalidateJsonInternationalisation() throws Exception {
        System.out.println("validateJsonInternationalisation");
        File tmpCATCFolder = folder.newFolder("CATCH");
        File unzipFolder = new File(tmpCATCFolder.getAbsolutePath() + "/tmp/123/unizp/");
        unzipFolder.mkdirs();
        File tmpFile1 = new File(unzipFolder, "CATC_en.json");
        InputStream in1 = JsonFileValidationServiceImplTest.class.getClassLoader().getResourceAsStream("json/CATC_en.json");
        inputStreamToFile(in1, tmpFile1);
        File tmpFile2 = new File(unzipFolder, "CATC_fr.json");
        InputStream in2 = JsonFileValidationServiceImplTest.class.getClassLoader().getResourceAsStream("json/CATC_fr_wrongValue.json");
        inputStreamToFile(in2, tmpFile2);
        List<File> jsonFiles = new ArrayList<>();
        jsonFiles.add(tmpFile1);
        jsonFiles.add(tmpFile2);
        this.jsonFileValidationService.validateJsonInternationalisation(jsonFiles);
    }

    /**
     * -- wrong json files : the two json files do not have the same fields Test of validateJsonInternationalisation
     * method, of class JsonFileValidation. the two json does not have the same fields Expected result: throw a
     * InvalidJsonInternationalisationException
     */
    @Test(expected = InvalidJsonInternationalisationException.class)
    public void testInvalidateJsonInternationalisationWrongField() throws Exception {
        System.out.println("validateJsonInternationalisation");
        File tmpCATCFolder = folder.newFolder("CATCH");
        File unzipFolder = new File(tmpCATCFolder.getAbsolutePath() + "/tmp/123/unizp/");
        unzipFolder.mkdirs();
        File tmpFile1 = new File(unzipFolder, "CATC_en.json");
        InputStream in1 = JsonFileValidationServiceImplTest.class.getClassLoader().getResourceAsStream("json/CATC_en.json");
        inputStreamToFile(in1, tmpFile1);
        File tmpFile2 = new File(unzipFolder, "CATC_fr.json");
        InputStream in2 = JsonFileValidationServiceImplTest.class.getClassLoader().getResourceAsStream("json/CATC_fr_wrongField.json");
        inputStreamToFile(in2, tmpFile2);
        List<File> jsonFiles = new ArrayList<>();
        jsonFiles.add(tmpFile1);
        jsonFiles.add(tmpFile2);
        this.jsonFileValidationService.validateJsonInternationalisation(jsonFiles);
    }

    @Test(expected = Test.None.class)
    public void testValidateTheiaCategories() throws Exception {
        System.out.println("validateTheiaCategories");
        File tmpCATCFolder = folder.newFolder("CATC");
        File unzipFolder = new File(tmpCATCFolder.getAbsolutePath() + "/tmp/123/unizp/");
        unzipFolder.mkdirs();
        File tmpFile = new File(unzipFolder, "CATC_en.json");
        InputStream in = JsonFileValidationServiceImplTest.class.getClassLoader().getResourceAsStream("json/CATC_en.json");
        inputStreamToFile(in, tmpFile);
        Mockito.when(rdfDaoSpy.findCategoryLeafsUri()).thenReturn( TestUtils.getTheiaUris() );

        //JsonObject json = FileUtils.readJsonFile(tmpFile);
        ObjectNode json = (ObjectNode)objectMapper.readTree(tmpFile);
       ArrayNode categoryNode = (ArrayNode)json.get("datasets").get(0)
               .get("observations").get(0).get("observedProperty")
               .get("theiaCategories");
       List<String> uris = new ArrayList<>();
        Iterator<JsonNode> iterator = categoryNode.elements();
        while(iterator.hasNext()){
            this.jsonFileValidationService.isValidCategory(iterator.next().textValue());
        }
    }

    @Test
    public void testValidateWrongTheiaCategories() throws Exception {
        System.out.println("validateTheiaCategories");
        File tmpCATCFolder = folder.newFolder("CATC");
        File unzipFolder = new File(tmpCATCFolder.getAbsolutePath() + "/tmp/123/unizp/");
        unzipFolder.mkdirs();
        File tmpFile = new File(unzipFolder, "CATC_en.json");
        InputStream in = JsonFileValidationServiceImplTest.class.getClassLoader().getResourceAsStream("json/CATC_en_wrong_category.json");
        inputStreamToFile(in, tmpFile);
        Mockito.when(rdfDaoSpy.findCategoryLeafsUri()).thenReturn(TestUtils.getTheiaUris() );

        ObjectNode json = (ObjectNode)objectMapper.readTree(tmpFile);
        ArrayNode categoryNode = (ArrayNode)json.get("datasets").get(0)
                .get("observations").get(0).get("observedProperty")
                .get("theiaCategories");
        List<String> uris = new ArrayList<>();
        Iterator<JsonNode> iterator = categoryNode.elements();
        while(iterator.hasNext()){
            Assert.assertFalse(this.jsonFileValidationService.isValidCategory(iterator.next().textValue()));
        };
    }

    // is this any different to Files.copy(src, dst) ? cf code smell & tech debt that we _will_ pay later.
    private static void inputStreamToFile(InputStream in, File file) {
        try (OutputStream out = new FileOutputStream(file)) {
            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = in.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }



}
