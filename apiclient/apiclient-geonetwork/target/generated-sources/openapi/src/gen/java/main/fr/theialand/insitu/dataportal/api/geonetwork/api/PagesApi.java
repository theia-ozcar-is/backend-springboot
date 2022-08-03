package fr.theialand.insitu.dataportal.api.geonetwork.api;

import fr.theialand.insitu.dataportal.api.geonetwork.handler.ApiClient;

import fr.theialand.insitu.dataportal.api.geonetwork.model.PageJSONWrapper;

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
@Component("fr.theialand.insitu.dataportal.api.geonetwork.api.PagesApi")
public class PagesApi {
    private ApiClient apiClient;

    public PagesApi() {
        this(new ApiClient());
    }

    @Autowired
    public PagesApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Return the page object details except the content
     * &lt;a href&#x3D;&#39;http://geonetwork-opensource.org/manuals/trunk/eng/users/user-guide/define-static-pages/define-pages.html&#39;&gt;More info&lt;/a&gt;
     * <p><b>404</b> - Page not found
     * <p><b>200</b> - Page found
     * <p><b>403</b> - Operation not allowed. User needs to be able to view the resource.
     * <p><b>0</b> - default response
     * @param language  (required)
     * @param pageId  (required)
     * @return PageJSONWrapper
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public PageJSONWrapper getPage(String language, String pageId) throws RestClientException {
        return getPageWithHttpInfo(language, pageId).getBody();
    }

    /**
     * Return the page object details except the content
     * &lt;a href&#x3D;&#39;http://geonetwork-opensource.org/manuals/trunk/eng/users/user-guide/define-static-pages/define-pages.html&#39;&gt;More info&lt;/a&gt;
     * <p><b>404</b> - Page not found
     * <p><b>200</b> - Page found
     * <p><b>403</b> - Operation not allowed. User needs to be able to view the resource.
     * <p><b>0</b> - default response
     * @param language  (required)
     * @param pageId  (required)
     * @return ResponseEntity&lt;PageJSONWrapper&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<PageJSONWrapper> getPageWithHttpInfo(String language, String pageId) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'language' is set
        if (language == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'language' when calling getPage");
        }
        
        // verify the required parameter 'pageId' is set
        if (pageId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'pageId' when calling getPage");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("language", language);
        uriVariables.put("pageId", pageId);
        String path = apiClient.expandPath("/pages/{language}/{pageId}", uriVariables);

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

        ParameterizedTypeReference<PageJSONWrapper> returnType = new ParameterizedTypeReference<PageJSONWrapper>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Return the static html content identified by pageId
     * &lt;a href&#x3D;&#39;http://geonetwork-opensource.org/manuals/trunk/eng/users/user-guide/define-static-pages/define-pages.html&#39;&gt;More info&lt;/a&gt;
     * <p><b>404</b> - Page not found
     * <p><b>200</b> - Page found
     * <p><b>403</b> - Operation not allowed. User needs to be able to view the resource.
     * <p><b>0</b> - default response
     * @param language  (required)
     * @param pageId  (required)
     * @return String
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public String getPageContent(String language, String pageId) throws RestClientException {
        return getPageContentWithHttpInfo(language, pageId).getBody();
    }

    /**
     * Return the static html content identified by pageId
     * &lt;a href&#x3D;&#39;http://geonetwork-opensource.org/manuals/trunk/eng/users/user-guide/define-static-pages/define-pages.html&#39;&gt;More info&lt;/a&gt;
     * <p><b>404</b> - Page not found
     * <p><b>200</b> - Page found
     * <p><b>403</b> - Operation not allowed. User needs to be able to view the resource.
     * <p><b>0</b> - default response
     * @param language  (required)
     * @param pageId  (required)
     * @return ResponseEntity&lt;String&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<String> getPageContentWithHttpInfo(String language, String pageId) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'language' is set
        if (language == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'language' when calling getPageContent");
        }
        
        // verify the required parameter 'pageId' is set
        if (pageId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'pageId' when calling getPageContent");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("language", language);
        uriVariables.put("pageId", pageId);
        String path = apiClient.expandPath("/pages/{language}/{pageId}/content", uriVariables);

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        final String[] accepts = { 
            "application/json", "text/plain;charset=UTF-8"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] { "basicAuth" };

        ParameterizedTypeReference<String> returnType = new ParameterizedTypeReference<String>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * List all pages according to the filters
     * &lt;a href&#x3D;&#39;http://geonetwork-opensource.org/manuals/trunk/eng/users/user-guide/define-static-pages/define-pages.html&#39;&gt;More info&lt;/a&gt;
     * <p><b>403</b> - Operation not allowed. User needs to be able to view the resource.
     * <p><b>0</b> - default response
     * @param language  (optional)
     * @param section  (optional)
     * @param format  (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void listPages(String language, String section, String format) throws RestClientException {
        listPagesWithHttpInfo(language, section, format);
    }

    /**
     * List all pages according to the filters
     * &lt;a href&#x3D;&#39;http://geonetwork-opensource.org/manuals/trunk/eng/users/user-guide/define-static-pages/define-pages.html&#39;&gt;More info&lt;/a&gt;
     * <p><b>403</b> - Operation not allowed. User needs to be able to view the resource.
     * <p><b>0</b> - default response
     * @param language  (optional)
     * @param section  (optional)
     * @param format  (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> listPagesWithHttpInfo(String language, String section, String format) throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/pages/list", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "language", language));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "section", section));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "format", format));

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
}
