/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.theialand.insitu.dataportal.backendmetadata.controller;

import fr.theialand.insitu.dataportal.repository.mongo.model.DTO.metadataportal.DatasetPageDTO;
import fr.theialand.insitu.dataportal.repository.mongo.model.DTO.metadataportal.FacetFiltersDTO;
import fr.theialand.insitu.dataportal.repository.mongo.model.DTO.metadataportal.PagePayloadDTO;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.entity.MapItem;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.entity.ObservationDocument;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.entity.ObservationDocumentLite;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.dataset.SpatialExtent;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.observation.TheiaVariable;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.producer.Producer;
import fr.theialand.insitu.dataportal.repository.mongo.model.view.ResponseDocument;
import fr.theialand.insitu.dataportal.repository.mongo.model.view.facet.FacetClassification;
import fr.theialand.insitu.dataportal.repository.mongo.model.view.popup.PopupContent;
import fr.theialand.insitu.dataportal.repository.mongo.service.search.metadata.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 *
 * @author coussotc
 */
@RestController
@RequestMapping("/observation")
@CrossOrigin(origins = "*")
public class ObservationsController {

    /**
     * Inject the repository to be queried
     */
    private final PageService pageService;
    private final FacetService facetService;
    private final PopupService popupService;
    private final DetailService detailService;
    private final SearchService searchService;

    @Autowired
    public ObservationsController(PageService pageService, FacetService facetService, PopupService popupService, DetailService detailService, SearchService searchService) {
        this.pageService = pageService;
        this.facetService = facetService;
        this.popupService = popupService;
        this.detailService = detailService;
        this.searchService = searchService;
    }

