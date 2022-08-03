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
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * SavedQuery
 */
@JsonPropertyOrder({
  SavedQuery.JSON_PROPERTY_ID,
  SavedQuery.JSON_PROPERTY_XPATH,
  SavedQuery.JSON_PROPERTY_LABEL,
  SavedQuery.JSON_PROPERTY_CLEAN_VALUES,
  SavedQuery.JSON_PROPERTY_PARAMETERS
})
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-04-13T11:23:44.539800+02:00[Europe/Paris]")
public class SavedQuery {
  public static final String JSON_PROPERTY_ID = "id";
  private String id;

  public static final String JSON_PROPERTY_XPATH = "xpath";
  private String xpath;

  public static final String JSON_PROPERTY_LABEL = "label";
  private String label;

  public static final String JSON_PROPERTY_CLEAN_VALUES = "cleanValues";
  private String cleanValues;

  public static final String JSON_PROPERTY_PARAMETERS = "parameters";
  private List<String> parameters = null;


  public SavedQuery id(String id) {
    
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


  public SavedQuery xpath(String xpath) {
    
    this.xpath = xpath;
    return this;
  }

   /**
   * Get xpath
   * @return xpath
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_XPATH)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getXpath() {
    return xpath;
  }


  public void setXpath(String xpath) {
    this.xpath = xpath;
  }


  public SavedQuery label(String label) {
    
    this.label = label;
    return this;
  }

   /**
   * Get label
   * @return label
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_LABEL)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getLabel() {
    return label;
  }


  public void setLabel(String label) {
    this.label = label;
  }


  public SavedQuery cleanValues(String cleanValues) {
    
    this.cleanValues = cleanValues;
    return this;
  }

   /**
   * Get cleanValues
   * @return cleanValues
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_CLEAN_VALUES)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getCleanValues() {
    return cleanValues;
  }


  public void setCleanValues(String cleanValues) {
    this.cleanValues = cleanValues;
  }


  public SavedQuery parameters(List<String> parameters) {
    
    this.parameters = parameters;
    return this;
  }

  public SavedQuery addParametersItem(String parametersItem) {
    if (this.parameters == null) {
      this.parameters = new ArrayList<>();
    }
    this.parameters.add(parametersItem);
    return this;
  }

   /**
   * Get parameters
   * @return parameters
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_PARAMETERS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public List<String> getParameters() {
    return parameters;
  }


  public void setParameters(List<String> parameters) {
    this.parameters = parameters;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SavedQuery savedQuery = (SavedQuery) o;
    return Objects.equals(this.id, savedQuery.id) &&
        Objects.equals(this.xpath, savedQuery.xpath) &&
        Objects.equals(this.label, savedQuery.label) &&
        Objects.equals(this.cleanValues, savedQuery.cleanValues) &&
        Objects.equals(this.parameters, savedQuery.parameters);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, xpath, label, cleanValues, parameters);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SavedQuery {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    xpath: ").append(toIndentedString(xpath)).append("\n");
    sb.append("    label: ").append(toIndentedString(label)).append("\n");
    sb.append("    cleanValues: ").append(toIndentedString(cleanValues)).append("\n");
    sb.append("    parameters: ").append(toIndentedString(parameters)).append("\n");
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

