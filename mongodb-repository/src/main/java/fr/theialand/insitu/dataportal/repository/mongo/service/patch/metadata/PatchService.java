package fr.theialand.insitu.dataportal.repository.mongo.service.patch.metadata;

import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.observation.I18n;

import java.util.List;

public interface PatchService {
    List<String> getProducerImpactedByTheiaCategoryChange(List<String> categoryUris);
    List<String> getProducerImpactedByTheiaVariableUriChange(List<String> variableUris);
    void removeDummyCategoriesOfObservationsCollection(List<String> categoryUris);
    void removeDummyCategoriesOfVariableAssociationCollection(List<String> categoryUris);
    void removeDummyVariablesOfVariableAssociationCollection(List<String> theiaVariablesUris);
    void updatePrefLabelInVariableAssociationCollection(String uri, List<I18n> preflabelFuseki);
}
