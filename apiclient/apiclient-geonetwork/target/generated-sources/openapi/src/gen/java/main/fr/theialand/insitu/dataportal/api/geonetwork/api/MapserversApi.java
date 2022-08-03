package fr.theialand.insitu.dataportal.api.geonetwork.api;

import fr.theialand.insitu.dataportal.api.geonetwork.handler.ApiClient;

import fr.theialand.insitu.dataportal.api.geonetwork.model.AnonymousMapserver;
import fr.theialand.insitu.dataportal.api.geonetwork.model.MapServer;

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
@Component("fr.theialand.insitu.dataportal.api.geonetwork.api.MapserversApi")
public class MapserversApi {
    private ApiClient apiClient;

    public MapserversApi() {
        this(new ApiClient());
    }

    @Autowired
    public MapserversApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Add a mapserver
     * Return the id of the newly created mapserver.
     * <p><b>201</b> - Mapserver created.
     * <p><b>400</b> - Bad parameters.
     * <p><b>403</b> - Operation not allowed. Only Reviewvers can access it.
     * <p><b>0</b> - default response
     * @param mapServer Mapserver details (required)
     * @return Integer
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Integer addMapserver(MapServer mapServer) throws RestClientException {
        return addMapserverWithHttpInfo(mapServer).getBody();
    }

