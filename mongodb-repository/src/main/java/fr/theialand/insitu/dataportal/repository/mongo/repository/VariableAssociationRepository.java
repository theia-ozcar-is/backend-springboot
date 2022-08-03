package fr.theialand.insitu.dataportal.repository.mongo.repository;

import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.entity.VariableAssociation;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.observation.TheiaVariable;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface VariableAssociationRepository extends MongoRepository<VariableAssociation, ObjectId>, CustomVariableAssociationRepository {
    Long deleteVariableAssociationByProducerId(String producerId);

    VariableAssociation findFirstByProducerIdAndTheiaVariableUriAndProducerVariableNameEn(String producerId, String theiaVariableUri, String producerVariableNameEn);
    List<VariableAssociation> findByProducerId(String producerId);

}
