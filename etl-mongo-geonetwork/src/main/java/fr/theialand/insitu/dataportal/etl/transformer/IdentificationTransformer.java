package fr.theialand.insitu.dataportal.etl.transformer;

import fr.theialand.insitu.dataportal.etl.EtlUtils;
import fr.theialand.insitu.dataportal.etl.exception.EtlTransformerException;
import fr.theialand.insitu.dataportal.model.pivot.enumerations.EnumTopicCategory;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.entity.ObservationDocument;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.enumerations.EnumContactPersonRoles;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.enumerations.EnumEventType;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.dataset.Dataset;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.dataset.Metadata;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.dataset.Person;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.observation.Document;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.observation.I18n;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.observation.ObservedProperty;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.producer.Producer;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.sis.internal.jaxb.gcx.Anchor;
import org.apache.sis.metadata.iso.DefaultIdentifier;
import org.apache.sis.metadata.iso.citation.*;
import org.apache.sis.metadata.iso.identification.DefaultDataIdentification;
import org.apache.sis.metadata.iso.lineage.DefaultLineage;
import org.apache.sis.metadata.iso.maintenance.DefaultScope;
import org.apache.sis.metadata.iso.quality.DefaultConformanceResult;
import org.apache.sis.metadata.iso.quality.DefaultDataQuality;
import org.apache.sis.metadata.iso.quality.DefaultDomainConsistency;
import org.apache.sis.util.iso.DefaultInternationalString;
import org.opengis.metadata.citation.*;
import org.opengis.metadata.identification.Identification;
import org.opengis.metadata.identification.TopicCategory;
import org.opengis.metadata.lineage.Lineage;
import org.opengis.metadata.maintenance.ScopeCode;
import org.opengis.metadata.quality.ConformanceResult;
import org.opengis.metadata.quality.DataQuality;
import org.opengis.metadata.spatial.SpatialRepresentationType;
import org.springframework.lang.NonNull;

import java.net.URI;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

/**
 * All things converting Identification from Theia to ISO-apache SIS
 * package visibility, because it should not be used directly
 */
class IdentificationTransformer {

    /** hiding this constructor */
    private IdentificationTransformer() {}

    /**
     * Gather Id Information from our models.
     *
     * @param dataset our own dataset model
     * @param producer Producer info for when we need info from it
     * @param history htory of the dataset, stored in mongo
     * @param theiaProperties to populate keywords
     * @return {@link Identification} iso19115 id
     * @throws EtlTransformerException if smthg goes sideways
     */
    static Identification getIdentificationFromMetadata(@NonNull List<ObservationDocument> observationDocuments,  Map<EnumEventType,Date> history) throws EtlTransformerException {
        Dataset dataset = observationDocuments.get(0).getDataset();
        Producer producer = observationDocuments.get(0).getProducer();

        Metadata metadata = dataset.getMetadata();

        DefaultDataIdentification id = new DefaultDataIdentification();

        // In case of multilingual metadata, locales are mandatory
        id.setLocalesAndCharsets( getLocales(metadata) );
        id.setExtents(Collections.singleton( ExtentTransformer.getExtentFromMetadata( metadata ) ));
        id.setCitation( getCitationFromMetadata( dataset, history ) );
        id.setAbstract( EtlUtils.convertTheiaI18nString(metadata.getDescription()));
        if( CollectionUtils.isNotEmpty( metadata.getObjective() )) // beware the emptylist !
            id.setPurpose( EtlUtils.convertTheiaI18nString(metadata.getObjective()) );

//        Contact pocContact = ContactTransformer.extractPersonContact( metadata.getContacts(), EnumContactPersonRoles.PRINCIPAL_INVESTIGATOR );
//        Organisation pocOrg = new DefaultOrganisation( EtlUtils.getENorAnyStringFromI18n(producer.getName()), null, null, pocContact) ;
//        id.setPointOfContacts( Collections.singleton( new DefaultResponsibility( Role.POINT_OF_CONTACT, null, pocOrg) ) ) ;
        DefaultContact pocContact = new DefaultContact();
        if (producer.getEmail() != null) {
            Address pocMail = new DefaultAddress();
            pocMail.getElectronicMailAddresses().add(producer.getEmail());
            pocContact.setAddresses(Collections.singleton(pocMail));
        } else {
            pocContact = ContactTransformer.extractPersonContact(producer.getContacts(), EnumContactPersonRoles.PROJECT_LEADER);
        }
        DefaultOrganisation pocOrg = new DefaultOrganisation( EtlUtils.getENorAnyStringFromI18n(producer.getName()), null, null, pocContact) ;
        id.getPointOfContacts().add(new DefaultResponsibility(new DefaultResponsibility( Role.POINT_OF_CONTACT, null, pocOrg)));
        id.getPointOfContacts().addAll(ContactTransformer.getContactsFromData(dataset.getMetadata().getContacts(), producer.getContacts().stream()
                .filter(obj -> obj instanceof Person)
                .map(Person.class::cast).collect(Collectors.toCollection(ArrayList::new)), pocOrg, producer.getEmail()));

        id.setTopicCategories( getTopicCategoryFromMetadata( metadata.getTopicCategories() ));
        id.getDescriptiveKeywords().add(KeywordTransformer.convertInspireTheme(metadata.getInspireTheme()));
        id.getDescriptiveKeywords().addAll(KeywordTransformer.convertKeywords(observationDocuments));

        id.getResourceConstraints().addAll ( ConstraintTransformer.getIsoConstraintsFromConstraint( metadata.getDataConstraint()) );

        id.getSpatialRepresentationTypes().add(SpatialRepresentationType.VECTOR);

        return id;
    }

