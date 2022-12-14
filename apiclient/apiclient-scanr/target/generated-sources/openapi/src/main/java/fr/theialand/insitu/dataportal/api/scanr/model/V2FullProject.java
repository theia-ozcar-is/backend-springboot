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
import fr.theialand.insitu.dataportal.api.scanr.model.SimilarTypedObjectOfv2Project;
import fr.theialand.insitu.dataportal.api.scanr.model.V2Badge;
import fr.theialand.insitu.dataportal.api.scanr.model.V2Domain;
import fr.theialand.insitu.dataportal.api.scanr.model.V2PersonRelation;
import fr.theialand.insitu.dataportal.api.scanr.model.V2Project;
import fr.theialand.insitu.dataportal.api.scanr.model.V2ProjectAction;
import fr.theialand.insitu.dataportal.api.scanr.model.V2ProjectCall;
import fr.theialand.insitu.dataportal.api.scanr.model.V2ProjectStructureRelation;
import fr.theialand.insitu.dataportal.api.scanr.model.V2Publication;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * V2FullProject
 */
@JsonPropertyOrder({
  V2FullProject.JSON_PROPERTY_ACRONYM,
  V2FullProject.JSON_PROPERTY_ACTION,
  V2FullProject.JSON_PROPERTY_ASSOCIATED_PROJECTS,
  V2FullProject.JSON_PROPERTY_BADGES,
  V2FullProject.JSON_PROPERTY_BUDGET_FINANCED,
  V2FullProject.JSON_PROPERTY_BUDGET_TOTAL,
  V2FullProject.JSON_PROPERTY_CALL,
  V2FullProject.JSON_PROPERTY_CREATED_AT,
  V2FullProject.JSON_PROPERTY_CREATED_DATE,
  V2FullProject.JSON_PROPERTY_DESCRIPTION,
  V2FullProject.JSON_PROPERTY_DOMAINS,
  V2FullProject.JSON_PROPERTY_DURATION,
  V2FullProject.JSON_PROPERTY_END_DATE,
  V2FullProject.JSON_PROPERTY_FOCUS,
  V2FullProject.JSON_PROPERTY_ID,
  V2FullProject.JSON_PROPERTY_KEYWORDS,
  V2FullProject.JSON_PROPERTY_LABEL,
  V2FullProject.JSON_PROPERTY_LAST_UPDATED,
  V2FullProject.JSON_PROPERTY_PARTICIPANT_COUNT,
  V2FullProject.JSON_PROPERTY_PARTICIPANTS,
  V2FullProject.JSON_PROPERTY_PERSONS,
  V2FullProject.JSON_PROPERTY_PROJECT_URL,
  V2FullProject.JSON_PROPERTY_PUBLICATIONS,
  V2FullProject.JSON_PROPERTY_REMOVED_AT,
  V2FullProject.JSON_PROPERTY_SIGNATURE_DATE,
  V2FullProject.JSON_PROPERTY_SIMILAR_PROJECTS,
  V2FullProject.JSON_PROPERTY_START_DATE,
  V2FullProject.JSON_PROPERTY_TYPE,
  V2FullProject.JSON_PROPERTY_URL,
  V2FullProject.JSON_PROPERTY_YEAR
})
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-04-13T11:24:06.705650+02:00[Europe/Paris]")
public class V2FullProject {
  public static final String JSON_PROPERTY_ACRONYM = "acronym";
  private Map<String, String> acronym = null;

  public static final String JSON_PROPERTY_ACTION = "action";
  private V2ProjectAction action;

  public static final String JSON_PROPERTY_ASSOCIATED_PROJECTS = "associatedProjects";
  private List<V2Project> associatedProjects = null;

  public static final String JSON_PROPERTY_BADGES = "badges";
  private List<V2Badge> badges = null;

  public static final String JSON_PROPERTY_BUDGET_FINANCED = "budgetFinanced";
  private Float budgetFinanced;

  public static final String JSON_PROPERTY_BUDGET_TOTAL = "budgetTotal";
  private Float budgetTotal;

  public static final String JSON_PROPERTY_CALL = "call";
  private V2ProjectCall call;

  public static final String JSON_PROPERTY_CREATED_AT = "createdAt";
  private Date createdAt;

