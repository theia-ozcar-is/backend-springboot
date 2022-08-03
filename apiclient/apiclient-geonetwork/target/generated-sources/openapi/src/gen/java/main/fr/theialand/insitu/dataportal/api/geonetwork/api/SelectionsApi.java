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
@Component("fr.theialand.insitu.dataportal.api.geonetwork.api.SelectionsApi")
public class SelectionsApi {
    private ApiClient apiClient;

    public SelectionsApi() {
        this(new ApiClient());
    }

    @Autowired
    public SelectionsApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Select one or more items
     * 
     * <p><b>200</b> - OK
     * <p><b>0</b> - default response
     * @param bucket Bucket name (required)
     * @param uuid One or more record UUIDs. If null, select all in current search if bucket name is &#39;metadata&#39; (TODO: remove this limitation?). (optional, default to new ArrayList&lt;&gt;())
     * @return Integer
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Integer add(String bucket, List<String> uuid) throws RestClientException {
        return addWithHttpInfo(bucket, uuid).getBody();
    }

    /**
     * Select one or more items
     * 
     * <p><b>200</b> - OK
     * <p><b>0</b> - default response
     * @param bucket Bucket name (required)
     * @param uuid One or more record UUIDs. If null, select all in current search if bucket name is &#39;metadata&#39; (TODO: remove this limitation?). (optional, default to new ArrayList&lt;&gt;())
     * @return ResponseEntity&lt;Integer&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Integer> addWithHttpInfo(String bucket, List<String> uuid) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'bucket' is set
        if (bucket == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'bucket' when calling add");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("bucket", bucket);
        String path = apiClient.expandPath("/selections/{bucket}", uriVariables);

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        queryParams.putAll(apiClient.parameterToMultiValueMap(ApiClient.CollectionFormat.valueOf("multi".toUpperCase(Locale.ROOT)), "uuid", uuid));

        final String[] accepts = { 
            "application/json"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] { "basicAuth" };

        ParameterizedTypeReference<Integer> returnType = new ParameterizedTypeReference<Integer>() {};
        return apiClient.invokeAPI(path, HttpMethod.PUT, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Clear selection or remove items
     * 
     * <p><b>200</b> - OK
     * <p><b>0</b> - default response
     * @param bucket Selection bucket name (required)
     * @param uuid One or more record UUIDs (optional, default to new ArrayList&lt;&gt;())
     * @return Integer
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Integer clear(String bucket, List<String> uuid) throws RestClientException {
        return clearWithHttpInfo(bucket, uuid).getBody();
    }

    /**
     * Clear selection or remove items
     * 
     * <p><b>200</b> - OK
     * <p><b>0</b> - default response
     * @param bucket Selection bucket name (required)
     * @param uuid One or more record UUIDs (optional, default to new ArrayList&lt;&gt;())
     * @return ResponseEntity&lt;Integer&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Integer> clearWithHttpInfo(String bucket, List<String> uuid) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'bucket' is set
        if (bucket == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'bucket' when calling clear");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("bucket", bucket);
        String path = apiClient.expandPath("/selections/{bucket}", uriVariables);

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        queryParams.putAll(apiClient.parameterToMultiValueMap(ApiClient.CollectionFormat.valueOf("multi".toUpperCase(Locale.ROOT)), "uuid", uuid));

        final String[] accepts = { 
            "application/json"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] { "basicAuth" };

        ParameterizedTypeReference<Integer> returnType = new ParameterizedTypeReference<Integer>() {};
        return apiClient.invokeAPI(path, HttpMethod.DELETE, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Get current selection
     * 
     * <p><b>200</b> - OK
     * <p><b>0</b> - default response
     * @param bucket Bucket name (required)
     * @return List&lt;String&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<String> get(String bucket) throws RestClientException {
        return getWithHttpInfo(bucket).getBody();
    }

    /**
     * Get current selection
     * 
     * <p><b>200</b> - OK
     * <p><b>0</b> - default response
     * @param bucket Bucket name (required)
     * @return ResponseEntity&lt;List&lt;String&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<String>> getWithHttpInfo(String bucket) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'bucket' is set
        if (bucket == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'bucket' when calling get");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("bucket", bucket);
        String path = apiClient.expandPath("/selections/{bucket}", uriVariables);

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

        ParameterizedTypeReference<List<String>> returnType = new ParameterizedTypeReference<List<String>>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
}
