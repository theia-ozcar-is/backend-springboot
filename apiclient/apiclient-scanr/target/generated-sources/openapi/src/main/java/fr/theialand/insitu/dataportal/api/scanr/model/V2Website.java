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
import fr.theialand.insitu.dataportal.api.scanr.model.V2RssFeed;
import fr.theialand.insitu.dataportal.api.scanr.model.V2SocialAccount;
import fr.theialand.insitu.dataportal.api.scanr.model.WebPage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Same as v1
 */
@ApiModel(description = "Same as v1")
@JsonPropertyOrder({
  V2Website.JSON_PROPERTY_BASE_U_R_L,
  V2Website.JSON_PROPERTY_CANONICAL,
  V2Website.JSON_PROPERTY_CONTACT_FORMS,
  V2Website.JSON_PROPERTY_CRAWL_MODE,
  V2Website.JSON_PROPERTY_CREATED_DATE,
  V2Website.JSON_PROPERTY_DAILYMOTION,
  V2Website.JSON_PROPERTY_DESCRIPTION,
  V2Website.JSON_PROPERTY_ECOMMERCE,
  V2Website.JSON_PROPERTY_EXTRACTED_PROJECTS,
  V2Website.JSON_PROPERTY_EXTRACTED_PUBLICATIONS,
  V2Website.JSON_PROPERTY_FACEBOOK,
  V2Website.JSON_PROPERTY_GOOGLE_PLUS,
  V2Website.JSON_PROPERTY_ID,
  V2Website.JSON_PROPERTY_INSTAGRAM,
  V2Website.JSON_PROPERTY_LAST_COMPLETION,
  V2Website.JSON_PROPERTY_LAST_UPDATED,
  V2Website.JSON_PROPERTY_LINKED_IN,
  V2Website.JSON_PROPERTY_META_DESCRIPTION,
  V2Website.JSON_PROPERTY_MOBILE,
  V2Website.JSON_PROPERTY_MONITORING,
  V2Website.JSON_PROPERTY_PAGE_COUNT,
  V2Website.JSON_PROPERTY_PLATFORMS,
  V2Website.JSON_PROPERTY_QUALITY,
  V2Website.JSON_PROPERTY_RESOLVED_PUBLICATIONS,
  V2Website.JSON_PROPERTY_RESPONSIVE,
  V2Website.JSON_PROPERTY_RSS,
  V2Website.JSON_PROPERTY_TWITTER,
  V2Website.JSON_PROPERTY_VIADEO,
  V2Website.JSON_PROPERTY_VIMEO,
  V2Website.JSON_PROPERTY_WEB_PAGES,
  V2Website.JSON_PROPERTY_YOUTUBE
})
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-04-13T11:24:06.705650+02:00[Europe/Paris]")
public class V2Website {
  public static final String JSON_PROPERTY_BASE_U_R_L = "baseURL";
  private String baseURL;

  public static final String JSON_PROPERTY_CANONICAL = "canonical";
  private Boolean canonical;

  public static final String JSON_PROPERTY_CONTACT_FORMS = "contactForms";
  private List<String> contactForms = null;

  /**
   * Gets or Sets crawlMode
   */
  public enum CrawlModeEnum {
    SINGLE_PAGE("SINGLE_PAGE"),
    
    SUBPATH("SUBPATH"),
    
    FULL_DOMAIN("FULL_DOMAIN");

    private String value;