  public static final String JSON_PROPERTY_CREATED_DATE = "createdDate";
  private Date createdDate;

  public static final String JSON_PROPERTY_DESCRIPTION = "description";
  private Map<String, String> description = null;

  public static final String JSON_PROPERTY_DOMAINS = "domains";
  private List<V2Domain> domains = null;

  public static final String JSON_PROPERTY_DURATION = "duration";
  private Integer duration;

  public static final String JSON_PROPERTY_END_DATE = "endDate";
  private Date endDate;

  public static final String JSON_PROPERTY_FOCUS = "focus";
  private List<String> focus = null;

  public static final String JSON_PROPERTY_ID = "id";
  private String id;

  public static final String JSON_PROPERTY_KEYWORDS = "keywords";
  private Map<String, List> keywords = null;

  public static final String JSON_PROPERTY_LABEL = "label";
  private Map<String, String> label = null;

  public static final String JSON_PROPERTY_LAST_UPDATED = "lastUpdated";
  private Date lastUpdated;

  public static final String JSON_PROPERTY_PARTICIPANT_COUNT = "participantCount";
  private Integer participantCount;

  public static final String JSON_PROPERTY_PARTICIPANTS = "participants";
  private List<V2ProjectStructureRelation> participants = null;

  public static final String JSON_PROPERTY_PERSONS = "persons";
  private List<V2PersonRelation> persons = null;

  public static final String JSON_PROPERTY_PROJECT_URL = "projectUrl";
  private String projectUrl;

  public static final String JSON_PROPERTY_PUBLICATIONS = "publications";
  private List<V2Publication> publications = null;

  public static final String JSON_PROPERTY_REMOVED_AT = "removedAt";
  private Date removedAt;

  public static final String JSON_PROPERTY_SIGNATURE_DATE = "signatureDate";
  private Date signatureDate;

  public static final String JSON_PROPERTY_SIMILAR_PROJECTS = "similarProjects";
  private List<SimilarTypedObjectOfv2Project> similarProjects = null;

  public static final String JSON_PROPERTY_START_DATE = "startDate";
  private Date startDate;

  public static final String JSON_PROPERTY_TYPE = "type";
  private String type;

  public static final String JSON_PROPERTY_URL = "url";
  private String url;

  public static final String JSON_PROPERTY_YEAR = "year";
  private Integer year;


  public V2FullProject acronym(Map<String, String> acronym) {
    
    this.acronym = acronym;
    return this;
  }

  public V2FullProject putAcronymItem(String key, String acronymItem) {
    if (this.acronym == null) {
      this.acronym = new HashMap<>();
    }
    this.acronym.put(key, acronymItem);
    return this;
  }

