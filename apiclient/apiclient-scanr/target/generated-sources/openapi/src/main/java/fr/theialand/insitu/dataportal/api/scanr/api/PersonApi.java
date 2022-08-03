package fr.theialand.insitu.dataportal.api.scanr.api;

import fr.theialand.insitu.dataportal.api.scanr.handler.ApiClient;

import fr.theialand.insitu.dataportal.api.scanr.model.LikeResponseOfv2FullPerson;
import fr.theialand.insitu.dataportal.api.scanr.model.SearchResponseOfv2FullPerson;
import fr.theialand.insitu.dataportal.api.scanr.model.Statistique;
import fr.theialand.insitu.dataportal.api.scanr.model.V2FullPerson;
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
@Component("fr.theialand.insitu.dataportal.api.scanr.api.PersonApi")
public class PersonApi {
    private ApiClient apiClient;

    public PersonApi() {
        this(new ApiClient());
    }

    @Autowired
    public PersonApi(ApiClient apiClient) {
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
    public void exportPersonSearch(String request, String requestPath) throws RestClientException {
        exportPersonSearchWithHttpInfo(request, requestPath);
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
    public ResponseEntity<Void> exportPersonSearchWithHttpInfo(String request, String requestPath) throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/v2/persons/search/export", Collections.<String, Object>emptyMap());

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
     * @return SearchResponseOfv2FullPerson
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public SearchResponseOfv2FullPerson geoSearch(V2SearchRequest searchRequest) throws RestClientException {
        return geoSearchWithHttpInfo(searchRequest).getBody();
    }

    /**
     * Return all results for the current FullStructure search (no paging, all results using scroll) in Elasticsearch with geo coordinates, in order to build the maps :  &lt;br/&gt;- of structures, by extracting all (label, address.gps filtered on main one) pairs  &lt;br/&gt;- of projects, by extracting all (projects.project.label, address.gps filtered on main one) pairs  &lt;br/&gt;- of persons, by extracting all (persons.person.fullName, address.gps filtered on main one) pairs.
     * 
     * <p><b>200</b> - OK
     * @param searchRequest searchRequest (required)
     * @return ResponseEntity&lt;SearchResponseOfv2FullPerson&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<SearchResponseOfv2FullPerson> geoSearchWithHttpInfo(V2SearchRequest searchRequest) throws RestClientException {
        Object postBody = searchRequest;
        
        // verify the required parameter 'searchRequest' is set
        if (searchRequest == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'searchRequest' when calling geoSearch");
        }
        
        String path = apiClient.expandPath("/v2/persons/search/georesults", Collections.<String, Object>emptyMap());

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

        ParameterizedTypeReference<SearchResponseOfv2FullPerson> returnType = new ParameterizedTypeReference<SearchResponseOfv2FullPerson>() {};
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Gets by id in Elasticsearch
     * 
     * <p><b>200</b> - OK
     * @param id id (required)
     * @return V2FullPerson
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public V2FullPerson getPerson(String id) throws RestClientException {
        return getPersonWithHttpInfo(id).getBody();
    }

    /**
     * Gets by id in Elasticsearch
     * 
     * <p><b>200</b> - OK
     * @param id id (required)
     * @return ResponseEntity&lt;V2FullPerson&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<V2FullPerson> getPersonWithHttpInfo(String id) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getPerson");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/v2/persons/{id}", uriVariables);

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

        ParameterizedTypeReference<V2FullPerson> returnType = new ParameterizedTypeReference<V2FullPerson>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Finds FullPerson with given fields similar to given texts or fields of FullPerson of given id
     * 
     * <p><b>200</b> - OK
     * @param searchRequest searchRequest (required)
     * @return LikeResponseOfv2FullPerson
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public LikeResponseOfv2FullPerson likePerson(V2LikeRequest searchRequest) throws RestClientException {
        return likePersonWithHttpInfo(searchRequest).getBody();
    }

    /**
     * Finds FullPerson with given fields similar to given texts or fields of FullPerson of given id
     * 
     * <p><b>200</b> - OK
     * @param searchRequest searchRequest (required)
     * @return ResponseEntity&lt;LikeResponseOfv2FullPerson&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<LikeResponseOfv2FullPerson> likePersonWithHttpInfo(V2LikeRequest searchRequest) throws RestClientException {
        Object postBody = searchRequest;
        
        // verify the required parameter 'searchRequest' is set
        if (searchRequest == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'searchRequest' when calling likePerson");
        }
        
        String path = apiClient.expandPath("/v2/persons/like", Collections.<String, Object>emptyMap());

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

        ParameterizedTypeReference<LikeResponseOfv2FullPerson> returnType = new ParameterizedTypeReference<LikeResponseOfv2FullPerson>() {};
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * searchPerson
     * 
     * <p><b>200</b> - OK
     * @param searchRequest searchRequest (required)
     * @return SearchResponseOfv2FullPerson
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public SearchResponseOfv2FullPerson searchPerson(V2SearchRequest searchRequest) throws RestClientException {
        return searchPersonWithHttpInfo(searchRequest).getBody();
    }

    /**
     * searchPerson
     * 
     * <p><b>200</b> - OK
     * @param searchRequest searchRequest (required)
     * @return ResponseEntity&lt;SearchResponseOfv2FullPerson&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<SearchResponseOfv2FullPerson> searchPersonWithHttpInfo(V2SearchRequest searchRequest) throws RestClientException {
        Object postBody = searchRequest;
        
        // verify the required parameter 'searchRequest' is set
        if (searchRequest == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'searchRequest' when calling searchPerson");
        }
        
        String path = apiClient.expandPath("/v2/persons/search", Collections.<String, Object>emptyMap());

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

        ParameterizedTypeReference<SearchResponseOfv2FullPerson> returnType = new ParameterizedTypeReference<SearchResponseOfv2FullPerson>() {};
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Find persons near the given one (default to 20 nearest ones, max 100). Does an Elasticsearch geo_distance query in FullPerson.affiliations.(light)structure.(main)address, see https://www.elastic.co/guide/en/elasticsearch/reference/current/query-dsl-geo-distance-query.htmlIn v2 was rather in MongoDB
     * Distance in km
     * <p><b>200</b> - OK
     * @param id id (required)
     * @param distance distance (required)
     * @param nb nb (optional, default to 20)
     * @return List&lt;V2FullPerson&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<V2FullPerson> searchPersonClosedTo(String id, Double distance, Integer nb) throws RestClientException {
        return searchPersonClosedToWithHttpInfo(id, distance, nb).getBody();
    }

    /**
     * Find persons near the given one (default to 20 nearest ones, max 100). Does an Elasticsearch geo_distance query in FullPerson.affiliations.(light)structure.(main)address, see https://www.elastic.co/guide/en/elasticsearch/reference/current/query-dsl-geo-distance-query.htmlIn v2 was rather in MongoDB
     * Distance in km
     * <p><b>200</b> - OK
     * @param id id (required)
     * @param distance distance (required)
     * @param nb nb (optional, default to 20)
     * @return ResponseEntity&lt;List&lt;V2FullPerson&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<V2FullPerson>> searchPersonClosedToWithHttpInfo(String id, Double distance, Integer nb) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling searchPersonClosedTo");
        }
        
        // verify the required parameter 'distance' is set
        if (distance == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'distance' when calling searchPersonClosedTo");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/v2/persons/persons/near/{id}", uriVariables);

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

        ParameterizedTypeReference<List<V2FullPerson>> returnType = new ParameterizedTypeReference<List<V2FullPerson>>() {};
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
    public Statistique statistique(String startDate, String endDate) throws RestClientException {
        return statistiqueWithHttpInfo(startDate, endDate).getBody();
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
    public ResponseEntity<Statistique> statistiqueWithHttpInfo(String startDate, String endDate) throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/v2/persons/statistics", Collections.<String, Object>emptyMap());

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