    /**
     * convert Lineage (w/o quality) from our model to SIS iso19115
     * @param lineage I18n lineage for pivot model
     * @return Lineage ISO19115
     */
    static Collection<Lineage> getLineagesFromMetadataLineage(List<I18n> lineage) {
        DefaultLineage defaultLineage = new DefaultLineage();
        defaultLineage.setStatement(EtlUtils.convertTheiaI18nString( lineage ));
        return Collections.singleton( defaultLineage );
    }

    /**
     * convert Quality (included in lineage in our model) to iso19915
     * @param lineage
     * @return List of @{@link DataQuality}
     */
    static Collection<DataQuality> getQualityFromMetadataLineage(List<I18n> lineage) {
        DefaultDataQuality dataQuality = new DefaultDataQuality(ScopeCode.DATASET);

        DefaultCitation inspireCitation = new DefaultCitation();
        inspireCitation.setTitle(new Anchor(URI.create("http://data.europa.eu/eli/reg/2010/1089"),
                "commission regulation (eu) no 1089/2010 of 23 november 2010 implementing directive 2007/2/ec of the european parliament and of the council as regards interoperability of spatial data sets and services"));
        inspireCitation.getDates().add(new DefaultCitationDate( new GregorianCalendar(2010, Calendar.DECEMBER, 8).getTime() , DateType.PUBLICATION) );

        ConformanceResult conformanceResult = new DefaultConformanceResult(inspireCitation,"See the referenced specification" , true);
        DefaultDomainConsistency report = new DefaultDomainConsistency();
        report.getResults().add(conformanceResult);

        dataQuality.getReports().add(report);

        DefaultLineage isoLineage = new DefaultLineage();
        isoLineage.setScope(new DefaultScope( ScopeCode.DATASET ));
        isoLineage.setStatement(  EtlUtils.convertTheiaI18nString(lineage ));
        dataQuality.setLineage( isoLineage );

        return Collections.singleton(dataQuality);
    }

    // INNER METHODSS

    /**
     * convert topicCategories
     * @param topicCategories List of what should be enums in our model.
     * @return list of @{@link TopicCategory} enums.
     */
    private static Collection<TopicCategory> getTopicCategoryFromMetadata(List<String> topicCategories) {
        // this should aalready be an enum.
        List<EnumTopicCategory> theiaTopics = topicCategories.stream().map(EnumTopicCategory::getEnum).collect(Collectors.toList());

        return theiaTopics.stream().map(e-> TopicCategory.valueOf(e.name())).collect(Collectors.toList());
    }

