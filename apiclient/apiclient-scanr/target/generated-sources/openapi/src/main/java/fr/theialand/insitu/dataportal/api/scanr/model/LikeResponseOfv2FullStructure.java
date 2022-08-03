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
import fr.theialand.insitu.dataportal.api.scanr.model.SearchResultOfv2FullStructure;
import fr.theialand.insitu.dataportal.api.scanr.model.V2LikeRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * LikeResponseOfv2FullStructure
 */
@JsonPropertyOrder({
  LikeResponseOfv2FullStructure.JSON_PROPERTY_FACETS,
  LikeResponseOfv2FullStructure.JSON_PROPERTY_REQUEST,
  LikeResponseOfv2FullStructure.JSON_PROPERTY_RESULTS,
  LikeResponseOfv2FullStructure.JSON_PROPERTY_TOTAL
})
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-04-13T11:24:06.705650+02:00[Europe/Paris]")
public class LikeResponseOfv2FullStructure {
  public static final String JSON_PROPERTY_FACETS = "facets";
  private List<FacetResult> facets = null;

  public static final String JSON_PROPERTY_REQUEST = "request";
  private V2LikeRequest request;

  public static final String JSON_PROPERTY_RESULTS = "results";
  private List<SearchResultOfv2FullStructure> results = null;

  public static final String JSON_PROPERTY_TOTAL = "total";
  private Long total;


  public LikeResponseOfv2FullStructure facets(List<FacetResult> facets) {
    
    this.facets = facets;
    return this;
  }

  public LikeResponseOfv2FullStructure addFacetsItem(FacetResult facetsItem) {
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


  public LikeResponseOfv2FullStructure request(V2LikeRequest request) {
    
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

  public V2LikeRequest getRequest() {
    return request;
  }


  public void setRequest(V2LikeRequest request) {
    this.request = request;
  }


  public LikeResponseOfv2FullStructure results(List<SearchResultOfv2FullStructure> results) {
    
    this.results = results;
    return this;
  }

  public LikeResponseOfv2FullStructure addResultsItem(SearchResultOfv2FullStructure resultsItem) {
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

  public List<SearchResultOfv2FullStructure> getResults() {
    return results;
  }


  public void setResults(List<SearchResultOfv2FullStructure> results) {
    this.results = results;
  }


  public LikeResponseOfv2FullStructure total(Long total) {
    
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
    LikeResponseOfv2FullStructure likeResponseOfv2FullStructure = (LikeResponseOfv2FullStructure) o;
    return Objects.equals(this.facets, likeResponseOfv2FullStructure.facets) &&
        Objects.equals(this.request, likeResponseOfv2FullStructure.request) &&
        Objects.equals(this.results, likeResponseOfv2FullStructure.results) &&
        Objects.equals(this.total, likeResponseOfv2FullStructure.total);
  }

  @Override
  public int hashCode() {
    return Objects.hash(facets, request, results, total);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LikeResponseOfv2FullStructure {\n");
    sb.append("    facets: ").append(toIndentedString(facets)).append("\n");
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

