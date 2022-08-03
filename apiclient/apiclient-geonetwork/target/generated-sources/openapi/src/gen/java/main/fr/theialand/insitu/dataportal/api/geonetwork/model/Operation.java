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
 * Operation
 */
@JsonPropertyOrder({
  Operation.JSON_PROPERTY_NAME,
  Operation.JSON_PROPERTY_ID,
  Operation.JSON_PROPERTY_RESERVED,
  Operation.JSON_PROPERTY_RESERVED_OPERATION,
  Operation.JSON_PROPERTY_LABEL
})
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-04-13T11:23:44.539800+02:00[Europe/Paris]")
public class Operation {
  public static final String JSON_PROPERTY_NAME = "name";
  private String name;

  public static final String JSON_PROPERTY_ID = "id";
  private Integer id;

  public static final String JSON_PROPERTY_RESERVED = "reserved";
  private Boolean reserved;

  /**
   * Gets or Sets reservedOperation
   */
  public enum ReservedOperationEnum {
    VIEW("view"),
    
    DOWNLOAD("download"),
    
    EDITING("editing"),
    
    NOTIFY("notify"),
    
    DYNAMIC("dynamic"),
    
    FEATURED("featured");

    private String value;

    ReservedOperationEnum(String value) {
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
    public static ReservedOperationEnum fromValue(String value) {
      for (ReservedOperationEnum b : ReservedOperationEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  public static final String JSON_PROPERTY_RESERVED_OPERATION = "reservedOperation";
  private ReservedOperationEnum reservedOperation;

  public static final String JSON_PROPERTY_LABEL = "label";
  private Map<String, String> label = null;


  public Operation name(String name) {
    
    this.name = name;
    return this;
  }

   /**
   * Get name
   * @return name
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_NAME)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getName() {
    return name;
  }


  public void setName(String name) {
    this.name = name;
  }


  public Operation id(Integer id) {
    
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

  public Integer getId() {
    return id;
  }


  public void setId(Integer id) {
    this.id = id;
  }


  public Operation reserved(Boolean reserved) {
    
    this.reserved = reserved;
    return this;
  }

   /**
   * Get reserved
   * @return reserved
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_RESERVED)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Boolean getReserved() {
    return reserved;
  }


  public void setReserved(Boolean reserved) {
    this.reserved = reserved;
  }


  public Operation reservedOperation(ReservedOperationEnum reservedOperation) {
    
    this.reservedOperation = reservedOperation;
    return this;
  }

   /**
   * Get reservedOperation
   * @return reservedOperation
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_RESERVED_OPERATION)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public ReservedOperationEnum getReservedOperation() {
    return reservedOperation;
  }


  public void setReservedOperation(ReservedOperationEnum reservedOperation) {
    this.reservedOperation = reservedOperation;
  }


  public Operation label(Map<String, String> label) {
    
    this.label = label;
    return this;
  }

  public Operation putLabelItem(String key, String labelItem) {
    if (this.label == null) {
      this.label = new HashMap<>();
    }
    this.label.put(key, labelItem);
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

  public Map<String, String> getLabel() {
    return label;
  }


  public void setLabel(Map<String, String> label) {
    this.label = label;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Operation operation = (Operation) o;
    return Objects.equals(this.name, operation.name) &&
        Objects.equals(this.id, operation.id) &&
        Objects.equals(this.reserved, operation.reserved) &&
        Objects.equals(this.reservedOperation, operation.reservedOperation) &&
        Objects.equals(this.label, operation.label);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, id, reserved, reservedOperation, label);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Operation {\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    reserved: ").append(toIndentedString(reserved)).append("\n");
    sb.append("    reservedOperation: ").append(toIndentedString(reservedOperation)).append("\n");
    sb.append("    label: ").append(toIndentedString(label)).append("\n");
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