    /**
     * Gather Citation from MD, in particular dealing with OnlineResources
     * A @{@link Citation} is one of the basis elements of opengis.
     * @param history of the dataset
     * @param dataset @{@link Dataset}
     * @return @{@link Citation}
     */
    private static Citation getCitationFromMetadata(Dataset dataset, Map<EnumEventType,Date> history) throws EtlTransformerException {
        Metadata metadata = dataset.getMetadata();

        DefaultCitation citation = new DefaultCitation(EtlUtils.convertTheiaI18nString( metadata.getTitle() ) );

        String mdID = MetadataTransformer.ID_PREFIX + dataset.getDatasetId();

        citation.setIdentifiers(Collections.singleton(new DefaultIdentifier( mdID )));

        citation.setTitle( EtlUtils.convertTheiaI18nString( metadata.getTitle() ) );

        // setting the CreaDate to the last date of the data ... to be confirmed !
        citation.setDates( getDatesFromHistory( history ) );

        if(CollectionUtils.isNotEmpty( dataset.getMetadata().getDocuments() )  ) { // beware of the null list !
            List<Document> docs = dataset.getMetadata().getDocuments();

            // in 19115:2007, otherCitationDetails can only contains ONE element.
            // if we put multiple elements in this list, it will be serialized by SIS xml'2007 as multiple otherCitationDetails.
            // which will ultimately fail INSPIRE tests.
            String chainedDocs = docs.stream().map(doc -> doc.getType() +": "+ EtlUtils.getENorAnyStringFromI18n( doc.getUrl()) ).collect(Collectors.joining(" ; "));
            citation.setOtherCitationDetails(Collections.singletonList( new DefaultInternationalString(chainedDocs)));
        }
        return citation;
    }

    /**
     * convert the dataset history to ISO 19115 history
     * @param history of the dataset, see @{@link EnumEventType}
     * @return Collection of @{@link CitationDate}
     * @throws EtlTransformerException our usual suspect Exception
     */
    private static Collection<CitationDate> getDatesFromHistory( Map<EnumEventType,Date> history ) throws EtlTransformerException {

        // If no history, let's say we put a CreationDate at Now
        if ( history.isEmpty() )
            return Collections.singletonList( new DefaultCitationDate( new Date(), DateType.CREATION));

        List<CitationDate> dates = new ArrayList<>();
        for (Map.Entry<EnumEventType,Date> event : history.entrySet()) {
            dates.add(new DefaultCitationDate(event.getValue(), getDateTypeFromEnumEventType(event.getKey())));
        }
        return dates;
    }

    /**
     * an enum converter
     * @param eventType @{@link EnumEventType}
     * @return @{@link DateType}
     */
    private static DateType getDateTypeFromEnumEventType(EnumEventType eventType ) throws EtlTransformerException {
        switch (eventType) {
            case CREATION:       return DateType.CREATION ;
            case PUBLICATION:    return DateType.PUBLICATION ;
            case REVISION:       return DateType.REVISION;
            default: throw new EtlTransformerException(eventType.toString()+" undefined");
        }
    }

    /**
     * In case of multilingual metadata, locales are mandatory
     * get the locales and charsets used by this MD
     * @param metadata @{@link Metadata}
     * @return see @{@link Locale} and UTF8 as a charset
     */
    private static Map<? extends Locale,? extends Charset> getLocales(Metadata metadata) {
        Map<Locale, Charset> mapLocales = new HashMap<>();

        // todo, more robust way to find out which locale is used, parsing ?
        List<I18n> i18nTitle = metadata.getTitle();

        List<String> localesAsString = i18nTitle.stream().map(I18n::getLang).collect(Collectors.toList());
        List<Locale> locales = localesAsString.stream().map(Locale::forLanguageTag).collect(Collectors.toList());
        locales.forEach(l -> mapLocales.put(l, StandardCharsets.UTF_8));
        return mapLocales;
    }

}
