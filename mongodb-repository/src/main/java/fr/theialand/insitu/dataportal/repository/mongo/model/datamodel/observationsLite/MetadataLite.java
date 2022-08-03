/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observationsLite;

import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.dataset.PortalSearchCriteria;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.dataset.SpatialExtent;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.observation.I18n;

import java.util.List;

/**
 *
 * @author coussotc
 */
public class MetadataLite {
    /**
     * Title of the dataset. It should be a unique name that can reference the geographical area covered.
     */
    private List<I18n> title;
    /**
     * Short description, abstract, description of the dataset. 
     */
    private List<I18n> description;
    private SpatialExtent spatialExtent;

    private PortalSearchCriteria portalSearchCriteria;

    public void setTitle(List<I18n> title) {
        this.title = title;
    }

    public void setDescription(List<I18n> description) {
        this.description = description;
    }

    public void setSpatialExtent(SpatialExtent spatialExtent) {
        this.spatialExtent = spatialExtent;
    }

    public PortalSearchCriteria getPortalSearchCriteria() {
        return portalSearchCriteria;
    }

    public void setPortalSearchCriteria(PortalSearchCriteria portalSearchCriteria) {
        this.portalSearchCriteria = portalSearchCriteria;
    }

    public SpatialExtent getSpatialExtent() {
        return spatialExtent;
    }
    public List<I18n> getTitle() {
        return title;
    }
    public List<I18n> getDescription() {
        return description;
    }
}
