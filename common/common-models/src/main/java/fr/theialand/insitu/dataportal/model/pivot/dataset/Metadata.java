/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.theialand.insitu.dataportal.model.pivot.dataset;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import fr.theialand.insitu.dataportal.model.pivot.enumerations.EnumInspireTheme;
import fr.theialand.insitu.dataportal.model.pivot.enumerations.EnumTopicCategory;
import fr.theialand.insitu.dataportal.model.pivot.observation.Document;

import java.util.List;

/**
 * Class representing all the metadata associated to a dataset
 * @see Dataset
 * @author coussotc
 */
public class Metadata {

    @JsonProperty(required = true)
    @JsonPropertyDescription("Title of the dataset. It should be a unique name that can reference the geographical area covered")
    private String title;

    @JsonProperty(required = true)
    @JsonPropertyDescription("Short description, abstract, description of the dataset")
    private String description;

    /**
     * Description of the scientifique objectives of the dataset
     */
    @JsonPropertyDescription("Optional description of the objective")
    private String objective;

    @JsonPropertyDescription("A OnlineResource object representing the online resource for the dataset")
    private OnlineResource onlineResource;

    @JsonProperty(required = true)
    @JsonPropertyDescription("Description of the previous processing that the dataset underwent")
    private String datasetLineage;

    @JsonProperty(required = true)
    @JsonPropertyDescription("ordered list of contact for the dataset")
    private List<Contact> contacts;

    @JsonProperty(required = true)
    @JsonPropertyDescription("A DataConstraint objet representing access and use constraint on the dataset")
    private DataConstraint dataConstraint;

//    @JsonProperty(required = true)
    @JsonPropertyDescription("A PortalSearchCriteria object that represents the some search criteria on the dataset to be used by the portal.")
    private PortalSearchCriteria portalSearchCriteria;

    /**
     * Ordered list of thematic catégories for the dataset. Each element of the list must
 be a value of the EnumTopicCategories enumeration.
     */
    @JsonProperty(required = true)
    @JsonPropertyDescription("Ordered list of thematic catégories for the dataset")
    private List<EnumTopicCategory> topicCategories;

    @JsonProperty(required = true)
    @JsonPropertyDescription("Inspire theme for the dataset.")
    private EnumInspireTheme inspireTheme;

    @JsonPropertyDescription("A list of Keyword objects for the dataset")
    private List<Keyword> keywords;

    @JsonPropertyDescription("A list of Document objects for the dataset.The list can contain a maxium of 2 elements since only two types of documents are possible in the EnumDocumentTypes enumeration")
    private List<Document> documents;

    @JsonProperty(required = true)
    @JsonPropertyDescription("The spatialExtent of the dataset. This must be a bounding box or several bouding boxes.")
    private SpatialExtent spatialExtent;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getObjective() {
        return objective;
    }

    public OnlineResource getOnlineResource() {
        return onlineResource;
    }

    public String getDatasetLineage() {
        return datasetLineage;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public DataConstraint getDataConstraint() {
        return dataConstraint;
    }

    public PortalSearchCriteria getPortalSearchCriteria() {
        return portalSearchCriteria;
    }

    public List<EnumTopicCategory> getTopicCategories() {
        return topicCategories;
    }

    public EnumInspireTheme getInspireTheme() {
        return inspireTheme;
    }

    public List<Keyword> getKeywords() {
        return keywords;
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public SpatialExtent getSpatialExtent() {
        return spatialExtent;
    }
}
