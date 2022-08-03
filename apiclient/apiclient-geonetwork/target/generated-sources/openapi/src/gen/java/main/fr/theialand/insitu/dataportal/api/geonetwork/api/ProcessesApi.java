package fr.theialand.insitu.dataportal.api.geonetwork.api;

import fr.theialand.insitu.dataportal.api.geonetwork.handler.ApiClient;

import fr.theialand.insitu.dataportal.api.geonetwork.model.MetadataReplacementProcessingReport;
import fr.theialand.insitu.dataportal.api.geonetwork.model.ProcessingReport;
import fr.theialand.insitu.dataportal.api.geonetwork.model.XsltMetadataProcessingReport;

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
@Component("fr.theialand.insitu.dataportal.api.geonetwork.api.ProcessesApi")
public class ProcessesApi {
    private ApiClient apiClient;

    public ProcessesApi() {
        this(new ApiClient());
    }

    @Autowired
    public ProcessesApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Clear process reports list
     * 
     * <p><b>403</b> - Operation not allowed. Only authenticated user can access it.
     * <p><b>204</b> - Report registry cleared.
     * <p><b>0</b> - default response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void delete() throws RestClientException {
        deleteWithHttpInfo();
    }

    /**
     * Clear process reports list
     * 
     * <p><b>403</b> - Operation not allowed. Only authenticated user can access it.
     * <p><b>204</b> - Report registry cleared.
     * <p><b>0</b> - default response
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> deleteWithHttpInfo() throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/processes/reports", Collections.<String, Object>emptyMap());

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
     * Get current process reports
     * When processing, the report is stored in memory and allows to retrieve progress repport during processing. Usually, process reports are returned by the synchronous processing operation.
     * <p><b>200</b> - List of reports returned.
     * <p><b>403</b> - Operation not allowed. Only authenticated user can access it.
     * <p><b>0</b> - default response
     * @return List&lt;ProcessingReport&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<ProcessingReport> getProcessReport() throws RestClientException {
        return getProcessReportWithHttpInfo().getBody();
    }

    /**
     * Get current process reports
     * When processing, the report is stored in memory and allows to retrieve progress repport during processing. Usually, process reports are returned by the synchronous processing operation.
     * <p><b>200</b> - List of reports returned.
     * <p><b>403</b> - Operation not allowed. Only authenticated user can access it.
     * <p><b>0</b> - default response
     * @return ResponseEntity&lt;List&lt;ProcessingReport&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<ProcessingReport>> getProcessReportWithHttpInfo() throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/processes/reports", Collections.<String, Object>emptyMap());

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

        ParameterizedTypeReference<List<ProcessingReport>> returnType = new ParameterizedTypeReference<List<ProcessingReport>>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Preview process result applied to one or more records
     * Preview result of a process applied to metadata records with an XSL transformation declared in the metadata schema (See the process folder). Parameters sent to the service are forwarded to XSL process. Append mode has 2 limitations. First, it only support a set of records in the same schema. Secondly, it does not propagate URL parameters. This mode is mainly used to create custom reports based on metadata records content.If process name ends with &#39;.csv&#39;, the XSL process output a text document which is returned. When errors occur during processing, the processing report is returned in JSON format.
     * <p><b>403</b> - Operation not allowed. Only Editors can access it.
     * <p><b>200</b> - Processed records.
     * <p><b>0</b> - default response
     * @param process Process identifier (required)
     * @param uuids Record UUIDs. If null current selection is used. (optional, default to new ArrayList&lt;&gt;())
     * @param bucket Selection bucket name (optional)
     * @param appendFirst Append documents before processing (optional, default to false)
     * @return Object
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Object previewProcessRecords(String process, List<String> uuids, String bucket, Boolean appendFirst) throws RestClientException {
        return previewProcessRecordsWithHttpInfo(process, uuids, bucket, appendFirst).getBody();
    }

    /**
     * Preview process result applied to one or more records
     * Preview result of a process applied to metadata records with an XSL transformation declared in the metadata schema (See the process folder). Parameters sent to the service are forwarded to XSL process. Append mode has 2 limitations. First, it only support a set of records in the same schema. Secondly, it does not propagate URL parameters. This mode is mainly used to create custom reports based on metadata records content.If process name ends with &#39;.csv&#39;, the XSL process output a text document which is returned. When errors occur during processing, the processing report is returned in JSON format.
     * <p><b>403</b> - Operation not allowed. Only Editors can access it.
     * <p><b>200</b> - Processed records.
     * <p><b>0</b> - default response
     * @param process Process identifier (required)
     * @param uuids Record UUIDs. If null current selection is used. (optional, default to new ArrayList&lt;&gt;())
     * @param bucket Selection bucket name (optional)
     * @param appendFirst Append documents before processing (optional, default to false)
     * @return ResponseEntity&lt;Object&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Object> previewProcessRecordsWithHttpInfo(String process, List<String> uuids, String bucket, Boolean appendFirst) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'process' is set
        if (process == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'process' when calling previewProcessRecords");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("process", process);
        String path = apiClient.expandPath("/processes/{process}", uriVariables);

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        queryParams.putAll(apiClient.parameterToMultiValueMap(ApiClient.CollectionFormat.valueOf("multi".toUpperCase(Locale.ROOT)), "uuids", uuids));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "bucket", bucket));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "appendFirst", appendFirst));

        final String[] accepts = { 
            "application/json", "*/*"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] { "basicAuth" };

