package fr.theialand.insitu.dataportal.etl.transformer;

import com.mongodb.client.model.geojson.Point;
import com.mongodb.client.model.geojson.Position;
import fr.theialand.insitu.dataportal.etl.EtlUtils;
import fr.theialand.insitu.dataportal.model.pivot.enumerations.EnumInspireTheme;
import fr.theialand.insitu.dataportal.model.pivot.enumerations.EnumTopicCategory;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.entity.ObservationDocument;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.enumerations.EnumContactOrganisationRoles;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.enumerations.EnumContactPersonRoles;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.dataset.*;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.observation.*;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.producer.Producer;

import java.util.*;

public class TransformerTestHelper {

    /**
     * used in {@link org.xmlunit.assertj.XmlAssert}
     */
    static Map<String,String> getNSs () {
        Map<String,String> namespaces = new HashMap<>();

        namespaces.put("gmd", "http://www.isotc211.org/2005/gmd");
        namespaces.put("gcol","http://www.isotc211.org/2005/gco");
        namespaces.put("gmi","http://standards.iso.org/iso/19115/-2/gmi/1.0");
        namespaces.put("gml", "http://www.opengis.net/gml/3.2");
        namespaces.put("gmx", "http://www.isotc211.org/2005/gmx");
        namespaces.put("gts", "http://www.isotc211.org/2005/gts");
        namespaces.put("srv1","http://www.isotc211.org/2005/srv");
        namespaces.put("xlink","http://www.w3.org/1999/xlink");
        namespaces.put("xsi","http://www.w3.org/2001/XMLSchema-instance");

        return namespaces;
    }

    static Observation buildSampleObservation() {
        Observation observation = new Observation();

        ObservedProperty observedProperty = new ObservedProperty();
        observedProperty.setName(EtlUtils.createSimpleI18nString("variable"));
        observedProperty.setDescription(EtlUtils.createSimpleI18nString("Description"));
        observedProperty.setTheiaCategories( List.of("http://test.nope.osug.fr/category2"));

        SamplingGeometry samplingGeometry = new SamplingGeometry();
        samplingGeometry.setName(EtlUtils.createSimpleI18nString("station"));
        samplingGeometry.setGeometry(new Point(new Position(2.109375,46.31658418182218)));
        FeatureOfInterest featureOfInterest = new FeatureOfInterest();
        featureOfInterest.setSamplingFeature(samplingGeometry);
        featureOfInterest.setClimateFeature("climate");
        featureOfInterest.setGeologyFeature("geologie");

        observation.setObservedProperty(observedProperty);
        observation.setFeatureOfInterest(featureOfInterest);
        return observation;
    }

    static ObservationDocument buildSampleObservationDocument(Producer producer, Dataset dataset, Observation observation) {
        ObservationDocument observationDocument = new ObservationDocument();
        observationDocument.setProducer(producer);
        observationDocument.setDataset(dataset);
        observationDocument.setObservation(observation);
        return observationDocument;
    }

    static Dataset buildSampleDataset() {
        Dataset ds = new Dataset();

        ds.setMetadata( buildSampleMetadata() );

        return ds;
    }

    static Metadata buildSampleMetadata() {
        Metadata md = new Metadata();

        md.setTitle( EtlUtils.createSimpleI18nString("TestTitle" ));
        md.setDescription( EtlUtils.createSimpleI18nString("Test desc") );
        md.setTopicCategories(List.of(EnumTopicCategory.CLIMATOLOGY_METEOROLOGY_ATMOSPHERE.getTopicName()));
        md.setInspireTheme(EnumInspireTheme.ATMOSPHERIC_METEO.getThemeName());

        md.setDatasetLineage(EtlUtils.createSimpleI18nString("fr.theialand.insitu.test lineage"));

        md.setContacts( buildSampleContacts() );

        DataConstraint constraint = new DataConstraint();
        constraint.setAccessUseConstraint(EtlUtils.createSimpleI18nString("useCstrt"));
        md.setDataConstraint(constraint);

        TemporalExtent tempEx = new TemporalExtent();
        tempEx.setDateBeg(new Date());
        tempEx.setDateEnd(new Date());
        md.setTemporalExtent( tempEx );

        OnlineResource ol = new OnlineResource();
        //ol.setUrlDownload(new I18nList("http://testURL.com"));
        ol.setUrlDownload(EtlUtils.createSimpleI18nString("http://testURL.com"));
        md.setOnlineResource( ol );

        return md;
    }

    static Producer buildSampleProducer() {
        Producer prod = new Producer();
        prod.setName(EtlUtils.createSimpleI18nString("TEST"));

//        prod.setName(new I18nList("TEST"));
        prod.setContacts(buildSamplePersonContacts());
        return prod;
    }

    private static List<Contact>  buildSampleContacts() {
        List<Contact> contacts = new ArrayList<>();

        Organisation orgContact = new Organisation();
        //orgContact.setName( new I18nList("Person name"));
        orgContact.setName( EtlUtils.createSimpleI18nString("Person name"));

        orgContact.setRole(EnumContactOrganisationRoles.RESEARCH_GROUP.toString());
        contacts.add(orgContact);

        contacts.addAll(buildSamplePersonContacts());

        return contacts ;
    }

    private static List<Contact>  buildSamplePersonContacts() {
        List<Contact> persons = new ArrayList<>();

        Person personContact = new Person();
        personContact.setRole( EnumContactPersonRoles.PRINCIPAL_INVESTIGATOR.toString() );
        personContact.setFirstName("testFN");
        personContact.setLastName("testLN");
        personContact.setEmail("testPI@test.tu");
        persons.add(personContact);

        Person leaderContact = new Person();
        leaderContact.setRole( EnumContactPersonRoles.PROJECT_LEADER.toString() );
        leaderContact.setFirstName("testFN");
        leaderContact.setLastName("testLN");
        leaderContact.setEmail("testPL@test.tu");
        persons.add(leaderContact);

        return persons;
    }
}
