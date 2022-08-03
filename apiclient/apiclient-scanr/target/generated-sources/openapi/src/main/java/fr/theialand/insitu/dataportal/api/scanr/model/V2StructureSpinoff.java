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
import fr.theialand.insitu.dataportal.api.scanr.model.V2Project;
import fr.theialand.insitu.dataportal.api.scanr.model.V2Structure;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Spinoff of an RNSR structure
 */
@ApiModel(description = "Spinoff of an RNSR structure")
@JsonPropertyOrder({
  V2StructureSpinoff.JSON_PROPERTY_LABEL,
  V2StructureSpinoff.JSON_PROPERTY_PROJECT,
  V2StructureSpinoff.JSON_PROPERTY_STRUCTURE,
  V2StructureSpinoff.JSON_PROPERTY_TYPE,
  V2StructureSpinoff.JSON_PROPERTY_YEAR_CLOSING
})
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-04-13T11:24:06.705650+02:00[Europe/Paris]")
public class V2StructureSpinoff {
  public static final String JSON_PROPERTY_LABEL = "label";
  private String label;

  public static final String JSON_PROPERTY_PROJECT = "project";
  private V2Project project;

  public static final String JSON_PROPERTY_STRUCTURE = "structure";
  private V2Structure structure;

  public static final String JSON_PROPERTY_TYPE = "type";
  private String type;

  public static final String JSON_PROPERTY_YEAR_CLOSING = "yearClosing";
  private Integer yearClosing;


  public V2StructureSpinoff label(String label) {
    
    this.label = label;
    return this;
  }

   /**
   * label of the company spinoff
   * @return label
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "label of the company spinoff")
  @JsonProperty(JSON_PROPERTY_LABEL)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getLabel() {
    return label;
  }


  public void setLabel(String label) {
    this.label = label;
  }


  public V2StructureSpinoff project(V2Project project) {
    
    this.project = project;
    return this;
  }

   /**
   * Get project
   * @return project
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_PROJECT)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public V2Project getProject() {
    return project;
  }


  public void setProject(V2Project project) {
    this.project = project;
  }


  public V2StructureSpinoff structure(V2Structure structure) {
    
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


  public V2StructureSpinoff type(String type) {
    
    this.type = type;
    return this;
  }

   /**
   * Type of the spinoff, most commonly a program
   * @return type
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Type of the spinoff, most commonly a program")
  @JsonProperty(JSON_PROPERTY_TYPE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getType() {
    return type;
  }


  public void setType(String type) {
    this.type = type;
  }


  public V2StructureSpinoff yearClosing(Integer yearClosing) {
    
    this.yearClosing = yearClosing;
    return this;
  }

   /**
   * optional year when this spinoff has been closed
   * @return yearClosing
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "optional year when this spinoff has been closed")
  @JsonProperty(JSON_PROPERTY_YEAR_CLOSING)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Integer getYearClosing() {
    return yearClosing;
  }


  public void setYearClosing(Integer yearClosing) {
    this.yearClosing = yearClosing;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    V2StructureSpinoff v2StructureSpinoff = (V2StructureSpinoff) o;
    return Objects.equals(this.label, v2StructureSpinoff.label) &&
        Objects.equals(this.project, v2StructureSpinoff.project) &&
        Objects.equals(this.structure, v2StructureSpinoff.structure) &&
        Objects.equals(this.type, v2StructureSpinoff.type) &&
        Objects.equals(this.yearClosing, v2StructureSpinoff.yearClosing);
  }

  @Override
  public int hashCode() {
    return Objects.hash(label, project, structure, type, yearClosing);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class V2StructureSpinoff {\n");
    sb.append("    label: ").append(toIndentedString(label)).append("\n");
    sb.append("    project: ").append(toIndentedString(project)).append("\n");
    sb.append("    structure: ").append(toIndentedString(structure)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    yearClosing: ").append(toIndentedString(yearClosing)).append("\n");
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
