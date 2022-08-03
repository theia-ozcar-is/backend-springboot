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
 * UserSecurity
 */
@JsonPropertyOrder({
  UserSecurity.JSON_PROPERTY_AUTH_TYPE,
  UserSecurity.JSON_PROPERTY_NODE_ID,
  UserSecurity.JSON_PROPERTY_SECURITY_NOTIFICATIONS,
  UserSecurity.JSON_PROPERTY_SECURITY_NOTIFICATIONS_STRING
})
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-04-13T11:23:44.539800+02:00[Europe/Paris]")
public class UserSecurity {
  public static final String JSON_PROPERTY_AUTH_TYPE = "authType";
  private String authType;

  public static final String JSON_PROPERTY_NODE_ID = "nodeId";
  private String nodeId;

  /**
   * Gets or Sets securityNotifications
   */
  public enum SecurityNotificationsEnum {
    UPDATE_HASH_REQUIRED("UPDATE_HASH_REQUIRED"),
    
    UNKNOWN("UNKNOWN");

    private String value;

    SecurityNotificationsEnum(String value) {
      this.value = value;
    }

    @JsonValue
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static SecurityNotificationsEnum fromValue(String value) {
      for (SecurityNotificationsEnum b : SecurityNotificationsEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  public static final String JSON_PROPERTY_SECURITY_NOTIFICATIONS = "securityNotifications";
  private List<SecurityNotificationsEnum> securityNotifications = null;

  public static final String JSON_PROPERTY_SECURITY_NOTIFICATIONS_STRING = "securityNotificationsString";
  private UserSecurity securityNotificationsString;


  public UserSecurity authType(String authType) {
    
    this.authType = authType;
    return this;
  }

   /**
   * Get authType
   * @return authType
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_AUTH_TYPE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getAuthType() {
    return authType;
  }


  public void setAuthType(String authType) {
    this.authType = authType;
  }


  public UserSecurity nodeId(String nodeId) {
    
    this.nodeId = nodeId;
    return this;
  }

   /**
   * Get nodeId
   * @return nodeId
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_NODE_ID)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getNodeId() {
    return nodeId;
  }


  public void setNodeId(String nodeId) {
    this.nodeId = nodeId;
  }


  public UserSecurity securityNotifications(List<SecurityNotificationsEnum> securityNotifications) {
    
    this.securityNotifications = securityNotifications;
    return this;
  }

  public UserSecurity addSecurityNotificationsItem(SecurityNotificationsEnum securityNotificationsItem) {
    if (this.securityNotifications == null) {
      this.securityNotifications = new ArrayList<>();
    }
    this.securityNotifications.add(securityNotificationsItem);
    return this;
  }

   /**
   * Get securityNotifications
   * @return securityNotifications
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_SECURITY_NOTIFICATIONS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public List<SecurityNotificationsEnum> getSecurityNotifications() {
    return securityNotifications;
  }


  public void setSecurityNotifications(List<SecurityNotificationsEnum> securityNotifications) {
    this.securityNotifications = securityNotifications;
  }


  public UserSecurity securityNotificationsString(UserSecurity securityNotificationsString) {
    
    this.securityNotificationsString = securityNotificationsString;
    return this;
  }

   /**
   * Get securityNotificationsString
   * @return securityNotificationsString
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_SECURITY_NOTIFICATIONS_STRING)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public UserSecurity getSecurityNotificationsString() {
    return securityNotificationsString;
  }


  public void setSecurityNotificationsString(UserSecurity securityNotificationsString) {
    this.securityNotificationsString = securityNotificationsString;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserSecurity userSecurity = (UserSecurity) o;
    return Objects.equals(this.authType, userSecurity.authType) &&
        Objects.equals(this.nodeId, userSecurity.nodeId) &&
        Objects.equals(this.securityNotifications, userSecurity.securityNotifications) &&
        Objects.equals(this.securityNotificationsString, userSecurity.securityNotificationsString);
  }

  @Override
  public int hashCode() {
    return Objects.hash(authType, nodeId, securityNotifications, securityNotificationsString);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UserSecurity {\n");
    sb.append("    authType: ").append(toIndentedString(authType)).append("\n");
    sb.append("    nodeId: ").append(toIndentedString(nodeId)).append("\n");
    sb.append("    securityNotifications: ").append(toIndentedString(securityNotifications)).append("\n");
    sb.append("    securityNotificationsString: ").append(toIndentedString(securityNotificationsString)).append("\n");
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
