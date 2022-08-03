package fr.theialand.insitu.dataportal.api.geonetwork;

import fr.theialand.insitu.dataportal.api.geonetwork.exceptions.GeonetworkException;
import org.hamcrest.text.MatchesPattern;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.opengis.metadata.Metadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureMockRestServiceServer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.client.response.MockRestResponseCreators;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureMockRestServiceServer
@SpringBootTest(classes = GeonetworkConfig.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ComponentScan("fr.theialand.insitu.dataportal.api") // to find the RestTemplate which is in api-common package
@Disabled("Should be tested using a test geonetwork instance, not using the dev cluster")
class GeonetworkReadTest {


    @Autowired
    GeonetworkClient gnClient;

    @Autowired
    MockRestServiceServer mockServer;

    /**
     * Test the custom spring http string converters
     */
    @Test
    void getRecordShouldReturnXMLTest() throws GeonetworkException {

        String mostBasicXML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
                +"<gmd:MD_Metadata xmlns:gmd=\"http://www.isotc211.org/2005/gmd\">\n"
                +"  <gmd:fileIdentifier>test</gmd:fileIdentifier>"
                +"</gmd:MD_Metadata>";
        // Mock the GET records
        mockServer.expect(ExpectedCount.once(), MockRestRequestMatchers.method(HttpMethod.GET))
                .andExpect(MockRestRequestMatchers.requestTo(MatchesPattern.matchesPattern(".*/api/records.*")))
                .andRespond(MockRestResponseCreators.withSuccess(mostBasicXML, MediaType.APPLICATION_XML));

        Metadata md = gnClient.getDataset("TEST_id_whateveritismocked");
        assertThat( md ).isNotNull();
    }

}
