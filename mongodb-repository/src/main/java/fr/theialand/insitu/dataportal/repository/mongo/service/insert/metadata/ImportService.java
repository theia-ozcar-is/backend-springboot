package fr.theialand.insitu.dataportal.repository.mongo.service.insert.metadata;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;

public interface ImportService {
    void enrichObservationsWithVariableAssociationsCollection(String producerId);
    void groupObservationDocumentsByVariableAtGivenLocationAndInsertObservationDocumentLiteCollection(String producerId);
    void groupObservationDocumentLiteByLocationAndInsertMapItemCollection(String producerId);
    void refreshVariableAssociationsWithObservationsCollection(String producerId);
    void enrichObservationsWithAdministrativeFeatureOfInterest(String producerId) throws JsonProcessingException;
    void aggregateObservationDocumentLiteToNoFilterFacetsCollection();

    //No need to use mongodb to enrich with koppen classification. Should be in enrcihment service from import-metadata-service module
    //void enrichObservationsWithKoppenClassification(String producerId) throws IOException;
}
