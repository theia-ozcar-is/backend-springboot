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
import fr.theialand.insitu.dataportal.api.geonetwork.model.MultilingualValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * RelatedThumbnailItem
 */
@JsonPropertyOrder({
  RelatedThumbnailItem.JSON_PROPERTY_ID,
  RelatedThumbnailItem.JSON_PROPERTY_URL,
  RelatedThumbnailItem.JSON_PROPERTY_TYPE,
  RelatedThumbnailItem.JSON_PROPERTY_TITLE
})
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-04-13T11:23:44.539800+02:00[Europe/Paris]")
public class RelatedThumbnailItem {
  public static final String JSON_PROPERTY_ID = "id";
  private String id;

  public static final String JSON_PROPERTY_URL = "url";
  private MultilingualValue url;

  public static final String JSON_PROPERTY_TYPE = "type";
  private String type;

  public static final String JSON_PROPERTY_TITLE = "title";
  private MultilingualValue title;


  public RelatedThumbnailItem id(String id) {
    
    this.id = id;
    return this;
  }

   /**
   * Get id
   * @return id
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_ID)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getId() {
    return id;
  }


  public void setId(String id) {
    this.id = id;
  }


  public RelatedThumbnailItem url(MultilingualValue url) {
    
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

  public MultilingualValue getUrl() {
    return url;
  }


  public void setUrl(MultilingualValue url) {
    this.url = url;
  }


  public RelatedThumbnailItem type(String type) {
    
    this.type = type;
    return this;
  }

   /**
   * Get type
   * @return type
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_TYPE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getType() {
    return type;
  }


  public void setType(String type) {
    this.type = type;
  }


  public RelatedThumbnailItem title(MultilingualValue title) {
    
    this.title = title;
    return this;
  }

   /**
   * Get title
   * @return title
  **/
  @ApiModelProperty(required = true, value = "")
  @JsonProperty(JSON_PROPERTY_TITLE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public MultilingualValue getTitle() {
    return title;
  }


  public void setTitle(MultilingualValue title) {
    this.title = title;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RelatedThumbnailItem relatedThumbnailItem = (RelatedThumbnailItem) o;
    return Objects.equals(this.id, relatedThumbnailItem.id) &&
        Objects.equals(this.url, relatedThumbnailItem.url) &&
        Objects.equals(this.type, relatedThumbnailItem.type) &&
        Objects.equals(this.title, relatedThumbnailItem.title);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, url, type, title);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RelatedThumbnailItem {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    url: ").append(toIndentedString(url)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
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

