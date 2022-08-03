package fr.theialand.insitu.dataportal.repository.mongo.service.search.metadata;

import fr.theialand.insitu.dataportal.repository.mongo.model.DTO.metadataportal.FacetFiltersDTO;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.entity.MapItem;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.entity.ObservationDocument;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.dataset.Dataset;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.dataset.SpatialExtent;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.observation.TheiaVariable;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.producer.Producer;
import fr.theialand.insitu.dataportal.repository.mongo.model.view.ResponseDocument;
import fr.theialand.insitu.dataportal.repository.mongo.repository.AdministrativeFeatureRepository;
import fr.theialand.insitu.dataportal.repository.mongo.repository.MapItemRepository;
import fr.theialand.insitu.dataportal.repository.mongo.repository.MongoDbUtils;
import fr.theialand.insitu.dataportal.repository.mongo.repository.ObservationDocumentRepository;
import org.bson.Document;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Box;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SearchServiceImpl extends AbstractSearchService implements SearchService {
    private final MapItemRepository mapItemRepository;
    private final PageService pageService;
    private final ObservationDocumentRepository observationDocumentRepository;
    private final AdministrativeFeatureRepository administrativeFeatureRepository;

    @Autowired
    public SearchServiceImpl(MongoDbUtils mongoDbUtils, MapItemRepository mapItemRepository, PageService pageService, ObservationDocumentRepository observationDocumentRepository, AdministrativeFeatureRepository administrativeFeatureRepository) {
        super(mongoDbUtils);
        this.mapItemRepository = mapItemRepository;
        this.pageService = pageService;
        this.observationDocumentRepository = observationDocumentRepository;
        this.administrativeFeatureRepository = administrativeFeatureRepository;
    }

    /**
     * Recursive method used to store all value of a GeoJSON coordinantes fields into an array
     *
     * @param coordinates JSONArray representation of the 'coordinantes' fields of a GEoJSON object
     * @param latLngs The List that will be filled using all the position of the 'coordinates' field.
     */
    private void getPointRecursivly(JSONArray coordinates, List<Number[]> latLngs) {
        for (int i = 0; i < coordinates.length(); i++) {
            if (coordinates.optJSONArray(i) != null) {
                getPointRecursivly(coordinates.optJSONArray(i), latLngs);
            } else {
                List<Number> intList = (List<Number>) (List<?>) coordinates.toList();
                Number[] intArray = new Number[intList.size()];
                latLngs.add(intList.toArray(intArray));
            }
        }
    }


    /**
     * Methods to find the list of ObservationDocument matching the list of "documentId" in parameter
     * @param documentIds list of String
     * @return List of ObservationDocument
     */
    @Override
    public List<ObservationDocument> findByDocumentId(List<String> documentIds) {
        List<ObservationDocument> observationDocuments = new ArrayList<>();
        documentIds.forEach(item -> observationDocuments.add(this.observationDocumentRepository.findByDocumentId(item)));
        return observationDocuments;
    }

    /**
     * Get the observationId from the "observationsLite" collection of the document corresponding to a TheiaVariable at
     * a given location.
     *
     * @param queryFilter String representation of a Json object containing the query parameter. ex:
     * {\"uri\":\"https://w3id.org/ozcar-theia/variables/organicCarbon\",\"coordinates\":[6.239739,47.04832,370]}
     * @return List of String corresponding to the ids queried
     */
    @Override
    public List<String> getObservationIdsOfOtherTheiaVariableAtLocation(String queryFilter) {
        /*
         * Parse the json string into a JSONObject
         */
        JSONObject queryFilterJson = new JSONObject(queryFilter);
        List<Number[]> latLngs = new ArrayList<>();
        /*
         * Store the point of the "coordinates" of the location into an array
         */
        getPointRecursivly(queryFilterJson.getJSONArray("coordinates"), latLngs);
        /*
         * Calculate the BBOX of the location
         */
        List<Double> lat = new ArrayList<>();
        List<Double> lon = new ArrayList<>();

        latLngs.forEach(item -> {
            lon.add(item[0].doubleValue());
            lat.add(item[1].doubleValue());
        });
        Double minLong = lon.stream().min(Comparator.comparing(Double::valueOf)).get();
        Double maxLong = lon.stream().max(Comparator.comparing(Double::valueOf)).get();
        Double minLat = lat.stream().min(Comparator.comparing(Double::valueOf)).get();
        Double maxLat = lat.stream().max(Comparator.comparing(Double::valueOf)).get();

        /*
         * Query the observation ids according to the BBOX or the Point of the location and using the uri of the Theia
         * Variable.
         */
        MatchOperation m1;
        Criteria andCriteria = new Criteria();
        if (Objects.equals(minLong, maxLong) && Objects.equals(minLat, maxLat)) {
            Point bottomLeft = new Point(minLong, minLat);
            Point upperRight = new Point(maxLong, maxLat);
            andCriteria.andOperator(Criteria.where("observations.0.observedProperty.theiaVariable.uri").is(queryFilterJson.getString("uri")),
                    Criteria.where("observations.0.featureOfInterest.samplingFeature.geometry").within(new Box(bottomLeft, upperRight)));
            m1 = Aggregation.match(andCriteria);
        } else {
            andCriteria.andOperator(Criteria.where("observations.0.observedProperty.theiaVariable.uri").is(queryFilterJson.getString("uri")),
                    Criteria.where("observations.0.featureOfInterest.samplingFeature.geometry").is(new Point(minLong, minLat)));
            m1 = Aggregation.match(andCriteria);
        }
        UnwindOperation u1 = Aggregation.unwind("observations");
        GroupOperation g1 = Aggregation.group().push("observations.observationId").as("observationId");
        ProjectionOperation p1 = Aggregation.project("observationId").andExclude("_id");

        Document doc = this.mongoDbUtils.aggregateToUnique("observationsLite", Document.class,true, m1,u1,g1,p1);
        return doc.get("observationId",List.class);
    }

    /**
     * Get all the variables measured at the location of a station. The location can either be a point or a bbox.
     *
     * @param coordinatesString String representation of the Json Array contenaining the coordinantes of the geojson
     * object representing the location to be queried
     * @return A list of TheiaVariable
     */
    @Override
    public List<TheiaVariable> getVariablesAtOneLocation(String coordinatesString) {
        /*
         * Parse the String representation of the Json array into a JSONArray
         */
        JSONArray coordinates = new JSONArray(coordinatesString);

        /*
         * Get the list of point of the coordinates if the location in order to calculate the BBOX containg the
         * observation object
         */
        List<Number[]> latLngs = new ArrayList<>();
        getPointRecursivly(coordinates, latLngs);

        /*
         * Calculate the BBOX
         */
        List<Double> lat = new ArrayList<>();
        List<Double> lon = new ArrayList<>();

        latLngs.forEach(item -> {
            lon.add(item[0].doubleValue());
            lat.add(item[1].doubleValue());
        });
        Double minLong = lon.stream().min(Comparator.comparing(Double::valueOf)).get();
        Double maxLong = lon.stream().max(Comparator.comparing(Double::valueOf)).get();
        Double minLat = lat.stream().min(Comparator.comparing(Double::valueOf)).get();
        Double maxLat = lat.stream().max(Comparator.comparing(Double::valueOf)).get();

        /*
         * Create the aggregation pipeline used to query the TheiaVariable at a given location. If the location is a
         * Point or a BBOX, different MatchOperation are generated.
         */
        MatchOperation m1;
        Criteria andCriteria = new Criteria();
        if (Objects.equals(minLong, maxLong) && Objects.equals(minLat, maxLat)) {
            Point bottomLeft = new Point(minLong, minLat);
            Point upperRight = new Point(maxLong, maxLat);
            andCriteria.andOperator(Criteria.where("observations.0.observedProperty.theiaVariable").exists(true), Criteria.where("observations.0.featureOfInterest.samplingFeature.geometry").within(new Box(bottomLeft, upperRight)));
            m1 = Aggregation.match(andCriteria);
        } else {
            andCriteria.andOperator(Criteria.where("observations.0.observedProperty.theiaVariable").exists(true), Criteria.where("observations.0.featureOfInterest.samplingFeature.geometry").is(new Point(minLong, minLat)));
            m1 = Aggregation.match(andCriteria);
        }
        ProjectionOperation p1 = Aggregation.project().and("observations.observedProperty.theiaVariable").as("theiaVariable");
        ReplaceRootOperation rp1 = Aggregation.replaceRoot().withValueOf(ArrayOperators.ArrayElemAt.arrayOf("theiaVariable").elementAt(0));
        return this.mongoDbUtils.aggregateToList("observationsLite", TheiaVariable.class,true,m1,p1,rp1);
    }

    /**
     * Query a list of observation from the "observationsLite" collection using the datasetId
     *
     * @param datasetId datasetId to be queried
     * @return A list of Document. Each document is an list of ObservationLite object: {"observations":[ObservationLite,
     * ObservationLite, ObservationLite]}
     */
    @Override
    public List<Document> getObservationsOfADataset(String datasetId) {
        Criteria andCriteria = new Criteria();
        andCriteria.andOperator(Criteria.where("dataset.datasetId").is(datasetId));
        MatchOperation m1 = Aggregation.match(andCriteria);
        ProjectionOperation p1 = Aggregation.project().and("observations").as("observations");
        return this.mongoDbUtils.aggregateToList("observationsLite",Document.class, false, m1,p1);
    }

    /**
     * Get the spatial extend of a dataset
     * @param datasetId String id of the dataset
     * @return SpatialExtent object
     */
    @Override
    public SpatialExtent getBBOXOfOfADataset(String datasetId) {
        MatchOperation m1 = Aggregation.match(Criteria.where("dataset.datasetId").is(datasetId));
        ReplaceRootOperation r1 = Aggregation.replaceRoot("dataset.metadata.spatialExtent");
        LimitOperation l1 = Aggregation.limit(1);
        return this.mongoDbUtils.aggregateToUnique("observations",SpatialExtent.class,false,m1,r1,l1);

    }


    /**
     * Method used to query the database using query filters. The method query the database, generate the new facets
     * depending on the result, return items to be printed on the map and paginated on results list.
     *
     * @param queryElements String that can be parsed into json defining the query filters
     * @return ResponseDocument document containing the facet classification, the MapItems to be mapped and the
     * paginated results
     */
    @Override
    public ResponseDocument searchObservations(FacetFiltersDTO queryElements) {

        //ResponseDocument Object to be returned
        ResponseDocument responseDocument = new ResponseDocument();
        /*
         * Aggregation pipeline to be executed to find the observation matching the query in "observationsLite"
         * collection.
         */
        List<AggregationOperation> aggregationOperations = setMatchOperationUsingFilters(queryElements, null);

        /*
         * The aggregation pipeline is executed to obtain the list of ObservationLiteDocument corresponding to the
         * query. The following result is Paginated and stored into the ResponseDocument object
         */
        //responseDocument.setObservationDocumentLitePage(this.pageService.getObservationsPage("{\"pageSelected\":1,\"pageSize\":10,\"filters\":"+queryElements+"}"));

        /*
         * Add Unwind and project aggregation operation to the aggregation pipeline in order to return all the
         * observationId matching the query
         */
        UnwindOperation u1 = Aggregation.unwind("observations");
        ProjectionOperation p1 = Aggregation.project().and("observations.observationId").as("observationId").andExclude("_id");
        aggregationOperations.add(u1);
        aggregationOperations.add(p1);

        /*
         * Get the list of the observation ids that are resulting the query.
         */
        //Get the list of "observationId" of ObservationLite collection
        List<String> observationLiteIds = new ArrayList<>();
        this.mongoDbUtils.aggregateToList("observationsLite", Document.class,true,
                aggregationOperations.stream().toArray(AggregationOperation[]::new)).forEach((t) -> {
            observationLiteIds.add(t.get("observationId").toString());
        });
        /*
         * Remove the two last aggregation operation that are not used for the following operations.
         */
        aggregationOperations.remove(u1);
        aggregationOperations.remove(p1);

        /*
         * From the list of ids resulting the query on "observationLite" collection, the list of station mesuring the
         * observtions is queried from "mapItems" collection. The document of the collection "mapItems" that have a
         * field "doucmentIds" containing at least one element of the "documentIdsFromObservationLite" Set object are
         * queried.
         */
        List<MapItem> mapItems = this.mapItemRepository.findByObservationIdsIn(observationLiteIds);
        mapItems.forEach(item -> {
            /*
             * For each document queried from the "mapItems" collection, the ids from the field documentIds that are not
             * present in "documentIdsFromObservationLite" Set object are removed.
             */
            Set<String> observationIdsFromMapItems = new HashSet<>(item.getObservationIds());
            observationIdsFromMapItems.retainAll(observationLiteIds);
            item.setObservationIds(new ArrayList<>(observationIdsFromMapItems));
        });
        /*
         * Store the mapItems and the facets in the ResponseDocument
         */
        responseDocument.setMapItems(mapItems);
        responseDocument.setFacetClassification(setFacetClassification(facetOperation, aggregationOperations));
        return responseDocument;
    }

    @Override
    public List<ObservationDocument> findByDatasetId(String datasetId) {
        return this.observationDocumentRepository.findByDatasetDatasetId( datasetId );
    }

    @Override
    public ObservationDocument findFirstByDatasetId(String datasetId) {
        return this.observationDocumentRepository.findFirstByDatasetDatasetId( datasetId );
    }

    @Override
    public Producer findProducer(String producerId) {
        ObservationDocument obs = this.observationDocumentRepository.findFirstByProducerProducerId( producerId );
        return obs != null ? obs.getProducer() : null ;
    }

    @Override
    public List<Dataset> findDatasetsByProducerId(String producerId) {
        MatchOperation m1  = Aggregation.match( new Criteria("producer.producerId").is( producerId ) );
        GroupOperation g1 = Aggregation.group("$dataset.datasetId").first("$dataset").as("firstDataset");
        ProjectionOperation p1   = Aggregation.project( "$firstDataset.datasetId", "$firstDataset.metadata");

        return this.mongoDbUtils.aggregateToList("observations",Dataset.class,true,m1,g1,p1);
    }
}

