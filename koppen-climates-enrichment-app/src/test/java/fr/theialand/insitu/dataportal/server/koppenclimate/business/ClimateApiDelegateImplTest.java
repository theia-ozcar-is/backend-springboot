package fr.theialand.insitu.dataportal.server.koppenclimate.business;

import fr.theialand.insitu.dataportal.server.koppenclimate.model.LocationKoppenClimateDTO;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

@SpringBootTest
class ClimateApiDelegateImplTest {

    @Autowired
    ClimateApiDelegateImpl climateApiDelegate;

    @Test
    void getClimate() throws IOException {
        ResponseEntity<LocationKoppenClimateDTO> locationKoppenClimateDTO = this.climateApiDelegate.getClimate("48.351,-4.844");
        assertEquals("Csb", locationKoppenClimateDTO.getBody().getKoppenClimate().getClassification().getCode());
        assertEquals(HttpStatus.OK,locationKoppenClimateDTO.getStatusCode());
    }

    @Test
    void getClimateSavannah() throws IOException {
        ResponseEntity<LocationKoppenClimateDTO> locationKoppenClimateDTO = this.climateApiDelegate.getClimate("9.7912,1.718");
        assertEquals("Aw", locationKoppenClimateDTO.getBody().getKoppenClimate().getClassification().getCode());
        assertEquals(HttpStatus.OK,locationKoppenClimateDTO.getStatusCode());
    }

    @Test
    void getClimatePolar() throws IOException {
        ResponseEntity<LocationKoppenClimateDTO> locationKoppenClimateDTO = this.climateApiDelegate.getClimate("82.1664460084773,-37.14453125");
        assertEquals("EF", locationKoppenClimateDTO.getBody().getKoppenClimate().getClassification().getCode());
        assertEquals(HttpStatus.OK,locationKoppenClimateDTO.getStatusCode());
    }

    @Test
    void getClimateSea() throws IOException {
        ResponseEntity<LocationKoppenClimateDTO> locationKoppenClimateDTO = this.climateApiDelegate.getClimate("82.1664460084773,-38.14453125");
        assertNull(locationKoppenClimateDTO.getBody().getKoppenClimate());
        assertEquals(HttpStatus.OK,locationKoppenClimateDTO.getStatusCode());
    }
}