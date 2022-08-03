package fr.theialand.insitu.dataportal.importmetadata.service.insert;

import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.entity.ObservationDocument;

import java.util.List;

public interface InsertService {
    void insertObservationDocuments(List<ObservationDocument> observationDocumentList);
    void deleteProducerDocuments(String producerId);
    void groupObservationDocumentsAndInsertInObservationDocumentLiteCollection(String producerId);
    void groupObservationDocumentLiteByLocationAndInsertMapItemCollection(String producerId);
    void aggregateObservationDocumentLiteToNoFilterFacetsCollection();
    void refreshVariableAssociations(String producerId);

    /**
     * Update our mongo collection of very basic datasets history
     * It will get the current ones with the help of producerId,
     * compare with the old ones given as parameter,
     * and update accordingly mongo : new, modified, deleted
     * @param producerId for getting the current ones
     * @param previousDatasets ... will be compared to this list
     */
    void updateDatasetHistory(String producerId, List<String> previousDatasets);

    /**
     * Get all the DS ids from a given producer
     */
    List<String> getDatasetsId(String producerId);
}
