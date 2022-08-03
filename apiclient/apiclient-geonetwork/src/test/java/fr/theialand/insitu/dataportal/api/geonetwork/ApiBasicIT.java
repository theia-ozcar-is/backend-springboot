package fr.theialand.insitu.dataportal.api.geonetwork;

import fr.theialand.insitu.dataportal.api.geonetwork.exceptions.GeonetworkException;
import fr.theialand.insitu.dataportal.api.geonetwork.model.StagingEnum;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * IT test of a almost useless API, the Me API, that check logged in user.
 * it is used to verify the state of the framework
 */
@EnableAutoConfiguration
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(classes = GeonetworkConfig.class)
@ComponentScan("fr.theialand.insitu.dataportal.api") // to find the RestTemplate which is in api-common package
@Disabled("Should be tested using a test geonetwork instance, not using the dev cluster")
class ApiBasicIT {

    @Autowired
    protected GeonetworkClient gnClient;

    @Test
    void beanGeonetworkShouldBeCreatedTest() {
        assertThat( gnClient ).isNotNull();
    }

    @Test
    void getMeWithAuthUserShouldReturnUserInfoTest() throws GeonetworkException {
        // We should already have an authorized user, adminadmin
        boolean status = gnClient.amILoggedIn();
        assertThat( status ).isTrue();
    }

    @Test
    void getStatusShouldReturnInfoTest() throws GeonetworkException {
        StagingEnum status = gnClient.getEnvironment();
        assertThat( status )
                .isNotNull()
                .isIn(StagingEnum.production, StagingEnum.development, StagingEnum.testing);
    }

}
