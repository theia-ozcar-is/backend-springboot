package fr.theialand.insitu.dataportal.repository.mongo.service.search.metadata;

import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.observation.TheiaVariable;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.producer.Producer;
import fr.theialand.insitu.dataportal.repository.mongo.model.view.facet.FacetClassification;
import fr.theialand.insitu.dataportal.repository.mongo.model.view.facet.TheiaCategoryFacetElement;
import fr.theialand.insitu.dataportal.repository.mongo.model.view.facet.TheiaCategoryTree;
import fr.theialand.insitu.dataportal.repository.mongo.repository.FacetsNoFilterRepository;
import fr.theialand.insitu.dataportal.repository.mongo.repository.MongoDbUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class FacetServiceImpl extends AbstractFacetService implements FacetService {

    private FacetsNoFilterRepository facetsNoFilterRepository;

    @Autowired
    public FacetServiceImpl(MongoDbUtils mongoDbUtils, FacetsNoFilterRepository facetsNoFilterRepository) {
        super(mongoDbUtils);
        this.facetsNoFilterRepository = facetsNoFilterRepository;
    }


    /**
     * initialise facets filters with the entire database
     * @return FacetClassification.class corresponding to the facet filters available
     */
    @Override
    public FacetClassification initFacets() {
        return this.facetsNoFilterRepository.findFirstBy();
        //return super.setFacetClassification(facetOperation, new ArrayList<>());
    }


    /**
     * Get the the category branches corresponding to a list of uris - Find the category branches to be printed in info-panel for a list of category
     *
     * @param uris List of uri with whom the variable is associated
     * @return List of List of TheiaVariable.class
     */
    @Override
    public List<List<TheiaVariable>> getCategoryHierarchies(List<String> uris) {
        List<TheiaCategoryTree> categoryTrees = new ArrayList<>();
        List<TheiaCategoryFacetElement> theiaCategoryFacetElements = super.mongoDbUtils.findAll("variableCategories", TheiaCategoryFacetElement.class);
        for (String uri : uris) {
            TheiaCategoryFacetElement cat = theiaCategoryFacetElements.stream().filter((t) -> t.getUri().equals(uri)).findFirst().orElse(null);

            assert cat != null;
            categoryTrees.add(TheiaCategoryTree.withBroaders(cat.getUri(), cat.getSimplifiedLabel(), populateBroaders(cat.getBroaders(), theiaCategoryFacetElements), 0));
        }
        List<List<TheiaVariable>> categoriesHierarchies = new ArrayList<>();
        for (TheiaCategoryTree tct : categoryTrees) {
            categoriesHierarchies.addAll(setCategoryBranches(tct));
        }
        return categoriesHierarchies;
    }


    /**
     * Method to get each producer information. To be displayed in producer facet search
     *
     * @return List of Producer
     */
    @Override
    public List<Producer> getProducerInfo() {
        AggregationOperation p1 = Aggregation.project()
                .and("producer.producerId").as("producerId")
                .and("producer.name").as("name")
                .and("producer.title").as("title")
                .and("producer.description").as("description")
                .and("producer.objectives").as("objectives")
                .and("producer.measuredVariables").as("measuredVariables");
        AggregationOperation g1 = Aggregation.group("producerId")
                .addToSet("description").as("description")
                .addToSet("objectives").as("objectives")
                .addToSet("measuredVariables").as("measuredVariables")
                .addToSet("name").as("name")
                .addToSet("title").as("title");
        AggregationOperation p2 = Aggregation.project()
                .and("_id").as("producerId").andExclude("_id")
                .and(ArrayOperators.ArrayElemAt.arrayOf("description").elementAt(0)).as("description")
                .and(ArrayOperators.ArrayElemAt.arrayOf("objectives").elementAt(0)).as("objectives")
                .and(ArrayOperators.ArrayElemAt.arrayOf("measuredVariables").elementAt(0)).as("measuredVariables")
                .and(ArrayOperators.ArrayElemAt.arrayOf("name").elementAt(0)).as("name")
                .and(ArrayOperators.ArrayElemAt.arrayOf("title").elementAt(0)).as("title");
        return this.mongoDbUtils.aggregateToList("observations",Producer.class,true,p1,g1,p2);
    }

    /**
     * Recursive method to populate the broaders of TheiaCategory in order to print the category branch in the info
     * panel
     *
     * @param uriBroaders uri of the broaders of a given concept category
     * @param facetElements The list of TheiaCategoryFacetElement corresponding the cateory of the variable queried
     * @return Set of TheiaCategory populated with broaders
     */
    private Set<TheiaCategoryTree> populateBroaders(List<String> uriBroaders, List<TheiaCategoryFacetElement> facetElements) {
        //List to return
        Set<TheiaCategoryTree> broaders = new HashSet<>();
        //For each uri of the list uriBroaders, a new TheiaCategoryTree object is added to the broaders list.
        uriBroaders.forEach(uri -> {
            TheiaCategoryFacetElement facetElement = facetElements.stream().filter((t) -> t.getUri().equals(uri)).findFirst().orElse(null);

            if (facetElement != null && facetElement.getBroaders().size() > 0) {
                broaders.add(TheiaCategoryTree.withBroaders(
                        facetElement.getUri(),
                        facetElement.getPrefLabel(),
                        populateBroaders(facetElement.getBroaders(), facetElements),
                        facetElement.getCount()));
            }

        });
        return broaders;
    }

    private List<List<TheiaVariable>> setCategoryBranches(TheiaCategoryTree tct) {
        List<List<TheiaVariable>> resultList = new ArrayList<>();
        if (tct.getBroaders() != null && tct.getBroaders().size() > 0) {
            for (TheiaCategoryTree broader : tct.getBroaders()) {
                resultList.addAll(setCategoryBranches(broader));
            }
        }
        if (resultList.size() > 0) {
            List<List<TheiaVariable>> tmpResultList = new ArrayList<>();
            for (List<TheiaVariable> list : resultList) {
                TheiaVariable tv = new TheiaVariable();
                tv.setSimplifiedLabel(tct.getSimplifiedLabel());
                tv.setUri(tct.getUri());
                list.add(tv);
                tmpResultList.add(list);
            }
            return tmpResultList;
        } else {
            List<TheiaVariable> tmp = new ArrayList<>();
            TheiaVariable tv = new TheiaVariable();
            tv.setSimplifiedLabel(tct.getSimplifiedLabel());
            tv.setUri(tct.getUri());
            tmp.add(tv);
            resultList.add(tmp);
            return resultList;
        }
    }
}
