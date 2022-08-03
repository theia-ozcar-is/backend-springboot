package fr.theialand.insitu.dataportal.api.geonetwork.exceptions;

/**
 * When parsing (marshalling) a string into an XML fails before sending this XML to GN
 */
public class GeonetworkXMLMarshallingException extends GeonetworkException{


    public GeonetworkXMLMarshallingException(String msg, Exception jaxbEx) {
        super(msg, jaxbEx);
    }
}
