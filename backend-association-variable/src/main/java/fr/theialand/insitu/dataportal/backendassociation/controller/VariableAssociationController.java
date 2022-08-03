package fr.theialand.insitu.dataportal.backendassociation.controller;


import fr.theialand.insitu.dataportal.backendassociation.service.AssociationService;
import fr.theialand.insitu.dataportal.repository.mongo.model.DTO.association.AssociationInformationDTO;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.observation.I18n;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.observation.ObservedProperty;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.observation.TheiaVariable;
import fr.theialand.insitu.dataportal.repository.mongo.model.view.ProducerStat;
import fr.theialand.insitu.dataportal.repository.mongo.service.associate.variable.AssociationOperationService;
import fr.theialand.insitu.dataportal.repository.mongo.service.associate.variable.AssociationViewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * @author coussotc
 */
@RestController
@RequestMapping("/association")
@CrossOrigin(origins = "${app.api_host}")
public class VariableAssociationController {
    private final AssociationViewService mongoAssociationViewService;
    private final AssociationOperationService mongoAssociationOperationService;
    private final AssociationService associationService;

    @Autowired
    public VariableAssociationController(AssociationViewService associationViewService, AssociationOperationService associationOperationService, AssociationService associationService) {
        this.mongoAssociationViewService = associationViewService;
        this.mongoAssociationOperationService = associationOperationService;
        this.associationService = associationService;
    }

    /**
     * For each producer, query the number producer variable associated to theia variable and the number of diffrenet
     * producer variable. In order to print the state of association work for each producer.
     *
     * @return List of ProducerStat - one for each prodcuer
     */
    @Operation(summary = "For each producer, query the number producer variable associated to theia variable and the number of different producer variables",
            description = "In order to print the state of association work for each producer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = { @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema  = @Schema(implementation = ProducerStat.class)))
                    })
    })
    @GetMapping("/setProducerStats")
    private List<ProducerStat> setProducerStats() {
        return this.mongoAssociationViewService.getProducerStats();
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
    @Operation(summary = "For a given producer, query the non-associated producer variable name and the associated producer variable name",
            description = "A producer variables are concidered different if for a same 'name' they have different 'unit' or 'theiaCateogries'",
            parameters = {@Parameter(
                    content = { @Content(
                            schema = @Schema(implementation = String.class),
                            examples = {@ExampleObject(value = "KARS")}
                    )})})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = { @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema  = @Schema(implementation = List.class)))
                    })
    })
    @GetMapping("/setProducerVariables")
    private List<List<ObservedProperty>> setProducerVariables(@RequestParam("producerId") String producerId) {
        return this.mongoAssociationViewService.getDifferentProducerVariableSorted(producerId);
    }


    /**
     * Find the Theia Varaiable already associated to one or several cateogories. In order to suggest Theia variable to
     * be associated with for a given producer variable.
     *
     * @param categories List of category uri ex: "["https://w3id.org/ozcar-theia/atmosphericRadiation"]"
     * @return List of Document containing the theia variables
     */
    @Operation(summary = "Find the Theia Variable already associated to one or several categories",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = { @Content(
                            array = @ArraySchema(schema = @Schema(implementation = String.class) ) ,
                            examples = {@ExampleObject(value = "[\"https://w3id.org/ozcar-theia/c_23c8c514\"]")}
                    )}))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = { @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema  = @Schema(implementation = TheiaVariable.class)))
                    })
    })
    @PostMapping("/setVariablesAlreadyAssociatedToCategories")
    private List<TheiaVariable> setVariablesAlreadyAssociatedToCategories(@RequestBody List<String> categories) {
        return this.mongoAssociationViewService.getVariablesAlreadyAssociatedToCategories(categories);
    }

    /**
     * Save association between one or several producer variables and theia variables using the "variableAssociations"
     * collection
     *
     * @param associationInfo a String that can be parsed into json ex:
     * {"producerId":"CATC","associations":[{"variable":{"name":[{"lang":"en","text":"Air
     * Pressure"}],"unit":[{"lang":"en","text":"mbar"}],"theiaVariable":null,"theiaCategories":["https://w3id.org/ozcar-theia/atmosphericPressure"],"oldIndex":4},"uri":"https://w3id.org/ozcar-theia/atmosphericPressure","prefLabel":[{"lang":"en","text":"Atmospheric
     * pressure"}]}]}
     */
    @Operation(summary = "Save association between one or several producer variables and theia variables using the \\\"variableAssociations\\\" collection\"",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = { @Content(
                            schema = @Schema(implementation = AssociationInformationDTO.class)
                    )}))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200")
    })
    @PostMapping("/submitAssociation")
    private void submitAssociation(@RequestBody AssociationInformationDTO associationInfo) {
        this.associationService.submitAssociation(associationInfo);
    }

    /**
     * Get the prefLabel of a skos concept using the uri of the concept
     *
     * @param uri String - uri of the concept
     * @return ResponseEntity<Map<String, String>>
     */
    @Operation(summary = "Get the prefLabel of a skos concept using the uri of the concept",
            parameters = {@Parameter(
                    content = { @Content(
                            schema = @Schema(implementation = String.class),
                            examples = {@ExampleObject(value = "https://w3id.org/ozcar-theia/c_23c8c514")}
                    )})})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = { @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema  = @Schema(implementation = List.class)))
                    })
    })
    @GetMapping("/getPrefLabelUsingURI")
    private ResponseEntity<List<I18n>> getPrefLabelUsingURI(@RequestParam("URI") String uri) {
       return this.associationService.getPrefLabelUsingUri(uri);
    }

    /**
     * Get the skos:broader of a skos:Concept that are of rdf:type CategoryOfVariable using the concept uri
     *
     * @param uri String - uri of the concept
     * @return ResponseEntity<Map<String, String>>
     */
    @Operation(summary = "Get the broader category of a skos variable concept using the uri of the concept",
            parameters = {@Parameter(
                    content = { @Content(
                            schema = @Schema(implementation = String.class),
                            examples = {@ExampleObject(value = "https://w3id.org/ozcar-theia/c_c14e4576")}
                    )})})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = { @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema  = @Schema(implementation = List.class)))
                    })
    })
    @GetMapping("/getBroaderCategoryURIUsingVariableURI")
    private ResponseEntity<List<String>> findFirstLevelBroaderCategory(@RequestParam("URI") String uri) {
        return this.associationService.getBroaderCategoryUsingUri(uri);
    }

    /**
     * Check whether the URI correspond to a iadopt:Variable
     *
     * @param uri String - uri of the concept
     * @return ResponseEntity<Boolean>
     */
    @Operation(summary = "Check whether the concept is a iadopt:Variable",
            parameters = {@Parameter(
                    content = { @Content(
                            schema = @Schema(implementation = String.class),
                            examples = {@ExampleObject(value = "https://w3id.org/ozcar-theia/c_c14e4576")}
                    )})})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = { @Content(
                            schema  = @Schema(implementation = Boolean.class))
                    })
    })
    @GetMapping("/IsAVariableURI")
    private ResponseEntity<Boolean> IsAVariableURI(@RequestParam("URI") String uri) {
        return this.associationService.IsAVariableURI(uri);
    }
}
