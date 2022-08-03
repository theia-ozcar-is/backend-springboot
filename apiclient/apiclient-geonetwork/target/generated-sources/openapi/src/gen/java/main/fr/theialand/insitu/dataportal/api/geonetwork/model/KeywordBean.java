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
 * KeywordBean
 */
@JsonPropertyOrder({
  KeywordBean.JSON_PROPERTY_VALUES,
  KeywordBean.JSON_PROPERTY_DEFINITIONS,
  KeywordBean.JSON_PROPERTY_COORD_EAST,
  KeywordBean.JSON_PROPERTY_COORD_WEST,
  KeywordBean.JSON_PROPERTY_COORD_SOUTH,
  KeywordBean.JSON_PROPERTY_COORD_NORTH,
  KeywordBean.JSON_PROPERTY_THESAURUS_KEY,
  KeywordBean.JSON_PROPERTY_NAMESPACE_CODE,
  KeywordBean.JSON_PROPERTY_THESAURUS_INFO,
  KeywordBean.JSON_PROPERTY_DEFINITION,
  KeywordBean.JSON_PROPERTY_VALUE,
  KeywordBean.JSON_PROPERTY_URI
})
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-04-13T11:23:44.539800+02:00[Europe/Paris]")
public class KeywordBean {
  public static final String JSON_PROPERTY_VALUES = "values";
  private Map<String, String> values = null;

  public static final String JSON_PROPERTY_DEFINITIONS = "definitions";
  private Map<String, String> definitions = null;

  public static final String JSON_PROPERTY_COORD_EAST = "coordEast";
  private String coordEast;

  public static final String JSON_PROPERTY_COORD_WEST = "coordWest";
  private String coordWest;

  public static final String JSON_PROPERTY_COORD_SOUTH = "coordSouth";
  private String coordSouth;

  public static final String JSON_PROPERTY_COORD_NORTH = "coordNorth";
  private String coordNorth;

  public static final String JSON_PROPERTY_THESAURUS_KEY = "thesaurusKey";
  private String thesaurusKey;

  public static final String JSON_PROPERTY_NAMESPACE_CODE = "namespaceCode";
  private KeywordBean namespaceCode;

  public static final String JSON_PROPERTY_THESAURUS_INFO = "thesaurusInfo";
  private KeywordBean thesaurusInfo;

  public static final String JSON_PROPERTY_DEFINITION = "definition";
  private String definition;

  public static final String JSON_PROPERTY_VALUE = "value";
  private String value;

  public static final String JSON_PROPERTY_URI = "uri";
  private String uri;


  public KeywordBean values(Map<String, String> values) {
    
    this.values = values;
    return this;
  }

  public KeywordBean putValuesItem(String key, String valuesItem) {
    if (this.values == null) {
      this.values = new HashMap<>();
    }
    this.values.put(key, valuesItem);
    return this;
  }

