package fr.theialand.insitu.dataportal.api.koppenclimate.api;

import fr.theialand.insitu.dataportal.api.koppenclimate.handler.ApiClient;

import fr.theialand.insitu.dataportal.api.koppenclimate.model.LocationKoppenClimateDTO;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-04-15T15:53:41.507160+02:00[Europe/Paris]")
@Component("fr.theialand.insitu.dataportal.api.koppenclimate.api.KoppenClimateApi")
public class KoppenClimateApi {
    private ApiClient apiClient;

    public KoppenClimateApi() {
        this(new ApiClient());
    }

    @Autowired
    public KoppenClimateApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * 
     * Returns the koppen climate code at one (latitude,longitude) point. 
     * <p><b>200</b> - A JSON object with a single result containing latitude, longitude and koppenClimate.
     * @param location Location in latitude, longitude format, similar to the Google Elevation API (required)
     * @return LocationKoppenClimateDTO
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public LocationKoppenClimateDTO getClimate(String location) throws RestClientException {
        return getClimateWithHttpInfo(location).getBody();
    }

    /**
     * 
     * Returns the koppen climate code at one (latitude,longitude) point. 
     * <p><b>200</b> - A JSON object with a single result containing latitude, longitude and koppenClimate.
     * @param location Location in latitude, longitude format, similar to the Google Elevation API (required)
     * @return ResponseEntity&lt;LocationKoppenClimateDTO&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<LocationKoppenClimateDTO> getClimateWithHttpInfo(String location) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'location' is set
        if (location == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'location' when calling getClimate");
        }
        

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "location", location));

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] contentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] {  };

        ParameterizedTypeReference<LocationKoppenClimateDTO> returnType = new ParameterizedTypeReference<LocationKoppenClimateDTO>() {};
        return apiClient.invokeAPI("/climate", HttpMethod.GET, Collections.<String, Object>emptyMap(), queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, authNames, returnType);
    }
}
