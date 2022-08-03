package fr.theialand.insitu.dataportal.importmetadata.service.validation;

import com.fasterxml.jackson.databind.JsonNode;
import com.networknt.schema.ValidationMessage;
import fr.theialand.insitu.dataportal.importmetadata.Exceptions.ImportException;
import fr.theialand.insitu.dataportal.importmetadata.service.JsonUtils;
import fr.theialand.insitu.dataportal.model.pivot.dataset.Dataset;
import fr.theialand.insitu.dataportal.model.pivot.observation.Observation;
import fr.theialand.insitu.dataportal.model.pivot.producer.Producer;
import fr.theialand.insitu.dataportal.model.configuration.JsonSchemaGeneratorConfiguration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = {
        JsonSchemaValidatorImpl.class,
        JsonSchemaGeneratorConfiguration.class
        })
class JsonSchemaValidationTest {
    @Autowired
    private JsonSchemaValidator schemaValidator;

    @ParameterizedTest
    @ValueSource(strings = {"json/CATC_en.json","json/CATC_fr.json", "json/KARS_en.json"})
    void goodPivotFileShouldValidate(String goodFilePath) throws ImportException, IOException {
        File catcGood = new ClassPathResource(goodFilePath).getFile();
        Set<ValidationMessage> errorMsgs = schemaValidator.validateFile(catcGood);

        assertThat(errorMsgs).isEmpty();
    }

    @Test
    void pivotWithInvalidScanrIdAndIso3166ShouldInvalidate() throws ImportException, IOException {
        File catcGood = new ClassPathResource("json/CATC_en_invalid_scanrId_iso3166.json").getFile();
        Set<ValidationMessage> errorMsgs = schemaValidator.validateFile(catcGood);

        assertThat(errorMsgs)
                .anySatisfy( err -> assertThat(err.getMessage()).contains("idScanR: does not match the regex pattern"))
                .anySatisfy( err -> assertThat(err.getMessage()).contains("iso3166: is missing but it is required"));
    }

    @Test
    void datasetWithInvalidDataShouldInvalidate() throws ImportException, IOException {
        JsonNode incompleteDS = JsonUtils.getNodeFromFile(new ClassPathResource("json-schema/dataset_missingDatasetTitle.json").getFile());
        Set<ValidationMessage> errorMsgs = schemaValidator.validateNode(incompleteDS, Dataset.class);

        assertThat(errorMsgs)
                .isNotEmpty()
                .hasSize(1)
                .allSatisfy( err -> assertThat(err.getMessage()).contains("metadata.title: is missing but it is required"));
    }

    @Test
    void datasetWithValidDataShouldValidate() throws ImportException, IOException {
        JsonNode goodDS = JsonUtils.getNodeFromFile(new ClassPathResource("json-schema/dataset_good.json").getFile());
        Set<ValidationMessage> errorMsgs = schemaValidator.validateNode(goodDS, Dataset.class);

        assertThat(errorMsgs).isEmpty();
    }

    @Test
    void obsWithValidDataShouldValidate() throws ImportException, IOException {
        JsonNode goodObs = JsonUtils.getNodeFromFile(new ClassPathResource("json-schema/observation_good.json").getFile());
        Set<ValidationMessage> errorMsgs = schemaValidator.validateNode(goodObs, Observation.class);

        assertThat(errorMsgs).isEmpty();
    }

    @Test
    void obsWithInvalidDataShouldInvalidate() throws ImportException, IOException {
        JsonNode incompleteObs = JsonUtils.getNodeFromFile(new ClassPathResource("json-schema/observation_WrongDatatype.json").getFile());
        Set<ValidationMessage> errorMsgs = schemaValidator.validateNode(incompleteObs, Observation.class);

        assertThat(errorMsgs)
                .isNotEmpty()
                .hasSize(1)
                .allSatisfy( err -> assertThat(err.getMessage()).contains("dataType: does not have a value in the enumeration"));
    }

    @Test
    void prodWithValidDataShouldValidate() throws ImportException, IOException {
        JsonNode goodProducer = JsonUtils.getNodeFromFile(new ClassPathResource("json-schema/producer_good.json").getFile());
        Set<ValidationMessage> errorMsgs = schemaValidator.validateNode(goodProducer, Producer.class);

        assertThat(errorMsgs).isEmpty();
    }

    @Test
    void prodWithInvalidDataShouldInvalidate() throws ImportException, IOException {
        JsonNode incompleteProducer = JsonUtils.getNodeFromFile(new ClassPathResource("json-schema/producer_badUrl.json").getFile());
        Set<ValidationMessage> errorMsgs = schemaValidator.validateNode(incompleteProducer, Producer.class);

        assertThat(errorMsgs)
                .isNotEmpty()
                .hasSize(1)
                .allSatisfy( err -> assertThat(err.getMessage()).contains("urlDownload: does not match the uri pattern"));
    }

}
