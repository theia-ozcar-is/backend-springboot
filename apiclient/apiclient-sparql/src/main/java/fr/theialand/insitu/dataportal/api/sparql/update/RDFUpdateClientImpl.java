package fr.theialand.insitu.dataportal.api.sparql.update;

import fr.theialand.insitu.dataportal.api.sparql.AbstractRDFClient;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClients;
import org.apache.jena.update.UpdateExecutionFactory;
import org.apache.jena.update.UpdateFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RDFUpdateClientImpl extends AbstractRDFClient implements RDFUpdateClient {

    @Value("${sparql.endpoint.user}")
    private String sparqlUser;

    @Value("${sparql.endpoint.password}")
    private String sparqlPassword;

    /**
     * Insert a new variable in the triple store.
     *
     * @param uri String - uri of the concept variable
     * @param prefLabel String - prefLabel of the concept
     * @param exactMatches List\<String\> - list of uri of exact match concepts
     */
    @Override
    public void insertSkosVariable(@NonNull String uri, @NonNull String prefLabel, List<String> exactMatches) {

        CredentialsProvider credsProvider = new BasicCredentialsProvider();
        Credentials credentials = new UsernamePasswordCredentials(sparqlUser, sparqlPassword);
        credsProvider.setCredentials(AuthScope.ANY, credentials);
        HttpClient httpclient = HttpClients.custom()
                .setDefaultCredentialsProvider(credsProvider)
                .build();

        List<String> updateString = new ArrayList<>();
        updateString.add("PREFIX skos: <http://www.w3.org/2004/02/skos/core#> INSERT DATA { GRAPH <https://w3id.org/ozcar-theia/> {<" + uri + "> a skos:Concept }}");
        updateString.add("PREFIX skos: <http://www.w3.org/2004/02/skos/core#> INSERT DATA { GRAPH <https://w3id.org/ozcar-theia/> {<" + uri + "> skos:inScheme <https://w3id.org/ozcar-theia/ozcarTheiaThesaurus> }}");
        updateString.add("PREFIX skos: <http://www.w3.org/2004/02/skos/core#> INSERT DATA{ GRAPH <https://w3id.org/ozcar-theia/> {<" + uri + "> skos:prefLabel '" + prefLabel + "'@en} }");
        updateString.add("PREFIX skos: <http://www.w3.org/2004/02/skos/core#> INSERT DATA{ GRAPH <https://w3id.org/ozcar-theia/> {<https://w3id.org/ozcar-theia/variableGroup> skos:member <" + uri + "> } }");
        updateString.add("PREFIX skos: <http://www.w3.org/2004/02/skos/core#> INSERT DATA{ GRAPH <https://w3id.org/ozcar-theia/> {<https://w3id.org/ozcar-theia/variables> skos:narrower <" + uri + "> } }");
        updateString.add("PREFIX skos: <http://www.w3.org/2004/02/skos/core#> INSERT DATA { GRAPH <https://w3id.org/ozcar-theia/> {<" + uri + "> skos:broader <https://w3id.org/ozcar-theia/variables>}}");

        for (String exactMatchUri : exactMatches) {
            updateString.add("PREFIX skos: <http://www.w3.org/2004/02/skos/core#>  INSERT DATA { GRAPH <https://w3id.org/ozcar-theia/> {<" + uri + "> skos:exactMatch <" + exactMatchUri + ">}}");
        }
        for (String s : updateString) {
            UpdateExecutionFactory.createRemote(UpdateFactory.create(s), sparqlUrl, httpclient).execute();
        }
    }

    @Override
    public void instertSkosBroaders(@NonNull String uri, List<String> categories) {
        CredentialsProvider credsProvider = new BasicCredentialsProvider();
        Credentials credentials = new UsernamePasswordCredentials(sparqlUser, sparqlPassword);
        credsProvider.setCredentials(AuthScope.ANY, credentials);
        HttpClient httpclient = HttpClients.custom()
                .setDefaultCredentialsProvider(credsProvider)
                .build();

        String update;

        for (String categoryUri : categories) {
            update = "PREFIX skos: <http://www.w3.org/2004/02/skos/core#>  INSERT DATA { GRAPH <https://w3id.org/ozcar-theia/> {<" + uri + "> skos:broader <" + categoryUri + ">}}";
            UpdateExecutionFactory.createRemote(UpdateFactory.create(update), sparqlUrl, httpclient).execute();
        }
    }

    @Override
    public void removeSkosBroaders(@NonNull String uriVariable, @NonNull String uriCategory) {
        CredentialsProvider credsProvider = new BasicCredentialsProvider();
        Credentials credentials = new UsernamePasswordCredentials(sparqlUser, sparqlPassword);
        credsProvider.setCredentials(AuthScope.ANY, credentials);
        HttpClient httpclient = HttpClients.custom()
                .setDefaultCredentialsProvider(credsProvider)
                .build();
        String update = "PREFIX skos: <http://www.w3.org/2004/02/skos/core#>  DELETE DATA { GRAPH <https://w3id.org/ozcar-theia/> {<" + uriVariable + "> skos:broader <" + uriCategory + ">}}";
        UpdateExecutionFactory.createRemote(UpdateFactory.create(update), sparqlUrl, httpclient).execute();
    }

//    @Override
//    public void removeBroaderLinkbetweenVariableAndNotCategoryLeafs() {
//        CredentialsProvider credsProvider = new BasicCredentialsProvider();
//        Credentials credentials = new UsernamePasswordCredentials(sparqlUser, sparqlPassword);
//        credsProvider.setCredentials(AuthScope.ANY, credentials);
//        HttpClient httpclient = HttpClients.custom()
//                .setDefaultCredentialsProvider(credsProvider)
//                .build();
//
//        String updateString
//                = "PREFIX skos: <http://www.w3.org/2004/02/skos/core#>\n"
//                + "WITH <https://w3id.org/ozcar-theia/>\n"
//                + "DELETE { ?variable ?p ?category }\n"
//                + "WHERE {\n"
//                + "  <https://w3id.org/ozcar-theia/variableGroup> skos:member ?variable .\n"
//                + "  ?variable skos:broader ?category .\n"
//                + "  FILTER (?category != <https://w3id.org/ozcar-theia/variables>) .\n"
//                + "  ?variable ?p ?category .\n"
//                + "  MINUS {\n"
//                + "    <https://w3id.org/ozcar-theia/variableCategoriesGroup> skos:member ?category .\n"
//                + "    MINUS {\n"
//                + "    ?category skos:narrower ?o\n"
//                + "  }\n"
//                + "}\n"
//                + "}";
//        UpdateExecutionFactory.createRemote(UpdateFactory.create(updateString), sparqlUrl, httpclient).execute();
//
//    }

}
