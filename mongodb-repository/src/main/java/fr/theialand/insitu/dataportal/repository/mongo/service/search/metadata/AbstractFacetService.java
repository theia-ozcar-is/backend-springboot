package fr.theialand.insitu.dataportal.repository.mongo.service.search.metadata;

import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.observation.I18n;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.observation.TheiaVariable;
import fr.theialand.insitu.dataportal.repository.mongo.model.view.facet.FacetClassification;
import fr.theialand.insitu.dataportal.repository.mongo.model.view.facet.FacetClassificationTmp;
import fr.theialand.insitu.dataportal.repository.mongo.model.view.facet.TheiaCategoryFacetElement;
import fr.theialand.insitu.dataportal.repository.mongo.model.view.facet.TheiaCategoryTree;
import fr.theialand.insitu.dataportal.repository.mongo.repository.MongoDbUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;
import org.springframework.data.mongodb.core.aggregation.FacetOperation;

import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;
import static org.springframework.data.mongodb.core.aggregation.ArrayOperators.Filter.filter;
import static org.springframework.data.mongodb.core.aggregation.ComparisonOperators.Eq.valueOf;

public abstract class AbstractFacetService {

    protected MongoDbUtils mongoDbUtils;

    public AbstractFacetService(MongoDbUtils mongoDbUtils) {
        this.mongoDbUtils = mongoDbUtils;
    }

    /**
     * Facet aggregation operation that is used to create the facet of the application
     */
    protected final FacetOperation facetOperation = facet()
            .and(
                    unwind("theiaCategories"),
                    group("theiaCategories.uri")
                            .first("theiaCategories.uri").as("uri")
                            .first("theiaCategories.broaders").as("broaders")
                            .first("theiaCategories.narrowers").as("narrowers")
                            .first("theiaCategories.simplifiedLabel").as("simplifiedLabel")
                            .count().as("count")
                            .addToSet("theiaVariable").as("theiaVariables")
            )
            .as("theiaCategorieFacetElements")
            .and(
                    project().and("observations").arrayElementAt(0).as("observations"),
                    project().and("observations.featureOfInterest.administrativeFeatures").as("administrativeFeatureProperties"),
                    unwind("administrativeFeatureProperties"),
                    group("administrativeFeatureProperties.id")
                            .first("administrativeFeatureProperties.id").as("id")
                            .first("administrativeFeatureProperties.admin_level").as("admin_level")
                            .first("administrativeFeatureProperties.name").as("name")
                            .first("administrativeFeatureProperties.admin_level2_parent_name_en").as("admin_level2_parent_name_en")
            ).as("administrativeFeatureProperties")
            .and(unwind("producer.fundings"),
                    project().and("producer.fundings.type").as("type")
                            .and("producer.fundings.acronym").as("acronym")
                            .and("producer.fundings.idScanR").as("idScanR")
                            .and("producer.fundings.country").as("country")
                            .and(filter("producer.fundings.name").as("item")
                                    .by(valueOf("item.lang")
                                            .equalToValue("en")))
                            .as("name"),
                    project("type")
                            .and(ArrayOperators.ArrayElemAt.arrayOf("name.text").elementAt(0)).as("name")
                            .and("acronym").as("acronym")
                            .and("country").as("country")
                            .and("idScanR").as("idScanR"),
                    group("type", "name", "acronym", "country", "idScanR").count().as("count"),
                    project("count").and("_id.name").as("name").and("_id.acronym").as("acronym").and("_id.country").as("country").and("_id.idScanR").as("idScanR").and("_id.type").as("type").andExclude("_id"),
                    sort(Sort.Direction.ASC, "name")
            ).as("fundingNamesFacet")
            .and(
                    project().and("observations").arrayElementAt(0).as("observations"),
                    project().and("observations.featureOfInterest.climateFeature").as("name"),
                    group("name").count().as("count")
            ).as("climatesFacet")
            .and(
                    project().and("observations").arrayElementAt(0).as("observations"),
                    project().and("observations.featureOfInterest.geologyFeature").as("name"),
                    group("name").count().as("count")
            ).as("geologiesFacet")
            .and(project().and(filter("producer.name").as("item")
                            .by(valueOf("item.lang")
                                    .equalToValue("en")))
                            .as("name"),
                    project().and(ArrayOperators.ArrayElemAt.arrayOf("name.text").elementAt(0)).as("name"),
                    group("name").count().as("count"),
                    sort(Sort.Direction.ASC, "_id")
            ).as("producerNamesFacet")
            .and(group().count().as("count"))
            .as("totalCount");

