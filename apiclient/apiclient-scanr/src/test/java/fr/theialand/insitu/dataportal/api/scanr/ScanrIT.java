package fr.theialand.insitu.dataportal.api.scanr;

import fr.theialand.insitu.dataportal.api.exception.ClientException;
import fr.theialand.insitu.dataportal.api.scanr.model.V2FullStructure;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@SpringBootConfiguration
@EnableAutoConfiguration  // autoconfigure RestTemplateBuilder
@ComponentScan("fr.theialand.insitu.dataportal.api")
class ScanrIT {

    private static final String IRD = "180006025";

    @Autowired
    ScanRClient scanRClient;

    @Test
    void testGetIRDstructureShouldReturnFullStructure() throws ClientException {
        V2FullStructure structure = scanRClient.getStructure( IRD );
        System.out.println(structure.getLabel().get("default"));
        assertThat( structure ).isNotNull();
        assertThat( structure.getAcronym() ).containsEntry("default", "IRD");
    }
}
