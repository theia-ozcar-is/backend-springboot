package fr.theialand.insitu.dataportal.etl.transformer;

import fr.theialand.insitu.dataportal.etl.EtlUtils;
import fr.theialand.insitu.dataportal.etl.exception.EtlTransformerException;
import fr.theialand.insitu.dataportal.etl.model.GemetThesaurus;
import fr.theialand.insitu.dataportal.etl.model.TheiaKeywordThesaurus;
import fr.theialand.insitu.dataportal.etl.model.TheiaVariablesThesaurus;
import fr.theialand.insitu.dataportal.etl.model.Thesaurus;
import fr.theialand.insitu.dataportal.model.pivot.enumerations.EnumInspireTheme;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.entity.ObservationDocument;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.dataset.Dataset;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.dataset.Keyword;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.dataset.Metadata;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.observation.ObservedProperty;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.observation.TheiaVariable;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.producer.Producer;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.sis.internal.jaxb.gcx.Anchor;
import org.apache.sis.metadata.iso.citation.DefaultCitation;
import org.apache.sis.metadata.iso.citation.DefaultCitationDate;
import org.apache.sis.metadata.iso.citation.DefaultOnlineResource;
import org.apache.sis.metadata.iso.identification.DefaultKeywordClass;
import org.apache.sis.metadata.iso.identification.DefaultKeywords;
import org.apache.sis.util.iso.DefaultInternationalString;
import org.apache.sis.util.iso.SimpleInternationalString;
import org.opengis.metadata.citation.Citation;
import org.opengis.metadata.citation.DateType;
import org.opengis.metadata.citation.OnLineFunction;
import org.opengis.metadata.identification.KeywordClass;
import org.opengis.metadata.identification.KeywordType;
import org.opengis.metadata.identification.Keywords;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * All things converting KW from Theia to ISO-apache SIS
 * package visibility, because it should not be used directly
 */
class  KeywordTransformer {

    private static final String DATASET_KEYWORDS_TITLE = "Dataset keywords";
//    private static final String THEIA_THESAURUS_TITLE = "OZCAR-Theia variables thesaurus";
//    private static final URI THEIA_THESAURUS_URI      = URI.create("https://w3id.org/ozcar-theia/ozcarTheiaThesaurus");

    /** hiding this constructor */
    private KeywordTransformer() {}

    /**
     * Convert KW from Theia to SIS
     *
     * @param observationDocuments A list of @{@link ObservationDocument} containing all the information to be mapped to keywords
     * @return List of {@link Keywords}, can be empty if input is null or empty
     */
    static List<Keywords> convertKeywords(@NonNull List<ObservationDocument> observationDocuments) {

        Metadata metadata = observationDocuments.get(0).getDataset().getMetadata();

        // some KW might appears as double, may be a bug in GN :
        // https://github.com/inspire-eu-validation/metadata/issues/121

        List<Keywords> isoKeywords = new ArrayList<>();

        // sort props. we should use a Set<Locale,String> one day.
        List<ObservedProperty> observedProperties = observationDocuments.stream().map(o -> o.getObservation().getObservedProperty()).collect(Collectors.toList()); // copy the potentially immutable list to a modifiable one
        observedProperties.sort(
                Comparator.comparing(ObservedProperty::getName,
                    Comparator.comparing(EtlUtils::getENorAnyStringFromI18n)));

        // Theia VAR KW
        List<TheiaVariable> linkedVariables = observedProperties.stream()
                .map(ObservedProperty::getTheiaVariable)
                .filter(Objects::nonNull)
                .distinct()
                .collect(Collectors.toList());
        if ( !linkedVariables.isEmpty() ) {
            isoKeywords.add( convertObservationsLinkedKeywords(linkedVariables));
        }

        // DATASET KW
        if ( metadata.getKeywords() != null && ! metadata.getKeywords().isEmpty())  // because it CAN be null !
            isoKeywords.add(convertDatasetKeywords(metadata.getKeywords()));

        // PRODUCER KW
        // List of producer variable not yet linked to theia variable
        List<ObservedProperty> unlinkedProperties = observedProperties.stream()
                .filter(o->Objects.isNull(o.getTheiaVariable()))
                .distinct()
                .collect(Collectors.toList());
        if ( !unlinkedProperties.isEmpty() ) {
            isoKeywords.add( convertObservationsUnlinkedKeywords(unlinkedProperties));
        }

        // Climate and geologie keywords
        List<String> climates = observationDocuments.stream()
                .map(o -> o.getObservation().getFeatureOfInterest().getClimateFeature())
                .filter(o -> Objects.nonNull(o))
                .distinct()
                .collect(Collectors.toList());
        if(climates != null && climates.size() > 0 ) {
                isoKeywords.add(convertCriteriasIntoKeywords(climates));
        }
        List<String> geologies = observationDocuments.stream()
                .map(o -> o.getObservation().getFeatureOfInterest().getGeologyFeature())
                .filter(o -> Objects.nonNull(o))
                .distinct()
                .collect(Collectors.toList());
        if(geologies != null && geologies.size() > 0 ) {
            isoKeywords.add(convertCriteriasIntoKeywords(geologies));
        }

        return isoKeywords;
    }