    /**
     * Calculate the facet for a given set of filters defined by user.
     *
     * @param facetOperation FacetOperation aggregation operation to be executed to calculate the facets
     * @param aggregationOperations The aggregation pipeline generated for the set of filter defined by the user
     * @return FacetClassification object corresponding to the filters
     */
    protected FacetClassification setFacetClassification(FacetOperation facetOperation, List<AggregationOperation> aggregationOperations) {
        /*
         * Add the Facet aggregation operation to the pipeline to generate the relative facet. The following result is
         * stored into the ResponseDocument object
         */
        aggregationOperations.add(facetOperation);
        FacetClassificationTmp facetClassificationTmp = this.mongoDbUtils.aggregateToUnique(
                "observationsLite",FacetClassificationTmp.class,true, aggregationOperations.toArray(AggregationOperation[]::new));

        /*
         * Post processing of the FacetClassificationTmp class before returning to the front end client.
         */
        /*
         * 1 - Build the category tree 2 - Build the collection of Theia Variable
         */
        List<TheiaCategoryTree> categoryTrees = new ArrayList<>();
        Set<TheiaVariable> theiaVariablesTmp = new HashSet<>();
        facetClassificationTmp.getTheiaCategorieFacetElements().stream().filter((t) -> t.getBroaders().contains("https://w3id.org/ozcar-theia/c_341ce66a")).forEach((t) -> {
            /*
             * Recursivly build the category tree
             */
            categoryTrees.add(TheiaCategoryTree.withNarrowers(t.getUri(), t.getSimplifiedLabel(), populateNarrowers(t.getNarrowers(), facetClassificationTmp.getTheiaCategorieFacetElements()), t.getCount()));
            /*
             * Build the list of Theia variable
             */
            if(t.getTheiaVariables() != null || t.getTheiaVariables().size() > 0){
                theiaVariablesTmp.addAll(t.getTheiaVariables());
            }

        });
        //Only the simplifiedLabel information is necessary for facets. removing the other unused information to avoid confusion
        //if uri is null, uri is not used in hashcode() method and is necessary for set collection
        Set<TheiaVariable> tmpSet = new HashSet<>();
        theiaVariablesTmp.forEach(s -> {
            TheiaVariable v = new TheiaVariable();
            v.setSimplifiedLabel(s.getSimplifiedLabel());
            tmpSet.add(v);
        });
        theiaVariablesTmp.clear();
        theiaVariablesTmp.addAll(tmpSet);

        //Sort in alphabetical order
        Comparator<TheiaVariable> variableComparator = (h1, h2) -> {
            I18n label1 = h1.getSimplifiedLabel().stream().filter(i18n -> "en".equals(i18n.getLang())).findFirst().get();
            I18n label2 = h2.getSimplifiedLabel().stream().filter(i18n -> "en".equals(i18n.getLang())).findFirst().get();
            return label1.getText().compareTo(label2.getText());
        };
        List<TheiaVariable> theiaVariables = theiaVariablesTmp.stream().sorted(variableComparator).collect(Collectors.toList());
        Comparator<TheiaCategoryTree> theiaCategoryTreeComparator = (h1, h2) -> {
            I18n label1 = h1.getSimplifiedLabel().stream().filter(i18n -> "en".equals(i18n.getLang())).findFirst().get();
            I18n label2 = h2.getSimplifiedLabel().stream().filter(i18n -> "en".equals(i18n.getLang())).findFirst().get();
            return label1.getText().compareTo(label2.getText());
        };
        categoryTrees.sort(theiaCategoryTreeComparator);
        return new FacetClassification(
                theiaVariables,
                categoryTrees,
                facetClassificationTmp.getAdministrativeFeatureProperties(),
                facetClassificationTmp.getFundingNamesFacet(),
                facetClassificationTmp.getClimatesFacet(),
                facetClassificationTmp.getGeologiesFacet(),
                facetClassificationTmp.getProducerNamesFacet(),
                facetClassificationTmp.getTotalCount());
    }

    /**
     * Recursive methods to populate narrowers property of the TheiaCategoryTree object according to the filters applied in the facet
     * @param uriNarrowers list String of of narrowers uris
     * @param facetElements List of TheiaCategoryFacetElement corresponding to the facet filters
     * @return Set of TheiaCategoryTree
     */
    private Set<TheiaCategoryTree> populateNarrowers(List<String> uriNarrowers, List<TheiaCategoryFacetElement> facetElements) {
        //List to return
        Set<TheiaCategoryTree> narrowers = new HashSet<>();

        //For each uri of the list uriNarrowers, a new TheiaCategoryTree object is added to the narrowers list.
        uriNarrowers.forEach(uri -> {
            TheiaCategoryFacetElement facetElement = facetElements.stream().filter((t) -> t.getUri().equals(uri)).findFirst().orElse(null);

            //If the narrowers uri is not present in the facetElements collection, nothing happens
            if (facetElement != null) {
                // Check if the theia variable is directly linked to the theia category facet element. If true, the theia variable needs to be printed as a narrower in
                // hierarchy tree.
                boolean isVariableDirectNarrower = facetElement.getTheiaVariables().stream().anyMatch(v-> v.getBroaderCategories().stream().anyMatch(categoryUri -> facetElement.getUri().equals(categoryUri)));
                //The limit case: the FacetElement object found does not have narrowers attribute
                if (isVariableDirectNarrower) {
                    narrowers.add(TheiaCategoryTree.withTheiaVariables(
                            facetElement.getUri(),
                            facetElement.getSimplifiedLabel(),
                            new HashSet<>(facetElement.getTheiaVariables()),
                            facetElement.getCount()));
                } else if (facetElement.getNarrowers() == null || facetElement.getNarrowers().size() == 0){
                    //Limit case: the FacetElement object found does NOT have Narrowers attribute and does not have TheiaVariable. We add a TheiaCategoryTree without narrowers property
                    TheiaCategoryTree tree = new TheiaCategoryTree();
                    tree.setUri(facetElement.getUri());
                    tree.setSimplifiedLabel(facetElement.getSimplifiedLabel());
                    tree.setCount(facetElement.getCount());
                    narrowers.add(tree);
                }
                //The recursive case: the FacetElement object found does have narrowers attribute, populateNarrowers methods
                // is recursivly used
                else if (facetElement.getNarrowers() != null && facetElement.getNarrowers().size() > 0) {
                    narrowers.add(TheiaCategoryTree.withNarrowers(
                            facetElement.getUri(),
                            facetElement.getSimplifiedLabel(),
                            populateNarrowers(facetElement.getNarrowers(), facetElements),
                            facetElement.getCount()));
                }
            }
        });
        return narrowers;
    }

}
