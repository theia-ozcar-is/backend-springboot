package fr.theialand.insitu.dataportal.api.geonetwork;

import fr.theialand.insitu.dataportal.api.geonetwork.exceptions.GeonetworkApiException;
import fr.theialand.insitu.dataportal.api.geonetwork.exceptions.GeonetworkException;
import fr.theialand.insitu.dataportal.api.geonetwork.exceptions.GeonetworkLinkException;
import org.apache.sis.metadata.iso.DefaultMetadata;
import org.apache.sis.xml.XML;
import org.assertj.core.api.Fail;
import org.hamcrest.text.MatchesPattern;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.opengis.metadata.Metadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureMockRestServiceServer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.client.response.MockRestResponseCreators;
import org.springframework.web.client.RestClientException;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.net.SocketTimeoutException;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureMockRestServiceServer
@SpringBootTest(classes = GeonetworkConfig.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ComponentScan("fr.theialand.insitu.dataportal.api") // to find the RestTemplate which is in api-common package
@Disabled("Should be tested using a test geonetwork instance, not using the dev cluster")
class GeonetworkPublishTest {

    @Autowired
    private GeonetworkClient gnClient;

    @Autowired
    private MockRestServiceServer mockServer;

    private static Metadata TEST_METADATA;

    /**
     * All Publish Calls have a first call to the groupAPI to get Ids
     */
    @BeforeAll
    void prepareClient() {
        //this.mockServer.reset();
        this.mockServer.expect( ExpectedCount.once(),
                MockRestRequestMatchers.method(HttpMethod.GET))
                .andExpect(MockRestRequestMatchers.requestTo(MatchesPattern.matchesPattern(".*/api/groups.*")))
                .andRespond( MockRestResponseCreators.withSuccess(new ClassPathResource("test_samples/groups.json"), MediaType.APPLICATION_JSON));
    }

    @BeforeAll
    static void initSampleDatasets() throws IOException, JAXBException {
        //String xml =  Files.readString( new ClassPathResource("test_samples/record.xml").getFile().toPath() );
        TEST_METADATA = (Metadata) XML.unmarshal(new ClassPathResource("test_samples/record.xml").getFile());
    }

    @Test
    void twoConsecutiveCallsToPublishShouldOnlyCallGroupsApiOnceTest() throws GeonetworkException {
        // SETUP
        // Mock the real PUT req
        this.mockServer.expect(ExpectedCount.twice(),
                MockRestRequestMatchers.method(HttpMethod.PUT))
                .andExpect(MockRestRequestMatchers.requestTo(MatchesPattern.matchesPattern(".*/api/records.*")))
                .andRespond(MockRestResponseCreators.withSuccess(new ClassPathResource("test_samples/reportSuccess.json"), MediaType.APPLICATION_JSON));

        // CALL
//        DefaultMetadata md = new DefaultMetadata();
//        md.setFileIdentifier("TEST");

        gnClient.publishDataset(TEST_METADATA); // see mock
        gnClient.publishDataset(TEST_METADATA);

        // ASSERT
        this.mockServer.verify(); // 1 GET 2 PUT
    }

    @Test
    void publishWhenGNisOutShouldThrowExTest() throws GeonetworkException {

        // Mock the real PUT req
        this.mockServer.expect(ExpectedCount.once(), MockRestRequestMatchers.method(HttpMethod.PUT))
                .andExpect(MockRestRequestMatchers.requestTo(MatchesPattern.matchesPattern(".*/api/records.*")))
                .andRespond(MockRestResponseCreators.withException(new SocketTimeoutException("too late!")));

        try {
//            DefaultMetadata md = new DefaultMetadata();
//            md.setFileIdentifier("TEST");
            gnClient.publishDataset(TEST_METADATA); // whatever,mocked
            Fail.failBecauseExceptionWasNotThrown(GeonetworkApiException.class);
        } catch (GeonetworkLinkException gnApiEx) {
            this.mockServer.verify();
            assertThat( gnApiEx ).hasCauseInstanceOf(RestClientException.class );
            assertThat( gnApiEx ).hasRootCauseInstanceOf(SocketTimeoutException.class );
        }
    }

}
