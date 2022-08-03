package fr.theialand.insitu.dataportal.api.geonetwork;

import fr.theialand.insitu.dataportal.api.geonetwork.exceptions.GeonetworkApiException;
import fr.theialand.insitu.dataportal.api.geonetwork.exceptions.GeonetworkClientException;
import fr.theialand.insitu.dataportal.api.geonetwork.exceptions.GeonetworkException;
import fr.theialand.insitu.dataportal.api.geonetwork.model.StagingEnum;
import org.apache.sis.metadata.iso.DefaultMetadata;
import org.apache.sis.xml.XML;
import org.assertj.core.api.Fail;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.opengis.metadata.Metadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.ClassPathResource;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * IT tests of the Records API, the main API for publishing MD
 */
@EnableAutoConfiguration
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(classes = GeonetworkConfig.class)
@ComponentScan("fr.theialand.insitu.dataportal.api") // to find the RestTemplate which is in api-common package
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Disabled("Should be tested using a test geonetwork instance, not using the dev cluster")
class ApiRecordsIT {

    /**
     * Note the "TEST_IDENTIFIER" in the fileIdentifier field, it will be used later on
     */
    static String XML_ISO19139 ;
    static Metadata TEST_METADATA;

    @Autowired
    protected GeonetworkClient gnClient;

    @BeforeAll
    void initApiClient() throws GeonetworkException {

        StagingEnum envEnum = gnClient.getEnvironment();
        Assumptions.assumeFalse(envEnum.equals(StagingEnum.production), "The GN instance used for testing advertise itself as being PRODUCTION, Aborting");
    }

    @BeforeAll
    static void initSampleDatasets() throws IOException, JAXBException {
        //String xml =  Files.readString( new ClassPathResource("test_samples/record.xml").getFile().toPath() );
        TEST_METADATA = (Metadata) XML.unmarshal(new ClassPathResource("test_samples/record.xml").getFile());
    }



    @Test
    @Order(1)
    void pushRecordsShouldReturnIDTest() throws GeonetworkException {
        UUID uuid = gnClient.publishDataset(TEST_METADATA);
        assertThat(uuid).isNotNull();
    }

    @Test
    @Order(2)
    void searchShouldReturnSomethingTest() throws GeonetworkException {

        List<String> ids = gnClient.searchByProducer("TEST_PRODUCER");
        assertThat( ids ).isNotEmpty();
        assertThat( ids.get(0) ).isNotNull();
    }

    @Test
    @Order(3)
    void getRecordShouldReturnMetadataTest() throws GeonetworkException {
        Metadata md = gnClient.getDataset("TEST_IDENTIFIER");

        assertThat(md).isNotNull();
        assertThat(md.getFileIdentifier()).isNotBlank();
    }

    @Test
    @Order(4)
    void deleteShouldBeEffectiveTest() throws GeonetworkException {
        gnClient.deleteDataset("TEST_IDENTIFIER");

        try {
            gnClient.getDataset("TEST_IDENTIFIER");
            Fail.failBecauseExceptionWasNotThrown(GeonetworkApiException.class);
        } catch (GeonetworkApiException apiEx) {
            assertThat(apiEx.getErrorCode()).isEqualTo(404);
        }
    }

    @Test
    void publishEmptyMDShouldRaiseClientExceptionTest()  {

        try {
            gnClient.publishDataset(new DefaultMetadata());
            Fail.shouldHaveThrown(GeonetworkClientException.class);
        } catch ( GeonetworkClientException gnEx) {
            assertThat(gnEx).hasMessageContaining("FileIdentifier");
        } catch ( GeonetworkException gnEx ){
            Fail.shouldHaveThrown(GeonetworkClientException.class);
        }

    }

    @ParameterizedTest
    @ValueSource(strings = {"OZCAR-RI CRYOBS-CLIM" , "OZCAR-RI SNO-Tourbières" })
    @Disabled( "Warning, destructive op for manual tests only")
    void deleteAllRecordsFromProducerTest(String producer) throws GeonetworkException {
        List<String> ids = gnClient.searchByProducer(producer);
        gnClient.deleteDataset(ids.toArray(String[]::new));

        List<String> idsNotFound = gnClient.searchByProducer(producer);

        assertThat(idsNotFound).isEmpty();
    }

}
