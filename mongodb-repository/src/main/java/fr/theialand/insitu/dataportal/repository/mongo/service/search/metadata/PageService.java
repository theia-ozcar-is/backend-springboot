package fr.theialand.insitu.dataportal.repository.mongo.service.search.metadata;

import fr.theialand.insitu.dataportal.repository.mongo.model.DTO.metadataportal.DatasetPageDTO;
import fr.theialand.insitu.dataportal.repository.mongo.model.DTO.metadataportal.PagePayloadDTO;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.entity.ObservationDocumentLite;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.producer.Producer;
import org.springframework.data.domain.Page;

public interface PageService {
    public Page<ObservationDocumentLite> getObservationsPage(PagePayloadDTO payload);
    public Page<Producer> getProducerPage(PagePayloadDTO payload);
    public Page<DatasetPageDTO> getDatasetPage(PagePayloadDTO payload);

}
