package fr.theialand.insitu.dataportal.repository.mongo.service.associate.variable;

import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.observation.ObservedProperty;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.observation.TheiaVariable;
import fr.theialand.insitu.dataportal.repository.mongo.model.view.ProducerStat;

import java.util.List;

public interface AssociationViewService {
    public List<ProducerStat> getProducerStats();
    public List<List<ObservedProperty>> getDifferentProducerVariableSorted(String producerId);
    public List<TheiaVariable> getVariablesAlreadyAssociatedToCategories(List<String> categories);
}
