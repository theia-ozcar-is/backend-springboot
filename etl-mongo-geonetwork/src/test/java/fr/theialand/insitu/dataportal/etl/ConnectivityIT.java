package fr.theialand.insitu.dataportal.etl;

import fr.theialand.insitu.dataportal.api.geonetwork.GeonetworkClient;
import fr.theialand.insitu.dataportal.api.geonetwork.exceptions.GeonetworkException;
import fr.theialand.insitu.dataportal.api.geonetwork.model.StagingEnum;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.entity.ObservationDocument;
import fr.theialand.insitu.dataportal.repository.mongo.service.search.metadata.SearchService;
import org.assertj.core.api.Fail;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Integration Testing
 * THIS NEEDS A localhost:8080 RUNNING GEONETWORK ! (with a valid group too, see properties)
 * Prepare Mongo and GeoNetwork Containers for IT testing
 */
@SpringBootTest
@Disabled("Mongo is not reachable on the Gitlab Runner")
class ConnectivityIT  {

    @Autowired
    private GeonetworkClient gnClient;

    @Autowired
    private SearchService mongoSearchService;

    @Test
    void mongoRepoContainerShouldBeReachableTest() {
        try {

            List<ObservationDocument> obss = mongoSearchService.findByDocumentId(Collections.singletonList("424242"));
            assertThat(obss).isNotEmpty();// [null] ? TODO: return an empty list
        } catch (Exception ex ) {
            Fail.fail("Should not have thrown Exception", ex);
        }

    }

    @Test
    void GeoNetworkContainerShouldBeReachableTest() throws GeonetworkException {
        assertThat(gnClient).isNotNull();
        StagingEnum gnRunning = gnClient.getEnvironment();
        assertThat(gnRunning).isNotEqualTo(StagingEnum.production);
    }
}
