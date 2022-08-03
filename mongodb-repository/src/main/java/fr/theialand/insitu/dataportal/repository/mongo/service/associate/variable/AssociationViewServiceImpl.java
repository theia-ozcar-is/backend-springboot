package fr.theialand.insitu.dataportal.repository.mongo.service.associate.variable;

import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.observation.ObservedProperty;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.observation.TheiaVariable;
import fr.theialand.insitu.dataportal.repository.mongo.model.view.ProducerStat;
import fr.theialand.insitu.dataportal.repository.mongo.repository.MongoDbUtils;
import fr.theialand.insitu.dataportal.repository.mongo.repository.VariableAssociationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Service
public class AssociationViewServiceImpl implements AssociationViewService {
    private MongoDbUtils mongoDbUtils;
    private VariableAssociationRepository variableAssociationRepository;

    @Autowired
    public AssociationViewServiceImpl(MongoDbUtils mongoDbUtils, VariableAssociationRepository variableAssociationRepository) {
        this.mongoDbUtils = mongoDbUtils;
        this.variableAssociationRepository = variableAssociationRepository;
    }


    @Override
    public List<ProducerStat> getProducerStats() {
        /**
         * Group operation to get the number of producerVariable per producer in collection "observations"
         */
        ProjectionOperation po1 = Aggregation.project().and("producer.producerId").as("producerId")
                .and(ArrayOperators.Filter.filter("observation.observedProperty.name").as("item").by(ComparisonOperators.Eq.valueOf("item.lang").equalToValue("en"))).as("producerVariableName")
                .and("observation.observedProperty.unit").as("unit")
                .and("observation.observedProperty.theiaCategories").as("theiaCategories");
        GroupOperation go1 = Aggregation.group("producerId", "producerVariableName", "unit", "theiaCategories");
        GroupOperation go2 = Aggregation.group("producerId").count().as("variableCount");
        List<Map> numberOfProducerVariables = this.mongoDbUtils.aggregateToList("observations",Map.class,true,po1,go1,go2);

        /**
         * Group operation to get the number of variable associated per producer in collection "observations"
         */
        MatchOperation m1 = Aggregation.match(Criteria.where("observation.observedProperty.theiaVariable").exists(true));
        List<Map> numberOfAssociatedProducerVariables = this.mongoDbUtils.aggregateToList("observations",Map.class,true,m1, po1, go1, go2);

        /**
         * Create the response object containing the stats for each producer
         */
        List<ProducerStat> producerStats = new ArrayList<>();
        numberOfProducerVariables.forEach(item -> {
            ProducerStat producerStat = new ProducerStat();
            producerStat.setName(item.get("_id").toString());
            producerStat.setTotal((Integer) item.get("variableCount"));

            List<Map> tmp = numberOfAssociatedProducerVariables.stream()
                    .filter((t) -> {
                        Map associatedProducerVariable = (Map) t;
                        return associatedProducerVariable.get("_id").toString().equals(item.get("_id").toString());
                    }).collect(Collectors.toList());
            if (!tmp.isEmpty()) {
                producerStat.setAssociated((Integer) tmp.get(0).get("variableCount"));
            } else {
                producerStat.setAssociated(0);
            }
            producerStats.add(producerStat);
        });
        return producerStats;
    }


