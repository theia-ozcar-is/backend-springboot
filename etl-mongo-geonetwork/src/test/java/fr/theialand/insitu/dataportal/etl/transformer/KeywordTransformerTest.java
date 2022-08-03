package fr.theialand.insitu.dataportal.etl.transformer;

import fr.theialand.insitu.dataportal.etl.exception.EtlTransformerException;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.entity.ObservationDocument;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.dataset.Dataset;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.dataset.Keyword;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.dataset.Metadata;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.observation.*;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.producer.Producer;
import org.junit.jupiter.api.Test;
import org.opengis.metadata.citation.OnlineResource;
import org.opengis.metadata.identification.Keywords;
import org.opengis.util.InternationalString;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;

// Spring boot is not really needed.
// But we need this annotation so the optional application.properties get loaded
// (or its default values => loglevel INFO in spring boot, DEBUG without it )
// (ExtendWith(SpringExtension) is not enough)
@SpringBootTest(classes = Void.class)
class KeywordTransformerTest {

    @Test
    void createInspireKWwithVariousCaseShouldNormalizeCaseTest() throws EtlTransformerException {
        Keywords kw = KeywordTransformer.convertInspireTheme("environmental Monitoring facilities");

        assertThat(kw).isNotNull();
        assertThat(kw.getKeywords())
                .isNotEmpty()
                .hasSize(1);
        InternationalString i18nString = kw.getKeywords().iterator().next();
        assertThat( i18nString.toString() ).hasToString("Environmental monitoring facilities");
    }

    @Test
    void createInspireShouldIncludeURLTest() throws EtlTransformerException {

        Keywords kw = KeywordTransformer.convertInspireTheme("Environmental monitoring facilities");

        assertThat(kw).isNotNull();
        assertThat(kw.getThesaurusName()).isNotNull();

        assertThat(kw.getThesaurusName().getTitle().toString()).contains("GEMET");
        assertThat(kw.getThesaurusName().getOnlineResources()).isNotEmpty();
        OnlineResource ol = kw.getThesaurusName().getOnlineResources().iterator().next();
        assertThat(ol.getLinkage()).isNotNull();
        assertThat(ol.getLinkage().getHost()).contains("europa.eu");
    }

    @Test
    void createInspireKWwithInvalidKWShouldThrowEtlExTest()  {
        try {
            KeywordTransformer.convertInspireTheme("test");
        } catch (EtlTransformerException etlTransfoEx) {
            assertThat( etlTransfoEx )
                    .hasCauseInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("test");
        }
    }

    @Test
    void testCreateKWwithDatasetKWShouldIncludeKW() {

        Keyword kw1 = new Keyword();
        kw1.setKeyword(List.of(new I18n("en", "testKW1")));
        Keyword kw2 = new Keyword();
        kw2.setKeyword(List.of(new I18n("en", "testKW2")));
        List<Keyword> mongoKws = List.of(kw1, kw2);

        Metadata md = new Metadata();
        md.setKeywords(mongoKws);
        Dataset dataset = new Dataset();
        dataset.setMetadata(md);

        Observation observation = new Observation();
        observation.setFeatureOfInterest(new FeatureOfInterest());
        ObservedProperty prop1 = new ObservedProperty();
        prop1.setName(List.of(new I18n("en","testKW1")));
        prop1.setTheiaCategories( List.of("http://test.nope.osug.fr/category1"));
        observation.setObservedProperty(prop1);

        ObservationDocument observationDocument = new ObservationDocument();
        observationDocument.setDataset(dataset);
        observationDocument.setObservation(observation);
        List<ObservationDocument> observationDocuments = new ArrayList<>();
        observationDocuments.add(observationDocument);

        List<Keywords> isoKws = KeywordTransformer.convertKeywords(observationDocuments);

        // Keywords of keywords
        assertThat(isoKws).hasSize(2);
        assertThat(isoKws.get(0).getKeywords()).hasSize(2);
        for (InternationalString isoKw : isoKws.get(0).getKeywords())
            assertThat(isoKw.toString(Locale.ENGLISH)).isIn("testKW1", "testKW2");
    }

    @Test
    void testCreateObsKWwithvalidObservedPropNotLinked()  {
        Metadata metadata = new Metadata();
        Dataset dataset = new Dataset();
        dataset.setMetadata(metadata);
        ObservedProperty prop1 = new ObservedProperty();
        prop1.setName(List.of(new I18n("en","testKW1")));
        prop1.setTheiaCategories( List.of("http://test.nope.osug.fr/category1"));
        ObservedProperty prop2 = new ObservedProperty();
        prop2.setName(List.of(new I18n("en","testKW2")));
        prop2.setTheiaCategories( List.of("http://test.nope.osug.fr/category2"));


        ObservationDocument observationDocument1 = new ObservationDocument();
        ObservationDocument observationDocument2 = new ObservationDocument();
        FeatureOfInterest foi = new FeatureOfInterest();
        Observation observation1 = new Observation();
        Observation observation2 = new Observation();
        observation1.setFeatureOfInterest(foi);
        observation2.setFeatureOfInterest(foi);
        observation1.setObservedProperty(prop1);
        observation2.setObservedProperty(prop2);
        observationDocument1.setObservation(observation1);
        observationDocument2.setObservation(observation2);
        observationDocument1.setDataset(dataset);
        observationDocument2.setDataset(dataset);

        List<ObservationDocument> observationDocuments = new ArrayList<>();
        observationDocuments.add(observationDocument1);
        observationDocuments.add(observationDocument2);
        List<Keywords> isoKws = KeywordTransformer.convertKeywords( observationDocuments );

        assertThat(isoKws).hasSize(1);
        for(InternationalString str: isoKws.get(0).getKeywords())
            assertThat( str.toString(Locale.ENGLISH) ).isIn("testKW1", "testKW2");
    }

