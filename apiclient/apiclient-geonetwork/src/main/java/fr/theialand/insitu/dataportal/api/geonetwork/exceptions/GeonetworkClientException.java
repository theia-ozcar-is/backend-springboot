package fr.theialand.insitu.dataportal.api.geonetwork.exceptions;

/**
 * "Business" errors , workflow, unexpected high lvl distorsions...
 */
public class GeonetworkClientException extends GeonetworkException {
    public GeonetworkClientException(String errorMessage) {
        super(errorMessage);
    }
}
