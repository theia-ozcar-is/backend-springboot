package fr.theialand.insitu.dataportal.api.geonetwork.api;

import fr.theialand.insitu.dataportal.api.geonetwork.handler.ApiClient;

import java.io.File;

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
@Component("fr.theialand.insitu.dataportal.api.geonetwork.api.LogosApi")
public class LogosApi {
    private ApiClient apiClient;

    public LogosApi() {
        this(new ApiClient());
    }

    @Autowired
    public LogosApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Add a logo
     * 
     * <p><b>403</b> - Operation not allowed. Only UserAdmins can access it.
     * <p><b>201</b> - Logo added.
     * <p><b>0</b> - default response
     * @param file The logo image to upload (required)
     * @param overwrite Overwrite if exists (optional, default to false)
     * @return String
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public String addLogo(List<File> file, Boolean overwrite) throws RestClientException {
        return addLogoWithHttpInfo(file, overwrite).getBody();
    }

    /**
     * Add a logo
     * 
     * <p><b>403</b> - Operation not allowed. Only UserAdmins can access it.
     * <p><b>201</b> - Logo added.
     * <p><b>0</b> - default response
     * @param file The logo image to upload (required)
     * @param overwrite Overwrite if exists (optional, default to false)
     * @return ResponseEntity&lt;String&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<String> addLogoWithHttpInfo(List<File> file, Boolean overwrite) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'file' is set
        if (file == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'file' when calling addLogo");
        }
        
        String path = apiClient.expandPath("/logos", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        queryParams.putAll(apiClient.parameterToMultiValueMap(ApiClient.CollectionFormat.valueOf("multi".toUpperCase(Locale.ROOT)), "file", file));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "overwrite", overwrite));

        final String[] accepts = { 
            "application/json"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] { "basicAuth" };

        ParameterizedTypeReference<String> returnType = new ParameterizedTypeReference<String>() {};
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Remove a logo
     * 
     * <p><b>404</b> - Resource not found.
     * <p><b>403</b> - Operation not allowed. Only UserAdmins can access it.
     * <p><b>204</b> - Logo removed.
     * <p><b>0</b> - default response
     * @param file The logo filename to delete (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void deleteLogo(String file) throws RestClientException {
        deleteLogoWithHttpInfo(file);
    }

    /**
     * Remove a logo
     * 
     * <p><b>404</b> - Resource not found.
     * <p><b>403</b> - Operation not allowed. Only UserAdmins can access it.
     * <p><b>204</b> - Logo removed.
     * <p><b>0</b> - default response
     * @param file The logo filename to delete (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> deleteLogoWithHttpInfo(String file) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'file' is set
        if (file == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'file' when calling deleteLogo");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("file", file);
        String path = apiClient.expandPath("/logos/{file}", uriVariables);

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
     * Get all logos
     * Logos are used for the catalog, the groups logos, and harvester icons. Logos are stored in the data directory in &lt;dataDirectory&gt;/resources/images/harvesting.&lt;br/&gt; Records are attached to a source. A source can be the local catalog or a harvester node. When a source is created, its logo is located in the images/logos folder with the source UUID as filename. For some sources the logo can be automatically retrieved (eg. when harvesting GeoNetwork catalogs). For others, the logo is usually manually defined when configuring the harvester.
     * <p><b>200</b> - OK
     * <p><b>0</b> - default response
     * @return List&lt;String&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<String> getLogos() throws RestClientException {
        return getLogosWithHttpInfo().getBody();
    }

    /**
     * Get all logos
     * Logos are used for the catalog, the groups logos, and harvester icons. Logos are stored in the data directory in &lt;dataDirectory&gt;/resources/images/harvesting.&lt;br/&gt; Records are attached to a source. A source can be the local catalog or a harvester node. When a source is created, its logo is located in the images/logos folder with the source UUID as filename. For some sources the logo can be automatically retrieved (eg. when harvesting GeoNetwork catalogs). For others, the logo is usually manually defined when configuring the harvester.
     * <p><b>200</b> - OK
     * <p><b>0</b> - default response
     * @return ResponseEntity&lt;List&lt;String&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<String>> getLogosWithHttpInfo() throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/logos", Collections.<String, Object>emptyMap());

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
