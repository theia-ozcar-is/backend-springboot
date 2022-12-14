/*
 * Api Documentation
 * Api Documentation
 *
 * The version of the OpenAPI document: 1.0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package fr.theialand.insitu.dataportal.api.scanr.model;

import java.util.Objects;
import java.util.Arrays;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import fr.theialand.insitu.dataportal.api.scanr.model.FacetResult;
import fr.theialand.insitu.dataportal.api.scanr.model.SearchResultOfv2FullProject;
import fr.theialand.insitu.dataportal.api.scanr.model.V2SearchRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * SearchResponseOfv2FullProject
 */
@JsonPropertyOrder({
  SearchResponseOfv2FullProject.JSON_PROPERTY_FACETS,
  SearchResponseOfv2FullProject.JSON_PROPERTY_HISTOGRAMS_MAP_VALUE_TYPE_DUMMY,
  SearchResponseOfv2FullProject.JSON_PROPERTY_REQUEST,
  SearchResponseOfv2FullProject.JSON_PROPERTY_RESULTS,
  SearchResponseOfv2FullProject.JSON_PROPERTY_TOTAL
})
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-04-13T11:24:06.705650+02:00[Europe/Paris]")
public class SearchResponseOfv2FullProject {
  public static final String JSON_PROPERTY_FACETS = "facets";
  private List<FacetResult> facets = null;

  public static final String JSON_PROPERTY_HISTOGRAMS_MAP_VALUE_TYPE_DUMMY = "histogramsMapValueTypeDummy";
  private FacetResult histogramsMapValueTypeDummy;

  public static final String JSON_PROPERTY_REQUEST = "request";
  private V2SearchRequest request;

  public static final String JSON_PROPERTY_RESULTS = "results";
  private List<SearchResultOfv2FullProject> results = null;

  public static final String JSON_PROPERTY_TOTAL = "total";
  private Long total;


  public SearchResponseOfv2FullProject facets(List<FacetResult> facets) {
    
    this.facets = facets;
    return this;
  }

  public SearchResponseOfv2FullProject addFacetsItem(FacetResult facetsItem) {
    if (this.facets == null) {
      this.facets = new ArrayList<>();
    }
    this.facets.add(facetsItem);
    return this;
  }

   /**
   * Computed buckets or bins for each named aggregation
   * @return facets
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Computed buckets or bins for each named aggregation")
  @JsonProperty(JSON_PROPERTY_FACETS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public List<FacetResult> getFacets() {
    return facets;
  }


  public void setFacets(List<FacetResult> facets) {
    this.facets = facets;
  }


  public SearchResponseOfv2FullProject histogramsMapValueTypeDummy(FacetResult histogramsMapValueTypeDummy) {
    
    this.histogramsMapValueTypeDummy = histogramsMapValueTypeDummy;
    return this;
  }

   /**
   * Get histogramsMapValueTypeDummy
   * @return histogramsMapValueTypeDummy
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_HISTOGRAMS_MAP_VALUE_TYPE_DUMMY)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public FacetResult getHistogramsMapValueTypeDummy() {
    return histogramsMapValueTypeDummy;
  }


  public void setHistogramsMapValueTypeDummy(FacetResult histogramsMapValueTypeDummy) {
    this.histogramsMapValueTypeDummy = histogramsMapValueTypeDummy;
  }


  public SearchResponseOfv2FullProject request(V2SearchRequest request) {
    
    this.request = request;
    return this;
  }

   /**
   * Get request
   * @return request
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_REQUEST)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public V2SearchRequest getRequest() {
    return request;
  }


  public void setRequest(V2SearchRequest request) {
    this.request = request;
  }


  public SearchResponseOfv2FullProject results(List<SearchResultOfv2FullProject> results) {
    
    this.results = results;
    return this;
  }

  public SearchResponseOfv2FullProject addResultsItem(SearchResultOfv2FullProject resultsItem) {
    if (this.results == null) {
      this.results = new ArrayList<>();
    }
    this.results.add(resultsItem);
    return this;
  }

   /**
   * Found objects
   * @return results
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Found objects")
  @JsonProperty(JSON_PROPERTY_RESULTS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public List<SearchResultOfv2FullProject> getResults() {
    return results;
  }


  public void setResults(List<SearchResultOfv2FullProject> results) {
    this.results = results;
  }


  public SearchResponseOfv2FullProject total(Long total) {
    
    this.total = total;
    return this;
  }

   /**
   * Get total
   * @return total
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_TOTAL)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Long getTotal() {
    return total;
  }


  public void setTotal(Long total) {
    this.total = total;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SearchResponseOfv2FullProject searchResponseOfv2FullProject = (SearchResponseOfv2FullProject) o;
    return Objects.equals(this.facets, searchResponseOfv2FullProject.facets) &&
        Objects.equals(this.histogramsMapValueTypeDummy, searchResponseOfv2FullProject.histogramsMapValueTypeDummy) &&
        Objects.equals(this.request, searchResponseOfv2FullProject.request) &&
        Objects.equals(this.results, searchResponseOfv2FullProject.results) &&
        Objects.equals(this.total, searchResponseOfv2FullProject.total);
  }

  @Override
  public int hashCode() {
    return Objects.hash(facets, histogramsMapValueTypeDummy, request, results, total);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SearchResponseOfv2FullProject {\n");
    sb.append("    facets: ").append(toIndentedString(facets)).append("\n");
    sb.append("    histogramsMapValueTypeDummy: ").append(toIndentedString(histogramsMapValueTypeDummy)).append("\n");
    sb.append("    request: ").append(toIndentedString(request)).append("\n");
    sb.append("    results: ").append(toIndentedString(results)).append("\n");
    sb.append("    total: ").append(toIndentedString(total)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}

