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
import fr.theialand.insitu.dataportal.api.scanr.model.GeoPoint;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * V2Address
 */
@JsonPropertyOrder({
  V2Address.JSON_PROPERTY_ADDRESS,
  V2Address.JSON_PROPERTY_CITY,
  V2Address.JSON_PROPERTY_CITYCODE,
  V2Address.JSON_PROPERTY_COUNTRY,
  V2Address.JSON_PROPERTY_GPS,
  V2Address.JSON_PROPERTY_LOCALISATION_SUGGESTIONS,
  V2Address.JSON_PROPERTY_MAIN,
  V2Address.JSON_PROPERTY_POSTCODE,
  V2Address.JSON_PROPERTY_PROVIDER,
  V2Address.JSON_PROPERTY_SCORE,
  V2Address.JSON_PROPERTY_URBAN_UNIT_CODE,
  V2Address.JSON_PROPERTY_URBAN_UNIT_LABEL
})
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-04-13T11:24:06.705650+02:00[Europe/Paris]")
public class V2Address {
  public static final String JSON_PROPERTY_ADDRESS = "address";
  private String address;

  public static final String JSON_PROPERTY_CITY = "city";
  private String city;

  public static final String JSON_PROPERTY_CITYCODE = "citycode";
  private String citycode;

  public static final String JSON_PROPERTY_COUNTRY = "country";
  private String country;

  public static final String JSON_PROPERTY_GPS = "gps";
  private GeoPoint gps;

  public static final String JSON_PROPERTY_LOCALISATION_SUGGESTIONS = "localisationSuggestions";
  private List<String> localisationSuggestions = null;

  public static final String JSON_PROPERTY_MAIN = "main";
  private Boolean main;

  public static final String JSON_PROPERTY_POSTCODE = "postcode";
  private String postcode;

  public static final String JSON_PROPERTY_PROVIDER = "provider";
  private String provider;

  public static final String JSON_PROPERTY_SCORE = "score";
  private String score;

  public static final String JSON_PROPERTY_URBAN_UNIT_CODE = "urbanUnitCode";
  private String urbanUnitCode;

  public static final String JSON_PROPERTY_URBAN_UNIT_LABEL = "urbanUnitLabel";
  private String urbanUnitLabel;


  public V2Address address(String address) {
    
    this.address = address;
    return this;
  }

