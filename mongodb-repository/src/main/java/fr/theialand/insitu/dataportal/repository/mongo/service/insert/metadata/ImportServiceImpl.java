package fr.theialand.insitu.dataportal.repository.mongo.service.insert.metadata;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.model.geojson.Point;
import fr.theialand.insitu.dataportal.repository.mongo.model.DTO.metadataportal.SamplingFeatureGeomDTO;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.entity.MapItem;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.entity.ObservationDocumentLite;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.entity.VariableAssociation;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.geojsonproperties.AdministrativeFeatureProperties;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.geojsonproperties.KoppenClassifcationFeatureProperties;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.observation.TheiaVariable;
import fr.theialand.insitu.dataportal.repository.mongo.model.view.facet.FacetClassification;
import fr.theialand.insitu.dataportal.repository.mongo.repository.*;
import fr.theialand.insitu.dataportal.repository.mongo.service.GenericAggregationOperation;
import fr.theialand.insitu.dataportal.repository.mongo.service.search.metadata.AbstractFacetService;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.geo.GeoJson;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.util.CloseableIterator;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.graphLookup;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.query.Criteria.where;

@Service
public class ImportServiceImpl extends AbstractFacetService implements ImportService {

   // private MongoDbUtils mongoDbUtils;
    private ObservationDocumentLiteRepository observationDocumentLiteRepository;
    private MapItemRepository mapItemRepository;
    private ObservationDocumentRepository observationDocumentRepository;
    private AdministrativeFeatureRepository administrativeFeatureRepository;
    private FacetsNoFilterRepository facetsNoFilterRepository;
    private ObjectMapper objectMapper;

    private static final Logger LOG = LoggerFactory.getLogger(ImportServiceImpl.class);

    @Autowired
    public ImportServiceImpl(MongoDbUtils mongoDbUtils, ObservationDocumentLiteRepository observationDocumentLiteRepository, MapItemRepository mapItemRepository, ObservationDocumentRepository observationDocumentRepository, AdministrativeFeatureRepository administrativeFeatureRepository, FacetsNoFilterRepository facetsNoFilterRepository,@Qualifier("mongoRepositoryObjectMapper") ObjectMapper objectMapper) {
        super(mongoDbUtils);
        //this.mongoDbUtils = mongoDbUtils;
        this.observationDocumentLiteRepository = observationDocumentLiteRepository;
        this.mapItemRepository = mapItemRepository;
        this.observationDocumentRepository = observationDocumentRepository;
        this.administrativeFeatureRepository = administrativeFeatureRepository;
        this.facetsNoFilterRepository = facetsNoFilterRepository;
        this.objectMapper = objectMapper;
    }

    /**
     * Documents of the "observations" collection are updated with the associations stored in the "variableAssociation" collection
     * Documents of the given producer are updated with theia variable according to the english producer variable name and the theiaCategories associated
     * @param producerId String - id of the producer for which the observations will associated with theia ozcar variables
     */
    @Override
    public void enrichObservationsWithVariableAssociationsCollection(String producerId) {


        MatchOperation m1 = Aggregation.match(where("producer.producerId").is(producerId));

        String projectJson = "{"
                + "	\"documentId\": 1,"
                + " \"producerId\":\"$producer.producerId\","
                + "	\"theiaCategories\": \"$observation.observedProperty.theiaCategories\","
                + "	\"producerVariableNameEn\": {"
                + "		\"$filter\": {"
                + "			\"input\": \"$observation.observedProperty.name\","
                + "			\"as\": \"name\","
                + "			\"cond\": {"
                + "				\"$eq\": [\"$$name.lang\", \"en\"]"
                + "			}"
                + "		}"
                + "	}"
                + "}";

        AggregationOperation p1 = new GenericAggregationOperation("$project", projectJson);

        UnwindOperation u1 = Aggregation.unwind("producerVariableNameEn");

        String lookupJson = "{"
                + "	\"from\": \"variableAssociations\","
                + "	\"let\": {"
                + "		\"observations_producerVariableNameEn\": \"$producerVariableNameEn\","
                + "		\"observations_theiaCategories\": \"$theiaVariable.broaderCategories\","
                + "     \"observations_producerId\":\"$producerId\""
                + "	},"
                + "	\"pipeline\": [{"
                + "			\"$match\": {"
                + "				\"$expr\": {"
                + "					\"$and\": [{"
                + "							\"$eq\": [\"$producerVariableNameEn\", \"$$observations_producerVariableNameEn.text\"]"
                + "						},"
                + "						{"
                + "							\"$eq\": [\"$producerId\", \"$$observations_producerId\"]"
                + "						},"
                + "						{"
                + "							\"$eq\": [\"$theiaCategories\", \"$$observations_theiaCategories\"]"
                + "						}"
                + "					]"
                + "				}"
                + "			}"
                + "		},"
                + "		{"
                + "			\"$project\": {"
                + "				\"theiaVariable\": 1"
                + "			}"
                + "		}"
                + "	],"
                + "	\"as\": \"theiaVariables\""
                + "}";

        AggregationOperation l1 = new GenericAggregationOperation("$lookup", lookupJson);

        UnwindOperation u2 = Aggregation.unwind("theiaVariables");
        ProjectionOperation p2 = Aggregation.project("documentId").and("theiaVariables.theiaVariable").as("theiaVariable");

        LOG.info("Enriching {} with variable associations", producerId);
        List<Document> observations = this.mongoDbUtils.aggregateToList("observations", Document.class, true, m1, p1, u1, l1, u2, p2);
        //List<Document> observations = mongoTemplate.aggregate(Aggregation.newAggregation(m1, p1, u1, l1, u2, p2), "observations", Document.class).getMappedResults();



        observations.forEach(observation -> {
            Query query = Query.query(where("documentId").is(observation.getString("documentId")));
            Update update = Update.update("observation.observedProperty.theiaVariable", observation.get("theiaVariable"));
            update.set("observation.observedProperty.theiaCategories",observation.getEmbedded(Arrays.asList("theiaVariable","broaderCategories"),List.class));
            LOG.debug("Updating {} with variable {}",observation.get("documentId"), observation.get("theiaVariable"));
            this.mongoDbUtils.updateFirst(query, update, "observations");
        });
    }

