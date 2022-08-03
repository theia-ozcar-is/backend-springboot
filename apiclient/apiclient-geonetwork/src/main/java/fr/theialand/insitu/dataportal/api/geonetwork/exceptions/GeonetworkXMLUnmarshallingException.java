package fr.theialand.insitu.dataportal.api.geonetwork.exceptions;

public class GeonetworkXMLUnmarshallingException extends GeonetworkException {
    public GeonetworkXMLUnmarshallingException(String msg, Exception jaxbEx) {
        super(msg, jaxbEx);
    }

}