   /**
   * Anything not in other fields, especially city and country.
   * @return address
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Anything not in other fields, especially city and country.")
  @JsonProperty(JSON_PROPERTY_ADDRESS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getAddress() {
    return address;
  }


  public void setAddress(String address) {
    this.address = address;
  }


  public V2Address city(String city) {
    
    this.city = city;
    return this;
  }

   /**
   * Get city
   * @return city
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "Paris", value = "")
  @JsonProperty(JSON_PROPERTY_CITY)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getCity() {
    return city;
  }


  public void setCity(String city) {
    this.city = city;
  }


  public V2Address citycode(String citycode) {
    
    this.citycode = citycode;
    return this;
  }

   /**
   * Get citycode
   * @return citycode
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "75009", value = "")
  @JsonProperty(JSON_PROPERTY_CITYCODE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getCitycode() {
    return citycode;
  }


  public void setCitycode(String citycode) {
    this.citycode = citycode;
  }


  public V2Address country(String country) {
    
    this.country = country;
    return this;
  }

   /**
   * since v2
   * @return country
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "since v2")
  @JsonProperty(JSON_PROPERTY_COUNTRY)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getCountry() {
    return country;
  }


  public void setCountry(String country) {
    this.country = country;
  }


  public V2Address gps(GeoPoint gps) {
    
    this.gps = gps;
    return this;
  }

   /**
   * Get gps
   * @return gps
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_GPS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public GeoPoint getGps() {
    return gps;
  }


  public void setGps(GeoPoint gps) {
    this.gps = gps;
  }


  public V2Address localisationSuggestions(List<String> localisationSuggestions) {
    
    this.localisationSuggestions = localisationSuggestions;
    return this;
  }

  public V2Address addLocalisationSuggestionsItem(String localisationSuggestionsItem) {
    if (this.localisationSuggestions == null) {
      this.localisationSuggestions = new ArrayList<>();
    }
    this.localisationSuggestions.add(localisationSuggestionsItem);
    return this;
  }

   /**
   * Gathers addres, city... fields to provide autocompletion in a single \&quot;place\&quot; input using Elasticsearch suggest API. Computed by ScanESR on all Address instances.
   * @return localisationSuggestions
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Gathers addres, city... fields to provide autocompletion in a single \"place\" input using Elasticsearch suggest API. Computed by ScanESR on all Address instances.")
  @JsonProperty(JSON_PROPERTY_LOCALISATION_SUGGESTIONS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public List<String> getLocalisationSuggestions() {
    return localisationSuggestions;
  }


  public void setLocalisationSuggestions(List<String> localisationSuggestions) {
    this.localisationSuggestions = localisationSuggestions;
  }


  public V2Address main(Boolean main) {
    
    this.main = main;
    return this;
  }

   /**
   * Get main
   * @return main
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_MAIN)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Boolean getMain() {
    return main;
  }


  public void setMain(Boolean main) {
    this.main = main;
  }


  public V2Address postcode(String postcode) {
    
    this.postcode = postcode;
    return this;
  }

   /**
   * Get postcode
   * @return postcode
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_POSTCODE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getPostcode() {
    return postcode;
  }


  public void setPostcode(String postcode) {
    this.postcode = postcode;
  }


  public V2Address provider(String provider) {
    
    this.provider = provider;
    return this;
  }

   /**
   * Address information provider. Since v2
   * @return provider
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Address information provider. Since v2")
  @JsonProperty(JSON_PROPERTY_PROVIDER)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getProvider() {
    return provider;
  }


  public void setProvider(String provider) {
    this.provider = provider;
  }


  public V2Address score(String score) {
    
    this.score = score;
    return this;
  }

   /**
   * Score relatif au provider des informations li??es ?? l&#39;adresse. Since v2
   * @return score
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Score relatif au provider des informations li??es ?? l'adresse. Since v2")
  @JsonProperty(JSON_PROPERTY_SCORE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getScore() {
    return score;
  }


  public void setScore(String score) {
    this.score = score;
  }


  public V2Address urbanUnitCode(String urbanUnitCode) {
    
    this.urbanUnitCode = urbanUnitCode;
    return this;
  }

   /**
   * Provided to OpenStreetMap ?
   * @return urbanUnitCode
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Provided to OpenStreetMap ?")
  @JsonProperty(JSON_PROPERTY_URBAN_UNIT_CODE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getUrbanUnitCode() {
    return urbanUnitCode;
  }


  public void setUrbanUnitCode(String urbanUnitCode) {
    this.urbanUnitCode = urbanUnitCode;
  }


  public V2Address urbanUnitLabel(String urbanUnitLabel) {
    
    this.urbanUnitLabel = urbanUnitLabel;
    return this;
  }

   /**
   * Provided to OpenStreetMap for display ?
   * @return urbanUnitLabel
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Provided to OpenStreetMap for display ?")
  @JsonProperty(JSON_PROPERTY_URBAN_UNIT_LABEL)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getUrbanUnitLabel() {
    return urbanUnitLabel;
  }


  public void setUrbanUnitLabel(String urbanUnitLabel) {
    this.urbanUnitLabel = urbanUnitLabel;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    V2Address v2Address = (V2Address) o;
    return Objects.equals(this.address, v2Address.address) &&
        Objects.equals(this.city, v2Address.city) &&
        Objects.equals(this.citycode, v2Address.citycode) &&
        Objects.equals(this.country, v2Address.country) &&
        Objects.equals(this.gps, v2Address.gps) &&
        Objects.equals(this.localisationSuggestions, v2Address.localisationSuggestions) &&
        Objects.equals(this.main, v2Address.main) &&
        Objects.equals(this.postcode, v2Address.postcode) &&
        Objects.equals(this.provider, v2Address.provider) &&
        Objects.equals(this.score, v2Address.score) &&
        Objects.equals(this.urbanUnitCode, v2Address.urbanUnitCode) &&
        Objects.equals(this.urbanUnitLabel, v2Address.urbanUnitLabel);
  }

  @Override
  public int hashCode() {
    return Objects.hash(address, city, citycode, country, gps, localisationSuggestions, main, postcode, provider, score, urbanUnitCode, urbanUnitLabel);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class V2Address {\n");
    sb.append("    address: ").append(toIndentedString(address)).append("\n");
    sb.append("    city: ").append(toIndentedString(city)).append("\n");
    sb.append("    citycode: ").append(toIndentedString(citycode)).append("\n");
    sb.append("    country: ").append(toIndentedString(country)).append("\n");
    sb.append("    gps: ").append(toIndentedString(gps)).append("\n");
    sb.append("    localisationSuggestions: ").append(toIndentedString(localisationSuggestions)).append("\n");
    sb.append("    main: ").append(toIndentedString(main)).append("\n");
    sb.append("    postcode: ").append(toIndentedString(postcode)).append("\n");
    sb.append("    provider: ").append(toIndentedString(provider)).append("\n");
    sb.append("    score: ").append(toIndentedString(score)).append("\n");
    sb.append("    urbanUnitCode: ").append(toIndentedString(urbanUnitCode)).append("\n");
    sb.append("    urbanUnitLabel: ").append(toIndentedString(urbanUnitLabel)).append("\n");
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

