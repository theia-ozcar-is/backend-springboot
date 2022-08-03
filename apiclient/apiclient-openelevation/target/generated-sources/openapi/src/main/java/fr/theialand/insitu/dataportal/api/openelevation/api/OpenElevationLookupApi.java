package fr.theialand.insitu.dataportal.api.openelevation.api;

import fr.theialand.insitu.dataportal.api.openelevation.handler.ApiClient;

import fr.theialand.insitu.dataportal.api.openelevation.model.LocationElevationResultsDTO;
import fr.theialand.insitu.dataportal.api.openelevation.model.LocationsPostParam;

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

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-04-15T16:39:57.003788+02:00[Europe/Paris]")
@Component("fr.theialand.insitu.dataportal.api.openelevation.api.OpenElevationLookupApi")
public class OpenElevationLookupApi {
    private ApiClient apiClient;

    public OpenElevationLookupApi() {
        this(new ApiClient());
    }

    @Autowired
    public OpenElevationLookupApi(ApiClient apiClient) {
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
     * Returns (\&quot;looks up\&quot;) the elevation at one or more (latitude,longitude) points. The GET API is limited to 1024 bytes in the request line. If you plan on making large requests, consider using the POST api. 
     * <p><b>200</b> - A JSON object with a single list of results, in the results field is returned. Each result contains latitude, longitude and elevation. The results are in the same order as the request parameters. Elevation is in meters. If there is no recorded elevation at the provided coordinate, sea level (0 meters) is returned.
     * @param locations List of locations, separated by | in latitude, longitude format, similar to the Google Elevation API (required)
     * @return LocationElevationResultsDTO
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public LocationElevationResultsDTO getLookup(String locations) throws RestClientException {
        return getLookupWithHttpInfo(locations).getBody();
    }

    /**
     * 
     * Returns (\&quot;looks up\&quot;) the elevation at one or more (latitude,longitude) points. The GET API is limited to 1024 bytes in the request line. If you plan on making large requests, consider using the POST api. 
     * <p><b>200</b> - A JSON object with a single list of results, in the results field is returned. Each result contains latitude, longitude and elevation. The results are in the same order as the request parameters. Elevation is in meters. If there is no recorded elevation at the provided coordinate, sea level (0 meters) is returned.
     * @param locations List of locations, separated by | in latitude, longitude format, similar to the Google Elevation API (required)
     * @return ResponseEntity&lt;LocationElevationResultsDTO&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<LocationElevationResultsDTO> getLookupWithHttpInfo(String locations) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'locations' is set
        if (locations == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'locations' when calling getLookup");
        }
        
        String path = apiClient.expandPath("/api/v1/lookup", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "locations", locations));

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] contentTypes = {  };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] {  };

        ParameterizedTypeReference<LocationElevationResultsDTO> returnType = new ParameterizedTypeReference<LocationElevationResultsDTO>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * 
     * Returns (looks up) the elevation at one or more (latitude,longitude) points. The POST API currently has no limit 
     * <p><b>200</b> - A JSON object with a single list of results, in the results field is returned. Each result contains latitude, longitude and elevation. The results are in the same order as the request parameters. Elevation is in meters. If there is no recorded elevation at the provided coordinate, sea level (0 meters) is returned.
     * @param locationsPostParam  (optional)
     * @return LocationElevationResultsDTO
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public LocationElevationResultsDTO postLookup(LocationsPostParam locationsPostParam) throws RestClientException {
        return postLookupWithHttpInfo(locationsPostParam).getBody();
    }

    /**
     * 
     * Returns (looks up) the elevation at one or more (latitude,longitude) points. The POST API currently has no limit 
     * <p><b>200</b> - A JSON object with a single list of results, in the results field is returned. Each result contains latitude, longitude and elevation. The results are in the same order as the request parameters. Elevation is in meters. If there is no recorded elevation at the provided coordinate, sea level (0 meters) is returned.
     * @param locationsPostParam  (optional)
     * @return ResponseEntity&lt;LocationElevationResultsDTO&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<LocationElevationResultsDTO> postLookupWithHttpInfo(LocationsPostParam locationsPostParam) throws RestClientException {
        Object postBody = locationsPostParam;
        
        String path = apiClient.expandPath("/api/v1/lookup", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] contentTypes = { 
            "application/json"
         };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] {  };

        ParameterizedTypeReference<LocationElevationResultsDTO> returnType = new ParameterizedTypeReference<LocationElevationResultsDTO>() {};
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
}
