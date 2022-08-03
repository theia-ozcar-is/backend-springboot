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
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Financial and statistical data about the structure
 */
@ApiModel(description = "Financial and statistical data about the structure")
@JsonPropertyOrder({
  V2StructureFinance.JSON_PROPERTY_DATE,
  V2StructureFinance.JSON_PROPERTY_OPERATING_INCOME,
  V2StructureFinance.JSON_PROPERTY_REVENUE
})
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-04-13T11:24:06.705650+02:00[Europe/Paris]")
public class V2StructureFinance {
  public static final String JSON_PROPERTY_DATE = "date";
  private Date date;

  public static final String JSON_PROPERTY_OPERATING_INCOME = "operatingIncome";
  private String operatingIncome;

  public static final String JSON_PROPERTY_REVENUE = "revenue";
  private String revenue;


  public V2StructureFinance date(Date date) {
    
    this.date = date;
    return this;
  }

   /**
   * Date of the information
   * @return date
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Date of the information")
  @JsonProperty(JSON_PROPERTY_DATE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Date getDate() {
    return date;
  }


  public void setDate(Date date) {
    this.date = date;
  }


  public V2StructureFinance operatingIncome(String operatingIncome) {
    
    this.operatingIncome = operatingIncome;
    return this;
  }

   /**
   * Mere string (with currency ?), not used in aggregations
   * @return operatingIncome
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Mere string (with currency ?), not used in aggregations")
  @JsonProperty(JSON_PROPERTY_OPERATING_INCOME)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getOperatingIncome() {
    return operatingIncome;
  }


  public void setOperatingIncome(String operatingIncome) {
    this.operatingIncome = operatingIncome;
  }


  public V2StructureFinance revenue(String revenue) {
    
    this.revenue = revenue;
    return this;
  }

   /**
   * Mere string (with currency ?)
   * @return revenue
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Mere string (with currency ?)")
  @JsonProperty(JSON_PROPERTY_REVENUE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getRevenue() {
    return revenue;
  }


  public void setRevenue(String revenue) {
    this.revenue = revenue;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    V2StructureFinance v2StructureFinance = (V2StructureFinance) o;
    return Objects.equals(this.date, v2StructureFinance.date) &&
        Objects.equals(this.operatingIncome, v2StructureFinance.operatingIncome) &&
        Objects.equals(this.revenue, v2StructureFinance.revenue);
  }

  @Override
  public int hashCode() {
    return Objects.hash(date, operatingIncome, revenue);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class V2StructureFinance {\n");
    sb.append("    date: ").append(toIndentedString(date)).append("\n");
    sb.append("    operatingIncome: ").append(toIndentedString(operatingIncome)).append("\n");
    sb.append("    revenue: ").append(toIndentedString(revenue)).append("\n");
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
