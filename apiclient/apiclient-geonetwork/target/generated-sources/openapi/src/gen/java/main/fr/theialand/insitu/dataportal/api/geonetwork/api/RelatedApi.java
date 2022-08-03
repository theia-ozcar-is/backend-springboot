package fr.theialand.insitu.dataportal.api.geonetwork.api;

import fr.theialand.insitu.dataportal.api.geonetwork.handler.ApiClient;

import fr.theialand.insitu.dataportal.api.geonetwork.model.RelatedResponse;

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
@Component("fr.theialand.insitu.dataportal.api.geonetwork.api.RelatedApi")
public class RelatedApi {
    private ApiClient apiClient;

    public RelatedApi() {
        this(new ApiClient());
    }

    @Autowired
    public RelatedApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Get record related resources for all requested metadatas
     * Retrieve related services, datasets, onlines, thumbnails, sources, ... to all requested records.&lt;br/&gt;&lt;a href&#x3D;&#39;http://geonetwork-opensource.org/manuals/trunk/eng/users/user-guide/associating-resources/index.html&#39;&gt;More info&lt;/a&gt;
     * <p><b>200</b> - Return the associated resources.
     * <p><b>403</b> - Operation not allowed. User needs to be able to view the resource.
     * <p><b>0</b> - default response
     * @param type Type of related resource. If none, all resources are returned. (optional, default to new ArrayList&lt;&gt;())
     * @param uuid Uuids of the metadatas you request the relations from. (optional, default to new ArrayList&lt;&gt;())
     * @return Map&lt;String, RelatedResponse&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Map<String, RelatedResponse> getAssociatedResourcesForRecords(List<String> type, List<String> uuid) throws RestClientException {
        return getAssociatedResourcesForRecordsWithHttpInfo(type, uuid).getBody();
    }

    /**
     * Get record related resources for all requested metadatas
     * Retrieve related services, datasets, onlines, thumbnails, sources, ... to all requested records.&lt;br/&gt;&lt;a href&#x3D;&#39;http://geonetwork-opensource.org/manuals/trunk/eng/users/user-guide/associating-resources/index.html&#39;&gt;More info&lt;/a&gt;
     * <p><b>200</b> - Return the associated resources.
     * <p><b>403</b> - Operation not allowed. User needs to be able to view the resource.
     * <p><b>0</b> - default response
     * @param type Type of related resource. If none, all resources are returned. (optional, default to new ArrayList&lt;&gt;())
     * @param uuid Uuids of the metadatas you request the relations from. (optional, default to new ArrayList&lt;&gt;())
     * @return ResponseEntity&lt;Map&lt;String, RelatedResponse&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Map<String, RelatedResponse>> getAssociatedResourcesForRecordsWithHttpInfo(List<String> type, List<String> uuid) throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/related", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        queryParams.putAll(apiClient.parameterToMultiValueMap(ApiClient.CollectionFormat.valueOf("multi".toUpperCase(Locale.ROOT)), "type", type));
        queryParams.putAll(apiClient.parameterToMultiValueMap(ApiClient.CollectionFormat.valueOf("multi".toUpperCase(Locale.ROOT)), "uuid", uuid));

        final String[] accepts = { 
            "application/json", "application/xml", 
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] { "basicAuth" };

        ParameterizedTypeReference<Map<String, RelatedResponse>> returnType = new ParameterizedTypeReference<Map<String, RelatedResponse>>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
}
