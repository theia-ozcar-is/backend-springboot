package fr.theialand.insitu.dataportal.api.geonetwork.api;

import fr.theialand.insitu.dataportal.api.geonetwork.handler.ApiClient;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-04-13T11:23:44.539800+02:00[Europe/Paris]")
@Component("fr.theialand.insitu.dataportal.api.geonetwork.api.HarvestersApi")
public class HarvestersApi {
    private ApiClient apiClient;

    public HarvestersApi() {
        this(new ApiClient());
    }

    @Autowired
    public HarvestersApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Assign harvester records to a new source
     * 
     * <p><b>404</b> - Resource not found.
     * <p><b>403</b> - Operation not allowed. Only UserAdmins can access it.
     * <p><b>204</b> - Harvester records transfered to new source.
     * <p><b>0</b> - default response
     * @param harvesterUuid The harvester UUID (required)
     * @param source The target source UUID (required)
     * @return String
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public String assignHarvestedRecordToSource(String harvesterUuid, String source) throws RestClientException {
        return assignHarvestedRecordToSourceWithHttpInfo(harvesterUuid, source).getBody();
    }

    /**
     * Assign harvester records to a new source
     * 
     * <p><b>404</b> - Resource not found.
     * <p><b>403</b> - Operation not allowed. Only UserAdmins can access it.
     * <p><b>204</b> - Harvester records transfered to new source.
     * <p><b>0</b> - default response
     * @param harvesterUuid The harvester UUID (required)
     * @param source The target source UUID (required)
     * @return ResponseEntity&lt;String&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<String> assignHarvestedRecordToSourceWithHttpInfo(String harvesterUuid, String source) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'harvesterUuid' is set
        if (harvesterUuid == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'harvesterUuid' when calling assignHarvestedRecordToSource");
        }
        
        // verify the required parameter 'source' is set
        if (source == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'source' when calling assignHarvestedRecordToSource");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("harvesterUuid", harvesterUuid);
        String path = apiClient.expandPath("/harvesters/{harvesterUuid}/assign", uriVariables);

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "source", source));

        final String[] accepts = { 
            "application/json"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] { "basicAuth" };

        ParameterizedTypeReference<String> returnType = new ParameterizedTypeReference<String>() {};
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
}
