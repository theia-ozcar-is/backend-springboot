package fr.theialand.insitu.dataportal.api.geonetwork.api;

import fr.theialand.insitu.dataportal.api.geonetwork.handler.ApiClient;

import fr.theialand.insitu.dataportal.api.geonetwork.model.Category;
import fr.theialand.insitu.dataportal.api.geonetwork.model.ListRegionsResponse;

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
@Component("fr.theialand.insitu.dataportal.api.geonetwork.api.RegionsApi")
public class RegionsApi {
    private ApiClient apiClient;

    public RegionsApi() {
        this(new ApiClient());
    }

    @Autowired
    public RegionsApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Get list of region types
     * 
     * <p><b>200</b> - List of region types.
     * <p><b>0</b> - default response
     * @return List&lt;Category&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<Category> getRegionTypes() throws RestClientException {
        return getRegionTypesWithHttpInfo().getBody();
    }

    /**
     * Get list of region types
     * 
     * <p><b>200</b> - List of region types.
     * <p><b>0</b> - default response
     * @return ResponseEntity&lt;List&lt;Category&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<Category>> getRegionTypesWithHttpInfo() throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/regions/types", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        final String[] accepts = { 
            "application/json"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] { "basicAuth" };

        ParameterizedTypeReference<List<Category>> returnType = new ParameterizedTypeReference<List<Category>>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Get list of regions
     * 
     * <p><b>200</b> - List of regions.
     * <p><b>0</b> - default response
     * @param label  (optional)
     * @param categoryId  (optional)
     * @param maxRecords  (optional, default to -1)
     * @return ListRegionsResponse
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ListRegionsResponse getRegions(String label, String categoryId, Integer maxRecords) throws RestClientException {
        return getRegionsWithHttpInfo(label, categoryId, maxRecords).getBody();
    }

    /**
     * Get list of regions
     * 
     * <p><b>200</b> - List of regions.
     * <p><b>0</b> - default response
     * @param label  (optional)
     * @param categoryId  (optional)
     * @param maxRecords  (optional, default to -1)
     * @return ResponseEntity&lt;ListRegionsResponse&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ListRegionsResponse> getRegionsWithHttpInfo(String label, String categoryId, Integer maxRecords) throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/regions", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "label", label));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "categoryId", categoryId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "maxRecords", maxRecords));

        final String[] accepts = { 
            "application/json", "application/xml", 
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] { "basicAuth" };

        ParameterizedTypeReference<ListRegionsResponse> returnType = new ParameterizedTypeReference<ListRegionsResponse>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
}
