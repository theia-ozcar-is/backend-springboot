package fr.theialand.insitu.dataportal.api.sparql;

import org.springframework.beans.factory.annotation.Value;

public abstract class AbstractRDFClient {
    @Value("${sparql.endpoint.url}")
    protected String sparqlUrl;

}