    /**
     * For a given producer, query the non-associated producer variable name and the associated producer variable name.
     * A producer variables are concidered different if for a same 'name' they have different 'unit' or
     * 'theiaCateogries'
     *
     * @param producerId String value of the producer ID
     * @return List of List of ObservedProperty.class - Containing
     * "producerVariableName","unit","theiaCategories","theiaVariable"
     */
    @Override
    public List<List<ObservedProperty>> getDifferentProducerVariableSorted(String producerId) {
        /**
         * Match the producer Id
         */
        MatchOperation m1 = Aggregation.match(where("producer.producerId").is(producerId));
        /**
         * Match the producer variable already associated
         */
        MatchOperation m2 = Aggregation.match(where("observation.observedProperty.theiaVariable").exists(true));
        /**
         * Match the producer variable not associated yet
         */
        MatchOperation m3 = Aggregation.match(where("observation.observedProperty.theiaVariable").exists(false));
        /**
         * Group the same producer variable. Producer variables are concidered different if for a same 'name' they have
         * different 'unit' or 'theiaCateogries'
         */
        ProjectionOperation p1 = Aggregation.project().and("observation.observedProperty.name").as("name")
                .and("observation.observedProperty.unit").as("unit")
                .and("observation.observedProperty.theiaCategories").as("theiaCategories")
                .and("observation.observedProperty.theiaVariable").as("theiaVariable")
                .and("observation.observedProperty.description").as("description")
                .and("observation.observedProperty.keywords").as("keywords");
        GroupOperation g1 = Aggregation.group("name", "unit", "theiaCategories", "theiaVariable")
                .first("description").as("description")
                .first("keywords").as("keywords");
        ProjectionOperation p2 = Aggregation.project()
                .and("_id.name").as("name")
                .and("_id.unit").as("unit")
                .and("_id.theiaCategories").as("theiaCategories")
                .and("_id.theiaVariable").as("theiaVariable")
                .and("description").as("description")
                .and("keywords").as("keywords")
                .andExclude("_id");
        /**
         * Sort by alphabetical order
         */
        SortOperation s1 = Aggregation.sort(Sort.Direction.ASC, "name.0.text");

        /**
         * Execute the aggregation pipeline
         */
        List<List<ObservedProperty>> response = new ArrayList<>();
        List<ObservedProperty> associatedVariables = this.mongoDbUtils.aggregateToList("observations",ObservedProperty.class,true,m1, m2, p1, g1, p2, s1);
        response.add(associatedVariables);
         /*
         For Each associatied variable, the categories of the corresponding observation have been replaced by the categories from the semantic relation of the variable (from the thesaurus).
         We need to query the variableAssociation collection to find the corresponding association and retrieve the categories originally associated by the producer
          */
        for(ObservedProperty observedProperty : associatedVariables){
            String producerVariableNameEn = observedProperty.getName().stream()
                    .filter(label -> "en".equals(label.getLang())).findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("No english Label in producer variable name")).getText();
            observedProperty.setTheiaCategories(
                    this.variableAssociationRepository.findFirstByProducerIdAndTheiaVariableUriAndProducerVariableNameEn(producerId,observedProperty.getTheiaVariable().getUri(),producerVariableNameEn)
                            .getProducerTheiaCategories());
        }
        List<ObservedProperty> nonAssociatedVariables = this.mongoDbUtils.aggregateToList("observations",ObservedProperty.class,true,m1, m3, p1, g1, p2, s1);
        response.add(nonAssociatedVariables);
        return response;
    }

    /**
     * Find the Theia Varaiable already associated to one or several cateogories. In order to suggest Theia variable to
     * be associated with for a given producer variable.
     *
     * @param categories List of category uri ex: "["https://w3id.org/ozcar-theia/atmosphericRadiation"]"
     * @return List of Document containing the theia variables
     */
    @Override
    public List<TheiaVariable> getVariablesAlreadyAssociatedToCategories(List<String> categories) {
        List<Criteria> orCriteriasList = new ArrayList<>();
        for (String category : categories) {
            orCriteriasList.add(Criteria.where("observation.observedProperty.theiaCategories").is(category));
        }
        Criteria[] orCriterias = new Criteria[orCriteriasList.size()];
        for (int i = 0; i < orCriteriasList.size(); i++) {
            orCriterias[i] = orCriteriasList.get(i);
        }

        MatchOperation m1 = Aggregation.match(where("observation.observedProperty.theiaVariable").exists(true));
        UnwindOperation u1 = Aggregation.unwind("observation.observedProperty.theiaCategories");
        MatchOperation m2 = Aggregation.match(new Criteria().orOperator(orCriterias));
        ProjectionOperation p1 = Aggregation.project().and("observation.observedProperty.theiaVariable").as("theiaVariable");
        GroupOperation g1 = Aggregation.group("theiaVariable");
        ReplaceRootOperation r1 = Aggregation.replaceRoot("_id");

        return this.mongoDbUtils.aggregateToList("observations",TheiaVariable.class,true,m1, u1, m2, p1, g1, r1);
    }
}