    CrawlModeEnum(String value) {
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
    public static CrawlModeEnum fromValue(String value) {
      for (CrawlModeEnum b : CrawlModeEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  public static final String JSON_PROPERTY_CRAWL_MODE = "crawlMode";
  private CrawlModeEnum crawlMode;

  public static final String JSON_PROPERTY_CREATED_DATE = "createdDate";
  private Date createdDate;

  public static final String JSON_PROPERTY_DAILYMOTION = "dailymotion";
  private List<V2SocialAccount> dailymotion = null;

  public static final String JSON_PROPERTY_DESCRIPTION = "description";
  private String description;

  public static final String JSON_PROPERTY_ECOMMERCE = "ecommerce";
  private Boolean ecommerce;

  public static final String JSON_PROPERTY_EXTRACTED_PROJECTS = "extractedProjects";
  private List<String> extractedProjects = null;

  public static final String JSON_PROPERTY_EXTRACTED_PUBLICATIONS = "extractedPublications";
  private List<String> extractedPublications = null;

  public static final String JSON_PROPERTY_FACEBOOK = "facebook";
  private List<V2SocialAccount> facebook = null;

  public static final String JSON_PROPERTY_GOOGLE_PLUS = "googlePlus";
  private List<V2SocialAccount> googlePlus = null;

  public static final String JSON_PROPERTY_ID = "id";
  private String id;

  public static final String JSON_PROPERTY_INSTAGRAM = "instagram";
  private List<V2SocialAccount> instagram = null;

  public static final String JSON_PROPERTY_LAST_COMPLETION = "lastCompletion";
  private Date lastCompletion;

  public static final String JSON_PROPERTY_LAST_UPDATED = "lastUpdated";
  private Date lastUpdated;

  public static final String JSON_PROPERTY_LINKED_IN = "linkedIn";
  private List<V2SocialAccount> linkedIn = null;

  public static final String JSON_PROPERTY_META_DESCRIPTION = "metaDescription";
  private String metaDescription;

  public static final String JSON_PROPERTY_MOBILE = "mobile";
  private Boolean mobile;

  public static final String JSON_PROPERTY_MONITORING = "monitoring";
  private List<String> monitoring = null;

  public static final String JSON_PROPERTY_PAGE_COUNT = "pageCount";
  private Integer pageCount;

  public static final String JSON_PROPERTY_PLATFORMS = "platforms";
  private List<String> platforms = null;

  public static final String JSON_PROPERTY_QUALITY = "quality";
  private Double quality;

  public static final String JSON_PROPERTY_RESOLVED_PUBLICATIONS = "resolvedPublications";
  private List<String> resolvedPublications = null;

  public static final String JSON_PROPERTY_RESPONSIVE = "responsive";
  private Boolean responsive;

  public static final String JSON_PROPERTY_RSS = "rss";
  private List<V2RssFeed> rss = null;

  public static final String JSON_PROPERTY_TWITTER = "twitter";
  private List<V2SocialAccount> twitter = null;

  public static final String JSON_PROPERTY_VIADEO = "viadeo";
  private List<V2SocialAccount> viadeo = null;

  public static final String JSON_PROPERTY_VIMEO = "vimeo";
  private List<V2SocialAccount> vimeo = null;

  public static final String JSON_PROPERTY_WEB_PAGES = "webPages";
  private List<WebPage> webPages = null;

  public static final String JSON_PROPERTY_YOUTUBE = "youtube";
  private List<V2SocialAccount> youtube = null;


  public V2Website baseURL(String baseURL) {
    
    this.baseURL = baseURL;
    return this;
  }

   /**
   * Get baseURL
   * @return baseURL
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_BASE_U_R_L)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getBaseURL() {
    return baseURL;
  }


  public void setBaseURL(String baseURL) {
    this.baseURL = baseURL;
  }


  public V2Website canonical(Boolean canonical) {
    
    this.canonical = canonical;
    return this;
  }

   /**
   * Get canonical
   * @return canonical
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_CANONICAL)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Boolean getCanonical() {
    return canonical;
  }


  public void setCanonical(Boolean canonical) {
    this.canonical = canonical;
  }


  public V2Website contactForms(List<String> contactForms) {
    
    this.contactForms = contactForms;
    return this;
  }

  public V2Website addContactFormsItem(String contactFormsItem) {
    if (this.contactForms == null) {
      this.contactForms = new ArrayList<>();
    }
    this.contactForms.add(contactFormsItem);
    return this;
  }

   /**
   * Get contactForms
   * @return contactForms
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_CONTACT_FORMS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public List<String> getContactForms() {
    return contactForms;
  }


  public void setContactForms(List<String> contactForms) {
    this.contactForms = contactForms;
  }


  public V2Website crawlMode(CrawlModeEnum crawlMode) {
    
    this.crawlMode = crawlMode;
    return this;
  }

   /**
   * Get crawlMode
   * @return crawlMode
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_CRAWL_MODE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public CrawlModeEnum getCrawlMode() {
    return crawlMode;
  }


  public void setCrawlMode(CrawlModeEnum crawlMode) {
    this.crawlMode = crawlMode;
  }


  public V2Website createdDate(Date createdDate) {
    
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


  public V2Website dailymotion(List<V2SocialAccount> dailymotion) {
    
    this.dailymotion = dailymotion;
    return this;
  }

  public V2Website addDailymotionItem(V2SocialAccount dailymotionItem) {
    if (this.dailymotion == null) {
      this.dailymotion = new ArrayList<>();
    }
    this.dailymotion.add(dailymotionItem);
    return this;
  }

   /**
   * Get dailymotion
   * @return dailymotion
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_DAILYMOTION)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public List<V2SocialAccount> getDailymotion() {
    return dailymotion;
  }


  public void setDailymotion(List<V2SocialAccount> dailymotion) {
    this.dailymotion = dailymotion;
  }


  public V2Website description(String description) {
    
    this.description = description;
    return this;
  }

   /**
   * Get description
   * @return description
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_DESCRIPTION)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getDescription() {
    return description;
  }


  public void setDescription(String description) {
    this.description = description;
  }


  public V2Website ecommerce(Boolean ecommerce) {
    
    this.ecommerce = ecommerce;
    return this;
  }

   /**
   * Get ecommerce
   * @return ecommerce
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_ECOMMERCE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Boolean getEcommerce() {
    return ecommerce;
  }


  public void setEcommerce(Boolean ecommerce) {
    this.ecommerce = ecommerce;
  }


  public V2Website extractedProjects(List<String> extractedProjects) {
    
    this.extractedProjects = extractedProjects;
    return this;
  }

  public V2Website addExtractedProjectsItem(String extractedProjectsItem) {
    if (this.extractedProjects == null) {
      this.extractedProjects = new ArrayList<>();
    }
    this.extractedProjects.add(extractedProjectsItem);
    return this;
  }

   /**
   * Get extractedProjects
   * @return extractedProjects
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_EXTRACTED_PROJECTS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public List<String> getExtractedProjects() {
    return extractedProjects;
  }


  public void setExtractedProjects(List<String> extractedProjects) {
    this.extractedProjects = extractedProjects;
  }


  public V2Website extractedPublications(List<String> extractedPublications) {
    
    this.extractedPublications = extractedPublications;
    return this;
  }

  public V2Website addExtractedPublicationsItem(String extractedPublicationsItem) {
    if (this.extractedPublications == null) {
      this.extractedPublications = new ArrayList<>();
    }
    this.extractedPublications.add(extractedPublicationsItem);
    return this;
  }

   /**
   * Get extractedPublications
   * @return extractedPublications
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_EXTRACTED_PUBLICATIONS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public List<String> getExtractedPublications() {
    return extractedPublications;
  }


  public void setExtractedPublications(List<String> extractedPublications) {
    this.extractedPublications = extractedPublications;
  }


  public V2Website facebook(List<V2SocialAccount> facebook) {
    
    this.facebook = facebook;
    return this;
  }

  public V2Website addFacebookItem(V2SocialAccount facebookItem) {
    if (this.facebook == null) {
      this.facebook = new ArrayList<>();
    }
    this.facebook.add(facebookItem);
    return this;
  }

   /**
   * Get facebook
   * @return facebook
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_FACEBOOK)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public List<V2SocialAccount> getFacebook() {
    return facebook;
  }


  public void setFacebook(List<V2SocialAccount> facebook) {
    this.facebook = facebook;
  }


  public V2Website googlePlus(List<V2SocialAccount> googlePlus) {
    
    this.googlePlus = googlePlus;
    return this;
  }

  public V2Website addGooglePlusItem(V2SocialAccount googlePlusItem) {
    if (this.googlePlus == null) {
      this.googlePlus = new ArrayList<>();
    }
    this.googlePlus.add(googlePlusItem);
    return this;
  }

   /**
   * Get googlePlus
   * @return googlePlus
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_GOOGLE_PLUS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public List<V2SocialAccount> getGooglePlus() {
    return googlePlus;
  }


  public void setGooglePlus(List<V2SocialAccount> googlePlus) {
    this.googlePlus = googlePlus;
  }


  public V2Website id(String id) {
    
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


  public V2Website instagram(List<V2SocialAccount> instagram) {
    
    this.instagram = instagram;
    return this;
  }

  public V2Website addInstagramItem(V2SocialAccount instagramItem) {
    if (this.instagram == null) {
      this.instagram = new ArrayList<>();
    }
    this.instagram.add(instagramItem);
    return this;
  }

   /**
   * Get instagram
   * @return instagram
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_INSTAGRAM)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public List<V2SocialAccount> getInstagram() {
    return instagram;
  }


  public void setInstagram(List<V2SocialAccount> instagram) {
    this.instagram = instagram;
  }


  public V2Website lastCompletion(Date lastCompletion) {
    
    this.lastCompletion = lastCompletion;
    return this;
  }

   /**
   * Get lastCompletion
   * @return lastCompletion
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_LAST_COMPLETION)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Date getLastCompletion() {
    return lastCompletion;
  }


  public void setLastCompletion(Date lastCompletion) {
    this.lastCompletion = lastCompletion;
  }


  public V2Website lastUpdated(Date lastUpdated) {
    
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


  public V2Website linkedIn(List<V2SocialAccount> linkedIn) {
    
    this.linkedIn = linkedIn;
    return this;
  }

  public V2Website addLinkedInItem(V2SocialAccount linkedInItem) {
    if (this.linkedIn == null) {
      this.linkedIn = new ArrayList<>();
    }
    this.linkedIn.add(linkedInItem);
    return this;
  }

   /**
   * Get linkedIn
   * @return linkedIn
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_LINKED_IN)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public List<V2SocialAccount> getLinkedIn() {
    return linkedIn;
  }


  public void setLinkedIn(List<V2SocialAccount> linkedIn) {
    this.linkedIn = linkedIn;
  }


  public V2Website metaDescription(String metaDescription) {
    
    this.metaDescription = metaDescription;
    return this;
  }

   /**
   * Get metaDescription
   * @return metaDescription
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_META_DESCRIPTION)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getMetaDescription() {
    return metaDescription;
  }


  public void setMetaDescription(String metaDescription) {
    this.metaDescription = metaDescription;
  }


  public V2Website mobile(Boolean mobile) {
    
    this.mobile = mobile;
    return this;
  }

   /**
   * Get mobile
   * @return mobile
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_MOBILE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Boolean getMobile() {
    return mobile;
  }


  public void setMobile(Boolean mobile) {
    this.mobile = mobile;
  }


  public V2Website monitoring(List<String> monitoring) {
    
    this.monitoring = monitoring;
    return this;
  }

  public V2Website addMonitoringItem(String monitoringItem) {
    if (this.monitoring == null) {
      this.monitoring = new ArrayList<>();
    }
    this.monitoring.add(monitoringItem);
    return this;
  }

   /**
   * Get monitoring
   * @return monitoring
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_MONITORING)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public List<String> getMonitoring() {
    return monitoring;
  }


  public void setMonitoring(List<String> monitoring) {
    this.monitoring = monitoring;
  }


  public V2Website pageCount(Integer pageCount) {
    
    this.pageCount = pageCount;
    return this;
  }

   /**
   * Get pageCount
   * @return pageCount
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_PAGE_COUNT)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Integer getPageCount() {
    return pageCount;
  }


  public void setPageCount(Integer pageCount) {
    this.pageCount = pageCount;
  }


  public V2Website platforms(List<String> platforms) {
    
    this.platforms = platforms;
    return this;
  }

  public V2Website addPlatformsItem(String platformsItem) {
    if (this.platforms == null) {
      this.platforms = new ArrayList<>();
    }
    this.platforms.add(platformsItem);
    return this;
  }

   /**
   * Get platforms
   * @return platforms
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_PLATFORMS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public List<String> getPlatforms() {
    return platforms;
  }


  public void setPlatforms(List<String> platforms) {
    this.platforms = platforms;
  }


  public V2Website quality(Double quality) {
    
    this.quality = quality;
    return this;
  }

   /**
   * Get quality
   * @return quality
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_QUALITY)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Double getQuality() {
    return quality;
  }


  public void setQuality(Double quality) {
    this.quality = quality;
  }


  public V2Website resolvedPublications(List<String> resolvedPublications) {
    
    this.resolvedPublications = resolvedPublications;
    return this;
  }

  public V2Website addResolvedPublicationsItem(String resolvedPublicationsItem) {
    if (this.resolvedPublications == null) {
      this.resolvedPublications = new ArrayList<>();
    }
    this.resolvedPublications.add(resolvedPublicationsItem);
    return this;
  }

   /**
   * Get resolvedPublications
   * @return resolvedPublications
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_RESOLVED_PUBLICATIONS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public List<String> getResolvedPublications() {
    return resolvedPublications;
  }


  public void setResolvedPublications(List<String> resolvedPublications) {
    this.resolvedPublications = resolvedPublications;
  }


  public V2Website responsive(Boolean responsive) {
    
    this.responsive = responsive;
    return this;
  }

   /**
   * Get responsive
   * @return responsive
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_RESPONSIVE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Boolean getResponsive() {
    return responsive;
  }


  public void setResponsive(Boolean responsive) {
    this.responsive = responsive;
  }


  public V2Website rss(List<V2RssFeed> rss) {
    
    this.rss = rss;
    return this;
  }

  public V2Website addRssItem(V2RssFeed rssItem) {
    if (this.rss == null) {
      this.rss = new ArrayList<>();
    }
    this.rss.add(rssItem);
    return this;
  }

   /**
   * Get rss
   * @return rss
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_RSS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public List<V2RssFeed> getRss() {
    return rss;
  }


  public void setRss(List<V2RssFeed> rss) {
    this.rss = rss;
  }


  public V2Website twitter(List<V2SocialAccount> twitter) {
    
    this.twitter = twitter;
    return this;
  }

  public V2Website addTwitterItem(V2SocialAccount twitterItem) {
    if (this.twitter == null) {
      this.twitter = new ArrayList<>();
    }
    this.twitter.add(twitterItem);
    return this;
  }

   /**
   * Get twitter
   * @return twitter
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_TWITTER)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public List<V2SocialAccount> getTwitter() {
    return twitter;
  }


  public void setTwitter(List<V2SocialAccount> twitter) {
    this.twitter = twitter;
  }


  public V2Website viadeo(List<V2SocialAccount> viadeo) {
    
    this.viadeo = viadeo;
    return this;
  }

  public V2Website addViadeoItem(V2SocialAccount viadeoItem) {
    if (this.viadeo == null) {
      this.viadeo = new ArrayList<>();
    }
    this.viadeo.add(viadeoItem);
    return this;
  }

   /**
   * Get viadeo
   * @return viadeo
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_VIADEO)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public List<V2SocialAccount> getViadeo() {
    return viadeo;
  }


  public void setViadeo(List<V2SocialAccount> viadeo) {
    this.viadeo = viadeo;
  }


  public V2Website vimeo(List<V2SocialAccount> vimeo) {
    
    this.vimeo = vimeo;
    return this;
  }

  public V2Website addVimeoItem(V2SocialAccount vimeoItem) {
    if (this.vimeo == null) {
      this.vimeo = new ArrayList<>();
    }
    this.vimeo.add(vimeoItem);
    return this;
  }

   /**
   * Get vimeo
   * @return vimeo
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_VIMEO)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public List<V2SocialAccount> getVimeo() {
    return vimeo;
  }


  public void setVimeo(List<V2SocialAccount> vimeo) {
    this.vimeo = vimeo;
  }


  public V2Website webPages(List<WebPage> webPages) {
    
    this.webPages = webPages;
    return this;
  }

  public V2Website addWebPagesItem(WebPage webPagesItem) {
    if (this.webPages == null) {
      this.webPages = new ArrayList<>();
    }
    this.webPages.add(webPagesItem);
    return this;
  }

   /**
   * Get webPages
   * @return webPages
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_WEB_PAGES)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public List<WebPage> getWebPages() {
    return webPages;
  }


  public void setWebPages(List<WebPage> webPages) {
    this.webPages = webPages;
  }


  public V2Website youtube(List<V2SocialAccount> youtube) {
    
    this.youtube = youtube;
    return this;
  }

  public V2Website addYoutubeItem(V2SocialAccount youtubeItem) {
    if (this.youtube == null) {
      this.youtube = new ArrayList<>();
    }
    this.youtube.add(youtubeItem);
    return this;
  }

   /**
   * Get youtube
   * @return youtube
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_YOUTUBE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public List<V2SocialAccount> getYoutube() {
    return youtube;
  }


  public void setYoutube(List<V2SocialAccount> youtube) {
    this.youtube = youtube;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    V2Website v2Website = (V2Website) o;
    return Objects.equals(this.baseURL, v2Website.baseURL) &&
        Objects.equals(this.canonical, v2Website.canonical) &&
        Objects.equals(this.contactForms, v2Website.contactForms) &&
        Objects.equals(this.crawlMode, v2Website.crawlMode) &&
        Objects.equals(this.createdDate, v2Website.createdDate) &&
        Objects.equals(this.dailymotion, v2Website.dailymotion) &&
        Objects.equals(this.description, v2Website.description) &&
        Objects.equals(this.ecommerce, v2Website.ecommerce) &&
        Objects.equals(this.extractedProjects, v2Website.extractedProjects) &&
        Objects.equals(this.extractedPublications, v2Website.extractedPublications) &&
        Objects.equals(this.facebook, v2Website.facebook) &&
        Objects.equals(this.googlePlus, v2Website.googlePlus) &&
        Objects.equals(this.id, v2Website.id) &&
        Objects.equals(this.instagram, v2Website.instagram) &&
        Objects.equals(this.lastCompletion, v2Website.lastCompletion) &&
        Objects.equals(this.lastUpdated, v2Website.lastUpdated) &&
        Objects.equals(this.linkedIn, v2Website.linkedIn) &&
        Objects.equals(this.metaDescription, v2Website.metaDescription) &&
        Objects.equals(this.mobile, v2Website.mobile) &&
        Objects.equals(this.monitoring, v2Website.monitoring) &&
        Objects.equals(this.pageCount, v2Website.pageCount) &&
        Objects.equals(this.platforms, v2Website.platforms) &&
        Objects.equals(this.quality, v2Website.quality) &&
        Objects.equals(this.resolvedPublications, v2Website.resolvedPublications) &&
        Objects.equals(this.responsive, v2Website.responsive) &&
        Objects.equals(this.rss, v2Website.rss) &&
        Objects.equals(this.twitter, v2Website.twitter) &&
        Objects.equals(this.viadeo, v2Website.viadeo) &&
        Objects.equals(this.vimeo, v2Website.vimeo) &&
        Objects.equals(this.webPages, v2Website.webPages) &&
        Objects.equals(this.youtube, v2Website.youtube);
  }

  @Override
  public int hashCode() {
    return Objects.hash(baseURL, canonical, contactForms, crawlMode, createdDate, dailymotion, description, ecommerce, extractedProjects, extractedPublications, facebook, googlePlus, id, instagram, lastCompletion, lastUpdated, linkedIn, metaDescription, mobile, monitoring, pageCount, platforms, quality, resolvedPublications, responsive, rss, twitter, viadeo, vimeo, webPages, youtube);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class V2Website {\n");
    sb.append("    baseURL: ").append(toIndentedString(baseURL)).append("\n");
    sb.append("    canonical: ").append(toIndentedString(canonical)).append("\n");
    sb.append("    contactForms: ").append(toIndentedString(contactForms)).append("\n");
    sb.append("    crawlMode: ").append(toIndentedString(crawlMode)).append("\n");
    sb.append("    createdDate: ").append(toIndentedString(createdDate)).append("\n");
    sb.append("    dailymotion: ").append(toIndentedString(dailymotion)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    ecommerce: ").append(toIndentedString(ecommerce)).append("\n");
    sb.append("    extractedProjects: ").append(toIndentedString(extractedProjects)).append("\n");
    sb.append("    extractedPublications: ").append(toIndentedString(extractedPublications)).append("\n");
    sb.append("    facebook: ").append(toIndentedString(facebook)).append("\n");
    sb.append("    googlePlus: ").append(toIndentedString(googlePlus)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    instagram: ").append(toIndentedString(instagram)).append("\n");
    sb.append("    lastCompletion: ").append(toIndentedString(lastCompletion)).append("\n");
    sb.append("    lastUpdated: ").append(toIndentedString(lastUpdated)).append("\n");
    sb.append("    linkedIn: ").append(toIndentedString(linkedIn)).append("\n");
    sb.append("    metaDescription: ").append(toIndentedString(metaDescription)).append("\n");
    sb.append("    mobile: ").append(toIndentedString(mobile)).append("\n");
    sb.append("    monitoring: ").append(toIndentedString(monitoring)).append("\n");
    sb.append("    pageCount: ").append(toIndentedString(pageCount)).append("\n");
    sb.append("    platforms: ").append(toIndentedString(platforms)).append("\n");
    sb.append("    quality: ").append(toIndentedString(quality)).append("\n");
    sb.append("    resolvedPublications: ").append(toIndentedString(resolvedPublications)).append("\n");
    sb.append("    responsive: ").append(toIndentedString(responsive)).append("\n");
    sb.append("    rss: ").append(toIndentedString(rss)).append("\n");
    sb.append("    twitter: ").append(toIndentedString(twitter)).append("\n");
    sb.append("    viadeo: ").append(toIndentedString(viadeo)).append("\n");
    sb.append("    vimeo: ").append(toIndentedString(vimeo)).append("\n");
    sb.append("    webPages: ").append(toIndentedString(webPages)).append("\n");
    sb.append("    youtube: ").append(toIndentedString(youtube)).append("\n");
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

