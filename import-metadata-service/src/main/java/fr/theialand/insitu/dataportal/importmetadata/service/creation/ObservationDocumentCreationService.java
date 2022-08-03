package fr.theialand.insitu.dataportal.importmetadata.service.creation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import fr.theialand.insitu.dataportal.importmetadata.service.ImportConfig;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.entity.ObservationDocument;

import java.util.List;

public interface ObservationDocumentCreationService {
    public List<ObservationDocument> createObservationDocument(JsonNode json, ImportConfig importConfig) throws JsonProcessingException;

}
