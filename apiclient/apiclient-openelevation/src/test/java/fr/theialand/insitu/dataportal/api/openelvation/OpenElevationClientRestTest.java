package fr.theialand.insitu.dataportal.api.openelvation;

import fr.theialand.insitu.dataportal.api.openelevation.OpenElevationClient;
import fr.theialand.insitu.dataportal.api.openelevation.model.LocationElevationResultsDTO;
import org.hamcrest.text.MatchesPattern;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.client.response.MockRestResponseCreators;

import static org.assertj.core.api.Assertions.assertThat;

@ComponentScan("fr.theialand.insitu.dataportal.api")
@RestClientTest(OpenElevationClient.class)
@ContextConfiguration(classes = OpenElevationClient.class)
public class OpenElevationClientRestTest {

    @Autowired
    OpenElevationClient openElevationClient;

    @Autowired
    MockRestServiceServer mockServer; // will be injected

    @Test
    void testGetMissingStructureShouldThrowEx()  {
        this.mockServer.expect( ExpectedCount.once(),
                        MockRestRequestMatchers.method(HttpMethod.GET))
                .andExpect(MockRestRequestMatchers.requestTo(MatchesPattern.matchesPattern(".*/api/v1/lookup.*")))
                .andRespond( MockRestResponseCreators.withSuccess()); // body empty, no content

        LocationElevationResultsDTO elevation = openElevationClient.getElevation("TESTwhatever");

        this.mockServer.verify();
        assertThat( elevation ).isNull();
    }
}
