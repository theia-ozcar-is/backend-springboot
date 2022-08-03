package fr.theialand.insitu.dataportal.repository.mongo.service;

import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.entity.ObservationDocument;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.enumerations.EnumEventType;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.dataset.Dataset;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.observation.ObservedProperty;
import org.springframework.lang.NonNull;

import java.util.Date;
import java.util.List;
import java.util.SortedMap;

public interface DatasetService {

//    void addEvent(@NonNull String datasetId, @NonNull EnumEventType eventType);

    SortedMap<Date, EnumEventType> getEvents(@NonNull String datasetId ) ;


    /**
     * update DB with an event of this type REVISION
     * modify the record if it already exists, otherwise, add a record
     * @param datasetId id of affected DS
     */
    void saveRevisionEvent(String datasetId);

    /**
     * update DB with an event of this type PUBLICATION set to NOW
     * and compute the CREATION to the oldest obs in database
     * @param datasetId id of affected DS
     */
    void savePublicationAndCreationEvents(String datasetId);

    //List<ObservedProperty> getObservedProperties(Dataset dataset);
    List<ObservationDocument> getObservedProperties(String datasetId);

}