    /**
     * Add a mapserver
     * Return the id of the newly created mapserver.
     * <p><b>201</b> - Mapserver created.
     * <p><b>400</b> - Bad parameters.
     * <p><b>403</b> - Operation not allowed. Only Reviewvers can access it.
     * <p><b>0</b> - default response
     * @param mapServer Mapserver details (required)
     * @return ResponseEntity&lt;Integer&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Integer> addMapserverWithHttpInfo(MapServer mapServer) throws RestClientException {
        Object postBody = mapServer;
        
        // verify the required parameter 'mapServer' is set
        if (mapServer == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'mapServer' when calling addMapserver");
        }
        
        String path = apiClient.expandPath("/mapservers", Collections.<String, Object>emptyMap());

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
     * Remove a metadata mapserver resource
     * 
     * <p><b>403</b> - Operation not allowed. Only Editors can access it.
     * <p><b>0</b> - default response
     * @param mapserverId Mapserver identifier (required)
     * @param metadataUuid Record UUID. (required)
     * @param resource Resource name (could be a file or a db connection) (required)
     * @param metadataTitle Metadata title (optional, default to &quot;&quot;)
     * @param metadataAbstract Metadata abstract (optional, default to &quot;&quot;)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void deleteMapserverResource(String mapserverId, String metadataUuid, String resource, String metadataTitle, String metadataAbstract) throws RestClientException {
        deleteMapserverResourceWithHttpInfo(mapserverId, metadataUuid, resource, metadataTitle, metadataAbstract);
    }

    /**
     * Remove a metadata mapserver resource
     * 
     * <p><b>403</b> - Operation not allowed. Only Editors can access it.
     * <p><b>0</b> - default response
     * @param mapserverId Mapserver identifier (required)
     * @param metadataUuid Record UUID. (required)
     * @param resource Resource name (could be a file or a db connection) (required)
     * @param metadataTitle Metadata title (optional, default to &quot;&quot;)
     * @param metadataAbstract Metadata abstract (optional, default to &quot;&quot;)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> deleteMapserverResourceWithHttpInfo(String mapserverId, String metadataUuid, String resource, String metadataTitle, String metadataAbstract) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'mapserverId' is set
        if (mapserverId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'mapserverId' when calling deleteMapserverResource");
        }
        
        // verify the required parameter 'metadataUuid' is set
        if (metadataUuid == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'metadataUuid' when calling deleteMapserverResource");
        }
        
        // verify the required parameter 'resource' is set
        if (resource == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'resource' when calling deleteMapserverResource");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("mapserverId", mapserverId);
        uriVariables.put("metadataUuid", metadataUuid);
        String path = apiClient.expandPath("/mapservers/{mapserverId}/records/{metadataUuid}", uriVariables);

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "resource", resource));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "metadataTitle", metadataTitle));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "metadataAbstract", metadataAbstract));

        final String[] accepts = { 
            "application/json", "text/plain"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] { "basicAuth" };

        ParameterizedTypeReference<Void> returnType = new ParameterizedTypeReference<Void>() {};
        return apiClient.invokeAPI(path, HttpMethod.DELETE, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Get a mapserver
     * 
     * <p><b>404</b> - Resource not found.
     * <p><b>403</b> - Operation not allowed. Only Editors can access it.
     * <p><b>0</b> - default response
     * @param mapserverId Mapserver identifier (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void getMapserver(String mapserverId) throws RestClientException {
        getMapserverWithHttpInfo(mapserverId);
    }

    /**
     * Get a mapserver
     * 
     * <p><b>404</b> - Resource not found.
     * <p><b>403</b> - Operation not allowed. Only Editors can access it.
     * <p><b>0</b> - default response
     * @param mapserverId Mapserver identifier (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> getMapserverWithHttpInfo(String mapserverId) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'mapserverId' is set
        if (mapserverId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'mapserverId' when calling getMapserver");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("mapserverId", mapserverId);
        String path = apiClient.expandPath("/mapservers/{mapserverId}", uriVariables);

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

        ParameterizedTypeReference<Void> returnType = new ParameterizedTypeReference<Void>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Check metadata mapserver resource is published 
     * 
     * <p><b>403</b> - Operation not allowed. Only Editors can access it.
     * <p><b>0</b> - default response
     * @param mapserverId Mapserver identifier (required)
     * @param metadataUuid Record UUID. (required)
     * @param resource Resource name (could be a file or a db connection) (required)
     * @param metadataTitle Metadata title (optional, default to &quot;&quot;)
     * @param metadataAbstract Metadata abstract (optional, default to &quot;&quot;)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void getMapserverResource(String mapserverId, String metadataUuid, String resource, String metadataTitle, String metadataAbstract) throws RestClientException {
        getMapserverResourceWithHttpInfo(mapserverId, metadataUuid, resource, metadataTitle, metadataAbstract);
    }

    /**
     * Check metadata mapserver resource is published 
     * 
     * <p><b>403</b> - Operation not allowed. Only Editors can access it.
     * <p><b>0</b> - default response
     * @param mapserverId Mapserver identifier (required)
     * @param metadataUuid Record UUID. (required)
     * @param resource Resource name (could be a file or a db connection) (required)
     * @param metadataTitle Metadata title (optional, default to &quot;&quot;)
     * @param metadataAbstract Metadata abstract (optional, default to &quot;&quot;)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> getMapserverResourceWithHttpInfo(String mapserverId, String metadataUuid, String resource, String metadataTitle, String metadataAbstract) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'mapserverId' is set
        if (mapserverId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'mapserverId' when calling getMapserverResource");
        }
        
        // verify the required parameter 'metadataUuid' is set
        if (metadataUuid == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'metadataUuid' when calling getMapserverResource");
        }
        
        // verify the required parameter 'resource' is set
        if (resource == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'resource' when calling getMapserverResource");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("mapserverId", mapserverId);
        uriVariables.put("metadataUuid", metadataUuid);
        String path = apiClient.expandPath("/mapservers/{mapserverId}/records/{metadataUuid}", uriVariables);

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "resource", resource));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "metadataTitle", metadataTitle));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "metadataAbstract", metadataAbstract));

        final String[] accepts = { 
            "application/json", "text/plain"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] { "basicAuth" };

        ParameterizedTypeReference<Void> returnType = new ParameterizedTypeReference<Void>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Get mapservers
     * Mapservers are used by the catalog to publish record attachments (eg. ZIP file with shape) or record associated resources (eg. database table, file on the local network) in a remote mapserver like GeoServer or MapServer. The catalog communicate with the mapserver using GeoServer REST API.
     * <p><b>403</b> - Operation not allowed. Only Editors can access it.
     * <p><b>0</b> - default response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void getMapservers() throws RestClientException {
        getMapserversWithHttpInfo();
    }

    /**
     * Get mapservers
     * Mapservers are used by the catalog to publish record attachments (eg. ZIP file with shape) or record associated resources (eg. database table, file on the local network) in a remote mapserver like GeoServer or MapServer. The catalog communicate with the mapserver using GeoServer REST API.
     * <p><b>403</b> - Operation not allowed. Only Editors can access it.
     * <p><b>0</b> - default response
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> getMapserversWithHttpInfo() throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/mapservers", Collections.<String, Object>emptyMap());

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

        ParameterizedTypeReference<Void> returnType = new ParameterizedTypeReference<Void>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Publish a metadata resource in a mapserver
     * 
     * <p><b>403</b> - Operation not allowed. Only Editors can access it.
     * <p><b>0</b> - default response
     * @param mapserverId Mapserver identifier (required)
     * @param metadataUuid Record UUID. (required)
     * @param resource Resource name (could be a file or a db connection) (required)
     * @param metadataTitle Metadata title (optional, default to &quot;&quot;)
     * @param metadataAbstract Metadata abstract (optional, default to &quot;&quot;)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void publishMapserverResource(String mapserverId, String metadataUuid, String resource, String metadataTitle, String metadataAbstract) throws RestClientException {
        publishMapserverResourceWithHttpInfo(mapserverId, metadataUuid, resource, metadataTitle, metadataAbstract);
    }

    /**
     * Publish a metadata resource in a mapserver
     * 
     * <p><b>403</b> - Operation not allowed. Only Editors can access it.
     * <p><b>0</b> - default response
     * @param mapserverId Mapserver identifier (required)
     * @param metadataUuid Record UUID. (required)
     * @param resource Resource name (could be a file or a db connection) (required)
     * @param metadataTitle Metadata title (optional, default to &quot;&quot;)
     * @param metadataAbstract Metadata abstract (optional, default to &quot;&quot;)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> publishMapserverResourceWithHttpInfo(String mapserverId, String metadataUuid, String resource, String metadataTitle, String metadataAbstract) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'mapserverId' is set
        if (mapserverId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'mapserverId' when calling publishMapserverResource");
        }
        
        // verify the required parameter 'metadataUuid' is set
        if (metadataUuid == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'metadataUuid' when calling publishMapserverResource");
        }
        
        // verify the required parameter 'resource' is set
        if (resource == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'resource' when calling publishMapserverResource");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("mapserverId", mapserverId);
        uriVariables.put("metadataUuid", metadataUuid);
        String path = apiClient.expandPath("/mapservers/{mapserverId}/records/{metadataUuid}", uriVariables);

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "resource", resource));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "metadataTitle", metadataTitle));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "metadataAbstract", metadataAbstract));

        final String[] accepts = { 
            "application/json", "text/plain"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] { "basicAuth" };

        ParameterizedTypeReference<Void> returnType = new ParameterizedTypeReference<Void>() {};
        return apiClient.invokeAPI(path, HttpMethod.PUT, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Update a mapserver
     * 
     * <p><b>404</b> - Resource not found.
     * <p><b>204</b> - Mapserver updated.
     * <p><b>403</b> - Operation not allowed. Only Reviewvers can access it.
     * <p><b>0</b> - default response
     * @param mapserverId Mapserver identifier (required)
     * @param mapServer Mapserver details (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void updateMapserver(Integer mapserverId, MapServer mapServer) throws RestClientException {
        updateMapserverWithHttpInfo(mapserverId, mapServer);
    }

    /**
     * Update a mapserver
     * 
     * <p><b>404</b> - Resource not found.
     * <p><b>204</b> - Mapserver updated.
     * <p><b>403</b> - Operation not allowed. Only Reviewvers can access it.
     * <p><b>0</b> - default response
     * @param mapserverId Mapserver identifier (required)
     * @param mapServer Mapserver details (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> updateMapserverWithHttpInfo(Integer mapserverId, MapServer mapServer) throws RestClientException {
        Object postBody = mapServer;
        
        // verify the required parameter 'mapserverId' is set
        if (mapserverId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'mapserverId' when calling updateMapserver");
        }
        
        // verify the required parameter 'mapServer' is set
        if (mapServer == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'mapServer' when calling updateMapserver");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("mapserverId", mapserverId);
        String path = apiClient.expandPath("/mapservers/{mapserverId}", uriVariables);

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
