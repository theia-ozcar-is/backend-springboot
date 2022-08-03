package fr.theialand.insitu.dataportal.etl.transformer;

import com.mongodb.client.model.geojson.Polygon;
import com.mongodb.client.model.geojson.Position;
import fr.theialand.insitu.dataportal.api.geonetwork.GeonetworkUtils;
import fr.theialand.insitu.dataportal.api.geonetwork.exceptions.GeonetworkXMLMarshallingException;
import fr.theialand.insitu.dataportal.etl.EtlUtils;
import fr.theialand.insitu.dataportal.etl.exception.EtlTransformerException;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.dataset.Dataset;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.dataset.Metadata;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.dataset.PortalSearchCriteria;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.dataset.SpatialExtent;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.observation.Observation;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.producer.Producer;
import org.apache.sis.metadata.iso.DefaultMetadata;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.opengis.metadata.extent.Extent;
import org.opengis.metadata.extent.GeographicBoundingBox;
import org.opengis.metadata.extent.GeographicExtent;
import org.springframework.boot.test.context.SpringBootTest;
import org.xmlunit.assertj.XmlAssert;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

// Spring boot is not really needed.
// But we need this annotation so the optional application.properties get loaded
// (or its default values => loglevel INFO in spring boot, DEBUG without it )
// (ExtendWith(SpringExtension) is not enough)
@SpringBootTest(classes = Void.class)
class TransformerTest {

    @Test
    void createNewDatasetTest() throws  EtlTransformerException {

        // 1 . SETUP
        Dataset sampleDs = TransformerTestHelper.buildSampleDataset();
        Producer sampleProducer = TransformerTestHelper.buildSampleProducer();
        // 2. CALL
        DefaultMetadata md = MetadataTransformer.createTheiaIsoMetadataFromMongo(
                List.of(TransformerTestHelper.buildSampleObservationDocument(sampleProducer, sampleDs, TransformerTestHelper.buildSampleObservation())) , Collections.emptySortedMap());

        //3. ASSERT
        assertThat(md).isNotNull();
    }

    /**
     * issue created at SIS JIRA: #SIS-508") // https://issues.apache.org/jira/browse/SIS-508
     */
    @Test
    @DisplayName("SIS bug 508: we should have replaced DQ_Scope with MD_Scope")
    void datasetShouldProduceValidDataQualityScopeCode() throws EtlTransformerException, GeonetworkXMLMarshallingException {

        // 1 . SETUP
        Dataset sampleDs = TransformerTestHelper.buildSampleDataset();
        Producer sampleProducer = TransformerTestHelper.buildSampleProducer();
        DefaultMetadata md = MetadataTransformer.createTheiaIsoMetadataFromMongo(
                List.of(TransformerTestHelper.buildSampleObservationDocument(sampleProducer, sampleDs, TransformerTestHelper.buildSampleObservation())) , Collections.emptySortedMap());

        // 2. CALL
        String xml = GeonetworkUtils.toXml( md );

        //3. ASSERT
        XmlAssert.assertThat(xml).withNamespaceContext(TransformerTestHelper.getNSs())
                .doesNotHaveXPath("//gmd:DQ_DataQuality/gmd:scope/gmd:MD_Scope");
        XmlAssert.assertThat(xml).withNamespaceContext(TransformerTestHelper.getNSs())
                .hasXPath("//gmd:DQ_DataQuality/gmd:scope/gmd:DQ_Scope");
    }

    @Test
    void datasetShouldProduceValidDateTimeStamp() throws EtlTransformerException, GeonetworkXMLMarshallingException {

        // 1 . SETUP
        Dataset sampleDs = TransformerTestHelper.buildSampleDataset();
        Producer sampleProducer = TransformerTestHelper.buildSampleProducer();
        DefaultMetadata md = MetadataTransformer.createTheiaIsoMetadataFromMongo(
                List.of(TransformerTestHelper.buildSampleObservationDocument(sampleProducer, sampleDs, TransformerTestHelper.buildSampleObservation())) , Collections.emptySortedMap());

        // 2. CALL
        String xml = GeonetworkUtils.toXml( md );

        //3. ASSERT
        XmlAssert.assertThat(xml).withNamespaceContext(TransformerTestHelper.getNSs())
            .hasXPath("//gmd:dateStamp");
    }