    @Override
/**
 * Group the Document of a collection by variable at a given location for a given dataset. The resulting document
 * are inserted in a new collection
 *
 * @param inputCollectionName String - the collection name from which document are grouped
 * @param outputCollectionName String - the collection name used to store resulting document of the grouping
 * operation
 * @param producerId String - the producerId of the prodcuer inserting the Document. it is used to remove the
 * relative document previously inserted before update.
 */
    public void groupObservationDocumentsByVariableAtGivenLocationAndInsertObservationDocumentLiteCollection(String producerId) {

        // this.observationDocumentLiteRepository.deleteObservationDocumentLiteByProducerProducerId(producerId);

        /**
         * All observation matching the producer ID
         */
        MatchOperation m1 = Aggregation.match(where("producer.producerId").is(producerId));

        /**
         * Recursivly get the categories from the variableCategories collection
         */
        GraphLookupOperation glu1 = graphLookup("variableCategories")
                .startWith("observation.observedProperty.theiaCategories")
                .connectFrom("broaders")
                .connectTo("uri")
                .as("categoryHierarchy");

        /**
         * Add important fields for the portal facet query at the root of the document
         */
        String addFieldsJson = "{" +
                    "\"theiaVariableUri\": \"$observation.observedProperty.theiaVariable.uri\"," +
                    "\"theiaVariable\": \"$observation.observedProperty.theiaVariable\"," +
                    "\"theiaVariableNameEn\": {" +
                        "\"$arrayElemAt\": [{" +
                            "\"$map\": {" +
                                "\"input\": {" +
                                    "\"$filter\": {" +
                                        "\"input\": \"$observation.observedProperty.theiaVariable.simplifiedLabel\"," +
                                        "\"as\": \"item\"," +
                                        "\"cond\": {" +
                                            "\"$eq\": [\"$$item.lang\", \"en\"]" +
                                        "}" +
                                    "}" +
                                "}," +
                                "\"as\": \"variableTmp\"," +
                                "\"in\": \"$$variableTmp.text\"" +
                            "}" +
                        "}, 0]" +
                    "}," +
                    "\"producerVariableNameEn\": {" +
                        "\"$arrayElemAt\": [{" +
                            "\"$map\": {" +
                                "\"input\": {" +
                                    "\"$filter\": {" +
                                        "\"input\": \"$observation.observedProperty.name\"," +
                                        "\"as\": \"item\"," +
                                        "\"cond\": {" +
                                            "\"$eq\": [\"$$item.lang\", \"en\"]" +
                                        "}" +
                                    "}" +
                                "}," +
                                "\"as\": \"variableTmp\"," +
                                "\"in\": \"$$variableTmp.text\"" +
                            "}" +
                        "}, 0]" +
                    "}," +
                    "\"featureOfInterest\": \"$observation.featureOfInterest\"" +
                "}";
        AggregationOperation a1 = new GenericAggregationOperation("$addFields", addFieldsJson);

        /**
         * Group all the observation by producer, dataset, sampling feature and theiaVariable or producerVariableNameEn
         * when theiaVariable does not exist. The documentId of the observations grouped are push in an array The
         * observedPorperty of the observation grouped are pushed in an array in order to keep the information
         * theiaVariable / theiaCategories for each observation
         *
         * This group operation is complex and not supported by Spring data mongodb. AggregationOperation.class is
         * extended to support Aggregation operation building using Document.class
         */

        String groupJson = "{" +
                        "\"_id\": {" +
                            "\"producerId\": \"$producer.producerId\"," +
                            "\"datasetId\": \"$dataset.datasetId\"," +
                            "\"producerVariableNameEn\": {" +
                                "\"$cond\": [{" +
                                    "\"$not\": [\"$theiaVariableUri\"]" +
                                "}, \"$producerVariableNameEn\", null]" +
                            "}," +
                            "\"theiaVariableNameEn\": {" +
                                "\"$cond\": [{" +
                                    "\"$not\": [\"$theiaVariableUri\"]" +
                                "}, null, \"$theiaVariableNameEn\"]" +
                            "}," +
                            "\"featureOfInterest\": \"$featureOfInterest\"" +
                        "}," +
                        "\"producer\": {" +
                            "\"$first\": \"$producer\"" +
                        "}," +
                        "\"dataset\": {" +
                            "\"$first\": \"$dataset\"" +
                        "}," +
                        "\"observations\": {" +
                            "\"$push\": \"$observation\"" +
                        "}," +
                        "\"theiaVariable\": {" +
                        "    \"$first\":\"$theiaVariable\"" +
                        "}," +
                        "\"theiaVariableNameEn\": {" +
                        "    \"$first\":\"$theiaVariableNameEn\"" +
                        "}," +
                        "\"producerVariableNameEn\": {" +
                        "    $first: {$cond:[{$eq:[\"$theiaVariableNameEn\",null]}" +
                        "    ,'$producerVariableNameEn', \"$$REMOVE\"]}" +
                        "  }," +
                        "\"theiaCategories\":{" +
                        "   \"$first\":\"$categoryHierarchy\"" +
                        "}," +
                        "\"featureOfInterest\": {" +
                        "    \"$first\":\"$featureOfInterest\"" +
                        "}" +
                        "" +
                    "}";
        AggregationOperation g1 = new GenericAggregationOperation("$group", groupJson);

        /**
         * This project operation is not necessary since the unwanted fields won't be mapped to the ObservationLite object
         */
        /**
         * Project the result of the group operation before to be inserted in collection
         */
//        String projectJson = "{"
//                //                + "	\"documentIds\": 1,"
//                + "	\"producer.producerId\": 1,"
//                + "	\"producer.name\": 1,"
//                + "	\"producer.title\": 1,"
//                + "	\"producer.fundings\": 1,"
//                + "	\"dataset.datasetId\": 1,"
//                + "	\"dataset.metadata.portalSearchCriteria\": 1,"
//                + "	\"dataset.metadata.title\": 1,"
//                + "	\"dataset.metadata.keywords\": 1,"
//                + "	\"dataset.metadata.description\": 1,"
//                + "     \"observations\": 1,"
//                //                + "	\"observation.observedProperties\": \"$observedProperties\","
//                //                + "	\"observation.temporalExtents\": \"$temporalExtents\","
//                //                + "	\"observation.featureOfInterest.samplingFeature\": \"$samplingFeature\","
//                + "	\"_id\": 0"
//                + "}";
//        AggregationOperation p2 = new GenericAggregationOperation("$project", projectJson);

        LOG.info("Build ObservationLites and inserting them in the obseravtionsLite Collection using a stream to save memory");
        try( CloseableIterator<ObservationDocumentLite> obsLites = this.mongoDbUtils.aggregateToStream("observations", ObservationDocumentLite.class, m1, glu1, a1, g1) ) {
            while( obsLites.hasNext() ) {
                ObservationDocumentLite obsLite = obsLites.next();
                LOG.debug("saving obsLite of datasetId {}, producerVariableNameEn {}", obsLite.getDataset().getDatasetId(), obsLite.getProducerVariableNameEn());
                this.observationDocumentLiteRepository.save(obsLite);
            }
        }

        LOG.info("insert Indexes in ObservationLite Collection");
        this.observationDocumentLiteRepository.insertIndexes();
        LOG.info("insert Indexes in Observation Collection");
        this.observationDocumentRepository.insertIndexes();
        LOG.info("insert Indexes in MapItem Collection");
        this.mapItemRepository.insertIndexes();
    }

