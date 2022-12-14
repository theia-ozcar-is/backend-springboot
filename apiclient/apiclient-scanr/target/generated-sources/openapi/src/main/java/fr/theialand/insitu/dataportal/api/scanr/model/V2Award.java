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
 * Used in Person (all fields), Publication (only label, date).En Light uniquement label (et non prize), date.
 */
@ApiModel(description = "Used in Person (all fields), Publication (only label, date).En Light uniquement label (et non prize), date.")
@JsonPropertyOrder({
  V2Award.JSON_PROPERTY_AMOUNT,
  V2Award.JSON_PROPERTY_DATE,
  V2Award.JSON_PROPERTY_DESCRIPTION,
  V2Award.JSON_PROPERTY_LABEL,
  V2Award.JSON_PROPERTY_STRUCTURE,
  V2Award.JSON_PROPERTY_STRUCTURE_NAME,
  V2Award.JSON_PROPERTY_URL
})
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-04-13T11:24:06.705650+02:00[Europe/Paris]")
public class V2Award {
  public static final String JSON_PROPERTY_AMOUNT = "amount";
  private String amount;

  public static final String JSON_PROPERTY_DATE = "date";
  private Date date;

  public static final String JSON_PROPERTY_DESCRIPTION = "description";
  private String description;

  public static final String JSON_PROPERTY_LABEL = "label";
  private String label;

  public static final String JSON_PROPERTY_STRUCTURE = "structure";
  private V2Structure structure;

  public static final String JSON_PROPERTY_STRUCTURE_NAME = "structureName";
  private String structureName;

  public static final String JSON_PROPERTY_URL = "url";
  private String url;


  public V2Award amount(String amount) {
    
    this.amount = amount;
    return this;
  }

   /**
   * including currency
   * @return amount
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "including currency")
  @JsonProperty(JSON_PROPERTY_AMOUNT)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getAmount() {
    return amount;
  }


  public void setAmount(String amount) {
    this.amount = amount;
  }


  public V2Award date(Date date) {
    
    this.date = date;
    return this;
  }

   /**
   * Get date
   * @return date
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_DATE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Date getDate() {
    return date;
  }


  public void setDate(Date date) {
    this.date = date;
  }


  public V2Award description(String description) {
    
    this.description = description;
    return this;
  }

   /**
   * Get description
   * @return description
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_DESCRIPTION)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getDescription() {
    return description;
  }


  public void setDescription(String description) {
    this.description = description;
  }


  public V2Award label(String label) {
    
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


  public V2Award structure(V2Structure structure) {
    
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


  public V2Award structureName(String structureName) {
    
    this.structureName = structureName;
    return this;
  }

   /**
   * Label brut de l&#39;institution li??e, fourni par MESRI
   * @return structureName
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Label brut de l'institution li??e, fourni par MESRI")
  @JsonProperty(JSON_PROPERTY_STRUCTURE_NAME)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getStructureName() {
    return structureName;
  }


  public void setStructureName(String structureName) {
    this.structureName = structureName;
  }


  public V2Award url(String url) {
    
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
    V2Award v2Award = (V2Award) o;
    return Objects.equals(this.amount, v2Award.amount) &&
        Objects.equals(this.date, v2Award.date) &&
        Objects.equals(this.description, v2Award.description) &&
        Objects.equals(this.label, v2Award.label) &&
        Objects.equals(this.structure, v2Award.structure) &&
        Objects.equals(this.structureName, v2Award.structureName) &&
        Objects.equals(this.url, v2Award.url);
  }

  @Override
  public int hashCode() {
    return Objects.hash(amount, date, description, label, structure, structureName, url);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class V2Award {\n");
    sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
    sb.append("    date: ").append(toIndentedString(date)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    label: ").append(toIndentedString(label)).append("\n");
    sb.append("    structure: ").append(toIndentedString(structure)).append("\n");
    sb.append("    structureName: ").append(toIndentedString(structureName)).append("\n");
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

