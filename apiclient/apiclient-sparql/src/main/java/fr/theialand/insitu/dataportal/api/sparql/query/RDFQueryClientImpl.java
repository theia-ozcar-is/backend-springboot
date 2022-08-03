package fr.theialand.insitu.dataportal.api.sparql.query;

import fr.theialand.insitu.dataportal.api.sparql.AbstractRDFClient;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.entity.TheiaCategory;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.observation.I18n;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Literal;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Service
public class RDFQueryClientImpl extends AbstractRDFClient implements RDFQueryClient {

    // TODO : try to get mongo classes out of this module

    @Override
    public boolean isThisURIaCategory(@NonNull String uri) {
        String queryString = "PREFIX skos: <http://www.w3.org/2004/02/skos/core#>\n"
                + "SELECT *\n"
                + "FROM <https://w3id.org/ozcar-theia/>\n"
                + "WHERE {\n"
                + "   <https://w3id.org/ozcar-theia/variableCategoriesGroup> skos:member <" + uri + ">\n"
                + "}";
        Query query = QueryFactory.create(queryString);
        try (QueryExecution qExec = QueryExecutionFactory.createServiceRequest(sparqlUrl, query);) {

            ResultSet rs = qExec.execSelect();
            return rs.hasNext();
        }
    }

    @Override
    public String getEnPrefLabel(@NonNull String uri) {
        String queryString = "PREFIX skos: <http://www.w3.org/2004/02/skos/core#> "
                + "SELECT ?o FROM <https://w3id.org/ozcar-theia/> WHERE { ?s ?p ?o . FILTER(?s = <" + uri + "> && ?p = skos:prefLabel)}";
        Query query = QueryFactory.create(queryString);
        try (QueryExecution qExec = QueryExecutionFactory.createServiceRequest(sparqlUrl, query);) {
            ResultSet rs = qExec.execSelect();
            String prefLabelEn = null;
            while(rs.hasNext()){
                Literal literal = rs.next().get("o").asLiteral();
                if("en".equals(literal.getLanguage())){
                    prefLabelEn = literal.getString();
                }
            }
            return prefLabelEn;
        }
    }