    public void groupObservationDocumentLiteByLocationAndInsertMapItemCollection(String producerId) {

        //this.mapItemRepository.deleteMapItemByProducerId(producerId);
        MatchOperation m1 = Aggregation.match(where("producer.producerId").is(producerId));

        UnwindOperation u1 = Aggregation.unwind("observations");

        ProjectionOperation p1 = Aggregation.project()
                .and("producer.producerId").as("producerId")
                .and("dataset.datasetId").as("datasetId")
                .and("observations.featureOfInterest.samplingFeature").as("samplingFeature")
                .and("observations.observationId").as("observationId");

        //UnwindOperation u1 = Aggregation.unwind("documentIds");
        GroupOperation g1 = group(
                "producerId",
                "datasetId",
                "samplingFeature"
        ).push("observationId").as("observationIds");

        ProjectionOperation p2 = Aggregation.project("observationIds")
                .and("_id.producerId").as("producerId")
                .and("_id.datasetId").as("datasetId")
                .and("_id.samplingFeature").as("samplingFeature")
                .andExclude("_id");

        LOG.info("Group observations at a given Location, building according MapItem list");
        List<MapItem> docs = this.mongoDbUtils.aggregateToList("observationsLite", MapItem.class, true, m1, u1, p1, g1, p2);

        LOG.info("save {} mapItems", docs.size());
        this.mapItemRepository.saveAll(docs);
    }

