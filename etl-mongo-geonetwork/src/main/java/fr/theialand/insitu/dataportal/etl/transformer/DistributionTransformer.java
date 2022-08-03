package fr.theialand.insitu.dataportal.etl.transformer;

import fr.theialand.insitu.dataportal.etl.EtlUtils;
import fr.theialand.insitu.dataportal.etl.model.EnumLinkProtocol;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.dataset.Metadata;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.dataset.Webservice;
import org.apache.sis.internal.jaxb.gcx.Anchor;
import org.apache.sis.metadata.iso.citation.DefaultCitation;
import org.apache.sis.metadata.iso.citation.DefaultOnlineResource;
import org.apache.sis.metadata.iso.distribution.DefaultDigitalTransferOptions;
import org.apache.sis.metadata.iso.distribution.DefaultDistribution;
import org.apache.sis.metadata.iso.distribution.DefaultFormat;
import org.apache.sis.util.iso.DefaultInternationalString;
import org.apache.sis.xml.NilReason;
import org.opengis.metadata.citation.OnLineFunction;
import org.opengis.metadata.citation.OnlineResource;
import org.opengis.metadata.distribution.Distribution;
import org.opengis.util.InternationalString;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

class DistributionTransformer {

    private static final String DOI_SERVER = "http://dx.doi.org/";

    private DistributionTransformer() {}

    /**
     * Build iso19115 Distribution, including onleneResources
     * @param metadata where we get the @{@link fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.dataset.OnlineResource}
     * @return iso19115 @{@link Distribution}
     */
    static Distribution getDistributionInfo(@NonNull Metadata metadata) {
        DefaultDistribution distri = new DefaultDistribution();
        DefaultFormat distriFormat = new DefaultFormat();
        InternationalString csvMediaType = new Anchor(URI.create("http://inspire.ec.europa.eu/media-types/text/csv"), "csv");
        distriFormat.setName( csvMediaType );

        // somehow, this 19139 is not honored by the XML 139 creator
        //distriFormat.setVersion(new DefaultInternationalString(VERSION)); // or
        //distriFormat.setVersion(NilReason.UNKNOWN.createNilObject(InternationalString.class));
        // ... and this 19115-3:2016 is not converted into 19139 ...
        // DefaultCitation version = new DefaultCitation();
        // version.setEdition(new DefaultInternationalString(VERSION));
        // distriFormat.setFormatSpecificationCitation(version);
        // .... so we're doomed and have do to it by hand in fr.theialand.insitu.dataportal.api.geonetwork.GeonetworkUtils

        DefaultCitation versionCitation = new DefaultCitation();
        versionCitation.setEdition(NilReason.UNKNOWN.createNilObject(InternationalString.class));
        versionCitation.getAlternateTitles().add( csvMediaType ) ;

        distriFormat.setFormatSpecificationCitation(versionCitation);
        distri.getDistributionFormats().add(distriFormat);

        if( metadata.getOnlineResource() != null ) {
            DefaultDigitalTransferOptions transferOption = new DefaultDigitalTransferOptions();
            transferOption.setOnLines( getOnlineResourcesFromMetadataResources(metadata.getOnlineResource()));
            distri.setTransferOptions(List.of(transferOption));
        }

        return distri;
    }


    /**
     * All the online Resources , DOI, urlDownload...
     * @param onlineResources in the pivot model
     * @return list of Online Resources ISO
     */
    private static Collection<? extends OnlineResource> getOnlineResourcesFromMetadataResources(fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.dataset.OnlineResource onlineResources) {

        List<OnlineResource> isoResources = new ArrayList<>();

        if(onlineResources.getUrlDownload() != null) {
            OnlineResource dlResource = createOnlineResource(
                    URI.create(EtlUtils.getENorAnyStringFromI18n(onlineResources.getUrlDownload())),
                    "Download this dataset from producer dataportal",
                    "Download this dataset from producer dataportal",
                    EnumLinkProtocol.HTTP,OnLineFunction.DOWNLOAD );
            isoResources.add(dlResource);
        }

        if(onlineResources.getUrlInfo() != null) {
            OnlineResource infoResource = createOnlineResource(
                    URI.create(EtlUtils.getENorAnyStringFromI18n(onlineResources.getUrlInfo())),
                    "Producer dataportal (all datasets)",
                    "Producer dataportal (all datasets)",
                    EnumLinkProtocol.HTTP,OnLineFunction.INFORMATION );
            isoResources.add(infoResource);
        }

        if(onlineResources.getDoi() != null) {
            OnlineResource doiResource = createOnlineResource(
                    URI.create(DOI_SERVER+onlineResources.getDoi()),
                    "DOI",
                    "DOI",
                    EnumLinkProtocol.HTTP,OnLineFunction.INFORMATION );
            isoResources.add(doiResource);
        }

        if ( onlineResources.getWebservices() != null ) // because, yes, it can be null..
            for(Webservice webService : onlineResources.getWebservices()) {
                OnlineResource webResource = createOnlineResource(
                        URI.create(EtlUtils.getENorAnyStringFromI18n(webService.getUrl())),
                        EtlUtils.getENorAnyStringFromI18n(webService.getDescription()),
                        EtlUtils.getENorAnyStringFromI18n(webService.getDescription()),
                        EnumLinkProtocol.HTTP,OnLineFunction.INFORMATION );
                isoResources.add(webResource);
            }

        return  isoResources;
    }

    /**
     * Create an onlineResource with specified params. it is kinda Constructor that is missing in new {@link DefaultOnlineResource}
     * @param uri mandatory
     * @param name optional but recommended
     * @param description optional but recommended
     * @param protocol optional
     * @param function highly optional
     * @return the onlineResource
     */
    private static OnlineResource createOnlineResource(@NonNull URI uri,
                                                @Nullable String name,
                                                @Nullable String description,
                                                @Nullable EnumLinkProtocol protocol,
                                                @Nullable OnLineFunction function) {

        DefaultOnlineResource onlineResource = new DefaultOnlineResource( uri );
        if ( name != null )
            onlineResource.setName(new DefaultInternationalString(name));
        if( description != null )
            onlineResource.setDescription(new DefaultInternationalString(description));
        if( protocol != null)
            onlineResource.setProtocol(protocol.toString());
        if( function != null)
            onlineResource.setFunction(function);

        return onlineResource;
    }
}