    @Override
    public List<TheiaCategory> getAllVariableCategories() {
        String queryString =
                "PREFIX skos: <http://www.w3.org/2004/02/skos/core#>\n" +
                        "SELECT ?categoryURI ?p ?o\n" +
                        "FROM <https://w3id.org/ozcar-theia/>\n" +
                        "WHERE {\n" +
                        "  <https://w3id.org/ozcar-theia/c_341ce66a> skos:narrower+ ?categoryURI .\n" +
                        "  ?categoryURI a <https://w3id.org/ozcar-theia/CategoryOfVariable> .\n" +
                        "  ?categoryURI ?p ?o\n" +
                        "  FILTER(?p = skos:prefLabel || ?p = skos:narrower || ?p = skos:broader || ?p = <https://w3id.org/ozcar-theia/simplifiedLabel> )\n" +
                        "}";
        Query query = QueryFactory.create(queryString);
        try (QueryExecution qExec = QueryExecutionFactory.createServiceRequest(sparqlUrl, query);) {

            ResultSet rs = qExec.execSelect();
            List<TheiaCategory> vcs = new ArrayList<>();

            while (rs.hasNext()) {
                QuerySolution q = rs.next();
                int index = findURIinList(vcs, q.get("categoryURI").asResource().getURI());
                if (index == -1) {
                    TheiaCategory vc = new TheiaCategory();
                    vc.setUri(q.get("categoryURI").asResource().getURI());
                    switch (q.get("p").asResource().getURI()) {
                        case "http://www.w3.org/2004/02/skos/core#broader":
                            List<String> broaders = new ArrayList();
                            broaders.add(q.get("o").asResource().getURI());
                            vc.setBroaders(broaders);
                            break;
                        case "http://www.w3.org/2004/02/skos/core#narrower":
                            List<String> narrowers = new ArrayList();
                            narrowers.add(q.get("o").asResource().getURI());
                            vc.setNarrowers(narrowers);
                            break;
                        case "https://w3id.org/ozcar-theia/simplifiedLabel":
                            List<I18n> simplifiedLabel = new ArrayList();
                            I18n slabel = new I18n(q.get("o").asLiteral().getLanguage(),
                                    q.get("o").asLiteral().getString());
                            simplifiedLabel.add(slabel);
                            vc.setSimplifiedLabel(simplifiedLabel);
                            break;
                        case "http://www.w3.org/2004/02/skos/core#prefLabel":
                            List<I18n> prefLabel = new ArrayList();
                            I18n plabel = new I18n(q.get("o").asLiteral().getLanguage(),
                                    q.get("o").asLiteral().getString());
                            prefLabel.add(plabel);
                            vc.setPrefLabel(prefLabel);
                            break;
                    }
                    vcs.add(vc);
                } else {
                    switch (q.get("p").asResource().getURI()) {
                        case "http://www.w3.org/2004/02/skos/core#broader":
                            List<String> broaders;
                            if (vcs.get(index).getBroaders() == null) {
                                broaders = new ArrayList<>();
                            } else {
                                broaders = vcs.get(index).getBroaders();
                            }
                            broaders.add(q.get("o").asResource().getURI());
                            vcs.get(index).setBroaders(broaders);
                            break;
                        case "http://www.w3.org/2004/02/skos/core#narrower":
                            List<String> narrowers;
                            if (vcs.get(index).getNarrowers() == null) {
                                narrowers = new ArrayList<>();
                            } else {
                                narrowers = vcs.get(index).getNarrowers();
                            }
                            narrowers.add(q.get("o").asResource().getURI());
                            vcs.get(index).setNarrowers(narrowers);
                            break;
                        case "https://w3id.org/ozcar-theia/simplifiedLabel":
                            List<I18n> simplifiedLabel;
                            if (vcs.get(index).getSimplifiedLabel() == null) {
                                simplifiedLabel = new ArrayList<>();
                            } else {
                                simplifiedLabel = vcs.get(index).getPrefLabel();
                            }
                            I18n slabel = new I18n(q.get("o").asLiteral().getLanguage(),
                                    q.get("o").asLiteral().getString());
                            simplifiedLabel.add(slabel);
                            vcs.get(index).setSimplifiedLabel(simplifiedLabel);
                            break;
                        case "http://www.w3.org/2004/02/skos/core#prefLabel":
                            List<I18n> prefLabel;
                            if (vcs.get(index).getPrefLabel() == null) {
                                prefLabel = new ArrayList<>();
                            } else {
                                prefLabel = vcs.get(index).getPrefLabel();
                            }
                            I18n plabel = new I18n(q.get("o").asLiteral().getLanguage(),
                                    q.get("o").asLiteral().getString());
                            prefLabel.add(plabel);
                            vcs.get(index).setPrefLabel(prefLabel);
                            break;
                    }
                }
            }
            return vcs;
        }
    }

    @Override
    public List<String> findCategoryLeafsUri() {
        String queryString = "PREFIX skos: <http://www.w3.org/2004/02/skos/core#>\n" +
                "PREFIX : <https://w3id.org/ozcar-theia/>\n" +
                "\n" +
                "SELECT ?o ?label\n" +
                "FROM <https://w3id.org/ozcar-theia/> \n" +
                "WHERE { \n" +
                "  ?o a :CategoryOfVariable .\n" +
                "  FILTER NOT EXISTS{?o skos:narrower [ a :CategoryOfVariable]}\n" +
                "}";
        Query query = QueryFactory.create(queryString);
        List<String> uris = new ArrayList<>();
        try (QueryExecution qExec = QueryExecutionFactory.createServiceRequest(sparqlUrl, query)) {
            ResultSet rs = qExec.execSelect();

            while (rs.hasNext()) {
                uris.add(rs.next().getResource("o").getURI());
            }
        }
        return uris;
    }

