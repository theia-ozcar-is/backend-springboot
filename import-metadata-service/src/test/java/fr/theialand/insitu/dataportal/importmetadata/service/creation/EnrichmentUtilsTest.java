/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.theialand.insitu.dataportal.importmetadata.service.creation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fr.theialand.insitu.dataportal.api.RestClientConfig;
import fr.theialand.insitu.dataportal.api.cgmwgeology.GeologyWMSConfig;
import fr.theialand.insitu.dataportal.api.koppenclimate.KoppenClimateConfig;
import fr.theialand.insitu.dataportal.api.openelevation.OpenElevationClient;
import fr.theialand.insitu.dataportal.api.openelevation.OpenElevationConfig;
import fr.theialand.insitu.dataportal.api.restcountries.RestCountriesConfig;
import fr.theialand.insitu.dataportal.api.scanr.ScanRConfig;
import fr.theialand.insitu.dataportal.importmetadata.Exceptions.ImportException;
import fr.theialand.insitu.dataportal.importmetadata.Exceptions.Iso3116Exception;
import fr.theialand.insitu.dataportal.importmetadata.Exceptions.ScanRException;
import fr.theialand.insitu.dataportal.repository.mongo.configuration.MongoRepoConfiguration;
import fr.theialand.insitu.dataportal.repository.mongo.service.insert.metadata.ImportService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author coussotc
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {
        MongoRepoConfiguration.class,
        RestClientConfig.class,
        ScanRConfig.class,
        RestCountriesConfig.class,
        OpenElevationConfig.class,
        KoppenClimateConfig.class,
        GeologyWMSConfig.class,
        EnrichmentServiceImpl.class
})
@EnableMongoRepositories("fr.theialand.insitu.dataportal.repository.mongo.repository")
@TestPropertySource(locations = "classpath:application.properties")
public class EnrichmentUtilsTest {


    @Autowired
    OpenElevationClient elevationClient;

    @Autowired
    ImportService mongoImportService;

    @Autowired
    EnrichmentServiceImpl enrichmentService;

    ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Test of enrichmentUsingScanR method, of class EnrichmentUtils.
     * Test a scanRId which does not exist
     * @throws Exception
     */
    @Test
    public void testEnrichmentUsingRestCountry() throws Exception {
        File stringJsonFile = new ClassPathResource("json/CATC_en_invalid_scanrId_iso3166.json").getFile();
        ObjectNode json =  (ObjectNode) objectMapper.readTree(stringJsonFile);
         Assert.assertThrows(Iso3116Exception.class, () -> {
             this.enrichmentService.enrichment((ObjectNode)  json.get("producer").get("fundings").get(0));
         });
    }

    /**
     * Test of testEnrichmentUsingScanRFederativeStruct method, of class EnrichmentUtils.
     * Test a federative structure which is not referenced as such in scanR
     * cf: https://scanr-api.enseignementsup-recherche.gouv.fr/api/v2/structures/structure/200919527R (OREME, level="Unité de recherche")
     * @throws Exception
     */
    @Test
    public void testEnrichmentUsingScanRFederativeStruct() throws Exception {
        File stringJsonFile = new ClassPathResource("json/CATC_en_invalid_scanrId_iso3166.json").getFile();
        ObjectNode json =  (ObjectNode) objectMapper.readTree(stringJsonFile);
        this.enrichmentService.enrichment((ObjectNode) json.get("producer").get("fundings").get(3));
    }

    /**
     * Test of testEnrichmentUsingScanREPCSCP method, of class EnrichmentUtils.
     * Test a structure whichs have no acronym and alias objects returned by scanR API
     * cf: https://scanr-api.enseignementsup-recherche.gouv.fr/api/v2/structures/structure/193113842 (Université Paul Sabatier Toulouse)
     * @throws Exception
     */
    @Test
    public void testEnrichmentUsingScanREPCSCP() throws ScanRException,Iso3116Exception,IOException {
        File stringJsonFile = new ClassPathResource("json/CATC_en_invalid_scanrId_iso3166.json").getFile();
        ObjectNode json =  (ObjectNode) objectMapper.readTree(stringJsonFile);
        this.enrichmentService.enrichment((ObjectNode) json.get("producer").get("fundings").get(8));
    }

    /**
     * Test of enrichmentUsingIso3166 method, of class EnrichmentUtils.
     */
    @Test
    public void testEnrichmentUsingIso3166() throws Exception {
        File stringJsonFile = new ClassPathResource("json/CATC_en_invalid_scanrId_iso3166.json").getFile();
        ObjectNode json =  (ObjectNode) objectMapper.readTree(stringJsonFile);
        Assert.assertThrows(Iso3116Exception.class, () -> {
            this.enrichmentService.enrichmentWithIso3166((ObjectNode)json.get("producer").get("fundings").get(0));
        });
    }

        /**
     * Test of getFundingsUsingScanR method, of class ObservationDocumentsCreation.
     * The scanR id is wrong and the funder is not added to fundings list but the import process is not stopped
     */
    @Test
    public void testGetFundingsUsingScanR() throws IOException, ImportException {
        File stringJsonFile = new ClassPathResource("json/CATC_en_invalid_scanrId_iso3166.json").getFile();
        ObjectNode json =  (ObjectNode) objectMapper.readTree(stringJsonFile);
        this.enrichmentService.setFundingsUsingScanR((ArrayNode)json.get("producer").get("fundings"));
    }

}
