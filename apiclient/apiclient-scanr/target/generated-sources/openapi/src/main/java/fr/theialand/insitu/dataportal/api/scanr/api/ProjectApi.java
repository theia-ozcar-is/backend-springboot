package fr.theialand.insitu.dataportal.api.scanr.api;

import fr.theialand.insitu.dataportal.api.scanr.handler.ApiClient;

import fr.theialand.insitu.dataportal.api.scanr.model.LikeResponseOfv2FullProject;
import fr.theialand.insitu.dataportal.api.scanr.model.SearchResponseOfv2FullProject;
import fr.theialand.insitu.dataportal.api.scanr.model.Statistique;
import fr.theialand.insitu.dataportal.api.scanr.model.V2FullProject;
import fr.theialand.insitu.dataportal.api.scanr.model.V2LikeRequest;
import fr.theialand.insitu.dataportal.api.scanr.model.V2SearchRequest;

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

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-04-13T11:24:06.705650+02:00[Europe/Paris]")
@Component("fr.theialand.insitu.dataportal.api.scanr.api.ProjectApi")
public class ProjectApi {
    private ApiClient apiClient;

    public ProjectApi() {
        this(new ApiClient());
    }

    @Autowired
    public ProjectApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Excel export of the search (produces content type application/vnd.ms-excel).
     * 
     * <p><b>200</b> - OK
     * @param request request (optional)
     * @param requestPath requestPath (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void exportProjectSearch(String request, String requestPath) throws RestClientException {
        exportProjectSearchWithHttpInfo(request, requestPath);
    }

    /**
     * Excel export of the search (produces content type application/vnd.ms-excel).
     * 
     * <p><b>200</b> - OK
     * @param request request (optional)
     * @param requestPath requestPath (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> exportProjectSearchWithHttpInfo(String request, String requestPath) throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/v2/projects/search/export", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "request", request));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "requestPath", requestPath));

        final String[] accepts = { };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] {  };

        ParameterizedTypeReference<Void> returnType = new ParameterizedTypeReference<Void>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Return all results for the current FullStructure search (no paging, all results using scroll) in Elasticsearch with geo coordinates, in order to build the maps :  &lt;br/&gt;- of structures, by extracting all (label, address.gps filtered on main one) pairs  &lt;br/&gt;- of projects, by extracting all (projects.project.label, address.gps filtered on main one) pairs  &lt;br/&gt;- of persons, by extracting all (persons.person.fullName, address.gps filtered on main one) pairs.
     * 
     * <p><b>200</b> - OK
     * @param searchRequest searchRequest (required)
     * @return SearchResponseOfv2FullProject
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public SearchResponseOfv2FullProject geoSearch1(V2SearchRequest searchRequest) throws RestClientException {
        return geoSearch1WithHttpInfo(searchRequest).getBody();
    }

    /**
     * Return all results for the current FullStructure search (no paging, all results using scroll) in Elasticsearch with geo coordinates, in order to build the maps :  &lt;br/&gt;- of structures, by extracting all (label, address.gps filtered on main one) pairs  &lt;br/&gt;- of projects, by extracting all (projects.project.label, address.gps filtered on main one) pairs  &lt;br/&gt;- of persons, by extracting all (persons.person.fullName, address.gps filtered on main one) pairs.
     * 
     * <p><b>200</b> - OK
     * @param searchRequest searchRequest (required)
     * @return ResponseEntity&lt;SearchResponseOfv2FullProject&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<SearchResponseOfv2FullProject> geoSearch1WithHttpInfo(V2SearchRequest searchRequest) throws RestClientException {
        Object postBody = searchRequest;
        
        // verify the required parameter 'searchRequest' is set
        if (searchRequest == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'searchRequest' when calling geoSearch1");
        }
        
        String path = apiClient.expandPath("/v2/projects/search/georesults", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        final String[] accepts = { 
            "application/json;charset=UTF-8"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { 
            "application/json"
        };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] {  };

        ParameterizedTypeReference<SearchResponseOfv2FullProject> returnType = new ParameterizedTypeReference<SearchResponseOfv2FullProject>() {};
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Gets by id in Elasticsearch
     * 
     * <p><b>200</b> - OK
     * @param id id (required)
     * @return V2FullProject
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public V2FullProject getProject(String id) throws RestClientException {
        return getProjectWithHttpInfo(id).getBody();
    }

    /**
     * Gets by id in Elasticsearch
     * 
     * <p><b>200</b> - OK
     * @param id id (required)
     * @return ResponseEntity&lt;V2FullProject&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<V2FullProject> getProjectWithHttpInfo(String id) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getProject");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/v2/projects/{id}", uriVariables);

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        final String[] accepts = { 
            "application/json;charset=UTF-8"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] {  };

        ParameterizedTypeReference<V2FullProject> returnType = new ParameterizedTypeReference<V2FullProject>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Finds FullProject with given fields similar to given texts or fields of FullProject of given id
     * 
     * <p><b>200</b> - OK
     * @param searchRequest searchRequest (required)
     * @return LikeResponseOfv2FullProject
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public LikeResponseOfv2FullProject likeProject(V2LikeRequest searchRequest) throws RestClientException {
        return likeProjectWithHttpInfo(searchRequest).getBody();
    }

    /**
     * Finds FullProject with given fields similar to given texts or fields of FullProject of given id
     * 
     * <p><b>200</b> - OK
     * @param searchRequest searchRequest (required)
     * @return ResponseEntity&lt;LikeResponseOfv2FullProject&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<LikeResponseOfv2FullProject> likeProjectWithHttpInfo(V2LikeRequest searchRequest) throws RestClientException {
        Object postBody = searchRequest;
        
        // verify the required parameter 'searchRequest' is set
        if (searchRequest == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'searchRequest' when calling likeProject");
        }
        
        String path = apiClient.expandPath("/v2/projects/like", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        final String[] accepts = { 
            "application/json;charset=UTF-8"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { 
            "application/json"
        };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] {  };

        ParameterizedTypeReference<LikeResponseOfv2FullProject> returnType = new ParameterizedTypeReference<LikeResponseOfv2FullProject>() {};
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * searchProject
     * 
     * <p><b>200</b> - OK
     * @param searchRequest searchRequest (required)
     * @return SearchResponseOfv2FullProject
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public SearchResponseOfv2FullProject searchProject(V2SearchRequest searchRequest) throws RestClientException {
        return searchProjectWithHttpInfo(searchRequest).getBody();
    }

    /**
     * searchProject
     * 
     * <p><b>200</b> - OK
     * @param searchRequest searchRequest (required)
     * @return ResponseEntity&lt;SearchResponseOfv2FullProject&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<SearchResponseOfv2FullProject> searchProjectWithHttpInfo(V2SearchRequest searchRequest) throws RestClientException {
        Object postBody = searchRequest;
        
        // verify the required parameter 'searchRequest' is set
        if (searchRequest == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'searchRequest' when calling searchProject");
        }
        
        String path = apiClient.expandPath("/v2/projects/search", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        final String[] accepts = { 
            "application/json;charset=UTF-8"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { 
            "application/json"
        };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] {  };

        ParameterizedTypeReference<SearchResponseOfv2FullProject> returnType = new ParameterizedTypeReference<SearchResponseOfv2FullProject>() {};
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Find projects near the given one (default to 20 nearest ones, max 100). Does an Elasticsearch geo_distance query in FullProject.participants.(light)structure.(main)address, see https://www.elastic.co/guide/en/elasticsearch/reference/current/query-dsl-geo-distance-query.htmlIn v2 was rather in MongoDB
     * Distance in km
     * <p><b>200</b> - OK
     * @param id id (required)
     * @param distance distance (required)
     * @param nb nb (optional, default to 20)
     * @return List&lt;V2FullProject&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<V2FullProject> searchProjectClosedTo(String id, Double distance, Integer nb) throws RestClientException {
        return searchProjectClosedToWithHttpInfo(id, distance, nb).getBody();
    }

    /**
     * Find projects near the given one (default to 20 nearest ones, max 100). Does an Elasticsearch geo_distance query in FullProject.participants.(light)structure.(main)address, see https://www.elastic.co/guide/en/elasticsearch/reference/current/query-dsl-geo-distance-query.htmlIn v2 was rather in MongoDB
     * Distance in km
     * <p><b>200</b> - OK
     * @param id id (required)
     * @param distance distance (required)
     * @param nb nb (optional, default to 20)
     * @return ResponseEntity&lt;List&lt;V2FullProject&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<V2FullProject>> searchProjectClosedToWithHttpInfo(String id, Double distance, Integer nb) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling searchProjectClosedTo");
        }
        
        // verify the required parameter 'distance' is set
        if (distance == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'distance' when calling searchProjectClosedTo");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/v2/projects/near/{id}", uriVariables);

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "distance", distance));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "nb", nb));

        final String[] accepts = { 
            "application/json;charset=UTF-8"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] {  };

        ParameterizedTypeReference<List<V2FullProject>> returnType = new ParameterizedTypeReference<List<V2FullProject>>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * statistique
     * 
     * <p><b>200</b> - OK
     * @param startDate startDate (optional)
     * @param endDate endDate (optional)
     * @return Statistique
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Statistique statistique1(String startDate, String endDate) throws RestClientException {
        return statistique1WithHttpInfo(startDate, endDate).getBody();
    }

    /**
     * statistique
     * 
     * <p><b>200</b> - OK
     * @param startDate startDate (optional)
     * @param endDate endDate (optional)
     * @return ResponseEntity&lt;Statistique&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Statistique> statistique1WithHttpInfo(String startDate, String endDate) throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/v2/projects/statistics", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "startDate", startDate));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "endDate", endDate));

        final String[] accepts = { 
            "application/json;charset=UTF-8"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] {  };

        ParameterizedTypeReference<Statistique> returnType = new ParameterizedTypeReference<Statistique>() {};
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
}
