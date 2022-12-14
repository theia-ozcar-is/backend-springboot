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
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * MetadataIdentifierTemplate
 */
@JsonPropertyOrder({
  MetadataIdentifierTemplate.JSON_PROPERTY_NAME,
  MetadataIdentifierTemplate.JSON_PROPERTY_ID,
  MetadataIdentifierTemplate.JSON_PROPERTY_TEMPLATE,
  MetadataIdentifierTemplate.JSON_PROPERTY_SYSTEM_PROVIDED_J_P_A_WORKAROUND,
  MetadataIdentifierTemplate.JSON_PROPERTY_SYSTEM_PROVIDED,
  MetadataIdentifierTemplate.JSON_PROPERTY_SYSTEM_DEFAULT
})
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-04-13T11:23:44.539800+02:00[Europe/Paris]")
public class MetadataIdentifierTemplate {
  public static final String JSON_PROPERTY_NAME = "name";
  private String name;

  public static final String JSON_PROPERTY_ID = "id";
  private Integer id;

  public static final String JSON_PROPERTY_TEMPLATE = "template";
  private String template;

  public static final String JSON_PROPERTY_SYSTEM_PROVIDED_J_P_A_WORKAROUND = "systemProvided_JPAWorkaround";
  private String systemProvidedJPAWorkaround;

  public static final String JSON_PROPERTY_SYSTEM_PROVIDED = "systemProvided";
  private Boolean systemProvided;

  public static final String JSON_PROPERTY_SYSTEM_DEFAULT = "systemDefault";
  private Boolean systemDefault;


  public MetadataIdentifierTemplate name(String name) {
    
    this.name = name;
    return this;
  }

   /**
   * Get name
   * @return name
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_NAME)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getName() {
    return name;
  }


  public void setName(String name) {
    this.name = name;
  }


  public MetadataIdentifierTemplate id(Integer id) {
    
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

  public Integer getId() {
    return id;
  }


  public void setId(Integer id) {
    this.id = id;
  }


  public MetadataIdentifierTemplate template(String template) {
    
    this.template = template;
    return this;
  }

   /**
   * Get template
   * @return template
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_TEMPLATE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getTemplate() {
    return template;
  }


  public void setTemplate(String template) {
    this.template = template;
  }


  public MetadataIdentifierTemplate systemProvidedJPAWorkaround(String systemProvidedJPAWorkaround) {
    
    this.systemProvidedJPAWorkaround = systemProvidedJPAWorkaround;
    return this;
  }

   /**
   * Get systemProvidedJPAWorkaround
   * @return systemProvidedJPAWorkaround
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_SYSTEM_PROVIDED_J_P_A_WORKAROUND)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getSystemProvidedJPAWorkaround() {
    return systemProvidedJPAWorkaround;
  }


  public void setSystemProvidedJPAWorkaround(String systemProvidedJPAWorkaround) {
    this.systemProvidedJPAWorkaround = systemProvidedJPAWorkaround;
  }


  public MetadataIdentifierTemplate systemProvided(Boolean systemProvided) {
    
    this.systemProvided = systemProvided;
    return this;
  }

   /**
   * Get systemProvided
   * @return systemProvided
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_SYSTEM_PROVIDED)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Boolean getSystemProvided() {
    return systemProvided;
  }


  public void setSystemProvided(Boolean systemProvided) {
    this.systemProvided = systemProvided;
  }


  public MetadataIdentifierTemplate systemDefault(Boolean systemDefault) {
    
    this.systemDefault = systemDefault;
    return this;
  }

   /**
   * Get systemDefault
   * @return systemDefault
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_SYSTEM_DEFAULT)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Boolean getSystemDefault() {
    return systemDefault;
  }


  public void setSystemDefault(Boolean systemDefault) {
    this.systemDefault = systemDefault;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MetadataIdentifierTemplate metadataIdentifierTemplate = (MetadataIdentifierTemplate) o;
    return Objects.equals(this.name, metadataIdentifierTemplate.name) &&
        Objects.equals(this.id, metadataIdentifierTemplate.id) &&
        Objects.equals(this.template, metadataIdentifierTemplate.template) &&
        Objects.equals(this.systemProvidedJPAWorkaround, metadataIdentifierTemplate.systemProvidedJPAWorkaround) &&
        Objects.equals(this.systemProvided, metadataIdentifierTemplate.systemProvided) &&
        Objects.equals(this.systemDefault, metadataIdentifierTemplate.systemDefault);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, id, template, systemProvidedJPAWorkaround, systemProvided, systemDefault);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MetadataIdentifierTemplate {\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    template: ").append(toIndentedString(template)).append("\n");
    sb.append("    systemProvidedJPAWorkaround: ").append(toIndentedString(systemProvidedJPAWorkaround)).append("\n");
    sb.append("    systemProvided: ").append(toIndentedString(systemProvided)).append("\n");
    sb.append("    systemDefault: ").append(toIndentedString(systemDefault)).append("\n");
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

