package fr.theialand.insitu.dataportal.repository.mongo.service.search.metadata;

import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.observation.TheiaVariable;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.producer.Producer;
import fr.theialand.insitu.dataportal.repository.mongo.model.view.facet.FacetClassification;

import java.util.List;

public interface FacetService {
    FacetClassification initFacets();
    List<List<TheiaVariable>> getCategoryHierarchies(List<String> uris);
    List<Producer> getProducerInfo();
}
