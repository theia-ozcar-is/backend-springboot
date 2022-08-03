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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * RatingAverage
 */
@JsonPropertyOrder({
  RatingAverage.JSON_PROPERTY_RATING_COUNT,
  RatingAverage.JSON_PROPERTY_RATING_AVERAGES,
  RatingAverage.JSON_PROPERTY_USERFEEDBACK_COUNT,
  RatingAverage.JSON_PROPERTY_LAST_COMMENT
})
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-04-13T11:23:44.539800+02:00[Europe/Paris]")
public class RatingAverage {
  public static final String JSON_PROPERTY_RATING_COUNT = "ratingCount";
  private Integer ratingCount;

  public static final String JSON_PROPERTY_RATING_AVERAGES = "ratingAverages";
  private Map<String, Integer> ratingAverages = null;

  public static final String JSON_PROPERTY_USERFEEDBACK_COUNT = "userfeedbackCount";
  private Integer userfeedbackCount;

  public static final String JSON_PROPERTY_LAST_COMMENT = "lastComment";
  private String lastComment;


  public RatingAverage ratingCount(Integer ratingCount) {
    
    this.ratingCount = ratingCount;
    return this;
  }

   /**
   * Get ratingCount
   * @return ratingCount
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_RATING_COUNT)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Integer getRatingCount() {
    return ratingCount;
  }


  public void setRatingCount(Integer ratingCount) {
    this.ratingCount = ratingCount;
  }


  public RatingAverage ratingAverages(Map<String, Integer> ratingAverages) {
    
    this.ratingAverages = ratingAverages;
    return this;
  }

  public RatingAverage putRatingAveragesItem(String key, Integer ratingAveragesItem) {
    if (this.ratingAverages == null) {
      this.ratingAverages = new HashMap<>();
    }
    this.ratingAverages.put(key, ratingAveragesItem);
    return this;
  }

   /**
   * Get ratingAverages
   * @return ratingAverages
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_RATING_AVERAGES)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Map<String, Integer> getRatingAverages() {
    return ratingAverages;
  }


  public void setRatingAverages(Map<String, Integer> ratingAverages) {
    this.ratingAverages = ratingAverages;
  }


  public RatingAverage userfeedbackCount(Integer userfeedbackCount) {
    
    this.userfeedbackCount = userfeedbackCount;
    return this;
  }

   /**
   * Get userfeedbackCount
   * @return userfeedbackCount
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_USERFEEDBACK_COUNT)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Integer getUserfeedbackCount() {
    return userfeedbackCount;
  }


  public void setUserfeedbackCount(Integer userfeedbackCount) {
    this.userfeedbackCount = userfeedbackCount;
  }


  public RatingAverage lastComment(String lastComment) {
    
    this.lastComment = lastComment;
    return this;
  }

   /**
   * Get lastComment
   * @return lastComment
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_LAST_COMMENT)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getLastComment() {
    return lastComment;
  }


  public void setLastComment(String lastComment) {
    this.lastComment = lastComment;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RatingAverage ratingAverage = (RatingAverage) o;
    return Objects.equals(this.ratingCount, ratingAverage.ratingCount) &&
        Objects.equals(this.ratingAverages, ratingAverage.ratingAverages) &&
        Objects.equals(this.userfeedbackCount, ratingAverage.userfeedbackCount) &&
        Objects.equals(this.lastComment, ratingAverage.lastComment);
  }

  @Override
  public int hashCode() {
    return Objects.hash(ratingCount, ratingAverages, userfeedbackCount, lastComment);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RatingAverage {\n");
    sb.append("    ratingCount: ").append(toIndentedString(ratingCount)).append("\n");
    sb.append("    ratingAverages: ").append(toIndentedString(ratingAverages)).append("\n");
    sb.append("    userfeedbackCount: ").append(toIndentedString(userfeedbackCount)).append("\n");
    sb.append("    lastComment: ").append(toIndentedString(lastComment)).append("\n");
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
