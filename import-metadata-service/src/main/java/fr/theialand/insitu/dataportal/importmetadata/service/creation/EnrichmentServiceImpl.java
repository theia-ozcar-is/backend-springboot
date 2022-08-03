/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.theialand.insitu.dataportal.importmetadata.service.creation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mongodb.client.model.geojson.*;
import fr.theialand.insitu.dataportal.api.cgmwgeology.GeologyWMSClient;
import fr.theialand.insitu.dataportal.api.exception.ClientException;
import fr.theialand.insitu.dataportal.api.koppenclimate.KoppenClimateApiClient;
import fr.theialand.insitu.dataportal.api.koppenclimate.model.KoppenClimate;
import fr.theialand.insitu.dataportal.api.openelevation.OpenElevationClient;
import fr.theialand.insitu.dataportal.api.restcountries.RestCountriesClient;
import fr.theialand.insitu.dataportal.api.restcountries.model.RestCountry;
import fr.theialand.insitu.dataportal.api.scanr.ScanRClient;
import fr.theialand.insitu.dataportal.api.scanr.model.V2FullStructure;
import fr.theialand.insitu.dataportal.importmetadata.Exceptions.ImportException;
import fr.theialand.insitu.dataportal.importmetadata.Exceptions.Iso3116Exception;
import fr.theialand.insitu.dataportal.importmetadata.Exceptions.ScanRException;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.observation.Observation;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.observation.SamplingGeometry;
import fr.theialand.insitu.dataportal.repository.mongo.service.insert.metadata.ImportService;
import org.apache.commons.collections4.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author coussotc
 */
@Service
public class EnrichmentServiceImpl implements EnrichmentService {

    private final ImportService mongoImportService;
    private final ScanRClient scanRClient;
    private final RestCountriesClient countryClient;
    private final OpenElevationClient openElevationClient;
    private final KoppenClimateApiClient koppenClimateApiClient;
    private final GeologyWMSClient geologyWMSClient;

    private static final Logger logger = LoggerFactory.getLogger(EnrichmentServiceImpl.class);
    private static final Marker USER = MarkerFactory.getMarker("USER");

    private final List<RestCountry> restCountries = new ArrayList<>();
    private final List<V2FullStructure> structureList = new ArrayList<>();


    @Autowired
    public EnrichmentServiceImpl(ImportService mongoImportService,
                                 ScanRClient scanRClient,
                                 OpenElevationClient openElevationClient, RestCountriesClient restCountryClient, KoppenClimateApiClient koppenClimateApiClient, GeologyWMSClient geologyWMSClient) {
        this.mongoImportService = mongoImportService;
        this.scanRClient = scanRClient;
        this.countryClient = restCountryClient;
        this.openElevationClient = openElevationClient;
        this.koppenClimateApiClient = koppenClimateApiClient;
        this.geologyWMSClient = geologyWMSClient;
    }

    @Override
    public void enrichObservationsWithVariableAssociationsCollection(String producerId) {
        this.mongoImportService.enrichObservationsWithVariableAssociationsCollection(producerId);
    }

    @Override
    public void enrichObservationsWithAdministrativeFeaturesCollection(String producerId) throws JsonProcessingException {
        this.mongoImportService.enrichObservationsWithAdministrativeFeatureOfInterest(producerId);
    }

