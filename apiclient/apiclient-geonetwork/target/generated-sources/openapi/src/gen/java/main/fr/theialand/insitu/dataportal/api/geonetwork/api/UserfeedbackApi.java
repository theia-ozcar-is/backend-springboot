package fr.theialand.insitu.dataportal.api.geonetwork.api;

import fr.theialand.insitu.dataportal.api.geonetwork.handler.ApiClient;

import fr.theialand.insitu.dataportal.api.geonetwork.model.RatingAverage;
import fr.theialand.insitu.dataportal.api.geonetwork.model.RatingCriteria;
import fr.theialand.insitu.dataportal.api.geonetwork.model.UserFeedbackDTO;

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
@Component("fr.theialand.insitu.dataportal.api.geonetwork.api.UserfeedbackApi")
public class UserfeedbackApi {
    private ApiClient apiClient;

    public UserfeedbackApi() {
        this(new ApiClient());
    }

    @Autowired
    public UserfeedbackApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Removes a user feedback
     * Removes a user feedback
     * <p><b>204</b> - User feedback removed.
     * <p><b>403</b> - Operation not allowed. Only Reviewvers can access it.
     * <p><b>0</b> - default response
     * @param uuid User feedback UUID. (required)
     * @return String
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public String deleteUserFeedback(String uuid) throws RestClientException {
        return deleteUserFeedbackWithHttpInfo(uuid).getBody();
    }

