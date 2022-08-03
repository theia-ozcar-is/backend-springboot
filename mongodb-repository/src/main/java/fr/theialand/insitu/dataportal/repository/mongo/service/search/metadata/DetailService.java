package fr.theialand.insitu.dataportal.repository.mongo.service.search.metadata;


import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.entity.ObservationDocument;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.producer.Producer;

public interface DetailService {
    public Producer getProducerDetailed(String producerId);
    public ObservationDocument getDatasetDetailed(String datasetId);
}
