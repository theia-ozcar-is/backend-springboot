package fr.theialand.insitu.dataportal.repository.mongo;

import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.entity.ObservationDocument;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.enumerations.EnumEventType;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.dataset.Dataset;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.observation.ObservedProperty;
import fr.theialand.insitu.dataportal.repository.mongo.repository.DatasetLogTestRepository;
import fr.theialand.insitu.dataportal.repository.mongo.repository.ObservationDocumentRepository;
import fr.theialand.insitu.dataportal.repository.mongo.service.DatasetService;
import fr.theialand.insitu.dataportal.repository.mongo.service.search.metadata.SearchService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;
import java.util.SortedMap;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Disabled("kind of a PoC test, not really a test")
class DatasetServiceIT {

    private static final String TESTID = "TEST_ID";

    @Autowired
    private DatasetService datasetService;

    @Autowired
    private SearchService searchService;

    @Autowired
    private DatasetLogTestRepository testRepo ;

    @Autowired
    private ObservationDocumentRepository obsDocRepo ;

    @AfterAll
    void cleanupTestData() {
        testRepo.deleteDatasetLogsByDatasetId(TESTID);
    }

    @Test
    @DisplayName("3 Revision should only be persisted as 1")
    void insertNewDatainDatalogTest() throws InterruptedException {

        datasetService.saveRevisionEvent(TESTID);
        Thread.sleep(1000);
        datasetService.saveRevisionEvent(TESTID);
        Thread.sleep(1000);
        datasetService.saveRevisionEvent(TESTID);

        SortedMap<Date,EnumEventType> events = datasetService.getEvents(TESTID);

        assertThat(events).isNotEmpty().hasSizeGreaterThanOrEqualTo(1);
        assertThat(events.lastKey()).isCloseTo(new Date(), 1800);
        assertThat(events.values()).endsWith(EnumEventType.REVISION);
    }

    @Test
    void testfindTheiaVariablesOfWrongDatasetShouldReturnEmpty(){

        List<ObservationDocument> props = datasetService.getObservedProperties(new String());

        assertThat(props).isEmpty();
    }

    @Test
    void testfindTheiaVariablesOfGoodDatasetShouldReturnSomeProps(){
        Dataset ds = searchService.findDatasetsByProducerId("CRYO").get(0);

        List<ObservationDocument> observationDocuments = datasetService.getObservedProperties(ds.getDatasetId());

        assertThat(observationDocuments).isNotEmpty();
        for(ObservationDocument obs : observationDocuments) {
            assertThat(obs).isNotNull();
            if(obs.getObservation().getObservedProperty().getTheiaVariable() != null)
                assertThat(obs.getObservation().getObservedProperty().getTheiaVariable().getUri()).contains("theia");
        }
    }

    @Test
    @DisplayName("set creation Date should find the oldest data date")
    void testSetEventCreation() {

        this.datasetService.savePublicationAndCreationEvents("MSdEC_DAT_6");
        SortedMap<Date,EnumEventType> events = datasetService.getEvents("MSdEC_DAT_6");

        assertThat(events).hasSizeLessThanOrEqualTo(2);
    }

}
