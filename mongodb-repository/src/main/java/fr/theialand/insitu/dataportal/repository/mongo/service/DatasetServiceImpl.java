package fr.theialand.insitu.dataportal.repository.mongo.service;

import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.entity.DatasetLog;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.entity.ObservationDocument;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.enumerations.EnumEventType;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.dataset.Dataset;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.observation.ObservedProperty;
import fr.theialand.insitu.dataportal.repository.mongo.repository.DatasetLogRepository;
import fr.theialand.insitu.dataportal.repository.mongo.repository.ObservationDocumentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DatasetServiceImpl implements DatasetService {

    private final DatasetLogRepository datasetLogRepo;
    private final ObservationDocumentRepository obsDocRepo;


    private static final Logger LOG = LoggerFactory.getLogger(DatasetServiceImpl.class);

    @Autowired
    DatasetServiceImpl(DatasetLogRepository datasetLogRepo, ObservationDocumentRepository obsDocRepo) {
        this.datasetLogRepo = datasetLogRepo;
        this.obsDocRepo = obsDocRepo;
    }

    @Override
    public SortedMap<Date, EnumEventType> getEvents(@NonNull String datasetId) {
        List<DatasetLog> logs = datasetLogRepo.findByDatasetIdOrderByDate(datasetId);
        return new TreeMap<>( logs.stream().collect(Collectors.toMap(DatasetLog::getDate, DatasetLog::getEventType )) );
    }

    @Override
    public void saveRevisionEvent(String datasetId) {
        List<DatasetLog> datasetEvents = datasetLogRepo.findByDatasetIdOrderByDate(datasetId);

        // we surely already have a revision, update it
        Optional<DatasetLog> oldRevisionEvent = datasetEvents.stream().filter(dl -> dl.getEventType().equals(EnumEventType.REVISION)).findAny();
        DatasetLog revisionEvent;
        if (oldRevisionEvent.isPresent()) {
            revisionEvent = oldRevisionEvent.get();
            revisionEvent.setDate(new Date());
        } else {
            revisionEvent = new DatasetLog(datasetId, new Date(), EnumEventType.REVISION);
        }
        this.datasetLogRepo.save(revisionEvent);
    }

    @Override
    public void savePublicationAndCreationEvents(String datasetId) {
        List<DatasetLog> datasetEvents = datasetLogRepo.findByDatasetIdOrderByDate(datasetId);

        // PUBLICATION
        // see if we have old event of this ttype, we shouldn't , but it might happen at the begining of things
        Optional<DatasetLog> oldPublicationEvent = datasetEvents.stream().filter(dl -> dl.getEventType().equals(EnumEventType.PUBLICATION)).findAny();
        if (oldPublicationEvent.isEmpty()) {
            // we add the Publish only if it does not exist aliready
            DatasetLog publicationEvent = new DatasetLog(datasetId, new Date(), EnumEventType.PUBLICATION);
            this.datasetLogRepo.save(publicationEvent);
        }

        // CREATION
        // we sure don't want 2 Creation Event ... so act only if we dont have one
        Optional<DatasetLog> oldCreationEvent = datasetEvents.stream().filter(dl -> dl.getEventType().equals(EnumEventType.CREATION)).findAny();
        if (oldCreationEvent.isEmpty()) {
            // if there is no CREATION DATE, we need to compute it...
            ObservationDocument firstObs = this.obsDocRepo.findFirstByDatasetDatasetIdOrderByObservationTemporalExtentDateBegAsc(datasetId);
            if( firstObs == null )
                LOG.warn("No observation with a valid TemporalExtent has been found for this dataset {}, perhaps there is no observation at all ? falling back the creation date of the dataset to (now)", datasetId);

            DatasetLog creationEvent = new DatasetLog(
                    datasetId,
                    firstObs == null ? new Date() : firstObs.getObservation().getTemporalExtent().getDateBeg() ,
                    EnumEventType.CREATION);
            this.datasetLogRepo.save(creationEvent);
        }
    }

    @Override
    public List<ObservationDocument> getObservedProperties(String datasetId) {
//        return obsDocRepo.findObservedPropertiesOfDataset( dataset.getDatasetId() )
//                .stream()
//                .map(o -> o.getObservation().getObservedProperty())
//                .filter(Objects::nonNull)  // some ObsProps might be null, we dont want them
//                .distinct()  // ObservedProperty object implements equals ! so this is possible.
//                .collect(Collectors.toList());
        return obsDocRepo.findByDatasetDatasetId(datasetId);
    }

}