        ParameterizedTypeReference<Object> returnType = new ParameterizedTypeReference<Object>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Apply a process to one or more records
     * Process a metadata with an XSL transformation declared in the metadata schema (See the process folder). Parameters sent to the service are forwarded to XSL process.
     * <p><b>403</b> - Operation not allowed. Only Editors can access it.
     * <p><b>201</b> - Report about processed records.
     * <p><b>0</b> - default response
     * @param process Process identifier (required)
     * @param uuids Record UUIDs. If null current selection is used. (optional, default to new ArrayList&lt;&gt;())
     * @param bucket Selection bucket name (optional)
     * @param updateDateStamp If true updates the DateStamp (or equivalent in standards different to ISO 19139) field in the metadata with the current timestamp (optional, default to true)
     * @param index Index after processing (optional, default to true)
     * @return XsltMetadataProcessingReport
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public XsltMetadataProcessingReport processRecords(String process, List<String> uuids, String bucket, Boolean updateDateStamp, Boolean index) throws RestClientException {
        return processRecordsWithHttpInfo(process, uuids, bucket, updateDateStamp, index).getBody();
    }

    /**
     * Apply a process to one or more records
     * Process a metadata with an XSL transformation declared in the metadata schema (See the process folder). Parameters sent to the service are forwarded to XSL process.
     * <p><b>403</b> - Operation not allowed. Only Editors can access it.
     * <p><b>201</b> - Report about processed records.
     * <p><b>0</b> - default response
     * @param process Process identifier (required)
     * @param uuids Record UUIDs. If null current selection is used. (optional, default to new ArrayList&lt;&gt;())
     * @param bucket Selection bucket name (optional)
     * @param updateDateStamp If true updates the DateStamp (or equivalent in standards different to ISO 19139) field in the metadata with the current timestamp (optional, default to true)
     * @param index Index after processing (optional, default to true)
     * @return ResponseEntity&lt;XsltMetadataProcessingReport&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<XsltMetadataProcessingReport> processRecordsWithHttpInfo(String process, List<String> uuids, String bucket, Boolean updateDateStamp, Boolean index) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'process' is set
        if (process == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'process' when calling processRecords");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("process", process);
        String path = apiClient.expandPath("/processes/{process}", uriVariables);

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        queryParams.putAll(apiClient.parameterToMultiValueMap(ApiClient.CollectionFormat.valueOf("multi".toUpperCase(Locale.ROOT)), "uuids", uuids));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "bucket", bucket));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "updateDateStamp", updateDateStamp));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "index", index));

        final String[] accepts = { 
            "application/json"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] { "basicAuth" };

        ParameterizedTypeReference<XsltMetadataProcessingReport> returnType = new ParameterizedTypeReference<XsltMetadataProcessingReport>() {};
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Search and replace values in one or more records
     * Service to apply replacements to one or more records.  To define a replacement, send the following parameters:  * mdsection-139815551372&#x3D;metadata  * mdfield-1398155513728&#x3D;id.contact.individualName  * replaceValue-1398155513728&#x3D;Juan  * searchValue-1398155513728&#x3D;Jose  TODO: Would be good to provide a simple object to define list of changes instead of group of parameters.&lt;br/&gt;Batch editing can also be used for similar works.
     * <p><b>200</b> - Replacements applied.
     * <p><b>403</b> - Operation not allowed. Only Editors can access it.
     * <p><b>0</b> - default response
     * @param process  (optional, default to &quot;massive-content-update&quot;)
     * @param uuids Record UUIDs. If null current selection is used. (optional, default to new ArrayList&lt;&gt;())
     * @param bucket Selection bucket name (optional)
     * @param isTesting Test only (ie. metadata are not saved). Return the report only. (optional, default to false)
     * @param isCaseInsensitive Case insensitive search. (optional, default to false)
     * @param vacuumMode &#39;record&#39; to apply vacuum.xsl, &#39;element&#39; to remove empty elements. Empty to not affect empty elements. (optional, default to &quot;&quot;)
     * @return MetadataReplacementProcessingReport
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public MetadataReplacementProcessingReport searchAndReplace(String process, List<String> uuids, String bucket, Boolean isTesting, Boolean isCaseInsensitive, String vacuumMode) throws RestClientException {
        return searchAndReplaceWithHttpInfo(process, uuids, bucket, isTesting, isCaseInsensitive, vacuumMode).getBody();
    }

    /**
     * Search and replace values in one or more records
     * Service to apply replacements to one or more records.  To define a replacement, send the following parameters:  * mdsection-139815551372&#x3D;metadata  * mdfield-1398155513728&#x3D;id.contact.individualName  * replaceValue-1398155513728&#x3D;Juan  * searchValue-1398155513728&#x3D;Jose  TODO: Would be good to provide a simple object to define list of changes instead of group of parameters.&lt;br/&gt;Batch editing can also be used for similar works.
     * <p><b>200</b> - Replacements applied.
     * <p><b>403</b> - Operation not allowed. Only Editors can access it.
     * <p><b>0</b> - default response
     * @param process  (optional, default to &quot;massive-content-update&quot;)
     * @param uuids Record UUIDs. If null current selection is used. (optional, default to new ArrayList&lt;&gt;())
     * @param bucket Selection bucket name (optional)
     * @param isTesting Test only (ie. metadata are not saved). Return the report only. (optional, default to false)
     * @param isCaseInsensitive Case insensitive search. (optional, default to false)
     * @param vacuumMode &#39;record&#39; to apply vacuum.xsl, &#39;element&#39; to remove empty elements. Empty to not affect empty elements. (optional, default to &quot;&quot;)
     * @return ResponseEntity&lt;MetadataReplacementProcessingReport&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<MetadataReplacementProcessingReport> searchAndReplaceWithHttpInfo(String process, List<String> uuids, String bucket, Boolean isTesting, Boolean isCaseInsensitive, String vacuumMode) throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/processes/search-and-replace", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "process", process));
        queryParams.putAll(apiClient.parameterToMultiValueMap(ApiClient.CollectionFormat.valueOf("multi".toUpperCase(Locale.ROOT)), "uuids", uuids));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "bucket", bucket));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "isTesting", isTesting));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "isCaseInsensitive", isCaseInsensitive));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "vacuumMode", vacuumMode));

        final String[] accepts = { 
            "application/json"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] { "basicAuth" };

        ParameterizedTypeReference<MetadataReplacementProcessingReport> returnType = new ParameterizedTypeReference<MetadataReplacementProcessingReport>() {};
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
}
