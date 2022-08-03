package fr.theialand.insitu.dataportal.repository.mongo.repository;

import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.observation.TheiaVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.ReplaceRootOperation;

import java.util.List;

public class CustomVariableAssociationRepositoryImpl implements  CustomVariableAssociationRepository{
    private MongoDbUtils mongoDbUtils;
    private final String collection = "variableAssociations";

    @Autowired
    public CustomVariableAssociationRepositoryImpl(MongoDbUtils mongoDbUtils) {
        this.mongoDbUtils = mongoDbUtils;
    }

    @Override
    public List<TheiaVariable> getTheiaVariables() {
        GroupOperation g1 = Aggregation.group("theiaVariable");
        ReplaceRootOperation r1 = Aggregation.replaceRoot("_id");
        return this.mongoDbUtils.aggregateToList(collection,TheiaVariable.class,false,g1,r1);
    }
}
