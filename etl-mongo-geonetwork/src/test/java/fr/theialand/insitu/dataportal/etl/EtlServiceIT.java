package fr.theialand.insitu.dataportal.etl;

import fr.theialand.insitu.dataportal.api.geonetwork.GeonetworkClient;
import fr.theialand.insitu.dataportal.etl.exception.EtlException;
import fr.theialand.insitu.dataportal.repository.mongo.service.search.metadata.SearchService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Integration Testing
 * THIS NEEDS A localhost:8080 RUNNING GEONETWORK ! (with a valid group too, see properties)
 * Prepare Mongo and GeoNetwork Containers for IT testing
 * run automagically by maven phase verify
 */
@SpringBootTest
@Disabled("Mongo is not reachable on the Gitlab Runner")
class EtlServiceIT {

    //private final static String DATASET_ID = "TOUR_DAT_frn-meteosol-meteosol";
    private final static String DATASET_ID = "CRYO_DAT_gl_mb_alp_arg";

    @Autowired
    private GeonetworkClient gnClient;

    @Autowired
    private SearchService mongoSearchService;

    @Autowired
    private EtlService etlService;

    @Test
    void syncMetadataShouldReturnUUIDTest () throws EtlException {
        UUID uuid = etlService.updateDataset( DATASET_ID );
        assertThat( uuid ).isNotNull();
    }

    /**
     * Populate the GN database
     */
    @ParameterizedTest
    @ValueSource(strings = {"TOUR", "CRYO","CATC"})
    void syncProducerShouldReturnUUIDsTest (String producerId) throws EtlException {
        List<UUID> uuids = etlService.updateAllDatasetsFromProducer( producerId );
        assertThat( uuids ).isNotEmpty();
    }
}
