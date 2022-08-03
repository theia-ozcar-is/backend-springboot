/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.theialand.insitu.dataportal.importmetadata.ImportConfiguration;


import fr.theialand.insitu.dataportal.importmetadata.service.ImportConfig;
import org.junit.*;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author coussotc
 */
public class ImportConfigTest {

    public ImportConfigTest() {
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
     * Test of getLanguages method, of class ImportConfig.
     */
    @Test
    public void testGetLanguages() throws IOException {
        System.out.println("getLanguages");
        File tmpCATCFolder = folder.newFolder("CATCH");
        File tmpFile1 = new File(tmpCATCFolder, "CATC_en.json");
        InputStream in1 = ImportConfigTest.class.getClassLoader().getResourceAsStream("json/CATC_en.json");
        inputStreamToFile(in1, tmpFile1);
        File tmpFile2 = new File(tmpCATCFolder, "CATC_fr.json");
        InputStream in2 = ImportConfigTest.class.getClassLoader().getResourceAsStream("json/CATC_fr.json");
        inputStreamToFile(in2, tmpFile2);
        List<File> jsonFiles = new ArrayList<>();
        jsonFiles.add(tmpFile1);
        jsonFiles.add(tmpFile2);
        Map<String, Map<String, Boolean>> linkedToDataFile = new LinkedHashMap<>();
        Map<String,Boolean> link = new LinkedHashMap<>();
        link.put("test", Boolean.FALSE);
        linkedToDataFile.put("en", link);
        ImportConfig instance = new ImportConfig(linkedToDataFile, jsonFiles);
        String[] expResult = {"en", "fr"};
        String[] result = instance.getLanguages();
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of isLinkTowardDatafile method, of class ImportConfig.
     */
    @Test
    public void testIsLinkTowardDatafile() throws IOException {
        System.out.println("isLinkTowardDatafile");
        File tmpCATCFolder = folder.newFolder("CATCH");
        File tmpFile1 = new File(tmpCATCFolder, "CATC_en.json");
        InputStream in1 = ImportConfigTest.class.getClassLoader().getResourceAsStream("json/CATC_en.json");
        inputStreamToFile(in1, tmpFile1);
        File tmpFile2 = new File(tmpCATCFolder, "CATC_fr.json");
        InputStream in2 = ImportConfigTest.class.getClassLoader().getResourceAsStream("json/CATC_fr.json");
        inputStreamToFile(in2, tmpFile2);
        List<File> jsonFiles = new ArrayList<>();
        jsonFiles.add(tmpFile1);
        jsonFiles.add(tmpFile2);
        Map<String, Map<String, Boolean>> linkedToDataFile = new LinkedHashMap<>();
        Map<String,Boolean> link = new LinkedHashMap<>();
        link.put("test", Boolean.FALSE);
        linkedToDataFile.put("en", link);
        ImportConfig instance = new ImportConfig(linkedToDataFile, jsonFiles);
        boolean expResult =false;
        boolean result = instance.isLinkTowardDatafile("en","test");
        assertEquals(expResult, result);
    }

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
