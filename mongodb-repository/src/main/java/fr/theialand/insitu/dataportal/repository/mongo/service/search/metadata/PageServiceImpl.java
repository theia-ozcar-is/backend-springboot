package fr.theialand.insitu.dataportal.repository.mongo.service.search.metadata;

import fr.theialand.insitu.dataportal.repository.mongo.model.DTO.metadataportal.DatasetPageDTO;
import fr.theialand.insitu.dataportal.repository.mongo.model.DTO.metadataportal.PagePayloadDTO;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.entity.ObservationDocumentLite;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.producer.Producer;
import fr.theialand.insitu.dataportal.repository.mongo.repository.MongoDbUtils;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.ConditionalOperators;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.limit;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.skip;

@Service
public class PageServiceImpl extends AbstractSearchService implements PageService {

    @Autowired
    public PageServiceImpl(MongoDbUtils mongoDbUtils) {
    super(mongoDbUtils);
    }

    /**
     * Query the observationLiteDocument to be printed on a given result page in the user interface. This method is used
     * when user is changing the page of the paginated result or when the first page of results is generated.
     *
     * aggregate to page resultSize is defaulted to 1 for optimisation purpose. Another request would be needed to get the actual result size.
     *
     * @param payload String with the filter set by user on ui, the page number and the number of result to display on the list.
     * @return Page object containing the results
     */
    @Override
    public Page<ObservationDocumentLite> getObservationsPage(PagePayloadDTO payload) {
        Pageable pageable = PageRequest.of(payload.getPageSelected() - 1, payload.getPageSize());

        List<AggregationOperation> aggregationOperations = this.setMatchOperationUsingFilters(payload.getFilters(), null);
        aggregationOperations.add(Aggregation.project("observations", "dataset", "producer", "producerVariableNameEn", "textScore").and(ConditionalOperators.ifNull("theiaVariableNameEn").then("zzzzz")).as("theiaVariableNameEn"));
        aggregationOperations.add(Aggregation.sort(Sort.by(Sort.Order.desc("textScore"))).and(Sort.by(Sort.Order.asc("theiaVariableNameEn"))).and(Sort.by(Sort.Order.asc("producerVariableNameEn"))));
        aggregationOperations.add(Aggregation.project("observations", "dataset", "producer", "textScore").and(ConditionalOperators.when(Criteria.where("theiaVariableNameEn").is("zzzzz")).then("$$REMOVE").otherwiseValueOf("theiaVariableNameEn")).as("theiaVariableNameEn"));
        aggregationOperations.add(skip((long) pageable.getPageNumber() * pageable.getPageSize()));
        aggregationOperations.add(limit(pageable.getPageSize()));
        return this.mongoDbUtils.aggregateToPage("observationsLite", ObservationDocumentLite.class,true,
                pageable,1, aggregationOperations.toArray(AggregationOperation[]::new));
    }


    /**
     * Method to query a page of producers to diplay on list items
     *
     * @param payload String with the filter set by user on ui, the page number and the number of result to display on the list.
     * @return Page of Producer
     */
    @Override
    public Page<Producer> getProducerPage(PagePayloadDTO payload) {
        Pageable pageable = PageRequest.of(payload.getPageSelected() - 1, payload.getPageSize());
        //Get the dataset ids using the filter applied by the user on ui
        List<String> producerIds = this.mongoDbUtils.aggregateToList("observationsLite", Document.class, true,
                this.setMatchOperationUsingFilters(payload.getFilters(), "producer").toArray(AggregationOperation[]::new)).stream().map((t) -> t.get("_id", String.class)).collect(Collectors.toList());
        //Pipeline of aggregation operation to obtain the pagination of the result
        AggregationOperation m1 = Aggregation.match(Criteria.where("producer.producerId").in(producerIds));
        AggregationOperation g1 = Aggregation.group("producer.producerId")
                .first("producer.producerId").as("producerId")
                .first("producer.description").as("description")
                .first("producer.objectives").as("objectives")
                .first("producer.measuredVariables").as("measuredVariables")
                .first("producer.title").as("title")
                .first("producer.name").as("name");
        AggregationOperation s1 = Aggregation.sort(Sort.by(Sort.Order.asc("name")));
        AggregationOperation sk1 = Aggregation.skip((long) pageable.getPageNumber() * pageable.getPageSize());
        AggregationOperation l1 = Aggregation.limit(pageable.getPageSize());
        AggregationOperation g1count = Aggregation.group("producer.producerId");
        int resultSize = this.mongoDbUtils.count("observations",true, m1,g1count);

        return this.mongoDbUtils.aggregateToPage(
                "observations",
                Producer.class,
                true,
                pageable,
                resultSize,
                m1, g1, s1, sk1, l1);
    }

    /**
     * Method to query a page of producers to diplay on list items
     *
     * @param payload String with the filter set by user on ui, the page number and the number of result to display on the list.
     * @return Page of Document
     */
    @Override
    public Page<DatasetPageDTO> getDatasetPage(PagePayloadDTO payload) {
        Pageable pageable = PageRequest.of(payload.getPageSelected() - 1, payload.getPageSize());
        //Get the dataset ids using the filter applied by the user on ui
        List<String> datasetIds = this.mongoDbUtils.aggregateToList("observationsLite", Document.class, true,
                this.setMatchOperationUsingFilters(payload.getFilters(), "dataset").toArray(AggregationOperation[]::new)).stream().map((t) -> t.get("_id", String.class)).collect(Collectors.toList());
        //Pipeline of aggregation operation to obtain the pagination of the result
        AggregationOperation m1 = Aggregation.match(Criteria.where("dataset.datasetId").in(datasetIds));
        AggregationOperation g1 = Aggregation.group("dataset.datasetId")
                .first("dataset.datasetId").as("datasetId")
                .first("producer.name").as("producerName")
                .first("producer.producerId").as("producerId")
                .first("dataset.metadata.title").as("title")
                .first("dataset.metadata.description").as("description")
                .first("dataset.metadata.objective").as("objective")
                .first("dataset.metadata.temporalExtent").as("temporalExtent")
                .first("dataset.metadata.spatialExtent").as("spatialExtent")
                .first("dataset.metadata.keywords").as("keywords");
        AggregationOperation s1 = Aggregation.sort(Sort.by(Sort.Order.asc("title")));
        AggregationOperation sk1 = Aggregation.skip((long) pageable.getPageNumber() * pageable.getPageSize());
        AggregationOperation l1 = Aggregation.limit(pageable.getPageSize());
        AggregationOperation g1count = Aggregation.group("dataset.datasetId");
        int resultSize = this.mongoDbUtils.count("observations",true, m1,g1count);

        return this.mongoDbUtils.aggregateToPage(
                "observations",
                DatasetPageDTO.class,
                true,
                pageable,
                resultSize,
                m1, g1, s1, sk1, l1);
    }
}