   /**
   * Get values
   * @return values
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_VALUES)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Map<String, String> getValues() {
    return values;
  }


  public void setValues(Map<String, String> values) {
    this.values = values;
  }


  public KeywordBean definitions(Map<String, String> definitions) {
    
    this.definitions = definitions;
    return this;
  }

  public KeywordBean putDefinitionsItem(String key, String definitionsItem) {
    if (this.definitions == null) {
      this.definitions = new HashMap<>();
    }
    this.definitions.put(key, definitionsItem);
    return this;
  }

   /**
   * Get definitions
   * @return definitions
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_DEFINITIONS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Map<String, String> getDefinitions() {
    return definitions;
  }


  public void setDefinitions(Map<String, String> definitions) {
    this.definitions = definitions;
  }


  public KeywordBean coordEast(String coordEast) {
    
    this.coordEast = coordEast;
    return this;
  }

   /**
   * Get coordEast
   * @return coordEast
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_COORD_EAST)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getCoordEast() {
    return coordEast;
  }


  public void setCoordEast(String coordEast) {
    this.coordEast = coordEast;
  }


  public KeywordBean coordWest(String coordWest) {
    
    this.coordWest = coordWest;
    return this;
  }

   /**
   * Get coordWest
   * @return coordWest
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_COORD_WEST)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getCoordWest() {
    return coordWest;
  }


  public void setCoordWest(String coordWest) {
    this.coordWest = coordWest;
  }


  public KeywordBean coordSouth(String coordSouth) {
    
    this.coordSouth = coordSouth;
    return this;
  }

   /**
   * Get coordSouth
   * @return coordSouth
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_COORD_SOUTH)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getCoordSouth() {
    return coordSouth;
  }


  public void setCoordSouth(String coordSouth) {
    this.coordSouth = coordSouth;
  }


  public KeywordBean coordNorth(String coordNorth) {
    
    this.coordNorth = coordNorth;
    return this;
  }

   /**
   * Get coordNorth
   * @return coordNorth
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_COORD_NORTH)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getCoordNorth() {
    return coordNorth;
  }


  public void setCoordNorth(String coordNorth) {
    this.coordNorth = coordNorth;
  }


  public KeywordBean thesaurusKey(String thesaurusKey) {
    
    this.thesaurusKey = thesaurusKey;
    return this;
  }

   /**
   * Get thesaurusKey
   * @return thesaurusKey
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_THESAURUS_KEY)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getThesaurusKey() {
    return thesaurusKey;
  }


  public void setThesaurusKey(String thesaurusKey) {
    this.thesaurusKey = thesaurusKey;
  }


  public KeywordBean namespaceCode(KeywordBean namespaceCode) {
    
    this.namespaceCode = namespaceCode;
    return this;
  }

   /**
   * Get namespaceCode
   * @return namespaceCode
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_NAMESPACE_CODE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public KeywordBean getNamespaceCode() {
    return namespaceCode;
  }


  public void setNamespaceCode(KeywordBean namespaceCode) {
    this.namespaceCode = namespaceCode;
  }


  public KeywordBean thesaurusInfo(KeywordBean thesaurusInfo) {
    
    this.thesaurusInfo = thesaurusInfo;
    return this;
  }

   /**
   * Get thesaurusInfo
   * @return thesaurusInfo
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_THESAURUS_INFO)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public KeywordBean getThesaurusInfo() {
    return thesaurusInfo;
  }


  public void setThesaurusInfo(KeywordBean thesaurusInfo) {
    this.thesaurusInfo = thesaurusInfo;
  }


  public KeywordBean definition(String definition) {
    
    this.definition = definition;
    return this;
  }

   /**
   * Get definition
   * @return definition
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_DEFINITION)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getDefinition() {
    return definition;
  }


  public void setDefinition(String definition) {
    this.definition = definition;
  }


  public KeywordBean value(String value) {
    
    this.value = value;
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

  public String getValue() {
    return value;
  }


  public void setValue(String value) {
    this.value = value;
  }


  public KeywordBean uri(String uri) {
    
    this.uri = uri;
    return this;
  }

   /**
   * Get uri
   * @return uri
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_URI)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getUri() {
    return uri;
  }


  public void setUri(String uri) {
    this.uri = uri;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    KeywordBean keywordBean = (KeywordBean) o;
    return Objects.equals(this.values, keywordBean.values) &&
        Objects.equals(this.definitions, keywordBean.definitions) &&
        Objects.equals(this.coordEast, keywordBean.coordEast) &&
        Objects.equals(this.coordWest, keywordBean.coordWest) &&
        Objects.equals(this.coordSouth, keywordBean.coordSouth) &&
        Objects.equals(this.coordNorth, keywordBean.coordNorth) &&
        Objects.equals(this.thesaurusKey, keywordBean.thesaurusKey) &&
        Objects.equals(this.namespaceCode, keywordBean.namespaceCode) &&
        Objects.equals(this.thesaurusInfo, keywordBean.thesaurusInfo) &&
        Objects.equals(this.definition, keywordBean.definition) &&
        Objects.equals(this.value, keywordBean.value) &&
        Objects.equals(this.uri, keywordBean.uri);
  }

  @Override
  public int hashCode() {
    return Objects.hash(values, definitions, coordEast, coordWest, coordSouth, coordNorth, thesaurusKey, namespaceCode, thesaurusInfo, definition, value, uri);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class KeywordBean {\n");
    sb.append("    values: ").append(toIndentedString(values)).append("\n");
    sb.append("    definitions: ").append(toIndentedString(definitions)).append("\n");
    sb.append("    coordEast: ").append(toIndentedString(coordEast)).append("\n");
    sb.append("    coordWest: ").append(toIndentedString(coordWest)).append("\n");
    sb.append("    coordSouth: ").append(toIndentedString(coordSouth)).append("\n");
    sb.append("    coordNorth: ").append(toIndentedString(coordNorth)).append("\n");
    sb.append("    thesaurusKey: ").append(toIndentedString(thesaurusKey)).append("\n");
    sb.append("    namespaceCode: ").append(toIndentedString(namespaceCode)).append("\n");
    sb.append("    thesaurusInfo: ").append(toIndentedString(thesaurusInfo)).append("\n");
    sb.append("    definition: ").append(toIndentedString(definition)).append("\n");
    sb.append("    value: ").append(toIndentedString(value)).append("\n");
    sb.append("    uri: ").append(toIndentedString(uri)).append("\n");
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
