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
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * V2LongRangeFilter
 */
@JsonPropertyOrder({
  V2LongRangeFilter.JSON_PROPERTY_MAX,
  V2LongRangeFilter.JSON_PROPERTY_MIN,
  V2LongRangeFilter.JSON_PROPERTY_MISSING
})
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-04-13T11:24:06.705650+02:00[Europe/Paris]")
public class V2LongRangeFilter {
  public static final String JSON_PROPERTY_MAX = "max";
  private Long max;

  public static final String JSON_PROPERTY_MIN = "min";
  private Long min;

  public static final String JSON_PROPERTY_MISSING = "missing";
  private Boolean missing;


  public V2LongRangeFilter max(Long max) {
    
    this.max = max;
    return this;
  }

   /**
   * Get max
   * @return max
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_MAX)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Long getMax() {
    return max;
  }


  public void setMax(Long max) {
    this.max = max;
  }


  public V2LongRangeFilter min(Long min) {
    
    this.min = min;
    return this;
  }

   /**
   * Get min
   * @return min
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_MIN)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Long getMin() {
    return min;
  }


  public void setMin(Long min) {
    this.min = min;
  }


  public V2LongRangeFilter missing(Boolean missing) {
    
    this.missing = missing;
    return this;
  }

   /**
   * Get missing
   * @return missing
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_MISSING)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Boolean getMissing() {
    return missing;
  }


  public void setMissing(Boolean missing) {
    this.missing = missing;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    V2LongRangeFilter v2LongRangeFilter = (V2LongRangeFilter) o;
    return Objects.equals(this.max, v2LongRangeFilter.max) &&
        Objects.equals(this.min, v2LongRangeFilter.min) &&
        Objects.equals(this.missing, v2LongRangeFilter.missing);
  }

  @Override
  public int hashCode() {
    return Objects.hash(max, min, missing);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class V2LongRangeFilter {\n");
    sb.append("    max: ").append(toIndentedString(max)).append("\n");
    sb.append("    min: ").append(toIndentedString(min)).append("\n");
    sb.append("    missing: ").append(toIndentedString(missing)).append("\n");
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

