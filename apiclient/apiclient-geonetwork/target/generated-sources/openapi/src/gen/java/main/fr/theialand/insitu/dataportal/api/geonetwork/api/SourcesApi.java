package fr.theialand.insitu.dataportal.api.geonetwork.api;

import fr.theialand.insitu.dataportal.api.geonetwork.handler.ApiClient;

import fr.theialand.insitu.dataportal.api.geonetwork.model.Source;

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
@Component("fr.theialand.insitu.dataportal.api.geonetwork.api.SourcesApi")
public class SourcesApi {
    private ApiClient apiClient;

    public SourcesApi() {
        this(new ApiClient());
    }

    @Autowired
    public SourcesApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Get portal list
     * List all subportal available.
     * <p><b>200</b> - List of portals.
     * <p><b>0</b> - default response
     * @param group Group owner of the source (only applies to subportal). (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void getSources1(Integer group) throws RestClientException {
        getSources1WithHttpInfo(group);
    }

    /**
     * Get portal list
     * List all subportal available.
     * <p><b>200</b> - List of portals.
     * <p><b>0</b> - default response
     * @param group Group owner of the source (only applies to subportal). (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> getSources1WithHttpInfo(Integer group) throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/sources", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "group", group));

        final String[] accepts = { 
            "application/json"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] { "basicAuth" };

        ParameterizedTypeReference<Void> returnType = new ParameterizedTypeReference<Void>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Get all sources by type
     * Sources are the local catalogue, subportal, external catalogue (when importing MEF files) or harvesters.
     * <p><b>200</b> - OK
     * <p><b>0</b> - default response
     * @param type  (required)
     * @return List&lt;Source&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<Source> getSourcesByType(String type) throws RestClientException {
        return getSourcesByTypeWithHttpInfo(type).getBody();
    }

    /**
     * Get all sources by type
     * Sources are the local catalogue, subportal, external catalogue (when importing MEF files) or harvesters.
     * <p><b>200</b> - OK
     * <p><b>0</b> - default response
     * @param type  (required)
     * @return ResponseEntity&lt;List&lt;Source&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<Source>> getSourcesByTypeWithHttpInfo(String type) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'type' is set
        if (type == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'type' when calling getSourcesByType");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("type", type);
        String path = apiClient.expandPath("/sources/{type}", uriVariables);

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

        ParameterizedTypeReference<List<Source>> returnType = new ParameterizedTypeReference<List<Source>>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
}
