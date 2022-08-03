package fr.theialand.insitu.dataportal.backendassociation.service;

import fr.theialand.insitu.dataportal.repository.mongo.model.DTO.association.AssociationInformationDTO;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.observation.I18n;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.observation.TheiaVariable;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface AssociationService {
    //ResponseEntity<TheiaVariable> createNewTheiaVariable(String info);
    void submitAssociation(AssociationInformationDTO info);
    /**
     * Get the skos:prefLabel of a skos:Concept using its URI
     * @param uri
     * @return ResponseEntity of with HttpStatus.ACCEPTED and the data such as {"prefLabel":[{"lang":"en","text":"prefLabelOfAConcept"}]} or HttpStatus.INTERNAL_SERVER_ERROR or HttpStatus.NOT_FOUND
     */
    ResponseEntity<List<I18n>> getPrefLabelUsingUri(String uri);
    ResponseEntity<List<String>> getBroaderCategoryUsingUri(String uri);
    ResponseEntity<Boolean> IsAVariableURI(String uri);
}