    @Test
    void testCreateObsKWwithvalidMixedObservedPropsShouldSeparateKW()  {
        Metadata metadata = new Metadata();
        Dataset dataset = new Dataset();
        dataset.setMetadata(metadata);
        ObservedProperty prop1 = new ObservedProperty();
        prop1.setName(List.of(new I18n("en","testKW1")));
        prop1.setTheiaCategories( List.of("http://test.nope.osug.fr/category1"));
        ObservedProperty propLinked = new ObservedProperty();
        propLinked.setName(List.of(new I18n("en","testKW2")));
        propLinked.setTheiaCategories( List.of("http://test.nope.osug.fr/category2"));
        TheiaVariable tv = new TheiaVariable();
        tv.setPrefLabel(List.of(new I18n("en","testLinked")));
        tv.setUri("http://test.nope.osug.fr/varLinked");
        propLinked.setTheiaVariable(tv);

        ObservationDocument observationDocument1 = new ObservationDocument();
        ObservationDocument observationDocument2 = new ObservationDocument();
        Observation observation1 = new Observation();
        Observation observation2 = new Observation();
        FeatureOfInterest foi = new FeatureOfInterest();
        observation1.setFeatureOfInterest(foi);
        observation2.setFeatureOfInterest(foi);
        observation1.setObservedProperty(prop1);
        observation2.setObservedProperty(propLinked);
        observationDocument1.setDataset(dataset);
        observationDocument2.setDataset(dataset);
        observationDocument1.setObservation(observation1);
        observationDocument2.setObservation(observation2);
        List<ObservationDocument> observationDocuments = new ArrayList<>();
        observationDocuments.add(observationDocument1);
        observationDocuments.add(observationDocument2);

        List<Keywords> isoKws = KeywordTransformer.convertKeywords( observationDocuments );

        assertThat(isoKws).hasSize(2);
    }

    @Test
    void testCreateObsKWwithvalidObservedPropsOnlyLinkedShouldHaveThesaurus()  {
        Metadata metadata = new Metadata();
        Dataset dataset = new Dataset();
        dataset.setMetadata(metadata);
        ObservedProperty propLinked = new ObservedProperty();
        propLinked.setName(List.of(new I18n("en","testKW1")));
        propLinked.setTheiaCategories(List.of("http://test.nope.osug.fr/category1"));
        TheiaVariable tv = new TheiaVariable();
        tv.setPrefLabel(List.of(new I18n("en","testLinked1")));
        tv.setUri("http://test.nope.osug.fr/varLinked1");
        propLinked.setTheiaVariable(tv);
        ObservedProperty propLinked2 = new ObservedProperty();
        propLinked2.setName(List.of(new I18n("en","testKW2")));
        propLinked2.setTheiaCategories(List.of("http://test.nope.osug.fr/category2"));
        TheiaVariable tv2 = new TheiaVariable();
        tv2.setPrefLabel(List.of(new I18n("en","testLinked2")));
        tv2.setUri("http://test.nope.osug.fr/varLinked");
        propLinked2.setTheiaVariable(tv2);
        List<ObservedProperty> obsProps = List.of(propLinked, propLinked2);

        ObservationDocument observationDocument1 = new ObservationDocument();
        ObservationDocument observationDocument2 = new ObservationDocument();
        Observation observation1 = new Observation();
        Observation observation2 = new Observation();
        observation1.setFeatureOfInterest(new FeatureOfInterest());
        observation2.setFeatureOfInterest(new FeatureOfInterest());
        observation1.setObservedProperty(propLinked);
        observation2.setObservedProperty(propLinked2);
        observationDocument1.setDataset(dataset);
        observationDocument2.setDataset(dataset);
        observationDocument1.setObservation(observation1);
        observationDocument2.setObservation(observation2);
        List<ObservationDocument> observationDocuments = new ArrayList<>();
        observationDocuments.add(observationDocument1);
        observationDocuments.add(observationDocument2);

        List<Keywords> isoKws = KeywordTransformer.convertKeywords( observationDocuments );

        assertThat(isoKws).hasSize(1);
        assertThat(isoKws.get(0).getThesaurusName()).isNotNull();
        assertThat(isoKws.get(0).getKeywords()).hasSize(2); // testLinked1 AND testKW1

        for( InternationalString istr : isoKws.get(0).getKeywords()) {
            assertThat(istr.toString(Locale.ENGLISH)).isIn("testLinked2", "testLinked1"); // testLinked1 AND testKW1

        }
    }

}
