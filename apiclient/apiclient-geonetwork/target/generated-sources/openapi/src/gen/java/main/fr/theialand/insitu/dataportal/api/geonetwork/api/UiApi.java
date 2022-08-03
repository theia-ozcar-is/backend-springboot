package fr.theialand.insitu.dataportal.api.geonetwork.api;

import fr.theialand.insitu.dataportal.api.geonetwork.handler.ApiClient;

import fr.theialand.insitu.dataportal.api.geonetwork.model.UiSetting;

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
@Component("fr.theialand.insitu.dataportal.api.geonetwork.api.UiApi")
public class UiApi {
    private ApiClient apiClient;

    public UiApi() {
        this(new ApiClient());
    }

    @Autowired
    public UiApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Get a UI configuration
     * 
     * <p><b>200</b> - UI configuration.
     * <p><b>0</b> - default response
     * @param uiIdentifier UI identifier (required)
     * @return UiSetting
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public UiSetting getUiConfiguration(String uiIdentifier) throws RestClientException {
        return getUiConfigurationWithHttpInfo(uiIdentifier).getBody();
    }

    /**
     * Get a UI configuration
     * 
     * <p><b>200</b> - UI configuration.
     * <p><b>0</b> - default response
     * @param uiIdentifier UI identifier (required)
     * @return ResponseEntity&lt;UiSetting&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<UiSetting> getUiConfigurationWithHttpInfo(String uiIdentifier) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'uiIdentifier' is set
        if (uiIdentifier == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'uiIdentifier' when calling getUiConfiguration");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("uiIdentifier", uiIdentifier);
        String path = apiClient.expandPath("/ui/{uiIdentifier}", uriVariables);

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

        ParameterizedTypeReference<UiSetting> returnType = new ParameterizedTypeReference<UiSetting>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Get UI configuration
     * 
     * <p><b>200</b> - List of configuration.
     * <p><b>0</b> - default response
     * @return List&lt;UiSetting&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<UiSetting> getUiConfigurations() throws RestClientException {
        return getUiConfigurationsWithHttpInfo().getBody();
    }

    /**
     * Get UI configuration
     * 
     * <p><b>200</b> - List of configuration.
     * <p><b>0</b> - default response
     * @return ResponseEntity&lt;List&lt;UiSetting&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<UiSetting>> getUiConfigurationsWithHttpInfo() throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/ui", Collections.<String, Object>emptyMap());

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

        ParameterizedTypeReference<List<UiSetting>> returnType = new ParameterizedTypeReference<List<UiSetting>>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Update a UI configuration
     * 
     * <p><b>403</b> - Operation not allowed. Only UserAdmins can access it.
     * <p><b>204</b> - UI configuration updated.
     * <p><b>0</b> - default response
     * @param uiIdentifier UI configuration identifier (required)
     * @param uiSetting  (required)
     * @return String
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public String updateUiConfiguration(String uiIdentifier, UiSetting uiSetting) throws RestClientException {
        return updateUiConfigurationWithHttpInfo(uiIdentifier, uiSetting).getBody();
    }

    /**
     * Update a UI configuration
     * 
     * <p><b>403</b> - Operation not allowed. Only UserAdmins can access it.
     * <p><b>204</b> - UI configuration updated.
     * <p><b>0</b> - default response
     * @param uiIdentifier UI configuration identifier (required)
     * @param uiSetting  (required)
     * @return ResponseEntity&lt;String&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<String> updateUiConfigurationWithHttpInfo(String uiIdentifier, UiSetting uiSetting) throws RestClientException {
        Object postBody = uiSetting;
        
        // verify the required parameter 'uiIdentifier' is set
        if (uiIdentifier == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'uiIdentifier' when calling updateUiConfiguration");
        }
        
        // verify the required parameter 'uiSetting' is set
        if (uiSetting == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'uiSetting' when calling updateUiConfiguration");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("uiIdentifier", uiIdentifier);
        String path = apiClient.expandPath("/ui/{uiIdentifier}", uriVariables);

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
