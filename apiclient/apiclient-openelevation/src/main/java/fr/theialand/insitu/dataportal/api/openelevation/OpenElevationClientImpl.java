package fr.theialand.insitu.dataportal.api.openelevation;

import fr.theialand.insitu.dataportal.api.openelevation.api.OpenElevationLookupApi;
import fr.theialand.insitu.dataportal.api.openelevation.model.LocationElevationResultsDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class OpenElevationClientImpl implements OpenElevationClient {

    private static final Logger LOG = LoggerFactory.getLogger(OpenElevationClientImpl.class);

    private OpenElevationLookupApi openElevationLookupApi;

    @Autowired
    public OpenElevationClientImpl(OpenElevationLookupApi openElevationLookupApi, @Value("${openelevation.api.url}") String basePath) {
        openElevationLookupApi.getApiClient().setBasePath(basePath);
        this.openElevationLookupApi = openElevationLookupApi;
    }

    @Override
    public LocationElevationResultsDTO getElevation(String locations) {
        LOG.info("Reading elevation for location " +locations);
        return this.openElevationLookupApi.getLookup(locations);
    }
}
