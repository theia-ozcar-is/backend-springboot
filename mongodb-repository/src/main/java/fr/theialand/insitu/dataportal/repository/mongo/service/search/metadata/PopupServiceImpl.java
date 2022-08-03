package fr.theialand.insitu.dataportal.repository.mongo.service.search.metadata;

import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.observation.I18n;
import fr.theialand.insitu.dataportal.repository.mongo.model.view.popup.PopupContent;
import fr.theialand.insitu.dataportal.repository.mongo.model.view.popup.PopupDocument;
import fr.theialand.insitu.dataportal.repository.mongo.repository.MongoDbUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PopupServiceImpl implements PopupService {
    private final MongoDbUtils mongoDbUtils;

    @Autowired
    public PopupServiceImpl(MongoDbUtils mongoDbUtils) {
        this.mongoDbUtils = mongoDbUtils;
    }

    /**
     * Method used to query the popup content depending of the document ids parameter. Popup need the producer name, the
     * station name and the variable names of the observation at the given sampling feature.
     *
     * @param ids String that can be parsed inot JSON object containing and array of documentIds
     * @return PopupContent object
     */
    @Override
    public PopupContent getPopupContent(List<String> ids) {
        //return this.observationDocumentLiteRepository.loadPopupContent(payload);

        Set<String> observationIdsFromMarker = new HashSet<>();
        ids.forEach(observationIdsFromMarker::add);
        //Query the "observationsLite" collection using the newly created documentIds Set object
        MatchOperation m1 = Aggregation.match(Criteria.where("observations.observationId").in(observationIdsFromMarker));
        List<PopupDocument> result = this.mongoDbUtils.aggregateToList("observationsLite",PopupDocument.class,false,m1);

        //Set the PopupContent Object to be returned
        PopupContent popupContent = new PopupContent();
        popupContent.setProducerName(result.get(0).getProducer().getName());
        //Condition on station name since it is not a mandatory field
        if (!result.get(0).getObservations().get(0).getFeatureOfInterest().getSamplingFeature().getName().isEmpty()) {
            popupContent.setStationName(result.get(0).getObservations().get(0).getFeatureOfInterest().getSamplingFeature().getName());
        }
        /*
         * List of variable name and Ids to print in the popup window
         */
        List<PopupContent.VariableNameAndId> variableNameAndIds = new ArrayList<>();
        result.forEach(item -> {
            PopupContent.VariableNameAndId nameAndId = new PopupContent.VariableNameAndId();
            /*
             * Lists of Ids pointed by a given variable name
             */
            List<String> observationIdsFromObservationsLite = new ArrayList<>();
            List<List<I18n>> producerVariableNamesFromObservationsLite = new ArrayList<>();
            /*
             * Theia variable name if it exists
             */
            if (item.getObservations().get(0).getObservedProperty().getTheiaVariable() != null) {
                nameAndId.setTheiaVariableName(item.getObservations().get(0).getObservedProperty().getTheiaVariable().getSimplifiedLabel());
            }

            item.getObservations().forEach((t) -> {
                //List to store all the observationId of one document from observationsLite collection
                observationIdsFromObservationsLite.add(t.getObservationId());

                //List to store all the producerVariableName of one document from observationsLite collection if theia variable
                // is not defined
                producerVariableNamesFromObservationsLite.add(t.getObservedProperty().getName());
            });
            nameAndId.setIds(observationIdsFromObservationsLite);
            nameAndId.setProducerVariableNames(producerVariableNamesFromObservationsLite);
            variableNameAndIds.add(nameAndId);
        });

        popupContent.setVariableNameAndIds(variableNameAndIds);
        return popupContent;
    }
}
