package fr.theialand.insitu.dataportal.api.geonetwork.api;

import fr.theialand.insitu.dataportal.api.geonetwork.handler.ApiClient;

import fr.theialand.insitu.dataportal.api.geonetwork.model.MetadataIdentifierTemplate;

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
@Component("fr.theialand.insitu.dataportal.api.geonetwork.api.IdentifiersApi")
public class IdentifiersApi {
    private ApiClient apiClient;

    public IdentifiersApi() {
        this(new ApiClient());
    }

    @Autowired
    public IdentifiersApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Add an identifier template
     * 
     * <p><b>201</b> - Identifier template created.
     * <p><b>403</b> - Operation not allowed. Only Editor can access it.
     * <p><b>0</b> - default response
     * @param metadataIdentifierTemplate Identifier template details (required)
     * @return Integer
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Integer addIdentifier(MetadataIdentifierTemplate metadataIdentifierTemplate) throws RestClientException {
        return addIdentifierWithHttpInfo(metadataIdentifierTemplate).getBody();
    }

    /**
     * Add an identifier template
     * 
     * <p><b>201</b> - Identifier template created.
     * <p><b>403</b> - Operation not allowed. Only Editor can access it.
     * <p><b>0</b> - default response
     * @param metadataIdentifierTemplate Identifier template details (required)
     * @return ResponseEntity&lt;Integer&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Integer> addIdentifierWithHttpInfo(MetadataIdentifierTemplate metadataIdentifierTemplate) throws RestClientException {
        Object postBody = metadataIdentifierTemplate;
        
        // verify the required parameter 'metadataIdentifierTemplate' is set
        if (metadataIdentifierTemplate == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'metadataIdentifierTemplate' when calling addIdentifier");
        }
        
        String path = apiClient.expandPath("/identifiers", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        final String[] accepts = { 
            "application/json"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { 
            "application/json"
        };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] { "basicAuth" };

        ParameterizedTypeReference<Integer> returnType = new ParameterizedTypeReference<Integer>() {};
        return apiClient.invokeAPI(path, HttpMethod.PUT, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Get identifier templates
     * Identifier templates are used to create record UUIDs havind a particular structure. The template will be used when user creates a new record. The template identifier to use is defined in the administration &gt; settings.
     * <p><b>200</b> - List of identifier templates.
     * <p><b>403</b> - Operation not allowed. Only Editor can access it.
     * <p><b>0</b> - default response
     * @param userDefinedOnly Only user defined ones (optional, default to false)
     * @return List&lt;MetadataIdentifierTemplate&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<MetadataIdentifierTemplate> getIdentifiers(Boolean userDefinedOnly) throws RestClientException {
        return getIdentifiersWithHttpInfo(userDefinedOnly).getBody();
    }

    /**
     * Get identifier templates
     * Identifier templates are used to create record UUIDs havind a particular structure. The template will be used when user creates a new record. The template identifier to use is defined in the administration &gt; settings.
     * <p><b>200</b> - List of identifier templates.
     * <p><b>403</b> - Operation not allowed. Only Editor can access it.
     * <p><b>0</b> - default response
     * @param userDefinedOnly Only user defined ones (optional, default to false)
     * @return ResponseEntity&lt;List&lt;MetadataIdentifierTemplate&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<MetadataIdentifierTemplate>> getIdentifiersWithHttpInfo(Boolean userDefinedOnly) throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/identifiers", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "userDefinedOnly", userDefinedOnly));

        final String[] accepts = { 
            "application/json"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] { "basicAuth" };

        ParameterizedTypeReference<List<MetadataIdentifierTemplate>> returnType = new ParameterizedTypeReference<List<MetadataIdentifierTemplate>>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Update an identifier template
     * 
     * <p><b>404</b> - Resource not found.
     * <p><b>403</b> - Operation not allowed. Only Editor can access it.
     * <p><b>204</b> - Identifier template updated.
     * <p><b>0</b> - default response
     * @param identifier Identifier template identifier (required)
     * @param metadataIdentifierTemplate Identifier template details (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void updateIdentifier(Integer identifier, MetadataIdentifierTemplate metadataIdentifierTemplate) throws RestClientException {
        updateIdentifierWithHttpInfo(identifier, metadataIdentifierTemplate);
    }

    /**
     * Update an identifier template
     * 
     * <p><b>404</b> - Resource not found.
     * <p><b>403</b> - Operation not allowed. Only Editor can access it.
     * <p><b>204</b> - Identifier template updated.
     * <p><b>0</b> - default response
     * @param identifier Identifier template identifier (required)
     * @param metadataIdentifierTemplate Identifier template details (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> updateIdentifierWithHttpInfo(Integer identifier, MetadataIdentifierTemplate metadataIdentifierTemplate) throws RestClientException {
        Object postBody = metadataIdentifierTemplate;
        
        // verify the required parameter 'identifier' is set
        if (identifier == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'identifier' when calling updateIdentifier");
        }
        
        // verify the required parameter 'metadataIdentifierTemplate' is set
        if (metadataIdentifierTemplate == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'metadataIdentifierTemplate' when calling updateIdentifier");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("identifier", identifier);
        String path = apiClient.expandPath("/identifiers/{identifier}", uriVariables);

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        final String[] accepts = { 
            "application/json"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { 
            "application/json"
        };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] { "basicAuth" };

        ParameterizedTypeReference<Void> returnType = new ParameterizedTypeReference<Void>() {};
        return apiClient.invokeAPI(path, HttpMethod.PUT, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
}