    @Override
    public List<String> findAllVariableUri() {
        String queryString = "PREFIX skos: <http://www.w3.org/2004/02/skos/core#>\n"
                + "SELECT ?variable\n"
                + "FROM <https://w3id.org/ozcar-theia/>\n"
                + "WHERE { \n"
                + "<https://w3id.org/ozcar-theia/variableGroup> skos:member ?variable .\n"
                + "}";
        Query query = QueryFactory.create(queryString);
        List<String> uris = new ArrayList<>();
        try (QueryExecution qExec = QueryExecutionFactory.createServiceRequest(sparqlUrl, query)) {
            ResultSet rs = qExec.execSelect();

            while (rs.hasNext()) {
                uris.add(rs.next().getResource("variable").getURI());
            }
        }
        return uris;
    }

    @Override
    public List<I18n> getPrefLabels(@NonNull String uri) {
        String queryString = "PREFIX skos: <http://www.w3.org/2004/02/skos/core#> "
                + "SELECT ?o FROM <https://w3id.org/ozcar-theia/> WHERE { ?s ?p ?o . FILTER(?s = <" + uri + "> && ?p = skos:prefLabel)}";
        Query query = QueryFactory.create(queryString);
        try (QueryExecution qExec = QueryExecutionFactory.createServiceRequest(sparqlUrl, query);) {
            ResultSet rs = qExec.execSelect();
            List<I18n> i18ns = new ArrayList<>();
            while(rs.hasNext()){
                Literal literal = rs.next().get("o").asLiteral();
                i18ns.add(new I18n(literal.getLanguage(), literal.getString()));
            }
            return i18ns;
        }
    }

    @Override
    public List<String> findFirstBroaderCategories(String uri) {
        String queryString =
                "PREFIX skos: <http://www.w3.org/2004/02/skos/core#>\n" +
                        "PREFIX : <https://w3id.org/ozcar-theia/>\n" +
                        "\n" +
                        "SELECT ?o\n" +
                        "FROM <https://w3id.org/ozcar-theia/> \n" +
                        "WHERE { \n" +
                        "    {\n" +
                        "   \t\tSELECT ?o WHERE {\n" +
                        "            <" + uri + "> skos:broader+ ?o . \n" +
                        "  \t\t\t?o a :CategoryOfVariable .      \n" +
                        "        }\n" +
                        "    }\n" +
                        "    MINUS \n" +
                        "    {\n" +
                        "\t?cat a :CategoryOfVariable .\n" +
                        "    <" + uri + "> skos:broader+ ?cat . \n" +
                        "    ?o skos:narrower ?cat\n" +
                        "    }\n" +
                        "}";
        Query query = QueryFactory.create(queryString);
        try (QueryExecution qExec = QueryExecutionFactory.createServiceRequest(sparqlUrl, query);) {

            ResultSet rs = qExec.execSelect();
            List<String> uris = new ArrayList<>();
            while(rs.hasNext()){
                uris.add(rs.next().getResource("o").getURI());
            }
            return uris;
        }
    }

    @Override
    public List<I18n> getSimplifiedLabels(String uri) {
        String queryString = "PREFIX : <https://w3id.org/ozcar-theia/> \n"
                + "SELECT ?o FROM <https://w3id.org/ozcar-theia/> WHERE { <" + uri + "> :simplifiedLabel ?o .}";
        Query query = QueryFactory.create(queryString);
        try (QueryExecution qExec = QueryExecutionFactory.createServiceRequest(sparqlUrl, query);) {
            ResultSet rs = qExec.execSelect();
            List<I18n> i18ns = new ArrayList<>();
            while(rs.hasNext()){
                Literal literal = rs.next().get("o").asLiteral();
                i18ns.add(new I18n(literal.getLanguage(), literal.getString()));
            }
            return i18ns;
        }
    }

    @Override
    public Boolean isAVariableURI(String uri) {
        String queryString = "PREFIX iadopt: <https://w3id.org/iadopt/ont/> \n"
                + "SELECT * FROM <https://w3id.org/ozcar-theia/> \n" +
                "WHERE { ?s a  iadopt:Variable .\n" +
                "FILTER(?s = <"+uri+">)\n" +
                "}";
        Query query = QueryFactory.create(queryString);
        try (QueryExecution qExec = QueryExecutionFactory.createServiceRequest(sparqlUrl, query);) {

            ResultSet rs = qExec.execSelect();
            return rs.hasNext();
        }
    }

