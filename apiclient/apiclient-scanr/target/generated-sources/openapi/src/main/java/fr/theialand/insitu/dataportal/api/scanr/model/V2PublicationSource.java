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
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * V2PublicationSource
 */
@JsonPropertyOrder({
  V2PublicationSource.JSON_PROPERTY_ARTICLE_NUMBER,
  V2PublicationSource.JSON_PROPERTY_IS_IN_DOAJ,
  V2PublicationSource.JSON_PROPERTY_IS_OA,
  V2PublicationSource.JSON_PROPERTY_ISSUE,
  V2PublicationSource.JSON_PROPERTY_JOURNAL_ISSNS,
  V2PublicationSource.JSON_PROPERTY_PAGINATION,
  V2PublicationSource.JSON_PROPERTY_PUBLISHER,
  V2PublicationSource.JSON_PROPERTY_SUBTITLE,
  V2PublicationSource.JSON_PROPERTY_TITLE,
  V2PublicationSource.JSON_PROPERTY_TYPE
})
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-04-13T11:24:06.705650+02:00[Europe/Paris]")
public class V2PublicationSource {
  public static final String JSON_PROPERTY_ARTICLE_NUMBER = "articleNumber";
  private String articleNumber;

  public static final String JSON_PROPERTY_IS_IN_DOAJ = "isInDoaj";
  private Boolean isInDoaj;

  public static final String JSON_PROPERTY_IS_OA = "isOa";
  private Boolean isOa;

  public static final String JSON_PROPERTY_ISSUE = "issue";
  private String issue;

  public static final String JSON_PROPERTY_JOURNAL_ISSNS = "journalIssns";
  private List<String> journalIssns = null;

  public static final String JSON_PROPERTY_PAGINATION = "pagination";
  private String pagination;

  public static final String JSON_PROPERTY_PUBLISHER = "publisher";
  private String publisher;

  public static final String JSON_PROPERTY_SUBTITLE = "subtitle";
  private String subtitle;

  public static final String JSON_PROPERTY_TITLE = "title";
  private String title;

  /**
   * Type of the source, if COLLECTION then collection may contain collection data
   */
  public enum TypeEnum {
    PROCEEDINGS("PROCEEDINGS"),
    
    EVENT("EVENT"),
    
    BOOK("BOOK"),
    
    COLLECTION("COLLECTION"),
    
    ARTICLE("ARTICLE");

    private String value;

