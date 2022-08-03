/*
 * koppen-climate
 * openAPI definition for koppen-climate API. The API is queried using a location and return the Koppen climate corresponding to the location. Koppen climates come from Hylke E. Beck et al. 2018. https://doi.org/10.1038/sdata.2018.214. The dataset used is Beck_KG_V1_present_conf_0p0083.tif that represents the present-day (1980–2016) Köppen-Geiger climate classification at a spacial resolution of 0.0083°.
 *
 * The version of the OpenAPI document: 0.1
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package fr.theialand.insitu.dataportal.api.koppenclimate.model;

import java.util.Objects;
import java.util.Arrays;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;
import fr.theialand.insitu.dataportal.api.koppenclimate.model.KoppenClimateCodeLiteral;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * KoppenClimate
 */
@JsonPropertyOrder({
  KoppenClimate.JSON_PROPERTY_CLASSIFICATION,
  KoppenClimate.JSON_PROPERTY_MAIN_GROUP,
  KoppenClimate.JSON_PROPERTY_SEASONAL_PRECIPITATION_SUB_GROUP,
  KoppenClimate.JSON_PROPERTY_TEMPERATURE_SUB_GROUP
})
@JsonTypeName("KoppenClimate")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-04-15T15:53:41.507160+02:00[Europe/Paris]")
public class KoppenClimate {
  public static final String JSON_PROPERTY_CLASSIFICATION = "classification";
  private KoppenClimateCodeLiteral classification;

  public static final String JSON_PROPERTY_MAIN_GROUP = "mainGroup";
  private KoppenClimateCodeLiteral mainGroup;

  public static final String JSON_PROPERTY_SEASONAL_PRECIPITATION_SUB_GROUP = "seasonalPrecipitationSubGroup";
  private KoppenClimateCodeLiteral seasonalPrecipitationSubGroup;

  public static final String JSON_PROPERTY_TEMPERATURE_SUB_GROUP = "temperatureSubGroup";
  private KoppenClimateCodeLiteral temperatureSubGroup;

  public KoppenClimate() { 
  }

  public KoppenClimate classification(KoppenClimateCodeLiteral classification) {
    
    this.classification = classification;
    return this;
  }

   /**
   * Get classification
   * @return classification
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_CLASSIFICATION)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public KoppenClimateCodeLiteral getClassification() {
    return classification;
  }


  @JsonProperty(JSON_PROPERTY_CLASSIFICATION)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setClassification(KoppenClimateCodeLiteral classification) {
    this.classification = classification;
  }


  public KoppenClimate mainGroup(KoppenClimateCodeLiteral mainGroup) {
    
    this.mainGroup = mainGroup;
    return this;
  }

   /**
   * Get mainGroup
   * @return mainGroup
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_MAIN_GROUP)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public KoppenClimateCodeLiteral getMainGroup() {
    return mainGroup;
  }


  @JsonProperty(JSON_PROPERTY_MAIN_GROUP)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setMainGroup(KoppenClimateCodeLiteral mainGroup) {
    this.mainGroup = mainGroup;
  }


  public KoppenClimate seasonalPrecipitationSubGroup(KoppenClimateCodeLiteral seasonalPrecipitationSubGroup) {
    
    this.seasonalPrecipitationSubGroup = seasonalPrecipitationSubGroup;
    return this;
  }

   /**
   * Get seasonalPrecipitationSubGroup
   * @return seasonalPrecipitationSubGroup
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_SEASONAL_PRECIPITATION_SUB_GROUP)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public KoppenClimateCodeLiteral getSeasonalPrecipitationSubGroup() {
    return seasonalPrecipitationSubGroup;
  }


  @JsonProperty(JSON_PROPERTY_SEASONAL_PRECIPITATION_SUB_GROUP)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setSeasonalPrecipitationSubGroup(KoppenClimateCodeLiteral seasonalPrecipitationSubGroup) {
    this.seasonalPrecipitationSubGroup = seasonalPrecipitationSubGroup;
  }


  public KoppenClimate temperatureSubGroup(KoppenClimateCodeLiteral temperatureSubGroup) {
    
    this.temperatureSubGroup = temperatureSubGroup;
    return this;
  }

   /**
   * Get temperatureSubGroup
   * @return temperatureSubGroup
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_TEMPERATURE_SUB_GROUP)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public KoppenClimateCodeLiteral getTemperatureSubGroup() {
    return temperatureSubGroup;
  }


  @JsonProperty(JSON_PROPERTY_TEMPERATURE_SUB_GROUP)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setTemperatureSubGroup(KoppenClimateCodeLiteral temperatureSubGroup) {
    this.temperatureSubGroup = temperatureSubGroup;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    KoppenClimate koppenClimate = (KoppenClimate) o;
    return Objects.equals(this.classification, koppenClimate.classification) &&
        Objects.equals(this.mainGroup, koppenClimate.mainGroup) &&
        Objects.equals(this.seasonalPrecipitationSubGroup, koppenClimate.seasonalPrecipitationSubGroup) &&
        Objects.equals(this.temperatureSubGroup, koppenClimate.temperatureSubGroup);
  }

  @Override
  public int hashCode() {
    return Objects.hash(classification, mainGroup, seasonalPrecipitationSubGroup, temperatureSubGroup);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class KoppenClimate {\n");
    sb.append("    classification: ").append(toIndentedString(classification)).append("\n");
    sb.append("    mainGroup: ").append(toIndentedString(mainGroup)).append("\n");
    sb.append("    seasonalPrecipitationSubGroup: ").append(toIndentedString(seasonalPrecipitationSubGroup)).append("\n");
    sb.append("    temperatureSubGroup: ").append(toIndentedString(temperatureSubGroup)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}
