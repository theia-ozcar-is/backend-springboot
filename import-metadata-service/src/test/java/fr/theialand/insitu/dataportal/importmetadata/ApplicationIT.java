package fr.theialand.insitu.dataportal.importmetadata;

import fr.theialand.insitu.dataportal.importmetadata.service.validation.JsonFileValidationServiceImpl;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(
        args = "--prodDir=src/test/resources/zipProducer/TEST/tmp/123/unzip",
        webEnvironment = SpringBootTest.WebEnvironment.NONE)

@Disabled("Without any mocking, this class, even if empty, runs a full import. That's why it should be run only manually")
class ApplicationIT {

    @Test
    @Disabled("even if empty, this DOES run the entire app.. see @SpringBootTest args above")
    void runImportApp() {
        // even if empty, this DOES run the entire app.. see @SpringBootTest args above
        // Do.nothing() pattern
    }

}
