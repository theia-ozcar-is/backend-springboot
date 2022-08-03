package fr.theialand.insitu.dataportal.api.geonetwork.api;

import fr.theialand.insitu.dataportal.api.geonetwork.handler.ApiClient;

import fr.theialand.insitu.dataportal.api.geonetwork.model.OwnerResponse;
import fr.theialand.insitu.dataportal.api.geonetwork.model.PasswordUpdateParameter;
import fr.theialand.insitu.dataportal.api.geonetwork.model.TransferRequest;
import fr.theialand.insitu.dataportal.api.geonetwork.model.User;
import fr.theialand.insitu.dataportal.api.geonetwork.model.UserDto;
import fr.theialand.insitu.dataportal.api.geonetwork.model.UserGroup;
import fr.theialand.insitu.dataportal.api.geonetwork.model.UserGroupsResponse;
import fr.theialand.insitu.dataportal.api.geonetwork.model.UserRegisterDto;

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
@Component("fr.theialand.insitu.dataportal.api.geonetwork.api.UsersApi")
public class UsersApi {
    private ApiClient apiClient;

    public UsersApi() {
        this(new ApiClient());
    }

    @Autowired
    public UsersApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Creates a user
     * Creates a catalog user.
     * <p><b>200</b> - OK
     * <p><b>0</b> - default response
     * @param userDto  (required)
     * @return String
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public String createUser(UserDto userDto) throws RestClientException {
        return createUserWithHttpInfo(userDto).getBody();
    }

