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
import fr.theialand.insitu.dataportal.api.scanr.model.V2Person;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Structure (leaders), Project or Publication relation to Person
 */
@ApiModel(description = "Structure (leaders), Project or Publication relation to Person")
@JsonPropertyOrder({
  V2PersonRelation.JSON_PROPERTY_EMAIL,
  V2PersonRelation.JSON_PROPERTY_FIRST_NAME,
  V2PersonRelation.JSON_PROPERTY_FROM_DATE,
  V2PersonRelation.JSON_PROPERTY_FULL_NAME,
  V2PersonRelation.JSON_PROPERTY_LAST_NAME,
  V2PersonRelation.JSON_PROPERTY_PERSON,
  V2PersonRelation.JSON_PROPERTY_ROLE,
  V2PersonRelation.JSON_PROPERTY_TITLE
})
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-04-13T11:24:06.705650+02:00[Europe/Paris]")
public class V2PersonRelation {
  public static final String JSON_PROPERTY_EMAIL = "email";
  private String email;

  public static final String JSON_PROPERTY_FIRST_NAME = "firstName";
  private String firstName;

  public static final String JSON_PROPERTY_FROM_DATE = "fromDate";
  private String fromDate;

  public static final String JSON_PROPERTY_FULL_NAME = "fullName";
  private String fullName;

  public static final String JSON_PROPERTY_LAST_NAME = "lastName";
  private String lastName;

  public static final String JSON_PROPERTY_PERSON = "person";
  private V2Person person;

  public static final String JSON_PROPERTY_ROLE = "role";
  private String role;

  public static final String JSON_PROPERTY_TITLE = "title";
  private String title;


  public V2PersonRelation email(String email) {
    
    this.email = email;
    return this;
  }

   /**
   * Only in a Project
   * @return email
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Only in a Project")
  @JsonProperty(JSON_PROPERTY_EMAIL)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getEmail() {
    return email;
  }


  public void setEmail(String email) {
    this.email = email;
  }


  public V2PersonRelation firstName(String firstName) {
    
    this.firstName = firstName;
    return this;
  }

   /**
   * Get firstName
   * @return firstName
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_FIRST_NAME)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getFirstName() {
    return firstName;
  }


  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }


  public V2PersonRelation fromDate(String fromDate) {
    
    this.fromDate = fromDate;
    return this;
  }

   /**
   * Only in a Structure
   * @return fromDate
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Only in a Structure")
  @JsonProperty(JSON_PROPERTY_FROM_DATE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getFromDate() {
    return fromDate;
  }


  public void setFromDate(String fromDate) {
    this.fromDate = fromDate;
  }


  public V2PersonRelation fullName(String fullName) {
    
    this.fullName = fullName;
    return this;
  }

   /**
   * Only in a Publication
   * @return fullName
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Only in a Publication")
  @JsonProperty(JSON_PROPERTY_FULL_NAME)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getFullName() {
    return fullName;
  }


  public void setFullName(String fullName) {
    this.fullName = fullName;
  }


  public V2PersonRelation lastName(String lastName) {
    
    this.lastName = lastName;
    return this;
  }

   /**
   * Get lastName
   * @return lastName
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_LAST_NAME)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getLastName() {
    return lastName;
  }


  public void setLastName(String lastName) {
    this.lastName = lastName;
  }


  public V2PersonRelation person(V2Person person) {
    
    this.person = person;
    return this;
  }

   /**
   * Get person
   * @return person
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_PERSON)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public V2Person getPerson() {
    return person;
  }


  public void setPerson(V2Person person) {
    this.person = person;
  }


  public V2PersonRelation role(String role) {
    
    this.role = role;
    return this;
  }

   /**
   * Role of the person in the object that is source of the relation
   * @return role
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Role of the person in the object that is source of the relation")
  @JsonProperty(JSON_PROPERTY_ROLE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getRole() {
    return role;
  }


  public void setRole(String role) {
    this.role = role;
  }


  public V2PersonRelation title(String title) {
    
    this.title = title;
    return this;
  }

   /**
   * Only in a Structure
   * @return title
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Only in a Structure")
  @JsonProperty(JSON_PROPERTY_TITLE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getTitle() {
    return title;
  }


  public void setTitle(String title) {
    this.title = title;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    V2PersonRelation v2PersonRelation = (V2PersonRelation) o;
    return Objects.equals(this.email, v2PersonRelation.email) &&
        Objects.equals(this.firstName, v2PersonRelation.firstName) &&
        Objects.equals(this.fromDate, v2PersonRelation.fromDate) &&
        Objects.equals(this.fullName, v2PersonRelation.fullName) &&
        Objects.equals(this.lastName, v2PersonRelation.lastName) &&
        Objects.equals(this.person, v2PersonRelation.person) &&
        Objects.equals(this.role, v2PersonRelation.role) &&
        Objects.equals(this.title, v2PersonRelation.title);
  }

  @Override
  public int hashCode() {
    return Objects.hash(email, firstName, fromDate, fullName, lastName, person, role, title);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class V2PersonRelation {\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    firstName: ").append(toIndentedString(firstName)).append("\n");
    sb.append("    fromDate: ").append(toIndentedString(fromDate)).append("\n");
    sb.append("    fullName: ").append(toIndentedString(fullName)).append("\n");
    sb.append("    lastName: ").append(toIndentedString(lastName)).append("\n");
    sb.append("    person: ").append(toIndentedString(person)).append("\n");
    sb.append("    role: ").append(toIndentedString(role)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
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

