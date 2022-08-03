package fr.theialand.insitu.dataportal.api.geonetwork.exceptions;

/**
 * to be subclassed, depending on the type of error, link, xml, result, businesslogic ...
 * todo: should depends on some TheiaClientException
 */
public abstract class GeonetworkException extends Exception {
    protected GeonetworkException(String desc, Exception ex) {
        super(desc, ex);
    }

    protected GeonetworkException(Exception ex) {
        super(ex);
    }

    protected GeonetworkException(String desc) {
        super(desc);
    }
}
