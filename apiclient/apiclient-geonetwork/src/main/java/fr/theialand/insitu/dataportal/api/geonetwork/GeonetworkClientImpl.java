package fr.theialand.insitu.dataportal.api.geonetwork;

import fr.theialand.insitu.dataportal.api.geonetwork.api.*;
import fr.theialand.insitu.dataportal.api.geonetwork.exceptions.GeonetworkApiException;
import fr.theialand.insitu.dataportal.api.geonetwork.exceptions.GeonetworkClientException;
import fr.theialand.insitu.dataportal.api.geonetwork.exceptions.GeonetworkException;
import fr.theialand.insitu.dataportal.api.geonetwork.exceptions.GeonetworkLinkException;
import fr.theialand.insitu.dataportal.api.geonetwork.handler.ApiClient;
import fr.theialand.insitu.dataportal.api.geonetwork.model.*;
import org.opengis.metadata.Metadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class GeonetworkClientImpl implements GeonetworkClient {

    /**
     * in case we need to convert, from iso19115 v2014 to v2018.
     * Hopefully, GN has this transformation XSL built-in...
     * @ deprecated since we can output iso 19139, and use its schema
     */
    //    private static final String GN_TRANSFORM = "ISO19115-3-2014-to-ISO19115-3-2018";

    /**
     * XML sent to GN use this schema
     */
    private static final String GN_SCHEMA = "iso19139"; //"iso19115-3.2018";

    /**
     * OUR group ID
     * all records in GN must belong to a group,
     * So this property will store the ID of our group that has been provisionned
     * see GN_GROUPNAME
     */
    private int gnGroupId;
    private final String gnGroupName;

    /**
     * ids of the tags where ALL the dataset will be posted
     * they are relevant per group
     *
     * GN Records can be assigned to a category
     * This is NOT the TopicCategories known to Inspire and iso
     * They are "Audio/Video","Maps","Conference" ...
     * only one is relevant for us : "Datasets".  ... and we need its ID
     *
     * Note: categs can be added, deleted, modified. we might want to make it match inspire.
     * OREME has create 2 new categories : "Environment" and "geoscientific", so some of their records have 3 categs:
     * "datasets","Environment" and "geoscientific"
     * again, NOT the same as inspire themes.
     */
    private final List<Integer> gnCommonTagsIds = new ArrayList<>();

    /**
     * Api of all things Identification, "Am I logged in" and so on.
     * Useful for Status tests
     */
    private final MeApi meApi;

    /**
     * Main Api to import the metadata to GN
     */
    private final RecordsApi recordsApi;

    /**
     * Api to search records
     */
    private final SearchApi searchApi;

    /**
     * Info about the GN portal, useful to get its status
     */
    private final SiteApi siteApi;

    /**
     *  about groups, including mandatory info on our user, group ID and its "tags" ("categories")
     *  see also the properties file for setting the username
     */
    private final GroupsApi groupsApi;

    private static final Logger LOG = LoggerFactory.getLogger(GeonetworkClientImpl.class);

    /**
     * Setup the API with a low level client
     * @param gnApiClient Bean of Low-level client, @see {@link GeonetworkConfig}
     */
    @Autowired
    private GeonetworkClientImpl(
            @Autowired ApiClient gnApiClient,
            @Value("${geonetwork.address}")  String gnAddress,
            @Value("${geonetwork.user}") String gnUser,
            @Value("${geonetwork.password}") String gnPassword,
            @Value("${geonetwork.group}") String gnGroup ) {

        LOG.info("Setting up the geonetwork Client on {}, user {}, group {}", gnAddress, gnUser, gnGroup);

        // setting this to true makes the client to fail.
        // because the inputstream will be read by the logging interceptor, and then closed
        // for everyone else, including the real Stream-to-object converter...
        // one solution to get some trace would be to rewrite an Interceptor, and then attach it to RestTemplate
        // but then there is some issues with the buffering of the stream ...
        gnApiClient.setDebugging( false ); // gnDebug

        gnApiClient.setBasePath( gnAddress );
        gnApiClient.setUsername( gnUser  );
        gnApiClient.setPassword( gnPassword );

        this.meApi      = new MeApi( gnApiClient );
        this.recordsApi = new RecordsApi(gnApiClient);
        this.siteApi    = new SiteApi(gnApiClient);
        this.groupsApi  = new GroupsApi(gnApiClient);
        this.searchApi  = new SearchApi(gnApiClient);

        this.gnGroupName = gnGroup;
    }

    @Override
    public Metadata getDataset(String identifier ) throws GeonetworkException {
        Object xml;
        try {
            xml = recordsApi.getRecordAsXml(
                    identifier,
                    true,
                    false,
                    true,
                    true,
                    false
            );
        } catch (RestClientResponseException apiEx) {
            throw new GeonetworkApiException(apiEx);
        } catch (ResourceAccessException linkEx) {
            throw new GeonetworkLinkException(linkEx);
        }
        if(xml == null) {
            LOG.warn("id not found in Geonetwork: {}",identifier);
            return null;
        }
        return GeonetworkUtils.fromXml(xml.toString());
    }

    @Override
    public UUID publishDataset(@NonNull Metadata md) throws GeonetworkException {

        validateMetadata(md);

        String xml = GeonetworkUtils.toXml(md);

        // finalyze our mappings of IDs, with our group (see .properties) and its tags
        if( this.gnGroupId == 0 )
            defineOurGroupMappings(this.gnGroupName);

        //UUIDProcessingEnum uuidProcessingEnum = getProcessingTypeFromIdentifier(datasetMetadata.getMetadataIdentifier() );
        // TODO, make this a param
        UUIDProcessingEnum uuidProcessingEnum = UUIDProcessingEnum.OVERWRITE;

        LOG.info("Publishing dataset to Geonetwork");
        SimpleMetadataProcessingReport reportInsert;
        try {
            reportInsert = recordsApi.insert(
                    MetaDataTypeEnum.METADATA.toString(),    // or template ?!?
                    null,                     // URL of a file
                    null,              // server Folder (related to the File above)
                    false,            // recursive folder (see above)
                    true,  // publish to All, Publish record // ??
                    false,     // assignTocatalogue MEF file only
                    uuidProcessingEnum.toString(), // probably always OVERWRITE.
                    String.valueOf(this.gnGroupId),     // group of users, int, mandatory !
                    this.gnCommonTagsIds.stream().map(String::valueOf).collect(Collectors.toList()),  // categories List<int> // TODO : enum ?
                    false,               // reject if invalid, force to true and get rid of errors, or see XML comment above
                    null,           // XSL transform, iso 2014 to 2018
                    GN_SCHEMA,              // force schema. we leave it to the GN autodetection, or "iso19139"
                    null,       // extra,
                    xml                          // DATASET
            );
        } catch (RestClientResponseException apiEx) {
            throw new GeonetworkApiException(apiEx);
        } catch (ResourceAccessException linkEx) {
            throw new GeonetworkLinkException(linkEx);
        }

        // AFAIK, no error are ever reported in a 20X answer by GN,
        // and errors are reported via a 400 , which generate APIException, which is NOT in the report , obviously
        // therefore, the report is mostly useless
        return GeonetworkUtils.analyzeReport( reportInsert );
    }

    @Override
    public List<String> searchByProducer(@NonNull String fullName ) throws GeonetworkException {
        // TODO : use some other elastic query mechanism...
        String query = "" +
                "{" +
                "  \"query\": {" +
                "              \"query_string\": {" +
                "                \"query\": \"(OrgForResource:\\\""+fullName+"\\\")\"" +
                "              }" +
                "  }," +
                "  \"size\": 500" + //  otherwise limited to 10 results
                "}" ;

        SearchResult result ;
        try {
            result = searchApi.search(null,query);
        } catch (RestClientResponseException apiEx) {
            throw new GeonetworkApiException(apiEx);
        } catch (ResourceAccessException linkEx) {
            throw new GeonetworkLinkException(linkEx);
        }

        if(result == null || result.getHits() == null || result.getHits().getHits().isEmpty())
            return Collections.emptyList();

        return result.getHits().getHits().stream().map(SearchHit::getId).collect(Collectors.toList());
    }

    @Override
    public int deleteDataset(String... idsToDelete) throws GeonetworkException {

        if ( idsToDelete.length == 0 )
            return 0;

        LOG.info("Deleting ids {}",Arrays.asList(idsToDelete));
        SimpleMetadataProcessingReport report ;
        try {
            report = recordsApi.deleteRecords(Arrays.asList(idsToDelete), null, null);
        } catch (RestClientResponseException apiEx) {
            throw new GeonetworkApiException(apiEx);
        } catch (ResourceAccessException linkEx) {
            throw new GeonetworkLinkException(linkEx);
        }

        if( report == null ) {
            LOG.warn("Geonetwork API returned a null ProcessingReport, this is not GN usual behavior");
            return 0;
        }

        int nbProcessed = report.getNumberOfRecordsProcessed();
        if( idsToDelete.length != nbProcessed ) // TODO: further analyzing of the report
            LOG.warn("{} records deleted, but {} IDs where given. NotFounds:{}, NotEditable:{}, Null:{}, WithErrors:{}",
                    nbProcessed,
                    idsToDelete.length,
                    report.getNumberOfRecordNotFound(),
                    report.getNumberOfRecordsNotEditable(),
                    report.getNumberOfNullRecords(),
                    report.getNumberOfRecordsWithErrors());

        GeonetworkUtils.analyzeReport(report);
        return nbProcessed;
    }


    @Override
    public StagingEnum getEnvironment() throws GeonetworkException {
        try {
            SystemInfo sysInfo = siteApi.getSystemInfo();
            return StagingEnum.valueOf(sysInfo.getStagingProfile());
        } catch (RestClientResponseException apiEx) {
            throw new GeonetworkApiException(apiEx);
        } catch (ResourceAccessException linkEx) {
            throw new GeonetworkLinkException(linkEx);
        } catch ( IllegalArgumentException iEx) {
            throw new GeonetworkApiException("StagingStatus returned is invalid, should be one of "+ Arrays.toString(StagingEnum.values()), iEx);
        }
    }


    @Override
    public boolean amILoggedIn() throws GeonetworkException {
        try {
            MeResponse meResponse = meApi.getMe();
            return meResponse != null && !StringUtils.isEmpty(meResponse.getName());
        } catch (RestClientResponseException apiEx) {
            throw new GeonetworkApiException(apiEx);
        } catch (ResourceAccessException linkEx) {
            throw new GeonetworkLinkException(linkEx);
        }
    }

    // ============ INNER METHODS

    /**
     * set up some ids dependant on the instance of GN
     *
     * @param groupName MUST exist as a group name in GN instance
     * @throws GeonetworkApiException    trouble on the link (including 4XX 5XX)
     * @throws GeonetworkClientException trouble with business logic, eg, if groupName does not exist
     */
    private void defineOurGroupMappings( String groupName ) throws GeonetworkException {

        LOG.info("group '{}' will be used to gather ids and categories(tags) from geonetwork instance", groupName);

        Group ourGroup;
        try {
            List<Group> groups = groupsApi.getGroups(
                    false, // nno need for reserved groups "Internet" "Guests" ...
                    null); // not profile-dependent

            ourGroup = groups.stream().filter(g -> g.getName().contains(groupName)).findAny()
                    .orElseThrow(() -> new GeonetworkClientException(groupName+" is unknown to this instance of Geonetwork"));
        } catch (RestClientResponseException apiEx) {
            throw new GeonetworkApiException(apiEx);
        } catch (ResourceAccessException linkEx) {
            throw new GeonetworkLinkException(linkEx);
        }

        // set the ID of our group that will be used for posting MD
        this.gnGroupId = ourGroup.getId();

        // set the IDs of the categories that will be used for posting MD
        if ( null != ourGroup.getDefaultCategory() )
            this.gnCommonTagsIds.add( ourGroup.getDefaultCategory().getId() );
        this.gnCommonTagsIds.addAll( ourGroup.getAllowedCategories().stream().map(MetadataCategory::getId).collect(Collectors.toList()) );

        LOG.info("on the defined instance, group '{}' is matched with id {} and the categories(tags) (if any) ids {}", groupName, this.gnGroupId, this.gnCommonTagsIds);
    }

    private void validateMetadata(Metadata md) throws GeonetworkClientException {
        if( md.getFileIdentifier() == null || md.getFileIdentifier().isBlank())
            throw new GeonetworkClientException("Metadata should have a FileIdentifier before being posted to GeoNetwork");

        // other test for a basic MD
    }

}
