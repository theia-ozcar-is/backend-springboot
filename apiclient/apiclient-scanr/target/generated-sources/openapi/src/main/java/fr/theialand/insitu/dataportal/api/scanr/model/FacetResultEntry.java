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
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * FacetResultEntry
 */
@JsonPropertyOrder({
  FacetResultEntry.JSON_PROPERTY_COUNT,
  FacetResultEntry.JSON_PROPERTY_SUB_FACETS,
  FacetResultEntry.JSON_PROPERTY_VALUE
})
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-04-13T11:24:06.705650+02:00[Europe/Paris]")
public class FacetResultEntry {
  public static final String JSON_PROPERTY_COUNT = "count";
  private Long count;

  public static final String JSON_PROPERTY_SUB_FACETS = "subFacets";
  private List<FacetResult> subFacets = null;

  public static final String JSON_PROPERTY_VALUE = "value";
  private String value;


  public FacetResultEntry count(Long count) {
    
    this.count = count;
    return this;
  }

   /**
   * Get count
   * @return count
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_COUNT)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Long getCount() {
    return count;
  }


  public void setCount(Long count) {
    this.count = count;
  }


  public FacetResultEntry subFacets(List<FacetResult> subFacets) {
    
    this.subFacets = subFacets;
    return this;
  }

  public FacetResultEntry addSubFacetsItem(FacetResult subFacetsItem) {
    if (this.subFacets == null) {
      this.subFacets = new ArrayList<>();
    }
    this.subFacets.add(subFacetsItem);
    return this;
  }

   /**
   * Get subFacets
   * @return subFacets
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_SUB_FACETS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public List<FacetResult> getSubFacets() {
    return subFacets;
  }


  public void setSubFacets(List<FacetResult> subFacets) {
    this.subFacets = subFacets;
  }


  public FacetResultEntry value(String value) {
    
    this.value = value;
    return this;
  }

   /**
   * Get value
   * @return value
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_VALUE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getValue() {
    return value;
  }


  public void setValue(String value) {
    this.value = value;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FacetResultEntry facetResultEntry = (FacetResultEntry) o;
    return Objects.equals(this.count, facetResultEntry.count) &&
        Objects.equals(this.subFacets, facetResultEntry.subFacets) &&
        Objects.equals(this.value, facetResultEntry.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(count, subFacets, value);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FacetResultEntry {\n");
    sb.append("    count: ").append(toIndentedString(count)).append("\n");
    sb.append("    subFacets: ").append(toIndentedString(subFacets)).append("\n");
    sb.append("    value: ").append(toIndentedString(value)).append("\n");
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

