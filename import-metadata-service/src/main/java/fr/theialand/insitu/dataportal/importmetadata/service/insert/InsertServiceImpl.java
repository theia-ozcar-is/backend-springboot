package fr.theialand.insitu.dataportal.importmetadata.service.insert;

import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.dataset.Dataset;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.entity.ObservationDocument;
import fr.theialand.insitu.dataportal.repository.mongo.repository.MapItemRepository;
import fr.theialand.insitu.dataportal.repository.mongo.repository.ObservationDocumentLiteRepository;
import fr.theialand.insitu.dataportal.repository.mongo.repository.ObservationDocumentRepository;
import fr.theialand.insitu.dataportal.repository.mongo.service.DatasetService;
import fr.theialand.insitu.dataportal.repository.mongo.service.insert.metadata.ImportService;
import fr.theialand.insitu.dataportal.repository.mongo.service.search.metadata.SearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InsertServiceImpl implements InsertService{

    private final ObservationDocumentRepository observationDocumentRepository;
    private final ObservationDocumentLiteRepository observationDocumentLiteRepository;
    private final MapItemRepository mapItemRepository;
    private final ImportService mongoImportService;
    private final DatasetService logService;
    private final SearchService searchService;

    private static final Logger LOG = LoggerFactory.getLogger(InsertServiceImpl.class);
    private static final Marker USER = MarkerFactory.getMarker("USER");

    @Autowired
    public InsertServiceImpl(ObservationDocumentRepository observationDocumentRepository, ObservationDocumentLiteRepository observationDocumentLiteRepository, MapItemRepository mapItemRepository, ImportService mongoImportService, DatasetService logService, SearchService searchService) {
        this.observationDocumentRepository = observationDocumentRepository;
        this.observationDocumentLiteRepository = observationDocumentLiteRepository;
        this.mapItemRepository = mapItemRepository;
        this.mongoImportService = mongoImportService;
        this.logService = logService;
        this.searchService = searchService;
    }

    @Override
    public void insertObservationDocuments(List<ObservationDocument> observationDocumentList) {
        LOG.info(USER, "{} observations (from all datasets) will be inserted", observationDocumentList.size());
        for (ObservationDocument doc: observationDocumentList) {
            this.observationDocumentRepository.insert(doc);
        }
        LOG.info("observations have been inserted into mongoDB");
    }

    @Override
    public void deleteProducerDocuments(String producerId) {
        LOG.info(USER, "Deleting all previous information from producer {}", producerId);
        this.observationDocumentRepository.deleteObservationDocumentByProducerProducerId(producerId);
        this.observationDocumentLiteRepository.deleteObservationDocumentLiteByProducerProducerId(producerId);
        this.mapItemRepository.deleteMapItemByProducerId(producerId);
    }

    @Override
    public void groupObservationDocumentsAndInsertInObservationDocumentLiteCollection(String producerId) {
        this.mongoImportService.groupObservationDocumentsByVariableAtGivenLocationAndInsertObservationDocumentLiteCollection(producerId);
    }

    @Override
    public void groupObservationDocumentLiteByLocationAndInsertMapItemCollection(String producerId) {
        this.mongoImportService.groupObservationDocumentLiteByLocationAndInsertMapItemCollection(producerId);
    }

    @Override
    public void aggregateObservationDocumentLiteToNoFilterFacetsCollection() {
        this.mongoImportService.aggregateObservationDocumentLiteToNoFilterFacetsCollection();
    }

    /**
     * Refresh document of the "variableAssociations" collection. An association may not be relevant if the corresponding observations
     * are not existing anymore in a new producer import
     * @param producerId -String ID of the producer
     */
    @Override
    public void refreshVariableAssociations(String producerId) {
        LOG.info(USER, "Refreshing {} variables associations collection with the newly imported observations", producerId);
        this.mongoImportService.refreshVariableAssociationsWithObservationsCollection(producerId);
    }

    @Override
    public void updateDatasetHistory(String producerId, List<String> previousDatasets) {

        List<String> actualDatasets = this.getDatasetsId(producerId);

        List<String> overwriteDatasets = actualDatasets.stream().filter(previousDatasets::contains).collect(Collectors.toList());
        List<String> newDatasets       = actualDatasets.stream().filter( s -> ! previousDatasets.contains(s)).collect(Collectors.toList());
        List<String> deletedDatasets   = previousDatasets.stream().filter( s -> ! actualDatasets.contains(s)).collect(Collectors.toList());

        // log stuff to both logs, tech and user ones
        LOG.info(USER, "Producer {}, {} overwritten datasets, {} created, {} deleted", producerId,
                overwriteDatasets.size(),
                newDatasets.size(),
                deletedDatasets.size());

        // TODO, make sure that we want that : each time it is considered "modified"
        // because we have no date in our pivot model, no way to tell if it is , in fact, modified
        // or just copied over
        for( String datasetId: overwriteDatasets)
            this.logService.saveRevisionEvent(datasetId);

        for( String datasetId: newDatasets)
            this.logService.savePublicationAndCreationEvents(datasetId);

//        for( String datasetId: deletedDatasets)
//            this.logService.addEvent(datasetId, EnumEventType.DELETION);
    }

    @Override
    public List<String> getDatasetsId(String producerId) {
        // might not need the full DS objct here...
        List<Dataset> datasets = this.searchService.findDatasetsByProducerId(producerId);
        return datasets.stream().map(Dataset::getDatasetId).collect(Collectors.toList());
    }


}
