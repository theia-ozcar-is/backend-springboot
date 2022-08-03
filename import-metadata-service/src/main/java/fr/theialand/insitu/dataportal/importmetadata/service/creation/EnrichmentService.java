package fr.theialand.insitu.dataportal.importmetadata.service.creation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fr.theialand.insitu.dataportal.importmetadata.Exceptions.ImportException;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.observation.Observation;
import org.springframework.context.annotation.ComponentScan;

import java.io.IOException;

@ComponentScan
public interface EnrichmentService {
    /**
     * Set the producer fundings metadata using scanR informations
     * @param fundings JsonArray of the producer.fundigs
     * @return JsonArray of enriched fundings
     */
    ArrayNode setFundingsUsingScanR(ArrayNode fundings) ;
    ArrayNode setContactsUsingScanR(ArrayNode contacts) throws ImportException;
    void enrichObservationsWithVariableAssociationsCollection(String producerId);
    void enrichObservationsWithAdministrativeFeaturesCollection(String producerId) throws JsonProcessingException;

    /**
     * Enrich observation.featureOfInterest.climateFeature with climate from koppen climate classification
     * @param observation Observation class being updated by the method
     * @throws IOException
     */
    void enrichObservationsWithKoppenClassification(Observation observation) throws IOException;
    /**
     * Enrich observation.featureOfInterest.samplingFeature with the elevation if not provided by the producer.
     * Using SRTM 250m
     * @param observation Observation class being updated by the method
     */
    void enrichObservationWithElevation(Observation observation);
    /**
     * Enrich observation.featureOfInterest.geologyFeature with geology from CGMW Bedrock and Structural geology
     * @param observation Observation class being updated by the method
     */
    void enrichObservationWithCGMWGeology(Observation observation);

}
