package fr.theialand.insitu.dataportal.server.koppenclimate.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * KoppenClimateCodeLiteral
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-04-15T15:48:18.579958+02:00[Europe/Paris]")
public class KoppenClimateCodeLiteral   {

  @JsonProperty("code")
  private String code;

  @JsonProperty("literal")
  private String literal;

  public KoppenClimateCodeLiteral code(String code) {
    this.code = code;
    return this;
  }

  /**
   * Get code
   * @return code
  */
  
  @Schema(name = "code", required = false)
  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public KoppenClimateCodeLiteral literal(String literal) {
    this.literal = literal;
    return this;
  }

  /**
   * Get literal
   * @return literal
  */
  
  @Schema(name = "literal", required = false)
  public String getLiteral() {
    return literal;
  }

  public void setLiteral(String literal) {
    this.literal = literal;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    KoppenClimateCodeLiteral koppenClimateCodeLiteral = (KoppenClimateCodeLiteral) o;
    return Objects.equals(this.code, koppenClimateCodeLiteral.code) &&
        Objects.equals(this.literal, koppenClimateCodeLiteral.literal);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, literal);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class KoppenClimateCodeLiteral {\n");
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    literal: ").append(toIndentedString(literal)).append("\n");
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

