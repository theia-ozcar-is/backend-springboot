package fr.theialand.insitu.dataportal.etl;

import fr.theialand.insitu.dataportal.api.geonetwork.GeonetworkClient;
import fr.theialand.insitu.dataportal.api.geonetwork.exceptions.GeonetworkException;
import fr.theialand.insitu.dataportal.etl.exception.EtlException;
import fr.theialand.insitu.dataportal.etl.exception.EtlFunctionalException;
import fr.theialand.insitu.dataportal.etl.exception.EtlPartnerException;
import fr.theialand.insitu.dataportal.etl.transformer.MetadataTransformer;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.entity.ObservationDocument;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.enumerations.EnumEventType;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.dataset.Dataset;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.observation.Observation;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.observation.ObservedProperty;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.producer.Producer;
import fr.theialand.insitu.dataportal.repository.mongo.service.DatasetService;
import fr.theialand.insitu.dataportal.repository.mongo.service.search.metadata.SearchService;
import org.apache.sis.metadata.iso.DefaultMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EtlServiceImpl implements EtlService {

    /**
     * Geonetwork API Client
     */
    private final GeonetworkClient gnClient;

    /**
     * Mongo Client side
     */
    private final SearchService mongoSearchService;
    private final DatasetService mongoDatasetService;

    private static final Logger LOG = LoggerFactory.getLogger(EtlServiceImpl.class);

    @Autowired
    public EtlServiceImpl(SearchService searchService, GeonetworkClient gnClient, DatasetService mongoDatasetService) {
        this.mongoSearchService  = searchService;
        this.gnClient    = gnClient;
        this.mongoDatasetService = mongoDatasetService;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UUID> updateAllDatasetsFromProducer(@NonNull String producerId) throws EtlException {

        LOG.info("Updating all datasets from producer {}", producerId);

        Producer producer = mongoSearchService.findProducer( producerId );
        if (producer == null)
            throw new EtlFunctionalException(String.format("The producer %s was not found", producerId));

        List<Dataset> datasets = mongoSearchService.findDatasetsByProducerId( producerId );
        if ( datasets.isEmpty() ) {
            LOG.warn("No datasets found for producer {} ({})", producerId, producer.getName());
            return Collections.emptyList();
        }
        LOG.info("Found {} datasets for producer {} in Mongo: {}", datasets.size(), producerId, datasets.stream().map(Dataset::getDatasetId).collect(Collectors.toList()));

        int nbPurged = purgeDatasetsOfProducer(EtlUtils.getENorAnyStringFromI18n(producer.getName()));
        LOG.info("purged {} GeoNetwork datasets from producer {}", nbPurged, producerId);

        List<UUID> uuids = new ArrayList<>();
        for( Dataset dataset : datasets ) {
            List<ObservationDocument> observationDocuments = mongoSearchService.findByDatasetId( dataset.getDatasetId() );
            UUID uuid = this.doPublishDataset( observationDocuments);
            uuids.add(uuid);
        }
        return uuids;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public UUID updateDataset(@NonNull String datasetId ) throws EtlException {
        List<ObservationDocument> observationDocuments = mongoSearchService.findByDatasetId(datasetId );
        if ( observationDocuments == null || observationDocuments.size() == 0 ) {
            LOG.warn("no ObservationDocument found with a datasetID {} in mongo", datasetId);
            return null;
        }

        return doPublishDataset(observationDocuments);
    }

    /**
     * Effectively publish a DS to GN
     * @param observationDocuments a List of @{@link ObservationDocument}
     * @return unused UUID (currently unused)
     * @throws EtlException in case something goes wrong
     */
    private UUID doPublishDataset(@NonNull List<ObservationDocument> observationDocuments) throws EtlException {

        Dataset dataset = observationDocuments.get(0).getDataset();
        Producer producer = observationDocuments.get(0).getProducer();

        SortedMap<Date, EnumEventType> history = mongoDatasetService.getEvents(dataset.getDatasetId() );
        LOG.info("Found {} events matching the dataset {}", history.size(), dataset.getDatasetId());

        // keepin only one event of each type
        // discard multiplpe REVISON events, Inspire wants only One event of each type.
        Map<EnumEventType, Date> singleEventsMap = history.entrySet().stream().collect(Collectors.toMap(
                Map.Entry::getValue, Map.Entry::getKey,                    // reverse the map
                (date1,date2)-> Collections.max(List.of(date1,date2))));   // keeps only the most recent event/date
        LOG.info("We kept only {} distincts events, one of each {}", singleEventsMap.size(), new ArrayList<>(singleEventsMap.keySet()));

        List<ObservationDocument> observedProperties = mongoDatasetService.getObservedProperties(dataset.getDatasetId());

        LOG.info("Converting dataset {} from producer {} to Apache SIS, ISO-19115 structure", dataset.getDatasetId(), EtlUtils.getENorAnyStringFromI18n(producer.getName()));
        DefaultMetadata isoMetadata = MetadataTransformer.createTheiaIsoMetadataFromMongo(observationDocuments, singleEventsMap);

        try {
            LOG.info("Publishing dataset {} to Geonetwork",dataset.getDatasetId());
            return gnClient.publishDataset( isoMetadata ) ;
        } catch ( GeonetworkException gnEx) {
            throw new EtlPartnerException("can't send Dataset with dataset ID "+ dataset.getDatasetId(), gnEx);
        }
    }

    /**
     * Delete from GN all datasets from this producer
     * @param producerName should be an enum as it is hardcoded anyway
     * @return nb of datasets purged
     */
    private int purgeDatasetsOfProducer(@NonNull String producerName) throws EtlPartnerException {
        try {
            List<String> ids = gnClient.searchByProducer(producerName);
            LOG.info("Found {} datasets matching producer {} in Geonetwork, purging them all.", ids.size(), producerName);
            return gnClient.deleteDataset( ids.toArray(String[]::new) );
        } catch (GeonetworkException gnEx) {
            throw new EtlPartnerException("can't purge datasets of producer "+ producerName, gnEx);
        }
    }
}
