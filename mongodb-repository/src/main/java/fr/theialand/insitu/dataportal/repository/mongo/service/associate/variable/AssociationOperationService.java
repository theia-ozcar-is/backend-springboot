package fr.theialand.insitu.dataportal.repository.mongo.service.associate.variable;

import fr.theialand.insitu.dataportal.repository.mongo.model.DTO.association.AssociationDTO;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.observation.TheiaVariable;
import org.json.JSONObject;

import java.util.List;

public interface AssociationOperationService {
    void updateVariableSemanticPropertiesForOtherObservations(TheiaVariable theiaVariable);
    void updateOneVariableAssociation(String producerId, AssociationDTO associationDTO);
    void unsetThieaVariableInMultiObservations(List<String> documentIds, List<String> categoryUrisFromProducer);
    void updateOneObservationObservedPropertyWithTheiaVariable(String documentId, TheiaVariable theiaVariable);
}
