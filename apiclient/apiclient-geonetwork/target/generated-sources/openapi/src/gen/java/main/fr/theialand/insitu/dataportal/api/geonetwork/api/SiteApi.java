package fr.theialand.insitu.dataportal.api.geonetwork.api;

import fr.theialand.insitu.dataportal.api.geonetwork.handler.ApiClient;

import fr.theialand.insitu.dataportal.api.geonetwork.model.Element;
import fr.theialand.insitu.dataportal.api.geonetwork.model.LogFileResponse;
import fr.theialand.insitu.dataportal.api.geonetwork.model.Setting;
import fr.theialand.insitu.dataportal.api.geonetwork.model.SettingsListResponse;
import fr.theialand.insitu.dataportal.api.geonetwork.model.SiteInformation;
import fr.theialand.insitu.dataportal.api.geonetwork.model.Status;
import fr.theialand.insitu.dataportal.api.geonetwork.model.SystemInfo;

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
@Component("fr.theialand.insitu.dataportal.api.geonetwork.api.SiteApi")
public class SiteApi {
    private ApiClient apiClient;

    public SiteApi() {
        this(new ApiClient());
    }

    @Autowired
    public SiteApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Get site informations
     * 
     * <p><b>200</b> - Site information.
     * <p><b>0</b> - default response
     * @return SiteInformation
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public SiteInformation getInformation() throws RestClientException {
        return getInformationWithHttpInfo().getBody();
    }

