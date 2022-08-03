package fr.theialand.insitu.dataportal.repository.mongo.repository;

import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.observation.TheiaVariable;

import java.util.List;

public interface CustomVariableAssociationRepository {

    List<TheiaVariable> getTheiaVariables();
}