    /**
     * Removes a user feedback
     * Removes a user feedback
     * <p><b>204</b> - User feedback removed.
     * <p><b>403</b> - Operation not allowed. Only Reviewvers can access it.
     * <p><b>0</b> - default response
     * @param uuid User feedback UUID. (required)
     * @return ResponseEntity&lt;String&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<String> deleteUserFeedbackWithHttpInfo(String uuid) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'uuid' is set
        if (uuid == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'uuid' when calling deleteUserFeedback");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("uuid", uuid);
        String path = apiClient.expandPath("/userfeedback/{uuid}", uriVariables);

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
     * Provides an average rating for a metadata record
     * 
     * <p><b>200</b> - OK
     * <p><b>0</b> - default response
     * @param metadataUuid Metadata record UUID. (required)
     * @return RatingAverage
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RatingAverage getMetadataRating(String metadataUuid) throws RestClientException {
        return getMetadataRatingWithHttpInfo(metadataUuid).getBody();
    }

    /**
     * Provides an average rating for a metadata record
     * 
     * <p><b>200</b> - OK
     * <p><b>0</b> - default response
     * @param metadataUuid Metadata record UUID. (required)
     * @return ResponseEntity&lt;RatingAverage&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RatingAverage> getMetadataRatingWithHttpInfo(String metadataUuid) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'metadataUuid' is set
        if (metadataUuid == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'metadataUuid' when calling getMetadataRating");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("metadataUuid", metadataUuid);
        String path = apiClient.expandPath("/records/{metadataUuid}/userfeedbackrating", uriVariables);

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

        ParameterizedTypeReference<RatingAverage> returnType = new ParameterizedTypeReference<RatingAverage>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Get list of rating criteria
     * 
     * <p><b>200</b> - OK
     * <p><b>0</b> - default response
     * @return List&lt;RatingCriteria&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<RatingCriteria> getRatingCriteria() throws RestClientException {
        return getRatingCriteriaWithHttpInfo().getBody();
    }

    /**
     * Get list of rating criteria
     * 
     * <p><b>200</b> - OK
     * <p><b>0</b> - default response
     * @return ResponseEntity&lt;List&lt;RatingCriteria&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<RatingCriteria>> getRatingCriteriaWithHttpInfo() throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/userfeedback/ratingcriteria", Collections.<String, Object>emptyMap());

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

        ParameterizedTypeReference<List<RatingCriteria>> returnType = new ParameterizedTypeReference<List<RatingCriteria>>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Finds a specific user feedback
     * 
     * <p><b>200</b> - OK
     * <p><b>0</b> - default response
     * @param uuid User feedback UUID. (required)
     * @return UserFeedbackDTO
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public UserFeedbackDTO getUserComment(String uuid) throws RestClientException {
        return getUserCommentWithHttpInfo(uuid).getBody();
    }

    /**
     * Finds a specific user feedback
     * 
     * <p><b>200</b> - OK
     * <p><b>0</b> - default response
     * @param uuid User feedback UUID. (required)
     * @return ResponseEntity&lt;UserFeedbackDTO&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<UserFeedbackDTO> getUserCommentWithHttpInfo(String uuid) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'uuid' is set
        if (uuid == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'uuid' when calling getUserComment");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("uuid", uuid);
        String path = apiClient.expandPath("/userfeedback/{uuid}", uriVariables);

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

        ParameterizedTypeReference<UserFeedbackDTO> returnType = new ParameterizedTypeReference<UserFeedbackDTO>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Finds a list of user feedback records. 
     *  This list will include also the draft user feedback if the client is logged as reviewer.
     * <p><b>200</b> - OK
     * <p><b>0</b> - default response
     * @param metadataUuid Metadata record UUID. (optional, default to &quot;&quot;)
     * @param size Maximum number of feedback to return. (optional, default to -1)
     * @return List&lt;UserFeedbackDTO&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<UserFeedbackDTO> getUserComments(String metadataUuid, Integer size) throws RestClientException {
        return getUserCommentsWithHttpInfo(metadataUuid, size).getBody();
    }

    /**
     * Finds a list of user feedback records. 
     *  This list will include also the draft user feedback if the client is logged as reviewer.
     * <p><b>200</b> - OK
     * <p><b>0</b> - default response
     * @param metadataUuid Metadata record UUID. (optional, default to &quot;&quot;)
     * @param size Maximum number of feedback to return. (optional, default to -1)
     * @return ResponseEntity&lt;List&lt;UserFeedbackDTO&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<UserFeedbackDTO>> getUserCommentsWithHttpInfo(String metadataUuid, Integer size) throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/userfeedback", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "metadataUuid", metadataUuid));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "size", size));

        final String[] accepts = { 
            "application/json"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] { "basicAuth" };

        ParameterizedTypeReference<List<UserFeedbackDTO>> returnType = new ParameterizedTypeReference<List<UserFeedbackDTO>>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Finds a list of user feedback for a specific records. 
     *  This list will include also the draft user feedback if the client is logged as reviewer.
     * <p><b>200</b> - OK
     * <p><b>0</b> - default response
     * @param metadataUuid Metadata record UUID. (required)
     * @param size Maximum number of feedback to return. (optional, default to -1)
     * @return List&lt;UserFeedbackDTO&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<UserFeedbackDTO> getUserCommentsOnARecord(String metadataUuid, Integer size) throws RestClientException {
        return getUserCommentsOnARecordWithHttpInfo(metadataUuid, size).getBody();
    }

    /**
     * Finds a list of user feedback for a specific records. 
     *  This list will include also the draft user feedback if the client is logged as reviewer.
     * <p><b>200</b> - OK
     * <p><b>0</b> - default response
     * @param metadataUuid Metadata record UUID. (required)
     * @param size Maximum number of feedback to return. (optional, default to -1)
     * @return ResponseEntity&lt;List&lt;UserFeedbackDTO&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<UserFeedbackDTO>> getUserCommentsOnARecordWithHttpInfo(String metadataUuid, Integer size) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'metadataUuid' is set
        if (metadataUuid == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'metadataUuid' when calling getUserCommentsOnARecord");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("metadataUuid", metadataUuid);
        String path = apiClient.expandPath("/records/{metadataUuid}/userfeedback", uriVariables);

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

        ParameterizedTypeReference<List<UserFeedbackDTO>> returnType = new ParameterizedTypeReference<List<UserFeedbackDTO>>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Creates a user feedback
     * Creates a user feedback in draft status if the user is not logged in.
     * <p><b>201</b> - Created
     * <p><b>0</b> - default response
     * @param userFeedbackDTO  (required)
     * @return String
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public String newUserFeedback(UserFeedbackDTO userFeedbackDTO) throws RestClientException {
        return newUserFeedbackWithHttpInfo(userFeedbackDTO).getBody();
    }

    /**
     * Creates a user feedback
     * Creates a user feedback in draft status if the user is not logged in.
     * <p><b>201</b> - Created
     * <p><b>0</b> - default response
     * @param userFeedbackDTO  (required)
     * @return ResponseEntity&lt;String&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<String> newUserFeedbackWithHttpInfo(UserFeedbackDTO userFeedbackDTO) throws RestClientException {
        Object postBody = userFeedbackDTO;
        
        // verify the required parameter 'userFeedbackDTO' is set
        if (userFeedbackDTO == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'userFeedbackDTO' when calling newUserFeedback");
        }
        
        String path = apiClient.expandPath("/userfeedback", Collections.<String, Object>emptyMap());

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
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Publishes a feedback
     * For reviewers
     * <p><b>404</b> - Resource not found.
     * <p><b>204</b> - User feedback published.
     * <p><b>403</b> - Operation not allowed. Only Reviewvers can access it.
     * <p><b>0</b> - default response
     * @param uuid User feedback UUID. (required)
     * @return String
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public String publishFeedback(String uuid) throws RestClientException {
        return publishFeedbackWithHttpInfo(uuid).getBody();
    }

    /**
     * Publishes a feedback
     * For reviewers
     * <p><b>404</b> - Resource not found.
     * <p><b>204</b> - User feedback published.
     * <p><b>403</b> - Operation not allowed. Only Reviewvers can access it.
     * <p><b>0</b> - default response
     * @param uuid User feedback UUID. (required)
     * @return ResponseEntity&lt;String&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<String> publishFeedbackWithHttpInfo(String uuid) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'uuid' is set
        if (uuid == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'uuid' when calling publishFeedback");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("uuid", uuid);
        String path = apiClient.expandPath("/userfeedback/{uuid}/publish", uriVariables);

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
     * Send an email to catalogue administrator or record&#39;s contact
     * 
     * <p><b>201</b> - Created
     * <p><b>0</b> - default response
     * @param metadataUuid Metadata record UUID. (required)
     * @param name User name. (required)
     * @param org User organisation. (required)
     * @param email User email address. (required)
     * @param comments A comment or question. (required)
     * @param recaptcha Recaptcha validation key. (optional, default to &quot;&quot;)
     * @param phone User phone number. (optional, default to &quot;&quot;)
     * @param subject Email subject. (optional, default to &quot;User feedback&quot;)
     * @param function User function. (optional, default to &quot;-&quot;)
     * @param type Comment type. (optional, default to &quot;-&quot;)
     * @param category Comment category. (optional, default to &quot;-&quot;)
     * @param metadataEmail List of record&#39;s contact to send this email. (optional, default to &quot;&quot;)
     * @return String
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public String sendEmailToContact(String metadataUuid, String name, String org, String email, String comments, String recaptcha, String phone, String subject, String function, String type, String category, String metadataEmail) throws RestClientException {
        return sendEmailToContactWithHttpInfo(metadataUuid, name, org, email, comments, recaptcha, phone, subject, function, type, category, metadataEmail).getBody();
    }

    /**
     * Send an email to catalogue administrator or record&#39;s contact
     * 
     * <p><b>201</b> - Created
     * <p><b>0</b> - default response
     * @param metadataUuid Metadata record UUID. (required)
     * @param name User name. (required)
     * @param org User organisation. (required)
     * @param email User email address. (required)
     * @param comments A comment or question. (required)
     * @param recaptcha Recaptcha validation key. (optional, default to &quot;&quot;)
     * @param phone User phone number. (optional, default to &quot;&quot;)
     * @param subject Email subject. (optional, default to &quot;User feedback&quot;)
     * @param function User function. (optional, default to &quot;-&quot;)
     * @param type Comment type. (optional, default to &quot;-&quot;)
     * @param category Comment category. (optional, default to &quot;-&quot;)
     * @param metadataEmail List of record&#39;s contact to send this email. (optional, default to &quot;&quot;)
     * @return ResponseEntity&lt;String&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<String> sendEmailToContactWithHttpInfo(String metadataUuid, String name, String org, String email, String comments, String recaptcha, String phone, String subject, String function, String type, String category, String metadataEmail) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'metadataUuid' is set
        if (metadataUuid == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'metadataUuid' when calling sendEmailToContact");
        }
        
        // verify the required parameter 'name' is set
        if (name == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'name' when calling sendEmailToContact");
        }
        
        // verify the required parameter 'org' is set
        if (org == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'org' when calling sendEmailToContact");
        }
        
        // verify the required parameter 'email' is set
        if (email == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'email' when calling sendEmailToContact");
        }
        
        // verify the required parameter 'comments' is set
        if (comments == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'comments' when calling sendEmailToContact");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("metadataUuid", metadataUuid);
        String path = apiClient.expandPath("/records/{metadataUuid}/alert", uriVariables);

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "recaptcha", recaptcha));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "name", name));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "org", org));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "email", email));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "comments", comments));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "phone", phone));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "subject", subject));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "function", function));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "type", type));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "category", category));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "metadataEmail", metadataEmail));

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
}