    @Test
    @DisplayName("DatasetURI should be removed")
    void datasetShouldNOTProduceDeprecatedDatasetUri() throws EtlTransformerException, GeonetworkXMLMarshallingException {

        // 1 . SETUP
        Dataset sampleDs = TransformerTestHelper.buildSampleDataset();
        Producer sampleProducer = TransformerTestHelper.buildSampleProducer();
        DefaultMetadata md = MetadataTransformer.createTheiaIsoMetadataFromMongo(
                List.of(TransformerTestHelper.buildSampleObservationDocument(sampleProducer, sampleDs, TransformerTestHelper.buildSampleObservation())) , Collections.emptySortedMap());

        // 2. CALL
        String xml = GeonetworkUtils.toXml( md );

        //3. ASSERT
        XmlAssert.assertThat(xml).withNamespaceContext(TransformerTestHelper.getNSs())
                .doesNotHaveXPath("/gmd:dataSetURI");
    }


    @Test
    @DisplayName("XML should contain an Inspire compliant language codelist")
    void datasetShouldProduceValidLanguage() throws EtlTransformerException, GeonetworkXMLMarshallingException {

        // 1 . SETUP
        Dataset sampleDs = TransformerTestHelper.buildSampleDataset();
        Producer sampleProducer = TransformerTestHelper.buildSampleProducer();
        DefaultMetadata md = MetadataTransformer.createTheiaIsoMetadataFromMongo(
                List.of(TransformerTestHelper.buildSampleObservationDocument(sampleProducer, sampleDs, TransformerTestHelper.buildSampleObservation())) , Collections.emptySortedMap());

        // 2. CALL
        String xml = GeonetworkUtils.toXml( md );

        //3. ASSERT
        XmlAssert.assertThat(xml).withNamespaceContext(TransformerTestHelper.getNSs())
                .hasXPath("//gmd:LanguageCode[@codeList='http://www.loc.gov/standards/iso639-2/']");
        XmlAssert.assertThat(xml).withNamespaceContext(TransformerTestHelper.getNSs())
                .doesNotHaveXPath("//gmd:LanguageCode[@codeList='http://www.isotc211.org/2005/resources/Codelist/gmxCodelists#LanguageCode']");
    }

    @Test
    @DisplayName("Inspire requires at least 2 digits for bbox coordinates")
    void unpreciseBBoxShouldBeTransformedIn2DigitPreciseBBox() throws EtlTransformerException {
        // 1 . prepare
        Metadata md = TransformerTestHelper.buildSampleMetadata();
        SpatialExtent spatialExtent = new SpatialExtent();
        spatialExtent.setType( "Polygon" );// todo ENUM
        Polygon bbox = new Polygon(List.of(
                new Position(10.1,20.2),
                new Position(20.1,20.2),
                new Position(20.1,10.2),
                new Position(10.1,10.2),
                new Position(10.1,20.2) ) );
        spatialExtent.setGeometry(bbox);
        md.setSpatialExtent(spatialExtent);

        //2. call
        Extent extent = ExtentTransformer.getExtentFromMetadata( md );

        //3. assert
        assertThat(extent.getGeographicElements()).isNotEmpty().hasSize(1);
        GeographicExtent geoExtent = extent.getGeographicElements().stream().findFirst().get();
        assertThat( geoExtent ).isInstanceOf(GeographicBoundingBox.class);
        GeographicBoundingBox computedBbox = (GeographicBoundingBox) geoExtent;
        assertThat( computedBbox ).isNotNull();

        assertThat( computedBbox.getEastBoundLongitude() ).isGreaterThan(20.1).isLessThan(20.10000002);
        assertThat( computedBbox.getWestBoundLongitude() ).isGreaterThan(10.1).isLessThan(10.10000002);
        assertThat( computedBbox.getNorthBoundLatitude() ).isGreaterThan(20.2).isLessThan(20.20000002);
        assertThat( computedBbox.getSouthBoundLatitude() ).isGreaterThan(10.2).isLessThan(10.20000002);
    }

