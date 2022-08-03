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
import fr.theialand.insitu.dataportal.api.geonetwork.model.MetadataStatus;
import fr.theialand.insitu.dataportal.api.geonetwork.model.StatusValue;
import fr.theialand.insitu.dataportal.api.geonetwork.model.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * MetadataWorkflowStatusResponse
 */
@JsonPropertyOrder({
  MetadataWorkflowStatusResponse.JSON_PROPERTY_CURRENT_STATUS,
  MetadataWorkflowStatusResponse.JSON_PROPERTY_REVIEWERS,
  MetadataWorkflowStatusResponse.JSON_PROPERTY_HAS_EDIT_PERMISSION,
  MetadataWorkflowStatusResponse.JSON_PROPERTY_STATUS
})
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-04-13T11:23:44.539800+02:00[Europe/Paris]")
public class MetadataWorkflowStatusResponse {
  public static final String JSON_PROPERTY_CURRENT_STATUS = "currentStatus";
  private MetadataStatus currentStatus;

  public static final String JSON_PROPERTY_REVIEWERS = "reviewers";
  private List<User> reviewers = null;

  public static final String JSON_PROPERTY_HAS_EDIT_PERMISSION = "hasEditPermission";
  private Boolean hasEditPermission;

  public static final String JSON_PROPERTY_STATUS = "status";
  private List<StatusValue> status = null;


  public MetadataWorkflowStatusResponse currentStatus(MetadataStatus currentStatus) {
    
    this.currentStatus = currentStatus;
    return this;
  }

   /**
   * Get currentStatus
   * @return currentStatus
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_CURRENT_STATUS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public MetadataStatus getCurrentStatus() {
    return currentStatus;
  }


  public void setCurrentStatus(MetadataStatus currentStatus) {
    this.currentStatus = currentStatus;
  }


  public MetadataWorkflowStatusResponse reviewers(List<User> reviewers) {
    
    this.reviewers = reviewers;
    return this;
  }

  public MetadataWorkflowStatusResponse addReviewersItem(User reviewersItem) {
    if (this.reviewers == null) {
      this.reviewers = new ArrayList<>();
    }
    this.reviewers.add(reviewersItem);
    return this;
  }

   /**
   * Get reviewers
   * @return reviewers
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_REVIEWERS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public List<User> getReviewers() {
    return reviewers;
  }


  public void setReviewers(List<User> reviewers) {
    this.reviewers = reviewers;
  }


  public MetadataWorkflowStatusResponse hasEditPermission(Boolean hasEditPermission) {
    
    this.hasEditPermission = hasEditPermission;
    return this;
  }

   /**
   * Get hasEditPermission
   * @return hasEditPermission
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_HAS_EDIT_PERMISSION)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Boolean getHasEditPermission() {
    return hasEditPermission;
  }


  public void setHasEditPermission(Boolean hasEditPermission) {
    this.hasEditPermission = hasEditPermission;
  }


  public MetadataWorkflowStatusResponse status(List<StatusValue> status) {
    
    this.status = status;
    return this;
  }

  public MetadataWorkflowStatusResponse addStatusItem(StatusValue statusItem) {
    if (this.status == null) {
      this.status = new ArrayList<>();
    }
    this.status.add(statusItem);
    return this;
  }

   /**
   * Get status
   * @return status
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_STATUS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public List<StatusValue> getStatus() {
    return status;
  }


  public void setStatus(List<StatusValue> status) {
    this.status = status;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MetadataWorkflowStatusResponse metadataWorkflowStatusResponse = (MetadataWorkflowStatusResponse) o;
    return Objects.equals(this.currentStatus, metadataWorkflowStatusResponse.currentStatus) &&
        Objects.equals(this.reviewers, metadataWorkflowStatusResponse.reviewers) &&
        Objects.equals(this.hasEditPermission, metadataWorkflowStatusResponse.hasEditPermission) &&
        Objects.equals(this.status, metadataWorkflowStatusResponse.status);
  }

  @Override
  public int hashCode() {
    return Objects.hash(currentStatus, reviewers, hasEditPermission, status);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MetadataWorkflowStatusResponse {\n");
    sb.append("    currentStatus: ").append(toIndentedString(currentStatus)).append("\n");
    sb.append("    reviewers: ").append(toIndentedString(reviewers)).append("\n");
    sb.append("    hasEditPermission: ").append(toIndentedString(hasEditPermission)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
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
