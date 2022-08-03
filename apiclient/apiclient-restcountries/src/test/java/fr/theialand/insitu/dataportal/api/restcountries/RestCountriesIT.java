package fr.theialand.insitu.dataportal.api.restcountries;

import fr.theialand.insitu.dataportal.api.exception.ClientException;
import fr.theialand.insitu.dataportal.api.restcountries.model.RestCountry;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan("fr.theialand.insitu.dataportal.api") // to find the RestTemplate which is in api-common package
@TestPropertySource(locations = "classpath:application.properties")
class RestCountriesIT {

    @Autowired
    RestCountriesClient countryStoreClient;

    @Test
    void testGetFRCountryShouldAnswerFRandMapResult () throws ClientException {
        RestCountry germany  = countryStoreClient.getCountryByCode("de");
        assertThat( germany ).isNotNull();
        assertThat( germany.getName() ).isEqualTo("Germany");
        assertThat( germany.getTranslations() )
                .containsEntry("fr", "Allemagne")
                .containsEntry("de", "Deutschland");
    }

    @Test
    void testGetEUCode () throws ClientException {
        RestCountry europe  = countryStoreClient.getCountryByCode("eu");
        assertThat( europe ).isNotNull();
        assertThat( europe.getName() ).isEqualTo("Europe");
        assertThat( europe.getTranslations() )
                .containsEntry("fr", "Europe")
                .containsEntry("en", "Europe");
    }
}
