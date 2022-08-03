package fr.theialand.insitu.dataportal.api.geonetwork.api;

import fr.theialand.insitu.dataportal.api.geonetwork.handler.ApiClient;

import fr.theialand.insitu.dataportal.api.geonetwork.model.Crs;
import fr.theialand.insitu.dataportal.api.geonetwork.model.Element;
import java.io.File;
import fr.theialand.insitu.dataportal.api.geonetwork.model.InlineObject2;
import fr.theialand.insitu.dataportal.api.geonetwork.model.KeywordBean;
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
@Component("fr.theialand.insitu.dataportal.api.geonetwork.api.RegistriesApi")
public class RegistriesApi {
    private ApiClient apiClient;

    public RegistriesApi() {
        this(new ApiClient());
    }

    @Autowired
    public RegistriesApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Delete a thesaurus by name
     * Delete a thesaurus.
     * <p><b>404</b> - Resource not found.
     * <p><b>403</b> - Operation not allowed. Only UserAdmins can access it.
     * <p><b>200</b> - Thesaurus deleted.
     * <p><b>0</b> - default response
     * @param thesaurus Thesaurus to delete. (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void deleteThesaurus(String thesaurus) throws RestClientException {
        deleteThesaurusWithHttpInfo(thesaurus);
    }

    /**
     * Delete a thesaurus by name
     * Delete a thesaurus.
     * <p><b>404</b> - Resource not found.
     * <p><b>403</b> - Operation not allowed. Only UserAdmins can access it.
     * <p><b>200</b> - Thesaurus deleted.
     * <p><b>0</b> - default response
     * @param thesaurus Thesaurus to delete. (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> deleteThesaurusWithHttpInfo(String thesaurus) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'thesaurus' is set
        if (thesaurus == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'thesaurus' when calling deleteThesaurus");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("thesaurus", thesaurus);
        String path = apiClient.expandPath("/registries/vocabularies/{thesaurus}", uriVariables);

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
     * Get CRS
     * 
     * <p><b>404</b> - CRS not found.
     * <p><b>200</b> - CRS details.
     * <p><b>0</b> - default response
     * @param id CRS identifier (required)
     * @return Crs
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Crs getCrs(String id) throws RestClientException {
        return getCrsWithHttpInfo(id).getBody();
    }

    /**
     * Get CRS
     * 
     * <p><b>404</b> - CRS not found.
     * <p><b>200</b> - CRS details.
     * <p><b>0</b> - default response
     * @param id CRS identifier (required)
     * @return ResponseEntity&lt;Crs&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Crs> getCrsWithHttpInfo(String id) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getCrs");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/registries/crs/{id}", uriVariables);

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

        ParameterizedTypeReference<Crs> returnType = new ParameterizedTypeReference<Crs>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Get list of CRS type
     * 
     * <p><b>200</b> - List of CRS types.
     * <p><b>0</b> - default response
     * @return List&lt;String&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<String> getCrsTypes() throws RestClientException {
        return getCrsTypesWithHttpInfo().getBody();
    }

    /**
     * Get list of CRS type
     * 
     * <p><b>200</b> - List of CRS types.
     * <p><b>0</b> - default response
     * @return ResponseEntity&lt;List&lt;String&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<String>> getCrsTypesWithHttpInfo() throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/registries/crs/types", Collections.<String, Object>emptyMap());

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
     * Get a directory entry
     * Directory entry (AKA subtemplates) are XML fragments that can be inserted in metadata records using XLinks. XLinks can be remote or local.
     * <p><b>200</b> - Directory entry.
     * <p><b>403</b> - Operation not allowed. User needs to be able to view the resource.
     * <p><b>0</b> - default response
     * @param uuid Directory entry UUID. (required)
     * @param process Process (optional, default to new ArrayList&lt;&gt;())
     * @param transformation Transformation (optional)
     * @param lang lang (optional, default to new ArrayList&lt;&gt;())
     * @param schema schema (optional, default to &quot;iso19139&quot;)
     * @return Element
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Element getEntry(String uuid, List<String> process, String transformation, List<String> lang, String schema) throws RestClientException {
        return getEntryWithHttpInfo(uuid, process, transformation, lang, schema).getBody();
    }

    /**
     * Get a directory entry
     * Directory entry (AKA subtemplates) are XML fragments that can be inserted in metadata records using XLinks. XLinks can be remote or local.
     * <p><b>200</b> - Directory entry.
     * <p><b>403</b> - Operation not allowed. User needs to be able to view the resource.
     * <p><b>0</b> - default response
     * @param uuid Directory entry UUID. (required)
     * @param process Process (optional, default to new ArrayList&lt;&gt;())
     * @param transformation Transformation (optional)
     * @param lang lang (optional, default to new ArrayList&lt;&gt;())
     * @param schema schema (optional, default to &quot;iso19139&quot;)
     * @return ResponseEntity&lt;Element&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Element> getEntryWithHttpInfo(String uuid, List<String> process, String transformation, List<String> lang, String schema) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'uuid' is set
        if (uuid == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'uuid' when calling getEntry");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("uuid", uuid);
        String path = apiClient.expandPath("/registries/entries/{uuid}", uriVariables);

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        queryParams.putAll(apiClient.parameterToMultiValueMap(ApiClient.CollectionFormat.valueOf("multi".toUpperCase(Locale.ROOT)), "process", process));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "transformation", transformation));
        queryParams.putAll(apiClient.parameterToMultiValueMap(ApiClient.CollectionFormat.valueOf("multi".toUpperCase(Locale.ROOT)), "lang", lang));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "schema", schema));

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
     * Get keyword by id
     * Retrieve XML representation of keyword(s) from same thesaurususing different transformations. &#39;to-iso19139-keyword&#39; is the default and return an ISO19139 snippet.&#39;to-iso19139-keyword-as-xlink&#39; return an XLinked element. Custom transformation can be create on a per schema basis.
     * <p><b>200</b> - XML snippet with requested keywords.
     * <p><b>0</b> - default response
     * @param id Keyword identifier or list of keyword identifiers comma separated. (required)
     * @param thesaurus Thesaurus to look info for the keyword(s). (required)
     * @param lang Languages. (optional, default to new ArrayList&lt;&gt;())
     * @param keywordOnly Only print the keyword, no thesaurus information. (optional, default to false)
     * @param transformation XSL template to use (ISO19139 keyword by default, see convert.xsl). (optional)
     * @param langMap langMap, that converts the values in the &#39;lang&#39; parameter to how they will be actually represented in the record. {&#39;fre&#39;:&#39;fra&#39;} or {&#39;fre&#39;:&#39;fr&#39;}.  Missing/empty means to convert to iso 2 letter. (optional)
     * @return Element
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Element getKeywordById(String id, String thesaurus, List<String> lang, Boolean keywordOnly, String transformation, String langMap) throws RestClientException {
        return getKeywordByIdWithHttpInfo(id, thesaurus, lang, keywordOnly, transformation, langMap).getBody();
    }

    /**
     * Get keyword by id
     * Retrieve XML representation of keyword(s) from same thesaurususing different transformations. &#39;to-iso19139-keyword&#39; is the default and return an ISO19139 snippet.&#39;to-iso19139-keyword-as-xlink&#39; return an XLinked element. Custom transformation can be create on a per schema basis.
     * <p><b>200</b> - XML snippet with requested keywords.
     * <p><b>0</b> - default response
     * @param id Keyword identifier or list of keyword identifiers comma separated. (required)
     * @param thesaurus Thesaurus to look info for the keyword(s). (required)
     * @param lang Languages. (optional, default to new ArrayList&lt;&gt;())
     * @param keywordOnly Only print the keyword, no thesaurus information. (optional, default to false)
     * @param transformation XSL template to use (ISO19139 keyword by default, see convert.xsl). (optional)
     * @param langMap langMap, that converts the values in the &#39;lang&#39; parameter to how they will be actually represented in the record. {&#39;fre&#39;:&#39;fra&#39;} or {&#39;fre&#39;:&#39;fr&#39;}.  Missing/empty means to convert to iso 2 letter. (optional)
     * @return ResponseEntity&lt;Element&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Element> getKeywordByIdWithHttpInfo(String id, String thesaurus, List<String> lang, Boolean keywordOnly, String transformation, String langMap) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getKeywordById");
        }
        
        // verify the required parameter 'thesaurus' is set
        if (thesaurus == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'thesaurus' when calling getKeywordById");
        }
        
        String path = apiClient.expandPath("/registries/vocabularies/keyword", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "id", id));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "thesaurus", thesaurus));
        queryParams.putAll(apiClient.parameterToMultiValueMap(ApiClient.CollectionFormat.valueOf("multi".toUpperCase(Locale.ROOT)), "lang", lang));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "keywordOnly", keywordOnly));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "transformation", transformation));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "langMap", langMap));

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
     * Download a thesaurus by name
     * Download the thesaurus in SKOS format.
     * <p><b>404</b> - Resource not found.
     * <p><b>200</b> - Thesaurus in SKOS format.
     * <p><b>0</b> - default response
     * @param thesaurus Thesaurus to download. (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void getThesaurus(String thesaurus) throws RestClientException {
        getThesaurusWithHttpInfo(thesaurus);
    }

    /**
     * Download a thesaurus by name
     * Download the thesaurus in SKOS format.
     * <p><b>404</b> - Resource not found.
     * <p><b>200</b> - Thesaurus in SKOS format.
     * <p><b>0</b> - default response
     * @param thesaurus Thesaurus to download. (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> getThesaurusWithHttpInfo(String thesaurus) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'thesaurus' is set
        if (thesaurus == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'thesaurus' when calling getThesaurus");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("thesaurus", thesaurus);
        String path = apiClient.expandPath("/registries/vocabularies/{thesaurus}", uriVariables);

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
     * Import spatial directory entries
     * Directory entry (AKA subtemplates) are XML fragments that can be inserted in metadata records. Use this service to import geographic extent entries from an ESRI Shapefile format.
     * <p><b>201</b> - Directory entries imported.
     * <p><b>403</b> - Operation not allowed. Only Reviewvers can access it.
     * <p><b>0</b> - default response
     * @param uuidAttribute Attribute to use for UUID. If none, random UUID are generated. (optional)
     * @param uuidPattern Pattern to build UUID from. Default is &#39;{{uuid}}&#39;. (optional, default to &quot;{{uuid}}&quot;)
     * @param descriptionAttribute Attribute to use for extent description. If none, no extent description defined. TODO: Add per language desc ? (optional)
     * @param geomProjectionTo geomProjectionTo (optional)
     * @param lenient lenient (optional)
     * @param onlyBoundingBox Create only bounding box for each spatial objects. (optional, default to true)
     * @param process Process (optional)
     * @param schema Schema identifier (optional)
     * @param uuidProcessing Record identifier processing. (optional, default to NOTHING)
     * @param group The group the record is attached to. (optional)
     * @param file  (optional)
     * @return SimpleMetadataProcessingReport
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public SimpleMetadataProcessingReport importSpatialEntries(String uuidAttribute, String uuidPattern, String descriptionAttribute, String geomProjectionTo, Boolean lenient, Boolean onlyBoundingBox, String process, String schema, String uuidProcessing, Integer group, File file) throws RestClientException {
        return importSpatialEntriesWithHttpInfo(uuidAttribute, uuidPattern, descriptionAttribute, geomProjectionTo, lenient, onlyBoundingBox, process, schema, uuidProcessing, group, file).getBody();
    }

    /**
     * Import spatial directory entries
     * Directory entry (AKA subtemplates) are XML fragments that can be inserted in metadata records. Use this service to import geographic extent entries from an ESRI Shapefile format.
     * <p><b>201</b> - Directory entries imported.
     * <p><b>403</b> - Operation not allowed. Only Reviewvers can access it.
     * <p><b>0</b> - default response
     * @param uuidAttribute Attribute to use for UUID. If none, random UUID are generated. (optional)
     * @param uuidPattern Pattern to build UUID from. Default is &#39;{{uuid}}&#39;. (optional, default to &quot;{{uuid}}&quot;)
     * @param descriptionAttribute Attribute to use for extent description. If none, no extent description defined. TODO: Add per language desc ? (optional)
     * @param geomProjectionTo geomProjectionTo (optional)
     * @param lenient lenient (optional)
     * @param onlyBoundingBox Create only bounding box for each spatial objects. (optional, default to true)
     * @param process Process (optional)
     * @param schema Schema identifier (optional)
     * @param uuidProcessing Record identifier processing. (optional, default to NOTHING)
     * @param group The group the record is attached to. (optional)
     * @param file  (optional)
     * @return ResponseEntity&lt;SimpleMetadataProcessingReport&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<SimpleMetadataProcessingReport> importSpatialEntriesWithHttpInfo(String uuidAttribute, String uuidPattern, String descriptionAttribute, String geomProjectionTo, Boolean lenient, Boolean onlyBoundingBox, String process, String schema, String uuidProcessing, Integer group, File file) throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/registries/actions/entries/import/spatial", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "uuidAttribute", uuidAttribute));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "uuidPattern", uuidPattern));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "descriptionAttribute", descriptionAttribute));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "geomProjectionTo", geomProjectionTo));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "lenient", lenient));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "onlyBoundingBox", onlyBoundingBox));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "process", process));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "schema", schema));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "uuidProcessing", uuidProcessing));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "group", group));

        if (file != null)
            formParams.add("file", new FileSystemResource(file));

        final String[] accepts = { 
            "application/json"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { 
            "multipart/form-data"
        };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] { "basicAuth" };

        ParameterizedTypeReference<SimpleMetadataProcessingReport> returnType = new ParameterizedTypeReference<SimpleMetadataProcessingReport>() {};
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Preview updated matching entries in records
     * Scan one or more records for element matching the XPath provided and then check if this element is available in the directory. If Found, the element from the directory update the element in the record and optionally properties are preserved.&lt;br/&gt;&lt;br/&gt;The identifier XPath is used to find a match. An optional filtercan be added to restrict search to a subset of the directory. If no identifier XPaths is provided, the UUID is based on the content of the snippet (hash). It is recommended to use an identifier for better matching (eg. ISO19139 contact with different roles will not match on the automatic UUID mode).
     * <p><b>200</b> - OK
     * <p><b>0</b> - default response
     * @param xpath XPath of the elements to extract as entry. (required)
     * @param uuids Record UUIDs. If null current selection is used. (optional, default to new ArrayList&lt;&gt;())
     * @param bucket Selection bucket name (optional)
     * @param identifierXpath XPath of the element identifier. If not defined a random UUID is generated and analysis will not check for duplicates. (optional)
     * @param propertiesToCopy List of XPath of properties to copy from record to matching entry. (optional, default to new ArrayList&lt;&gt;())
     * @param substituteAsXLink Replace entry by XLink. (optional, default to false)
     * @param fq Filter query for directory search. (optional)
     * @return Object
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Object previewUpdatedRecordEntries(String xpath, List<String> uuids, String bucket, String identifierXpath, List<String> propertiesToCopy, Boolean substituteAsXLink, String fq) throws RestClientException {
        return previewUpdatedRecordEntriesWithHttpInfo(xpath, uuids, bucket, identifierXpath, propertiesToCopy, substituteAsXLink, fq).getBody();
    }

    /**
     * Preview updated matching entries in records
     * Scan one or more records for element matching the XPath provided and then check if this element is available in the directory. If Found, the element from the directory update the element in the record and optionally properties are preserved.&lt;br/&gt;&lt;br/&gt;The identifier XPath is used to find a match. An optional filtercan be added to restrict search to a subset of the directory. If no identifier XPaths is provided, the UUID is based on the content of the snippet (hash). It is recommended to use an identifier for better matching (eg. ISO19139 contact with different roles will not match on the automatic UUID mode).
     * <p><b>200</b> - OK
     * <p><b>0</b> - default response
     * @param xpath XPath of the elements to extract as entry. (required)
     * @param uuids Record UUIDs. If null current selection is used. (optional, default to new ArrayList&lt;&gt;())
     * @param bucket Selection bucket name (optional)
     * @param identifierXpath XPath of the element identifier. If not defined a random UUID is generated and analysis will not check for duplicates. (optional)
     * @param propertiesToCopy List of XPath of properties to copy from record to matching entry. (optional, default to new ArrayList&lt;&gt;())
     * @param substituteAsXLink Replace entry by XLink. (optional, default to false)
     * @param fq Filter query for directory search. (optional)
     * @return ResponseEntity&lt;Object&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Object> previewUpdatedRecordEntriesWithHttpInfo(String xpath, List<String> uuids, String bucket, String identifierXpath, List<String> propertiesToCopy, Boolean substituteAsXLink, String fq) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'xpath' is set
        if (xpath == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'xpath' when calling previewUpdatedRecordEntries");
        }
        
        String path = apiClient.expandPath("/registries/actions/entries/synchronize", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        queryParams.putAll(apiClient.parameterToMultiValueMap(ApiClient.CollectionFormat.valueOf("multi".toUpperCase(Locale.ROOT)), "uuids", uuids));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "bucket", bucket));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "xpath", xpath));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "identifierXpath", identifierXpath));
        queryParams.putAll(apiClient.parameterToMultiValueMap(ApiClient.CollectionFormat.valueOf("multi".toUpperCase(Locale.ROOT)), "propertiesToCopy", propertiesToCopy));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "substituteAsXLink", substituteAsXLink));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "fq", fq));

        final String[] accepts = { 
            "application/json", "application/xml"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] { "basicAuth" };

        ParameterizedTypeReference<Object> returnType = new ParameterizedTypeReference<Object>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Search coordinate reference system (CRS)
     * Based on GeoTools EPSG database. If phrase query, each words are searched separately.
     * <p><b>200</b> - List of CRS.
     * <p><b>0</b> - default response
     * @param q Search value (optional, default to &quot;&quot;)
     * @param type Type of CRS (optional)
     * @param rows Number of results. Default is: 100 (optional, default to 100)
     * @return List&lt;Crs&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<Crs> searchCrs(String q, String type, Integer rows) throws RestClientException {
        return searchCrsWithHttpInfo(q, type, rows).getBody();
    }

    /**
     * Search coordinate reference system (CRS)
     * Based on GeoTools EPSG database. If phrase query, each words are searched separately.
     * <p><b>200</b> - List of CRS.
     * <p><b>0</b> - default response
     * @param q Search value (optional, default to &quot;&quot;)
     * @param type Type of CRS (optional)
     * @param rows Number of results. Default is: 100 (optional, default to 100)
     * @return ResponseEntity&lt;List&lt;Crs&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<Crs>> searchCrsWithHttpInfo(String q, String type, Integer rows) throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/registries/crs", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "q", q));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "type", type));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "rows", rows));

        final String[] accepts = { 
            "application/json"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] { "basicAuth" };

        ParameterizedTypeReference<List<Crs>> returnType = new ParameterizedTypeReference<List<Crs>>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Search keywords
     * 
     * <p><b>200</b> - OK
     * <p><b>0</b> - default response
     * @param q Query (optional)
     * @param lang Query in that language (optional, default to &quot;eng&quot;)
     * @param rows Number of rows (optional, default to 1000)
     * @param start Start from (optional, default to 0)
     * @param pLang Return keyword information in one or more languages (optional, default to new ArrayList&lt;&gt;())
     * @param thesaurus Thesaurus identifier (optional, default to new ArrayList&lt;&gt;())
     * @param type Type of search (optional, default to CONTAINS)
     * @param uri URI query (optional)
     * @param sort Sort by (optional, default to &quot;DESC&quot;)
     * @return List&lt;KeywordBean&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<KeywordBean> searchKeywords(String q, String lang, Integer rows, Integer start, List<String> pLang, List<String> thesaurus, String type, String uri, String sort) throws RestClientException {
        return searchKeywordsWithHttpInfo(q, lang, rows, start, pLang, thesaurus, type, uri, sort).getBody();
    }

    /**
     * Search keywords
     * 
     * <p><b>200</b> - OK
     * <p><b>0</b> - default response
     * @param q Query (optional)
     * @param lang Query in that language (optional, default to &quot;eng&quot;)
     * @param rows Number of rows (optional, default to 1000)
     * @param start Start from (optional, default to 0)
     * @param pLang Return keyword information in one or more languages (optional, default to new ArrayList&lt;&gt;())
     * @param thesaurus Thesaurus identifier (optional, default to new ArrayList&lt;&gt;())
     * @param type Type of search (optional, default to CONTAINS)
     * @param uri URI query (optional)
     * @param sort Sort by (optional, default to &quot;DESC&quot;)
     * @return ResponseEntity&lt;List&lt;KeywordBean&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<KeywordBean>> searchKeywordsWithHttpInfo(String q, String lang, Integer rows, Integer start, List<String> pLang, List<String> thesaurus, String type, String uri, String sort) throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/registries/vocabularies/search", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "q", q));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "lang", lang));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "rows", rows));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "start", start));
        queryParams.putAll(apiClient.parameterToMultiValueMap(ApiClient.CollectionFormat.valueOf("multi".toUpperCase(Locale.ROOT)), "pLang", pLang));
        queryParams.putAll(apiClient.parameterToMultiValueMap(ApiClient.CollectionFormat.valueOf("multi".toUpperCase(Locale.ROOT)), "thesaurus", thesaurus));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "type", type));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "uri", uri));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "sort", sort));

        final String[] accepts = { 
            "application/json"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] { "basicAuth" };

        ParameterizedTypeReference<List<KeywordBean>> returnType = new ParameterizedTypeReference<List<KeywordBean>>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Update matching entries in records
     * Scan one or more records for element matching the XPath provided and then check if this element is available in the directory. If Found, the element from the directory update the element in the record and optionally properties are preserved.&lt;br/&gt;&lt;br/&gt;The identifier XPath is used to find a match. An optional filtercan be added to restrict search to a subset of the directory. If no identifier XPaths is provided, the UUID is based on the content of the snippet (hash). It is recommended to use an identifier for better matching (eg. ISO19139 contact with different roles will not match on the automatic UUID mode).
     * <p><b>201</b> - Created
     * <p><b>0</b> - default response
     * @param xpath XPath of the elements to extract as entry. (required)
     * @param uuids Record UUIDs. If null current selection is used. (optional, default to new ArrayList&lt;&gt;())
     * @param bucket Selection bucket name (optional)
     * @param identifierXpath XPath of the element identifier. If not defined a random UUID is generated and analysis will not check for duplicates. (optional)
     * @param propertiesToCopy List of XPath of properties to copy from record to matching entry. (optional, default to new ArrayList&lt;&gt;())
     * @param substituteAsXLink Replace entry by XLink. (optional, default to false)
     * @param fq Filter query for directory search. (optional)
     * @return Object
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Object updateRecordEntries(String xpath, List<String> uuids, String bucket, String identifierXpath, List<String> propertiesToCopy, Boolean substituteAsXLink, String fq) throws RestClientException {
        return updateRecordEntriesWithHttpInfo(xpath, uuids, bucket, identifierXpath, propertiesToCopy, substituteAsXLink, fq).getBody();
    }

    /**
     * Update matching entries in records
     * Scan one or more records for element matching the XPath provided and then check if this element is available in the directory. If Found, the element from the directory update the element in the record and optionally properties are preserved.&lt;br/&gt;&lt;br/&gt;The identifier XPath is used to find a match. An optional filtercan be added to restrict search to a subset of the directory. If no identifier XPaths is provided, the UUID is based on the content of the snippet (hash). It is recommended to use an identifier for better matching (eg. ISO19139 contact with different roles will not match on the automatic UUID mode).
     * <p><b>201</b> - Created
     * <p><b>0</b> - default response
     * @param xpath XPath of the elements to extract as entry. (required)
     * @param uuids Record UUIDs. If null current selection is used. (optional, default to new ArrayList&lt;&gt;())
     * @param bucket Selection bucket name (optional)
     * @param identifierXpath XPath of the element identifier. If not defined a random UUID is generated and analysis will not check for duplicates. (optional)
     * @param propertiesToCopy List of XPath of properties to copy from record to matching entry. (optional, default to new ArrayList&lt;&gt;())
     * @param substituteAsXLink Replace entry by XLink. (optional, default to false)
     * @param fq Filter query for directory search. (optional)
     * @return ResponseEntity&lt;Object&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Object> updateRecordEntriesWithHttpInfo(String xpath, List<String> uuids, String bucket, String identifierXpath, List<String> propertiesToCopy, Boolean substituteAsXLink, String fq) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'xpath' is set
        if (xpath == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'xpath' when calling updateRecordEntries");
        }
        
        String path = apiClient.expandPath("/registries/actions/entries/synchronize", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        queryParams.putAll(apiClient.parameterToMultiValueMap(ApiClient.CollectionFormat.valueOf("multi".toUpperCase(Locale.ROOT)), "uuids", uuids));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "bucket", bucket));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "xpath", xpath));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "identifierXpath", identifierXpath));
        queryParams.putAll(apiClient.parameterToMultiValueMap(ApiClient.CollectionFormat.valueOf("multi".toUpperCase(Locale.ROOT)), "propertiesToCopy", propertiesToCopy));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "substituteAsXLink", substituteAsXLink));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "fq", fq));

        final String[] accepts = { 
            "application/json"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] { "basicAuth" };

        ParameterizedTypeReference<Object> returnType = new ParameterizedTypeReference<Object>() {};
        return apiClient.invokeAPI(path, HttpMethod.PUT, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Uploads a new thesaurus from a file
     * Uploads a new thesaurus.
     * <p><b>201</b> - Thesaurus uploaded in SKOS format.
     * <p><b>403</b> - Operation not allowed. Only Reviewvers can access it.
     * <p><b>0</b> - default response
     * @param type Local or external (default). (optional, default to &quot;external&quot;)
     * @param dir Type of thesaurus, usually one of the ISO thesaurus type codelist value. Default is theme. (optional, default to &quot;theme&quot;)
     * @param stylesheet XSL to be use to convert the thesaurus before load. Default _none_. (optional, default to &quot;_none_&quot;)
     * @param inlineObject2  (optional)
     * @return String
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public String uploadThesaurus(String type, String dir, String stylesheet, InlineObject2 inlineObject2) throws RestClientException {
        return uploadThesaurusWithHttpInfo(type, dir, stylesheet, inlineObject2).getBody();
    }

    /**
     * Uploads a new thesaurus from a file
     * Uploads a new thesaurus.
     * <p><b>201</b> - Thesaurus uploaded in SKOS format.
     * <p><b>403</b> - Operation not allowed. Only Reviewvers can access it.
     * <p><b>0</b> - default response
     * @param type Local or external (default). (optional, default to &quot;external&quot;)
     * @param dir Type of thesaurus, usually one of the ISO thesaurus type codelist value. Default is theme. (optional, default to &quot;theme&quot;)
     * @param stylesheet XSL to be use to convert the thesaurus before load. Default _none_. (optional, default to &quot;_none_&quot;)
     * @param inlineObject2  (optional)
     * @return ResponseEntity&lt;String&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<String> uploadThesaurusWithHttpInfo(String type, String dir, String stylesheet, InlineObject2 inlineObject2) throws RestClientException {
        Object postBody = inlineObject2;
        
        String path = apiClient.expandPath("/registries/vocabularies", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "type", type));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "dir", dir));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "stylesheet", stylesheet));

        final String[] accepts = { 
            "application/json", "text/xml"
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
     * Uploads a new thesaurus from URL or Registry
     * Uploads a new thesaurus.
     * <p><b>201</b> - Thesaurus uploaded in SKOS format.
     * <p><b>403</b> - Operation not allowed. Only Reviewvers can access it.
     * <p><b>0</b> - default response
     * @param url If set, try to download from the Internet. (optional)
     * @param registryUrl If set, try to download from a registry. (optional)
     * @param registryLanguage Languages to download from a registry. (optional, default to new ArrayList&lt;&gt;())
     * @param type Local or external (default). (optional, default to &quot;external&quot;)
     * @param dir Type of thesaurus, usually one of the ISO thesaurus type codelist value. Default is theme. (optional, default to &quot;theme&quot;)
     * @param stylesheet XSL to be use to convert the thesaurus before load. Default _none_. (optional, default to &quot;_none_&quot;)
     * @return String
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public String uploadThesaurusFromUrl(String url, String registryUrl, List<String> registryLanguage, String type, String dir, String stylesheet) throws RestClientException {
        return uploadThesaurusFromUrlWithHttpInfo(url, registryUrl, registryLanguage, type, dir, stylesheet).getBody();
    }

    /**
     * Uploads a new thesaurus from URL or Registry
     * Uploads a new thesaurus.
     * <p><b>201</b> - Thesaurus uploaded in SKOS format.
     * <p><b>403</b> - Operation not allowed. Only Reviewvers can access it.
     * <p><b>0</b> - default response
     * @param url If set, try to download from the Internet. (optional)
     * @param registryUrl If set, try to download from a registry. (optional)
     * @param registryLanguage Languages to download from a registry. (optional, default to new ArrayList&lt;&gt;())
     * @param type Local or external (default). (optional, default to &quot;external&quot;)
     * @param dir Type of thesaurus, usually one of the ISO thesaurus type codelist value. Default is theme. (optional, default to &quot;theme&quot;)
     * @param stylesheet XSL to be use to convert the thesaurus before load. Default _none_. (optional, default to &quot;_none_&quot;)
     * @return ResponseEntity&lt;String&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<String> uploadThesaurusFromUrlWithHttpInfo(String url, String registryUrl, List<String> registryLanguage, String type, String dir, String stylesheet) throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/registries/vocabularies", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "url", url));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "registryUrl", registryUrl));
        queryParams.putAll(apiClient.parameterToMultiValueMap(ApiClient.CollectionFormat.valueOf("multi".toUpperCase(Locale.ROOT)), "registryLanguage", registryLanguage));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "type", type));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "dir", dir));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "stylesheet", stylesheet));

        final String[] accepts = { 
            "application/json", "text/xml"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] { "basicAuth" };

        ParameterizedTypeReference<String> returnType = new ParameterizedTypeReference<String>() {};
        return apiClient.invokeAPI(path, HttpMethod.PUT, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
}
