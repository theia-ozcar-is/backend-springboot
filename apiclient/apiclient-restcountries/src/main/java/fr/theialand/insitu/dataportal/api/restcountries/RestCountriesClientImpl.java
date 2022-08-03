package fr.theialand.insitu.dataportal.api.restcountries;

import fr.theialand.insitu.dataportal.api.exception.ClientException;
import fr.theialand.insitu.dataportal.api.exception.ClientLinkException;
import fr.theialand.insitu.dataportal.api.exception.ClientWorkflowException;
import fr.theialand.insitu.dataportal.api.restcountries.model.RestCountry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class RestCountriesClientImpl implements RestCountriesClient {

    /**
     * Should it change, it will probably be better in a .properties
     */
    private static String restCountriesUrl;

    private final RestTemplate restTemplate;

    private static final Logger LOG = LoggerFactory.getLogger(RestCountriesClientImpl.class);


    @Autowired
    RestCountriesClientImpl(RestTemplate restTemplate, @Value("${restcountries.api.url}") String restCountriesUrl ) {
        this.restTemplate = restTemplate;
        this.restCountriesUrl = restCountriesUrl;
    }

    @Override
    @Nullable
    public RestCountry getCountryByCode( @NonNull String countryCode) throws ClientException  {

        if ( countryCode.length() < 2 || countryCode.length() > 3)
            throw new ClientWorkflowException("the country code provided: "+countryCode+" should be 2 or 3 characters long");

        if (countryCode.equals("eu")) {
            LOG.info("Europe code is not implemented in restcountries API. Manually returned by the client implementation.");
            RestCountry europe = new RestCountry();
            europe.setAlpha2Code("eu");
            europe.setName("Europe");
            europe.setTranslations(Map.of("fr","Europe","en","Europe"));
            return europe;
        }


        String url = restCountriesUrl+"/v2/alpha/"+countryCode;
        LOG.info("Fetching {} country info from restcountries API at {}", countryCode, url);
        try {
            return restTemplate.getForObject(url, RestCountry.class);
        } catch ( RestClientResponseException restEx) {
            if( restEx.getRawStatusCode() == 404 ){
                LOG.warn("country code {} not found at restcountries API (404 {}, {})", countryCode, restEx.getStatusText(), restEx.getResponseBodyAsString());
                return null;
                //throw new ClientNotFoundException("country id "+countryCode+" was not foundon restCountries website. is it a valid country code ?", countryCode);
            }
            throw new ClientException("website RestCountries replied with a code "+restEx.getRawStatusCode() + restEx.getStatusText(), restEx ) ;
        } catch ( ResourceAccessException apiEx) {
            throw new ClientLinkException("Problem getting country "+countryCode+" information from RestCountry website", apiEx);
        }
    }
}
