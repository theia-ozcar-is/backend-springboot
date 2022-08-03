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
import fr.theialand.insitu.dataportal.api.geonetwork.model.Description;
import fr.theialand.insitu.dataportal.api.geonetwork.model.MultilingualValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * RelatedMetadataItem
 */
@JsonPropertyOrder({
  RelatedMetadataItem.JSON_PROPERTY_ID,
  RelatedMetadataItem.JSON_PROPERTY_URL,
  RelatedMetadataItem.JSON_PROPERTY_TYPE,
  RelatedMetadataItem.JSON_PROPERTY_TITLE,
  RelatedMetadataItem.JSON_PROPERTY_DESCRIPTION,
  RelatedMetadataItem.JSON_PROPERTY_MD_TYPE,
  RelatedMetadataItem.JSON_PROPERTY_ORIGIN
})
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-04-13T11:23:44.539800+02:00[Europe/Paris]")
public class RelatedMetadataItem {
  public static final String JSON_PROPERTY_ID = "id";
  private String id;

  public static final String JSON_PROPERTY_URL = "url";
  private MultilingualValue url;

  public static final String JSON_PROPERTY_TYPE = "type";
  private String type;

  public static final String JSON_PROPERTY_TITLE = "title";
  private MultilingualValue title;

  public static final String JSON_PROPERTY_DESCRIPTION = "description";
  private Description description;

  public static final String JSON_PROPERTY_MD_TYPE = "mdType";
  private List<String> mdType = new ArrayList<>();

  public static final String JSON_PROPERTY_ORIGIN = "origin";
  private String origin;


  public RelatedMetadataItem id(String id) {
    
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


  public RelatedMetadataItem url(MultilingualValue url) {
    
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


  public RelatedMetadataItem type(String type) {
    
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


  public RelatedMetadataItem title(MultilingualValue title) {
    
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


  public RelatedMetadataItem description(Description description) {
    
    this.description = description;
    return this;
  }

   /**
   * Get description
   * @return description
  **/
  @ApiModelProperty(required = true, value = "")
  @JsonProperty(JSON_PROPERTY_DESCRIPTION)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public Description getDescription() {
    return description;
  }


  public void setDescription(Description description) {
    this.description = description;
  }


  public RelatedMetadataItem mdType(List<String> mdType) {
    
    this.mdType = mdType;
    return this;
  }

  public RelatedMetadataItem addMdTypeItem(String mdTypeItem) {
    this.mdType.add(mdTypeItem);
    return this;
  }

   /**
   * Get mdType
   * @return mdType
  **/
  @ApiModelProperty(required = true, value = "")
  @JsonProperty(JSON_PROPERTY_MD_TYPE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public List<String> getMdType() {
    return mdType;
  }


  public void setMdType(List<String> mdType) {
    this.mdType = mdType;
  }


  public RelatedMetadataItem origin(String origin) {
    
    this.origin = origin;
    return this;
  }

   /**
   * Get origin
   * @return origin
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_ORIGIN)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getOrigin() {
    return origin;
  }


  public void setOrigin(String origin) {
    this.origin = origin;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RelatedMetadataItem relatedMetadataItem = (RelatedMetadataItem) o;
    return Objects.equals(this.id, relatedMetadataItem.id) &&
        Objects.equals(this.url, relatedMetadataItem.url) &&
        Objects.equals(this.type, relatedMetadataItem.type) &&
        Objects.equals(this.title, relatedMetadataItem.title) &&
        Objects.equals(this.description, relatedMetadataItem.description) &&
        Objects.equals(this.mdType, relatedMetadataItem.mdType) &&
        Objects.equals(this.origin, relatedMetadataItem.origin);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, url, type, title, description, mdType, origin);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RelatedMetadataItem {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    url: ").append(toIndentedString(url)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    mdType: ").append(toIndentedString(mdType)).append("\n");
    sb.append("    origin: ").append(toIndentedString(origin)).append("\n");
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
