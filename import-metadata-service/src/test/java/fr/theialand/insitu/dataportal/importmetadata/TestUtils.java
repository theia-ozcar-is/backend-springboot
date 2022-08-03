package fr.theialand.insitu.dataportal.importmetadata;

import org.apache.jena.query.*;

import java.util.ArrayList;
import java.util.List;

public class TestUtils {
    /**
     * Get the list of our uris, live !
     */
    public static List<String> getTheiaUris() {
        List<String> uris = new ArrayList<>();
        String queryString = "PREFIX skos: <http://www.w3.org/2004/02/skos/core#>\n"
                + "SELECT ?s\n"
                + "FROM <https://w3id.org/ozcar-theia/>\n"
                + "WHERE {\n"
                + "  <https://w3id.org/ozcar-theia/variableCategoriesGroup> skos:member ?s .\n"
                + "  MINUS {?s skos:narrower ?o}\n"
                + "}";
        Query query = QueryFactory.create(queryString);
        try (QueryExecution qExec = QueryExecutionFactory.createServiceRequest("https://in-situ.theia-land.fr/fuseki/theia_vocabulary/", query);) {
            ResultSet rs = qExec.execSelect();
            while (rs.hasNext()) {
                uris.add(rs.next().get("s").toString());
            }
        }

        return uris;
    }

}
