package fr.theialand.insitu.dataportal.api.geonetwork.api;

import fr.theialand.insitu.dataportal.api.geonetwork.handler.ApiClient;

import fr.theialand.insitu.dataportal.api.geonetwork.model.MetadataCategory;

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
@Component("fr.theialand.insitu.dataportal.api.geonetwork.api.TagsApi")
public class TagsApi {
    private ApiClient apiClient;

    public TagsApi() {
        this(new ApiClient());
    }

    @Autowired
    public TagsApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Get a tag
     * 
     * <p><b>200</b> - Tag details.
     * <p><b>0</b> - default response
     * @param tagIdentifier Tag identifier (required)
     * @return MetadataCategory
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public MetadataCategory getTag(Integer tagIdentifier) throws RestClientException {
        return getTagWithHttpInfo(tagIdentifier).getBody();
    }

    /**
     * Get a tag
     * 
     * <p><b>200</b> - Tag details.
     * <p><b>0</b> - default response
     * @param tagIdentifier Tag identifier (required)
     * @return ResponseEntity&lt;MetadataCategory&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<MetadataCategory> getTagWithHttpInfo(Integer tagIdentifier) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'tagIdentifier' is set
        if (tagIdentifier == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'tagIdentifier' when calling getTag");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("tagIdentifier", tagIdentifier);
        String path = apiClient.expandPath("/tags/{tagIdentifier}", uriVariables);

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

        ParameterizedTypeReference<MetadataCategory> returnType = new ParameterizedTypeReference<MetadataCategory>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Get tags
     * 
     * <p><b>200</b> - List of tags.
     * <p><b>0</b> - default response
     * @return List&lt;MetadataCategory&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<MetadataCategory> getTags() throws RestClientException {
        return getTagsWithHttpInfo().getBody();
    }

    /**
     * Get tags
     * 
     * <p><b>200</b> - List of tags.
     * <p><b>0</b> - default response
     * @return ResponseEntity&lt;List&lt;MetadataCategory&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<MetadataCategory>> getTagsWithHttpInfo() throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/tags", Collections.<String, Object>emptyMap());

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

        ParameterizedTypeReference<List<MetadataCategory>> returnType = new ParameterizedTypeReference<List<MetadataCategory>>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Update a tag
     * 
     * <p><b>403</b> - Operation not allowed. Only UserAdmins can access it.
     * <p><b>204</b> - Tag updated.
     * <p><b>0</b> - default response
     * @param tagIdentifier Tag identifier (required)
     * @param metadataCategory  (required)
     * @return String
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public String updateTag(Integer tagIdentifier, MetadataCategory metadataCategory) throws RestClientException {
        return updateTagWithHttpInfo(tagIdentifier, metadataCategory).getBody();
    }

    /**
     * Update a tag
     * 
     * <p><b>403</b> - Operation not allowed. Only UserAdmins can access it.
     * <p><b>204</b> - Tag updated.
     * <p><b>0</b> - default response
     * @param tagIdentifier Tag identifier (required)
     * @param metadataCategory  (required)
     * @return ResponseEntity&lt;String&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<String> updateTagWithHttpInfo(Integer tagIdentifier, MetadataCategory metadataCategory) throws RestClientException {
        Object postBody = metadataCategory;
        
        // verify the required parameter 'tagIdentifier' is set
        if (tagIdentifier == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'tagIdentifier' when calling updateTag");
        }
        
        // verify the required parameter 'metadataCategory' is set
        if (metadataCategory == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'metadataCategory' when calling updateTag");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("tagIdentifier", tagIdentifier);
        String path = apiClient.expandPath("/tags/{tagIdentifier}", uriVariables);

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        final String[] accepts = { 
            "application/json", "*/*"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { 
            "application/json"
        };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] { "basicAuth" };

        ParameterizedTypeReference<String> returnType = new ParameterizedTypeReference<String>() {};
        return apiClient.invokeAPI(path, HttpMethod.PUT, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
}