    /**
     * XML Should Contain a simple CRS
     */
    @Test
    @DisplayName("XML should contain only basic CRS information, EPSG4326")
    void xmlCRSshouldNotContainAnAuthority() throws EtlTransformerException, GeonetworkXMLMarshallingException {
        Dataset sampleDs =  TransformerTestHelper.buildSampleDataset() ;
        Producer sampleProducer =  TransformerTestHelper.buildSampleProducer() ;

        DefaultMetadata md = MetadataTransformer.createTheiaIsoMetadataFromMongo(
                List.of(TransformerTestHelper.buildSampleObservationDocument(sampleProducer, sampleDs, TransformerTestHelper.buildSampleObservation())) , Collections.emptySortedMap());

        // 2. CALL
        String xml = GeonetworkUtils.toXml( md );

        //3. ASSERT
        // standard formating of a basic CRS
        XmlAssert.assertThat(xml).withNamespaceContext(TransformerTestHelper.getNSs())
                .nodesByXPath("/gmd:MD_Metadata/gmd:referenceSystemInfo/gmd:MD_ReferenceSystem/gmd:referenceSystemIdentifier/gmd:RS_Identifier/*")
                .containsAnyNodeHavingXPath("//gmd:codeSpace/*[.=\"EPSG\"]")
                .containsAnyNodeHavingXPath("//gmd:code/*[.=\"4326\"]");

        // must NOT have the authority thing
        XmlAssert.assertThat(xml).withNamespaceContext(TransformerTestHelper.getNSs())
                .doesNotHaveXPath("/gmd:MD_Metadata/gmd:referenceSystemInfo/gmd:MD_ReferenceSystem/gmd:referenceSystemIdentifier/gmd:RS_Identifier/gmd:authority");
    }

    @Test
    @DisplayName("XML, distribution Format should contain a Version with nilReason")
    void xmlDistriFormatshouldContainAVersion() throws EtlTransformerException, GeonetworkXMLMarshallingException {
        Dataset sampleDs =  TransformerTestHelper.buildSampleDataset() ;
        Producer sampleProducer =  TransformerTestHelper.buildSampleProducer() ;

        DefaultMetadata md = MetadataTransformer.createTheiaIsoMetadataFromMongo(
                List.of(TransformerTestHelper.buildSampleObservationDocument(sampleProducer, sampleDs, TransformerTestHelper.buildSampleObservation())) , Collections.emptySortedMap());

        // 2. CALL
        String xml = GeonetworkUtils.toXml( md );

        //3. ASSERT
        // we check the presence of Version tag, and its nilReason attribute,
        XmlAssert.assertThat(xml).withNamespaceContext(TransformerTestHelper.getNSs())
                .hasXPath("//gmd:distributionFormat/gmd:MD_Format/gmd:version")
                .haveAttribute("nilReason","missing");
    }

    @Test
    @DisplayName("XML, DataQuality/ConformanceResult must have an 'Explanation'")
    void xmlDataQualityConformanceshouldContainAnExplanation() throws EtlTransformerException, GeonetworkXMLMarshallingException {
        Dataset sampleDs =  TransformerTestHelper.buildSampleDataset() ;
        Producer sampleProducer =  TransformerTestHelper.buildSampleProducer() ;

        DefaultMetadata md = MetadataTransformer.createTheiaIsoMetadataFromMongo(
                List.of(TransformerTestHelper.buildSampleObservationDocument(sampleProducer, sampleDs, TransformerTestHelper.buildSampleObservation())) , Collections.emptySortedMap());

        // 2. CALL
        String xml = GeonetworkUtils.toXml( md );

        //3. ASSERT
        // we check the presence of Version tag, and its nilReason attribute,
        // but NOT the value of the attribute. (must be "missing")
        XmlAssert.assertThat(xml).withNamespaceContext(TransformerTestHelper.getNSs())
                .hasXPath("//gmd:DQ_DomainConsistency/gmd:result/gmd:DQ_ConformanceResult/gmd:explanation");
    }

