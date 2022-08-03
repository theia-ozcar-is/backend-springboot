package fr.theialand.insitu.dataportal.api.sparql.update;

import org.springframework.lang.NonNull;

import java.util.List;

public interface RDFUpdateClient {

    /**
     * Insert a new variable in the triple store.
     *
     * @param uri String - uri of the concept variable
     * @param prefLabel String - prefLabel of the concept
     * @param exactMatches List\<String\> - list of uri of exact match concepts
     */
    void insertSkosVariable(@NonNull String uri, @NonNull String prefLabel, List<String> exactMatches) ;

    void instertSkosBroaders(@NonNull String uri, List<String> categories) ;

    void removeSkosBroaders(@NonNull String uriVariable, @NonNull String uriCategory) ;

//    void removeBroaderLinkbetweenVariableAndNotCategoryLeafs() ;

}
