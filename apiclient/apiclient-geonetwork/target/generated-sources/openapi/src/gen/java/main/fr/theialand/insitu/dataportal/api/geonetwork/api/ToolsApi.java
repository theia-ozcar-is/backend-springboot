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
@Component("fr.theialand.insitu.dataportal.api.geonetwork.api.ToolsApi")
public class ToolsApi {
    private ApiClient apiClient;

    public ToolsApi() {
        this(new ApiClient());
    }

    @Autowired
    public ToolsApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Add or update database translations.
     * 
     * <p><b>201</b> - Created
     * <p><b>0</b> - default response
     * @param key  (required)
     * @param requestBody  (required)
     * @param replace  (optional)
     * @return String
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public String addTranslations(String key, Map<String, String> requestBody, Boolean replace) throws RestClientException {
        return addTranslationsWithHttpInfo(key, requestBody, replace).getBody();
    }

    /**
     * Add or update database translations.
     * 
     * <p><b>201</b> - Created
     * <p><b>0</b> - default response
     * @param key  (required)
     * @param requestBody  (required)
     * @param replace  (optional)
     * @return ResponseEntity&lt;String&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<String> addTranslationsWithHttpInfo(String key, Map<String, String> requestBody, Boolean replace) throws RestClientException {
        Object postBody = requestBody;
        
        // verify the required parameter 'key' is set
        if (key == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'key' when calling addTranslations");
        }
        
        // verify the required parameter 'requestBody' is set
        if (requestBody == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'requestBody' when calling addTranslations");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("key", key);
        String path = apiClient.expandPath("/tools/i18n/db/translations/{key}", uriVariables);

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "replace", replace));

        final String[] accepts = { 
            "application/json"
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
    /**
     * Generate a SLD with a new filter
     * Get the currend SLD for the requested layers, add new filters in, save the SLD and return the new SLD URL.
     * <p><b>201</b> - Created
     * <p><b>0</b> - default response
     * @param url The WMS server URL (required)
     * @param layers The layers (required)
     * @param filters The filters in JSON (required)
     * @return String
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public String buildSLD(String url, String layers, String filters) throws RestClientException {
        return buildSLDWithHttpInfo(url, layers, filters).getBody();
    }

    /**
     * Generate a SLD with a new filter
     * Get the currend SLD for the requested layers, add new filters in, save the SLD and return the new SLD URL.
     * <p><b>201</b> - Created
     * <p><b>0</b> - default response
     * @param url The WMS server URL (required)
     * @param layers The layers (required)
     * @param filters The filters in JSON (required)
     * @return ResponseEntity&lt;String&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<String> buildSLDWithHttpInfo(String url, String layers, String filters) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'url' is set
        if (url == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'url' when calling buildSLD");
        }
        
        // verify the required parameter 'layers' is set
        if (layers == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'layers' when calling buildSLD");
        }
        
        // verify the required parameter 'filters' is set
        if (filters == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'filters' when calling buildSLD");
        }
        
        String path = apiClient.expandPath("/tools/ogc/sld", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "url", url));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "layers", layers));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "filters", filters));

        final String[] accepts = { 
            "application/json", "text/plain"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] { "basicAuth" };

        ParameterizedTypeReference<String> returnType = new ParameterizedTypeReference<String>() {};
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Call a migration step
     * 
     * <p><b>201</b> - Created
     * <p><b>0</b> - default response
     * @param stepName Class name to execute corresponding to a migration step. See DatabaseMigrationTask. (required)
     * @return String
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public String callStep(String stepName) throws RestClientException {
        return callStepWithHttpInfo(stepName).getBody();
    }

    /**
     * Call a migration step
     * 
     * <p><b>201</b> - Created
     * <p><b>0</b> - default response
     * @param stepName Class name to execute corresponding to a migration step. See DatabaseMigrationTask. (required)
     * @return ResponseEntity&lt;String&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<String> callStepWithHttpInfo(String stepName) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'stepName' is set
        if (stepName == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'stepName' when calling callStep");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("stepName", stepName);
        String path = apiClient.expandPath("/tools/migration/steps/{stepName}", uriVariables);

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        final String[] accepts = { 
            "application/json", "text/plain"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] { "basicAuth" };

        ParameterizedTypeReference<String> returnType = new ParameterizedTypeReference<String>() {};
        return apiClient.invokeAPI(path, HttpMethod.PUT, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Delete database translations.
     * 
     * <p><b>200</b> - OK
     * <p><b>0</b> - default response
     * @param key  (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void deleteTranslations(String key) throws RestClientException {
        deleteTranslationsWithHttpInfo(key);
    }

    /**
     * Delete database translations.
     * 
     * <p><b>200</b> - OK
     * <p><b>0</b> - default response
     * @param key  (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> deleteTranslationsWithHttpInfo(String key) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'key' is set
        if (key == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'key' when calling deleteTranslations");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("key", key);
        String path = apiClient.expandPath("/tools/i18n/db/translations/{key}", uriVariables);

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
        return apiClient.invokeAPI(path, HttpMethod.DELETE, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Remove all SLD files
     * Clean all SLD generated previously
     * <p><b>200</b> - OK
     * <p><b>0</b> - default response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void deteleSLD() throws RestClientException {
        deteleSLDWithHttpInfo();
    }

    /**
     * Remove all SLD files
     * Clean all SLD generated previously
     * <p><b>200</b> - OK
     * <p><b>0</b> - default response
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> deteleSLDWithHttpInfo() throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/tools/ogc/sld", Collections.<String, Object>emptyMap());

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
        return apiClient.invokeAPI(path, HttpMethod.DELETE, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * List database translations (used to overrides client application translations).
     * 
     * <p><b>200</b> - OK
     * <p><b>0</b> - default response
     * @return Map&lt;String, String&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Map<String, String> getDbTranslations() throws RestClientException {
        return getDbTranslationsWithHttpInfo().getBody();
    }

    /**
     * List database translations (used to overrides client application translations).
     * 
     * <p><b>200</b> - OK
     * <p><b>0</b> - default response
     * @return ResponseEntity&lt;Map&lt;String, String&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Map<String, String>> getDbTranslationsWithHttpInfo() throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/tools/i18n/db/translations", Collections.<String, Object>emptyMap());

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

        ParameterizedTypeReference<Map<String, String>> returnType = new ParameterizedTypeReference<Map<String, String>>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Get the list of SLD available
     * 
     * <p><b>200</b> - OK
     * <p><b>0</b> - default response
     * @return List&lt;String&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<String> getSLD() throws RestClientException {
        return getSLDWithHttpInfo().getBody();
    }

    /**
     * Get the list of SLD available
     * 
     * <p><b>200</b> - OK
     * <p><b>0</b> - default response
     * @return ResponseEntity&lt;List&lt;String&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<String>> getSLDWithHttpInfo() throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/tools/ogc/sld", Collections.<String, Object>emptyMap());

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
    /**
     * List translations for database description table
     * 
     * <p><b>200</b> - OK
     * <p><b>0</b> - default response
     * @param type  (optional, default to new ArrayList&lt;&gt;())
     * @return Map&lt;String, String&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Map<String, String> getTranslations(List<String> type) throws RestClientException {
        return getTranslationsWithHttpInfo(type).getBody();
    }

    /**
     * List translations for database description table
     * 
     * <p><b>200</b> - OK
     * <p><b>0</b> - default response
     * @param type  (optional, default to new ArrayList&lt;&gt;())
     * @return ResponseEntity&lt;Map&lt;String, String&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Map<String, String>> getTranslationsWithHttpInfo(List<String> type) throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/tools/i18n/db", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        queryParams.putAll(apiClient.parameterToMultiValueMap(ApiClient.CollectionFormat.valueOf("multi".toUpperCase(Locale.ROOT)), "type", type));

        final String[] accepts = { 
            "application/json"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] { "basicAuth" };

        ParameterizedTypeReference<Map<String, String>> returnType = new ParameterizedTypeReference<Map<String, String>>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
}