    @Override
    public void enrichObservationsWithKoppenClassification(Observation observation) {
        if (observation.getFeatureOfInterest().getSamplingFeature() instanceof SamplingGeometry) {
            if (((SamplingGeometry) observation.getFeatureOfInterest().getSamplingFeature()).getGeometry().getType() == GeoJsonObjectType.POINT) {
                List<Double> values = new ArrayList<>(((Point) ((SamplingGeometry) observation.getFeatureOfInterest().getSamplingFeature()).getGeometry()).getPosition().getValues());
                List<Double> locations = new ArrayList<>();
                locations.add(values.get(1));
                locations.add(values.get(0));
                KoppenClimate koppenClimate =  koppenClimateApiClient.getClimate(locations).getKoppenClimate();
                if (koppenClimate != null) { //The location does not point to the sea on the koppen climate map
                    observation.getFeatureOfInterest().setClimateFeature(koppenClimate.getClassification().getLiteral());
                } else { //The location point to the sea
                    //Checking if we find a climate in the surrounding pixels
                    logger.info("No Koppen climates found at "+ Arrays.toString(locations.toArray()));
                    koppenClimate = findClimateInSurroundingsPixels(locations, 3 );
                    if (koppenClimate == null) {
                        observation.getFeatureOfInterest().setClimateFeature("No data");
                    } else {
                        observation.getFeatureOfInterest().setClimateFeature(koppenClimate.getClassification().getLiteral());
                    }
                }


            } else {
                Position position = getCenterOfGeometry(((SamplingGeometry) observation.getFeatureOfInterest().getSamplingFeature()).getGeometry());
                List<Double> locations = new ArrayList<>();
                locations.add(position.getValues().get(1));
                locations.add(position.getValues().get(0));
                KoppenClimate koppenClimate =  koppenClimateApiClient.getClimate(locations).getKoppenClimate();
                if (koppenClimate != null) { //The location does not point to the sea on the koppen climate map
                    observation.getFeatureOfInterest().setClimateFeature(koppenClimate.getClassification().getLiteral());
                } else { //The location point to the sea
                    //Checking if we find a climate in the surrounding pixels
                    logger.info("No Koppen climates found at "+ Arrays.toString(locations.toArray()));
                    koppenClimate = findClimateInSurroundingsPixels(locations, 3 );
                    if (koppenClimate == null) {
                        observation.getFeatureOfInterest().setClimateFeature("No data");
                    } else {
                        observation.getFeatureOfInterest().setClimateFeature(koppenClimate.getClassification().getLiteral());
                    }
                }
            }
        }
    }

