package fr.theialand.insitu.dataportal.api.geonetwork.api;

import fr.theialand.insitu.dataportal.api.geonetwork.handler.ApiClient;

import fr.theialand.insitu.dataportal.api.geonetwork.model.Selection;

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
@Component("fr.theialand.insitu.dataportal.api.geonetwork.api.UserselectionsApi")
public class UserselectionsApi {
    private ApiClient apiClient;

    public UserselectionsApi() {
        this(new ApiClient());
    }

    @Autowired
    public UserselectionsApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Add items to a user selection set
     * 
     * <p><b>403</b> - Operation not allowed. Only UserAdmins can access it.
     * <p><b>404</b> - Selection or user or at least one UUID not found.
     * <p><b>201</b> - Records added to selection set.
     * <p><b>0</b> - default response
     * @param selectionIdentifier Selection identifier (required)
     * @param userIdentifier User identifier (required)
     * @param uuid One or more record UUIDs. (optional, default to new ArrayList&lt;&gt;())
     * @return String
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public String addToUserSelection(Integer selectionIdentifier, Integer userIdentifier, List<String> uuid) throws RestClientException {
        return addToUserSelectionWithHttpInfo(selectionIdentifier, userIdentifier, uuid).getBody();
    }

    /**
     * Add items to a user selection set
     * 
     * <p><b>403</b> - Operation not allowed. Only UserAdmins can access it.
     * <p><b>404</b> - Selection or user or at least one UUID not found.
     * <p><b>201</b> - Records added to selection set.
     * <p><b>0</b> - default response
     * @param selectionIdentifier Selection identifier (required)
     * @param userIdentifier User identifier (required)
     * @param uuid One or more record UUIDs. (optional, default to new ArrayList&lt;&gt;())
     * @return ResponseEntity&lt;String&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<String> addToUserSelectionWithHttpInfo(Integer selectionIdentifier, Integer userIdentifier, List<String> uuid) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'selectionIdentifier' is set
        if (selectionIdentifier == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'selectionIdentifier' when calling addToUserSelection");
        }
        
        // verify the required parameter 'userIdentifier' is set
        if (userIdentifier == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'userIdentifier' when calling addToUserSelection");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("selectionIdentifier", selectionIdentifier);
        uriVariables.put("userIdentifier", userIdentifier);
        String path = apiClient.expandPath("/userselections/{selectionIdentifier}/{userIdentifier}", uriVariables);

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

        ParameterizedTypeReference<String> returnType = new ParameterizedTypeReference<String>() {};
        return apiClient.invokeAPI(path, HttpMethod.PUT, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Add a user selection set
     * 
     * <p><b>403</b> - Operation not allowed. Only UserAdmins can access it.
     * <p><b>201</b> - Selection created.
     * <p><b>400</b> - A selection with that id or name already exist.
     * <p><b>0</b> - default response
     * @param selection  (required)
     * @return String
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public String createPersistentSelectionType(Selection selection) throws RestClientException {
        return createPersistentSelectionTypeWithHttpInfo(selection).getBody();
    }

    /**
     * Add a user selection set
     * 
     * <p><b>403</b> - Operation not allowed. Only UserAdmins can access it.
     * <p><b>201</b> - Selection created.
     * <p><b>400</b> - A selection with that id or name already exist.
     * <p><b>0</b> - default response
     * @param selection  (required)
     * @return ResponseEntity&lt;String&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<String> createPersistentSelectionTypeWithHttpInfo(Selection selection) throws RestClientException {
        Object postBody = selection;
        
        // verify the required parameter 'selection' is set
        if (selection == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'selection' when calling createPersistentSelectionType");
        }
        
        String path = apiClient.expandPath("/userselections", Collections.<String, Object>emptyMap());

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

        ParameterizedTypeReference<String> returnType = new ParameterizedTypeReference<String>() {};
        return apiClient.invokeAPI(path, HttpMethod.PUT, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Remove items to a user selection set
     * 
     * <p><b>403</b> - Operation not allowed. Only UserAdmins can access it.
     * <p><b>204</b> - Items removed from a set.
     * <p><b>404</b> - Selection or user not found.
     * <p><b>0</b> - default response
     * @param selectionIdentifier Selection identifier (required)
     * @param userIdentifier User identifier (required)
     * @param uuid One or more record UUIDs. If null, remove all. (optional, default to new ArrayList&lt;&gt;())
     * @return String
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public String deleteFromUserSelection(Integer selectionIdentifier, Integer userIdentifier, List<String> uuid) throws RestClientException {
        return deleteFromUserSelectionWithHttpInfo(selectionIdentifier, userIdentifier, uuid).getBody();
    }

    /**
     * Remove items to a user selection set
     * 
     * <p><b>403</b> - Operation not allowed. Only UserAdmins can access it.
     * <p><b>204</b> - Items removed from a set.
     * <p><b>404</b> - Selection or user not found.
     * <p><b>0</b> - default response
     * @param selectionIdentifier Selection identifier (required)
     * @param userIdentifier User identifier (required)
     * @param uuid One or more record UUIDs. If null, remove all. (optional, default to new ArrayList&lt;&gt;())
     * @return ResponseEntity&lt;String&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<String> deleteFromUserSelectionWithHttpInfo(Integer selectionIdentifier, Integer userIdentifier, List<String> uuid) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'selectionIdentifier' is set
        if (selectionIdentifier == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'selectionIdentifier' when calling deleteFromUserSelection");
        }
        
        // verify the required parameter 'userIdentifier' is set
        if (userIdentifier == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'userIdentifier' when calling deleteFromUserSelection");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("selectionIdentifier", selectionIdentifier);
        uriVariables.put("userIdentifier", userIdentifier);
        String path = apiClient.expandPath("/userselections/{selectionIdentifier}/{userIdentifier}", uriVariables);

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

        ParameterizedTypeReference<String> returnType = new ParameterizedTypeReference<String>() {};
        return apiClient.invokeAPI(path, HttpMethod.DELETE, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Get list of user selection sets
     * 
     * <p><b>200</b> - OK
     * <p><b>0</b> - default response
     * @return List&lt;Selection&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<Selection> getSelectionList() throws RestClientException {
        return getSelectionListWithHttpInfo().getBody();
    }

    /**
     * Get list of user selection sets
     * 
     * <p><b>200</b> - OK
     * <p><b>0</b> - default response
     * @return ResponseEntity&lt;List&lt;Selection&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<Selection>> getSelectionListWithHttpInfo() throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/userselections", Collections.<String, Object>emptyMap());

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

        ParameterizedTypeReference<List<Selection>> returnType = new ParameterizedTypeReference<List<Selection>>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Get record in a user selection set
     * 
     * <p><b>200</b> - OK
     * <p><b>0</b> - default response
     * @param selectionIdentifier Selection identifier (required)
     * @param userIdentifier User identifier (required)
     * @return List&lt;String&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<String> getSelectionRecords(Integer selectionIdentifier, Integer userIdentifier) throws RestClientException {
        return getSelectionRecordsWithHttpInfo(selectionIdentifier, userIdentifier).getBody();
    }

    /**
     * Get record in a user selection set
     * 
     * <p><b>200</b> - OK
     * <p><b>0</b> - default response
     * @param selectionIdentifier Selection identifier (required)
     * @param userIdentifier User identifier (required)
     * @return ResponseEntity&lt;List&lt;String&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<String>> getSelectionRecordsWithHttpInfo(Integer selectionIdentifier, Integer userIdentifier) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'selectionIdentifier' is set
        if (selectionIdentifier == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'selectionIdentifier' when calling getSelectionRecords");
        }
        
        // verify the required parameter 'userIdentifier' is set
        if (userIdentifier == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'userIdentifier' when calling getSelectionRecords");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("selectionIdentifier", selectionIdentifier);
        uriVariables.put("userIdentifier", userIdentifier);
        String path = apiClient.expandPath("/userselections/{selectionIdentifier}/{userIdentifier}", uriVariables);

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
