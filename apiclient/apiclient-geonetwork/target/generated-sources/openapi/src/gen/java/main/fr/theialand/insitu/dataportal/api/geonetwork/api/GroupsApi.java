package fr.theialand.insitu.dataportal.api.geonetwork.api;

import fr.theialand.insitu.dataportal.api.geonetwork.handler.ApiClient;

import fr.theialand.insitu.dataportal.api.geonetwork.model.Group;
import fr.theialand.insitu.dataportal.api.geonetwork.model.User;

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
@Component("fr.theialand.insitu.dataportal.api.geonetwork.api.GroupsApi")
public class GroupsApi {
    private ApiClient apiClient;

    public GroupsApi() {
        this(new ApiClient());
    }

    @Autowired
    public GroupsApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Add a group
     * Return the identifier of the group created.
     * <p><b>403</b> - Operation not allowed. Only UserAdmins can access it.
     * <p><b>400</b> - Group with that id or name already exist.
     * <p><b>201</b> - Group created.
     * <p><b>0</b> - default response
     * @param group Group details (required)
     * @return Integer
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Integer addGroup(Group group) throws RestClientException {
        return addGroupWithHttpInfo(group).getBody();
    }

    /**
     * Add a group
     * Return the identifier of the group created.
     * <p><b>403</b> - Operation not allowed. Only UserAdmins can access it.
     * <p><b>400</b> - Group with that id or name already exist.
     * <p><b>201</b> - Group created.
     * <p><b>0</b> - default response
     * @param group Group details (required)
     * @return ResponseEntity&lt;Integer&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Integer> addGroupWithHttpInfo(Group group) throws RestClientException {
        Object postBody = group;
        
        // verify the required parameter 'group' is set
        if (group == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'group' when calling addGroup");
        }
        
        String path = apiClient.expandPath("/groups", Collections.<String, Object>emptyMap());

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
     * Remove a group
     * Remove a group by first removing sharing settings, link to users and finally reindex all affected records.
     * <p><b>404</b> - Resource not found.
     * <p><b>403</b> - Operation not allowed. Only UserAdmins can access it.
     * <p><b>204</b> - Group removed.
     * <p><b>0</b> - default response
     * @param groupIdentifier Group identifier. (required)
     * @param force Force removal even if records are assigned to that group. (optional, default to false)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void deleteGroup(Integer groupIdentifier, Boolean force) throws RestClientException {
        deleteGroupWithHttpInfo(groupIdentifier, force);
    }

    /**
     * Remove a group
     * Remove a group by first removing sharing settings, link to users and finally reindex all affected records.
     * <p><b>404</b> - Resource not found.
     * <p><b>403</b> - Operation not allowed. Only UserAdmins can access it.
     * <p><b>204</b> - Group removed.
     * <p><b>0</b> - default response
     * @param groupIdentifier Group identifier. (required)
     * @param force Force removal even if records are assigned to that group. (optional, default to false)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> deleteGroupWithHttpInfo(Integer groupIdentifier, Boolean force) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'groupIdentifier' is set
        if (groupIdentifier == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'groupIdentifier' when calling deleteGroup");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("groupIdentifier", groupIdentifier);
        String path = apiClient.expandPath("/groups/{groupIdentifier}", uriVariables);

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "force", force));

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
     * Get group
     * Return the requested group details.
     * <p><b>404</b> - Resource not found.
     * <p><b>0</b> - default response
     * @param groupIdentifier Group identifier (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void getGroup(Integer groupIdentifier) throws RestClientException {
        getGroupWithHttpInfo(groupIdentifier);
    }

    /**
     * Get group
     * Return the requested group details.
     * <p><b>404</b> - Resource not found.
     * <p><b>0</b> - default response
     * @param groupIdentifier Group identifier (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> getGroupWithHttpInfo(Integer groupIdentifier) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'groupIdentifier' is set
        if (groupIdentifier == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'groupIdentifier' when calling getGroup");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("groupIdentifier", groupIdentifier);
        String path = apiClient.expandPath("/groups/{groupIdentifier}", uriVariables);

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
     * Get group users
     * 
     * <p><b>404</b> - Resource not found.
     * <p><b>403</b> - Operation not allowed. Only UserAdmins can access it.
     * <p><b>200</b> - List of users in that group.
     * <p><b>0</b> - default response
     * @param groupIdentifier Group identifier (required)
     * @return List&lt;User&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<User> getGroupUsers(Integer groupIdentifier) throws RestClientException {
        return getGroupUsersWithHttpInfo(groupIdentifier).getBody();
    }

    /**
     * Get group users
     * 
     * <p><b>404</b> - Resource not found.
     * <p><b>403</b> - Operation not allowed. Only UserAdmins can access it.
     * <p><b>200</b> - List of users in that group.
     * <p><b>0</b> - default response
     * @param groupIdentifier Group identifier (required)
     * @return ResponseEntity&lt;List&lt;User&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<User>> getGroupUsersWithHttpInfo(Integer groupIdentifier) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'groupIdentifier' is set
        if (groupIdentifier == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'groupIdentifier' when calling getGroupUsers");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("groupIdentifier", groupIdentifier);
        String path = apiClient.expandPath("/groups/{groupIdentifier}/users", uriVariables);

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

        ParameterizedTypeReference<List<User>> returnType = new ParameterizedTypeReference<List<User>>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Get groups
     * The catalog contains one or more groups. By default, there is 3 reserved groups (Internet, Intranet, Guest) and a sample group.&lt;br/&gt;This service returns all catalog groups when not authenticated or when current is user is an administrator. The list can contains or not reserved groups depending on the parameters.&lt;br/&gt;When authenticated, return user groups optionally filtered on a specific user profile.
     * <p><b>200</b> - OK
     * <p><b>0</b> - default response
     * @param withReservedGroup Including Internet, Intranet, Guest groups or not (optional, default to false)
     * @param profile For a specific profile (optional)
     * @return List&lt;Group&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<Group> getGroups(Boolean withReservedGroup, String profile) throws RestClientException {
        return getGroupsWithHttpInfo(withReservedGroup, profile).getBody();
    }

    /**
     * Get groups
     * The catalog contains one or more groups. By default, there is 3 reserved groups (Internet, Intranet, Guest) and a sample group.&lt;br/&gt;This service returns all catalog groups when not authenticated or when current is user is an administrator. The list can contains or not reserved groups depending on the parameters.&lt;br/&gt;When authenticated, return user groups optionally filtered on a specific user profile.
     * <p><b>200</b> - OK
     * <p><b>0</b> - default response
     * @param withReservedGroup Including Internet, Intranet, Guest groups or not (optional, default to false)
     * @param profile For a specific profile (optional)
     * @return ResponseEntity&lt;List&lt;Group&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<Group>> getGroupsWithHttpInfo(Boolean withReservedGroup, String profile) throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/groups", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "withReservedGroup", withReservedGroup));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "profile", profile));

        final String[] accepts = { 
            "application/json"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] { "basicAuth" };

        ParameterizedTypeReference<List<Group>> returnType = new ParameterizedTypeReference<List<Group>>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Update a group
     * 
     * <p><b>404</b> - Resource not found.
     * <p><b>403</b> - Operation not allowed. Only UserAdmins can access it.
     * <p><b>204</b> - Group updated.
     * <p><b>0</b> - default response
     * @param groupIdentifier Group identifier (required)
     * @param group Group details (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void updateGroup(Integer groupIdentifier, Group group) throws RestClientException {
        updateGroupWithHttpInfo(groupIdentifier, group);
    }

    /**
     * Update a group
     * 
     * <p><b>404</b> - Resource not found.
     * <p><b>403</b> - Operation not allowed. Only UserAdmins can access it.
     * <p><b>204</b> - Group updated.
     * <p><b>0</b> - default response
     * @param groupIdentifier Group identifier (required)
     * @param group Group details (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> updateGroupWithHttpInfo(Integer groupIdentifier, Group group) throws RestClientException {
        Object postBody = group;
        
        // verify the required parameter 'groupIdentifier' is set
        if (groupIdentifier == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'groupIdentifier' when calling updateGroup");
        }
        
        // verify the required parameter 'group' is set
        if (group == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'group' when calling updateGroup");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("groupIdentifier", groupIdentifier);
        String path = apiClient.expandPath("/groups/{groupIdentifier}", uriVariables);

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
