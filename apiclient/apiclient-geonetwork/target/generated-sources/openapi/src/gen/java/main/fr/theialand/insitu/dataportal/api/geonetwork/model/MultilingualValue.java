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
import fr.theialand.insitu.dataportal.api.geonetwork.model.LocalizedString;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * MultilingualValue
 */
@JsonPropertyOrder({
  MultilingualValue.JSON_PROPERTY_VALUE
})
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-04-13T11:23:44.539800+02:00[Europe/Paris]")
public class MultilingualValue {
  public static final String JSON_PROPERTY_VALUE = "value";
  private List<LocalizedString> value = null;


  public MultilingualValue value(List<LocalizedString> value) {
    
    this.value = value;
    return this;
  }

  public MultilingualValue addValueItem(LocalizedString valueItem) {
    if (this.value == null) {
      this.value = new ArrayList<>();
    }
    this.value.add(valueItem);
    return this;
  }

   /**
   * Get value
   * @return value
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_VALUE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public List<LocalizedString> getValue() {
    return value;
  }


  public void setValue(List<LocalizedString> value) {
    this.value = value;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MultilingualValue multilingualValue = (MultilingualValue) o;
    return Objects.equals(this.value, multilingualValue.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MultilingualValue {\n");
    sb.append("    value: ").append(toIndentedString(value)).append("\n");
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

