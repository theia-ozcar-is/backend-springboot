package fr.theialand.insitu.dataportal.api.openelevation;

import fr.theialand.insitu.dataportal.api.openelevation.model.LocationElevationResultsDTO;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
public interface OpenElevationClient {
    LocationElevationResultsDTO getElevation(String locations);
}