    /**
     * Creates a user
     * Creates a catalog user.
     * <p><b>200</b> - OK
     * <p><b>0</b> - default response
     * @param userDto  (required)
     * @return ResponseEntity&lt;String&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<String> createUserWithHttpInfo(UserDto userDto) throws RestClientException {
        Object postBody = userDto;
        
        // verify the required parameter 'userDto' is set
        if (userDto == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'userDto' when calling createUser");
        }
        
        String path = apiClient.expandPath("/users", Collections.<String, Object>emptyMap());

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
     * Delete a user
     * Deletes a catalog user by identifier.
     * <p><b>200</b> - OK
     * <p><b>0</b> - default response
     * @param userIdentifier User identifier. (required)
     * @return String
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public String deleteUser(Integer userIdentifier) throws RestClientException {
        return deleteUserWithHttpInfo(userIdentifier).getBody();
    }

    /**
     * Delete a user
     * Deletes a catalog user by identifier.
     * <p><b>200</b> - OK
     * <p><b>0</b> - default response
     * @param userIdentifier User identifier. (required)
     * @return ResponseEntity&lt;String&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<String> deleteUserWithHttpInfo(Integer userIdentifier) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'userIdentifier' is set
        if (userIdentifier == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'userIdentifier' when calling deleteUser");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("userIdentifier", userIdentifier);
        String path = apiClient.expandPath("/users/{userIdentifier}", uriVariables);

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
     * Get owners
     * Return users who actually owns one or more records.
     * <p><b>200</b> - OK
     * <p><b>0</b> - default response
     * @return List&lt;OwnerResponse&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<OwnerResponse> getRecordOwners() throws RestClientException {
        return getRecordOwnersWithHttpInfo().getBody();
    }

    /**
     * Get owners
     * Return users who actually owns one or more records.
     * <p><b>200</b> - OK
     * <p><b>0</b> - default response
     * @return ResponseEntity&lt;List&lt;OwnerResponse&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<OwnerResponse>> getRecordOwnersWithHttpInfo() throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/users/owners", Collections.<String, Object>emptyMap());

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

        ParameterizedTypeReference<List<OwnerResponse>> returnType = new ParameterizedTypeReference<List<OwnerResponse>>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Get user
     * 
     * <p><b>200</b> - OK
     * <p><b>0</b> - default response
     * @param userIdentifier User identifier. (required)
     * @return User
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public User getUser(Integer userIdentifier) throws RestClientException {
        return getUserWithHttpInfo(userIdentifier).getBody();
    }

    /**
     * Get user
     * 
     * <p><b>200</b> - OK
     * <p><b>0</b> - default response
     * @param userIdentifier User identifier. (required)
     * @return ResponseEntity&lt;User&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<User> getUserWithHttpInfo(Integer userIdentifier) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'userIdentifier' is set
        if (userIdentifier == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'userIdentifier' when calling getUser");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("userIdentifier", userIdentifier);
        String path = apiClient.expandPath("/users/{userIdentifier}", uriVariables);

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

        ParameterizedTypeReference<User> returnType = new ParameterizedTypeReference<User>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Get user identicon
     * 
     * <p><b>200</b> - OK
     * <p><b>0</b> - default response
     * @param userIdentifier User identifier. (required)
     * @param size Size. (optional, default to 18)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void getUserIdenticon(Integer userIdentifier, Integer size) throws RestClientException {
        getUserIdenticonWithHttpInfo(userIdentifier, size);
    }

    /**
     * Get user identicon
     * 
     * <p><b>200</b> - OK
     * <p><b>0</b> - default response
     * @param userIdentifier User identifier. (required)
     * @param size Size. (optional, default to 18)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> getUserIdenticonWithHttpInfo(Integer userIdentifier, Integer size) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'userIdentifier' is set
        if (userIdentifier == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'userIdentifier' when calling getUserIdenticon");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("userIdentifier", userIdentifier);
        String path = apiClient.expandPath("/users/{userIdentifier}.png", uriVariables);

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "size", size));

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
     * Get users
     * 
     * <p><b>200</b> - OK
     * <p><b>0</b> - default response
     * @return List&lt;User&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<User> getUsers() throws RestClientException {
        return getUsersWithHttpInfo().getBody();
    }

    /**
     * Get users
     * 
     * <p><b>200</b> - OK
     * <p><b>0</b> - default response
     * @return ResponseEntity&lt;List&lt;User&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<User>> getUsersWithHttpInfo() throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/users", Collections.<String, Object>emptyMap());

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
     * Create user account
     * User is created with a registered user profile. username field is ignored and the email is used as username. Password is sent by email. Catalog administrator is also notified.
     * <p><b>201</b> - Created
     * <p><b>0</b> - default response
     * @param userRegisterDto User details (required)
     * @return String
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public String registerUser(UserRegisterDto userRegisterDto) throws RestClientException {
        return registerUserWithHttpInfo(userRegisterDto).getBody();
    }

    /**
     * Create user account
     * User is created with a registered user profile. username field is ignored and the email is used as username. Password is sent by email. Catalog administrator is also notified.
     * <p><b>201</b> - Created
     * <p><b>0</b> - default response
     * @param userRegisterDto User details (required)
     * @return ResponseEntity&lt;String&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<String> registerUserWithHttpInfo(UserRegisterDto userRegisterDto) throws RestClientException {
        Object postBody = userRegisterDto;
        
        // verify the required parameter 'userRegisterDto' is set
        if (userRegisterDto == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'userRegisterDto' when calling registerUser");
        }
        
        String path = apiClient.expandPath("/user/actions/register", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        final String[] accepts = { 
            "application/json", "text/plain"
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
     * Resets user password
     * Resets the user password.
     * <p><b>200</b> - OK
     * <p><b>0</b> - default response
     * @param userIdentifier User identifier. (required)
     * @param password Password to change. (required)
     * @param password2 Password to change (repeat). (required)
     * @return String
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public String resetUserPassword(Integer userIdentifier, String password, String password2) throws RestClientException {
        return resetUserPasswordWithHttpInfo(userIdentifier, password, password2).getBody();
    }

    /**
     * Resets user password
     * Resets the user password.
     * <p><b>200</b> - OK
     * <p><b>0</b> - default response
     * @param userIdentifier User identifier. (required)
     * @param password Password to change. (required)
     * @param password2 Password to change (repeat). (required)
     * @return ResponseEntity&lt;String&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<String> resetUserPasswordWithHttpInfo(Integer userIdentifier, String password, String password2) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'userIdentifier' is set
        if (userIdentifier == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'userIdentifier' when calling resetUserPassword");
        }
        
        // verify the required parameter 'password' is set
        if (password == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'password' when calling resetUserPassword");
        }
        
        // verify the required parameter 'password2' is set
        if (password2 == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'password2' when calling resetUserPassword");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("userIdentifier", userIdentifier);
        String path = apiClient.expandPath("/users/{userIdentifier}/actions/forget-password", uriVariables);

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "password", password));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "password2", password2));

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
     * Retrieve all user groups
     * 
     * <p><b>200</b> - OK
     * <p><b>0</b> - default response
     * @return List&lt;UserGroupsResponse&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<UserGroupsResponse> retrieveAllUserGroups() throws RestClientException {
        return retrieveAllUserGroupsWithHttpInfo().getBody();
    }

    /**
     * Retrieve all user groups
     * 
     * <p><b>200</b> - OK
     * <p><b>0</b> - default response
     * @return ResponseEntity&lt;List&lt;UserGroupsResponse&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<UserGroupsResponse>> retrieveAllUserGroupsWithHttpInfo() throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/users/groups", Collections.<String, Object>emptyMap());

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

        ParameterizedTypeReference<List<UserGroupsResponse>> returnType = new ParameterizedTypeReference<List<UserGroupsResponse>>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Retrieve user groups
     * Retrieve the user groups.
     * <p><b>200</b> - OK
     * <p><b>0</b> - default response
     * @param userIdentifier User identifier. (required)
     * @return List&lt;UserGroup&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<UserGroup> retrieveUserGroups(Integer userIdentifier) throws RestClientException {
        return retrieveUserGroupsWithHttpInfo(userIdentifier).getBody();
    }

    /**
     * Retrieve user groups
     * Retrieve the user groups.
     * <p><b>200</b> - OK
     * <p><b>0</b> - default response
     * @param userIdentifier User identifier. (required)
     * @return ResponseEntity&lt;List&lt;UserGroup&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<UserGroup>> retrieveUserGroupsWithHttpInfo(Integer userIdentifier) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'userIdentifier' is set
        if (userIdentifier == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'userIdentifier' when calling retrieveUserGroups");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("userIdentifier", userIdentifier);
        String path = apiClient.expandPath("/users/{userIdentifier}/groups", uriVariables);

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

        ParameterizedTypeReference<List<UserGroup>> returnType = new ParameterizedTypeReference<List<UserGroup>>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Transfer privileges
     * 
     * <p><b>200</b> - OK
     * <p><b>0</b> - default response
     * @param transferRequest  (required)
     * @return String
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public String saveOwners(TransferRequest transferRequest) throws RestClientException {
        return saveOwnersWithHttpInfo(transferRequest).getBody();
    }

    /**
     * Transfer privileges
     * 
     * <p><b>200</b> - OK
     * <p><b>0</b> - default response
     * @param transferRequest  (required)
     * @return ResponseEntity&lt;String&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<String> saveOwnersWithHttpInfo(TransferRequest transferRequest) throws RestClientException {
        Object postBody = transferRequest;
        
        // verify the required parameter 'transferRequest' is set
        if (transferRequest == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'transferRequest' when calling saveOwners");
        }
        
        String path = apiClient.expandPath("/users/owners", Collections.<String, Object>emptyMap());

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
     * Send user password reminder by email
     * An email is sent to the requested user with a link to reset his password. User MUST have an email to get the link. LDAP users will not be able to retrieve their password using this service.
     * <p><b>201</b> - Created
     * <p><b>0</b> - default response
     * @param username The user name (required)
     * @return String
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public String sendPasswordByEmail(String username) throws RestClientException {
        return sendPasswordByEmailWithHttpInfo(username).getBody();
    }

    /**
     * Send user password reminder by email
     * An email is sent to the requested user with a link to reset his password. User MUST have an email to get the link. LDAP users will not be able to retrieve their password using this service.
     * <p><b>201</b> - Created
     * <p><b>0</b> - default response
     * @param username The user name (required)
     * @return ResponseEntity&lt;String&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<String> sendPasswordByEmailWithHttpInfo(String username) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'username' is set
        if (username == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'username' when calling sendPasswordByEmail");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("username", username);
        String path = apiClient.expandPath("/user/{username}/actions/forgot-password", uriVariables);

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
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Update user password
     * Get a valid changekey by email first and then update your password.
     * <p><b>201</b> - Created
     * <p><b>0</b> - default response
     * @param username The user name (required)
     * @param passwordUpdateParameter The new password and a valid change key (required)
     * @return String
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public String updatePassword(String username, PasswordUpdateParameter passwordUpdateParameter) throws RestClientException {
        return updatePasswordWithHttpInfo(username, passwordUpdateParameter).getBody();
    }

    /**
     * Update user password
     * Get a valid changekey by email first and then update your password.
     * <p><b>201</b> - Created
     * <p><b>0</b> - default response
     * @param username The user name (required)
     * @param passwordUpdateParameter The new password and a valid change key (required)
     * @return ResponseEntity&lt;String&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<String> updatePasswordWithHttpInfo(String username, PasswordUpdateParameter passwordUpdateParameter) throws RestClientException {
        Object postBody = passwordUpdateParameter;
        
        // verify the required parameter 'username' is set
        if (username == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'username' when calling updatePassword");
        }
        
        // verify the required parameter 'passwordUpdateParameter' is set
        if (passwordUpdateParameter == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'passwordUpdateParameter' when calling updatePassword");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("username", username);
        String path = apiClient.expandPath("/user/{username}", uriVariables);

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        final String[] accepts = { 
            "application/json", "text/plain"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { 
            "application/json"
        };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] { "basicAuth" };

        ParameterizedTypeReference<String> returnType = new ParameterizedTypeReference<String>() {};
        return apiClient.invokeAPI(path, HttpMethod.PATCH, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Update a user
     * Updates a catalog user.
     * <p><b>200</b> - OK
     * <p><b>0</b> - default response
     * @param userIdentifier User identifier. (required)
     * @param userDto  (required)
     * @return String
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public String updateUser(Integer userIdentifier, UserDto userDto) throws RestClientException {
        return updateUserWithHttpInfo(userIdentifier, userDto).getBody();
    }

    /**
     * Update a user
     * Updates a catalog user.
     * <p><b>200</b> - OK
     * <p><b>0</b> - default response
     * @param userIdentifier User identifier. (required)
     * @param userDto  (required)
     * @return ResponseEntity&lt;String&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<String> updateUserWithHttpInfo(Integer userIdentifier, UserDto userDto) throws RestClientException {
        Object postBody = userDto;
        
        // verify the required parameter 'userIdentifier' is set
        if (userIdentifier == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'userIdentifier' when calling updateUser");
        }
        
        // verify the required parameter 'userDto' is set
        if (userDto == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'userDto' when calling updateUser");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("userIdentifier", userIdentifier);
        String path = apiClient.expandPath("/users/{userIdentifier}", uriVariables);

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
}