    @Test
    @DisplayName("XML, DOI should be in DistributionFormat if present")
    void xmlDOIshouldAppearInDistributionFormatTag() throws EtlTransformerException, GeonetworkXMLMarshallingException {
        Dataset sampleDs =  TransformerTestHelper.buildSampleDataset() ;
        Producer sampleProducer =  TransformerTestHelper.buildSampleProducer() ;
        sampleDs.getMetadata().getOnlineResource().setDoi("10.424242/VladimirIllytch");

        DefaultMetadata md = MetadataTransformer.createTheiaIsoMetadataFromMongo(
                List.of(TransformerTestHelper.buildSampleObservationDocument(sampleProducer, sampleDs, TransformerTestHelper.buildSampleObservation())) , Collections.emptySortedMap());

        // 2. CALL
        String xml = GeonetworkUtils.toXml( md );

        //3. ASSERT
        XmlAssert.assertThat(xml).withNamespaceContext(TransformerTestHelper.getNSs())
                .hasXPath("//gmd:distributionInfo/gmd:MD_Distribution/gmd:transferOptions/gmd:MD_DigitalTransferOptions/gmd:onLine/gmd:CI_OnlineResource/gmd:linkage/gmd:URL[contains(text(),\"10.424242/VladimirIllytch\")]");
    }

    @Test
    @DisplayName("XML, urlDownload should appear in TransferOption if present in pivot model")
    void xmlUrlDownloadShouldAppear() throws EtlTransformerException, GeonetworkXMLMarshallingException {
        Dataset sampleDs =  TransformerTestHelper.buildSampleDataset() ;
        Producer sampleProducer =  TransformerTestHelper.buildSampleProducer() ;
        sampleDs.getMetadata().getOnlineResource().setUrlDownload(EtlUtils.createSimpleI18nString("http://testurldownload.test.fr"));

        DefaultMetadata md = MetadataTransformer.createTheiaIsoMetadataFromMongo(
                List.of(TransformerTestHelper.buildSampleObservationDocument(sampleProducer, sampleDs, TransformerTestHelper.buildSampleObservation())) , Collections.emptySortedMap());

        // 2. CALL
        String xml = GeonetworkUtils.toXml( md );

        //3. ASSERT
        XmlAssert.assertThat(xml).withNamespaceContext(TransformerTestHelper.getNSs())
                .hasXPath("//gmd:distributionInfo/gmd:MD_Distribution/gmd:transferOptions/gmd:MD_DigitalTransferOptions/gmd:onLine/gmd:CI_OnlineResource/gmd:linkage/gmd:URL[.=\"http://testurldownload.test.fr\"]");
    }

    @Test
    @DisplayName("XML, Keywords Geology and Climate should have NO thesaurus attachment")
    void xmlClimateAndGeologyShouldAppearWithoutThesaurus() throws EtlTransformerException, GeonetworkXMLMarshallingException {
        Dataset sampleDs =  TransformerTestHelper.buildSampleDataset() ;
        Producer sampleProducer =  TransformerTestHelper.buildSampleProducer() ;
        Observation observation = TransformerTestHelper.buildSampleObservation();
        observation.getFeatureOfInterest().setGeologyFeature("HardRock");
        observation.getFeatureOfInterest().setClimateFeature("HotHotHot");

        DefaultMetadata md = MetadataTransformer.createTheiaIsoMetadataFromMongo(
                List.of(TransformerTestHelper.buildSampleObservationDocument(sampleProducer, sampleDs, observation)) , Collections.emptySortedMap());

        // 2. CALL
        String xml = GeonetworkUtils.toXml( md );

        //3. ASSERT
        // we need to have those KW existing
        XmlAssert.assertThat(xml).withNamespaceContext(TransformerTestHelper.getNSs())
                .hasXPath("//gmd:MD_Keywords[gmd:keyword/gcol:CharacterString[.=\"HotHotHot\"]]");
        XmlAssert.assertThat(xml).withNamespaceContext(TransformerTestHelper.getNSs())
                .hasXPath("//gmd:MD_Keywords[gmd:keyword/gcol:CharacterString[.=\"HardRock\"]]");

        // ... but not with thesaurus
        XmlAssert.assertThat(xml).withNamespaceContext(TransformerTestHelper.getNSs())
                .doesNotHaveXPath("//gmd:MD_Keywords[gmd:keyword/gcol:CharacterString[.=\"HotHotHot\"]]/gmd:thesaurusName");
        XmlAssert.assertThat(xml).withNamespaceContext(TransformerTestHelper.getNSs())
                .doesNotHaveXPath("//gmd:MD_Keywords[gmd:keyword/gcol:CharacterString[.=\"HardRock\"]]/gmd:thesaurusName");
    }

}