    /**
     * Look for a koppen climate in the surrounding pixel of an initial position. The method will look for a climate
     * up to a certain pixel extent and will return as soon as a koppen climate is found. If no climate is found the method return null
     * @param initialPosition the initial pixel from where to look at the surrounding pixels
     * @param pixelExtent the surrounding pixel level the method will query
     * @return A koppen climate if a climate is found or null
     */
    private KoppenClimate findClimateInSurroundingsPixels(List<Double> initialPosition, int pixelExtent) {
        if (pixelExtent <= 0){
            throw new IllegalArgumentException();
        }
        List<List<Double>> surroundingLocations = new ArrayList<>();
        for (int i = 1 ; i <= pixelExtent; i++) {
            surroundingLocations.add(Arrays.asList(initialPosition.get(0) - 0.008 * pixelExtent, initialPosition.get(1)));
            surroundingLocations.add(Arrays.asList(initialPosition.get(0) + 0.008 * pixelExtent, initialPosition.get(1)));
            surroundingLocations.add(Arrays.asList(initialPosition.get(0) , initialPosition.get(1) - 0.008* pixelExtent));
            surroundingLocations.add(Arrays.asList(initialPosition.get(0) , initialPosition.get(1) + 0.008* pixelExtent));
            surroundingLocations.add(Arrays.asList(initialPosition.get(0) + 0.008* pixelExtent, initialPosition.get(1) + 0.008* pixelExtent));
            surroundingLocations.add(Arrays.asList(initialPosition.get(0) - 0.008* pixelExtent, initialPosition.get(1) + 0.008* pixelExtent));
            surroundingLocations.add(Arrays.asList(initialPosition.get(0) - 0.008* pixelExtent, initialPosition.get(1) - 0.008* pixelExtent));
            surroundingLocations.add(Arrays.asList(initialPosition.get(0) + 0.008* pixelExtent, initialPosition.get(1) - 0.008* pixelExtent));
        }
        int i = 0;
        KoppenClimate koppenClimate = null;
        while(koppenClimate == null && i < surroundingLocations.size() ) {
            koppenClimate =  koppenClimateApiClient.getClimate(surroundingLocations.get(i)).getKoppenClimate();
            i++;
        }
        return koppenClimate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void enrichObservationWithCGMWGeology(Observation observation) {
        if (observation.getFeatureOfInterest().getSamplingFeature() instanceof SamplingGeometry) {
            if (((SamplingGeometry) observation.getFeatureOfInterest().getSamplingFeature()).getGeometry().getType() == GeoJsonObjectType.POINT) {
                List<Double> values = new ArrayList<>(((Point) ((SamplingGeometry) observation.getFeatureOfInterest().getSamplingFeature()).getGeometry()).getPosition().getValues());
                observation.getFeatureOfInterest().setGeologyFeature(geologyWMSClient.getGeology(values).getLitho_en());
            } else {
                Position position = getCenterOfGeometry(((SamplingGeometry) observation.getFeatureOfInterest().getSamplingFeature()).getGeometry());
                observation.getFeatureOfInterest().setGeologyFeature(geologyWMSClient.getGeology(position.getValues()).getLitho_en());
            }
        }
    }


    @Override
    /**
     * {@inheritDoc}
     */
    public void enrichObservationWithElevation(Observation observation) {
        if (observation.getFeatureOfInterest().getSamplingFeature() instanceof SamplingGeometry) {
            switch (((SamplingGeometry) observation.getFeatureOfInterest().getSamplingFeature()).getGeometry().getType()) {
                case POINT: {
                    if (((Point) ((SamplingGeometry) observation.getFeatureOfInterest().getSamplingFeature()).getGeometry()).getPosition().getValues().size() < 3) {
                        List<Double> values = new ArrayList<>(((Point) ((SamplingGeometry) observation.getFeatureOfInterest().getSamplingFeature()).getGeometry()).getPosition().getValues());
                        values.add(Double.valueOf(openElevationClient.getElevation(values.get(1) + "," + values.get(0)).getResults().get(0).getElevation()));
                        ((SamplingGeometry) observation.getFeatureOfInterest().getSamplingFeature()).setGeometry(new Point(new Position(values)));
                    }
                }
                break;
                case LINE_STRING: {
                    List<Position> positions = new ArrayList<>(((LineString) ((SamplingGeometry) observation.getFeatureOfInterest().getSamplingFeature()).getGeometry()).getCoordinates());
                    for (int i = 0; i < positions.size(); i++) {
                        if (positions.get(i).getValues().size() < 3) {
                            List<Double> values = new ArrayList<>(positions.get(i).getValues());
                            values.add(Double.valueOf(
                                    openElevationClient.getElevation(positions.get(i).getValues().get(1) + "," + positions.get(i).getValues().get(0)).getResults().get(0).getElevation())
                            );
                            positions.set(i, new Position(values));
                        }
                    }
                    ((SamplingGeometry) observation.getFeatureOfInterest().getSamplingFeature()).setGeometry(new LineString(positions));
                }
                break;
                case MULTI_LINE_STRING: {
                    List<List<Position>> positions = new ArrayList<>(((MultiLineString) ((SamplingGeometry) observation.getFeatureOfInterest().getSamplingFeature()).getGeometry()).getCoordinates());
                    for (int j = 0; j < positions.size(); j++) {
                        for (int i = 0; i < positions.get(j).size(); i++) {
                            if (positions.get(j).get(i).getValues().size() < 3) {
                                List<Double> values = new ArrayList<>(positions.get(j).get(i).getValues());
                                values.add(Double.valueOf(
                                        openElevationClient.getElevation(positions.get(j).get(i).getValues().get(1) + "," + positions.get(j).get(i).getValues().get(0)).getResults().get(0).getElevation())
                                );
                                positions.get(j).set(i, new Position(values));

                            }
                        }
                    }
                    ((SamplingGeometry) observation.getFeatureOfInterest().getSamplingFeature()).setGeometry(new MultiLineString(positions));
                }
                break;
                case POLYGON: {
                    List<List<Position>> holes = new ArrayList<>(((Polygon) ((SamplingGeometry) observation.getFeatureOfInterest().getSamplingFeature()).getGeometry()).getCoordinates().getHoles());
                    for (int j = 0; j < holes.size(); j++) {
                        for (int i = 0; i < holes.get(j).size(); i++) {
                            if (holes.get(j).get(i).getValues().size() < 3) {
                                List<Double> values = new ArrayList<>(holes.get(j).get(i).getValues());
                                values.add(Double.valueOf(
                                        openElevationClient.getElevation(holes.get(j).get(i).getValues().get(1) + "," + holes.get(j).get(i).getValues().get(0)).getResults().get(0).getElevation())
                                );
                                List<Position> positions = new ArrayList<>(holes.get(j));
                                positions.set(i, new Position(values));
                                holes.set(j, positions);
                            }
                        }
                    }
                    List<Position> exterior = new ArrayList<>(((Polygon) ((SamplingGeometry) observation.getFeatureOfInterest().getSamplingFeature()).getGeometry()).getCoordinates().getExterior());
                    for (int i = 0; i < exterior.size(); i++) {
                        if (exterior.get(i).getValues().size() < 3) {
                            List<Double> values = new ArrayList<>(exterior.get(i).getValues());
                            values.add(Double.valueOf(
                                    openElevationClient.getElevation(exterior.get(i).getValues().get(1) + "," + exterior.get(i).getValues().get(0)).getResults().get(0).getElevation())
                            );
                            exterior.set(i, new Position(values));
                        }
                    }
                    ((SamplingGeometry) observation.getFeatureOfInterest().getSamplingFeature()).setGeometry(new Polygon(exterior, holes.toArray(new ArrayList[0])));
                }
                break;
                case MULTI_POINT: {
                    List<Position> positions = new ArrayList<>(((MultiPoint) ((SamplingGeometry) observation.getFeatureOfInterest().getSamplingFeature()).getGeometry()).getCoordinates());
                    for (int i = 0; i < positions.size(); i++) {
                        if (positions.get(i).getValues().size() < 3) {
                            List<Double> values = new ArrayList<>(positions.get(i).getValues());
                            values.add(Double.valueOf(
                                    openElevationClient.getElevation(positions.get(i).getValues().get(1) + "," + positions.get(i).getValues().get(0)).getResults().get(0).getElevation())
                            );
                            positions.set(i, new Position(values));
                        }
                    }
                    ((SamplingGeometry) observation.getFeatureOfInterest().getSamplingFeature()).setGeometry(new MultiPoint(positions));
                }
                break;
                case MULTI_POLYGON: {
                    List<PolygonCoordinates> polygons = new ArrayList<>(((MultiPolygon) ((SamplingGeometry) observation.getFeatureOfInterest().getSamplingFeature()).getGeometry()).getCoordinates());
                    for (int k = 0; k < polygons.size(); k++) {

                        List<List<Position>> holes = new ArrayList<>(polygons.get(k).getHoles());
                        for (int j = 0; j < holes.size(); j++) {
                            for (int i = 0; i < holes.get(j).size(); i++) {
                                if (holes.get(j).get(i).getValues().size() < 3) {
                                    List<Double> values = new ArrayList<>(holes.get(j).get(i).getValues());
                                    values.add(Double.valueOf(
                                            openElevationClient.getElevation(holes.get(j).get(i).getValues().get(1) + "," + holes.get(j).get(i).getValues().get(0)).getResults().get(0).getElevation())
                                    );
                                    List<Position> positions = new ArrayList<>(holes.get(j));
                                    positions.set(i, new Position(values));
                                    holes.set(j, positions);
                                }
                            }
                        }

                        List<Position> exterior = new ArrayList<>(polygons.get(k).getExterior());
                        for (int i = 0; i < exterior.size(); i++) {
                            if (exterior.get(i).getValues().size() < 3) {
                                List<Double> values = new ArrayList<>(exterior.get(i).getValues());
                                values.add(Double.valueOf(
                                        openElevationClient.getElevation(exterior.get(i).getValues().get(1) + "," + exterior.get(i).getValues().get(0)).getResults().get(0).getElevation())
                                );
                                exterior.set(i, new Position(values));
                            }
                        }

                        polygons.set(k, new PolygonCoordinates(exterior, holes.toArray(new ArrayList[0])));
                    }
                    ((SamplingGeometry) observation.getFeatureOfInterest().getSamplingFeature()).setGeometry(new MultiPolygon(polygons));
                }
            }
        }
    }


    @Override
    public ArrayNode setFundingsUsingScanR(ArrayNode fundings) {
        ArrayNode fundingsReturn = JsonNodeFactory.instance.arrayNode();

        for (JsonNode funding : fundings) {
            try {
                enrichment((ObjectNode) funding);
                fundingsReturn.add(funding);
            } catch (ScanRException scanREx) {
                logger.warn(USER, "can't validate scanR id {}", scanREx.getProblematicId());
                logger.warn("Exception ScanR received", scanREx);
            } catch (Iso3116Exception countryEx) {
                logger.warn(USER, "can't validate country code {}", countryEx.getCode());
                logger.warn("Exception RestCountries received", countryEx);
            }
        }

        return fundingsReturn;
    }


    @Override
    public ArrayNode setContactsUsingScanR(ArrayNode contacts) throws ImportException {

        ArrayNode contactModified = JsonNodeFactory.instance.arrayNode();
        for (JsonNode contactTmp : contacts) {
            ObjectNode contact = (ObjectNode) contactTmp;
            if (contact.has("organisation") && contact.get("organisation").has("idScanR")) {
                ObjectNode organisation = (ObjectNode) contact.get("organisation");

                contact.remove("organisation");
                contact.set("organisation", setOrganisationUsingScanR(organisation));
                contactModified.add(contact);
            } else if (contact.has("idScanR")) {
                contactModified.add(setOrganisationUsingScanR(contact));
            } else {
                contactModified.add(contact);
            }
        }
        return contactModified;
    }


    //########################################################
    // private & package(for tests) visibility methods below
    //########################################################

    /**
     * Enrich an organisation json object read in the pivot data model with the information of scanR v2 API and the country API
     *
     * @param orga A organisation Json object read in the pivot data model (name, acronym, idScanR, iso3166) that will be modified with the scanR API information (label, acronym) and the country API information (country name)
     * @throws ScanRException {@link Iso3116Exception}
     */
    void enrichment(ObjectNode orga) throws ScanRException, Iso3116Exception {
        logger.debug("See if we can enrich the producer info with scanR and/or iso3166");

        // countries stuff
        if (orga.has("iso3166"))
            enrichmentWithIso3166(orga);

        // scanR stuff
        if (orga.has("idScanR") && !"".equals(orga.get("idScanR").asText()))
            enrichmentWithScanR(orga);
    }

    /**
     * Remove iso3166 field from a JsonObject and replace it with the corresponding country and its traduction
     *
     * @param orga JSonObject of the organisation, will be modified with iso3166 field removed and country field added
     */
    void enrichmentWithIso3166(@NonNull ObjectNode orga) throws Iso3116Exception {
        logger.debug("Enriching producer info with iso3166 countries info ..");

        // iso3166 has to be correctly populated with a 2 letter code ! no checking it here.
        String countryId = orga.get("iso3166").asText().toLowerCase();

        Optional<RestCountry> countryCached = this.restCountries.stream().filter(c -> c.getAlpha2Code().equalsIgnoreCase(countryId)).findAny();

        RestCountry country;
        if (countryCached.isPresent()) {
            country = countryCached.get();
        } else {
            logger.debug("no country with code {} found in the cache, we'll have to fetch it form RestCountry WebService", countryId);
            try {
                country = countryClient.getCountryByCode(countryId);
            } catch (ClientException e) {
                throw new Iso3116Exception("restCountry ID: " + countryId + " - unable to retrieve data for this country. Restcountries API is not available or we are having problems with it.", countryId, e);
            }
            if (country == null || country.getName() == null) {
                throw new Iso3116Exception("CountryCode " + countryId + " is not recognized by restCountries webservice", countryId);
            }
            this.restCountries.add(country);
        }

        ObjectNode en = JsonNodeFactory.instance.objectNode();
        ObjectNode fr = JsonNodeFactory.instance.objectNode();
        fr.put("lang", "fr");
        fr.put("text", country.getTranslations().get("fr"));
        en.put("lang", "en");
        en.put("text", country.getName());

        ArrayNode countryArray = JsonNodeFactory.instance.arrayNode();
        countryArray.add(fr);
        countryArray.add(en);

        /*
         * Remove iso3166 field and replace by country field
         */
        orga.remove("iso3166");
        orga.set("country", countryArray);
    }

    /**
     * fetch info from ScanR and consolidate the producer info given.
     * in-place modification og Organizatino
     *
     * @param orga producer info, that will be enriched
     */
    private void enrichmentWithScanR(@NonNull ObjectNode orga) throws ScanRException {
        logger.debug("Enriching producer info with ScanR data, id ScanR {}", orga.get("idScanR").asText());

        String scanRid = orga.get("idScanR").asText();
        V2FullStructure scanRStructure = this.structureList.stream().filter(s -> s.getId().equals(scanRid)).findFirst().orElse(null);
        if (scanRStructure == null) {
            try {
                scanRStructure = scanRClient.getStructure(scanRid);
            } catch (ClientException scanREx) {
                logger.warn(USER, "can't validate or invalidate ScanR id {}", scanRid);
                throw new ScanRException("error when communicating with ScanR", scanRid, scanREx);
            }
            if (scanRStructure == null) {
                logger.warn(USER, "idScanR {} was not found in scanR webservice", scanRid);
                throw new ScanRException("scanR id not found", scanRid);
            }
            this.structureList.add(scanRStructure);
        }

        orga.put("iso3166", "fr");
        orga.put("name", MapUtils.getString(scanRStructure.getLabel(), "default"));
        orga.put("acronym", MapUtils.getString(scanRStructure.getAcronym(), "default"));

        // type is optional according to scanR
        String type = scanRStructure.getLevel();
        switch (Optional.ofNullable(type).orElse("TYPE_NULL")) {
            case "Unité de recherche":
                if (scanRStructure.getAlias() == null) {
                    orga.put("type", "French research unit");
                    break;
                }
                if (scanRStructure.getAlias().stream().anyMatch(a -> a.matches("^UMS\\s{1}.*"))) {
                    orga.put("type", "French federative structure");
                } else { // including UMR
                    orga.put("type", "French research unit");
                }
                break;
            case "Établissement public national à caractère scientifique culturel et professionnel (EPCSCP)":
            case "Autre établissement public national d'enseignement":
                orga.put("type", "French universities and schools");
                break;
            case "Structure fédérative":
                orga.put("type", "French federative structure");
                break;
            case "TYPE_NULL":
                orga.put("type", "Other");
                break;
            case "Établissement public administratif":
            case "Etablissement public à caractère industriel et commercial (EPIC)":
            default:
                orga.put("type", "French research institutes");
                break;
        }
    }

    private ObjectNode setOrganisationUsingScanR(ObjectNode organisation) throws ImportException {
        try {
            enrichment(organisation);
        } catch (Iso3116Exception ex) {
            logger.warn(ex.getMessage());
        }
        return organisation;
    }

    /**
     * Calculate the center of a geojson geometry (all geometry apart POINT)
     * @param geometry a Geojson Geometry
     * @return Position of the center of the geometry
     */
    private Position getCenterOfGeometry(Geometry geometry) {
        switch (geometry.getType()) {
            case LINE_STRING: {
                List<Position> list = ((LineString) geometry).getCoordinates();
                Double minLong = list.stream().min(Comparator.comparing(i -> i.getValues().get(0))).get().getValues().get(0);
                Double maxLong = list.stream().max(Comparator.comparing(i -> i.getValues().get(0))).get().getValues().get(0);
                Double maxLat = list.stream().min(Comparator.comparing(i -> i.getValues().get(1))).get().getValues().get(1);
                Double minLat = list.stream().max(Comparator.comparing(i -> i.getValues().get(1))).get().getValues().get(1);
                return new Position((minLong + maxLong) / 2, (minLat + maxLat) / 2);
            }
            case MULTI_POINT: {
                List<Position> list = ((MultiPoint) geometry).getCoordinates();
                Double minLong = list.stream().min(Comparator.comparing(i -> i.getValues().get(0))).get().getValues().get(0);
                Double maxLong = list.stream().max(Comparator.comparing(i -> i.getValues().get(0))).get().getValues().get(0);
                Double maxLat = list.stream().min(Comparator.comparing(i -> i.getValues().get(1))).get().getValues().get(1);
                Double minLat = list.stream().max(Comparator.comparing(i -> i.getValues().get(1))).get().getValues().get(1);
                return new Position((minLong + maxLong) / 2, (minLat + maxLat) / 2);
            }
            case POLYGON: {
                List<Position> list = ((Polygon) geometry).getExterior();
                Double minLong = list.stream().min(Comparator.comparing(i -> i.getValues().get(0))).get().getValues().get(0);
                Double maxLong = list.stream().max(Comparator.comparing(i -> i.getValues().get(0))).get().getValues().get(0);
                Double maxLat = list.stream().min(Comparator.comparing(i -> i.getValues().get(1))).get().getValues().get(1);
                Double minLat = list.stream().max(Comparator.comparing(i -> i.getValues().get(1))).get().getValues().get(1);
                return new Position((minLong + maxLong) / 2, (minLat + maxLat) / 2);
            }
            case MULTI_LINE_STRING: {
                List<Position> list = ((MultiLineString) geometry).getCoordinates().stream()
                        .flatMap(List::stream)
                        .collect(Collectors.toList());
                Double minLong = list.stream().min(Comparator.comparing(i -> i.getValues().get(0))).get().getValues().get(0);
                Double maxLong = list.stream().max(Comparator.comparing(i -> i.getValues().get(0))).get().getValues().get(0);
                Double maxLat = list.stream().min(Comparator.comparing(i -> i.getValues().get(1))).get().getValues().get(1);
                Double minLat = list.stream().max(Comparator.comparing(i -> i.getValues().get(1))).get().getValues().get(1);
                return new Position((minLong + maxLong) / 2, (minLat + maxLat) / 2);
            }
            case MULTI_POLYGON: {
                List<Position> list = ((MultiPolygon) geometry).getCoordinates().stream()
                        .map(PolygonCoordinates::getExterior).flatMap(List::stream).collect(Collectors.toList());
                Double minLong = list.stream().min(Comparator.comparing(i -> i.getValues().get(0))).get().getValues().get(0);
                Double maxLong = list.stream().max(Comparator.comparing(i -> i.getValues().get(0))).get().getValues().get(0);
                Double maxLat = list.stream().min(Comparator.comparing(i -> i.getValues().get(1))).get().getValues().get(1);
                Double minLat = list.stream().max(Comparator.comparing(i -> i.getValues().get(1))).get().getValues().get(1);
                return new Position((minLong + maxLong) / 2, (minLat + maxLat) / 2);
            }
            default: return null;

        }
    }

}
