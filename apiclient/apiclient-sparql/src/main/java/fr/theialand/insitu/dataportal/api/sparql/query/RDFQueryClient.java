package fr.theialand.insitu.dataportal.api.sparql.query;

import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.entity.TheiaCategory;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.observation.I18n;
import org.springframework.lang.NonNull;

import java.util.List;

public interface RDFQueryClient {

    /**
     * Check whether the uri corresponds to a category
     * @param uri
     * @return boolean
     */
    boolean isThisURIaCategory( @NonNull String uri );

    /**
     * Get the skos:prefLabel of a skos:Concept in english langage using its URI
     * @param uri
     * @return String
     */
    String getEnPrefLabel(@NonNull String uri) ;

    /**
     * Get all the Variable categories
     * @return A list of all the variable categories
     */
    List<TheiaCategory> getAllVariableCategories() ;

    /**
     * Find all the categories that are leafs of the category tree
     * @return List of URI
     */
    List<String> findCategoryLeafsUri() ;

    /**
     * Find all the variable concept URI
     * @return List of URI
     */
    List<String> findAllVariableUri() ;

    /**
     * Get the skos:prefLabel of a skos:Concept using its URI
     * @param uri
     * @return String
     */
    List<I18n> getPrefLabels(@NonNull String uri) ;

    /**
     * Get the skos:broader of a skos:Concept that correspond to a category
     * @param uri
     * @return List of URI
     */
    List<String> findFirstBroaderCategories(@NonNull String uri);

    /**
     * Get the thiea-ozcar:simplifiedLabel of a skos:Concept using its URI
     * @param uri
     * @return String
     */
    List<I18n> getSimplifiedLabels(@NonNull String uri) ;

    /**
     * Check whether the URI correspond to a iadopt:Variable
     * @param uri
     * @return Boolean
     */
    Boolean isAVariableURI(@NonNull String uri);

    /**
     * Find all concept that are of rdf:type ozcar-theia:CategoryOfVariable
     * @return List of uri of the concepts that are of rdf:type ozcar-theia:CategoryOfVariable
     */
    List<String> findCategoryUri();

    /**
     * Check whether the URI corresponds to a deprecated category of variable
     * @param uri category of variable URI
     * @return true if the URI corresponds to a deprecated category of variable
     */
    boolean isDeprecatedCategory(@NonNull String uri);

    /**
     * Check whether the URI corresponds to a deprecated concept
     * @param uri of a concept
     * @return true if the URI corresponds to a deprecated category of variable
     */
    boolean isDeprecatedConcept(@NonNull String uri);

    /**
     * Get the URI associated to the dct:isReplacedBy relation of a node
     * if the dct:isReplacedBy doesn't exist for the node it returns null
     * @param uri uri of a node
     * @return URI associated to the dct:isReplacedBy relation of a node or null
     */
    String getIsReplacedByUri(@NonNull String uri);






}
