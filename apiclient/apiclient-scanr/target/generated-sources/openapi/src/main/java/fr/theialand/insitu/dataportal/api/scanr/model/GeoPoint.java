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
 * GeoPoint
 */
@JsonPropertyOrder({
  GeoPoint.JSON_PROPERTY_FRAGMENT,
  GeoPoint.JSON_PROPERTY_GEOHASH,
  GeoPoint.JSON_PROPERTY_LAT,
  GeoPoint.JSON_PROPERTY_LON
})
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-04-13T11:24:06.705650+02:00[Europe/Paris]")
public class GeoPoint {
  public static final String JSON_PROPERTY_FRAGMENT = "fragment";
  private Boolean fragment;

  public static final String JSON_PROPERTY_GEOHASH = "geohash";
  private String geohash;

  public static final String JSON_PROPERTY_LAT = "lat";
  private Double lat;

  public static final String JSON_PROPERTY_LON = "lon";
  private Double lon;


  public GeoPoint fragment(Boolean fragment) {
    
    this.fragment = fragment;
    return this;
  }

   /**
   * Get fragment
   * @return fragment
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_FRAGMENT)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Boolean getFragment() {
    return fragment;
  }


  public void setFragment(Boolean fragment) {
    this.fragment = fragment;
  }


  public GeoPoint geohash(String geohash) {
    
    this.geohash = geohash;
    return this;
  }

   /**
   * Get geohash
   * @return geohash
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_GEOHASH)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getGeohash() {
    return geohash;
  }


  public void setGeohash(String geohash) {
    this.geohash = geohash;
  }


  public GeoPoint lat(Double lat) {
    
    this.lat = lat;
    return this;
  }

   /**
   * Get lat
   * @return lat
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_LAT)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Double getLat() {
    return lat;
  }


  public void setLat(Double lat) {
    this.lat = lat;
  }


  public GeoPoint lon(Double lon) {
    
    this.lon = lon;
    return this;
  }

   /**
   * Get lon
   * @return lon
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_LON)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Double getLon() {
    return lon;
  }


  public void setLon(Double lon) {
    this.lon = lon;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GeoPoint geoPoint = (GeoPoint) o;
    return Objects.equals(this.fragment, geoPoint.fragment) &&
        Objects.equals(this.geohash, geoPoint.geohash) &&
        Objects.equals(this.lat, geoPoint.lat) &&
        Objects.equals(this.lon, geoPoint.lon);
  }

  @Override
  public int hashCode() {
    return Objects.hash(fragment, geohash, lat, lon);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GeoPoint {\n");
    sb.append("    fragment: ").append(toIndentedString(fragment)).append("\n");
    sb.append("    geohash: ").append(toIndentedString(geohash)).append("\n");
    sb.append("    lat: ").append(toIndentedString(lat)).append("\n");
    sb.append("    lon: ").append(toIndentedString(lon)).append("\n");
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

