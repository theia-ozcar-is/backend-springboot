package fr.theialand.insitu.dataportal.etl;

import org.apache.sis.metadata.iso.DefaultMetadata;
import org.apache.sis.metadata.iso.citation.DefaultCitation;
import org.apache.sis.metadata.iso.citation.DefaultCitationDate;
import org.apache.sis.metadata.iso.citation.DefaultOnlineResource;
import org.apache.sis.metadata.iso.citation.DefaultResponsibleParty;
import org.apache.sis.metadata.iso.identification.DefaultDataIdentification;
import org.apache.sis.util.iso.DefaultInternationalString;
import org.opengis.metadata.citation.DateType;
import org.opengis.metadata.identification.DataIdentification;
import org.opengis.metadata.identification.TopicCategory;
import org.opengis.metadata.spatial.SpatialRepresentationType;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MetadataTestUtils {


    public static DefaultMetadata buildSampleMetadata() {

        DefaultMetadata sampleMd = new DefaultMetadata();

        sampleMd.getLocalesAndCharsets().put(Locale.ENGLISH, StandardCharsets.UTF_8);

        sampleMd.setIdentificationInfo( List.of( buildSampleDataIdentification() ));

        return sampleMd;
    }

    public static DataIdentification buildSampleDataIdentification() {
        DefaultDataIdentification dataId = new DefaultDataIdentification();
        dataId.setEnvironmentDescription(       new DefaultInternationalString("env Desc"));
        dataId.setSpatialRepresentationTypes(   List.of(SpatialRepresentationType.VECTOR) );
        dataId.setTopicCategories(              List.of(TopicCategory.CLIMATOLOGY_METEOROLOGY_ATMOSPHERE,
                TopicCategory.ENVIRONMENT,
                TopicCategory.GEOSCIENTIFIC_INFORMATION));
        dataId.setPointOfContacts(              List.of(new DefaultResponsibleParty()) );

        DefaultCitation cite = new DefaultCitation();
        cite.setTitle(          new DefaultInternationalString("title") );
        cite.setDates( List.of( new DefaultCitationDate(new Date(), DateType.REVISION),
                new DefaultCitationDate(new Date(), DateType.CREATION) ));

        // replace metadata root datasetURI ?
        cite.setOnlineResources( List.of(new DefaultOnlineResource(URI.create("http://www.cnrs.fr"))) );

        dataId.setCitation( cite );

        return dataId ;
    }
}
