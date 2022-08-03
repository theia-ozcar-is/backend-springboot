package fr.theialand.insitu.dataportal.api.geonetwork.api;

import fr.theialand.insitu.dataportal.api.geonetwork.handler.ApiClient;

import fr.theialand.insitu.dataportal.api.geonetwork.model.PaginatedUserSearchResponse;
import fr.theialand.insitu.dataportal.api.geonetwork.model.UserSearchDto;

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
@Component("fr.theialand.insitu.dataportal.api.geonetwork.api.UsersearchesApi")
public class UsersearchesApi {
    private ApiClient apiClient;

    public UsersearchesApi() {
        this(new ApiClient());
    }

    @Autowired
    public UsersearchesApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Creates a user search
     * Creates a user search.
     * <p><b>201</b> - User search created.
     * <p><b>0</b> - default response
     * @param userSearchDto User search details (required)
     * @return Integer
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Integer createUserCustomSearch(UserSearchDto userSearchDto) throws RestClientException {
        return createUserCustomSearchWithHttpInfo(userSearchDto).getBody();
    }

    /**
     * Creates a user search
     * Creates a user search.
     * <p><b>201</b> - User search created.
     * <p><b>0</b> - default response
     * @param userSearchDto User search details (required)
     * @return ResponseEntity&lt;Integer&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Integer> createUserCustomSearchWithHttpInfo(UserSearchDto userSearchDto) throws RestClientException {
        Object postBody = userSearchDto;
        
        // verify the required parameter 'userSearchDto' is set
        if (userSearchDto == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'userSearchDto' when calling createUserCustomSearch");
        }
        
        String path = apiClient.expandPath("/usersearches", Collections.<String, Object>emptyMap());

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
     * Delete a user search
     * Deletes a user search by identifier.
     * <p><b>200</b> - OK
     * <p><b>0</b> - default response
     * @param searchIdentifier Search identifier. (required)
     * @return String
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public String deleteUserCustomSerach(Integer searchIdentifier) throws RestClientException {
        return deleteUserCustomSerachWithHttpInfo(searchIdentifier).getBody();
    }

    /**
     * Delete a user search
     * Deletes a user search by identifier.
     * <p><b>200</b> - OK
     * <p><b>0</b> - default response
     * @param searchIdentifier Search identifier. (required)
     * @return ResponseEntity&lt;String&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<String> deleteUserCustomSerachWithHttpInfo(Integer searchIdentifier) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'searchIdentifier' is set
        if (searchIdentifier == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'searchIdentifier' when calling deleteUserCustomSerach");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("searchIdentifier", searchIdentifier);
        String path = apiClient.expandPath("/usersearches/{searchIdentifier}", uriVariables);

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
        return apiClient.invokeAPI(path, HttpMethod.DELETE, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Get user custom searches for all users (no paginated)
     * 
     * <p><b>200</b> - OK
     * <p><b>0</b> - default response
     * @param featuredType Featured type search. (optional)
     * @return List&lt;UserSearchDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<UserSearchDto> getAllUserCustomSearches(String featuredType) throws RestClientException {
        return getAllUserCustomSearchesWithHttpInfo(featuredType).getBody();
    }

    /**
     * Get user custom searches for all users (no paginated)
     * 
     * <p><b>200</b> - OK
     * <p><b>0</b> - default response
     * @param featuredType Featured type search. (optional)
     * @return ResponseEntity&lt;List&lt;UserSearchDto&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<UserSearchDto>> getAllUserCustomSearchesWithHttpInfo(String featuredType) throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/usersearches/all", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "featuredType", featuredType));

        final String[] accepts = { 
            "application/json"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] { "basicAuth" };

        ParameterizedTypeReference<List<UserSearchDto>> returnType = new ParameterizedTypeReference<List<UserSearchDto>>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Get user custom searches for all users (paginated)
     * 
     * <p><b>200</b> - OK
     * <p><b>0</b> - default response
     * @param featuredType Featured  type search. (optional)
     * @param search  (optional, default to &quot;&quot;)
     * @param offset From page (optional, default to 0)
     * @param limit Number of records to return (optional, default to 10)
     * @return PaginatedUserSearchResponse
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public PaginatedUserSearchResponse getAllUserCustomSearchesPage(String featuredType, String search, Integer offset, Integer limit) throws RestClientException {
        return getAllUserCustomSearchesPageWithHttpInfo(featuredType, search, offset, limit).getBody();
    }

    /**
     * Get user custom searches for all users (paginated)
     * 
     * <p><b>200</b> - OK
     * <p><b>0</b> - default response
     * @param featuredType Featured  type search. (optional)
     * @param search  (optional, default to &quot;&quot;)
     * @param offset From page (optional, default to 0)
     * @param limit Number of records to return (optional, default to 10)
     * @return ResponseEntity&lt;PaginatedUserSearchResponse&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<PaginatedUserSearchResponse> getAllUserCustomSearchesPageWithHttpInfo(String featuredType, String search, Integer offset, Integer limit) throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/usersearches/allpaginated", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "featuredType", featuredType));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "search", search));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "offset", offset));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "limit", limit));

        final String[] accepts = { 
            "application/json"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] { "basicAuth" };

        ParameterizedTypeReference<PaginatedUserSearchResponse> returnType = new ParameterizedTypeReference<PaginatedUserSearchResponse>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Get featured user custom searches
     * 
     * <p><b>200</b> - OK
     * <p><b>0</b> - default response
     * @param type Number of records to return (optional)
     * @return List&lt;UserSearchDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<UserSearchDto> getFeaturedUserCustomSearches(String type) throws RestClientException {
        return getFeaturedUserCustomSearchesWithHttpInfo(type).getBody();
    }

    /**
     * Get featured user custom searches
     * 
     * <p><b>200</b> - OK
     * <p><b>0</b> - default response
     * @param type Number of records to return (optional)
     * @return ResponseEntity&lt;List&lt;UserSearchDto&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<UserSearchDto>> getFeaturedUserCustomSearchesWithHttpInfo(String type) throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/usersearches/featured", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "type", type));

        final String[] accepts = { 
            "application/json"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] { "basicAuth" };

        ParameterizedTypeReference<List<UserSearchDto>> returnType = new ParameterizedTypeReference<List<UserSearchDto>>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Get custom search
     * 
     * <p><b>200</b> - OK
     * <p><b>0</b> - default response
     * @param searchIdentifier User search identifier (required)
     * @return UserSearchDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public UserSearchDto getUserCustomSearch(Integer searchIdentifier) throws RestClientException {
        return getUserCustomSearchWithHttpInfo(searchIdentifier).getBody();
    }

    /**
     * Get custom search
     * 
     * <p><b>200</b> - OK
     * <p><b>0</b> - default response
     * @param searchIdentifier User search identifier (required)
     * @return ResponseEntity&lt;UserSearchDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<UserSearchDto> getUserCustomSearchWithHttpInfo(Integer searchIdentifier) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'searchIdentifier' is set
        if (searchIdentifier == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'searchIdentifier' when calling getUserCustomSearch");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("searchIdentifier", searchIdentifier);
        String path = apiClient.expandPath("/usersearches/{searchIdentifier}", uriVariables);

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

        ParameterizedTypeReference<UserSearchDto> returnType = new ParameterizedTypeReference<UserSearchDto>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Get user custom searches
     * 
     * <p><b>200</b> - OK
     * <p><b>0</b> - default response
     * @return List&lt;UserSearchDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<UserSearchDto> getUserCustomSearches() throws RestClientException {
        return getUserCustomSearchesWithHttpInfo().getBody();
    }

    /**
     * Get user custom searches
     * 
     * <p><b>200</b> - OK
     * <p><b>0</b> - default response
     * @return ResponseEntity&lt;List&lt;UserSearchDto&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<UserSearchDto>> getUserCustomSearchesWithHttpInfo() throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/usersearches", Collections.<String, Object>emptyMap());

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

        ParameterizedTypeReference<List<UserSearchDto>> returnType = new ParameterizedTypeReference<List<UserSearchDto>>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Update a user search
     * 
     * <p><b>404</b> - Resource not found.
     * <p><b>204</b> - User search  updated.
     * <p><b>0</b> - default response
     * @param searchIdentifier User search identifier (required)
     * @param userSearchDto User search details (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void updateCustomUserSearch(Integer searchIdentifier, UserSearchDto userSearchDto) throws RestClientException {
        updateCustomUserSearchWithHttpInfo(searchIdentifier, userSearchDto);
    }

    /**
     * Update a user search
     * 
     * <p><b>404</b> - Resource not found.
     * <p><b>204</b> - User search  updated.
     * <p><b>0</b> - default response
     * @param searchIdentifier User search identifier (required)
     * @param userSearchDto User search details (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> updateCustomUserSearchWithHttpInfo(Integer searchIdentifier, UserSearchDto userSearchDto) throws RestClientException {
        Object postBody = userSearchDto;
        
        // verify the required parameter 'searchIdentifier' is set
        if (searchIdentifier == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'searchIdentifier' when calling updateCustomUserSearch");
        }
        
        // verify the required parameter 'userSearchDto' is set
        if (userSearchDto == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'userSearchDto' when calling updateCustomUserSearch");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("searchIdentifier", searchIdentifier);
        String path = apiClient.expandPath("/usersearches/{searchIdentifier}", uriVariables);

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
