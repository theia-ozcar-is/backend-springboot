package fr.theialand.insitu.dataportal.repository.mongo.repository;

import fr.theialand.insitu.dataportal.repository.mongo.model.DTO.metadataportal.SamplingFeatureGeomDTO;

import java.util.List;

public interface CustomObservationDocumentRepository {
    List<String> getProducerIds();
    List<SamplingFeatureGeomDTO> getByProducerIdGroupBySamplingFeatureGeometry(String producerId);
    void insertIndexes();
}
