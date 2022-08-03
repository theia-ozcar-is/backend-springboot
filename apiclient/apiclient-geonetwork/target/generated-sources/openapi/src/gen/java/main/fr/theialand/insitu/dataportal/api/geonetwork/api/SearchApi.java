package fr.theialand.insitu.dataportal.api.geonetwork.api;

import fr.theialand.insitu.dataportal.api.geonetwork.handler.ApiClient;

import fr.theialand.insitu.dataportal.api.geonetwork.model.SearchResult;

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
@Component("fr.theialand.insitu.dataportal.api.geonetwork.api.SearchApi")
public class SearchApi {
    private ApiClient apiClient;

    public SearchApi() {
        this(new ApiClient());
    }

    @Autowired
    public SearchApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Search endpoint
     * See https://www.elastic.co/guide/en/elasticsearch/reference/current/query-dsl.html for search parameters details.
     * <p><b>200</b> - OK
     * @param bucket  (optional, default to &quot;metadata&quot;)
     * @param body JSON request based on Elasticsearch API. (optional)
     * @return SearchResult
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public SearchResult search(String bucket, String body) throws RestClientException {
        return searchWithHttpInfo(bucket, body).getBody();
    }

    /**
     * Search endpoint
     * See https://www.elastic.co/guide/en/elasticsearch/reference/current/query-dsl.html for search parameters details.
     * <p><b>200</b> - OK
     * @param bucket  (optional, default to &quot;metadata&quot;)
     * @param body JSON request based on Elasticsearch API. (optional)
     * @return ResponseEntity&lt;SearchResult&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<SearchResult> searchWithHttpInfo(String bucket, String body) throws RestClientException {
        Object postBody = body;
        
        String path = apiClient.expandPath("/search/records/_search", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "bucket", bucket));

        final String[] accepts = { 
            "application/json"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { 
            "application/json"
        };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] { "basicAuth" };

        ParameterizedTypeReference<SearchResult> returnType = new ParameterizedTypeReference<SearchResult>() {};
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
}
