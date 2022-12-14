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
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Inverse relation of Structure.parents, in Full only
 */
@ApiModel(description = "Inverse relation of Structure.parents, in Full only")
@JsonPropertyOrder({
  V2StructureChildInverseRelation.JSON_PROPERTY_FROM_DATE,
  V2StructureChildInverseRelation.JSON_PROPERTY_RELATION_TYPE,
  V2StructureChildInverseRelation.JSON_PROPERTY_STRUCTURE
})
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-04-13T11:24:06.705650+02:00[Europe/Paris]")
public class V2StructureChildInverseRelation {
  public static final String JSON_PROPERTY_FROM_DATE = "fromDate";
  private Date fromDate;

  public static final String JSON_PROPERTY_RELATION_TYPE = "relationType";
  private String relationType;

  public static final String JSON_PROPERTY_STRUCTURE = "structure";
  private V2Structure structure;


  public V2StructureChildInverseRelation fromDate(Date fromDate) {
    
    this.fromDate = fromDate;
    return this;
  }

   /**
   * Get fromDate
   * @return fromDate
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_FROM_DATE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Date getFromDate() {
    return fromDate;
  }


  public void setFromDate(Date fromDate) {
    this.fromDate = fromDate;
  }


  public V2StructureChildInverseRelation relationType(String relationType) {
    
    this.relationType = relationType;
    return this;
  }

   /**
   * Type of parent relation (including whether exclusive)
   * @return relationType
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Type of parent relation (including whether exclusive)")
  @JsonProperty(JSON_PROPERTY_RELATION_TYPE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getRelationType() {
    return relationType;
  }


  public void setRelationType(String relationType) {
    this.relationType = relationType;
  }


  public V2StructureChildInverseRelation structure(V2Structure structure) {
    
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


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    V2StructureChildInverseRelation v2StructureChildInverseRelation = (V2StructureChildInverseRelation) o;
    return Objects.equals(this.fromDate, v2StructureChildInverseRelation.fromDate) &&
        Objects.equals(this.relationType, v2StructureChildInverseRelation.relationType) &&
        Objects.equals(this.structure, v2StructureChildInverseRelation.structure);
  }

  @Override
  public int hashCode() {
    return Objects.hash(fromDate, relationType, structure);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class V2StructureChildInverseRelation {\n");
    sb.append("    fromDate: ").append(toIndentedString(fromDate)).append("\n");
    sb.append("    relationType: ").append(toIndentedString(relationType)).append("\n");
    sb.append("    structure: ").append(toIndentedString(structure)).append("\n");
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

