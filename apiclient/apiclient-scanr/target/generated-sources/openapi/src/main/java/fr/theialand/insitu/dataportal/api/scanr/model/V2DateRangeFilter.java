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
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * V2DateRangeFilter
 */
@JsonPropertyOrder({
  V2DateRangeFilter.JSON_PROPERTY_MAX,
  V2DateRangeFilter.JSON_PROPERTY_MIN,
  V2DateRangeFilter.JSON_PROPERTY_MISSING
})
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-04-13T11:24:06.705650+02:00[Europe/Paris]")
public class V2DateRangeFilter {
  public static final String JSON_PROPERTY_MAX = "max";
  private Date max;

  public static final String JSON_PROPERTY_MIN = "min";
  private Date min;

  public static final String JSON_PROPERTY_MISSING = "missing";
  private Boolean missing;


  public V2DateRangeFilter max(Date max) {
    
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

  public Date getMax() {
    return max;
  }


  public void setMax(Date max) {
    this.max = max;
  }


  public V2DateRangeFilter min(Date min) {
    
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

  public Date getMin() {
    return min;
  }


  public void setMin(Date min) {
    this.min = min;
  }


  public V2DateRangeFilter missing(Boolean missing) {
    
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
    V2DateRangeFilter v2DateRangeFilter = (V2DateRangeFilter) o;
    return Objects.equals(this.max, v2DateRangeFilter.max) &&
        Objects.equals(this.min, v2DateRangeFilter.min) &&
        Objects.equals(this.missing, v2DateRangeFilter.missing);
  }

  @Override
  public int hashCode() {
    return Objects.hash(max, min, missing);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class V2DateRangeFilter {\n");
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
