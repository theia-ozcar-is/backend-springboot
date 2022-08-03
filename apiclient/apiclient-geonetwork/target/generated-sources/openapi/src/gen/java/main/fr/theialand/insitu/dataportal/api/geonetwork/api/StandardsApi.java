package fr.theialand.insitu.dataportal.api.geonetwork.api;

import fr.theialand.insitu.dataportal.api.geonetwork.handler.ApiClient;

import fr.theialand.insitu.dataportal.api.geonetwork.model.BatchEditing;
import fr.theialand.insitu.dataportal.api.geonetwork.model.Codelist;
import fr.theialand.insitu.dataportal.api.geonetwork.model.Element;
import fr.theialand.insitu.dataportal.api.geonetwork.model.MetadataSchema;

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
@Component("fr.theialand.insitu.dataportal.api.geonetwork.api.StandardsApi")
public class StandardsApi {
    private ApiClient apiClient;

    public StandardsApi() {
        this(new ApiClient());
    }

    @Autowired
    public StandardsApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Get batch editor configuration for a standard
     * 
     * <p><b>200</b> - OK
     * <p><b>0</b> - default response
     * @param schema Schema identifier (required)
     * @return Map&lt;String, BatchEditing&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Map<String, BatchEditing> getConfiguration(String schema) throws RestClientException {
        return getConfigurationWithHttpInfo(schema).getBody();
    }