    /**
     * method used to show detailed information about observations
     *
     * @param documentIds List of string containing the id of the observations to be printed
     * @return observationDocuments object
     */
    @Operation(summary = "Finds detailed observation documents from the collection 'observations'. ",
            description = "The documents are queried using a json array of 'documentId' fields. ex: \"[\"CATC_OBS_CE.Veg_Gh_86\",\"CATC_OBS_CE.Veg_Gh_8\"]\"",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = { @Content(
                        array = @ArraySchema(schema = @Schema(implementation = String.class)),
                        examples = {@ExampleObject(value = "[\"CATC_OBS_CL.Rain_Nig_3\",\"CATC_OBS_CL.Rain_Nig_18\",\"CATC_OBS_CL.Rain_Nig_14\"]")}
                    )
            })
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = { @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ObservationDocument.class))
                    )
            })
    })
    @PostMapping("/showObservationsDetailed")
    public List<ObservationDocument> findByDocumentId(@RequestBody List<String> documentIds) {
        return this.searchService.findByDocumentId(documentIds);
    }

    /**
     * Finds observationId of a TheiaVariable at a given location. The documents are queried from 'ObservationsLite'
     * collection using the uri of the theia vairable and the coordinantes field of a geoJson object
     *
     * @param payload String -
     * {\"uri\":\"https://w3id.org/ozcar-theia/variables/organicCarbon\",\"coordinates\":[6.239739,47.04832,370]}
     * @return List of String corresponding to the ids queried
     */
    @Operation(summary = "Finds observationId of a TheiaVariable at a given location",
            description = "The documents are queried from 'ObservationsLite' collection using the uri of the theia variable and the coordinates field of a geoJson object",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = { @Content(
                            mediaType = "application/json",
                            examples = {@ExampleObject(value = "{\"uri\":\"https://w3id.org/ozcar-theia/variables/organicCarbon\",\"coordinates\":[6.239739,47.04832,370]}")}
                    )
                    }))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = { @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = String.class))
                            )
                    })})
    @PostMapping("/getObservationIdsOfOtherTheiaVariableAtLocation")
    public List<String> getObservationIdsOfOtherTheiaVariableAtLocation(@RequestBody String payload) {
        return this.searchService.getObservationIdsOfOtherTheiaVariableAtLocation(payload);
    }

    /**
     * Finds Theia variable available at a given location. The documents are queried using 'coordinates' field of a
     * geojson object fields. ex: [3.795429,43.64558,0]
     *
     * @param coordinates String value of coordinantes geojsonfields. ex: [3.795429,43.64558,0]
     * @return List of TheiaVariable corresponding to the query.
     */
    @Operation(summary = "Finds Theia variable available at a given location",
            description = "The documents are queried using 'coordinates' field of a geojson object fields. ex: [3.795429,43.64558,0]",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = { @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = String.class),
                            examples = {@ExampleObject(value = "[3.795429,43.64558,0]")}
                    )
                    }))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = { @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = TheiaVariable.class)))
                    })
    })
    @PostMapping("/getVariablesAtOneLocation")
    public List<TheiaVariable> getVariablesAtOneLocation(@RequestBody String coordinates) {
        return this.searchService.getVariablesAtOneLocation(coordinates);
    }

    /**
     * Find all mapItems of a given dataset. Documents are queried from the 'observationsLite' collection using the
     * 'datasetId'
     *
     * @param datasetId String - ex: KARS_DAT_MOSSON-1
     * @return List of Document - Each document is an list of ObservationLite object: {"observations":[ObservationLite,
     * ObservationLite, ObservationLite]}
     */
    @Operation(summary = "Find all mapItems of a given dataset",
            description = "Documents are queried from the 'observationsLite' collection using the 'datasetId'",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = { @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = String.class),
                            examples = {@ExampleObject(value = "KARS_DAT_MOSSON-1")}
                    )
                    }))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = { @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Document.class)))
                    })
    })
    @GetMapping("/getObservationsOfADataset/{datasetId}")
    public List<Document> getObservationsOfADataset(@PathVariable String datasetId) {
        return this.searchService.getObservationsOfADataset(datasetId);
    }

    /**
     * Find all mapItems of a given dataset. Documents are queried from the 'mapItems' collection using the 'datasetId'
     *
     * @param datasetId String - ex: KARS_DAT_MOSSON-1
     * @return List of MapItems
     */
    @Operation(summary = "Find the BBOX of a given dataset",
            description = "Document are queried from the 'observations' collection using the 'datasetId'",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = { @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = String.class),
                            examples = {@ExampleObject(value = "KARS_DAT_MOSSON-1")}
                    )
                    }))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = { @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = MapItem.class)
                            )
                    })
    })
    @GetMapping("/getBBOXOfADataset/{datasetId}")
    public SpatialExtent getBBOXOfOfADataset(@PathVariable String datasetId) {
        return this.searchService.getBBOXOfOfADataset(datasetId);
    }

    /**
     * Find the category branches to be printed in info-panel for a list of category
     *
     * @param uris list of uri of the theia categories
     * @return List of List of List of I18n corresponding to the prefLabel of each Theia categories concept
     */
    @Operation(summary = "Find the category branches of the list of Theia Category",
            description = "Document are queried from the 'observations'",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = { @Content(
                            mediaType = "application/json",
                            array = @ArraySchema( schema = @Schema(implementation = String.class)),
                            examples = {@ExampleObject(value = "[\"https://w3id.org/ozcar-theia/surfaceWaterMajorIons\"]")}
                    )
                    }))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = { @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema  = @Schema(implementation = List.class)))
                    })
    })
    @PostMapping("/getCategoryHierarchies")
    public List<List<TheiaVariable>> getCategoryHierarchies(@RequestBody List<String> uris) {
        return this.facetService.getCategoryHierarchies(uris);
    }

    /**
     * Method used to query the database using defined filters
     *
     * @param payload String that can be parsed into json object containing the filters used to query the database
     * @return ResponseDocument Object
     */
    @Operation(summary = "Query documents from 'ObservationsLite' and 'MapItems' collections using the filters defined by he user",
            description = "The documents are queried using the filters json object. The list of observation and the mapItems document are return",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = { @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = FacetFiltersDTO.class),
                            examples = {@ExampleObject(value = "{\\n\"\n" +
                                    "                                    + \"\t\\\"temporalExtents\\\": [{\\n\"\n" +
                                    "                                    + \"\t\t\\\"position\\\": 1,\\n\"\n" +
                                    "                                    + \"\t\t\\\"fromDate\\\": \\\"1996-07-22T22:00:00.000Z\\\",\\n\"\n" +
                                    "                                    + \"\t\t\\\"toDate\\\": \\\"2019-07-30T22:00:00.000Z\\\"\\n\"\n" +
                                    "                                    + \"\t}],\\n\"\n" +
                                    "                                    + \"\t\\\"spatialExtent\\\": {\\n\"\n" +
                                    "                                    + \"\t\t\\\"type\\\": \\\"FeatureCollection\\\",\\n\"\n" +
                                    "                                    + \"\t\t\\\"features\\\": [{\\n\"\n" +
                                    "                                    + \"\t\t\t\\\"type\\\": \\\"Feature\\\",\\n\"\n" +
                                    "                                    + \"\t\t\t\\\"properties\\\": {},\\n\"\n" +
                                    "                                    + \"\t\t\t\\\"geometry\\\": {\\n\"\n" +
                                    "                                    + \"\t\t\t\t\\\"type\\\": \\\"Polygon\\\",\\n\"\n" +
                                    "                                    + \"\t\t\t\t\\\"coordinates\\\": [\\n\"\n" +
                                    "                                    + \"\t\t\t\t\t[\\n\"\n" +
                                    "                                    + \"\t\t\t\t\t\t[5.87142, 44.944389],\\n\"\n" +
                                    "                                    + \"\t\t\t\t\t\t[5.87142, 45.73686],\\n\"\n" +
                                    "                                    + \"\t\t\t\t\t\t[7.25419, 45.73686],\\n\"\n" +
                                    "                                    + \"\t\t\t\t\t\t[7.25419, 44.944389],\\n\"\n" +
                                    "                                    + \"\t\t\t\t\t\t[5.87142, 44.944389]\\n\"\n" +
                                    "                                    + \"\t\t\t\t\t]\\n\"\n" +
                                    "                                    + \"\t\t\t\t]\\n\"\n" +
                                    "                                    + \"\t\t\t}\\n\"\n" +
                                    "                                    + \"\t\t}]\\n\"\n" +
                                    "                                    + \"\t},\\n\"\n" +
                                    "                                    + \"\t\\\"climates\\\": [\\\"Mountain climate\\\"],\\n\"\n" +
                                    "                                    + \"\t\\\"geologies\\\": [\\\"Plutonic rocks\\\"],\\n\"\n" +
                                    "                                    + \"\t\\\"producerNames\\\": [\\\"CRYOBS-CLIM\\\"],\\n\"\n" +
                                    "                                    + \"\t\\\"fundingNames\\\": [],\\n\"\n" +
                                    "                                    + \"\t\\\"fundingAcronyms\\\": [],\\n\"\n" +
                                    "                                    + \"\t\\\"fullText\\\": null,\\n\"\n" +
                                    "                                    + \"\t\\\"theiaCategories\\\": [\\\"https://w3id.org/ozcar-theia/solidPrecipitation\\\", \\\"https://w3id.org/ozcar-theia/liquidPrecipitation\\\", \\\"https://w3id.org/ozcar-theia/atmosphericRadiation\\\", \\\"https://w3id.org/ozcar-theia/atmosphericWaterVapor\\\", \\\"https://w3id.org/ozcar-theia/atmosphericChemistry\\\", \\\"https://w3id.org/ozcar-theia/atmosphericTemperature\\\", \\\"https://w3id.org/ozcar-theia/atmosphericPressure\\\", \\\"https://w3id.org/ozcar-theia/wind\\\", \\\"https://w3id.org/ozcar-theia/surfaceFluxes\\\"],\\n\"\n" +
                                    "                                    + \"\t\\\"theiaVariables\\\": []\\n\"\n" +
                                    "                                    + \"}\"")}
                    )
                    }))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = { @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = FacetClassification.class))
                    })

    })
    @PostMapping("/searchObservations")
    public ResponseDocument searchObservations(@RequestBody FacetFiltersDTO payload) {
        return this.searchService.searchObservations(payload);
        //return null;
    }

    /**
     * Method used to initialise the facet using the entier database
     *
     * @return FacetClassification containing the facet elements calculated
     */
    @Operation(summary = "Initialize the facet elements",
            description = "The facet elements and their count are calculated over the entire 'ObservationsLite' collection")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = { @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = FacetClassification.class))
                    })

    })
    @GetMapping("/initFacets")
    public FacetClassification initFacets() {
        return this.facetService.initFacets();
    }

    /**
     * Method used to change the page of the paginated ObservationDocumentLite resulting from a query
     *
     * @param payload String that can be parsed into json object containg the filters used to query the database, the
     * pageSelected and the number observation per page
     * @return Page of ObservationDocumentLite object
     */
    @Operation(summary = "Find the document to print in tte observation from 'ObservationsLite' collection",
            description = "The documents are queried using the filters json object, the page size and the page number fields",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = { @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = PagePayloadDTO.class),
                    examples = {@ExampleObject(value = "{\"filters\":{\"temporalExtents\":[],\"spatialExtent\":null,\"climates\":[],\"geologies\":[\"Quartenary soils\"],\"producerNames\":[],\"fundingNames\":[],\"fundingAcronyms\":[],\"fullText\":null,\"theiaCategories\":[],\"theiaVariables\":[]},\"pageSize\":10,\"pageSelected\":2}")}
            )}))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = { @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema  = @Schema(implementation = ObservationDocumentLite.class)))
                    })
    })
    @PostMapping("/changeObservationsPage")
    public Page<ObservationDocumentLite> getObservationsPage(@RequestBody PagePayloadDTO payload) {
        return this.pageService.getObservationsPage(payload);
    }

    /**
     * Method use to load the content of a popup to be printed on ui
     *
     * @param payload String to be parsed into json Object containing the ids of the observation from which variable
     * names need to be printed on the map.
     * @return PopupContent object
     */

    @Operation(summary = "Load the content of a map popup",
            description = "The documents are queried using a json array of 'observationId' fields. ex: \"[\"CATC_OBS_CE.Veg_Gh_86\",\"CATC_OBS_CE.Veg_Gh_8\"]\"",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = { @Content(
                            mediaType = "application/json",
                            array = @ArraySchema( schema = @Schema(implementation = String.class)),
                            examples = {@ExampleObject(value =  "[\"CATC_OBS_CL.Rain_Nig_3\",\"CATC_OBS_CL.Rain_Nig_18\",\"CATC_OBS_CL.Rain_Nig_14\"]")}
                    )}))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = { @Content(
                            mediaType = "application/json",
                            schema  = @Schema(implementation = PopupContent.class)
                            )
                    })
    })
    @PostMapping("/loadPopupContent")
    public PopupContent loadPopupContent(@RequestBody List<String> payload) {
        return this.popupService.getPopupContent(payload);
    }

    /**
     * Methods used to load information of all the producer in order to have a little description about each producer in
     * the producer facet
     *
     * @return List of Producer object
     */
    @Operation(summary = "Load information about each producer",
            description = "Document are queried from the 'observations' collection")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = { @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema  = @Schema(implementation = Producer.class)))
                    })
    })
    @GetMapping("/getProducersInfo")
    public List<Producer> getProducerInfo() {
        return this.facetService.getProducerInfo();
    }

    @Operation(summary = "Load detailed information about one producer",
            description = "Document are queried from the 'observations' collection",
            parameters = {@Parameter(
                    content = { @Content(
                            schema = @Schema(implementation = String.class),
                            examples = {@ExampleObject(value =  "KARS")}
                    )})})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = { @Content(
                            mediaType = "application/json",
                            schema  = @Schema(implementation = Producer.class)
                    )})
    })
    @GetMapping("showProducerDetailed/{producerId}")
    public Producer showProducerDetailed(@PathVariable String producerId) {
        return this.detailService.getProducerDetailed(producerId);

    }

    @Operation(summary = "Load detailed information about one dataset",
            description = "Document are queried from the 'observations' collection",
            parameters = {@Parameter(
                    content = { @Content(
                            schema = @Schema(implementation = String.class),
                            examples = {@ExampleObject(value =  "KARS_DAT_MOSSON-1")}
                    )})})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = { @Content(
                            mediaType = "application/json",
                            schema  = @Schema(implementation = ObservationDocument.class)
                    )
            })
    })
    @GetMapping("showDatasetDetailed/{datasetId}")
    public ObservationDocument showDatasetDetailed(@PathVariable String datasetId) {
        return this.detailService.getDatasetDetailed(datasetId);

    }


    @Operation(summary = "Change page in producer list",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = { @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PagePayloadDTO.class),
                            examples = {@ExampleObject(value =  "{\"filters\":{\"temporalExtents\":[],\"spatialExtent\":null,\"climates\":[],\"geologies\":[\"Quartenary soils\"],\"producerNames\":[],\"fundingNames\":[],\"fundingAcronyms\":[],\"fullText\":null,\"theiaCategories\":[],\"theiaVariables\":[]},\"pageSize\":10,\"pageSelected\":2}")}
                    )}))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = { @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema  = @Schema(implementation = Producer.class))
                    )
                    })
    })
    @PostMapping("changeProducerPage")
    public Page<Producer> getProducerPage(@RequestBody PagePayloadDTO payload) {
        return this.pageService.getProducerPage(payload);

    }

    @Operation(summary = "Change page in dataset list",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = { @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PagePayloadDTO.class),
                            examples = {@ExampleObject(value =  "{\"filters\":{\"temporalExtents\":[],\"spatialExtent\":null,\"climates\":[],\"geologies\":[\"Quartenary soils\"],\"producerNames\":[],\"fundingNames\":[],\"fundingAcronyms\":[],\"fullText\":null,\"theiaCategories\":[],\"theiaVariables\":[]},\"pageSize\":10,\"pageSelected\":2}")}
                    )}))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = { @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema  = @Schema(implementation = DatasetPageDTO.class))
                    )
                    })
    })
    @PostMapping("changeDatasetPage")
    public Page<DatasetPageDTO> getDatastetPage(@RequestBody PagePayloadDTO payload) {
        return this.pageService.getDatasetPage(payload);
    }
}
