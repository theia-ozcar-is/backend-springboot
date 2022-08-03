package fr.theialand.insitu.dataportal.etl.exception;

import fr.theialand.insitu.dataportal.api.geonetwork.exceptions.GeonetworkException;

/**
 * When one of our partner provoked a failure : most proobably mongo or GN.
 */
public class EtlPartnerException extends EtlException {
    public EtlPartnerException(String msg, GeonetworkException gnEx) {
        super(msg, gnEx);
    }
    public EtlPartnerException(String msg, /*todo CustomMongoRepo*/Exception ex) {
        super(msg, ex);
    }

}
