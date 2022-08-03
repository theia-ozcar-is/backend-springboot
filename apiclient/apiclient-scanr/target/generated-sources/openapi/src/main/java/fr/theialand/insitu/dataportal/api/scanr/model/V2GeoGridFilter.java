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
import fr.theialand.insitu.dataportal.api.scanr.model.GeoPoint;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Allows an Elasticsearch geo_bounding_box query, see https://www.elastic.co/guide/en/elasticsearch/reference/current/query-dsl-geo-bounding-box-query.html
 */
@ApiModel(description = "Allows an Elasticsearch geo_bounding_box query, see https://www.elastic.co/guide/en/elasticsearch/reference/current/query-dsl-geo-bounding-box-query.html")
@JsonPropertyOrder({
  V2GeoGridFilter.JSON_PROPERTY_BOTTOM_RIGHT,
  V2GeoGridFilter.JSON_PROPERTY_TOP_LEFT
})
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-04-13T11:24:06.705650+02:00[Europe/Paris]")
public class V2GeoGridFilter {
  public static final String JSON_PROPERTY_BOTTOM_RIGHT = "bottomRight";
  private GeoPoint bottomRight;

  public static final String JSON_PROPERTY_TOP_LEFT = "topLeft";
  private GeoPoint topLeft;


  public V2GeoGridFilter bottomRight(GeoPoint bottomRight) {
    
    this.bottomRight = bottomRight;
    return this;
  }

   /**
   * Get bottomRight
   * @return bottomRight
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_BOTTOM_RIGHT)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public GeoPoint getBottomRight() {
    return bottomRight;
  }


  public void setBottomRight(GeoPoint bottomRight) {
    this.bottomRight = bottomRight;
  }


  public V2GeoGridFilter topLeft(GeoPoint topLeft) {
    
    this.topLeft = topLeft;
    return this;
  }

   /**
   * Get topLeft
   * @return topLeft
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_TOP_LEFT)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public GeoPoint getTopLeft() {
    return topLeft;
  }


  public void setTopLeft(GeoPoint topLeft) {
    this.topLeft = topLeft;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    V2GeoGridFilter v2GeoGridFilter = (V2GeoGridFilter) o;
    return Objects.equals(this.bottomRight, v2GeoGridFilter.bottomRight) &&
        Objects.equals(this.topLeft, v2GeoGridFilter.topLeft);
  }

  @Override
  public int hashCode() {
    return Objects.hash(bottomRight, topLeft);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class V2GeoGridFilter {\n");
    sb.append("    bottomRight: ").append(toIndentedString(bottomRight)).append("\n");
    sb.append("    topLeft: ").append(toIndentedString(topLeft)).append("\n");
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

