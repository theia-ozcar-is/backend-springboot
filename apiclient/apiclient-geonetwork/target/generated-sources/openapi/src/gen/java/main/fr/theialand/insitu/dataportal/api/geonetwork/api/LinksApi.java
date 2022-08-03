package fr.theialand.insitu.dataportal.api.geonetwork.api;

import fr.theialand.insitu.dataportal.api.geonetwork.handler.ApiClient;

import fr.theialand.insitu.dataportal.api.geonetwork.model.PageLink;
import fr.theialand.insitu.dataportal.api.geonetwork.model.SimpleMetadataProcessingReport;

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
@Component("fr.theialand.insitu.dataportal.api.geonetwork.api.LinksApi")
public class LinksApi {
    private ApiClient apiClient;

    public LinksApi() {
        this(new ApiClient());
    }

    @Autowired
    public LinksApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Analyze records links
     * One of uuids or bucket parameter is required if not an Administrator. Only records that you can edit will be validated.
     * <p><b>201</b> - Created
     * <p><b>0</b> - default response
     * @param uuids Record UUIDs. If null current selection is used. (optional, default to new ArrayList&lt;&gt;())
     * @param bucket Selection bucket name (optional)
     * @param removeFirst Only allowed if Administrator. (optional, default to true)
     * @param analyze  (optional, default to false)
     * @return SimpleMetadataProcessingReport
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public SimpleMetadataProcessingReport analyzeRecordLinks(List<String> uuids, String bucket, Boolean removeFirst, Boolean analyze) throws RestClientException {
        return analyzeRecordLinksWithHttpInfo(uuids, bucket, removeFirst, analyze).getBody();
    }

