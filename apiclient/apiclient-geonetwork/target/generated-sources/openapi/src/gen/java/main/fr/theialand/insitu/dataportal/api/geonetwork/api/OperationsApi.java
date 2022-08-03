package fr.theialand.insitu.dataportal.api.geonetwork.api;

import fr.theialand.insitu.dataportal.api.geonetwork.handler.ApiClient;

import fr.theialand.insitu.dataportal.api.geonetwork.model.Operation;

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
@Component("fr.theialand.insitu.dataportal.api.geonetwork.api.OperationsApi")
public class OperationsApi {
    private ApiClient apiClient;

    public OperationsApi() {
        this(new ApiClient());
    }

    @Autowired
    public OperationsApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Get operations
     * Return the list of operations. Operations are used to define authorization per group. Extending the list of default operations (ie. view, dynamic, download, editing, notify, featured) might be feasible but is experimental.&lt;br/&gt; Featured is not really used anymore (was used in past version for home page highlights).
     * <p><b>200</b> - OK
     * <p><b>0</b> - default response
     * @return List&lt;Operation&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<Operation> getOperations() throws RestClientException {
        return getOperationsWithHttpInfo().getBody();
    }

    /**
     * Get operations
     * Return the list of operations. Operations are used to define authorization per group. Extending the list of default operations (ie. view, dynamic, download, editing, notify, featured) might be feasible but is experimental.&lt;br/&gt; Featured is not really used anymore (was used in past version for home page highlights).
     * <p><b>200</b> - OK
     * <p><b>0</b> - default response
     * @return ResponseEntity&lt;List&lt;Operation&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<Operation>> getOperationsWithHttpInfo() throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/operations", Collections.<String, Object>emptyMap());

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

        ParameterizedTypeReference<List<Operation>> returnType = new ParameterizedTypeReference<List<Operation>>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
}
