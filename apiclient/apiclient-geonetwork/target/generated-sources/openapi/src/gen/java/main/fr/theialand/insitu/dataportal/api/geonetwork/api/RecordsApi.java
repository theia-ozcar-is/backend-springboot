package fr.theialand.insitu.dataportal.api.geonetwork.api;

import fr.theialand.insitu.dataportal.api.geonetwork.handler.ApiClient;

import fr.theialand.insitu.dataportal.api.geonetwork.model.BatchEditParameter;
import fr.theialand.insitu.dataportal.api.geonetwork.model.Element;
import fr.theialand.insitu.dataportal.api.geonetwork.model.ExtentDto;
import fr.theialand.insitu.dataportal.api.geonetwork.model.FeatureResponse;
import java.io.File;
import fr.theialand.insitu.dataportal.api.geonetwork.model.IProcessingReport;
import fr.theialand.insitu.dataportal.api.geonetwork.model.InlineObject1;
import fr.theialand.insitu.dataportal.api.geonetwork.model.MetadataCategory;
import fr.theialand.insitu.dataportal.api.geonetwork.model.MetadataProcessingReport;
import fr.theialand.insitu.dataportal.api.geonetwork.model.MetadataResource;
import fr.theialand.insitu.dataportal.api.geonetwork.model.MetadataStatusResponse;
import fr.theialand.insitu.dataportal.api.geonetwork.model.MetadataWorkflowStatusResponse;
import fr.theialand.insitu.dataportal.api.geonetwork.model.RelatedResponse;
import fr.theialand.insitu.dataportal.api.geonetwork.model.Reports;
import fr.theialand.insitu.dataportal.api.geonetwork.model.SavedQuery;
import fr.theialand.insitu.dataportal.api.geonetwork.model.SharingParameter;
import fr.theialand.insitu.dataportal.api.geonetwork.model.SharingResponse;
import fr.theialand.insitu.dataportal.api.geonetwork.model.SimpleMetadataProcessingReport;
import fr.theialand.insitu.dataportal.api.geonetwork.model.SuggestionType;

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
@Component("fr.theialand.insitu.dataportal.api.geonetwork.api.RecordsApi")
public class RecordsApi {
    private ApiClient apiClient;

    public RecordsApi() {
        this(new ApiClient());
    }

    @Autowired
    public RecordsApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Reorder element
     * 
     * <p><b>201</b> - Element reordered.
     * <p><b>403</b> - Operation not allowed. User needs to be able to edit the resource.
     * <p><b>0</b> - default response
     * @param metadataUuid Record UUID. (required)
     * @param ref Reference of the element to move. (required)
     * @param direction Direction (required)
     * @param displayAttributes Should attributes be shown on the editor snippet? (optional, default to false)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void addElement(String metadataUuid, String ref, String direction, Boolean displayAttributes) throws RestClientException {
        addElementWithHttpInfo(metadataUuid, ref, direction, displayAttributes);
    }

    /**
     * Reorder element
     * 
     * <p><b>201</b> - Element reordered.
     * <p><b>403</b> - Operation not allowed. User needs to be able to edit the resource.
     * <p><b>0</b> - default response
     * @param metadataUuid Record UUID. (required)
     * @param ref Reference of the element to move. (required)
     * @param direction Direction (required)
     * @param displayAttributes Should attributes be shown on the editor snippet? (optional, default to false)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> addElementWithHttpInfo(String metadataUuid, String ref, String direction, Boolean displayAttributes) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'metadataUuid' is set
        if (metadataUuid == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'metadataUuid' when calling addElement");
        }
        
        // verify the required parameter 'ref' is set
        if (ref == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'ref' when calling addElement");
        }
        
        // verify the required parameter 'direction' is set
        if (direction == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'direction' when calling addElement");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("metadataUuid", metadataUuid);
        uriVariables.put("direction", direction);
        String path = apiClient.expandPath("/records/{metadataUuid}/editor/elements/{direction}", uriVariables);

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "ref", ref));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "displayAttributes", displayAttributes));

        final String[] accepts = { 
            "application/json"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] { "basicAuth" };

        ParameterizedTypeReference<Void> returnType = new ParameterizedTypeReference<Void>() {};
        return apiClient.invokeAPI(path, HttpMethod.PUT, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Add samples
     * Add sample records for one or more schemas. Samples are defined for each standard in the samples folder as MEF files.
     * <p><b>201</b> - Return a report of what has been done.
     * <p><b>403</b> - Operation not allowed. Only Administrators can access it.
     * <p><b>0</b> - default response
     * @param schema Schema identifiers (required)
     * @return SimpleMetadataProcessingReport
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public SimpleMetadataProcessingReport addSamples(List<String> schema) throws RestClientException {
        return addSamplesWithHttpInfo(schema).getBody();
    }

    /**
     * Add samples
     * Add sample records for one or more schemas. Samples are defined for each standard in the samples folder as MEF files.
     * <p><b>201</b> - Return a report of what has been done.
     * <p><b>403</b> - Operation not allowed. Only Administrators can access it.
     * <p><b>0</b> - default response
     * @param schema Schema identifiers (required)
     * @return ResponseEntity&lt;SimpleMetadataProcessingReport&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<SimpleMetadataProcessingReport> addSamplesWithHttpInfo(List<String> schema) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'schema' is set
        if (schema == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'schema' when calling addSamples");
        }
        
        String path = apiClient.expandPath("/records/samples", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        queryParams.putAll(apiClient.parameterToMultiValueMap(ApiClient.CollectionFormat.valueOf("multi".toUpperCase(Locale.ROOT)), "schema", schema));

        final String[] accepts = { 
            "application/json", "*/*"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] { "basicAuth" };

