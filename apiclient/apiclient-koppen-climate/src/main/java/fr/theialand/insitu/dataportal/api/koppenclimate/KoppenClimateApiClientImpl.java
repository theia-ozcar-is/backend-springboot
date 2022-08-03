package fr.theialand.insitu.dataportal.api.koppenclimate;

import fr.theialand.insitu.dataportal.api.koppenclimate.api.KoppenClimateApi;
import fr.theialand.insitu.dataportal.api.koppenclimate.model.LocationKoppenClimateDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KoppenClimateApiClientImpl implements KoppenClimateApiClient {

    private static final Logger LOG = LoggerFactory.getLogger(KoppenClimateApiClientImpl.class);

    private KoppenClimateApi koppenClimateApi;

    @Autowired
    public KoppenClimateApiClientImpl(KoppenClimateApi koppenClimateApi,  @Value("${koppenclimate.api.url}") String basePath) {
        koppenClimateApi.getApiClient().setBasePath(basePath);
        this.koppenClimateApi = koppenClimateApi;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LocationKoppenClimateDTO getClimate(List<Double> location) {
        LOG.info("Reading koppen climate feature for location " +location.get(0) + ", " + location.get(1));
        return this.koppenClimateApi.getClimate(location.get(0)+","+ location.get(1));
    }
}
