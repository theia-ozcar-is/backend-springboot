package fr.theialand.insitu.dataportal.api.koppenclimate;

import fr.theialand.insitu.dataportal.api.koppenclimate.model.LocationKoppenClimateDTO;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;

@ComponentScan
public interface KoppenClimateApiClient {

    /**
     * Get the koppen climate at one location
     * @param location Location in LatLong
     * @return The koppen climate DTO object or null if no climate data is existing at the location
     */
    LocationKoppenClimateDTO getClimate(List<Double> location);

}