        ParameterizedTypeReference<SimpleMetadataProcessingReport> returnType = new ParameterizedTypeReference<SimpleMetadataProcessingReport>() {};
        return apiClient.invokeAPI(path, HttpMethod.PUT, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Add templates
     * Add template records for one or more schemas. Templates are defined for each standard in the template folder as XML files. Template may also contains subtemplates.
     * <p><b>201</b> - Return a report of what has been done.
     * <p><b>403</b> - Operation not allowed. Only Administrators can access it.
     * <p><b>0</b> - default response
     * @param schema Schema identifiers (required)
     * @return SimpleMetadataProcessingReport
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public SimpleMetadataProcessingReport addTemplates(List<String> schema) throws RestClientException {
        return addTemplatesWithHttpInfo(schema).getBody();
    }

    /**
     * Add templates
     * Add template records for one or more schemas. Templates are defined for each standard in the template folder as XML files. Template may also contains subtemplates.
     * <p><b>201</b> - Return a report of what has been done.
     * <p><b>403</b> - Operation not allowed. Only Administrators can access it.
     * <p><b>0</b> - default response
     * @param schema Schema identifiers (required)
     * @return ResponseEntity&lt;SimpleMetadataProcessingReport&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<SimpleMetadataProcessingReport> addTemplatesWithHttpInfo(List<String> schema) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'schema' is set
        if (schema == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'schema' when calling addTemplates");
        }
        
        String path = apiClient.expandPath("/records/templates", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        queryParams.putAll(apiClient.parameterToMultiValueMap(ApiClient.CollectionFormat.valueOf("multi".toUpperCase(Locale.ROOT)), "schema", schema));

        final String[] accepts = { 
            "application/json", "*/*"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] { "basicAuth" };

        ParameterizedTypeReference<SimpleMetadataProcessingReport> returnType = new ParameterizedTypeReference<SimpleMetadataProcessingReport>() {};
        return apiClient.invokeAPI(path, HttpMethod.PUT, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Apply a saved query for this metadata
     * All parameters will be substituted to the XPath query. eg. {{protocol}} in the XPath expression will be replaced by the protocol parameter provided in the request body.
     * <p><b>200</b> - List of matching elements. If element are nodes, then they are returned as string.
     * <p><b>403</b> - Operation not allowed. User needs to be able to view the resource.
     * <p><b>0</b> - default response
     * @param metadataUuid The metadata UUID (required)
     * @param savedQuery The saved query to apply (required)
     * @return Map&lt;String, String&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Map<String, String> applyQuery(String metadataUuid, String savedQuery) throws RestClientException {
        return applyQueryWithHttpInfo(metadataUuid, savedQuery).getBody();
    }

    /**
     * Apply a saved query for this metadata
     * All parameters will be substituted to the XPath query. eg. {{protocol}} in the XPath expression will be replaced by the protocol parameter provided in the request body.
     * <p><b>200</b> - List of matching elements. If element are nodes, then they are returned as string.
     * <p><b>403</b> - Operation not allowed. User needs to be able to view the resource.
     * <p><b>0</b> - default response
     * @param metadataUuid The metadata UUID (required)
     * @param savedQuery The saved query to apply (required)
     * @return ResponseEntity&lt;Map&lt;String, String&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Map<String, String>> applyQueryWithHttpInfo(String metadataUuid, String savedQuery) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'metadataUuid' is set
        if (metadataUuid == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'metadataUuid' when calling applyQuery");
        }
        
        // verify the required parameter 'savedQuery' is set
        if (savedQuery == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'savedQuery' when calling applyQuery");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("metadataUuid", metadataUuid);
        uriVariables.put("savedQuery", savedQuery);
        String path = apiClient.expandPath("/records/{metadataUuid}/query/{savedQuery}", uriVariables);

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
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Edit a set of records by XPath expressions. This operations applies the update-fixed-info.xsl transformation for the metadata schema and updates the change date if the parameter updateDateStamp is set to true.
     * 
     * <p><b>201</b> - Return a report of what has been done.
     * <p><b>403</b> - Operation not allowed. User needs to be able to edit the resource.
     * <p><b>0</b> - default response
     * @param batchEditParameter  (required)
     * @param uuids Record UUIDs. If null current selection is used. (optional, default to new ArrayList&lt;&gt;())
     * @param bucket Selection bucket name (optional)
     * @param updateDateStamp If true updates the DateStamp (or equivalent in standards different to ISO 19139) field in the metadata with the current timestamp (optional, default to false)
     * @return IProcessingReport
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public IProcessingReport batchEdit(List<BatchEditParameter> batchEditParameter, List<String> uuids, String bucket, Boolean updateDateStamp) throws RestClientException {
        return batchEditWithHttpInfo(batchEditParameter, uuids, bucket, updateDateStamp).getBody();
    }

    /**
     * Edit a set of records by XPath expressions. This operations applies the update-fixed-info.xsl transformation for the metadata schema and updates the change date if the parameter updateDateStamp is set to true.
     * 
     * <p><b>201</b> - Return a report of what has been done.
     * <p><b>403</b> - Operation not allowed. User needs to be able to edit the resource.
     * <p><b>0</b> - default response
     * @param batchEditParameter  (required)
     * @param uuids Record UUIDs. If null current selection is used. (optional, default to new ArrayList&lt;&gt;())
     * @param bucket Selection bucket name (optional)
     * @param updateDateStamp If true updates the DateStamp (or equivalent in standards different to ISO 19139) field in the metadata with the current timestamp (optional, default to false)
     * @return ResponseEntity&lt;IProcessingReport&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<IProcessingReport> batchEditWithHttpInfo(List<BatchEditParameter> batchEditParameter, List<String> uuids, String bucket, Boolean updateDateStamp) throws RestClientException {
        Object postBody = batchEditParameter;
        
        // verify the required parameter 'batchEditParameter' is set
        if (batchEditParameter == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'batchEditParameter' when calling batchEdit");
        }
        
        String path = apiClient.expandPath("/records/batchediting", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        queryParams.putAll(apiClient.parameterToMultiValueMap(ApiClient.CollectionFormat.valueOf("multi".toUpperCase(Locale.ROOT)), "uuids", uuids));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "bucket", bucket));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "updateDateStamp", updateDateStamp));

        final String[] accepts = { 
            "application/json"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { 
            "application/json"
        };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] { "basicAuth" };

        ParameterizedTypeReference<IProcessingReport> returnType = new ParameterizedTypeReference<IProcessingReport>() {};
        return apiClient.invokeAPI(path, HttpMethod.PUT, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Cancel edits
     * Cancel current editing session.
     * <p><b>403</b> - Operation not allowed. User needs to be able to edit the resource.
     * <p><b>204</b> - Editing session cancelled.
     * <p><b>0</b> - default response
     * @param metadataUuid Record UUID. (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void cancelEdits(String metadataUuid) throws RestClientException {
        cancelEditsWithHttpInfo(metadataUuid);
    }

    /**
     * Cancel edits
     * Cancel current editing session.
     * <p><b>403</b> - Operation not allowed. User needs to be able to edit the resource.
     * <p><b>204</b> - Editing session cancelled.
     * <p><b>0</b> - default response
     * @param metadataUuid Record UUID. (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> cancelEditsWithHttpInfo(String metadataUuid) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'metadataUuid' is set
        if (metadataUuid == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'metadataUuid' when calling cancelEdits");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("metadataUuid", metadataUuid);
        String path = apiClient.expandPath("/records/{metadataUuid}/editor", uriVariables);

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
     * Check that a record can be submitted to DataCite for DOI creation. DataCite requires some fields to be populated.
     * 
     * <p><b>200</b> - Record can be proposed to DataCite.
     * <p><b>404</b> - Metadata not found.
     * <p><b>403</b> - Operation not allowed. User needs to be able to edit the resource.
     * <p><b>500</b> - Service unavailable.
     * <p><b>400</b> - Record does not meet preconditions. Check error message.
     * <p><b>0</b> - default response
     * @param metadataUuid Record UUID. (required)
     * @return Map&lt;String, Boolean&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Map<String, Boolean> checkDoiStatus(String metadataUuid) throws RestClientException {
        return checkDoiStatusWithHttpInfo(metadataUuid).getBody();
    }

    /**
     * Check that a record can be submitted to DataCite for DOI creation. DataCite requires some fields to be populated.
     * 
     * <p><b>200</b> - Record can be proposed to DataCite.
     * <p><b>404</b> - Metadata not found.
     * <p><b>403</b> - Operation not allowed. User needs to be able to edit the resource.
     * <p><b>500</b> - Service unavailable.
     * <p><b>400</b> - Record does not meet preconditions. Check error message.
     * <p><b>0</b> - default response
     * @param metadataUuid Record UUID. (required)
     * @return ResponseEntity&lt;Map&lt;String, Boolean&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Map<String, Boolean>> checkDoiStatusWithHttpInfo(String metadataUuid) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'metadataUuid' is set
        if (metadataUuid == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'metadataUuid' when calling checkDoiStatus");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("metadataUuid", metadataUuid);
        String path = apiClient.expandPath("/records/{metadataUuid}/doi/checkPreConditions", uriVariables);

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

        ParameterizedTypeReference<Map<String, Boolean>> returnType = new ParameterizedTypeReference<Map<String, Boolean>>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Check the status of validation with the INSPIRE service.
     * User MUST be able to edit the record to validate it. An INSPIRE endpoint must be configured in Settings. If the process is complete an object with status is returned. 
     * <p><b>200</b> - Report ready.
     * <p><b>403</b> - Operation not allowed. User needs to be able to edit the resource.
     * <p><b>201</b> - Report not ready.
     * <p><b>404</b> - Report id not found.
     * <p><b>0</b> - default response
     * @param testId Test identifier (required)
     * @return Map&lt;String, String&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Map<String, String> checkValidation(String testId) throws RestClientException {
        return checkValidationWithHttpInfo(testId).getBody();
    }

    /**
     * Check the status of validation with the INSPIRE service.
     * User MUST be able to edit the record to validate it. An INSPIRE endpoint must be configured in Settings. If the process is complete an object with status is returned. 
     * <p><b>200</b> - Report ready.
     * <p><b>403</b> - Operation not allowed. User needs to be able to edit the resource.
     * <p><b>201</b> - Report not ready.
     * <p><b>404</b> - Report id not found.
     * <p><b>0</b> - default response
     * @param testId Test identifier (required)
     * @return ResponseEntity&lt;Map&lt;String, String&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Map<String, String>> checkValidationWithHttpInfo(String testId) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'testId' is set
        if (testId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'testId' when calling checkValidation");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("testId", testId);
        String path = apiClient.expandPath("/records/{testId}/validate/inspire", uriVariables);

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
     * Create a new record
     * Create a record from a template or by copying an existing record.Return the UUID of the newly created record. Existing links in the source record are preserved, this means that the new record may contains link to the source attachments. They need to be manually updated after creation.
     * <p><b>201</b> - Return the internal id of the newly created record.
     * <p><b>403</b> - Operation not allowed. Only Editors can access it.
     * <p><b>0</b> - default response
     * @param sourceUuid UUID of the source record to copy. (required)
     * @param group The group the record is attached to. (required)
     * @param metadataType The type of record. (optional, default to METADATA)
     * @param targetUuid Assign a custom UUID. If this UUID already exist an error is returned. This is enabled only if metadata create / generate UUID settings is activated. (optional)
     * @param isVisibleByAllGroupMembers Is published to all user group members? If not, only the author and administrator can edit the record. (optional, default to false)
     * @param category Tags to assign to the record. (optional, default to new ArrayList&lt;&gt;())
     * @param hasCategoryOfSource Copy categories from source? (optional, default to false)
     * @param isChildOfSource Is child of the record to copy? (optional, default to false)
     * @return String
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public String create(String sourceUuid, String group, String metadataType, String targetUuid, Boolean isVisibleByAllGroupMembers, List<String> category, Boolean hasCategoryOfSource, Boolean isChildOfSource) throws RestClientException {
        return createWithHttpInfo(sourceUuid, group, metadataType, targetUuid, isVisibleByAllGroupMembers, category, hasCategoryOfSource, isChildOfSource).getBody();
    }

    /**
     * Create a new record
     * Create a record from a template or by copying an existing record.Return the UUID of the newly created record. Existing links in the source record are preserved, this means that the new record may contains link to the source attachments. They need to be manually updated after creation.
     * <p><b>201</b> - Return the internal id of the newly created record.
     * <p><b>403</b> - Operation not allowed. Only Editors can access it.
     * <p><b>0</b> - default response
     * @param sourceUuid UUID of the source record to copy. (required)
     * @param group The group the record is attached to. (required)
     * @param metadataType The type of record. (optional, default to METADATA)
     * @param targetUuid Assign a custom UUID. If this UUID already exist an error is returned. This is enabled only if metadata create / generate UUID settings is activated. (optional)
     * @param isVisibleByAllGroupMembers Is published to all user group members? If not, only the author and administrator can edit the record. (optional, default to false)
     * @param category Tags to assign to the record. (optional, default to new ArrayList&lt;&gt;())
     * @param hasCategoryOfSource Copy categories from source? (optional, default to false)
     * @param isChildOfSource Is child of the record to copy? (optional, default to false)
     * @return ResponseEntity&lt;String&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<String> createWithHttpInfo(String sourceUuid, String group, String metadataType, String targetUuid, Boolean isVisibleByAllGroupMembers, List<String> category, Boolean hasCategoryOfSource, Boolean isChildOfSource) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'sourceUuid' is set
        if (sourceUuid == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'sourceUuid' when calling create");
        }
        
        // verify the required parameter 'group' is set
        if (group == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'group' when calling create");
        }
        
        String path = apiClient.expandPath("/records/duplicate", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "metadataType", metadataType));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "sourceUuid", sourceUuid));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "targetUuid", targetUuid));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "group", group));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "isVisibleByAllGroupMembers", isVisibleByAllGroupMembers));
        queryParams.putAll(apiClient.parameterToMultiValueMap(ApiClient.CollectionFormat.valueOf("multi".toUpperCase(Locale.ROOT)), "category", category));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "hasCategoryOfSource", hasCategoryOfSource));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "isChildOfSource", isChildOfSource));

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
     * Submit a record to the Datacite metadata store in order to create a DOI.
     * 
     * <p><b>404</b> - Metadata not found.
     * <p><b>403</b> - Operation not allowed. User needs to be able to edit the resource.
     * <p><b>500</b> - Service unavailable.
     * <p><b>201</b> - Check status of the report.
     * <p><b>0</b> - default response
     * @param metadataUuid Record UUID. (required)
     * @return Map&lt;String, String&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Map<String, String> createDoi(String metadataUuid) throws RestClientException {
        return createDoiWithHttpInfo(metadataUuid).getBody();
    }

    /**
     * Submit a record to the Datacite metadata store in order to create a DOI.
     * 
     * <p><b>404</b> - Metadata not found.
     * <p><b>403</b> - Operation not allowed. User needs to be able to edit the resource.
     * <p><b>500</b> - Service unavailable.
     * <p><b>201</b> - Check status of the report.
     * <p><b>0</b> - default response
     * @param metadataUuid Record UUID. (required)
     * @return ResponseEntity&lt;Map&lt;String, String&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Map<String, String>> createDoiWithHttpInfo(String metadataUuid) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'metadataUuid' is set
        if (metadataUuid == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'metadataUuid' when calling createDoi");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("metadataUuid", metadataUuid);
        String path = apiClient.expandPath("/records/{metadataUuid}/doi", uriVariables);

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
        return apiClient.invokeAPI(path, HttpMethod.PUT, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Delete attribute
     * 
     * <p><b>204</b> - Attribute removed.
     * <p><b>403</b> - Operation not allowed. User needs to be able to edit the resource.
     * <p><b>0</b> - default response
     * @param metadataUuid Record UUID. (required)
     * @param ref Reference of the attribute to remove. (required)
     * @param displayAttributes Should attributes be shown on the editor snippet? (optional, default to false)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void deleteAttribute(String metadataUuid, String ref, Boolean displayAttributes) throws RestClientException {
        deleteAttributeWithHttpInfo(metadataUuid, ref, displayAttributes);
    }

    /**
     * Delete attribute
     * 
     * <p><b>204</b> - Attribute removed.
     * <p><b>403</b> - Operation not allowed. User needs to be able to edit the resource.
     * <p><b>0</b> - default response
     * @param metadataUuid Record UUID. (required)
     * @param ref Reference of the attribute to remove. (required)
     * @param displayAttributes Should attributes be shown on the editor snippet? (optional, default to false)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> deleteAttributeWithHttpInfo(String metadataUuid, String ref, Boolean displayAttributes) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'metadataUuid' is set
        if (metadataUuid == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'metadataUuid' when calling deleteAttribute");
        }
        
        // verify the required parameter 'ref' is set
        if (ref == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'ref' when calling deleteAttribute");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("metadataUuid", metadataUuid);
        String path = apiClient.expandPath("/records/{metadataUuid}/editor/attributes", uriVariables);

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "ref", ref));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "displayAttributes", displayAttributes));

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
     * Delete element
     * 
     * <p><b>204</b> - Element removed.
     * <p><b>403</b> - Operation not allowed. User needs to be able to edit the resource.
     * <p><b>0</b> - default response
     * @param metadataUuid Record UUID. (required)
     * @param ref Reference of the element to remove. (required)
     * @param parent Name of the parent. (required)
     * @param displayAttributes Should attributes be shown on the editor snippet? (optional, default to false)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void deleteElement(String metadataUuid, List<String> ref, String parent, Boolean displayAttributes) throws RestClientException {
        deleteElementWithHttpInfo(metadataUuid, ref, parent, displayAttributes);
    }

    /**
     * Delete element
     * 
     * <p><b>204</b> - Element removed.
     * <p><b>403</b> - Operation not allowed. User needs to be able to edit the resource.
     * <p><b>0</b> - default response
     * @param metadataUuid Record UUID. (required)
     * @param ref Reference of the element to remove. (required)
     * @param parent Name of the parent. (required)
     * @param displayAttributes Should attributes be shown on the editor snippet? (optional, default to false)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> deleteElementWithHttpInfo(String metadataUuid, List<String> ref, String parent, Boolean displayAttributes) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'metadataUuid' is set
        if (metadataUuid == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'metadataUuid' when calling deleteElement");
        }
        
        // verify the required parameter 'ref' is set
        if (ref == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'ref' when calling deleteElement");
        }
        
        // verify the required parameter 'parent' is set
        if (parent == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'parent' when calling deleteElement");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("metadataUuid", metadataUuid);
        String path = apiClient.expandPath("/records/{metadataUuid}/editor/elements", uriVariables);

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        queryParams.putAll(apiClient.parameterToMultiValueMap(ApiClient.CollectionFormat.valueOf("multi".toUpperCase(Locale.ROOT)), "ref", ref));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "parent", parent));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "displayAttributes", displayAttributes));

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
     * Delete one or more records
     * User MUST be able to edit the record to delete it. 
     * <p><b>403</b> - Operation not allowed. Only Editors can access it.
     * <p><b>204</b> - Report about deleted records.
     * <p><b>0</b> - default response
     * @param uuids Record UUIDs. If null current selection is used. (optional, default to new ArrayList&lt;&gt;())
     * @param bucket Selection bucket name (optional)
     * @param withBackup Backup first the record as MEF in the metadata removed folder. (optional, default to true)
     * @return SimpleMetadataProcessingReport
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public SimpleMetadataProcessingReport deleteRecords(List<String> uuids, String bucket, Boolean withBackup) throws RestClientException {
        return deleteRecordsWithHttpInfo(uuids, bucket, withBackup).getBody();
    }

    /**
     * Delete one or more records
     * User MUST be able to edit the record to delete it. 
     * <p><b>403</b> - Operation not allowed. Only Editors can access it.
     * <p><b>204</b> - Report about deleted records.
     * <p><b>0</b> - default response
     * @param uuids Record UUIDs. If null current selection is used. (optional, default to new ArrayList&lt;&gt;())
     * @param bucket Selection bucket name (optional)
     * @param withBackup Backup first the record as MEF in the metadata removed folder. (optional, default to true)
     * @return ResponseEntity&lt;SimpleMetadataProcessingReport&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<SimpleMetadataProcessingReport> deleteRecordsWithHttpInfo(List<String> uuids, String bucket, Boolean withBackup) throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/records", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        queryParams.putAll(apiClient.parameterToMultiValueMap(ApiClient.CollectionFormat.valueOf("multi".toUpperCase(Locale.ROOT)), "uuids", uuids));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "bucket", bucket));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "withBackup", withBackup));

        final String[] accepts = { 
            "application/json"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] { "basicAuth" };

        ParameterizedTypeReference<SimpleMetadataProcessingReport> returnType = new ParameterizedTypeReference<SimpleMetadataProcessingReport>() {};
        return apiClient.invokeAPI(path, HttpMethod.DELETE, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Delete tags to one or more records
     * 
     * <p><b>403</b> - Operation not allowed. Only Editors can access it.
     * <p><b>201</b> - Report about removed records.
     * <p><b>0</b> - default response
     * @param id Tag identifier (required)
     * @param uuids Record UUIDs. If null current selection is used. (optional, default to new ArrayList&lt;&gt;())
     * @param bucket Selection bucket name (optional)
     * @return MetadataProcessingReport
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public MetadataProcessingReport deleteTagForRecords(List<Integer> id, List<String> uuids, String bucket) throws RestClientException {
        return deleteTagForRecordsWithHttpInfo(id, uuids, bucket).getBody();
    }

    /**
     * Delete tags to one or more records
     * 
     * <p><b>403</b> - Operation not allowed. Only Editors can access it.
     * <p><b>201</b> - Report about removed records.
     * <p><b>0</b> - default response
     * @param id Tag identifier (required)
     * @param uuids Record UUIDs. If null current selection is used. (optional, default to new ArrayList&lt;&gt;())
     * @param bucket Selection bucket name (optional)
     * @return ResponseEntity&lt;MetadataProcessingReport&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<MetadataProcessingReport> deleteTagForRecordsWithHttpInfo(List<Integer> id, List<String> uuids, String bucket) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling deleteTagForRecords");
        }
        
        String path = apiClient.expandPath("/records/tags", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        queryParams.putAll(apiClient.parameterToMultiValueMap(ApiClient.CollectionFormat.valueOf("multi".toUpperCase(Locale.ROOT)), "uuids", uuids));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "bucket", bucket));
        queryParams.putAll(apiClient.parameterToMultiValueMap(ApiClient.CollectionFormat.valueOf("multi".toUpperCase(Locale.ROOT)), "id", id));

        final String[] accepts = { 
            "application/json"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] { "basicAuth" };

        ParameterizedTypeReference<MetadataProcessingReport> returnType = new ParameterizedTypeReference<MetadataProcessingReport>() {};
        return apiClient.invokeAPI(path, HttpMethod.DELETE, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Delete tags of a record
     * 
     * <p><b>204</b> - Record tags removed.
     * <p><b>403</b> - Operation not allowed. User needs to be able to edit the resource.
     * <p><b>0</b> - default response
     * @param metadataUuid Record UUID. (required)
     * @param id Tag identifier. If none, all tags are removed. (optional, default to new ArrayList&lt;&gt;())
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void deleteTags(String metadataUuid, List<Integer> id) throws RestClientException {
        deleteTagsWithHttpInfo(metadataUuid, id);
    }

    /**
     * Delete tags of a record
     * 
     * <p><b>204</b> - Record tags removed.
     * <p><b>403</b> - Operation not allowed. User needs to be able to edit the resource.
     * <p><b>0</b> - default response
     * @param metadataUuid Record UUID. (required)
     * @param id Tag identifier. If none, all tags are removed. (optional, default to new ArrayList&lt;&gt;())
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> deleteTagsWithHttpInfo(String metadataUuid, List<Integer> id) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'metadataUuid' is set
        if (metadataUuid == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'metadataUuid' when calling deleteTags");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("metadataUuid", metadataUuid);
        String path = apiClient.expandPath("/records/{metadataUuid}/tags", uriVariables);

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        queryParams.putAll(apiClient.parameterToMultiValueMap(ApiClient.CollectionFormat.valueOf("multi".toUpperCase(Locale.ROOT)), "id", id));

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
     * Download MEF backup archive
     * The backup contains all metadata not harvested including templates.
     * <p><b>404</b> - Resource not found.
     * <p><b>0</b> - default response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void downloadBackup() throws RestClientException {
        downloadBackupWithHttpInfo();
    }

    /**
     * Download MEF backup archive
     * The backup contains all metadata not harvested including templates.
     * <p><b>404</b> - Resource not found.
     * <p><b>0</b> - default response
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> downloadBackupWithHttpInfo() throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/records/backups/latest", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        final String[] accepts = { 
            "application/json", "application/zip"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] { "basicAuth" };

        ParameterizedTypeReference<Void> returnType = new ParameterizedTypeReference<Void>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * (Experimental) Enable version control
     * 
     * <p><b>200</b> - OK
     * <p><b>0</b> - default response
     * @param metadataUuid Record UUID. (required)
     * @return String
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public String enableVersionControl(String metadataUuid) throws RestClientException {
        return enableVersionControlWithHttpInfo(metadataUuid).getBody();
    }

    /**
     * (Experimental) Enable version control
     * 
     * <p><b>200</b> - OK
     * <p><b>0</b> - default response
     * @param metadataUuid Record UUID. (required)
     * @return ResponseEntity&lt;String&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<String> enableVersionControlWithHttpInfo(String metadataUuid) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'metadataUuid' is set
        if (metadataUuid == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'metadataUuid' when calling enableVersionControl");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("metadataUuid", metadataUuid);
        String path = apiClient.expandPath("/records/{metadataUuid}/versions", uriVariables);

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        final String[] accepts = { 
            "application/json", "*/*"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] { "basicAuth" };

        ParameterizedTypeReference<String> returnType = new ParameterizedTypeReference<String>() {};
        return apiClient.invokeAPI(path, HttpMethod.PUT, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * (Experimental) Enable version control for one or more records
     * 
     * <p><b>200</b> - OK
     * <p><b>0</b> - default response
     * @param uuids Record UUIDs. If null current selection is used. (optional, default to new ArrayList&lt;&gt;())
     * @param bucket Selection bucket name (optional)
     * @return MetadataProcessingReport
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public MetadataProcessingReport enableVersionControlForRecords(List<String> uuids, String bucket) throws RestClientException {
        return enableVersionControlForRecordsWithHttpInfo(uuids, bucket).getBody();
    }

    /**
     * (Experimental) Enable version control for one or more records
     * 
     * <p><b>200</b> - OK
     * <p><b>0</b> - default response
     * @param uuids Record UUIDs. If null current selection is used. (optional, default to new ArrayList&lt;&gt;())
     * @param bucket Selection bucket name (optional)
     * @return ResponseEntity&lt;MetadataProcessingReport&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<MetadataProcessingReport> enableVersionControlForRecordsWithHttpInfo(List<String> uuids, String bucket) throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/records/versions", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        queryParams.putAll(apiClient.parameterToMultiValueMap(ApiClient.CollectionFormat.valueOf("multi".toUpperCase(Locale.ROOT)), "uuids", uuids));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "bucket", bucket));

        final String[] accepts = { 
            "application/json"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] { "basicAuth" };

        ParameterizedTypeReference<MetadataProcessingReport> returnType = new ParameterizedTypeReference<MetadataProcessingReport>() {};
        return apiClient.invokeAPI(path, HttpMethod.PUT, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Get a set of metadata records as CSV
     * The CSV is a short summary of each records.
     * <p><b>200</b> - Return requested records as CSV.
     * <p><b>403</b> - Operation not allowed. User needs to be able to view the resource.
     * <p><b>0</b> - default response
     * @param uuids Record UUIDs. If null current selection is used. (optional, default to new ArrayList&lt;&gt;())
     * @param bucket Selection bucket name (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void exportAsCsv(List<String> uuids, String bucket) throws RestClientException {
        exportAsCsvWithHttpInfo(uuids, bucket);
    }

    /**
     * Get a set of metadata records as CSV
     * The CSV is a short summary of each records.
     * <p><b>200</b> - Return requested records as CSV.
     * <p><b>403</b> - Operation not allowed. User needs to be able to view the resource.
     * <p><b>0</b> - default response
     * @param uuids Record UUIDs. If null current selection is used. (optional, default to new ArrayList&lt;&gt;())
     * @param bucket Selection bucket name (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> exportAsCsvWithHttpInfo(List<String> uuids, String bucket) throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/records/csv", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        queryParams.putAll(apiClient.parameterToMultiValueMap(ApiClient.CollectionFormat.valueOf("multi".toUpperCase(Locale.ROOT)), "uuids", uuids));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "bucket", bucket));

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
     * Get a set of metadata records as ZIP
     * Metadata Exchange Format (MEF) is returned. MEF is a ZIP file containing the metadata as XML and some others files depending on the version requested. See http://geonetwork-opensource.org/manuals/trunk/eng/users/annexes/mef-format.html.
     * <p><b>200</b> - Return requested records as ZIP.
     * <p><b>403</b> - Operation not allowed. User needs to be able to view the resource.
     * <p><b>0</b> - other response
     * @param uuids Record UUIDs. If null current selection is used. (optional, default to new ArrayList&lt;&gt;())
     * @param bucket Selection bucket name (optional)
     * @param format MEF file format. (optional, default to FULL)
     * @param withRelated With related records (parent and service). (optional, default to false)
     * @param withXLinksResolved Resolve XLinks in the records. (optional, default to true)
     * @param withXLinkAttribute Preserve XLink URLs in the records. (optional, default to false)
     * @param addSchemaLocation  (optional, default to true)
     * @param approved Download the approved version (optional, default to true)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void exportAsMef(List<String> uuids, String bucket, String format, Boolean withRelated, Boolean withXLinksResolved, Boolean withXLinkAttribute, Boolean addSchemaLocation, Boolean approved) throws RestClientException {
        exportAsMefWithHttpInfo(uuids, bucket, format, withRelated, withXLinksResolved, withXLinkAttribute, addSchemaLocation, approved);
    }

    /**
     * Get a set of metadata records as ZIP
     * Metadata Exchange Format (MEF) is returned. MEF is a ZIP file containing the metadata as XML and some others files depending on the version requested. See http://geonetwork-opensource.org/manuals/trunk/eng/users/annexes/mef-format.html.
     * <p><b>200</b> - Return requested records as ZIP.
     * <p><b>403</b> - Operation not allowed. User needs to be able to view the resource.
     * <p><b>0</b> - other response
     * @param uuids Record UUIDs. If null current selection is used. (optional, default to new ArrayList&lt;&gt;())
     * @param bucket Selection bucket name (optional)
     * @param format MEF file format. (optional, default to FULL)
     * @param withRelated With related records (parent and service). (optional, default to false)
     * @param withXLinksResolved Resolve XLinks in the records. (optional, default to true)
     * @param withXLinkAttribute Preserve XLink URLs in the records. (optional, default to false)
     * @param addSchemaLocation  (optional, default to true)
     * @param approved Download the approved version (optional, default to true)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> exportAsMefWithHttpInfo(List<String> uuids, String bucket, String format, Boolean withRelated, Boolean withXLinksResolved, Boolean withXLinkAttribute, Boolean addSchemaLocation, Boolean approved) throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/records/zip", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        queryParams.putAll(apiClient.parameterToMultiValueMap(ApiClient.CollectionFormat.valueOf("multi".toUpperCase(Locale.ROOT)), "uuids", uuids));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "bucket", bucket));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "format", format));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "withRelated", withRelated));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "withXLinksResolved", withXLinksResolved));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "withXLinkAttribute", withXLinkAttribute));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "addSchemaLocation", addSchemaLocation));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "approved", approved));

        final String[] accepts = { 
            "application/x-gn-mef-2-zip"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] { "basicAuth" };

        ParameterizedTypeReference<Void> returnType = new ParameterizedTypeReference<Void>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Get a set of metadata records as PDF
     * The PDF is a short summary of each records with links to the complete metadata record in different format (ie. landing page on the portal, XML)
     * <p><b>200</b> - Return requested records as PDF.
     * <p><b>403</b> - Operation not allowed. User needs to be able to view the resource.
     * <p><b>0</b> - default response
     * @param uuids Record UUIDs. If null current selection is used. (optional, default to new ArrayList&lt;&gt;())
     * @param bucket Selection bucket name (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void exportAsPdf(List<String> uuids, String bucket) throws RestClientException {
        exportAsPdfWithHttpInfo(uuids, bucket);
    }

    /**
     * Get a set of metadata records as PDF
     * The PDF is a short summary of each records with links to the complete metadata record in different format (ie. landing page on the portal, XML)
     * <p><b>200</b> - Return requested records as PDF.
     * <p><b>403</b> - Operation not allowed. User needs to be able to view the resource.
     * <p><b>0</b> - default response
     * @param uuids Record UUIDs. If null current selection is used. (optional, default to new ArrayList&lt;&gt;())
     * @param bucket Selection bucket name (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> exportAsPdfWithHttpInfo(List<String> uuids, String bucket) throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/records/pdf", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        queryParams.putAll(apiClient.parameterToMultiValueMap(ApiClient.CollectionFormat.valueOf("multi".toUpperCase(Locale.ROOT)), "uuids", uuids));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "bucket", bucket));

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
     * Get the list of users \&quot;active\&quot; during a time period.
     * 
     * <p><b>200</b> - List of users \&quot;active\&quot; during a time period.
     * <p><b>0</b> - default response
     * @param dateFrom From date of users login date (required)
     * @param dateTo To date of users login date (required)
     * @param groups Group(s) for the users (optional, default to new ArrayList&lt;&gt;())
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void getActiveUsers(String dateFrom, String dateTo, List<Integer> groups) throws RestClientException {
        getActiveUsersWithHttpInfo(dateFrom, dateTo, groups);
    }

    /**
     * Get the list of users \&quot;active\&quot; during a time period.
     * 
     * <p><b>200</b> - List of users \&quot;active\&quot; during a time period.
     * <p><b>0</b> - default response
     * @param dateFrom From date of users login date (required)
     * @param dateTo To date of users login date (required)
     * @param groups Group(s) for the users (optional, default to new ArrayList&lt;&gt;())
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> getActiveUsersWithHttpInfo(String dateFrom, String dateTo, List<Integer> groups) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'dateFrom' is set
        if (dateFrom == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'dateFrom' when calling getActiveUsers");
        }
        
        // verify the required parameter 'dateTo' is set
        if (dateTo == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'dateTo' when calling getActiveUsers");
        }
        
        String path = apiClient.expandPath("/reports/users", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "dateFrom", dateFrom));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "dateTo", dateTo));
        queryParams.putAll(apiClient.parameterToMultiValueMap(ApiClient.CollectionFormat.valueOf("multi".toUpperCase(Locale.ROOT)), "groups", groups));

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
     * Get list of record extents
     * A rendering of the geometry as a png. If no background is specified the image will be transparent. In getMap the envelope of the geometry is calculated then it is expanded by a factor.  That factor is the size of the map.  This allows the map to be slightly bigger than the geometry allowing some context to be shown. This parameter allows different factors to be chosen per scale level. Proportion is the proportion of the world that the geometry covers (bounds of WGS84)/(bounds of geometry in WGS84)  Named backgrounds allow the background parameter to be a simple key and the complete URL will be looked up from this list of named backgrounds 
     * <p><b>200</b> - OK
     * <p><b>0</b> - default response
     * @param metadataUuid Record UUID. (required)
     * @return List&lt;ExtentDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<ExtentDto> getAllRecordExtentAsJson(String metadataUuid) throws RestClientException {
        return getAllRecordExtentAsJsonWithHttpInfo(metadataUuid).getBody();
    }

    /**
     * Get list of record extents
     * A rendering of the geometry as a png. If no background is specified the image will be transparent. In getMap the envelope of the geometry is calculated then it is expanded by a factor.  That factor is the size of the map.  This allows the map to be slightly bigger than the geometry allowing some context to be shown. This parameter allows different factors to be chosen per scale level. Proportion is the proportion of the world that the geometry covers (bounds of WGS84)/(bounds of geometry in WGS84)  Named backgrounds allow the background parameter to be a simple key and the complete URL will be looked up from this list of named backgrounds 
     * <p><b>200</b> - OK
     * <p><b>0</b> - default response
     * @param metadataUuid Record UUID. (required)
     * @return ResponseEntity&lt;List&lt;ExtentDto&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<ExtentDto>> getAllRecordExtentAsJsonWithHttpInfo(String metadataUuid) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'metadataUuid' is set
        if (metadataUuid == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'metadataUuid' when calling getAllRecordExtentAsJson");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("metadataUuid", metadataUuid);
        String path = apiClient.expandPath("/records/{metadataUuid}/extents.json", uriVariables);

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

        ParameterizedTypeReference<List<ExtentDto>> returnType = new ParameterizedTypeReference<List<ExtentDto>>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * List all metadata attachments
     * &lt;a href&#x3D;&#39;http://geonetwork-opensource.org/manuals/trunk/eng/users/user-guide/associating-resources/using-filestore.html&#39;&gt;More info&lt;/a&gt;
     * <p><b>200</b> - Return the record attachments.
     * <p><b>403</b> - Operation not allowed. User needs to be able to view the resource.
     * <p><b>0</b> - default response
     * @param metadataUuid The metadata UUID (required)
     * @param sort Sort by (optional, default to name)
     * @param approved Use approved version or not (optional, default to true)
     * @param filter  (optional, default to &quot;*.*&quot;)
     * @return List&lt;MetadataResource&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<MetadataResource> getAllResources(String metadataUuid, String sort, Boolean approved, String filter) throws RestClientException {
        return getAllResourcesWithHttpInfo(metadataUuid, sort, approved, filter).getBody();
    }

    /**
     * List all metadata attachments
     * &lt;a href&#x3D;&#39;http://geonetwork-opensource.org/manuals/trunk/eng/users/user-guide/associating-resources/using-filestore.html&#39;&gt;More info&lt;/a&gt;
     * <p><b>200</b> - Return the record attachments.
     * <p><b>403</b> - Operation not allowed. User needs to be able to view the resource.
     * <p><b>0</b> - default response
     * @param metadataUuid The metadata UUID (required)
     * @param sort Sort by (optional, default to name)
     * @param approved Use approved version or not (optional, default to true)
     * @param filter  (optional, default to &quot;*.*&quot;)
     * @return ResponseEntity&lt;List&lt;MetadataResource&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<MetadataResource>> getAllResourcesWithHttpInfo(String metadataUuid, String sort, Boolean approved, String filter) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'metadataUuid' is set
        if (metadataUuid == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'metadataUuid' when calling getAllResources");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("metadataUuid", metadataUuid);
        String path = apiClient.expandPath("/records/{metadataUuid}/attachments", uriVariables);

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "sort", sort));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "approved", approved));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "filter", filter));

        final String[] accepts = { 
            "application/json"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] { "basicAuth" };

        ParameterizedTypeReference<List<MetadataResource>> returnType = new ParameterizedTypeReference<List<MetadataResource>>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Get catalog content as RDF. This endpoint supports the same Lucene query parameters as for the GUI search.
     * .
     * <p><b>200</b> - Return the catalog content as RDF.
     * <p><b>303</b> - Redirect the client to the first in-sequence page resource. This happens when the paging parameters (from, hitsPerPage) are not included in the request.
     * <p><b>0</b> - default response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void getAsRdf() throws RestClientException {
        getAsRdfWithHttpInfo();
    }

    /**
     * Get catalog content as RDF. This endpoint supports the same Lucene query parameters as for the GUI search.
     * .
     * <p><b>200</b> - Return the catalog content as RDF.
     * <p><b>303</b> - Redirect the client to the first in-sequence page resource. This happens when the paging parameters (from, hitsPerPage) are not included in the request.
     * <p><b>0</b> - default response
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> getAsRdfWithHttpInfo() throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/records", Collections.<String, Object>emptyMap());

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
     * Get record related resources
     * Retrieve related services, datasets, onlines, thumbnails, sources, ... to this records.&lt;br/&gt;&lt;a href&#x3D;&#39;http://geonetwork-opensource.org/manuals/trunk/eng/users/user-guide/associating-resources/index.html&#39;&gt;More info&lt;/a&gt;
     * <p><b>200</b> - Return the associated resources.
     * <p><b>403</b> - Operation not allowed. User needs to be able to view the resource.
     * <p><b>0</b> - default response
     * @param metadataUuid Record UUID. (required)
     * @param type Type of related resource. If none, all resources are returned. (optional, default to new ArrayList&lt;&gt;())
     * @param start Start offset for paging. Default 1. Only applies to related metadata records (ie. not for thumbnails). (optional, default to 0)
     * @param rows Number of rows returned. Default 100. (optional, default to 100)
     * @return RelatedResponse
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RelatedResponse getAssociatedResources(String metadataUuid, List<String> type, Integer start, Integer rows) throws RestClientException {
        return getAssociatedResourcesWithHttpInfo(metadataUuid, type, start, rows).getBody();
    }

    /**
     * Get record related resources
     * Retrieve related services, datasets, onlines, thumbnails, sources, ... to this records.&lt;br/&gt;&lt;a href&#x3D;&#39;http://geonetwork-opensource.org/manuals/trunk/eng/users/user-guide/associating-resources/index.html&#39;&gt;More info&lt;/a&gt;
     * <p><b>200</b> - Return the associated resources.
     * <p><b>403</b> - Operation not allowed. User needs to be able to view the resource.
     * <p><b>0</b> - default response
     * @param metadataUuid Record UUID. (required)
     * @param type Type of related resource. If none, all resources are returned. (optional, default to new ArrayList&lt;&gt;())
     * @param start Start offset for paging. Default 1. Only applies to related metadata records (ie. not for thumbnails). (optional, default to 0)
     * @param rows Number of rows returned. Default 100. (optional, default to 100)
     * @return ResponseEntity&lt;RelatedResponse&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RelatedResponse> getAssociatedResourcesWithHttpInfo(String metadataUuid, List<String> type, Integer start, Integer rows) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'metadataUuid' is set
        if (metadataUuid == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'metadataUuid' when calling getAssociatedResources");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("metadataUuid", metadataUuid);
        String path = apiClient.expandPath("/records/{metadataUuid}/related", uriVariables);

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        queryParams.putAll(apiClient.parameterToMultiValueMap(ApiClient.CollectionFormat.valueOf("multi".toUpperCase(Locale.ROOT)), "type", type));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "start", start));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "rows", rows));

        final String[] accepts = { 
            "application/json", "application/xml", 
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] { "basicAuth" };

        ParameterizedTypeReference<RelatedResponse> returnType = new ParameterizedTypeReference<RelatedResponse>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Returns a map to decode attributes in a dataset (from the associated feature catalog)
     * Retrieve related services, datasets, onlines, thumbnails, sources, ... to this records.&lt;br/&gt;&lt;a href&#x3D;&#39;http://geonetwork-opensource.org/manuals/trunk/eng/users/user-guide/associating-resources/index.html&#39;&gt;More info&lt;/a&gt;
     * <p><b>200</b> - Return the associated resources.
     * <p><b>403</b> - Operation not allowed. User needs to be able to view the resource.
     * <p><b>0</b> - default response
     * @param metadataUuid Record UUID. (required)
     * @return FeatureResponse
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public FeatureResponse getFeatureCatalog(String metadataUuid) throws RestClientException {
        return getFeatureCatalogWithHttpInfo(metadataUuid).getBody();
    }

    /**
     * Returns a map to decode attributes in a dataset (from the associated feature catalog)
     * Retrieve related services, datasets, onlines, thumbnails, sources, ... to this records.&lt;br/&gt;&lt;a href&#x3D;&#39;http://geonetwork-opensource.org/manuals/trunk/eng/users/user-guide/associating-resources/index.html&#39;&gt;More info&lt;/a&gt;
     * <p><b>200</b> - Return the associated resources.
     * <p><b>403</b> - Operation not allowed. User needs to be able to view the resource.
     * <p><b>0</b> - default response
     * @param metadataUuid Record UUID. (required)
     * @return ResponseEntity&lt;FeatureResponse&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<FeatureResponse> getFeatureCatalogWithHttpInfo(String metadataUuid) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'metadataUuid' is set
        if (metadataUuid == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'metadataUuid' when calling getFeatureCatalog");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("metadataUuid", metadataUuid);
        String path = apiClient.expandPath("/records/{metadataUuid}/featureCatalog", uriVariables);

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        final String[] accepts = { 
            "application/json", "application/xml", 
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] { "basicAuth" };

        ParameterizedTypeReference<FeatureResponse> returnType = new ParameterizedTypeReference<FeatureResponse>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Get a metadata record as JSON
     * 
     * <p><b>200</b> - Return the record.
     * <p><b>403</b> - Operation not allowed. User needs to be able to view the resource.
     * <p><b>0</b> - other response
     * @param metadataUuid Record UUID. (required)
     * @param addSchemaLocation Add XSD schema location based on standard configuration (see schema-ident.xml). (optional, default to true)
     * @param increasePopularity Increase record popularity (optional, default to true)
     * @param withInfo Add geonet:info details (optional, default to false)
     * @param attachment Download as a file (optional, default to false)
     * @param approved Download the approved version (optional, default to true)
     * @return Object
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Object getRecordAsJSON(String metadataUuid, Boolean addSchemaLocation, Boolean increasePopularity, Boolean withInfo, Boolean attachment, Boolean approved) throws RestClientException {
        return getRecordAsJSONWithHttpInfo(metadataUuid, addSchemaLocation, increasePopularity, withInfo, attachment, approved).getBody();
    }

    /**
     * Get a metadata record as JSON
     * 
     * <p><b>200</b> - Return the record.
     * <p><b>403</b> - Operation not allowed. User needs to be able to view the resource.
     * <p><b>0</b> - other response
     * @param metadataUuid Record UUID. (required)
     * @param addSchemaLocation Add XSD schema location based on standard configuration (see schema-ident.xml). (optional, default to true)
     * @param increasePopularity Increase record popularity (optional, default to true)
     * @param withInfo Add geonet:info details (optional, default to false)
     * @param attachment Download as a file (optional, default to false)
     * @param approved Download the approved version (optional, default to true)
     * @return ResponseEntity&lt;Object&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Object> getRecordAsJSONWithHttpInfo(String metadataUuid, Boolean addSchemaLocation, Boolean increasePopularity, Boolean withInfo, Boolean attachment, Boolean approved) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'metadataUuid' is set
        if (metadataUuid == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'metadataUuid' when calling getRecordAsJSON");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("metadataUuid", metadataUuid);
        String path = apiClient.expandPath("/records/{metadataUuid}/formatters/json", uriVariables);

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "addSchemaLocation", addSchemaLocation));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "increasePopularity", increasePopularity));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "withInfo", withInfo));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "attachment", attachment));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "approved", approved));

        final String[] accepts = { 
            "application/json", "application/xml", 
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] { "basicAuth" };

        ParameterizedTypeReference<Object> returnType = new ParameterizedTypeReference<Object>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Get a metadata record as XML
     * 
     * <p><b>200</b> - Return the record.
     * <p><b>403</b> - Operation not allowed. User needs to be able to view the resource.
     * <p><b>0</b> - other response
     * @param metadataUuid Record UUID. (required)
     * @param addSchemaLocation Add XSD schema location based on standard configuration (see schema-ident.xml). (optional, default to true)
     * @param increasePopularity Increase record popularity (optional, default to true)
     * @param withInfo Add geonet:info details (optional, default to false)
     * @param attachment Download as a file (optional, default to false)
     * @param approved Download the approved version (optional, default to true)
     * @return Object
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Object getRecordAsXml(String metadataUuid, Boolean addSchemaLocation, Boolean increasePopularity, Boolean withInfo, Boolean attachment, Boolean approved) throws RestClientException {
        return getRecordAsXmlWithHttpInfo(metadataUuid, addSchemaLocation, increasePopularity, withInfo, attachment, approved).getBody();
    }

    /**
     * Get a metadata record as XML
     * 
     * <p><b>200</b> - Return the record.
     * <p><b>403</b> - Operation not allowed. User needs to be able to view the resource.
     * <p><b>0</b> - other response
     * @param metadataUuid Record UUID. (required)
     * @param addSchemaLocation Add XSD schema location based on standard configuration (see schema-ident.xml). (optional, default to true)
     * @param increasePopularity Increase record popularity (optional, default to true)
     * @param withInfo Add geonet:info details (optional, default to false)
     * @param attachment Download as a file (optional, default to false)
     * @param approved Download the approved version (optional, default to true)
     * @return ResponseEntity&lt;Object&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Object> getRecordAsXmlWithHttpInfo(String metadataUuid, Boolean addSchemaLocation, Boolean increasePopularity, Boolean withInfo, Boolean attachment, Boolean approved) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'metadataUuid' is set
        if (metadataUuid == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'metadataUuid' when calling getRecordAsXml");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("metadataUuid", metadataUuid);
        String path = apiClient.expandPath("/records/{metadataUuid}/formatters/xml", uriVariables);

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "addSchemaLocation", addSchemaLocation));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "increasePopularity", increasePopularity));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "withInfo", withInfo));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "attachment", attachment));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "approved", approved));

        final String[] accepts = { 
            "application/xml"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] { "basicAuth" };

        ParameterizedTypeReference<Object> returnType = new ParameterizedTypeReference<Object>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Get a metadata record as ZIP
     * Metadata Exchange Format (MEF) is returned. MEF is a ZIP file containing the metadata as XML and some others files depending on the version requested. See http://geonetwork-opensource.org/manuals/trunk/eng/users/annexes/mef-format.html.
     * <p><b>200</b> - Return the record.
     * <p><b>403</b> - Operation not allowed. User needs to be able to view the resource.
     * <p><b>0</b> - other response
     * @param metadataUuid Record UUID. (required)
     * @param format MEF file format. (optional, default to FULL)
     * @param withRelated With related records (parent and service). (optional, default to true)
     * @param withXLinksResolved Resolve XLinks in the records. (optional, default to true)
     * @param withXLinkAttribute Preserve XLink URLs in the records. (optional, default to false)
     * @param addSchemaLocation  (optional, default to true)
     * @param approved Download the approved version (optional, default to true)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void getRecordAsZip(String metadataUuid, String format, Boolean withRelated, Boolean withXLinksResolved, Boolean withXLinkAttribute, Boolean addSchemaLocation, Boolean approved) throws RestClientException {
        getRecordAsZipWithHttpInfo(metadataUuid, format, withRelated, withXLinksResolved, withXLinkAttribute, addSchemaLocation, approved);
    }

    /**
     * Get a metadata record as ZIP
     * Metadata Exchange Format (MEF) is returned. MEF is a ZIP file containing the metadata as XML and some others files depending on the version requested. See http://geonetwork-opensource.org/manuals/trunk/eng/users/annexes/mef-format.html.
     * <p><b>200</b> - Return the record.
     * <p><b>403</b> - Operation not allowed. User needs to be able to view the resource.
     * <p><b>0</b> - other response
     * @param metadataUuid Record UUID. (required)
     * @param format MEF file format. (optional, default to FULL)
     * @param withRelated With related records (parent and service). (optional, default to true)
     * @param withXLinksResolved Resolve XLinks in the records. (optional, default to true)
     * @param withXLinkAttribute Preserve XLink URLs in the records. (optional, default to false)
     * @param addSchemaLocation  (optional, default to true)
     * @param approved Download the approved version (optional, default to true)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> getRecordAsZipWithHttpInfo(String metadataUuid, String format, Boolean withRelated, Boolean withXLinksResolved, Boolean withXLinkAttribute, Boolean addSchemaLocation, Boolean approved) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'metadataUuid' is set
        if (metadataUuid == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'metadataUuid' when calling getRecordAsZip");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("metadataUuid", metadataUuid);
        String path = apiClient.expandPath("/records/{metadataUuid}/formatters/zip", uriVariables);

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "format", format));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "withRelated", withRelated));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "withXLinksResolved", withXLinksResolved));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "withXLinkAttribute", withXLinkAttribute));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "addSchemaLocation", addSchemaLocation));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "approved", approved));

        final String[] accepts = { 
            "application/json", "application/x-gn-mef-2-zip"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] { "basicAuth" };

        ParameterizedTypeReference<Void> returnType = new ParameterizedTypeReference<Void>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Get a formatted metadata record
     * 
     * <p><b>200</b> - OK
     * <p><b>0</b> - other response
     * @param formatterId  (required)
     * @param metadataUuid Record UUID. (required)
     * @param width  (optional, default to _100)
     * @param mdpath  (optional)
     * @param language Optional language ISO 3 letters code to override HTTP Accept-language header. (optional)
     * @param output  (optional)
     * @param approved Download the approved version (optional, default to true)
     * @return String
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public String getRecordFormattedBy(String formatterId, String metadataUuid, String width, String mdpath, String language, String output, Boolean approved) throws RestClientException {
        return getRecordFormattedByWithHttpInfo(formatterId, metadataUuid, width, mdpath, language, output, approved).getBody();
    }

    /**
     * Get a formatted metadata record
     * 
     * <p><b>200</b> - OK
     * <p><b>0</b> - other response
     * @param formatterId  (required)
     * @param metadataUuid Record UUID. (required)
     * @param width  (optional, default to _100)
     * @param mdpath  (optional)
     * @param language Optional language ISO 3 letters code to override HTTP Accept-language header. (optional)
     * @param output  (optional)
     * @param approved Download the approved version (optional, default to true)
     * @return ResponseEntity&lt;String&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<String> getRecordFormattedByWithHttpInfo(String formatterId, String metadataUuid, String width, String mdpath, String language, String output, Boolean approved) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'formatterId' is set
        if (formatterId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'formatterId' when calling getRecordFormattedBy");
        }
        
        // verify the required parameter 'metadataUuid' is set
        if (metadataUuid == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'metadataUuid' when calling getRecordFormattedBy");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("formatterId", formatterId);
        uriVariables.put("metadataUuid", metadataUuid);
        String path = apiClient.expandPath("/records/{metadataUuid}/formatters/{formatterId}", uriVariables);

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "width", width));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "mdpath", mdpath));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "language", language));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "output", output));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "approved", approved));

        final String[] accepts = { 
            "application/json", "application/xml"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] { "basicAuth" };

        ParameterizedTypeReference<String> returnType = new ParameterizedTypeReference<String>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Get record sharing settings
     * Return current sharing options for a record.
     * <p><b>200</b> - The record sharing settings.
     * <p><b>403</b> - Operation not allowed. User needs to be able to view the resource.
     * <p><b>0</b> - default response
     * @param metadataUuid Record UUID. (required)
     * @return SharingResponse
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public SharingResponse getRecordSharingSettings(String metadataUuid) throws RestClientException {
        return getRecordSharingSettingsWithHttpInfo(metadataUuid).getBody();
    }

    /**
     * Get record sharing settings
     * Return current sharing options for a record.
     * <p><b>200</b> - The record sharing settings.
     * <p><b>403</b> - Operation not allowed. User needs to be able to view the resource.
     * <p><b>0</b> - default response
     * @param metadataUuid Record UUID. (required)
     * @return ResponseEntity&lt;SharingResponse&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<SharingResponse> getRecordSharingSettingsWithHttpInfo(String metadataUuid) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'metadataUuid' is set
        if (metadataUuid == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'metadataUuid' when calling getRecordSharingSettings");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("metadataUuid", metadataUuid);
        String path = apiClient.expandPath("/records/{metadataUuid}/sharing", uriVariables);

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

        ParameterizedTypeReference<SharingResponse> returnType = new ParameterizedTypeReference<SharingResponse>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Get record status history
     * 
     * <p><b>200</b> - OK
     * <p><b>0</b> - default response
     * @param metadataUuid Record UUID. (required)
     * @param details  (optional)
     * @param sortOrder Sort direction (optional, default to DESC)
     * @return List&lt;MetadataStatusResponse&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<MetadataStatusResponse> getRecordStatusHistory(String metadataUuid, Boolean details, String sortOrder) throws RestClientException {
        return getRecordStatusHistoryWithHttpInfo(metadataUuid, details, sortOrder).getBody();
    }

    /**
     * Get record status history
     * 
     * <p><b>200</b> - OK
     * <p><b>0</b> - default response
     * @param metadataUuid Record UUID. (required)
     * @param details  (optional)
     * @param sortOrder Sort direction (optional, default to DESC)
     * @return ResponseEntity&lt;List&lt;MetadataStatusResponse&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<MetadataStatusResponse>> getRecordStatusHistoryWithHttpInfo(String metadataUuid, Boolean details, String sortOrder) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'metadataUuid' is set
        if (metadataUuid == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'metadataUuid' when calling getRecordStatusHistory");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("metadataUuid", metadataUuid);
        String path = apiClient.expandPath("/records/{metadataUuid}/status", uriVariables);

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "details", details));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "sortOrder", sortOrder));

        final String[] accepts = { 
            "application/json"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] { "basicAuth" };

        ParameterizedTypeReference<List<MetadataStatusResponse>> returnType = new ParameterizedTypeReference<List<MetadataStatusResponse>>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Get record status history by type
     * 
     * <p><b>200</b> - OK
     * <p><b>0</b> - default response
     * @param metadataUuid Record UUID. (required)
     * @param type Type (required)
     * @param details  (optional)
     * @param sortOrder Sort direction (optional, default to DESC)
     * @return List&lt;MetadataStatusResponse&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<MetadataStatusResponse> getRecordStatusHistoryByType(String metadataUuid, String type, Boolean details, String sortOrder) throws RestClientException {
        return getRecordStatusHistoryByTypeWithHttpInfo(metadataUuid, type, details, sortOrder).getBody();
    }

    /**
     * Get record status history by type
     * 
     * <p><b>200</b> - OK
     * <p><b>0</b> - default response
     * @param metadataUuid Record UUID. (required)
     * @param type Type (required)
     * @param details  (optional)
     * @param sortOrder Sort direction (optional, default to DESC)
     * @return ResponseEntity&lt;List&lt;MetadataStatusResponse&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<MetadataStatusResponse>> getRecordStatusHistoryByTypeWithHttpInfo(String metadataUuid, String type, Boolean details, String sortOrder) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'metadataUuid' is set
        if (metadataUuid == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'metadataUuid' when calling getRecordStatusHistoryByType");
        }
        
        // verify the required parameter 'type' is set
        if (type == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'type' when calling getRecordStatusHistoryByType");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("metadataUuid", metadataUuid);
        uriVariables.put("type", type);
        String path = apiClient.expandPath("/records/{metadataUuid}/status/{type}", uriVariables);

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "details", details));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "sortOrder", sortOrder));

        final String[] accepts = { 
            "application/json"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] { "basicAuth" };

        ParameterizedTypeReference<List<MetadataStatusResponse>> returnType = new ParameterizedTypeReference<List<MetadataStatusResponse>>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Get record tags
     * Tags are used to classify information.&lt;br/&gt;&lt;a href&#x3D;&#39;http://geonetwork-opensource.org/manuals/trunk/eng/users/user-guide/tag-information/tagging-with-categories.html&#39;&gt;More info&lt;/a&gt;
     * <p><b>200</b> - Record tags.
     * <p><b>403</b> - Operation not allowed. User needs to be able to view the resource.
     * <p><b>0</b> - default response
     * @param metadataUuid Record UUID. (required)
     * @return List&lt;MetadataCategory&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<MetadataCategory> getRecordTags(String metadataUuid) throws RestClientException {
        return getRecordTagsWithHttpInfo(metadataUuid).getBody();
    }

    /**
     * Get record tags
     * Tags are used to classify information.&lt;br/&gt;&lt;a href&#x3D;&#39;http://geonetwork-opensource.org/manuals/trunk/eng/users/user-guide/tag-information/tagging-with-categories.html&#39;&gt;More info&lt;/a&gt;
     * <p><b>200</b> - Record tags.
     * <p><b>403</b> - Operation not allowed. User needs to be able to view the resource.
     * <p><b>0</b> - default response
     * @param metadataUuid Record UUID. (required)
     * @return ResponseEntity&lt;List&lt;MetadataCategory&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<MetadataCategory>> getRecordTagsWithHttpInfo(String metadataUuid) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'metadataUuid' is set
        if (metadataUuid == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'metadataUuid' when calling getRecordTags");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("metadataUuid", metadataUuid);
        String path = apiClient.expandPath("/records/{metadataUuid}/tags", uriVariables);

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

        ParameterizedTypeReference<List<MetadataCategory>> returnType = new ParameterizedTypeReference<List<MetadataCategory>>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Get list of metadata file downloads
     * 
     * <p><b>200</b> - List of metadata file downloads.
     * <p><b>0</b> - default response
     * @param dateFrom From date of the metadata downloads (required)
     * @param dateTo To date of the metadata downloads (required)
     * @param groups Metadata group(s) (optional, default to new ArrayList&lt;&gt;())
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void getReportDataDownloads(String dateFrom, String dateTo, List<Integer> groups) throws RestClientException {
        getReportDataDownloadsWithHttpInfo(dateFrom, dateTo, groups);
    }

    /**
     * Get list of metadata file downloads
     * 
     * <p><b>200</b> - List of metadata file downloads.
     * <p><b>0</b> - default response
     * @param dateFrom From date of the metadata downloads (required)
     * @param dateTo To date of the metadata downloads (required)
     * @param groups Metadata group(s) (optional, default to new ArrayList&lt;&gt;())
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> getReportDataDownloadsWithHttpInfo(String dateFrom, String dateTo, List<Integer> groups) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'dateFrom' is set
        if (dateFrom == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'dateFrom' when calling getReportDataDownloads");
        }
        
        // verify the required parameter 'dateTo' is set
        if (dateTo == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'dateTo' when calling getReportDataDownloads");
        }
        
        String path = apiClient.expandPath("/reports/datadownloads", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "dateFrom", dateFrom));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "dateTo", dateTo));
        queryParams.putAll(apiClient.parameterToMultiValueMap(ApiClient.CollectionFormat.valueOf("multi".toUpperCase(Locale.ROOT)), "groups", groups));

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
     * Get uploaded files to metadata records during a period.
     * 
     * <p><b>200</b> - OK
     * <p><b>0</b> - default response
     * @param dateFrom From date of the metadata uploads (required)
     * @param dateTo To date of the metadata uploads (required)
     * @param groups Metadata group(s) (optional, default to new ArrayList&lt;&gt;())
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void getReportDataUploads(String dateFrom, String dateTo, List<Integer> groups) throws RestClientException {
        getReportDataUploadsWithHttpInfo(dateFrom, dateTo, groups);
    }

    /**
     * Get uploaded files to metadata records during a period.
     * 
     * <p><b>200</b> - OK
     * <p><b>0</b> - default response
     * @param dateFrom From date of the metadata uploads (required)
     * @param dateTo To date of the metadata uploads (required)
     * @param groups Metadata group(s) (optional, default to new ArrayList&lt;&gt;())
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> getReportDataUploadsWithHttpInfo(String dateFrom, String dateTo, List<Integer> groups) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'dateFrom' is set
        if (dateFrom == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'dateFrom' when calling getReportDataUploads");
        }
        
        // verify the required parameter 'dateTo' is set
        if (dateTo == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'dateTo' when calling getReportDataUploads");
        }
        
        String path = apiClient.expandPath("/reports/datauploads", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "dateFrom", dateFrom));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "dateTo", dateTo));
        queryParams.putAll(apiClient.parameterToMultiValueMap(ApiClient.CollectionFormat.valueOf("multi".toUpperCase(Locale.ROOT)), "groups", groups));

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
     * Get the metadata not published during a period.
     * 
     * <p><b>200</b> - Metadata not published during a period.
     * <p><b>0</b> - default response
     * @param dateFrom From date of metadata change date (required)
     * @param dateTo To date of metadata change date (required)
     * @param groups Metadata group(s) (optional, default to new ArrayList&lt;&gt;())
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void getReportInternalMetadata(String dateFrom, String dateTo, List<Integer> groups) throws RestClientException {
        getReportInternalMetadataWithHttpInfo(dateFrom, dateTo, groups);
    }

    /**
     * Get the metadata not published during a period.
     * 
     * <p><b>200</b> - Metadata not published during a period.
     * <p><b>0</b> - default response
     * @param dateFrom From date of metadata change date (required)
     * @param dateTo To date of metadata change date (required)
     * @param groups Metadata group(s) (optional, default to new ArrayList&lt;&gt;())
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> getReportInternalMetadataWithHttpInfo(String dateFrom, String dateTo, List<Integer> groups) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'dateFrom' is set
        if (dateFrom == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'dateFrom' when calling getReportInternalMetadata");
        }
        
        // verify the required parameter 'dateTo' is set
        if (dateTo == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'dateTo' when calling getReportInternalMetadata");
        }
        
        String path = apiClient.expandPath("/reports/metadatainternal", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "dateFrom", dateFrom));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "dateTo", dateTo));
        queryParams.putAll(apiClient.parameterToMultiValueMap(ApiClient.CollectionFormat.valueOf("multi".toUpperCase(Locale.ROOT)), "groups", groups));

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
     * Get the updated metadata during a period.
     * 
     * <p><b>200</b> - Updated metadata during a period.
     * <p><b>0</b> - default response
     * @param dateFrom From date of metadata change date (required)
     * @param dateTo To date of metadata change date (required)
     * @param groups Metadata group(s) (optional, default to new ArrayList&lt;&gt;())
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void getReportUpdatedMetadata(String dateFrom, String dateTo, List<Integer> groups) throws RestClientException {
        getReportUpdatedMetadataWithHttpInfo(dateFrom, dateTo, groups);
    }

    /**
     * Get the updated metadata during a period.
     * 
     * <p><b>200</b> - Updated metadata during a period.
     * <p><b>0</b> - default response
     * @param dateFrom From date of metadata change date (required)
     * @param dateTo To date of metadata change date (required)
     * @param groups Metadata group(s) (optional, default to new ArrayList&lt;&gt;())
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> getReportUpdatedMetadataWithHttpInfo(String dateFrom, String dateTo, List<Integer> groups) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'dateFrom' is set
        if (dateFrom == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'dateFrom' when calling getReportUpdatedMetadata");
        }
        
        // verify the required parameter 'dateTo' is set
        if (dateTo == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'dateTo' when calling getReportUpdatedMetadata");
        }
        
        String path = apiClient.expandPath("/reports/metadataupdated", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "dateFrom", dateFrom));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "dateTo", dateTo));
        queryParams.putAll(apiClient.parameterToMultiValueMap(ApiClient.CollectionFormat.valueOf("multi".toUpperCase(Locale.ROOT)), "groups", groups));

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
     * Get a metadata resource
     * 
     * <p><b>403</b> - Operation not allowed. User needs to be able to download the resource.
     * <p><b>201</b> - Record attachment.
     * <p><b>0</b> - default response
     * @param metadataUuid The metadata UUID (required)
     * @param resourceId The resource identifier (ie. filename) (required)
     * @param approved Use approved version or not (optional, default to true)
     * @param size Size (only applies to images). From 1px to 2048px. (optional)
     * @return List&lt;byte[]&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<byte[]> getResource(String metadataUuid, String resourceId, Boolean approved, Integer size) throws RestClientException {
        return getResourceWithHttpInfo(metadataUuid, resourceId, approved, size).getBody();
    }

    /**
     * Get a metadata resource
     * 
     * <p><b>403</b> - Operation not allowed. User needs to be able to download the resource.
     * <p><b>201</b> - Record attachment.
     * <p><b>0</b> - default response
     * @param metadataUuid The metadata UUID (required)
     * @param resourceId The resource identifier (ie. filename) (required)
     * @param approved Use approved version or not (optional, default to true)
     * @param size Size (only applies to images). From 1px to 2048px. (optional)
     * @return ResponseEntity&lt;List&lt;byte[]&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<byte[]>> getResourceWithHttpInfo(String metadataUuid, String resourceId, Boolean approved, Integer size) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'metadataUuid' is set
        if (metadataUuid == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'metadataUuid' when calling getResource");
        }
        
        // verify the required parameter 'resourceId' is set
        if (resourceId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'resourceId' when calling getResource");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("metadataUuid", metadataUuid);
        uriVariables.put("resourceId", resourceId);
        String path = apiClient.expandPath("/records/{metadataUuid}/attachments/{resourceId}", uriVariables);

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "approved", approved));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "size", size));

        final String[] accepts = { 
            "application/json", "*/*"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] { "basicAuth" };

        ParameterizedTypeReference<List<byte[]>> returnType = new ParameterizedTypeReference<List<byte[]>>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * List saved queries for this metadata
     * 
     * <p><b>200</b> - Saved query available.
     * <p><b>403</b> - Operation not allowed. User needs to be able to view the resource.
     * <p><b>0</b> - default response
     * @param metadataUuid Record UUID. (required)
     * @return List&lt;SavedQuery&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<SavedQuery> getSavedQueries(String metadataUuid) throws RestClientException {
        return getSavedQueriesWithHttpInfo(metadataUuid).getBody();
    }

    /**
     * List saved queries for this metadata
     * 
     * <p><b>200</b> - Saved query available.
     * <p><b>403</b> - Operation not allowed. User needs to be able to view the resource.
     * <p><b>0</b> - default response
     * @param metadataUuid Record UUID. (required)
     * @return ResponseEntity&lt;List&lt;SavedQuery&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<SavedQuery>> getSavedQueriesWithHttpInfo(String metadataUuid) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'metadataUuid' is set
        if (metadataUuid == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'metadataUuid' when calling getSavedQueries");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("metadataUuid", metadataUuid);
        String path = apiClient.expandPath("/records/{metadataUuid}/query", uriVariables);

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

        ParameterizedTypeReference<List<SavedQuery>> returnType = new ParameterizedTypeReference<List<SavedQuery>>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Get record sharing settings
     * 
     * <p><b>403</b> - Operation not allowed. User needs to be able to edit the resource.
     * <p><b>200</b> - Return a default array of group and operations that can be used to set record sharing properties.
     * <p><b>0</b> - default response
     * @return SharingResponse
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public SharingResponse getSharingSettings() throws RestClientException {
        return getSharingSettingsWithHttpInfo().getBody();
    }

    /**
     * Get record sharing settings
     * 
     * <p><b>403</b> - Operation not allowed. User needs to be able to edit the resource.
     * <p><b>200</b> - Return a default array of group and operations that can be used to set record sharing properties.
     * <p><b>0</b> - default response
     * @return ResponseEntity&lt;SharingResponse&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<SharingResponse> getSharingSettingsWithHttpInfo() throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/records/sharing", Collections.<String, Object>emptyMap());

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

        ParameterizedTypeReference<SharingResponse> returnType = new ParameterizedTypeReference<SharingResponse>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Get last workflow status for a record
     * 
     * <p><b>200</b> - Record status.
     * <p><b>403</b> - Operation not allowed. User needs to be able to edit the resource.
     * <p><b>0</b> - default response
     * @param metadataUuid Record UUID. (required)
     * @return MetadataWorkflowStatusResponse
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public MetadataWorkflowStatusResponse getStatus(String metadataUuid) throws RestClientException {
        return getStatusWithHttpInfo(metadataUuid).getBody();
    }

    /**
     * Get last workflow status for a record
     * 
     * <p><b>200</b> - Record status.
     * <p><b>403</b> - Operation not allowed. User needs to be able to edit the resource.
     * <p><b>0</b> - default response
     * @param metadataUuid Record UUID. (required)
     * @return ResponseEntity&lt;MetadataWorkflowStatusResponse&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<MetadataWorkflowStatusResponse> getStatusWithHttpInfo(String metadataUuid) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'metadataUuid' is set
        if (metadataUuid == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'metadataUuid' when calling getStatus");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("metadataUuid", metadataUuid);
        String path = apiClient.expandPath("/records/{metadataUuid}/status/workflow/last", uriVariables);

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

        ParameterizedTypeReference<MetadataWorkflowStatusResponse> returnType = new ParameterizedTypeReference<MetadataWorkflowStatusResponse>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Get suggestions
     * Analyze the record an suggest processes to improve the quality of the record.&lt;br/&gt;&lt;a href&#x3D;&#39;http://geonetwork-opensource.org/manuals/trunk/eng/users/user-guide/workflow/batchupdate-xsl.html&#39;&gt;More info&lt;/a&gt;
     * <p><b>403</b> - Operation not allowed. User needs to be able to edit the resource.
     * <p><b>200</b> - Record suggestions.
     * <p><b>0</b> - default response
     * @param metadataUuid Record UUID. (required)
     * @return List&lt;SuggestionType&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<SuggestionType> getSuggestions(String metadataUuid) throws RestClientException {
        return getSuggestionsWithHttpInfo(metadataUuid).getBody();
    }

    /**
     * Get suggestions
     * Analyze the record an suggest processes to improve the quality of the record.&lt;br/&gt;&lt;a href&#x3D;&#39;http://geonetwork-opensource.org/manuals/trunk/eng/users/user-guide/workflow/batchupdate-xsl.html&#39;&gt;More info&lt;/a&gt;
     * <p><b>403</b> - Operation not allowed. User needs to be able to edit the resource.
     * <p><b>200</b> - Record suggestions.
     * <p><b>0</b> - default response
     * @param metadataUuid Record UUID. (required)
     * @return ResponseEntity&lt;List&lt;SuggestionType&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<SuggestionType>> getSuggestionsWithHttpInfo(String metadataUuid) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'metadataUuid' is set
        if (metadataUuid == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'metadataUuid' when calling getSuggestions");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("metadataUuid", metadataUuid);
        String path = apiClient.expandPath("/records/{metadataUuid}/processes", uriVariables);

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

        ParameterizedTypeReference<List<SuggestionType>> returnType = new ParameterizedTypeReference<List<SuggestionType>>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Get test suites available.
     * TG13, TG2, ...
     * <p><b>200</b> - List of testsuites available.
     * <p><b>403</b> - Operation not allowed. User needs to be able to edit the resource.
     * <p><b>0</b> - default response
     * @param metadataUuid Record UUID. (required)
     * @return Map&lt;String, List&lt;String&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Map<String, List<String>> getTestSuites(String metadataUuid) throws RestClientException {
        return getTestSuitesWithHttpInfo(metadataUuid).getBody();
    }

    /**
     * Get test suites available.
     * TG13, TG2, ...
     * <p><b>200</b> - List of testsuites available.
     * <p><b>403</b> - Operation not allowed. User needs to be able to edit the resource.
     * <p><b>0</b> - default response
     * @param metadataUuid Record UUID. (required)
     * @return ResponseEntity&lt;Map&lt;String, List&lt;String&gt;&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Map<String, List<String>>> getTestSuitesWithHttpInfo(String metadataUuid) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'metadataUuid' is set
        if (metadataUuid == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'metadataUuid' when calling getTestSuites");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("metadataUuid", metadataUuid);
        String path = apiClient.expandPath("/records/{metadataUuid}/validate/inspire/testsuites", uriVariables);

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

        ParameterizedTypeReference<Map<String, List<String>>> returnType = new ParameterizedTypeReference<Map<String, List<String>>>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Search status
     * 
     * <p><b>200</b> - OK
     * <p><b>0</b> - default response
     * @param type One or more types to retrieve (ie. worflow, event, task). Default is all. (optional, default to new ArrayList&lt;&gt;())
     * @param details All event details including XML changes. Responses are bigger. Default is false (optional)
     * @param author One or more event author. Default is all. (optional, default to new ArrayList&lt;&gt;())
     * @param owner One or more event owners. Default is all. (optional, default to new ArrayList&lt;&gt;())
     * @param record One or more record identifier. Default is all. (optional, default to new ArrayList&lt;&gt;())
     * @param dateFrom Start date (optional)
     * @param dateTo End date (optional)
     * @param from From page (optional, default to 0)
     * @param size Number of records to return (optional, default to 100)
     * @return List&lt;MetadataStatusResponse&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<MetadataStatusResponse> getWorkflowStatusByType(List<String> type, Boolean details, List<Integer> author, List<Integer> owner, List<Integer> record, String dateFrom, String dateTo, Integer from, Integer size) throws RestClientException {
        return getWorkflowStatusByTypeWithHttpInfo(type, details, author, owner, record, dateFrom, dateTo, from, size).getBody();
    }

    /**
     * Search status
     * 
     * <p><b>200</b> - OK
     * <p><b>0</b> - default response
     * @param type One or more types to retrieve (ie. worflow, event, task). Default is all. (optional, default to new ArrayList&lt;&gt;())
     * @param details All event details including XML changes. Responses are bigger. Default is false (optional)
     * @param author One or more event author. Default is all. (optional, default to new ArrayList&lt;&gt;())
     * @param owner One or more event owners. Default is all. (optional, default to new ArrayList&lt;&gt;())
     * @param record One or more record identifier. Default is all. (optional, default to new ArrayList&lt;&gt;())
     * @param dateFrom Start date (optional)
     * @param dateTo End date (optional)
     * @param from From page (optional, default to 0)
     * @param size Number of records to return (optional, default to 100)
     * @return ResponseEntity&lt;List&lt;MetadataStatusResponse&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<MetadataStatusResponse>> getWorkflowStatusByTypeWithHttpInfo(List<String> type, Boolean details, List<Integer> author, List<Integer> owner, List<Integer> record, String dateFrom, String dateTo, Integer from, Integer size) throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/records/status/search", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        queryParams.putAll(apiClient.parameterToMultiValueMap(ApiClient.CollectionFormat.valueOf("multi".toUpperCase(Locale.ROOT)), "type", type));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "details", details));
        queryParams.putAll(apiClient.parameterToMultiValueMap(ApiClient.CollectionFormat.valueOf("multi".toUpperCase(Locale.ROOT)), "author", author));
        queryParams.putAll(apiClient.parameterToMultiValueMap(ApiClient.CollectionFormat.valueOf("multi".toUpperCase(Locale.ROOT)), "owner", owner));
        queryParams.putAll(apiClient.parameterToMultiValueMap(ApiClient.CollectionFormat.valueOf("multi".toUpperCase(Locale.ROOT)), "record", record));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "dateFrom", dateFrom));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "dateTo", dateTo));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "from", from));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "size", size));

        final String[] accepts = { 
            "application/json"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] { "basicAuth" };

        ParameterizedTypeReference<List<MetadataStatusResponse>> returnType = new ParameterizedTypeReference<List<MetadataStatusResponse>>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Index a set of records
     * Index a set of records provided either by a bucket or a list of uuids
     * <p><b>403</b> - Operation not allowed. Only Administrators can access it.
     * <p><b>200</b> - Record indexed.
     * <p><b>0</b> - default response
     * @param uuids Record UUIDs. If null current selection is used. (optional, default to new ArrayList&lt;&gt;())
     * @param bucket Selection bucket name (optional)
     * @return Map&lt;String, Object&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Map<String, Object> index1(List<String> uuids, String bucket) throws RestClientException {
        return index1WithHttpInfo(uuids, bucket).getBody();
    }

    /**
     * Index a set of records
     * Index a set of records provided either by a bucket or a list of uuids
     * <p><b>403</b> - Operation not allowed. Only Administrators can access it.
     * <p><b>200</b> - Record indexed.
     * <p><b>0</b> - default response
     * @param uuids Record UUIDs. If null current selection is used. (optional, default to new ArrayList&lt;&gt;())
     * @param bucket Selection bucket name (optional)
     * @return ResponseEntity&lt;Map&lt;String, Object&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Map<String, Object>> index1WithHttpInfo(List<String> uuids, String bucket) throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/records/index", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        queryParams.putAll(apiClient.parameterToMultiValueMap(ApiClient.CollectionFormat.valueOf("multi".toUpperCase(Locale.ROOT)), "uuids", uuids));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "bucket", bucket));

        final String[] accepts = { 
            "application/json"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] { "basicAuth" };

        ParameterizedTypeReference<Map<String, Object>> returnType = new ParameterizedTypeReference<Map<String, Object>>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Add a record
     * Add one or more record from an XML fragment, URL or file in a folder on the catalog server. When loadingfrom the catalog server folder, it might be faster to use a local filesystem harvester.
     * <p><b>403</b> - Operation not allowed. Only Editors can access it.
     * <p><b>201</b> - Report about imported records.
     * <p><b>0</b> - default response
     * @param metadataType The type of record. (optional, default to METADATA)
     * @param url URL of a file to download and insert. (optional, default to new ArrayList&lt;&gt;())
     * @param serverFolder Server folder where to look for files. (optional)
     * @param recursiveSearch (Server folder import only) Recursive search in folder. (optional, default to false)
     * @param publishToAll (XML file only) Publish record. (optional, default to false)
     * @param assignToCatalog (MEF file only) Assign to current catalog. (optional, default to false)
     * @param uuidProcessing Record identifier processing. (optional, default to NOTHING)
     * @param group The group the record is attached to. (optional)
     * @param category Tags to assign to the record. (optional, default to new ArrayList&lt;&gt;())
     * @param rejectIfInvalid Validate the record first and reject it if not valid. (optional, default to false)
     * @param transformWith XSL transformation to apply to the record. (optional, default to &quot;_none_&quot;)
     * @param schema Force the schema of the record. If not set, schema autodetection is used (and is the preferred method). (optional)
     * @param extra (experimental) Add extra information to the record. (optional)
     * @param body XML fragment. (optional)
     * @return SimpleMetadataProcessingReport
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public SimpleMetadataProcessingReport insert(String metadataType, List<String> url, String serverFolder, Boolean recursiveSearch, Boolean publishToAll, Boolean assignToCatalog, String uuidProcessing, String group, List<String> category, Boolean rejectIfInvalid, String transformWith, String schema, String extra, String body) throws RestClientException {
        return insertWithHttpInfo(metadataType, url, serverFolder, recursiveSearch, publishToAll, assignToCatalog, uuidProcessing, group, category, rejectIfInvalid, transformWith, schema, extra, body).getBody();
    }

    /**
     * Add a record
     * Add one or more record from an XML fragment, URL or file in a folder on the catalog server. When loadingfrom the catalog server folder, it might be faster to use a local filesystem harvester.
     * <p><b>403</b> - Operation not allowed. Only Editors can access it.
     * <p><b>201</b> - Report about imported records.
     * <p><b>0</b> - default response
     * @param metadataType The type of record. (optional, default to METADATA)
     * @param url URL of a file to download and insert. (optional, default to new ArrayList&lt;&gt;())
     * @param serverFolder Server folder where to look for files. (optional)
     * @param recursiveSearch (Server folder import only) Recursive search in folder. (optional, default to false)
     * @param publishToAll (XML file only) Publish record. (optional, default to false)
     * @param assignToCatalog (MEF file only) Assign to current catalog. (optional, default to false)
     * @param uuidProcessing Record identifier processing. (optional, default to NOTHING)
     * @param group The group the record is attached to. (optional)
     * @param category Tags to assign to the record. (optional, default to new ArrayList&lt;&gt;())
     * @param rejectIfInvalid Validate the record first and reject it if not valid. (optional, default to false)
     * @param transformWith XSL transformation to apply to the record. (optional, default to &quot;_none_&quot;)
     * @param schema Force the schema of the record. If not set, schema autodetection is used (and is the preferred method). (optional)
     * @param extra (experimental) Add extra information to the record. (optional)
     * @param body XML fragment. (optional)
     * @return ResponseEntity&lt;SimpleMetadataProcessingReport&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<SimpleMetadataProcessingReport> insertWithHttpInfo(String metadataType, List<String> url, String serverFolder, Boolean recursiveSearch, Boolean publishToAll, Boolean assignToCatalog, String uuidProcessing, String group, List<String> category, Boolean rejectIfInvalid, String transformWith, String schema, String extra, String body) throws RestClientException {
        Object postBody = body;
        
        String path = apiClient.expandPath("/records", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "metadataType", metadataType));
        queryParams.putAll(apiClient.parameterToMultiValueMap(ApiClient.CollectionFormat.valueOf("multi".toUpperCase(Locale.ROOT)), "url", url));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "serverFolder", serverFolder));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "recursiveSearch", recursiveSearch));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "publishToAll", publishToAll));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "assignToCatalog", assignToCatalog));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "uuidProcessing", uuidProcessing));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "group", group));
        queryParams.putAll(apiClient.parameterToMultiValueMap(ApiClient.CollectionFormat.valueOf("multi".toUpperCase(Locale.ROOT)), "category", category));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "rejectIfInvalid", rejectIfInvalid));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "transformWith", transformWith));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "schema", schema));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "extra", extra));

        final String[] accepts = { 
            "application/json"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { 
            "application/xml", "application/x-www-form-urlencoded"
        };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] { "basicAuth" };

        ParameterizedTypeReference<SimpleMetadataProcessingReport> returnType = new ParameterizedTypeReference<SimpleMetadataProcessingReport>() {};
        return apiClient.invokeAPI(path, HttpMethod.PUT, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Add a record from XML or MEF/ZIP file
     * Add record in the catalog by uploading files.
     * <p><b>403</b> - Operation not allowed. Only Editors can access it.
     * <p><b>201</b> - Report about imported records.
     * <p><b>0</b> - default response
     * @param metadataType The type of record. (optional, default to METADATA)
     * @param file XML or MEF file to upload (optional, default to new ArrayList&lt;&gt;())
     * @param uuidProcessing Record identifier processing. (optional, default to NOTHING)
     * @param group The group the record is attached to. (optional)
     * @param category Tags to assign to the record. (optional, default to new ArrayList&lt;&gt;())
     * @param rejectIfInvalid Validate the record first and reject it if not valid. (optional, default to false)
     * @param publishToAll (XML file only) Publish record. (optional, default to false)
     * @param assignToCatalog (MEF file only) Assign to current catalog. (optional, default to false)
     * @param transformWith XSL transformation to apply to the record. (optional, default to &quot;_none_&quot;)
     * @param schema Force the schema of the record. If not set, schema autodetection is used (and is the preferred method). (optional)
     * @param extra (experimental) Add extra information to the record. (optional)
     * @return SimpleMetadataProcessingReport
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public SimpleMetadataProcessingReport insertFile(String metadataType, List<File> file, String uuidProcessing, String group, List<String> category, Boolean rejectIfInvalid, Boolean publishToAll, Boolean assignToCatalog, String transformWith, String schema, String extra) throws RestClientException {
        return insertFileWithHttpInfo(metadataType, file, uuidProcessing, group, category, rejectIfInvalid, publishToAll, assignToCatalog, transformWith, schema, extra).getBody();
    }

    /**
     * Add a record from XML or MEF/ZIP file
     * Add record in the catalog by uploading files.
     * <p><b>403</b> - Operation not allowed. Only Editors can access it.
     * <p><b>201</b> - Report about imported records.
     * <p><b>0</b> - default response
     * @param metadataType The type of record. (optional, default to METADATA)
     * @param file XML or MEF file to upload (optional, default to new ArrayList&lt;&gt;())
     * @param uuidProcessing Record identifier processing. (optional, default to NOTHING)
     * @param group The group the record is attached to. (optional)
     * @param category Tags to assign to the record. (optional, default to new ArrayList&lt;&gt;())
     * @param rejectIfInvalid Validate the record first and reject it if not valid. (optional, default to false)
     * @param publishToAll (XML file only) Publish record. (optional, default to false)
     * @param assignToCatalog (MEF file only) Assign to current catalog. (optional, default to false)
     * @param transformWith XSL transformation to apply to the record. (optional, default to &quot;_none_&quot;)
     * @param schema Force the schema of the record. If not set, schema autodetection is used (and is the preferred method). (optional)
     * @param extra (experimental) Add extra information to the record. (optional)
     * @return ResponseEntity&lt;SimpleMetadataProcessingReport&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<SimpleMetadataProcessingReport> insertFileWithHttpInfo(String metadataType, List<File> file, String uuidProcessing, String group, List<String> category, Boolean rejectIfInvalid, Boolean publishToAll, Boolean assignToCatalog, String transformWith, String schema, String extra) throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/records", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "metadataType", metadataType));
        queryParams.putAll(apiClient.parameterToMultiValueMap(ApiClient.CollectionFormat.valueOf("multi".toUpperCase(Locale.ROOT)), "file", file));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "uuidProcessing", uuidProcessing));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "group", group));
        queryParams.putAll(apiClient.parameterToMultiValueMap(ApiClient.CollectionFormat.valueOf("multi".toUpperCase(Locale.ROOT)), "category", category));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "rejectIfInvalid", rejectIfInvalid));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "publishToAll", publishToAll));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "assignToCatalog", assignToCatalog));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "transformWith", transformWith));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "schema", schema));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "extra", extra));

        final String[] accepts = { 
            "application/json"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] { "basicAuth" };

        ParameterizedTypeReference<SimpleMetadataProcessingReport> returnType = new ParameterizedTypeReference<SimpleMetadataProcessingReport>() {};
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Add a map metadata record from OGC OWS context
     * Add record in the catalog by uploading a map context.
     * <p><b>403</b> - Operation not allowed. Only Editors can access it.
     * <p><b>201</b> - Report about imported records.
     * <p><b>0</b> - default response
     * @param title A map title (required)
     * @param recordAbstract A map abstract (optional)
     * @param xml OGC OWS context as string (optional)
     * @param filename OGC OWS context file name (optional)
     * @param url OGC OWS context URL (optional)
     * @param viewerUrl A map viewer URL to visualize the map (optional)
     * @param overview Map overview as PNG (base64 encoded) (optional)
     * @param overviewFilename Map overview filename (optional)
     * @param topic Topic category (optional)
     * @param publishToAll Publish record. (optional, default to false)
     * @param uuidProcessing Record identifier processing. (optional, default to NOTHING)
     * @param group The group the record is attached to. (optional)
     * @return SimpleMetadataProcessingReport
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public SimpleMetadataProcessingReport insertOgcMapContextFile(String title, String recordAbstract, String xml, String filename, String url, String viewerUrl, String overview, String overviewFilename, String topic, Boolean publishToAll, String uuidProcessing, String group) throws RestClientException {
        return insertOgcMapContextFileWithHttpInfo(title, recordAbstract, xml, filename, url, viewerUrl, overview, overviewFilename, topic, publishToAll, uuidProcessing, group).getBody();
    }

    /**
     * Add a map metadata record from OGC OWS context
     * Add record in the catalog by uploading a map context.
     * <p><b>403</b> - Operation not allowed. Only Editors can access it.
     * <p><b>201</b> - Report about imported records.
     * <p><b>0</b> - default response
     * @param title A map title (required)
     * @param recordAbstract A map abstract (optional)
     * @param xml OGC OWS context as string (optional)
     * @param filename OGC OWS context file name (optional)
     * @param url OGC OWS context URL (optional)
     * @param viewerUrl A map viewer URL to visualize the map (optional)
     * @param overview Map overview as PNG (base64 encoded) (optional)
     * @param overviewFilename Map overview filename (optional)
     * @param topic Topic category (optional)
     * @param publishToAll Publish record. (optional, default to false)
     * @param uuidProcessing Record identifier processing. (optional, default to NOTHING)
     * @param group The group the record is attached to. (optional)
     * @return ResponseEntity&lt;SimpleMetadataProcessingReport&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<SimpleMetadataProcessingReport> insertOgcMapContextFileWithHttpInfo(String title, String recordAbstract, String xml, String filename, String url, String viewerUrl, String overview, String overviewFilename, String topic, Boolean publishToAll, String uuidProcessing, String group) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'title' is set
        if (title == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'title' when calling insertOgcMapContextFile");
        }
        
        String path = apiClient.expandPath("/records/importfrommap", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "title", title));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "recordAbstract", recordAbstract));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "xml", xml));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "filename", filename));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "url", url));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "viewerUrl", viewerUrl));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "overview", overview));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "overviewFilename", overviewFilename));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "topic", topic));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "publishToAll", publishToAll));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "uuidProcessing", uuidProcessing));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "group", group));

        final String[] accepts = { 
            "application/json"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] { "basicAuth" };

        ParameterizedTypeReference<SimpleMetadataProcessingReport> returnType = new ParameterizedTypeReference<SimpleMetadataProcessingReport>() {};
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Update the metadata resource visibility
     * 
     * <p><b>201</b> - Attachment visibility updated.
     * <p><b>403</b> - Operation not allowed. User needs to be able to edit the resource.
     * <p><b>0</b> - default response
     * @param metadataUuid The metadata UUID (required)
     * @param resourceId The resource identifier (ie. filename) (required)
     * @param visibility The visibility (required)
     * @param approved Use approved version or not (optional, default to false)
     * @return MetadataResource
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public MetadataResource patchResource(String metadataUuid, String resourceId, String visibility, Boolean approved) throws RestClientException {
        return patchResourceWithHttpInfo(metadataUuid, resourceId, visibility, approved).getBody();
    }

    /**
     * Update the metadata resource visibility
     * 
     * <p><b>201</b> - Attachment visibility updated.
     * <p><b>403</b> - Operation not allowed. User needs to be able to edit the resource.
     * <p><b>0</b> - default response
     * @param metadataUuid The metadata UUID (required)
     * @param resourceId The resource identifier (ie. filename) (required)
     * @param visibility The visibility (required)
     * @param approved Use approved version or not (optional, default to false)
     * @return ResponseEntity&lt;MetadataResource&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<MetadataResource> patchResourceWithHttpInfo(String metadataUuid, String resourceId, String visibility, Boolean approved) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'metadataUuid' is set
        if (metadataUuid == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'metadataUuid' when calling patchResource");
        }
        
        // verify the required parameter 'resourceId' is set
        if (resourceId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'resourceId' when calling patchResource");
        }
        
        // verify the required parameter 'visibility' is set
        if (visibility == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'visibility' when calling patchResource");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("metadataUuid", metadataUuid);
        uriVariables.put("resourceId", resourceId);
        String path = apiClient.expandPath("/records/{metadataUuid}/attachments/{resourceId}", uriVariables);

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "visibility", visibility));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "approved", approved));

        final String[] accepts = { 
            "application/json"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] { "basicAuth" };

        ParameterizedTypeReference<MetadataResource> returnType = new ParameterizedTypeReference<MetadataResource>() {};
        return apiClient.invokeAPI(path, HttpMethod.PATCH, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Apply a process
     * Process a metadata with an XSL transformation declared in the metadata schema (See the process folder). Parameters sent to the service are forwarded to XSL process.
     * <p><b>403</b> - Operation not allowed. User needs to be able to edit the resource.
     * <p><b>204</b> - Record processed and saved.
     * <p><b>0</b> - default response
     * @param metadataUuid Record UUID. (required)
     * @param process Process identifier (required)
     * @return String
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public String processRecord(String metadataUuid, String process) throws RestClientException {
        return processRecordWithHttpInfo(metadataUuid, process).getBody();
    }

    /**
     * Apply a process
     * Process a metadata with an XSL transformation declared in the metadata schema (See the process folder). Parameters sent to the service are forwarded to XSL process.
     * <p><b>403</b> - Operation not allowed. User needs to be able to edit the resource.
     * <p><b>204</b> - Record processed and saved.
     * <p><b>0</b> - default response
     * @param metadataUuid Record UUID. (required)
     * @param process Process identifier (required)
     * @return ResponseEntity&lt;String&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<String> processRecordWithHttpInfo(String metadataUuid, String process) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'metadataUuid' is set
        if (metadataUuid == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'metadataUuid' when calling processRecord");
        }
        
        // verify the required parameter 'process' is set
        if (process == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'process' when calling processRecord");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("metadataUuid", metadataUuid);
        uriVariables.put("process", process);
        String path = apiClient.expandPath("/records/{metadataUuid}/processes/{process}", uriVariables);

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        final String[] accepts = { 
            "application/json", "application/xml"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] { "basicAuth" };

        ParameterizedTypeReference<String> returnType = new ParameterizedTypeReference<String>() {};
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Preview process result
     * Process a metadata with an XSL transformation declared in the metadata schema (See the process folder). Parameters sent to the service are forwarded to XSL process.
     * <p><b>200</b> - A preview of the processed record.
     * <p><b>403</b> - Operation not allowed. User needs to be able to edit the resource.
     * <p><b>0</b> - default response
     * @param metadataUuid Record UUID. (required)
     * @param process Process identifier (required)
     * @return Element
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Element processRecordPreview(String metadataUuid, String process) throws RestClientException {
        return processRecordPreviewWithHttpInfo(metadataUuid, process).getBody();
    }

    /**
     * Preview process result
     * Process a metadata with an XSL transformation declared in the metadata schema (See the process folder). Parameters sent to the service are forwarded to XSL process.
     * <p><b>200</b> - A preview of the processed record.
     * <p><b>403</b> - Operation not allowed. User needs to be able to edit the resource.
     * <p><b>0</b> - default response
     * @param metadataUuid Record UUID. (required)
     * @param process Process identifier (required)
     * @return ResponseEntity&lt;Element&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Element> processRecordPreviewWithHttpInfo(String metadataUuid, String process) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'metadataUuid' is set
        if (metadataUuid == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'metadataUuid' when calling processRecordPreview");
        }
        
        // verify the required parameter 'process' is set
        if (process == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'process' when calling processRecordPreview");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("metadataUuid", metadataUuid);
        uriVariables.put("process", process);
        String path = apiClient.expandPath("/records/{metadataUuid}/processes/{process}", uriVariables);

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        final String[] accepts = { 
            "application/json", "application/xml"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] { "basicAuth" };

        ParameterizedTypeReference<Element> returnType = new ParameterizedTypeReference<Element>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Publish one or more records
     * See record sharing for more details.
     * <p><b>201</b> - Report about updated privileges.
     * <p><b>403</b> - Operation not allowed. Only Editors can access it.
     * <p><b>0</b> - default response
     * @param uuids Record UUIDs. If null current selection is used. (optional, default to new ArrayList&lt;&gt;())
     * @param bucket Selection bucket name (optional)
     * @return MetadataProcessingReport
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public MetadataProcessingReport publish(List<String> uuids, String bucket) throws RestClientException {
        return publishWithHttpInfo(uuids, bucket).getBody();
    }

    /**
     * Publish one or more records
     * See record sharing for more details.
     * <p><b>201</b> - Report about updated privileges.
     * <p><b>403</b> - Operation not allowed. Only Editors can access it.
     * <p><b>0</b> - default response
     * @param uuids Record UUIDs. If null current selection is used. (optional, default to new ArrayList&lt;&gt;())
     * @param bucket Selection bucket name (optional)
     * @return ResponseEntity&lt;MetadataProcessingReport&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<MetadataProcessingReport> publishWithHttpInfo(List<String> uuids, String bucket) throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/records/publish", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        queryParams.putAll(apiClient.parameterToMultiValueMap(ApiClient.CollectionFormat.valueOf("multi".toUpperCase(Locale.ROOT)), "uuids", uuids));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "bucket", bucket));

        final String[] accepts = { 
            "application/json", "*/*"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] { "basicAuth" };

        ParameterizedTypeReference<MetadataProcessingReport> returnType = new ParameterizedTypeReference<MetadataProcessingReport>() {};
        return apiClient.invokeAPI(path, HttpMethod.PUT, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Create a new resource for a given metadata
     * 
     * <p><b>403</b> - Operation not allowed. User needs to be able to edit the resource.
     * <p><b>201</b> - Attachment uploaded.
     * <p><b>0</b> - default response
     * @param metadataUuid The metadata UUID (required)
     * @param visibility The sharing policy (optional, default to public)
     * @param approved Use approved version or not (optional, default to false)
     * @param inlineObject1  (optional)
     * @return MetadataResource
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public MetadataResource putResource(String metadataUuid, String visibility, Boolean approved, InlineObject1 inlineObject1) throws RestClientException {
        return putResourceWithHttpInfo(metadataUuid, visibility, approved, inlineObject1).getBody();
    }

    /**
     * Create a new resource for a given metadata
     * 
     * <p><b>403</b> - Operation not allowed. User needs to be able to edit the resource.
     * <p><b>201</b> - Attachment uploaded.
     * <p><b>0</b> - default response
     * @param metadataUuid The metadata UUID (required)
     * @param visibility The sharing policy (optional, default to public)
     * @param approved Use approved version or not (optional, default to false)
     * @param inlineObject1  (optional)
     * @return ResponseEntity&lt;MetadataResource&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<MetadataResource> putResourceWithHttpInfo(String metadataUuid, String visibility, Boolean approved, InlineObject1 inlineObject1) throws RestClientException {
        Object postBody = inlineObject1;
        
        // verify the required parameter 'metadataUuid' is set
        if (metadataUuid == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'metadataUuid' when calling putResource");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("metadataUuid", metadataUuid);
        String path = apiClient.expandPath("/records/{metadataUuid}/attachments", uriVariables);

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "visibility", visibility));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "approved", approved));

        final String[] accepts = { 
            "application/json"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { 
            "application/json"
        };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] { "basicAuth" };

        ParameterizedTypeReference<MetadataResource> returnType = new ParameterizedTypeReference<MetadataResource>() {};
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Create a new resource from a URL for a given metadata
     * 
     * <p><b>201</b> - Attachment added.
     * <p><b>403</b> - Operation not allowed. User needs to be able to edit the resource.
     * <p><b>0</b> - default response
     * @param metadataUuid The metadata UUID (required)
     * @param url The URL to load in the store (required)
     * @param visibility The sharing policy (optional, default to public)
     * @param approved Use approved version or not (optional, default to false)
     * @return MetadataResource
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public MetadataResource putResourceFromURL(String metadataUuid, String url, String visibility, Boolean approved) throws RestClientException {
        return putResourceFromURLWithHttpInfo(metadataUuid, url, visibility, approved).getBody();
    }

    /**
     * Create a new resource from a URL for a given metadata
     * 
     * <p><b>201</b> - Attachment added.
     * <p><b>403</b> - Operation not allowed. User needs to be able to edit the resource.
     * <p><b>0</b> - default response
     * @param metadataUuid The metadata UUID (required)
     * @param url The URL to load in the store (required)
     * @param visibility The sharing policy (optional, default to public)
     * @param approved Use approved version or not (optional, default to false)
     * @return ResponseEntity&lt;MetadataResource&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<MetadataResource> putResourceFromURLWithHttpInfo(String metadataUuid, String url, String visibility, Boolean approved) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'metadataUuid' is set
        if (metadataUuid == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'metadataUuid' when calling putResourceFromURL");
        }
        
        // verify the required parameter 'url' is set
        if (url == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'url' when calling putResourceFromURL");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("metadataUuid", metadataUuid);
        String path = apiClient.expandPath("/records/{metadataUuid}/attachments", uriVariables);

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "visibility", visibility));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "url", url));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "approved", approved));

        final String[] accepts = { 
            "application/json", "*/*"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] { "basicAuth" };

        ParameterizedTypeReference<MetadataResource> returnType = new ParameterizedTypeReference<MetadataResource>() {};
        return apiClient.invokeAPI(path, HttpMethod.PUT, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Rate a record
     * User rating of metadata. If the metadata was harvested using the &#39;GeoNetwork&#39; protocol and the system setting localrating/enable is false (the default), the user&#39;s rating is shared between GN nodes in this harvesting network. If the metadata was not harvested or if localrating/enable is true then &#39;local rating&#39; is applied, counting only rating from users of this node.&lt;br/&gt;When a remote rating is applied, the local rating is not updated. It will be updated on the next harvest run (FIXME ?).
     * <p><b>201</b> - New rating value.
     * <p><b>403</b> - Operation not allowed. User needs to be able to view the resource.
     * <p><b>0</b> - default response
     * @param metadataUuid Record UUID. (required)
     * @param body Rating (required)
     * @return Integer
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Integer rateRecord(String metadataUuid, Integer body) throws RestClientException {
        return rateRecordWithHttpInfo(metadataUuid, body).getBody();
    }

    /**
     * Rate a record
     * User rating of metadata. If the metadata was harvested using the &#39;GeoNetwork&#39; protocol and the system setting localrating/enable is false (the default), the user&#39;s rating is shared between GN nodes in this harvesting network. If the metadata was not harvested or if localrating/enable is true then &#39;local rating&#39; is applied, counting only rating from users of this node.&lt;br/&gt;When a remote rating is applied, the local rating is not updated. It will be updated on the next harvest run (FIXME ?).
     * <p><b>201</b> - New rating value.
     * <p><b>403</b> - Operation not allowed. User needs to be able to view the resource.
     * <p><b>0</b> - default response
     * @param metadataUuid Record UUID. (required)
     * @param body Rating (required)
     * @return ResponseEntity&lt;Integer&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Integer> rateRecordWithHttpInfo(String metadataUuid, Integer body) throws RestClientException {
        Object postBody = body;
        
        // verify the required parameter 'metadataUuid' is set
        if (metadataUuid == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'metadataUuid' when calling rateRecord");
        }
        
        // verify the required parameter 'body' is set
        if (body == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'body' when calling rateRecord");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("metadataUuid", metadataUuid);
        String path = apiClient.expandPath("/records/{metadataUuid}/rate", uriVariables);

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

        ParameterizedTypeReference<Integer> returnType = new ParameterizedTypeReference<Integer>() {};
        return apiClient.invokeAPI(path, HttpMethod.PUT, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Save edits
     * Save the HTML form content.
     * <p><b>200</b> - The editor form.
     * <p><b>403</b> - Operation not allowed. User needs to be able to edit the resource.
     * <p><b>0</b> - default response
     * @param metadataUuid Record UUID. (required)
     * @param tab Tab (optional, default to &quot;simple&quot;)
     * @param withAttributes  (optional, default to false)
     * @param withValidationErrors  (optional, default to false)
     * @param minor  (optional, default to false)
     * @param status Submit for review directly after save. (optional, default to &quot;1&quot;)
     * @param commit Save current edits. (optional, default to false)
     * @param terminate Save and terminate session. (optional, default to false)
     * @param data Record as XML. TODO: rename xml (optional, default to &quot;&quot;)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void saveEdits(String metadataUuid, String tab, Boolean withAttributes, Boolean withValidationErrors, Boolean minor, String status, Boolean commit, Boolean terminate, String data) throws RestClientException {
        saveEditsWithHttpInfo(metadataUuid, tab, withAttributes, withValidationErrors, minor, status, commit, terminate, data);
    }

    /**
     * Save edits
     * Save the HTML form content.
     * <p><b>200</b> - The editor form.
     * <p><b>403</b> - Operation not allowed. User needs to be able to edit the resource.
     * <p><b>0</b> - default response
     * @param metadataUuid Record UUID. (required)
     * @param tab Tab (optional, default to &quot;simple&quot;)
     * @param withAttributes  (optional, default to false)
     * @param withValidationErrors  (optional, default to false)
     * @param minor  (optional, default to false)
     * @param status Submit for review directly after save. (optional, default to &quot;1&quot;)
     * @param commit Save current edits. (optional, default to false)
     * @param terminate Save and terminate session. (optional, default to false)
     * @param data Record as XML. TODO: rename xml (optional, default to &quot;&quot;)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> saveEditsWithHttpInfo(String metadataUuid, String tab, Boolean withAttributes, Boolean withValidationErrors, Boolean minor, String status, Boolean commit, Boolean terminate, String data) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'metadataUuid' is set
        if (metadataUuid == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'metadataUuid' when calling saveEdits");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("metadataUuid", metadataUuid);
        String path = apiClient.expandPath("/records/{metadataUuid}/editor", uriVariables);

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "tab", tab));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "withAttributes", withAttributes));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "withValidationErrors", withValidationErrors));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "minor", minor));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "status", status));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "commit", commit));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "terminate", terminate));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "data", data));

        final String[] accepts = { 
            "application/json"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] { "basicAuth" };

        ParameterizedTypeReference<Void> returnType = new ParameterizedTypeReference<Void>() {};
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Create an overview using the map print module
     * &lt;a href&#x3D;&#39;http://geonetwork-opensource.org/manuals/trunk/eng/users/user-guide/associating-resources/linking-thumbnail.html#generating-a-thumbnail-using-wms-layers&#39;&gt;More info&lt;/a&gt;
     * <p><b>201</b> - Thumbnail created.
     * <p><b>403</b> - Operation not allowed. User needs to be able to edit the resource.
     * <p><b>0</b> - default response
     * @param metadataUuid Record UUID. (required)
     * @param jsonConfig The mapprint module JSON configuration (required)
     * @param rotationAngle The rotation angle of the map (optional, default to 0)
     * @return MetadataResource
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public MetadataResource saveThumbnail(String metadataUuid, String jsonConfig, Integer rotationAngle) throws RestClientException {
        return saveThumbnailWithHttpInfo(metadataUuid, jsonConfig, rotationAngle).getBody();
    }

    /**
     * Create an overview using the map print module
     * &lt;a href&#x3D;&#39;http://geonetwork-opensource.org/manuals/trunk/eng/users/user-guide/associating-resources/linking-thumbnail.html#generating-a-thumbnail-using-wms-layers&#39;&gt;More info&lt;/a&gt;
     * <p><b>201</b> - Thumbnail created.
     * <p><b>403</b> - Operation not allowed. User needs to be able to edit the resource.
     * <p><b>0</b> - default response
     * @param metadataUuid Record UUID. (required)
     * @param jsonConfig The mapprint module JSON configuration (required)
     * @param rotationAngle The rotation angle of the map (optional, default to 0)
     * @return ResponseEntity&lt;MetadataResource&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<MetadataResource> saveThumbnailWithHttpInfo(String metadataUuid, String jsonConfig, Integer rotationAngle) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'metadataUuid' is set
        if (metadataUuid == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'metadataUuid' when calling saveThumbnail");
        }
        
        // verify the required parameter 'jsonConfig' is set
        if (jsonConfig == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'jsonConfig' when calling saveThumbnail");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("metadataUuid", metadataUuid);
        String path = apiClient.expandPath("/records/{metadataUuid}/attachments/print-thumbnail", uriVariables);

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "jsonConfig", jsonConfig));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "rotationAngle", rotationAngle));

        final String[] accepts = { 
            "application/json", "*/*"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] { "basicAuth" };

        ParameterizedTypeReference<MetadataResource> returnType = new ParameterizedTypeReference<MetadataResource>() {};
        return apiClient.invokeAPI(path, HttpMethod.PUT, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Set group and owner for one or more records
     * 
     * <p><b>403</b> - Operation not allowed. User needs to be able to edit the resource.
     * <p><b>201</b> - Records group and owner updated
     * <p><b>0</b> - default response
     * @param groupIdentifier Group identifier (required)
     * @param userIdentifier User identifier (required)
     * @param uuids Record UUIDs. If null current selection is used. (optional, default to new ArrayList&lt;&gt;())
     * @param bucket Selection bucket name (optional)
     * @param approved Use approved version or not (optional, default to false)
     * @return MetadataProcessingReport
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public MetadataProcessingReport setGroupAndOwner(Integer groupIdentifier, Integer userIdentifier, List<String> uuids, String bucket, Boolean approved) throws RestClientException {
        return setGroupAndOwnerWithHttpInfo(groupIdentifier, userIdentifier, uuids, bucket, approved).getBody();
    }

    /**
     * Set group and owner for one or more records
     * 
     * <p><b>403</b> - Operation not allowed. User needs to be able to edit the resource.
     * <p><b>201</b> - Records group and owner updated
     * <p><b>0</b> - default response
     * @param groupIdentifier Group identifier (required)
     * @param userIdentifier User identifier (required)
     * @param uuids Record UUIDs. If null current selection is used. (optional, default to new ArrayList&lt;&gt;())
     * @param bucket Selection bucket name (optional)
     * @param approved Use approved version or not (optional, default to false)
     * @return ResponseEntity&lt;MetadataProcessingReport&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<MetadataProcessingReport> setGroupAndOwnerWithHttpInfo(Integer groupIdentifier, Integer userIdentifier, List<String> uuids, String bucket, Boolean approved) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'groupIdentifier' is set
        if (groupIdentifier == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'groupIdentifier' when calling setGroupAndOwner");
        }
        
        // verify the required parameter 'userIdentifier' is set
        if (userIdentifier == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'userIdentifier' when calling setGroupAndOwner");
        }
        
        String path = apiClient.expandPath("/records/ownership", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        queryParams.putAll(apiClient.parameterToMultiValueMap(ApiClient.CollectionFormat.valueOf("multi".toUpperCase(Locale.ROOT)), "uuids", uuids));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "groupIdentifier", groupIdentifier));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "bucket", bucket));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "userIdentifier", userIdentifier));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "approved", approved));

        final String[] accepts = { 
            "application/json", "*/*"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] { "basicAuth" };

        ParameterizedTypeReference<MetadataProcessingReport> returnType = new ParameterizedTypeReference<MetadataProcessingReport>() {};
        return apiClient.invokeAPI(path, HttpMethod.PUT, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Set record group
     * A record is related to one group.
     * <p><b>204</b> - Record group updated.
     * <p><b>403</b> - Operation not allowed. User needs to be able to edit the resource.
     * <p><b>0</b> - default response
     * @param metadataUuid Record UUID. (required)
     * @param body Group identifier (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void setRecordGroup(String metadataUuid, Integer body) throws RestClientException {
        setRecordGroupWithHttpInfo(metadataUuid, body);
    }

    /**
     * Set record group
     * A record is related to one group.
     * <p><b>204</b> - Record group updated.
     * <p><b>403</b> - Operation not allowed. User needs to be able to edit the resource.
     * <p><b>0</b> - default response
     * @param metadataUuid Record UUID. (required)
     * @param body Group identifier (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> setRecordGroupWithHttpInfo(String metadataUuid, Integer body) throws RestClientException {
        Object postBody = body;
        
        // verify the required parameter 'metadataUuid' is set
        if (metadataUuid == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'metadataUuid' when calling setRecordGroup");
        }
        
        // verify the required parameter 'body' is set
        if (body == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'body' when calling setRecordGroup");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("metadataUuid", metadataUuid);
        String path = apiClient.expandPath("/records/{metadataUuid}/group", uriVariables);

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
    /**
     * Set record group and owner
     * 
     * <p><b>403</b> - Operation not allowed. User needs to be able to edit the resource.
     * <p><b>201</b> - Record group and owner updated
     * <p><b>0</b> - default response
     * @param metadataUuid Record UUID. (required)
     * @param groupIdentifier Group identifier (required)
     * @param userIdentifier User identifier (required)
     * @param approved Use approved version or not (optional, default to true)
     * @return MetadataProcessingReport
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public MetadataProcessingReport setRecordOwnership(String metadataUuid, Integer groupIdentifier, Integer userIdentifier, Boolean approved) throws RestClientException {
        return setRecordOwnershipWithHttpInfo(metadataUuid, groupIdentifier, userIdentifier, approved).getBody();
    }

    /**
     * Set record group and owner
     * 
     * <p><b>403</b> - Operation not allowed. User needs to be able to edit the resource.
     * <p><b>201</b> - Record group and owner updated
     * <p><b>0</b> - default response
     * @param metadataUuid Record UUID. (required)
     * @param groupIdentifier Group identifier (required)
     * @param userIdentifier User identifier (required)
     * @param approved Use approved version or not (optional, default to true)
     * @return ResponseEntity&lt;MetadataProcessingReport&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<MetadataProcessingReport> setRecordOwnershipWithHttpInfo(String metadataUuid, Integer groupIdentifier, Integer userIdentifier, Boolean approved) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'metadataUuid' is set
        if (metadataUuid == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'metadataUuid' when calling setRecordOwnership");
        }
        
        // verify the required parameter 'groupIdentifier' is set
        if (groupIdentifier == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'groupIdentifier' when calling setRecordOwnership");
        }
        
        // verify the required parameter 'userIdentifier' is set
        if (userIdentifier == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'userIdentifier' when calling setRecordOwnership");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("metadataUuid", metadataUuid);
        String path = apiClient.expandPath("/records/{metadataUuid}/ownership", uriVariables);

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "groupIdentifier", groupIdentifier));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "userIdentifier", userIdentifier));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "approved", approved));

        final String[] accepts = { 
            "application/json", "*/*"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] { "basicAuth" };

        ParameterizedTypeReference<MetadataProcessingReport> returnType = new ParameterizedTypeReference<MetadataProcessingReport>() {};
        return apiClient.invokeAPI(path, HttpMethod.PUT, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Set sharing settings for one or more records
     * See record sharing for more details.
     * <p><b>201</b> - Report about updated privileges.
     * <p><b>403</b> - Operation not allowed. Only Editors can access it.
     * <p><b>0</b> - default response
     * @param sharingParameter Privileges (required)
     * @param uuids Record UUIDs. If null current selection is used. (optional, default to new ArrayList&lt;&gt;())
     * @param bucket Selection bucket name (optional)
     * @return MetadataProcessingReport
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public MetadataProcessingReport share(SharingParameter sharingParameter, List<String> uuids, String bucket) throws RestClientException {
        return shareWithHttpInfo(sharingParameter, uuids, bucket).getBody();
    }

    /**
     * Set sharing settings for one or more records
     * See record sharing for more details.
     * <p><b>201</b> - Report about updated privileges.
     * <p><b>403</b> - Operation not allowed. Only Editors can access it.
     * <p><b>0</b> - default response
     * @param sharingParameter Privileges (required)
     * @param uuids Record UUIDs. If null current selection is used. (optional, default to new ArrayList&lt;&gt;())
     * @param bucket Selection bucket name (optional)
     * @return ResponseEntity&lt;MetadataProcessingReport&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<MetadataProcessingReport> shareWithHttpInfo(SharingParameter sharingParameter, List<String> uuids, String bucket) throws RestClientException {
        Object postBody = sharingParameter;
        
        // verify the required parameter 'sharingParameter' is set
        if (sharingParameter == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'sharingParameter' when calling share");
        }
        
        String path = apiClient.expandPath("/records/sharing", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        queryParams.putAll(apiClient.parameterToMultiValueMap(ApiClient.CollectionFormat.valueOf("multi".toUpperCase(Locale.ROOT)), "uuids", uuids));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "bucket", bucket));

        final String[] accepts = { 
            "application/json", "*/*"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { 
            "application/json"
        };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] { "basicAuth" };

        ParameterizedTypeReference<MetadataProcessingReport> returnType = new ParameterizedTypeReference<MetadataProcessingReport>() {};
        return apiClient.invokeAPI(path, HttpMethod.PUT, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Add tags to a record
     * 
     * <p><b>403</b> - Operation not allowed. User needs to be able to edit the resource.
     * <p><b>201</b> - Record tags added.
     * <p><b>0</b> - default response
     * @param metadataUuid Record UUID. (required)
     * @param id Tag identifier (required)
     * @param clear Clear all before adding new ones (optional, default to false)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void tagRecord(String metadataUuid, List<Integer> id, Boolean clear) throws RestClientException {
        tagRecordWithHttpInfo(metadataUuid, id, clear);
    }

    /**
     * Add tags to a record
     * 
     * <p><b>403</b> - Operation not allowed. User needs to be able to edit the resource.
     * <p><b>201</b> - Record tags added.
     * <p><b>0</b> - default response
     * @param metadataUuid Record UUID. (required)
     * @param id Tag identifier (required)
     * @param clear Clear all before adding new ones (optional, default to false)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> tagRecordWithHttpInfo(String metadataUuid, List<Integer> id, Boolean clear) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'metadataUuid' is set
        if (metadataUuid == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'metadataUuid' when calling tagRecord");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling tagRecord");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("metadataUuid", metadataUuid);
        String path = apiClient.expandPath("/records/{metadataUuid}/tags", uriVariables);

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        queryParams.putAll(apiClient.parameterToMultiValueMap(ApiClient.CollectionFormat.valueOf("multi".toUpperCase(Locale.ROOT)), "id", id));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "clear", clear));

        final String[] accepts = { 
            "application/json"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] { "basicAuth" };

        ParameterizedTypeReference<Void> returnType = new ParameterizedTypeReference<Void>() {};
        return apiClient.invokeAPI(path, HttpMethod.PUT, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Add tags to one or more records
     * 
     * <p><b>403</b> - Operation not allowed. Only Editors can access it.
     * <p><b>201</b> - Report about updated records.
     * <p><b>0</b> - default response
     * @param id Tag identifier (required)
     * @param uuids Record UUIDs. If null current selection is used. (optional, default to new ArrayList&lt;&gt;())
     * @param bucket Selection bucket name (optional)
     * @param clear Clear all before adding new ones (optional, default to false)
     * @return MetadataProcessingReport
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public MetadataProcessingReport tagRecords(List<Integer> id, List<String> uuids, String bucket, Boolean clear) throws RestClientException {
        return tagRecordsWithHttpInfo(id, uuids, bucket, clear).getBody();
    }

    /**
     * Add tags to one or more records
     * 
     * <p><b>403</b> - Operation not allowed. Only Editors can access it.
     * <p><b>201</b> - Report about updated records.
     * <p><b>0</b> - default response
     * @param id Tag identifier (required)
     * @param uuids Record UUIDs. If null current selection is used. (optional, default to new ArrayList&lt;&gt;())
     * @param bucket Selection bucket name (optional)
     * @param clear Clear all before adding new ones (optional, default to false)
     * @return ResponseEntity&lt;MetadataProcessingReport&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<MetadataProcessingReport> tagRecordsWithHttpInfo(List<Integer> id, List<String> uuids, String bucket, Boolean clear) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling tagRecords");
        }
        
        String path = apiClient.expandPath("/records/tags", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        queryParams.putAll(apiClient.parameterToMultiValueMap(ApiClient.CollectionFormat.valueOf("multi".toUpperCase(Locale.ROOT)), "uuids", uuids));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "bucket", bucket));
        queryParams.putAll(apiClient.parameterToMultiValueMap(ApiClient.CollectionFormat.valueOf("multi".toUpperCase(Locale.ROOT)), "id", id));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "clear", clear));

        final String[] accepts = { 
            "application/json"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] { "basicAuth" };

        ParameterizedTypeReference<MetadataProcessingReport> returnType = new ParameterizedTypeReference<MetadataProcessingReport>() {};
        return apiClient.invokeAPI(path, HttpMethod.PUT, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Trigger MEF backup archive
     * The backup contains all metadata not harvested including templates.
     * <p><b>200</b> - Return succeed message.
     * <p><b>0</b> - default response
     * @return String
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public String trigger() throws RestClientException {
        return triggerWithHttpInfo().getBody();
    }

    /**
     * Trigger MEF backup archive
     * The backup contains all metadata not harvested including templates.
     * <p><b>200</b> - Return succeed message.
     * <p><b>0</b> - default response
     * @return ResponseEntity&lt;String&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<String> triggerWithHttpInfo() throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/records/backups", Collections.<String, Object>emptyMap());

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
        return apiClient.invokeAPI(path, HttpMethod.PUT, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Un-publish one or more records
     * See record sharing for more details.
     * <p><b>201</b> - Report about updated privileges.
     * <p><b>403</b> - Operation not allowed. Only Editors can access it.
     * <p><b>0</b> - default response
     * @param uuids Record UUIDs. If null current selection is used. (optional, default to new ArrayList&lt;&gt;())
     * @param bucket Selection bucket name (optional)
     * @return MetadataProcessingReport
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public MetadataProcessingReport unpublish(List<String> uuids, String bucket) throws RestClientException {
        return unpublishWithHttpInfo(uuids, bucket).getBody();
    }

    /**
     * Un-publish one or more records
     * See record sharing for more details.
     * <p><b>201</b> - Report about updated privileges.
     * <p><b>403</b> - Operation not allowed. Only Editors can access it.
     * <p><b>0</b> - default response
     * @param uuids Record UUIDs. If null current selection is used. (optional, default to new ArrayList&lt;&gt;())
     * @param bucket Selection bucket name (optional)
     * @return ResponseEntity&lt;MetadataProcessingReport&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<MetadataProcessingReport> unpublishWithHttpInfo(List<String> uuids, String bucket) throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/records/unpublish", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        queryParams.putAll(apiClient.parameterToMultiValueMap(ApiClient.CollectionFormat.valueOf("multi".toUpperCase(Locale.ROOT)), "uuids", uuids));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "bucket", bucket));

        final String[] accepts = { 
            "application/json", "*/*"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] { "basicAuth" };

        ParameterizedTypeReference<MetadataProcessingReport> returnType = new ParameterizedTypeReference<MetadataProcessingReport>() {};
        return apiClient.invokeAPI(path, HttpMethod.PUT, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Validate a record
     * User MUST be able to edit the record to validate it. FIXME : id MUST be the id of the current metadata record in session ?
     * <p><b>201</b> - Validation report.
     * <p><b>403</b> - Operation not allowed. User needs to be able to edit the resource.
     * <p><b>0</b> - default response
     * @param metadataUuid Record UUID. (required)
     * @param isvalid Validation status. Should be provided only in case of SUBTEMPLATE validation. If provided for another type, throw a BadParameter Exception (optional)
     * @return Reports
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Reports validateRecord(String metadataUuid, Boolean isvalid) throws RestClientException {
        return validateRecordWithHttpInfo(metadataUuid, isvalid).getBody();
    }

    /**
     * Validate a record
     * User MUST be able to edit the record to validate it. FIXME : id MUST be the id of the current metadata record in session ?
     * <p><b>201</b> - Validation report.
     * <p><b>403</b> - Operation not allowed. User needs to be able to edit the resource.
     * <p><b>0</b> - default response
     * @param metadataUuid Record UUID. (required)
     * @param isvalid Validation status. Should be provided only in case of SUBTEMPLATE validation. If provided for another type, throw a BadParameter Exception (optional)
     * @return ResponseEntity&lt;Reports&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Reports> validateRecordWithHttpInfo(String metadataUuid, Boolean isvalid) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'metadataUuid' is set
        if (metadataUuid == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'metadataUuid' when calling validateRecord");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("metadataUuid", metadataUuid);
        String path = apiClient.expandPath("/records/{metadataUuid}/validate/internal", uriVariables);

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "isvalid", isvalid));

        final String[] accepts = { 
            "application/json", "application/xml", 
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] { "basicAuth" };

        ParameterizedTypeReference<Reports> returnType = new ParameterizedTypeReference<Reports>() {};
        return apiClient.invokeAPI(path, HttpMethod.PUT, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Submit a record to the INSPIRE service for validation.
     * User MUST be able to edit the record to validate it. An INSPIRE endpoint must be configured in Settings. This activates an asyncronous process, this method does not return any report. This method returns an id to be used to get the report.
     * <p><b>404</b> - Metadata not found.
     * <p><b>403</b> - Operation not allowed. User needs to be able to edit the resource.
     * <p><b>500</b> - Service unavailable.
     * <p><b>201</b> - Check status of the report.
     * <p><b>0</b> - default response
     * @param metadataUuid Record UUID. (required)
     * @param testsuite Test suite to run (required)
     * @return String
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public String validateRecordForInspire(String metadataUuid, String testsuite) throws RestClientException {
        return validateRecordForInspireWithHttpInfo(metadataUuid, testsuite).getBody();
    }

    /**
     * Submit a record to the INSPIRE service for validation.
     * User MUST be able to edit the record to validate it. An INSPIRE endpoint must be configured in Settings. This activates an asyncronous process, this method does not return any report. This method returns an id to be used to get the report.
     * <p><b>404</b> - Metadata not found.
     * <p><b>403</b> - Operation not allowed. User needs to be able to edit the resource.
     * <p><b>500</b> - Service unavailable.
     * <p><b>201</b> - Check status of the report.
     * <p><b>0</b> - default response
     * @param metadataUuid Record UUID. (required)
     * @param testsuite Test suite to run (required)
     * @return ResponseEntity&lt;String&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<String> validateRecordForInspireWithHttpInfo(String metadataUuid, String testsuite) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'metadataUuid' is set
        if (metadataUuid == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'metadataUuid' when calling validateRecordForInspire");
        }
        
        // verify the required parameter 'testsuite' is set
        if (testsuite == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'testsuite' when calling validateRecordForInspire");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("metadataUuid", metadataUuid);
        String path = apiClient.expandPath("/records/{metadataUuid}/validate/inspire", uriVariables);

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "testsuite", testsuite));

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
     * Validate one or more records
     * Update validation status for all records.
     * <p><b>403</b> - Operation not allowed. Only Editors can access it.
     * <p><b>201</b> - Records validated.
     * <p><b>0</b> - default response
     * @param uuids Record UUIDs. If null current selection is used. (optional, default to new ArrayList&lt;&gt;())
     * @param bucket Selection bucket name (optional)
     * @return SimpleMetadataProcessingReport
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public SimpleMetadataProcessingReport validateRecords(List<String> uuids, String bucket) throws RestClientException {
        return validateRecordsWithHttpInfo(uuids, bucket).getBody();
    }

    /**
     * Validate one or more records
     * Update validation status for all records.
     * <p><b>403</b> - Operation not allowed. Only Editors can access it.
     * <p><b>201</b> - Records validated.
     * <p><b>0</b> - default response
     * @param uuids Record UUIDs. If null current selection is used. (optional, default to new ArrayList&lt;&gt;())
     * @param bucket Selection bucket name (optional)
     * @return ResponseEntity&lt;SimpleMetadataProcessingReport&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<SimpleMetadataProcessingReport> validateRecordsWithHttpInfo(List<String> uuids, String bucket) throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/records/validate", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        queryParams.putAll(apiClient.parameterToMultiValueMap(ApiClient.CollectionFormat.valueOf("multi".toUpperCase(Locale.ROOT)), "uuids", uuids));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "bucket", bucket));

        final String[] accepts = { 
            "application/json"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] { "basicAuth" };

        ParameterizedTypeReference<SimpleMetadataProcessingReport> returnType = new ParameterizedTypeReference<SimpleMetadataProcessingReport>() {};
        return apiClient.invokeAPI(path, HttpMethod.PUT, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Validate one or more records in INSPIRE validator
     * Update validation status for all records.
     * <p><b>403</b> - Operation not allowed. Only Editors can access it.
     * <p><b>201</b> - Records validated.
     * <p><b>0</b> - default response
     * @param uuids Record UUIDs. If null current selection is used. (optional, default to new ArrayList&lt;&gt;())
     * @param bucket Selection bucket name (optional)
     * @return String
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public String validateRecordsInspire(List<String> uuids, String bucket) throws RestClientException {
        return validateRecordsInspireWithHttpInfo(uuids, bucket).getBody();
    }

    /**
     * Validate one or more records in INSPIRE validator
     * Update validation status for all records.
     * <p><b>403</b> - Operation not allowed. Only Editors can access it.
     * <p><b>201</b> - Records validated.
     * <p><b>0</b> - default response
     * @param uuids Record UUIDs. If null current selection is used. (optional, default to new ArrayList&lt;&gt;())
     * @param bucket Selection bucket name (optional)
     * @return ResponseEntity&lt;String&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<String> validateRecordsInspireWithHttpInfo(List<String> uuids, String bucket) throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/records/validate/inspire", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        queryParams.putAll(apiClient.parameterToMultiValueMap(ApiClient.CollectionFormat.valueOf("multi".toUpperCase(Locale.ROOT)), "uuids", uuids));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "bucket", bucket));

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
}
