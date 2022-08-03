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
import fr.theialand.insitu.dataportal.api.scanr.model.V2Structure;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Relation of Structures that are members in a Project.
 */
@ApiModel(description = "Relation of Structures that are members in a Project.")
@JsonPropertyOrder({
  V2ProjectStructureRelation.JSON_PROPERTY_FUNDING,
  V2ProjectStructureRelation.JSON_PROPERTY_LABEL,
  V2ProjectStructureRelation.JSON_PROPERTY_ROLE,
  V2ProjectStructureRelation.JSON_PROPERTY_STRUCTURE,
  V2ProjectStructureRelation.JSON_PROPERTY_URL
})
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-04-13T11:24:06.705650+02:00[Europe/Paris]")
public class V2ProjectStructureRelation {
  public static final String JSON_PROPERTY_FUNDING = "funding";
  private String funding;

  public static final String JSON_PROPERTY_LABEL = "label";
  private Map<String, String> label = null;

  public static final String JSON_PROPERTY_ROLE = "role";
  private String role;

  public static final String JSON_PROPERTY_STRUCTURE = "structure";
  private V2Structure structure;

  public static final String JSON_PROPERTY_URL = "url";
  private String url;


  public V2ProjectStructureRelation funding(String funding) {
    
    this.funding = funding;
    return this;
  }

   /**
   * Funding amount in the project
   * @return funding
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Funding amount in the project")
  @JsonProperty(JSON_PROPERTY_FUNDING)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getFunding() {
    return funding;
  }


  public void setFunding(String funding) {
    this.funding = funding;
  }


  public V2ProjectStructureRelation label(Map<String, String> label) {
    
    this.label = label;
    return this;
  }

  public V2ProjectStructureRelation putLabelItem(String key, String labelItem) {
    if (this.label == null) {
      this.label = new HashMap<>();
    }
    this.label.put(key, labelItem);
    return this;
  }

   /**
   * label of the project structure if this structure is not in scanr
   * @return label
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "label of the project structure if this structure is not in scanr")
  @JsonProperty(JSON_PROPERTY_LABEL)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Map<String, String> getLabel() {
    return label;
  }


  public void setLabel(Map<String, String> label) {
    this.label = label;
  }


  public V2ProjectStructureRelation role(String role) {
    
    this.role = role;
    return this;
  }

   /**
   * Get role
   * @return role
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_ROLE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getRole() {
    return role;
  }


  public void setRole(String role) {
    this.role = role;
  }


  public V2ProjectStructureRelation structure(V2Structure structure) {
    
    this.structure = structure;
    return this;
  }

   /**
   * Get structure
   * @return structure
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_STRUCTURE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public V2Structure getStructure() {
    return structure;
  }


  public void setStructure(V2Structure structure) {
    this.structure = structure;
  }


  public V2ProjectStructureRelation url(String url) {
    
    this.url = url;
    return this;
  }

   /**
   * url of the project structure if this structure is not in scanr
   * @return url
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "url of the project structure if this structure is not in scanr")
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
    V2ProjectStructureRelation v2ProjectStructureRelation = (V2ProjectStructureRelation) o;
    return Objects.equals(this.funding, v2ProjectStructureRelation.funding) &&
        Objects.equals(this.label, v2ProjectStructureRelation.label) &&
        Objects.equals(this.role, v2ProjectStructureRelation.role) &&
        Objects.equals(this.structure, v2ProjectStructureRelation.structure) &&
        Objects.equals(this.url, v2ProjectStructureRelation.url);
  }

  @Override
  public int hashCode() {
    return Objects.hash(funding, label, role, structure, url);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class V2ProjectStructureRelation {\n");
    sb.append("    funding: ").append(toIndentedString(funding)).append("\n");
    sb.append("    label: ").append(toIndentedString(label)).append("\n");
    sb.append("    role: ").append(toIndentedString(role)).append("\n");
    sb.append("    structure: ").append(toIndentedString(structure)).append("\n");
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