    /**
     * Get site informations
     * 
     * <p><b>200</b> - Site information.
     * <p><b>0</b> - default response
     * @return ResponseEntity&lt;SiteInformation&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<SiteInformation> getInformationWithHttpInfo() throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/site/info", Collections.<String, Object>emptyMap());

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

        ParameterizedTypeReference<SiteInformation> returnType = new ParameterizedTypeReference<SiteInformation>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Get last activity
     * 
     * <p><b>200</b> - OK
     * <p><b>0</b> - default response
     * @param lines Number of lines to return (optional, default to 2000)
     * @return String
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public String getLastActivity(Integer lines) throws RestClientException {
        return getLastActivityWithHttpInfo(lines).getBody();
    }

    /**
     * Get last activity
     * 
     * <p><b>200</b> - OK
     * <p><b>0</b> - default response
     * @param lines Number of lines to return (optional, default to 2000)
     * @return ResponseEntity&lt;String&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<String> getLastActivityWithHttpInfo(Integer lines) throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/site/logging/activity", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "lines", lines));

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
     * Get last activity in a ZIP
     * 
     * <p><b>200</b> - OK
     * <p><b>0</b> - default response
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void getLastActivityInAZip() throws RestClientException {
        getLastActivityInAZipWithHttpInfo();
    }

    /**
     * Get last activity in a ZIP
     * 
     * <p><b>200</b> - OK
     * <p><b>0</b> - default response
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> getLastActivityInAZipWithHttpInfo() throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/site/logging/activity/zip", Collections.<String, Object>emptyMap());

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
     * Get log files
     * 
     * <p><b>200</b> - OK
     * <p><b>0</b> - default response
     * @return List&lt;LogFileResponse&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<LogFileResponse> getLogFiles() throws RestClientException {
        return getLogFilesWithHttpInfo().getBody();
    }

    /**
     * Get log files
     * 
     * <p><b>200</b> - OK
     * <p><b>0</b> - default response
     * @return ResponseEntity&lt;List&lt;LogFileResponse&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<LogFileResponse>> getLogFilesWithHttpInfo() throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/site/logging", Collections.<String, Object>emptyMap());

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

        ParameterizedTypeReference<List<LogFileResponse>> returnType = new ParameterizedTypeReference<List<LogFileResponse>>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * robots.txt
     * 
     * <p><b>200</b> - robots.txt file for SEO.
     * <p><b>0</b> - default response
     * @return String
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public String getRobotsText() throws RestClientException {
        return getRobotsTextWithHttpInfo().getBody();
    }

    /**
     * robots.txt
     * 
     * <p><b>200</b> - robots.txt file for SEO.
     * <p><b>0</b> - default response
     * @return ResponseEntity&lt;String&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<String> getRobotsTextWithHttpInfo() throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/robots.txt", Collections.<String, Object>emptyMap());

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
     * Get settings with details
     * Provides also setting properties.
     * <p><b>200</b> - Settings with details.
     * <p><b>0</b> - default response
     * @param set Setting set. A common set of settings to retrieve. (optional, default to new ArrayList&lt;&gt;())
     * @param key Setting key (optional, default to new ArrayList&lt;&gt;())
     * @return List&lt;Setting&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<Setting> getSettingsDetails(List<String> set, List<String> key) throws RestClientException {
        return getSettingsDetailsWithHttpInfo(set, key).getBody();
    }

    /**
     * Get settings with details
     * Provides also setting properties.
     * <p><b>200</b> - Settings with details.
     * <p><b>0</b> - default response
     * @param set Setting set. A common set of settings to retrieve. (optional, default to new ArrayList&lt;&gt;())
     * @param key Setting key (optional, default to new ArrayList&lt;&gt;())
     * @return ResponseEntity&lt;List&lt;Setting&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<Setting>> getSettingsDetailsWithHttpInfo(List<String> set, List<String> key) throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/site/settings/details", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        queryParams.putAll(apiClient.parameterToMultiValueMap(ApiClient.CollectionFormat.valueOf("multi".toUpperCase(Locale.ROOT)), "set", set));
        queryParams.putAll(apiClient.parameterToMultiValueMap(ApiClient.CollectionFormat.valueOf("multi".toUpperCase(Locale.ROOT)), "key", key));

        final String[] accepts = { 
            "application/json"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] { "basicAuth" };

        ParameterizedTypeReference<List<Setting>> returnType = new ParameterizedTypeReference<List<Setting>>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Get settings
     * Return public settings for anonymous users, internals are allowed for authenticated.
     * <p><b>200</b> - Settings.
     * <p><b>0</b> - default response
     * @param set Setting set. A common set of settings to retrieve. (optional, default to new ArrayList&lt;&gt;())
     * @param key Setting key (optional, default to new ArrayList&lt;&gt;())
     * @return SettingsListResponse
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public SettingsListResponse getSettingsSet(List<String> set, List<String> key) throws RestClientException {
        return getSettingsSetWithHttpInfo(set, key).getBody();
    }

    /**
     * Get settings
     * Return public settings for anonymous users, internals are allowed for authenticated.
     * <p><b>200</b> - Settings.
     * <p><b>0</b> - default response
     * @param set Setting set. A common set of settings to retrieve. (optional, default to new ArrayList&lt;&gt;())
     * @param key Setting key (optional, default to new ArrayList&lt;&gt;())
     * @return ResponseEntity&lt;SettingsListResponse&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<SettingsListResponse> getSettingsSetWithHttpInfo(List<String> set, List<String> key) throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/site/settings", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        queryParams.putAll(apiClient.parameterToMultiValueMap(ApiClient.CollectionFormat.valueOf("multi".toUpperCase(Locale.ROOT)), "set", set));
        queryParams.putAll(apiClient.parameterToMultiValueMap(ApiClient.CollectionFormat.valueOf("multi".toUpperCase(Locale.ROOT)), "key", key));

        final String[] accepts = { 
            "application/json"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] { "basicAuth" };

        ParameterizedTypeReference<SettingsListResponse> returnType = new ParameterizedTypeReference<SettingsListResponse>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Get site (or portal) description
     * 
     * <p><b>200</b> - Site description.
     * <p><b>0</b> - default response
     * @return SettingsListResponse
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public SettingsListResponse getSiteOrPortalDescription() throws RestClientException {
        return getSiteOrPortalDescriptionWithHttpInfo().getBody();
    }

    /**
     * Get site (or portal) description
     * 
     * <p><b>200</b> - Site description.
     * <p><b>0</b> - default response
     * @return ResponseEntity&lt;SettingsListResponse&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<SettingsListResponse> getSiteOrPortalDescriptionWithHttpInfo() throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/site", Collections.<String, Object>emptyMap());

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

        ParameterizedTypeReference<SettingsListResponse> returnType = new ParameterizedTypeReference<SettingsListResponse>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Get sitemap
     * 
     * <p><b>200</b> - Site map.
     * <p><b>0</b> - default response
     * @param format Format (xml or html). (optional, default to &quot;html&quot;)
     * @param doc page. (optional, default to 0)
     * @return Element
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Element getSitemap(String format, Integer doc) throws RestClientException {
        return getSitemapWithHttpInfo(format, doc).getBody();
    }

    /**
     * Get sitemap
     * 
     * <p><b>200</b> - Site map.
     * <p><b>0</b> - default response
     * @param format Format (xml or html). (optional, default to &quot;html&quot;)
     * @param doc page. (optional, default to 0)
     * @return ResponseEntity&lt;Element&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Element> getSitemapWithHttpInfo(String format, Integer doc) throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/sitemap", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "format", format));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "doc", doc));

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
     * Get build details
     * To know when and how this version of the application was built.
     * <p><b>200</b> - Build info.
     * <p><b>0</b> - default response
     * @return SystemInfo
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public SystemInfo getSystemInfo() throws RestClientException {
        return getSystemInfoWithHttpInfo().getBody();
    }

    /**
     * Get build details
     * To know when and how this version of the application was built.
     * <p><b>200</b> - Build info.
     * <p><b>0</b> - default response
     * @return ResponseEntity&lt;SystemInfo&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<SystemInfo> getSystemInfoWithHttpInfo() throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/site/info/build", Collections.<String, Object>emptyMap());

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

        ParameterizedTypeReference<SystemInfo> returnType = new ParameterizedTypeReference<SystemInfo>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Get XSL tranformations available
     * XSL transformations may be applied while importing or harvesting records.
     * <p><b>200</b> - XSLT available.
     * <p><b>0</b> - default response
     * @return List&lt;String&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<String> getXslTransformations() throws RestClientException {
        return getXslTransformationsWithHttpInfo().getBody();
    }

    /**
     * Get XSL tranformations available
     * XSL transformations may be applied while importing or harvesting records.
     * <p><b>200</b> - XSLT available.
     * <p><b>0</b> - default response
     * @return ResponseEntity&lt;List&lt;String&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<String>> getXslTransformationsWithHttpInfo() throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/site/info/transforms", Collections.<String, Object>emptyMap());

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
    /**
     * Index
     * 
     * <p><b>200</b> - OK
     * <p><b>0</b> - default response
     * @param reset Drop and recreate index (optional, default to true)
     * @param asynchronous Asynchronous mode (only on all records. ie. no selection bucket) (optional, default to false)
     * @param havingXlinkOnly Records having only XLinks (optional, default to false)
     * @param indices Index. By default only remove record index. (optional, default to new ArrayList&lt;&gt;())
     * @param bucket Selection bucket name (optional)
     * @return String
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public String index(Boolean reset, Boolean asynchronous, Boolean havingXlinkOnly, List<String> indices, String bucket) throws RestClientException {
        return indexWithHttpInfo(reset, asynchronous, havingXlinkOnly, indices, bucket).getBody();
    }

    /**
     * Index
     * 
     * <p><b>200</b> - OK
     * <p><b>0</b> - default response
     * @param reset Drop and recreate index (optional, default to true)
     * @param asynchronous Asynchronous mode (only on all records. ie. no selection bucket) (optional, default to false)
     * @param havingXlinkOnly Records having only XLinks (optional, default to false)
     * @param indices Index. By default only remove record index. (optional, default to new ArrayList&lt;&gt;())
     * @param bucket Selection bucket name (optional)
     * @return ResponseEntity&lt;String&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<String> indexWithHttpInfo(Boolean reset, Boolean asynchronous, Boolean havingXlinkOnly, List<String> indices, String bucket) throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/site/index", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "reset", reset));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "asynchronous", asynchronous));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "havingXlinkOnly", havingXlinkOnly));
        queryParams.putAll(apiClient.parameterToMultiValueMap(ApiClient.CollectionFormat.valueOf("multi".toUpperCase(Locale.ROOT)), "indices", indices));
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
    /**
     * Index synchronized with database
     * 
     * <p><b>200</b> - OK
     * <p><b>0</b> - default response
     * @return Map&lt;String, Object&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Map<String, Object> indexAndDbSynchronizationStatus() throws RestClientException {
        return indexAndDbSynchronizationStatusWithHttpInfo().getBody();
    }

    /**
     * Index synchronized with database
     * 
     * <p><b>200</b> - OK
     * <p><b>0</b> - default response
     * @return ResponseEntity&lt;Map&lt;String, Object&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Map<String, Object>> indexAndDbSynchronizationStatusWithHttpInfo() throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/site/index/synchronized", Collections.<String, Object>emptyMap());

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

        ParameterizedTypeReference<Map<String, Object>> returnType = new ParameterizedTypeReference<Map<String, Object>>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Index status
     * 
     * <p><b>200</b> - OK
     * <p><b>0</b> - default response
     * @return Status
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Status indexStatus() throws RestClientException {
        return indexStatusWithHttpInfo().getBody();
    }

    /**
     * Index status
     * 
     * <p><b>200</b> - OK
     * <p><b>0</b> - default response
     * @return ResponseEntity&lt;Status&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Status> indexStatusWithHttpInfo() throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/site/index/status", Collections.<String, Object>emptyMap());

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

        ParameterizedTypeReference<Status> returnType = new ParameterizedTypeReference<Status>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Is CAS enabled?
     * 
     * <p><b>200</b> - OK
     * <p><b>0</b> - default response
     * @return Boolean
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Boolean isCasEnabled() throws RestClientException {
        return isCasEnabledWithHttpInfo().getBody();
    }

    /**
     * Is CAS enabled?
     * 
     * <p><b>200</b> - OK
     * <p><b>0</b> - default response
     * @return ResponseEntity&lt;Boolean&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Boolean> isCasEnabledWithHttpInfo() throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/site/info/isCasEnabled", Collections.<String, Object>emptyMap());

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

        ParameterizedTypeReference<Boolean> returnType = new ParameterizedTypeReference<Boolean>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Is indexing?
     * 
     * <p><b>200</b> - OK
     * <p><b>0</b> - default response
     * @return Boolean
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Boolean isIndexing() throws RestClientException {
        return isIndexingWithHttpInfo().getBody();
    }

    /**
     * Is indexing?
     * 
     * <p><b>200</b> - OK
     * <p><b>0</b> - default response
     * @return ResponseEntity&lt;Boolean&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Boolean> isIndexingWithHttpInfo() throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/site/indexing", Collections.<String, Object>emptyMap());

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

        ParameterizedTypeReference<Boolean> returnType = new ParameterizedTypeReference<Boolean>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Is in read-only mode?
     * 
     * <p><b>200</b> - OK
     * <p><b>0</b> - default response
     * @return Boolean
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Boolean isReadOnly() throws RestClientException {
        return isReadOnlyWithHttpInfo().getBody();
    }

    /**
     * Is in read-only mode?
     * 
     * <p><b>200</b> - OK
     * <p><b>0</b> - default response
     * @return ResponseEntity&lt;Boolean&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Boolean> isReadOnlyWithHttpInfo() throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/site/info/readonly", Collections.<String, Object>emptyMap());

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

        ParameterizedTypeReference<Boolean> returnType = new ParameterizedTypeReference<Boolean>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
}