    TypeEnum(String value) {
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
    public static TypeEnum fromValue(String value) {
      for (TypeEnum b : TypeEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  public static final String JSON_PROPERTY_TYPE = "type";
  private TypeEnum type;


  public V2PublicationSource articleNumber(String articleNumber) {
    
    this.articleNumber = articleNumber;
    return this;
  }

   /**
   * Article number inside the source as complementary info
   * @return articleNumber
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Article number inside the source as complementary info")
  @JsonProperty(JSON_PROPERTY_ARTICLE_NUMBER)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getArticleNumber() {
    return articleNumber;
  }


  public void setArticleNumber(String articleNumber) {
    this.articleNumber = articleNumber;
  }


  public V2PublicationSource isInDoaj(Boolean isInDoaj) {
    
    this.isInDoaj = isInDoaj;
    return this;
  }

   /**
   * Get isInDoaj
   * @return isInDoaj
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_IS_IN_DOAJ)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Boolean getIsInDoaj() {
    return isInDoaj;
  }


  public void setIsInDoaj(Boolean isInDoaj) {
    this.isInDoaj = isInDoaj;
  }


  public V2PublicationSource isOa(Boolean isOa) {
    
    this.isOa = isOa;
    return this;
  }

   /**
   * Get isOa
   * @return isOa
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_IS_OA)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Boolean getIsOa() {
    return isOa;
  }


  public void setIsOa(Boolean isOa) {
    this.isOa = isOa;
  }


  public V2PublicationSource issue(String issue) {
    
    this.issue = issue;
    return this;
  }

   /**
   * Get issue
   * @return issue
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_ISSUE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getIssue() {
    return issue;
  }


  public void setIssue(String issue) {
    this.issue = issue;
  }


  public V2PublicationSource journalIssns(List<String> journalIssns) {
    
    this.journalIssns = journalIssns;
    return this;
  }

  public V2PublicationSource addJournalIssnsItem(String journalIssnsItem) {
    if (this.journalIssns == null) {
      this.journalIssns = new ArrayList<>();
    }
    this.journalIssns.add(journalIssnsItem);
    return this;
  }

   /**
   * Get journalIssns
   * @return journalIssns
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_JOURNAL_ISSNS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public List<String> getJournalIssns() {
    return journalIssns;
  }


  public void setJournalIssns(List<String> journalIssns) {
    this.journalIssns = journalIssns;
  }


  public V2PublicationSource pagination(String pagination) {
    
    this.pagination = pagination;
    return this;
  }

   /**
   * pages information
   * @return pagination
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "512-518", value = "pages information")
  @JsonProperty(JSON_PROPERTY_PAGINATION)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getPagination() {
    return pagination;
  }


  public void setPagination(String pagination) {
    this.pagination = pagination;
  }


  public V2PublicationSource publisher(String publisher) {
    
    this.publisher = publisher;
    return this;
  }

   /**
   * Get publisher
   * @return publisher
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_PUBLISHER)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getPublisher() {
    return publisher;
  }


  public void setPublisher(String publisher) {
    this.publisher = publisher;
  }


  public V2PublicationSource subtitle(String subtitle) {
    
    this.subtitle = subtitle;
    return this;
  }

   /**
   * Get subtitle
   * @return subtitle
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_SUBTITLE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getSubtitle() {
    return subtitle;
  }


  public void setSubtitle(String subtitle) {
    this.subtitle = subtitle;
  }


  public V2PublicationSource title(String title) {
    
    this.title = title;
    return this;
  }

   /**
   * Title of the source, may be null if part of a collection
   * @return title
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Title of the source, may be null if part of a collection")
  @JsonProperty(JSON_PROPERTY_TITLE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getTitle() {
    return title;
  }


  public void setTitle(String title) {
    this.title = title;
  }


  public V2PublicationSource type(TypeEnum type) {
    
    this.type = type;
    return this;
  }

   /**
   * Type of the source, if COLLECTION then collection may contain collection data
   * @return type
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Type of the source, if COLLECTION then collection may contain collection data")
  @JsonProperty(JSON_PROPERTY_TYPE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public TypeEnum getType() {
    return type;
  }


  public void setType(TypeEnum type) {
    this.type = type;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    V2PublicationSource v2PublicationSource = (V2PublicationSource) o;
    return Objects.equals(this.articleNumber, v2PublicationSource.articleNumber) &&
        Objects.equals(this.isInDoaj, v2PublicationSource.isInDoaj) &&
        Objects.equals(this.isOa, v2PublicationSource.isOa) &&
        Objects.equals(this.issue, v2PublicationSource.issue) &&
        Objects.equals(this.journalIssns, v2PublicationSource.journalIssns) &&
        Objects.equals(this.pagination, v2PublicationSource.pagination) &&
        Objects.equals(this.publisher, v2PublicationSource.publisher) &&
        Objects.equals(this.subtitle, v2PublicationSource.subtitle) &&
        Objects.equals(this.title, v2PublicationSource.title) &&
        Objects.equals(this.type, v2PublicationSource.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(articleNumber, isInDoaj, isOa, issue, journalIssns, pagination, publisher, subtitle, title, type);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class V2PublicationSource {\n");
    sb.append("    articleNumber: ").append(toIndentedString(articleNumber)).append("\n");
    sb.append("    isInDoaj: ").append(toIndentedString(isInDoaj)).append("\n");
    sb.append("    isOa: ").append(toIndentedString(isOa)).append("\n");
    sb.append("    issue: ").append(toIndentedString(issue)).append("\n");
    sb.append("    journalIssns: ").append(toIndentedString(journalIssns)).append("\n");
    sb.append("    pagination: ").append(toIndentedString(pagination)).append("\n");
    sb.append("    publisher: ").append(toIndentedString(publisher)).append("\n");
    sb.append("    subtitle: ").append(toIndentedString(subtitle)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
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

