/*
 * GeoNetwork 4.0.0 OpenAPI Documentation
 * This is the description of the GeoNetwork OpenAPI. Use this API to manage your catalog.
 *
 * The version of the OpenAPI document: 4.0.0
 * Contact: geonetwork-users@lists.sourceforge.net
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package fr.theialand.insitu.dataportal.api.geonetwork.model;

import java.util.Objects;
import java.util.Arrays;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import fr.theialand.insitu.dataportal.api.geonetwork.model.SearchHits;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * SearchResult
 */
@JsonPropertyOrder({
  SearchResult.JSON_PROPERTY_TOOK,
  SearchResult.JSON_PROPERTY_TIMED_OUT,
  SearchResult.JSON_PROPERTY_SHARDS,
  SearchResult.JSON_PROPERTY_HITS,
  SearchResult.JSON_PROPERTY_AGGREGATIONS
})
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-04-13T11:23:44.539800+02:00[Europe/Paris]")
public class SearchResult {
  public static final String JSON_PROPERTY_TOOK = "took";
  private Integer took;

  public static final String JSON_PROPERTY_TIMED_OUT = "timed_out";
  private Boolean timedOut;

  public static final String JSON_PROPERTY_SHARDS = "shards";
  private Object shards;

  public static final String JSON_PROPERTY_HITS = "hits";
  private SearchHits hits;

  public static final String JSON_PROPERTY_AGGREGATIONS = "aggregations";
  private Object aggregations;


  public SearchResult took(Integer took) {
    
    this.took = took;
    return this;
  }

   /**
   * Get took
   * @return took
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_TOOK)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Integer getTook() {
    return took;
  }


  public void setTook(Integer took) {
    this.took = took;
  }


  public SearchResult timedOut(Boolean timedOut) {
    
    this.timedOut = timedOut;
    return this;
  }

   /**
   * Get timedOut
   * @return timedOut
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_TIMED_OUT)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Boolean getTimedOut() {
    return timedOut;
  }


  public void setTimedOut(Boolean timedOut) {
    this.timedOut = timedOut;
  }


  public SearchResult shards(Object shards) {
    
    this.shards = shards;
    return this;
  }

   /**
   * Get shards
   * @return shards
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_SHARDS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Object getShards() {
    return shards;
  }


  public void setShards(Object shards) {
    this.shards = shards;
  }


  public SearchResult hits(SearchHits hits) {
    
    this.hits = hits;
    return this;
  }

   /**
   * Get hits
   * @return hits
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_HITS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public SearchHits getHits() {
    return hits;
  }


  public void setHits(SearchHits hits) {
    this.hits = hits;
  }


  public SearchResult aggregations(Object aggregations) {
    
    this.aggregations = aggregations;
    return this;
  }

   /**
   * Get aggregations
   * @return aggregations
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_AGGREGATIONS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Object getAggregations() {
    return aggregations;
  }


  public void setAggregations(Object aggregations) {
    this.aggregations = aggregations;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SearchResult searchResult = (SearchResult) o;
    return Objects.equals(this.took, searchResult.took) &&
        Objects.equals(this.timedOut, searchResult.timedOut) &&
        Objects.equals(this.shards, searchResult.shards) &&
        Objects.equals(this.hits, searchResult.hits) &&
        Objects.equals(this.aggregations, searchResult.aggregations);
  }

  @Override
  public int hashCode() {
    return Objects.hash(took, timedOut, shards, hits, aggregations);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SearchResult {\n");
    sb.append("    took: ").append(toIndentedString(took)).append("\n");
    sb.append("    timedOut: ").append(toIndentedString(timedOut)).append("\n");
    sb.append("    shards: ").append(toIndentedString(shards)).append("\n");
    sb.append("    hits: ").append(toIndentedString(hits)).append("\n");
    sb.append("    aggregations: ").append(toIndentedString(aggregations)).append("\n");
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

