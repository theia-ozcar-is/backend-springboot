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
 * V2RssFeed
 */
@JsonPropertyOrder({
  V2RssFeed.JSON_PROPERTY_FREQ,
  V2RssFeed.JSON_PROPERTY_URL
})
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-04-13T11:24:06.705650+02:00[Europe/Paris]")
public class V2RssFeed {
  public static final String JSON_PROPERTY_FREQ = "freq";
  private Float freq;

  public static final String JSON_PROPERTY_URL = "url";
  private String url;


  public V2RssFeed freq(Float freq) {
    
    this.freq = freq;
    return this;
  }

   /**
   * Get freq
   * @return freq
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_FREQ)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Float getFreq() {
    return freq;
  }


  public void setFreq(Float freq) {
    this.freq = freq;
  }


  public V2RssFeed url(String url) {
    
    this.url = url;
    return this;
  }

   /**
   * Get url
   * @return url
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_URL)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getUrl() {
    return url;
  }


  public void setUrl(String url) {
    this.url = url;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    V2RssFeed v2RssFeed = (V2RssFeed) o;
    return Objects.equals(this.freq, v2RssFeed.freq) &&
        Objects.equals(this.url, v2RssFeed.url);
  }

  @Override
  public int hashCode() {
    return Objects.hash(freq, url);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class V2RssFeed {\n");
    sb.append("    freq: ").append(toIndentedString(freq)).append("\n");
    sb.append("    url: ").append(toIndentedString(url)).append("\n");
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