    /**
     * Update the variableAssociation for a given producer. The producer's documents associated with a theia variable
     * are retrevied from the observation collection and compared with the association stored in the variableAssociation collection.
     * Producer's association that have an theia variable uri and a producer variable name that cannot be found in the documents of the
     * observation collection are set to isActive:false.
     *
     * @param producerId String - the producerId
     */
    @Override
    public void refreshVariableAssociationsWithObservationsCollection(String producerId) {
        MatchOperation m1 = Aggregation.match(where("producer.producerId").is(producerId));
        MatchOperation m2 = Aggregation.match(where("observation.observedProperty.theiaVariable").exists(true));
        ProjectionOperation p1 = Aggregation.project()
                .and(ArrayOperators.Filter.filter("observation.observedProperty.name").as("item")
                        .by(ComparisonOperators.Eq.valueOf("item.lang").equalToValue("en"))).as("producerVariableNameEn")
                .and("observation.observedProperty.theiaVariable").as("theiaVariable");

        UnwindOperation u1 = Aggregation.unwind("producerVariableNameEn");

        ProjectionOperation p2 = Aggregation.project()
                .and("theiaVariable").as("theiaVariable")
                .and("producerVariableNameEn.text").as("producerVariableNameEn");
//
//        GroupOperation g1 = Aggregation.group("producer.producerId", "theiaCategories", "theiaVariable.uri")
//                .first("theiaVariable").as("theiaVariable")
//                .first("producer.producerId").as("producerId")
//                .push("producerVariableNameEn").as("producerVariableNameEn")
//                .first("theiaCategories").as("theiaCategories")
//                .addToSet(true).as("isActive");
//
//        UnwindOperation u2 = Aggregation.unwind("isActive");
//        UnwindOperation u3 = Aggregation.unwind("producerVariableNameEn");
//        ProjectionOperation p3 = Aggregation.project().andExclude("_id");
        GroupOperation g1 = Aggregation.group("theiaVariable.uri","producerVariableNameEn");
        ReplaceRootOperation r1 = Aggregation.replaceRoot("_id");
        LOG.info("get Variable of producer {} to refresh variableAssociations collection", producerId);

        /**
         * Get the combination of theiaVariable URI and producer variable name from observation collection documents that have
         * are associated to a theia variable for a given producer.
         */
        List<Map> tmp = this.mongoDbUtils.aggregateToList("observations", Map.class, true, m1, m2,p1,u1, p2,g1,r1);

        /**
         * Set all document from variableAssociation collection to isActive:false for a given producer
         */
        Query query1 = Query.query(Criteria.where("producerId").is(producerId));
        Update up1 = Update.update("isActive", false);
        this.mongoDbUtils.updateMulti(query1, up1, "variableAssociations");

        /**
         * For each combination of theiaVariable URI and producer variable name that have been found in observation collection for the given producer,
         * if the combination is found in a document of the variable association collection corresponding to the producer, the association is set to
         * isActive:true
         */
        LOG.info("updating variable associations of {}", producerId);
        tmp.forEach(map-> {
            Query query2 = Query.query(Criteria.where("producerId").is(producerId)
                    .andOperator(Criteria.where("producerVariableNameEn")
                            .is(map.get("producerVariableNameEn").toString()),Criteria.where("theiaVariable.uri").is(map.get("uri").toString())));
            Update up2 = Update.update("isActive", true);
            this.mongoDbUtils.updateMulti(query2, up2, "variableAssociations");
        });
    }