   /**
   * i18n since v2
   * @return acronym
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "i18n since v2")
  @JsonProperty(JSON_PROPERTY_ACRONYM)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Map<String, String> getAcronym() {
    return acronym;
  }


  public void setAcronym(Map<String, String> acronym) {
    this.acronym = acronym;
  }


  public V2FullProject action(V2ProjectAction action) {
    
    this.action = action;
    return this;
  }

   /**
   * Get action
   * @return action
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_ACTION)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public V2ProjectAction getAction() {
    return action;
  }


  public void setAction(V2ProjectAction action) {
    this.action = action;
  }


  public V2FullProject associatedProjects(List<V2Project> associatedProjects) {
    
    this.associatedProjects = associatedProjects;
    return this;
  }

  public V2FullProject addAssociatedProjectsItem(V2Project associatedProjectsItem) {
    if (this.associatedProjects == null) {
      this.associatedProjects = new ArrayList<>();
    }
    this.associatedProjects.add(associatedProjectsItem);
    return this;
  }

   /**
   * in FullProject Light fields, outside only id
   * @return associatedProjects
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "in FullProject Light fields, outside only id")
  @JsonProperty(JSON_PROPERTY_ASSOCIATED_PROJECTS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public List<V2Project> getAssociatedProjects() {
    return associatedProjects;
  }


  public void setAssociatedProjects(List<V2Project> associatedProjects) {
    this.associatedProjects = associatedProjects;
  }


  public V2FullProject badges(List<V2Badge> badges) {
    
    this.badges = badges;
    return this;
  }

  public V2FullProject addBadgesItem(V2Badge badgesItem) {
    if (this.badges == null) {
      this.badges = new ArrayList<>();
    }
    this.badges.add(badgesItem);
    return this;
  }

   /**
   * Get badges
   * @return badges
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_BADGES)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public List<V2Badge> getBadges() {
    return badges;
  }


  public void setBadges(List<V2Badge> badges) {
    this.badges = badges;
  }


  public V2FullProject budgetFinanced(Float budgetFinanced) {
    
    this.budgetFinanced = budgetFinanced;
    return this;
  }

   /**
   * since v2
   * @return budgetFinanced
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "since v2")
  @JsonProperty(JSON_PROPERTY_BUDGET_FINANCED)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Float getBudgetFinanced() {
    return budgetFinanced;
  }


  public void setBudgetFinanced(Float budgetFinanced) {
    this.budgetFinanced = budgetFinanced;
  }


  public V2FullProject budgetTotal(Float budgetTotal) {
    
    this.budgetTotal = budgetTotal;
    return this;
  }

   /**
   * budget before v2
   * @return budgetTotal
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "budget before v2")
  @JsonProperty(JSON_PROPERTY_BUDGET_TOTAL)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Float getBudgetTotal() {
    return budgetTotal;
  }


  public void setBudgetTotal(Float budgetTotal) {
    this.budgetTotal = budgetTotal;
  }


  public V2FullProject call(V2ProjectCall call) {
    
    this.call = call;
    return this;
  }

   /**
   * Get call
   * @return call
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_CALL)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public V2ProjectCall getCall() {
    return call;
  }


  public void setCall(V2ProjectCall call) {
    this.call = call;
  }


  public V2FullProject createdAt(Date createdAt) {
    
    this.createdAt = createdAt;
    return this;
  }

   /**
   * Get createdAt
   * @return createdAt
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_CREATED_AT)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Date getCreatedAt() {
    return createdAt;
  }


  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }


  public V2FullProject createdDate(Date createdDate) {
    
    this.createdDate = createdDate;
    return this;
  }

   /**
   * Get createdDate
   * @return createdDate
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_CREATED_DATE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Date getCreatedDate() {
    return createdDate;
  }


  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }


  public V2FullProject description(Map<String, String> description) {
    
    this.description = description;
    return this;
  }

  public V2FullProject putDescriptionItem(String key, String descriptionItem) {
    if (this.description == null) {
      this.description = new HashMap<>();
    }
    this.description.put(key, descriptionItem);
    return this;
  }

   /**
   * i18n since v2
   * @return description
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "i18n since v2")
  @JsonProperty(JSON_PROPERTY_DESCRIPTION)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Map<String, String> getDescription() {
    return description;
  }


  public void setDescription(Map<String, String> description) {
    this.description = description;
  }


  public V2FullProject domains(List<V2Domain> domains) {
    
    this.domains = domains;
    return this;
  }

  public V2FullProject addDomainsItem(V2Domain domainsItem) {
    if (this.domains == null) {
      this.domains = new ArrayList<>();
    }
    this.domains.add(domainsItem);
    return this;
  }

   /**
   * Get domains
   * @return domains
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_DOMAINS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public List<V2Domain> getDomains() {
    return domains;
  }


  public void setDomains(List<V2Domain> domains) {
    this.domains = domains;
  }


  public V2FullProject duration(Integer duration) {
    
    this.duration = duration;
    return this;
  }

   /**
   * in month
   * @return duration
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "in month")
  @JsonProperty(JSON_PROPERTY_DURATION)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Integer getDuration() {
    return duration;
  }


  public void setDuration(Integer duration) {
    this.duration = duration;
  }


  public V2FullProject endDate(Date endDate) {
    
    this.endDate = endDate;
    return this;
  }

   /**
   * since v2
   * @return endDate
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "since v2")
  @JsonProperty(JSON_PROPERTY_END_DATE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Date getEndDate() {
    return endDate;
  }


  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }


  public V2FullProject focus(List<String> focus) {
    
    this.focus = focus;
    return this;
  }

  public V2FullProject addFocusItem(String focusItem) {
    if (this.focus == null) {
      this.focus = new ArrayList<>();
    }
    this.focus.add(focusItem);
    return this;
  }

   /**
   * since v2
   * @return focus
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "since v2")
  @JsonProperty(JSON_PROPERTY_FOCUS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public List<String> getFocus() {
    return focus;
  }


  public void setFocus(List<String> focus) {
    this.focus = focus;
  }


  public V2FullProject id(String id) {
    
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

  public String getId() {
    return id;
  }


  public void setId(String id) {
    this.id = id;
  }


  public V2FullProject keywords(Map<String, List> keywords) {
    
    this.keywords = keywords;
    return this;
  }

  public V2FullProject putKeywordsItem(String key, List keywordsItem) {
    if (this.keywords == null) {
      this.keywords = new HashMap<>();
    }
    this.keywords.put(key, keywordsItem);
    return this;
  }

   /**
   * une liste de termes par langue (fr, en), fourni par MESRI
   * @return keywords
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "une liste de termes par langue (fr, en), fourni par MESRI")
  @JsonProperty(JSON_PROPERTY_KEYWORDS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Map<String, List> getKeywords() {
    return keywords;
  }


  public void setKeywords(Map<String, List> keywords) {
    this.keywords = keywords;
  }


  public V2FullProject label(Map<String, String> label) {
    
    this.label = label;
    return this;
  }

  public V2FullProject putLabelItem(String key, String labelItem) {
    if (this.label == null) {
      this.label = new HashMap<>();
    }
    this.label.put(key, labelItem);
    return this;
  }

   /**
   * i18n since v2
   * @return label
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "i18n since v2")
  @JsonProperty(JSON_PROPERTY_LABEL)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Map<String, String> getLabel() {
    return label;
  }


  public void setLabel(Map<String, String> label) {
    this.label = label;
  }


  public V2FullProject lastUpdated(Date lastUpdated) {
    
    this.lastUpdated = lastUpdated;
    return this;
  }

   /**
   * Get lastUpdated
   * @return lastUpdated
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_LAST_UPDATED)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Date getLastUpdated() {
    return lastUpdated;
  }


  public void setLastUpdated(Date lastUpdated) {
    this.lastUpdated = lastUpdated;
  }


  public V2FullProject participantCount(Integer participantCount) {
    
    this.participantCount = participantCount;
    return this;
  }

   /**
   * Get participantCount
   * @return participantCount
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_PARTICIPANT_COUNT)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Integer getParticipantCount() {
    return participantCount;
  }


  public void setParticipantCount(Integer participantCount) {
    this.participantCount = participantCount;
  }


  public V2FullProject participants(List<V2ProjectStructureRelation> participants) {
    
    this.participants = participants;
    return this;
  }

  public V2FullProject addParticipantsItem(V2ProjectStructureRelation participantsItem) {
    if (this.participants == null) {
      this.participants = new ArrayList<>();
    }
    this.participants.add(participantsItem);
    return this;
  }

   /**
   * project&#39;s participating structures (scanr structures or external structures). In FullStructure Light fields, outside only id
   * @return participants
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "project's participating structures (scanr structures or external structures). In FullStructure Light fields, outside only id")
  @JsonProperty(JSON_PROPERTY_PARTICIPANTS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public List<V2ProjectStructureRelation> getParticipants() {
    return participants;
  }


  public void setParticipants(List<V2ProjectStructureRelation> participants) {
    this.participants = participants;
  }


  public V2FullProject persons(List<V2PersonRelation> persons) {
    
    this.persons = persons;
    return this;
  }

  public V2FullProject addPersonsItem(V2PersonRelation personsItem) {
    if (this.persons == null) {
      this.persons = new ArrayList<>();
    }
    this.persons.add(personsItem);
    return this;
  }

   /**
   * since v2. Only id, firstName, lastName, email, plus Light fields in FullProject
   * @return persons
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "since v2. Only id, firstName, lastName, email, plus Light fields in FullProject")
  @JsonProperty(JSON_PROPERTY_PERSONS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public List<V2PersonRelation> getPersons() {
    return persons;
  }


  public void setPersons(List<V2PersonRelation> persons) {
    this.persons = persons;
  }


  public V2FullProject projectUrl(String projectUrl) {
    
    this.projectUrl = projectUrl;
    return this;
  }

   /**
   * since v2
   * @return projectUrl
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "since v2")
  @JsonProperty(JSON_PROPERTY_PROJECT_URL)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getProjectUrl() {
    return projectUrl;
  }


  public void setProjectUrl(String projectUrl) {
    this.projectUrl = projectUrl;
  }


  public V2FullProject publications(List<V2Publication> publications) {
    
    this.publications = publications;
    return this;
  }

  public V2FullProject addPublicationsItem(V2Publication publicationsItem) {
    if (this.publications == null) {
      this.publications = new ArrayList<>();
    }
    this.publications.add(publicationsItem);
    return this;
  }

   /**
   * Only in Full. Champ aliment?? par le champ \&quot;projects\&quot; pr??sent dans le sch??ma \&quot;Publication\&quot; (recherche des publications li??es au projet)
   * @return publications
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Only in Full. Champ aliment?? par le champ \"projects\" pr??sent dans le sch??ma \"Publication\" (recherche des publications li??es au projet)")
  @JsonProperty(JSON_PROPERTY_PUBLICATIONS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public List<V2Publication> getPublications() {
    return publications;
  }


  public void setPublications(List<V2Publication> publications) {
    this.publications = publications;
  }


  public V2FullProject removedAt(Date removedAt) {
    
    this.removedAt = removedAt;
    return this;
  }

   /**
   * Get removedAt
   * @return removedAt
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_REMOVED_AT)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Date getRemovedAt() {
    return removedAt;
  }


  public void setRemovedAt(Date removedAt) {
    this.removedAt = removedAt;
  }


  public V2FullProject signatureDate(Date signatureDate) {
    
    this.signatureDate = signatureDate;
    return this;
  }

   /**
   * since v2
   * @return signatureDate
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "since v2")
  @JsonProperty(JSON_PROPERTY_SIGNATURE_DATE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Date getSignatureDate() {
    return signatureDate;
  }


  public void setSignatureDate(Date signatureDate) {
    this.signatureDate = signatureDate;
  }


  public V2FullProject similarProjects(List<SimilarTypedObjectOfv2Project> similarProjects) {
    
    this.similarProjects = similarProjects;
    return this;
  }

  public V2FullProject addSimilarProjectsItem(SimilarTypedObjectOfv2Project similarProjectsItem) {
    if (this.similarProjects == null) {
      this.similarProjects = new ArrayList<>();
    }
    this.similarProjects.add(similarProjectsItem);
    return this;
  }

   /**
   * Get similarProjects
   * @return similarProjects
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_SIMILAR_PROJECTS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public List<SimilarTypedObjectOfv2Project> getSimilarProjects() {
    return similarProjects;
  }


  public void setSimilarProjects(List<SimilarTypedObjectOfv2Project> similarProjects) {
    this.similarProjects = similarProjects;
  }


  public V2FullProject startDate(Date startDate) {
    
    this.startDate = startDate;
    return this;
  }

   /**
   * since v2
   * @return startDate
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "since v2")
  @JsonProperty(JSON_PROPERTY_START_DATE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Date getStartDate() {
    return startDate;
  }


  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }


  public V2FullProject type(String type) {
    
    this.type = type;
    return this;
  }

   /**
   * Get type
   * @return type
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_TYPE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getType() {
    return type;
  }


  public void setType(String type) {
    this.type = type;
  }


  public V2FullProject url(String url) {
    
    this.url = url;
    return this;
  }

   /**
   * project&#39;s url
   * @return url
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "project's url")
  @JsonProperty(JSON_PROPERTY_URL)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getUrl() {
    return url;
  }


  public void setUrl(String url) {
    this.url = url;
  }


  public V2FullProject year(Integer year) {
    
    this.year = year;
    return this;
  }

   /**
   * project&#39;s year
   * @return year
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "project's year")
  @JsonProperty(JSON_PROPERTY_YEAR)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Integer getYear() {
    return year;
  }


  public void setYear(Integer year) {
    this.year = year;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    V2FullProject v2FullProject = (V2FullProject) o;
    return Objects.equals(this.acronym, v2FullProject.acronym) &&
        Objects.equals(this.action, v2FullProject.action) &&
        Objects.equals(this.associatedProjects, v2FullProject.associatedProjects) &&
        Objects.equals(this.badges, v2FullProject.badges) &&
        Objects.equals(this.budgetFinanced, v2FullProject.budgetFinanced) &&
        Objects.equals(this.budgetTotal, v2FullProject.budgetTotal) &&
        Objects.equals(this.call, v2FullProject.call) &&
        Objects.equals(this.createdAt, v2FullProject.createdAt) &&
        Objects.equals(this.createdDate, v2FullProject.createdDate) &&
        Objects.equals(this.description, v2FullProject.description) &&
        Objects.equals(this.domains, v2FullProject.domains) &&
        Objects.equals(this.duration, v2FullProject.duration) &&
        Objects.equals(this.endDate, v2FullProject.endDate) &&
        Objects.equals(this.focus, v2FullProject.focus) &&
        Objects.equals(this.id, v2FullProject.id) &&
        Objects.equals(this.keywords, v2FullProject.keywords) &&
        Objects.equals(this.label, v2FullProject.label) &&
        Objects.equals(this.lastUpdated, v2FullProject.lastUpdated) &&
        Objects.equals(this.participantCount, v2FullProject.participantCount) &&
        Objects.equals(this.participants, v2FullProject.participants) &&
        Objects.equals(this.persons, v2FullProject.persons) &&
        Objects.equals(this.projectUrl, v2FullProject.projectUrl) &&
        Objects.equals(this.publications, v2FullProject.publications) &&
        Objects.equals(this.removedAt, v2FullProject.removedAt) &&
        Objects.equals(this.signatureDate, v2FullProject.signatureDate) &&
        Objects.equals(this.similarProjects, v2FullProject.similarProjects) &&
        Objects.equals(this.startDate, v2FullProject.startDate) &&
        Objects.equals(this.type, v2FullProject.type) &&
        Objects.equals(this.url, v2FullProject.url) &&
        Objects.equals(this.year, v2FullProject.year);
  }

  @Override
  public int hashCode() {
    return Objects.hash(acronym, action, associatedProjects, badges, budgetFinanced, budgetTotal, call, createdAt, createdDate, description, domains, duration, endDate, focus, id, keywords, label, lastUpdated, participantCount, participants, persons, projectUrl, publications, removedAt, signatureDate, similarProjects, startDate, type, url, year);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class V2FullProject {\n");
    sb.append("    acronym: ").append(toIndentedString(acronym)).append("\n");
    sb.append("    action: ").append(toIndentedString(action)).append("\n");
    sb.append("    associatedProjects: ").append(toIndentedString(associatedProjects)).append("\n");
    sb.append("    badges: ").append(toIndentedString(badges)).append("\n");
    sb.append("    budgetFinanced: ").append(toIndentedString(budgetFinanced)).append("\n");
    sb.append("    budgetTotal: ").append(toIndentedString(budgetTotal)).append("\n");
    sb.append("    call: ").append(toIndentedString(call)).append("\n");
    sb.append("    createdAt: ").append(toIndentedString(createdAt)).append("\n");
    sb.append("    createdDate: ").append(toIndentedString(createdDate)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    domains: ").append(toIndentedString(domains)).append("\n");
    sb.append("    duration: ").append(toIndentedString(duration)).append("\n");
    sb.append("    endDate: ").append(toIndentedString(endDate)).append("\n");
    sb.append("    focus: ").append(toIndentedString(focus)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    keywords: ").append(toIndentedString(keywords)).append("\n");
    sb.append("    label: ").append(toIndentedString(label)).append("\n");
    sb.append("    lastUpdated: ").append(toIndentedString(lastUpdated)).append("\n");
    sb.append("    participantCount: ").append(toIndentedString(participantCount)).append("\n");
    sb.append("    participants: ").append(toIndentedString(participants)).append("\n");
    sb.append("    persons: ").append(toIndentedString(persons)).append("\n");
    sb.append("    projectUrl: ").append(toIndentedString(projectUrl)).append("\n");
    sb.append("    publications: ").append(toIndentedString(publications)).append("\n");
    sb.append("    removedAt: ").append(toIndentedString(removedAt)).append("\n");
    sb.append("    signatureDate: ").append(toIndentedString(signatureDate)).append("\n");
    sb.append("    similarProjects: ").append(toIndentedString(similarProjects)).append("\n");
    sb.append("    startDate: ").append(toIndentedString(startDate)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    url: ").append(toIndentedString(url)).append("\n");
    sb.append("    year: ").append(toIndentedString(year)).append("\n");
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

