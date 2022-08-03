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
 * ReplaceEntryChange
 */
@JsonPropertyOrder({
  ReplaceEntryChange.JSON_PROPERTY_ORIGINAL_VAL,
  ReplaceEntryChange.JSON_PROPERTY_CHANGED_VAL,
  ReplaceEntryChange.JSON_PROPERTY_FIELD_ID
})
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-04-13T11:23:44.539800+02:00[Europe/Paris]")
public class ReplaceEntryChange {
  public static final String JSON_PROPERTY_ORIGINAL_VAL = "originalVal";
  private String originalVal;

  public static final String JSON_PROPERTY_CHANGED_VAL = "changedVal";
  private String changedVal;

  public static final String JSON_PROPERTY_FIELD_ID = "fieldId";
  private String fieldId;


  public ReplaceEntryChange originalVal(String originalVal) {
    
    this.originalVal = originalVal;
    return this;
  }

   /**
   * Get originalVal
   * @return originalVal
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_ORIGINAL_VAL)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getOriginalVal() {
    return originalVal;
  }


  public void setOriginalVal(String originalVal) {
    this.originalVal = originalVal;
  }


  public ReplaceEntryChange changedVal(String changedVal) {
    
    this.changedVal = changedVal;
    return this;
  }

   /**
   * Get changedVal
   * @return changedVal
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_CHANGED_VAL)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getChangedVal() {
    return changedVal;
  }


  public void setChangedVal(String changedVal) {
    this.changedVal = changedVal;
  }


  public ReplaceEntryChange fieldId(String fieldId) {
    
    this.fieldId = fieldId;
    return this;
  }

   /**
   * Get fieldId
   * @return fieldId
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_FIELD_ID)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getFieldId() {
    return fieldId;
  }


  public void setFieldId(String fieldId) {
    this.fieldId = fieldId;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ReplaceEntryChange replaceEntryChange = (ReplaceEntryChange) o;
    return Objects.equals(this.originalVal, replaceEntryChange.originalVal) &&
        Objects.equals(this.changedVal, replaceEntryChange.changedVal) &&
        Objects.equals(this.fieldId, replaceEntryChange.fieldId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(originalVal, changedVal, fieldId);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ReplaceEntryChange {\n");
    sb.append("    originalVal: ").append(toIndentedString(originalVal)).append("\n");
    sb.append("    changedVal: ").append(toIndentedString(changedVal)).append("\n");
    sb.append("    fieldId: ").append(toIndentedString(fieldId)).append("\n");
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
