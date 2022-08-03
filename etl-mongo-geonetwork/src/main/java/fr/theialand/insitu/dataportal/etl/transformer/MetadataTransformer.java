package fr.theialand.insitu.dataportal.etl.transformer;


import fr.theialand.insitu.dataportal.etl.EtlUtils;
import fr.theialand.insitu.dataportal.etl.exception.EtlTransformerException;
import fr.theialand.insitu.dataportal.etl.model.CrsEPSG4326;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.entity.ObservationDocument;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.enumerations.EnumContactPersonRoles;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.enumerations.EnumEventType;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.dataset.Dataset;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.dataset.Person;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.observation.ObservedProperty;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.producer.Producer;
import org.apache.sis.internal.jaxb.metadata.replace.ReferenceSystemMetadata;
import org.apache.sis.metadata.iso.DefaultIdentifier;
import org.apache.sis.metadata.iso.DefaultMetadata;
import org.apache.sis.metadata.iso.DefaultMetadataScope;
import org.apache.sis.metadata.iso.citation.*;
import org.apache.sis.metadata.iso.maintenance.DefaultMaintenanceInformation;
import org.opengis.metadata.citation.Address;
import org.opengis.metadata.citation.Contact;
import org.opengis.metadata.citation.DateType;
import org.opengis.metadata.citation.Role;
import org.opengis.metadata.maintenance.MaintenanceFrequency;
import org.opengis.metadata.maintenance.ScopeCode;
import org.springframework.lang.NonNull;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Methods to convert Theia Mongo metadata to ISO metadata,
 * so it can later be fed to geonetwork
 *
 * TODO: pattern Converter<Theia,geoapi>
 */
public class  MetadataTransformer {

    /**
     * will be inserted in front of each ID before going to GN
     */
    static final String ID_PREFIX = "TheiaOZCAR.";

    /**
     * Hiding constrictor
     */
    private MetadataTransformer() {}

    /**
     * Base method to convert info from our format to the ISO structure
     * @param observationDocuments List of @{@link ObservationDocument}
     * @param history , only one date per event, see @{@link EnumEventType}
     *
     * @return a hopefully ISO compliant metadata, ready to be transfered to geonetwork
     * @throws EtlTransformerException @{@link EtlTransformerException} in case smthg goes wrong in one of its sub-methods
     */
    public static DefaultMetadata createTheiaIsoMetadataFromMongo(@NonNull List<ObservationDocument> observationDocuments,
                                                                  Map<EnumEventType, Date> history) throws EtlTransformerException {
        DefaultMetadata isoMetadata = new DefaultMetadata();

        Dataset dataset = observationDocuments.get(0).getDataset();
        Producer producer = observationDocuments.get(0).getProducer();

        isoMetadata.setMetadataIdentifier(new DefaultIdentifier(ID_PREFIX+dataset.getDatasetId() ));
        isoMetadata.getLocalesAndCharsets().putIfAbsent(Locale.ENGLISH, StandardCharsets.UTF_8); // on our image, defaultCharset is ASCII !!

        //pointOfContact.email is set with the producer.email if existing or with the first project leader mail.
        // see issue #10
        DefaultContact pocContact = new DefaultContact();
        if (producer.getEmail() != null) {
            Address pocMail = new DefaultAddress();
            pocMail.getElectronicMailAddresses().add(producer.getEmail());
            pocContact.setAddresses(Collections.singleton(pocMail));
        } else {
            pocContact = ContactTransformer.extractPersonContact(producer.getContacts(), EnumContactPersonRoles.PROJECT_LEADER);
        }
        DefaultOrganisation pocOrg = new DefaultOrganisation( EtlUtils.getENorAnyStringFromI18n(producer.getName()), null, null, pocContact) ;
        isoMetadata.getContacts().add( new DefaultResponsibility( Role.POINT_OF_CONTACT, null, pocOrg) ) ;

        isoMetadata.getContacts().addAll( ContactTransformer.getContactsFromMetadata( dataset.getMetadata().getContacts(), producer.getContacts().stream()
                .filter(obj -> obj instanceof Person)
                .map(Person.class::cast).collect(Collectors.toCollection(ArrayList::new)), pocOrg, producer.getEmail()));

        // in ISO19139, the dateType is irrelevant, it does not appear in the output, but is IS necessary so the gmd:DateStamp is generated
        isoMetadata.getDateInfo().add(new DefaultCitationDate(new Date(), DateType.CREATION));

        isoMetadata.getIdentificationInfo().add( IdentificationTransformer.getIdentificationFromMetadata(observationDocuments, history ) );
        isoMetadata.getMetadataConstraints().addAll( ConstraintTransformer.getIsoConstraintsFromConstraint( dataset.getMetadata().getDataConstraint())  );

        //isoMetadata.getApplicationSchemaInfo().add( getApplicationSchema() ) ;

        isoMetadata.setMetadataMaintenance(new DefaultMaintenanceInformation(MaintenanceFrequency.AS_NEEDED));

        isoMetadata.setResourceLineages( IdentificationTransformer.getLineagesFromMetadataLineage(dataset.getMetadata().getDatasetLineage()) );
        isoMetadata.setDataQualityInfo(  IdentificationTransformer.getQualityFromMetadataLineage(dataset.getMetadata().getDatasetLineage()));
        // use a dummy home-made CRS to avoid unnecessary details, (and f*cked up XML marshalling, too)
        isoMetadata.getReferenceSystemInfo().add(new ReferenceSystemMetadata( new CrsEPSG4326() ) );

        // Scope is always Dataset, international name may be used later "Common dataset" or smthg
        // HierarchyLevel is supposed to be deprecated, but is needed by the GN schematron (2nd level validation)
        isoMetadata.getMetadataScopes().add( new DefaultMetadataScope( ScopeCode.DATASET, null))  ;
        isoMetadata.getHierarchyLevels().add(ScopeCode.DATASET);

        isoMetadata.getDistributionInfo().add(DistributionTransformer.getDistributionInfo( dataset.getMetadata() ));

        return isoMetadata;
    }

}
