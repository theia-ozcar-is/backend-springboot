package fr.theialand.insitu.dataportal.api.geonetwork.filter;

import org.springframework.http.HttpMethod;

import java.io.IOException;

/**
 * Custom Jersey filter to deal with the CSRF protection
 * TODO : Renewal of the token !!
 * TODO : we are now doing it with RestTemplate, rewrite a csrf filter !
 */
public class CsrfTokenFilter /*implements ClientRequestFilter*/ {
    public static final String FILTER_HEADER_KEY = "X-XSRF-TOKEN";
    public static final String FILTER_COOKIE_KEY = "XSRF-TOKEN";

    /**
     * Store the current CSRF token value
     * TODO : deal with the renewal !
     */
    private String csrfTokenValue;

    //private static final Logger LOGGER = Logger.getLogger(CsrfTokenFilter.class.getName());
    //private static final Logger LOGGER = LoggerFactory.getLogger(CsrfTokenFilter.class);

    public CsrfTokenFilter(String csrfTokenAddress, HttpMethod httpMethod) {
//        getCsrfTokenFromApi(csrfTokenAddress, httpMethod);
    }

    public CsrfTokenFilter() {}

    /**
     * Geonetwork need both the header AND the CSRF cookie
     *
     * @param requestContext
     * @throws IOException
     * @see "https://www.geonetwork-opensource.org/manuals/trunk/en/customizing-application/misc.html"
     */
//    @Override
//    public void filter(ClientRequestContext requestContext) {
//        //LOGGER.warn(requestContext.getStringHeaders().toString());
//        requestContext.getHeaders().add(FILTER_HEADER_KEY, csrfTokenValue);
//
//        //a cookie is just a header
//        requestContext.getHeaders().add("Cookie", FILTER_COOKIE_KEY + "=" + csrfTokenValue);
//    }

    /**
     * Update the token
     * TODO : deal with the renewa l!!
     * @param csrfTokenAddress as "http://gnAdd:8080/geonetwork/srv/my/url/generate/a/token"
     * @param httpMethod associated method , such as a Delete
     */
//    private void getCsrfTokenFromApi(String csrfTokenAddress, HttpMethod httpMethod) {
//        Response resp = ClientBuilder.newClient()
//                //.register(new LoggingFeature(LOGGER, Level.WARNING, LoggingFeature.Verbosity.PAYLOAD_ANY, 64000))
//                .target(csrfTokenAddress)
//                .request(MediaType.WILDCARD_TYPE).method(httpMethod.toString());
//
//        if (resp.getCookies().get(FILTER_COOKIE_KEY) != null) {
//            this.csrfTokenValue = resp.getCookies().get(FILTER_COOKIE_KEY).getValue();
//        }
//    }
}