    /**
     * Analyze records links
     * One of uuids or bucket parameter is required if not an Administrator. Only records that you can edit will be validated.
     * <p><b>201</b> - Created
     * <p><b>0</b> - default response
     * @param uuids Record UUIDs. If null current selection is used. (optional, default to new ArrayList&lt;&gt;())
     * @param bucket Selection bucket name (optional)
     * @param removeFirst Only allowed if Administrator. (optional, default to true)
     * @param analyze  (optional, default to false)
     * @return ResponseEntity&lt;SimpleMetadataProcessingReport&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<SimpleMetadataProcessingReport> analyzeRecordLinksWithHttpInfo(List<String> uuids, String bucket, Boolean removeFirst, Boolean analyze) throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/records/links", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        queryParams.putAll(apiClient.parameterToMultiValueMap(ApiClient.CollectionFormat.valueOf("multi".toUpperCase(Locale.ROOT)), "uuids", uuids));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "bucket", bucket));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "removeFirst", removeFirst));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "analyze", analyze));

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
     * Get record links
     * 
     * <p><b>200</b> - OK
     * <p><b>0</b> - default response
     * @param filter Filter, e.g. \&quot;{url: &#39;png&#39;, lastState: &#39;ko&#39;, records: &#39;e421&#39;, groupId: 12}\&quot;, lastState being &#39;ok&#39;/&#39;ko&#39;/&#39;unknown&#39; (optional)
     * @param groupIdFilter Optional, filter links to records published in that group. (optional, default to new ArrayList&lt;&gt;())
     * @param groupOwnerIdFilter Optional, filter links to records created in that group. (optional, default to new ArrayList&lt;&gt;())
     * @return PageLink
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public PageLink getRecordLinks(Object filter, List<Integer> groupIdFilter, List<Integer> groupOwnerIdFilter) throws RestClientException {
        return getRecordLinksWithHttpInfo(filter, groupIdFilter, groupOwnerIdFilter).getBody();
    }

    /**
     * Get record links
     * 
     * <p><b>200</b> - OK
     * <p><b>0</b> - default response
     * @param filter Filter, e.g. \&quot;{url: &#39;png&#39;, lastState: &#39;ko&#39;, records: &#39;e421&#39;, groupId: 12}\&quot;, lastState being &#39;ok&#39;/&#39;ko&#39;/&#39;unknown&#39; (optional)
     * @param groupIdFilter Optional, filter links to records published in that group. (optional, default to new ArrayList&lt;&gt;())
     * @param groupOwnerIdFilter Optional, filter links to records created in that group. (optional, default to new ArrayList&lt;&gt;())
     * @return ResponseEntity&lt;PageLink&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<PageLink> getRecordLinksWithHttpInfo(Object filter, List<Integer> groupIdFilter, List<Integer> groupOwnerIdFilter) throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/records/links", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "filter", filter));
        queryParams.putAll(apiClient.parameterToMultiValueMap(ApiClient.CollectionFormat.valueOf("multi".toUpperCase(Locale.ROOT)), "groupIdFilter", groupIdFilter));
        queryParams.putAll(apiClient.parameterToMultiValueMap(ApiClient.CollectionFormat.valueOf("multi".toUpperCase(Locale.ROOT)), "groupOwnerIdFilter", groupOwnerIdFilter));

        final String[] accepts = { 
            "application/json"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] { "basicAuth" };

        ParameterizedTypeReference<PageLink> returnType = new ParameterizedTypeReference<PageLink>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * 
     * Get record links as CSV
     * <p><b>200</b> - OK
     * <p><b>0</b> - default response
     * @param filter Filter, e.g. \&quot;{url: &#39;png&#39;, lastState: &#39;ko&#39;, records: &#39;e421&#39;, groupId: 12}\&quot;, lastState being &#39;ok&#39;/&#39;ko&#39;/&#39;unknown&#39; (optional)
     * @param groupIdFilter Optional, filter links to records published in that group. (optional, default to new ArrayList&lt;&gt;())
     * @param groupOwnerIdFilter Optional, filter links to records created in that group. (optional, default to new ArrayList&lt;&gt;())
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void getRecordLinksAsCsv(Object filter, List<Integer> groupIdFilter, List<Integer> groupOwnerIdFilter) throws RestClientException {
        getRecordLinksAsCsvWithHttpInfo(filter, groupIdFilter, groupOwnerIdFilter);
    }

    /**
     * 
     * Get record links as CSV
     * <p><b>200</b> - OK
     * <p><b>0</b> - default response
     * @param filter Filter, e.g. \&quot;{url: &#39;png&#39;, lastState: &#39;ko&#39;, records: &#39;e421&#39;, groupId: 12}\&quot;, lastState being &#39;ok&#39;/&#39;ko&#39;/&#39;unknown&#39; (optional)
     * @param groupIdFilter Optional, filter links to records published in that group. (optional, default to new ArrayList&lt;&gt;())
     * @param groupOwnerIdFilter Optional, filter links to records created in that group. (optional, default to new ArrayList&lt;&gt;())
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> getRecordLinksAsCsvWithHttpInfo(Object filter, List<Integer> groupIdFilter, List<Integer> groupOwnerIdFilter) throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/records/links/csv", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "filter", filter));
        queryParams.putAll(apiClient.parameterToMultiValueMap(ApiClient.CollectionFormat.valueOf("multi".toUpperCase(Locale.ROOT)), "groupIdFilter", groupIdFilter));
        queryParams.putAll(apiClient.parameterToMultiValueMap(ApiClient.CollectionFormat.valueOf("multi".toUpperCase(Locale.ROOT)), "groupOwnerIdFilter", groupOwnerIdFilter));

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
     * Remove all links and status history
     * 
     * <p><b>200</b> - OK
     * <p><b>0</b> - default response
     * @return String
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public String purgeAll() throws RestClientException {
        return purgeAllWithHttpInfo().getBody();
    }

    /**
     * Remove all links and status history
     * 
     * <p><b>200</b> - OK
     * <p><b>0</b> - default response
     * @return ResponseEntity&lt;String&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<String> purgeAllWithHttpInfo() throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/records/links", Collections.<String, Object>emptyMap());

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
}
