package fr.theialand.insitu.dataportal.repository.mongo.service.search.metadata;
import fr.theialand.insitu.dataportal.repository.mongo.model.DTO.metadataportal.FacetFiltersDTO;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.geojsonproperties.AdministrativeFeatureProperties;
import fr.theialand.insitu.dataportal.repository.mongo.repository.MongoDbUtils;
import fr.theialand.insitu.dataportal.repository.mongo.service.GenericAggregationOperation;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.TextCriteria;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

public class AbstractSearchService extends AbstractFacetService {

    public AbstractSearchService(MongoDbUtils mongoDbUtils) {
        super(mongoDbUtils);
    }

    /**
     * Method used to parse the String containing the filters into aggregation operation
     *
     * @param queryElements String that can be parsed into json object.
     * @param groupOperationTarget String extra group operation target speciying if a group operation need to be
     * performed on "producer" or "dataset" objects
     * @return List of AggregationOperation composed of MatchOperation of each filter of the queryElements parameter
     */
    List<AggregationOperation> setMatchOperationUsingFilters(FacetFiltersDTO queryElements, String groupOperationTarget) {
        //Aggregation pipeline to be executed to find the observation matching the query
        List<AggregationOperation> aggregationOperations = new ArrayList<>();
        /*
         * For each element to be queried, a match aggregation operation is created and added to the aggregation
         * pipeline
         * -------------------------------------------------------------------------------------------------------------
         */
        /*
         * Match operation for the full text query parameter
         */
        if (queryElements.getFullText() != null && queryElements.getFullText().size() > 0) {
            String fullTextQuery = "";
            for (int i = 0; i < queryElements.getFullText().size() ; i++) {
                fullTextQuery = fullTextQuery.concat(queryElements.getFullText().get(i) +" ");
            }
            aggregationOperations.add(match(new TextCriteria().matchingAny(fullTextQuery.trim())));
            aggregationOperations.add(new GenericAggregationOperation("$addFields", "{ \"textScore\": { \"$meta\": \"textScore\" }}"));
        }
        /*
         * Match operation at the dataset or producer level -------------------------------------------------
         */
        /*
         * Match operation for producer and bucket bucket element query parameters
         */


        if (queryElements.getProducerNames() != null && queryElements.getProducerNames().size() > 0) {
            List<Criteria> producerCriterias = new ArrayList<>();
            queryElements.getProducerNames().forEach(item -> producerCriterias.add(Criteria.where("producer.name").elemMatch(
                    Criteria.where("lang").is("en").and("text").is(item))));
            aggregationOperations.add(
                    Aggregation.match(new Criteria().orOperator(producerCriterias.toArray(new Criteria[producerCriterias.size()]))
                    ));
        }

        if (queryElements.getFundingNames() != null && queryElements.getFundingNames().size() > 0) {
            List<Criteria> fundingCriterias = new ArrayList<>();
            queryElements.getFundingNames().forEach(item -> fundingCriterias.add(Criteria.where("producer.fundings").elemMatch(
                    Criteria.where("name").elemMatch(
                            Criteria.where("lang").is("en").and("text").is(item)))));
            aggregationOperations.add(
                    Aggregation.match(new Criteria().orOperator(fundingCriterias.toArray(new Criteria[fundingCriterias.size()]))
                    ));
        }

        /*
         * -------------------------------------------------------------------------------------------------------------
         *
         * Match operation on fields that will not be different among grouped observations
         *
         */

        /*
          2 - a ) Theia categories match operation
         */
        if ((queryElements.getTheiaCategories() != null && queryElements.getTheiaCategories().size() > 0) || (queryElements.getTheiaVariables() != null && queryElements.getTheiaVariables().size() > 0)) {
            List<Criteria> theiaCategoriesVariablesCriterias = new ArrayList<>();
            if (queryElements.getTheiaCategories() != null && queryElements.getTheiaCategories().size() > 0) {



                queryElements.getTheiaCategories().forEach(item -> {
                    theiaCategoriesVariablesCriterias.add(
                            Criteria.where("theiaCategories.uri").is(item.getUri())
                    );
                });
            }
            /*
             * 2 - b ) Theia variable match operations
             */
            if (queryElements.getTheiaVariables() != null && queryElements.getTheiaVariables().size() > 0) {
                queryElements.getTheiaVariables().forEach(item -> {
                    String simplifiedLabelEn = item.getSimplifiedLabel().stream().filter(t -> t.getLang().equals("en")).findFirst().get().getText();
                    theiaCategoriesVariablesCriterias.add(
                            Criteria.where("theiaVariable.simplifiedLabel").elemMatch(
                                    Criteria.where("lang").is("en").and("text").is(simplifiedLabelEn))
                    );
                });
            }
            aggregationOperations.add(Aggregation.match(new Criteria().orOperator(theiaCategoriesVariablesCriterias.toArray(new Criteria[theiaCategoriesVariablesCriterias.size()]))));
        }

        /*
         * 2 - c ) observation spatial extent match operations
         */
        if (queryElements.getSpatialExtent() != null) {
            List<Criteria> spatialExtentCriterias = new ArrayList<>();
            queryElements.getSpatialExtent().getFeatures().forEach(polygon -> spatialExtentCriterias.add(
                    Criteria.where("featureOfInterest.samplingFeature.geometry").within(polygon.getGeometry())) );
            aggregationOperations.add(Aggregation.match(new Criteria().orOperator(spatialExtentCriterias.toArray(new Criteria[spatialExtentCriterias.size()]))));
        }

        /*
         * 2 - d ) observation feature of interest match operations
         */
        if (queryElements.getFeatureOfInterests() != null && queryElements.getFeatureOfInterests().size() > 0) {
            List<Criteria> foiCriterias = new ArrayList<>();
            queryElements.getFeatureOfInterests().forEach(feature -> {
                if (feature.getClass().isInstance(new AdministrativeFeatureProperties())) {
                    foiCriterias.add(Criteria.where("featureOfInterest.administrativeFeatures").elemMatch(Criteria.where("name").is(((AdministrativeFeatureProperties) feature).getName())));
                }
            });
            aggregationOperations.add(Aggregation.match(new Criteria().orOperator(foiCriterias.toArray(new Criteria[foiCriterias.size()]))));
        }
        /*
         * 2 - e ) observation climate and geology features match operations
         */
        if (queryElements.getClimates() != null && queryElements.getClimates().size() > 0) {
            List<Criteria> climateCriterias = new ArrayList<>();
            queryElements.getClimates().forEach(item -> climateCriterias.add(Criteria.where("featureOfInterest.climateFeature")
                    .is(item)));
            aggregationOperations.add(
                    Aggregation.match(new Criteria().orOperator(climateCriterias.toArray(new Criteria[climateCriterias.size()]))
                    ));
        }
        if (queryElements.getGeologies() != null && queryElements.getGeologies().size() > 0) {
            List<Criteria> geologyCriterias = new ArrayList<>();
            queryElements.getGeologies().forEach(item -> geologyCriterias.add(Criteria.where("featureOfInterest.geologyFeature")
                    .is(item)));
            aggregationOperations.add(
                    Aggregation.match(new Criteria().orOperator(geologyCriterias.toArray(new Criteria[geologyCriterias.size()]))
                    ));
        }

        /*
         * Match operation at the observation level. In "observationsLite" collection observation level is grouped by
         * variable at a given location.
         * Hence it is needed to unwind the "observations" fields before to perform the
         * match operation on "temporalExtends" fields and to group the documents back to the initial form after the match operation on
         * "observations" level
         */
        /*
         * 2 - f ) Temporal extent match operation
         *  MatchOperations stage Match operation for the temporal extent parameters Document will not be returned
         * only if the temporal extent queried is outside the temporal extent of the document
         */
        if (queryElements.getTemporalExtents() != null && queryElements.getTemporalExtents().size() > 0) {
            aggregationOperations.add(unwind("observations"));
            List<Criteria> temporalExtentCriterias = new ArrayList<>();
            queryElements.getTemporalExtents().forEach(tmpExtent -> {
                Instant from = Instant.parse("1900-01-01T01:00:00.000Z");
                Instant to = Instant.now();
                if (tmpExtent.getFromDate()!= null) {
                    from = tmpExtent.getFromDate();
                }
                if (tmpExtent.getToDate()!= null) {
                    to = tmpExtent.getToDate();
                }
                temporalExtentCriterias.add(
                        new Criteria().orOperator(
                                new Criteria().andOperator(
                                        Criteria.where("observations.temporalExtent.dateBeg")
                                                .gte(Date.from(from))
                                                .lte(Date.from(to)),
                                        Criteria.where("observations.temporalExtent.dateEnd")
                                                .gte(Date.from(from))
                                                .lte(Date.from(to))
                                ),
                                new Criteria().andOperator(
                                        Criteria.where("observations.temporalExtent.dateBeg")
                                                .lte(Date.from(from))
                                                .lte(Date.from(to)),
                                        Criteria.where("observations.temporalExtent.dateEnd")
                                                .gte(Date.from(from))
                                                .gte(Date.from(to))
                                ),
                                new Criteria().andOperator(
                                        Criteria.where("observations.temporalExtent.dateBeg")
                                                .lte(Date.from(from))
                                                .lte(Date.from(to)),
                                        Criteria.where("observations.temporalExtent.dateEnd")
                                                .gte(Date.from(from))
                                                .lte(Date.from(to))
                                ),
                                new Criteria().andOperator(
                                        Criteria.where("observations.temporalExtent.dateBeg")
                                                .gte(Date.from(from))
                                                .lte(Date.from(to)),
                                        Criteria.where("observations.temporalExtent.dateEnd")
                                                .gte(Date.from(from))
                                                .gte(Date.from(to))
                                )
                        )
                );
            });
            aggregationOperations.add(Aggregation.match(new Criteria().orOperator(temporalExtentCriterias.toArray(new Criteria[temporalExtentCriterias.size()]))));
            /*
             * 3 - Group Operation stage GroupOperation to return the document according to the initial form (before unwind
             * of "observations")
             */
            GroupOperation g1 = group("_id")
                    .push("observations").as("observations")
                    .first("producer").as("producer")
                    .first("dataset").as("dataset")
                    .first("textScore").as("textScore")
                    .first("theiaVariableNameEn").as("theiaVariableNameEn")
                    .first("producerVariableNameEn").as("producerVariableNameEn")
                    .first("theiaVariable").as("theiaVariable")
                    .first("theiaCategories").as("theiaCategories")
                    .first("featureOfInterest").as("featureOfInterest")
                    ;
            aggregationOperations.add(g1);

            /**
             * Remove the TheiaVariable if the field is null
             */
            ProjectionOperation p1 = Aggregation.project("observations", "dataset", "producer", "textScore","theiaVariableNameEn", "producerVariableNameEn", "theiaCategories", "featureOfInterest")
                    .and(ConditionalOperators.ifNull("theiaVariable").then("$$REMOVE")).as("theiaVariable");
            aggregationOperations.add(p1);
        }

        /*
         * Used to filter list item according to "dataset","observation" or "producer"
         */
        if (groupOperationTarget != null) {
            switch (groupOperationTarget) {
                case "producer":
                    aggregationOperations.add(Aggregation.group("producer.producerId"));
                    break;
                case "dataset":
                    aggregationOperations.add(Aggregation.group("dataset.datasetId"));
                    break;
                case "samplingFeature":
                    break;
                default:
                    break;
            }
        }
        return aggregationOperations;
    }
}
