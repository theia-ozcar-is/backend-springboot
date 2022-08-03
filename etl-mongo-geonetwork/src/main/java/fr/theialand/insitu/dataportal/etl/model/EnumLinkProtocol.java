package fr.theialand.insitu.dataportal.etl.model;

/**
 * Temporary list of accepted protocols in OnlineResources of a 19115
 * Not official !
 */
public enum EnumLinkProtocol {
    HTTP("WWW:LINK-1.0-http--link"),
    WMS("OGC:WMS"),
    WFS("OGC:WFS"),
    SENSORTHINGS("OGC:Sensorthings"); // To be confirmed !


    private final String value;

    EnumLinkProtocol(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value ;
    }
}