    @Override
    public void enrichObservationsWithAdministrativeFeatureOfInterest(String producerId) throws JsonProcessingException {
        List<SamplingFeatureGeomDTO> geometryList = this.observationDocumentRepository.getByProducerIdGroupBySamplingFeatureGeometry(producerId);
        LOG.info("Enriching Observations of {} with administrative features, {} geometries to be enriched", producerId, geometryList.size());
        for (SamplingFeatureGeomDTO geom : geometryList) {
            GeoJson geoJson = this.objectMapper.readValue(geom.getGeometry().toJson(), GeoJson.class);
            //GeoJson geoJson = getGeoJsonObjectGeometryGEOJSON(geom.get("geometry"));
            List<AdministrativeFeatureProperties> administrativeFeatureProperties = new ArrayList<>();
            for (int i = 2; i <= 6; i++) {

                AdministrativeFeatureProperties administrativeFeatureProp = administrativeFeatureRepository.findFirstByGeometryIntersectionAndAdminLevel(geoJson, i);
                if (administrativeFeatureProp != null) {
                    administrativeFeatureProperties.add(administrativeFeatureProp);
                }
            }

            Query query = Query.query(new Criteria("documentId").in(geom.getDocumentIds()));
            //Query query = Query.query(new Criteria("producer.producerId").is(producerId).and("observation.featureOfInterest.samplingFeature.geometry").is(geom));
            Update update = Update.update("observation.featureOfInterest.administrativeFeatures", administrativeFeatureProperties);

            LOG.debug("Updating {} documentsIds with adminFeatures of {} administrative levels",geom.getDocumentIds().size(), administrativeFeatureProperties.size());
            this.mongoDbUtils.updateMulti(query, update, "observations");
        }
    }


    @Override
    public void aggregateObservationDocumentLiteToNoFilterFacetsCollection() {
        FacetClassification facetClassification = this.setFacetClassification(this.facetOperation,new ArrayList<>());
        List<Document> docs = this.mongoDbUtils.findAll("noFilterFacets",Document.class);
        LOG.info("{} facets found, insert facet Classification", docs.size());
        this.facetsNoFilterRepository.insert(facetClassification);
        for (Document doc : docs ){
            LOG.debug("Removing facet {}", doc.getObjectId("_id"));
            this.mongoDbUtils.remove(Query.query(Criteria.where("_id").is(doc.getObjectId("_id"))),"noFilterFacets");
        }
    }


//
//    /**
//     * Refresh document of the "variableAssociations" collection. An association may not be relevant if the corresponding observations
//     * are not existing anymore in a new producer import. Existing association being not relevant anymore are updated with "isActive" property
//     * set to "false".
//     * @param producerId -String ID of the producer
//     */
//    private void refreshAssociationSubmited(String producerId, List<String> uris) {
//
//        Query query = Query.query(where("producerId").is(producerId).and("theiaVariable.uri").nin(uris));
//        Update up1 = Update.update("isActive", false);
//
//        LOG.info("updating variable associations of {}", producerId);
//        this.mongoDbUtils.updateMulti(query, up1, "variableAssociations");
////        LOG.info("insert {} associations", associations.size());
////        this.variableAssociationRepository.insert(associations);
////
////        MatchOperation m1 = Aggregation.match(Criteria.where("producerId").is(producerId));
////
////        GroupOperation g1 = Aggregation.group("producerId", "theiaCategories", "theiaVariable.uri", "producerVariableNameEn")
////                .first("theiaVariable").as("theiaVariable")
////                .first("producerId").as("producerId")
////                .first("theiaCategories").as("theiaCategories")
////                .first("producerVariableNameEn").as("producerVariableNameEn")
////                .addToSet("isActive").as("isActiveArray");
////
////        ConditionalOperators.Cond condOperation = ConditionalOperators.when(Criteria.where("isActiveArray").ne(new Boolean[]{false}))
////                .then(true)
////                .otherwise(false);
////
////        ProjectionOperation p1 = Aggregation.project()
////                .and("theiaVariable").as("theiaVariable")
////                .and("producerId").as("producerId")
////                .and("theiaCategories").as("theiaCategories")
////                .and("producerVariableNameEn").as("producerVariableNameEn")
////                .andExclude("_id")
////                .and(condOperation).as("isActive");
////
////        //List<Document> docs = mongoTemplate.aggregate(Aggregation.newAggregation(m1, g1, p1), "variableAssociations", Document.class).getMappedResults();
////        LOG.info("get variable associations of {}", producerId);
////        List<VariableAssociation> variableAssociations = this.mongoDbUtils.aggregateToList("variableAssociations", VariableAssociation.class, true, m1, g1, p1);
////        //mongoTemplate.remove(query, collectionName);
////        this.variableAssociationRepository.deleteVariableAssociationByProducerId(producerId);
////        //mongoTemplate.insert(docs, collectionName);
////        LOG.info("old variable associations of {} deleted. inserting {} variable associations", producerId, variableAssociations.size());
////        this.variableAssociationRepository.insert(variableAssociations);
//    }

