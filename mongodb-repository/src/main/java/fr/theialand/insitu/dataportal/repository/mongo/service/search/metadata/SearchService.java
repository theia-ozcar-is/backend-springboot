package fr.theialand.insitu.dataportal.repository.mongo.service.search.metadata;

import fr.theialand.insitu.dataportal.repository.mongo.model.DTO.metadataportal.FacetFiltersDTO;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.entity.ObservationDocument;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.dataset.Dataset;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.dataset.SpatialExtent;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.observation.TheiaVariable;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.producer.Producer;
import fr.theialand.insitu.dataportal.repository.mongo.model.view.ResponseDocument;
import org.bson.Document;
import org.springframework.lang.NonNull;

import java.util.List;

public interface SearchService {
    public List<ObservationDocument> findByDocumentId(List<String> documentIds);
    public List<String> getObservationIdsOfOtherTheiaVariableAtLocation(String payload);
    public List<TheiaVariable> getVariablesAtOneLocation(String coordinates);
    public List<Document> getObservationsOfADataset(String datasetId);
    public SpatialExtent getBBOXOfOfADataset(String datasetId);
    public ResponseDocument searchObservations(FacetFiltersDTO payload);
    public List<ObservationDocument> findByDatasetId(String datasetId);
    public ObservationDocument findFirstByDatasetId(String datasetId);
    public Producer findProducer(String producerId) ;
    public List<Dataset> findDatasetsByProducerId(@NonNull String producer );

    }
