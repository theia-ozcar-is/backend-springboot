package fr.theialand.insitu.dataportal.api.geonetwork;

import fr.theialand.insitu.dataportal.api.geonetwork.exceptions.GeonetworkApiException;
import fr.theialand.insitu.dataportal.api.geonetwork.exceptions.GeonetworkException;
import fr.theialand.insitu.dataportal.api.geonetwork.exceptions.GeonetworkXMLMarshallingException;
import fr.theialand.insitu.dataportal.api.geonetwork.model.StagingEnum;
import org.opengis.metadata.Metadata;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.UUID;

/**
 * Entry Point of Geonetwork.
 */
public interface GeonetworkClient {

    /**
     * Publish a Metadata object to the GN server.
     * It is the main feature of this module.
     *
     * @return UUID, to be used in mongodb ?
     * @throws GeonetworkXMLMarshallingException if XML parsing failed
     * @throws GeonetworkApiException        if Network or GN answer was unexpected
     */
    UUID publishDataset(@NonNull Metadata metadata) throws GeonetworkException;

    /**
     *  GET method to fetch a dataset in XML
     *
     * @param identifier a "fileIdentifier" according to iso19115-1
     * @return xml String
     * @throws GeonetworkException
     */
    Metadata getDataset(String identifier) throws GeonetworkException;

    /**
     * List of ids to delete
     * @param idToDelete , varargs
     * @return nb of records deleted, can be different from the array size if some arent found or other errors
     * @throws GeonetworkException
     */
    int deleteDataset(String... idToDelete) throws GeonetworkException;


    /**
     * Get a list of ID (FileIdentifier in the XML) matching given Producer
     * @param fullName name of producer , like "TOUR" or "CATC"
     * @return datasets of producer
     * @throws GeonetworkException
     */
    List<String> searchByProducer(@NonNull String fullName ) throws GeonetworkException;

    /**
    * Check if a user is logged in, if Auth is good,
    *
    * @return true if user is logged in => basic auth was a success
    * @throws GeonetworkException
    */
    boolean amILoggedIn() throws GeonetworkException;

    /**
     * Check if the server is UP, (by getting is Version)
     * and retrun its profile "test" "production ..."
     *
     * @return @{@link StagingEnum}
     * @throws GeonetworkApiException if smthg go wrong on the line
     */
    StagingEnum getEnvironment() throws GeonetworkException;
}