    //    private GeoJson getGeoJsonObjectGeometryGEOJSON(GeometryGeoJSON geometryGeoJSON) {
//        String type = geometryGeoJSON.getType();
//        switch (type) {
//            case "MultiPolygon": {
//                MultiPolygon geom = (MultiPolygon) geometryGeoJSON;
//                List<List<List<List<Double>>>> multiPoly = geom.getCoordinates();
//                List<GeoJsonPolygon> geoJsonPolygons = new ArrayList<>();
//                for (List<List<List<Double>>> poly : multiPoly) {
//                    List<Point> points = new ArrayList<>();
//                    for (List<List<Double>> line : poly) {
//                        for (List<Double> point : line) {
//                            points.add(new Point(point.get(0), point.get(1)));
//                        }
//                    }
//                    geoJsonPolygons.add(new GeoJsonPolygon(points));
//                }
//                return new GeoJsonMultiPolygon(geoJsonPolygons);
//            }
//            case "Polygon": {
//                Polygon geom = (Polygon) geometryGeoJSON;
//                List<List<List<Double>>> poly = geom.getCoordinates();
//                List<Point> points = new ArrayList<>();
//                for (List<List<Double>> line : poly) {
//                    for (List<Double> point : line) {
//                        points.add(new Point(point.get(0), point.get(1)));
//                    }
//                }
//
//                return new GeoJsonPolygon(points);
//            }
//            case "MultiLineString": {
//                MultiLineString geom = (MultiLineString) geometryGeoJSON;
//                List<List<List<Double>>> multiLine = geom.getCoordinates();
//                List<GeoJsonLineString> lines = new ArrayList<>();
//                for (List<List<Double>> line : multiLine) {
//                    List<Point> points = new ArrayList<>();
//                    for (List<Double> point : line) {
//                        points.add(new Point(point.get(0), point.get(1)));
//                    }
//                    lines.add(new GeoJsonLineString(points));
//                }
//                return new GeoJsonMultiLineString(lines);
//            }
//            case "LineString": {
//                LineString geom = (LineString) geometryGeoJSON;
//                List<List<Double>> line = geom.getCoordinates();
//                List<Point> points = new ArrayList<>();
//                for (List<Double> point:line) {
//                        points.add(new Point(point.get(0), point.get(1)));
//                }
//                return new GeoJsonLineString(points);
//            }
//            case "MultiPoint": {
//                MultiPoint geom = (MultiPoint) geometryGeoJSON;
//                List<List<Double>> multiPoint = geom.getCoordinates();
//                List<Point> points = new ArrayList<>();
//                for (List<Double> point:multiPoint) {
//                    points.add(new Point(point.get(0), point.get(1)));
//                }
//                return new GeoJsonMultiPoint(points);
//            }
//            case "Point": {
//                fr.theialand.insitu.dataportal.repository.mongo.model.POJO.geometry.Point geom = (fr.theialand.insitu.dataportal.repository.mongo.model.POJO.geometry.Point) geometryGeoJSON;
//                List<Double> point = geom.getCoordinates();
//                return new GeoJsonPoint(point.get(0), point.get(1));
//            }
//            default:return null;
//        }
//    }

}
