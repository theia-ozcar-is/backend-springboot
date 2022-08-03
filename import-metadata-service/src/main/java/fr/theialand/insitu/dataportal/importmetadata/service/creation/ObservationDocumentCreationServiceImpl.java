package fr.theialand.insitu.dataportal.importmetadata.service.creation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.theialand.insitu.dataportal.importmetadata.service.ImportConfig;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.dataset.Dataset;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.dataset.TemporalExtent;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.observation.Observation;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.producer.Producer;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.entity.ObservationDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ObservationDocumentCreationServiceImpl implements ObservationDocumentCreationService {

    private static final Logger LOG = LoggerFactory.getLogger(ObservationDocumentCreationServiceImpl.class);

    private final ObjectMapper  objectMapper;

    @Autowired
    public ObservationDocumentCreationServiceImpl(@Qualifier("mongoRepositoryObjectMapper") ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public List<ObservationDocument> createObservationDocument(JsonNode json, ImportConfig importConfig) throws JsonProcessingException {
        LOG.info("Creating the observation documents to be insterted in the mongodb database");
        List<ObservationDocument> observationDocumentList = new ArrayList<>();
        Producer producer = objectMapper.treeToValue(json.get("producer"),Producer.class);


        json.get("datasets").forEach(jsonDatasetElement -> {
            LOG.info("Calculating the tempoeral extent of the dataset {}", jsonDatasetElement.get("datasetId"));
            List<Date> dateBegs = new ArrayList<>();
            List<Date> dateEnds  = new ArrayList<>();
            jsonDatasetElement.get("observations").forEach( jsonObservationElement -> {
                Observation observation = null;
                try {
                    observation = objectMapper.treeToValue(jsonObservationElement, Observation.class);

                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
                dateBegs.add(observation.getTemporalExtent().getDateBeg());
                dateEnds.add(observation.getTemporalExtent().getDateEnd());
            });
            TemporalExtent temporalExtent = new TemporalExtent();
            temporalExtent.setDateBeg(dateBegs.stream().min(Date::compareTo).get());
            temporalExtent.setDateEnd(dateEnds.stream().max(Date::compareTo).get());
            Dataset dataset = null;
            try {
                dataset = objectMapper.treeToValue(jsonDatasetElement, Dataset.class);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            dataset.getMetadata().setTemporalExtent(temporalExtent);
            Dataset finalDataset = dataset;
            jsonDatasetElement.get("observations").forEach(jsonObservationElement -> {
                LOG.info("Denormalizing producer and dataset information for observation : {}", jsonObservationElement.get("observationId"));
                Observation observation = null;
                try {
                    observation = objectMapper.treeToValue(jsonObservationElement, Observation.class);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
                ObservationDocument doc = new ObservationDocument();
                doc.setProducer(producer);
                doc.setDataset(finalDataset);
                doc.setObservation(observation);
                doc.setDocumentId(observation.getObservationId());
                doc.setVersion("1.0");
                observationDocumentList.add(doc);
            });
        });
        return observationDocumentList;
    }
}