    /**
     * Get batch editor configuration for a standard
     * 
     * <p><b>200</b> - OK
     * <p><b>0</b> - default response
     * @param schema Schema identifier (required)
     * @return ResponseEntity&lt;Map&lt;String, BatchEditing&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Map<String, BatchEditing>> getConfigurationWithHttpInfo(String schema) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'schema' is set
        if (schema == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'schema' when calling getConfiguration");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("schema", schema);
        String path = apiClient.expandPath("/standards/{schema}/batchconfiguration", uriVariables);

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

        ParameterizedTypeReference<Map<String, BatchEditing>> returnType = new ParameterizedTypeReference<Map<String, BatchEditing>>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Get batch editor configuration for standards
     * 
     * <p><b>200</b> - Batch editor configuration.
     * <p><b>0</b> - default response
     * @param schema Schema identifiers (optional, default to new ArrayList&lt;&gt;())
     * @return Map&lt;String, BatchEditing&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Map<String, BatchEditing> getConfigurations(List<String> schema) throws RestClientException {
        return getConfigurationsWithHttpInfo(schema).getBody();
    }

    /**
     * Get batch editor configuration for standards
     * 
     * <p><b>200</b> - Batch editor configuration.
     * <p><b>0</b> - default response
     * @param schema Schema identifiers (optional, default to new ArrayList&lt;&gt;())
     * @return ResponseEntity&lt;Map&lt;String, BatchEditing&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Map<String, BatchEditing>> getConfigurationsWithHttpInfo(List<String> schema) throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/standards/batchconfiguration", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        queryParams.putAll(apiClient.parameterToMultiValueMap(ApiClient.CollectionFormat.valueOf("multi".toUpperCase(Locale.ROOT)), "schema", schema));

        final String[] accepts = { 
            "application/json"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] { "basicAuth" };

        ParameterizedTypeReference<Map<String, BatchEditing>> returnType = new ParameterizedTypeReference<Map<String, BatchEditing>>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Get editor associated resources panel configuration
     * 
     * <p><b>200</b> - OK
     * <p><b>0</b> - default response
     * @param schema Schema identifier (required)
     * @param name Configuration identifier (required)
     * @return String
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public String getEditorAssociatedPanelConfiguration(String schema, String name) throws RestClientException {
        return getEditorAssociatedPanelConfigurationWithHttpInfo(schema, name).getBody();
    }

    /**
     * Get editor associated resources panel configuration
     * 
     * <p><b>200</b> - OK
     * <p><b>0</b> - default response
     * @param schema Schema identifier (required)
     * @param name Configuration identifier (required)
     * @return ResponseEntity&lt;String&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<String> getEditorAssociatedPanelConfigurationWithHttpInfo(String schema, String name) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'schema' is set
        if (schema == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'schema' when calling getEditorAssociatedPanelConfiguration");
        }
        
        // verify the required parameter 'name' is set
        if (name == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'name' when calling getEditorAssociatedPanelConfiguration");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("schema", schema);
        uriVariables.put("name", name);
        String path = apiClient.expandPath("/standards/{schema}/editor/associatedpanel/config/{name}.json", uriVariables);

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

        ParameterizedTypeReference<String> returnType = new ParameterizedTypeReference<String>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Get descriptor details
     * 
     * <p><b>200</b> - OK
     * <p><b>0</b> - default response
     * @param schema Schema identifier (required)
     * @param element Descriptor name (required)
     * @param parent  (optional)
     * @param displayIf  (optional)
     * @param xpath  (optional)
     * @param isoType  (optional)
     * @return Element
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Element getElementDetails(String schema, String element, String parent, String displayIf, String xpath, String isoType) throws RestClientException {
        return getElementDetailsWithHttpInfo(schema, element, parent, displayIf, xpath, isoType).getBody();
    }

    /**
     * Get descriptor details
     * 
     * <p><b>200</b> - OK
     * <p><b>0</b> - default response
     * @param schema Schema identifier (required)
     * @param element Descriptor name (required)
     * @param parent  (optional)
     * @param displayIf  (optional)
     * @param xpath  (optional)
     * @param isoType  (optional)
     * @return ResponseEntity&lt;Element&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Element> getElementDetailsWithHttpInfo(String schema, String element, String parent, String displayIf, String xpath, String isoType) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'schema' is set
        if (schema == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'schema' when calling getElementDetails");
        }
        
        // verify the required parameter 'element' is set
        if (element == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'element' when calling getElementDetails");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("schema", schema);
        uriVariables.put("element", element);
        String path = apiClient.expandPath("/standards/{schema}/descriptors/{element}/details", uriVariables);

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "parent", parent));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "displayIf", displayIf));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "xpath", xpath));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "isoType", isoType));

        final String[] accepts = { 
            "application/json", "application/xml", 
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] { "basicAuth" };

        ParameterizedTypeReference<Element> returnType = new ParameterizedTypeReference<Element>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Get codelist details
     * 
     * <p><b>200</b> - OK
     * <p><b>0</b> - default response
     * @param schema Schema identifier (required)
     * @param codelist Codelist element name or alias (required)
     * @param parent Parent name with namespace which may indicate a more precise label as defined in context attribute. (optional)
     * @param displayIf Display if condition as defined in the codelist.xml file. Allows to select a more precise codelist when more than one is defined for same name. (optional)
     * @param xpath XPath of the element to target which may indicate a more precise label as defined in context attribute. (optional)
     * @param isoType ISO type of the element to target which may indicate a more precise label as defined in context attribute. (Same as context. TODO: Deprecate ?) (optional)
     * @return Codelist
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Codelist getSchemaCodelistsWithDetails(String schema, String codelist, String parent, String displayIf, String xpath, String isoType) throws RestClientException {
        return getSchemaCodelistsWithDetailsWithHttpInfo(schema, codelist, parent, displayIf, xpath, isoType).getBody();
    }

    /**
     * Get codelist details
     * 
     * <p><b>200</b> - OK
     * <p><b>0</b> - default response
     * @param schema Schema identifier (required)
     * @param codelist Codelist element name or alias (required)
     * @param parent Parent name with namespace which may indicate a more precise label as defined in context attribute. (optional)
     * @param displayIf Display if condition as defined in the codelist.xml file. Allows to select a more precise codelist when more than one is defined for same name. (optional)
     * @param xpath XPath of the element to target which may indicate a more precise label as defined in context attribute. (optional)
     * @param isoType ISO type of the element to target which may indicate a more precise label as defined in context attribute. (Same as context. TODO: Deprecate ?) (optional)
     * @return ResponseEntity&lt;Codelist&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Codelist> getSchemaCodelistsWithDetailsWithHttpInfo(String schema, String codelist, String parent, String displayIf, String xpath, String isoType) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'schema' is set
        if (schema == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'schema' when calling getSchemaCodelistsWithDetails");
        }
        
        // verify the required parameter 'codelist' is set
        if (codelist == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'codelist' when calling getSchemaCodelistsWithDetails");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("schema", schema);
        uriVariables.put("codelist", codelist);
        String path = apiClient.expandPath("/standards/{schema}/codelists/{codelist}/details", uriVariables);

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "parent", parent));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "displayIf", displayIf));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "xpath", xpath));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "isoType", isoType));

        final String[] accepts = { 
            "application/json", "application/xml", 
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] { "basicAuth" };

        ParameterizedTypeReference<Codelist> returnType = new ParameterizedTypeReference<Codelist>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Get codelist translations
     * 
     * <p><b>200</b> - OK
     * <p><b>0</b> - default response
     * @param schema Schema identifier (required)
     * @param codelist Codelist element name or alias (required)
     * @param parent  (optional)
     * @param displayIf  (optional)
     * @param xpath  (optional)
     * @param isoType  (optional)
     * @return Map&lt;String, String&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Map<String, String> getSchemaTranslations(String schema, String codelist, String parent, String displayIf, String xpath, String isoType) throws RestClientException {
        return getSchemaTranslationsWithHttpInfo(schema, codelist, parent, displayIf, xpath, isoType).getBody();
    }

    /**
     * Get codelist translations
     * 
     * <p><b>200</b> - OK
     * <p><b>0</b> - default response
     * @param schema Schema identifier (required)
     * @param codelist Codelist element name or alias (required)
     * @param parent  (optional)
     * @param displayIf  (optional)
     * @param xpath  (optional)
     * @param isoType  (optional)
     * @return ResponseEntity&lt;Map&lt;String, String&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Map<String, String>> getSchemaTranslationsWithHttpInfo(String schema, String codelist, String parent, String displayIf, String xpath, String isoType) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'schema' is set
        if (schema == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'schema' when calling getSchemaTranslations");
        }
        
        // verify the required parameter 'codelist' is set
        if (codelist == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'codelist' when calling getSchemaTranslations");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("schema", schema);
        uriVariables.put("codelist", codelist);
        String path = apiClient.expandPath("/standards/{schema}/codelists/{codelist}", uriVariables);

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "parent", parent));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "displayIf", displayIf));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "xpath", xpath));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "isoType", isoType));

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
     * Get standards
     * 
     * <p><b>200</b> - List of standards.
     * <p><b>0</b> - default response
     * @return List&lt;MetadataSchema&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<MetadataSchema> getStandardConfigurations() throws RestClientException {
        return getStandardConfigurationsWithHttpInfo().getBody();
    }

    /**
     * Get standards
     * 
     * <p><b>200</b> - List of standards.
     * <p><b>0</b> - default response
     * @return ResponseEntity&lt;List&lt;MetadataSchema&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<MetadataSchema>> getStandardConfigurationsWithHttpInfo() throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/standards", Collections.<String, Object>emptyMap());

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

        ParameterizedTypeReference<List<MetadataSchema>> returnType = new ParameterizedTypeReference<List<MetadataSchema>>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Reload standards
     * 
     * <p><b>200</b> - Standards reloaded.
     * <p><b>0</b> - default response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void reloadSchema() throws RestClientException {
        reloadSchemaWithHttpInfo();
    }

    /**
     * Reload standards
     * 
     * <p><b>200</b> - Standards reloaded.
     * <p><b>0</b> - default response
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> reloadSchemaWithHttpInfo() throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/standards/reload", Collections.<String, Object>emptyMap());

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
}