    @Override
    public List<String> findCategoryUri() {
        String queryString = "PREFIX skos: <http://www.w3.org/2004/02/skos/core#>\n" +
                "PREFIX : <https://w3id.org/ozcar-theia/>\n" +
                "\n" +
                "SELECT ?s \n" +
                "FROM <https://w3id.org/ozcar-theia/> \n" +
                "WHERE { \n" +
                "  ?s a :CategoryOfVariable . \n" +
                "}";
        Query query = QueryFactory.create(queryString);
        try (QueryExecution qExec = QueryExecutionFactory.createServiceRequest(sparqlUrl, query);) {

            ResultSet rs = qExec.execSelect();
            List<String> uris = new ArrayList<>();
            while(rs.hasNext()){
                uris.add(rs.next().getResource("s").getURI());
            }
            return uris;
        }
    }

    @Override
    public boolean isDeprecatedCategory(String uri) {
        String queryString = "PREFIX skos: <http://www.w3.org/2004/02/skos/core#> \n" +
                "PREFIX owl: <http://www.w3.org/2002/07/owl#>  \n" +
                "SELECT * FROM <https://w3id.org/ozcar-theia/> \n" +
                "WHERE { ?s skos:broader+  <https://w3id.org/ozcar-theia/variableCategories> .\n" +
                "?s owl:deprecated true .\n" +
                "FILTER(?s = <"+uri+">)\n" +
                "}";
        Query query = QueryFactory.create(queryString);
        try (QueryExecution qExec = QueryExecutionFactory.createServiceRequest(sparqlUrl, query);) {

            ResultSet rs = qExec.execSelect();
            return rs.hasNext();
        }
    }

    @Override
    public boolean isDeprecatedConcept(String uri) {
        String queryString = "PREFIX skos: <http://www.w3.org/2004/02/skos/core#> \n" +
                "PREFIX owl: <http://www.w3.org/2002/07/owl#>  \n" +
                "SELECT * FROM <https://w3id.org/ozcar-theia/> \n" +
                "WHERE { ?s owl:deprecated true .\n" +
                "FILTER(?s = <"+uri+">)\n" +
                "}";
        Query query = QueryFactory.create(queryString);
        try (QueryExecution qExec = QueryExecutionFactory.createServiceRequest(sparqlUrl, query);) {

            ResultSet rs = qExec.execSelect();
            return rs.hasNext();
        }
    }

    @Override
    public String getIsReplacedByUri(String uri) {
        String queryString = "PREFIX skos: <http://www.w3.org/2004/02/skos/core#>\n" +
                "PREFIX dct: <http://purl.org/dc/terms/>\n" +
                "SELECT ?o FROM <https://w3id.org/ozcar-theia/> \n" +
                "WHERE { <" +uri+ "> dct:isReplacedBy  ?o .\n" +
                "}";
        Query query = QueryFactory.create(queryString);
        try (QueryExecution qExec = QueryExecutionFactory.createServiceRequest(sparqlUrl, query);) {

            ResultSet rs = qExec.execSelect();
            if(rs.hasNext()){
               return rs.next().getResource("o").getURI();
            } else {
                return null;
            }
        }
    }

    private boolean hasSkosNarrower(String uri) {
        String queryString = "PREFIX skos: <http://www.w3.org/2004/02/skos/core#>\n"
                + "SELECT *\n"
                + "FROM <https://w3id.org/ozcar-theia/>\n"
                + "WHERE {\n"
                + "   <" + uri + "> skos:narrower ?o\n"
                + "}";
        Query query = QueryFactory.create(queryString);
        try (QueryExecution qExec = QueryExecutionFactory.createServiceRequest(sparqlUrl, query);) {

            ResultSet rs = qExec.execSelect();
            return rs.hasNext();
        }
    }

    private int findURIinList(List<TheiaCategory> vcs, String uri) {
        return IntStream.range(0, vcs.size())
                .filter(i -> uri.equals(vcs.get(i).getUri()))
                .findFirst().orElse(-1);	// return -1 if target is not found
    }

}
