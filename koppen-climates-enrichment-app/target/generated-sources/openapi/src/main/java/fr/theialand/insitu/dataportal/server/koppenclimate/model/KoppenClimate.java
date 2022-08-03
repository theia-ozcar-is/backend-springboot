package fr.theialand.insitu.dataportal.server.koppenclimate.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import fr.theialand.insitu.dataportal.server.koppenclimate.model.KoppenClimateCodeLiteral;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * KoppenClimate
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-04-15T15:48:18.579958+02:00[Europe/Paris]")
public class KoppenClimate   {

  @JsonProperty("classification")
  private KoppenClimateCodeLiteral classification;

  @JsonProperty("mainGroup")
  private KoppenClimateCodeLiteral mainGroup;

  @JsonProperty("seasonalPrecipitationSubGroup")
  private KoppenClimateCodeLiteral seasonalPrecipitationSubGroup;

  @JsonProperty("temperatureSubGroup")
  private KoppenClimateCodeLiteral temperatureSubGroup;

  public KoppenClimate classification(KoppenClimateCodeLiteral classification) {
    this.classification = classification;
    return this;
  }

  /**
   * Get classification
   * @return classification
  */
  @Valid 
  @Schema(name = "classification", required = false)
  public KoppenClimateCodeLiteral getClassification() {
    return classification;
  }

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
  */
  @Valid 
  @Schema(name = "mainGroup", required = false)
  public KoppenClimateCodeLiteral getMainGroup() {
    return mainGroup;
  }

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
  */
  @Valid 
  @Schema(name = "seasonalPrecipitationSubGroup", required = false)
  public KoppenClimateCodeLiteral getSeasonalPrecipitationSubGroup() {
    return seasonalPrecipitationSubGroup;
  }

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
  */
  @Valid 
  @Schema(name = "temperatureSubGroup", required = false)
  public KoppenClimateCodeLiteral getTemperatureSubGroup() {
    return temperatureSubGroup;
  }

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