    /**
     * convert the inspiretheme to a iso19115 Keyword
     * @param inspireThemeAsString , not null ! it should be mandatory anyway. and an enum, too
     * @return SIS iso19115 {@link Keywords}
     */
    static Keywords convertInspireTheme( @NonNull String inspireThemeAsString ) throws EtlTransformerException {
        EnumInspireTheme inspireTheme ;
        try {
            inspireTheme = EnumInspireTheme.getEnumIgnoringCase(inspireThemeAsString);
        } catch (IllegalArgumentException illegalArgumentException) {
            String msg = "The inspire theme "+ inspireThemeAsString + " is not found in our list of Inspire themes";
            throw new EtlTransformerException( msg , illegalArgumentException);
        }
        DefaultKeywords inspireKw = new DefaultKeywords();

        // would prefer not to use classes from XML package, but seems inevitable
        Anchor inspireAnchor = new Anchor(inspireTheme.getThemeUrl(),inspireTheme.getThemeName());
        inspireKw.getKeywords().add( inspireAnchor );
        inspireKw.setType(KeywordType.THEME);

        inspireKw.setThesaurusName( buildThesaurusCitation( new GemetThesaurus()) );

        return inspireKw;
    }

    /**
     * convert the dataset @{@link Keyword} s to iso19115 ones
     * setting the Theia Thesaurus title to @DATASET_KEYWORDS_TITLE
     * @param datasetKeywords list of @{@link Keyword}
     * @return @{@link Keywords} iso19115
     */
    private static Keywords convertDatasetKeywords( List<Keyword> datasetKeywords) {

        DefaultKeywords isoKw = new DefaultKeywords();
        isoKw.setThesaurusName(buildThesaurusCitation( new TheiaKeywordThesaurus(DATASET_KEYWORDS_TITLE)) );

        for( Keyword kw : datasetKeywords )
            isoKw.getKeywords().add( EtlUtils.createSimpleStringOrAnchor(kw.getKeyword(), kw.getUri()) );

        return isoKw;
    }

    /**
     * Only deal with associated(linked) variables
     * @param theiaVariables list of @{@link TheiaVariable}
     * @return @{@link Keywords} iso19115
     */
    private static Keywords convertObservationsLinkedKeywords(List<TheiaVariable> theiaVariables) {
        DefaultKeywords isoKw = new DefaultKeywords();
        isoKw.setThesaurusName(buildThesaurusCitation(new TheiaVariablesThesaurus() ));

        for(TheiaVariable theiaVariable: theiaVariables) {
            isoKw.getKeywords().add( EtlUtils.createSimpleStringOrAnchor(
                    theiaVariable.getPrefLabel(),
                    theiaVariable.getUri() ) ) ;
        }
        isoKw.setType(KeywordType.THEME);

        return isoKw;
    }

    /**
     * Only deal with NOT associated(linked) variables
     * @param observedProperties list of @{@link ObservedProperty}
     * @return @{@link Keywords} iso19115
     */
    private static Keywords convertObservationsUnlinkedKeywords(List<ObservedProperty> observedProperties) {
        DefaultKeywords isoKw = new DefaultKeywords();
        //isoKw.setThesaurusName(new DefaultCitation(PRODUCER_THESAURUS_TITLE));

        for(ObservedProperty prop: observedProperties) {
            isoKw.getKeywords().add( new SimpleInternationalString(EtlUtils.getENorAnyStringFromI18n(prop.getName()))) ;
        }
        isoKw.setType(KeywordType.THEME);
        return isoKw;
    }

    /**
     * Create special keywords, and give them a "category"
     * used with  Geology or Climate
     * @param keywords geol or climate strings. should be enums.
     * @return @{@link Keywords} iso19115
     */
    private static Keywords convertCriteriasIntoKeywords(List<String> keywords) {
        DefaultKeywords isoKw = new DefaultKeywords();

        for(String kw: keywords) {
            isoKw.getKeywords().add( new SimpleInternationalString(kw)) ;
        }
        isoKw.setType(KeywordType.THEME);
        // KeywordClass is a post 2014 iso 19115 thing. it will be suppressed by our 2007 output.
        KeywordClass kwClass = new DefaultKeywordClass("N/A", null);
        isoKw.setKeywordClass(kwClass);

        return isoKw;
    }

    /**
     * build a static Citation from constants and URL, pointing to a thesaurus
     * to be used in keywords and other places in the metadata
     * @param thesaurus @{@link Thesaurus} internal class for easier dealing with thesauruses
     * @return a Sitation to our thesaurus
     */
    private static Citation buildThesaurusCitation(Thesaurus thesaurus) {
        DefaultCitation theiaThesaurusCitation = new DefaultCitation( thesaurus.getCatalogName() );

        Anchor catalogAnchor = new Anchor(thesaurus.getUrl(), thesaurus.getCatalogName());
        theiaThesaurusCitation.setTitle( catalogAnchor );

        DefaultOnlineResource theiaThesaurusOnline = new DefaultOnlineResource( thesaurus.getUrl() );
        theiaThesaurusOnline.setFunction(OnLineFunction.COMPLETE_METADATA);
        theiaThesaurusOnline.setName(new DefaultInternationalString(thesaurus.getCatalogName() ));
        theiaThesaurusCitation.setOnlineResources( List.of(theiaThesaurusOnline));

        theiaThesaurusCitation.getDates().add(new DefaultCitationDate(thesaurus.getCatalogDate(), DateType.PUBLICATION));

        return theiaThesaurusCitation;
    }


}
