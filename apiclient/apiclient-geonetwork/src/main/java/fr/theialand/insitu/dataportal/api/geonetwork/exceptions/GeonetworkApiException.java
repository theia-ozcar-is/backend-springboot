package fr.theialand.insitu.dataportal.api.geonetwork.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestClientResponseException;

/**
 * Link errors
 */
public class GeonetworkApiException extends GeonetworkException {

    /**
     * 40x, 50x ...
     */
    private int errorCode;

    /**
     * Sometimes, the Exception recieved contains a body.
     * it is often overlooked by RestTemplate which concentrate solely on the status Code
     * but we still have the possibility to store it in here
     */
    private String errorBody ;

    /**
     * Headers can be of some use to decode the body above (content-type)
     */
    private HttpHeaders errorHeaders;

    public GeonetworkApiException(RestClientResponseException apiEx) {
        super("Unexpected error "+apiEx.getRawStatusCode()+" while communicating with Geonetwork", apiEx);

        this.errorBody    = apiEx.getResponseBodyAsString();
        this.errorHeaders = apiEx.getResponseHeaders();
        this.errorCode    = apiEx.getRawStatusCode();
    }

    public GeonetworkApiException(String msg, Exception ex) {
        super(msg, ex);
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorBody() {
        return errorBody;
    }

    public HttpHeaders getErrorHeaders() {
        return errorHeaders;
    }

}
