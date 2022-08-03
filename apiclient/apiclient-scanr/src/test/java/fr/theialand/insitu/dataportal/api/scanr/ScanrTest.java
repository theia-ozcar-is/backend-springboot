package fr.theialand.insitu.dataportal.api.scanr;

import fr.theialand.insitu.dataportal.api.exception.ClientException;
import fr.theialand.insitu.dataportal.api.scanr.model.V2FullStructure;
import org.hamcrest.text.MatchesPattern;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpMethod;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.client.response.MockRestResponseCreators;

import static org.assertj.core.api.Assertions.assertThat;

@RestClientTest
@ComponentScan("fr.theialand.insitu.dataportal.api") // to find the RestTemplate which is in api-common package
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ScanrTest {

    @Autowired
    ScanRClient scanRClient;

    @Autowired
    MockRestServiceServer mockServer; // will be injected

    @Test
    void testGetMissingStructureShouldThrowEx() throws ClientException {
        this.mockServer.expect( ExpectedCount.once(),
                MockRestRequestMatchers.method(HttpMethod.GET))
                .andExpect(MockRestRequestMatchers.requestTo(MatchesPattern.matchesPattern(".*/api/v2/structures/structure/.*")))
                .andRespond( MockRestResponseCreators.withSuccess()); // body empty, no content

        V2FullStructure structure = scanRClient.getStructure("TESTwhatever");

        this.mockServer.verify();
        assertThat( structure ).isNull();
    }
}
