/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.theialand.insitu.dataportal.repository.mongo.model.view;

import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.entity.MapItem;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.entity.ObservationDocumentLite;
import fr.theialand.insitu.dataportal.repository.mongo.model.view.facet.FacetClassification;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 *
 * @author coussotc
 */
public class ResponseDocument {
//    private PagedListHolder<ObservationDocumentLite> observationDocumentLitePage;
    private Page<ObservationDocumentLite> observationDocumentLitePage;
    private FacetClassification facetClassification;
    private List<MapItem> mapItems;

    public Page<ObservationDocumentLite> getObservationDocumentLitePage() {
        return observationDocumentLitePage;
    }

    public void setObservationDocumentLitePage(Page<ObservationDocumentLite> observationDocumentLitePage) {
        this.observationDocumentLitePage = observationDocumentLitePage;
    }

    public List<MapItem> getMapItems() {
        return mapItems;
    }

    public void setMapItems(List<MapItem> mapItems) {
        this.mapItems = mapItems;
    }

    public FacetClassification getFacetClassification() {
        return facetClassification;
    }

    public void setFacetClassification(FacetClassification facetClassification) {
        this.facetClassification = facetClassification;
    }

    

}
