package fr.theialand.insitu.dataportal.api.geonetwork.exceptions;

import org.springframework.web.client.ResourceAccessException;

public class GeonetworkLinkException extends GeonetworkException {

    public GeonetworkLinkException(ResourceAccessException linkEx) {
        super(linkEx);
    }
}
