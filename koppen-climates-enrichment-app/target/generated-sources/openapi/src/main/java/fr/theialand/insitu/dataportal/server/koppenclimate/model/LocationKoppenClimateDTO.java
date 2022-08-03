package fr.theialand.insitu.dataportal.server.koppenclimate.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import fr.theialand.insitu.dataportal.server.koppenclimate.model.KoppenClimate;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * LocationKoppenClimateDTO
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-04-15T15:48:18.579958+02:00[Europe/Paris]")
public class LocationKoppenClimateDTO   {

  @JsonProperty("latitude")
  private Double latitude;

  @JsonProperty("longitude")
  private Double longitude;

  @JsonProperty("koppenClimate")
  private KoppenClimate koppenClimate;

  public LocationKoppenClimateDTO latitude(Double latitude) {
    this.latitude = latitude;
    return this;
  }

  /**
   * Get latitude
   * @return latitude
  */
  
  @Schema(name = "latitude", required = false)
  public Double getLatitude() {
    return latitude;
  }

  public void setLatitude(Double latitude) {
    this.latitude = latitude;
  }

  public LocationKoppenClimateDTO longitude(Double longitude) {
    this.longitude = longitude;
    return this;
  }

  /**
   * Get longitude
   * @return longitude
  */
  
  @Schema(name = "longitude", required = false)
  public Double getLongitude() {
    return longitude;
  }

  public void setLongitude(Double longitude) {
    this.longitude = longitude;
  }

  public LocationKoppenClimateDTO koppenClimate(KoppenClimate koppenClimate) {
    this.koppenClimate = koppenClimate;
    return this;
  }

  /**
   * Get koppenClimate
   * @return koppenClimate
  */
  @Valid 
  @Schema(name = "koppenClimate", required = false)
  public KoppenClimate getKoppenClimate() {
    return koppenClimate;
  }

  public void setKoppenClimate(KoppenClimate koppenClimate) {
    this.koppenClimate = koppenClimate;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LocationKoppenClimateDTO locationKoppenClimateDTO = (LocationKoppenClimateDTO) o;
    return Objects.equals(this.latitude, locationKoppenClimateDTO.latitude) &&
        Objects.equals(this.longitude, locationKoppenClimateDTO.longitude) &&
        Objects.equals(this.koppenClimate, locationKoppenClimateDTO.koppenClimate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(latitude, longitude, koppenClimate);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LocationKoppenClimateDTO {\n");
    sb.append("    latitude: ").append(toIndentedString(latitude)).append("\n");
    sb.append("    longitude: ").append(toIndentedString(longitude)).append("\n");
    sb.append("    koppenClimate: ").append(toIndentedString(koppenClimate)).append("\n");
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

