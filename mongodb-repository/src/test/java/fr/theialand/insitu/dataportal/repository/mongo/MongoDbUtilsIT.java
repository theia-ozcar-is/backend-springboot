package fr.theialand.insitu.dataportal.repository.mongo;

import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.entity.ObservationDocumentLite;
import fr.theialand.insitu.dataportal.repository.mongo.repository.MongoDbUtils;
import fr.theialand.insitu.dataportal.repository.mongo.repository.ObservationDocumentLiteRepository;
import fr.theialand.insitu.dataportal.repository.mongo.service.GenericAggregationOperation;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.GraphLookupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.util.CloseableIterator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.graphLookup;
import static org.springframework.data.mongodb.core.query.Criteria.where;

@SpringBootTest
@Disabled(" For this test to have any meaning, one should restrain Xmx Xms via Surefire plugin or via IntelliJ RunConfig")
class MongoDbUtilsIT {
    @Autowired
    private MongoDbUtils mongoDbUtils;

    @Autowired
    private ObservationDocumentLiteRepository obsLiteRepo;

    /**
     * For this test to have any meaning, one should restrain Xmx Xms via Surefire plugin
     */
    @ParameterizedTest
    @ValueSource(strings = {"TOUR" , "CATC" })
    void aggregateToCollectionShouldPopulate2ndCollection(String producer) {

        // 1 . PREPARE
        MatchOperation m1 = Aggregation.match(where("producer.producerId").is(producer));

        /**
         * Recursivly get the categories from the variableCategories collection
         */
        GraphLookupOperation glu1 = graphLookup("variableCategories")
                .startWith("observation.observedProperty.theiaCategories")
                .connectFrom("broaders")
                .connectTo("uri")
                .as("categoryHierarchy");

        /**
         * Add important fields for the portal facet query at the root of the document
         */
        String addFieldsJson = "{\n" +
                "\t\"theiaVariableUri\": \"$observation.observedProperty.theiaVariable.uri\",\n" +
                "\t\"theiaVariable\": \"$observation.observedProperty.theiaVariable\",\n" +
                "\t\"theiaVariableNameEn\": {\n" +
                "\t\t\"$arrayElemAt\": [{\n" +
                "\t\t\t\"$map\": {\n" +
                "\t\t\t\t\"input\": {\n" +
                "\t\t\t\t\t\"$filter\": {\n" +
                "\t\t\t\t\t\t\"input\": \"$observation.observedProperty.theiaVariable.prefLabel\",\n" +
                "\t\t\t\t\t\t\"as\": \"item\",\n" +
                "\t\t\t\t\t\t\"cond\": {\n" +
                "\t\t\t\t\t\t\t\"$eq\": [\"$$item.lang\", \"en\"]\n" +
                "\t\t\t\t\t\t}\n" +
                "\t\t\t\t\t}\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t\"as\": \"variableTmp\",\n" +
                "\t\t\t\t\"in\": \"$$variableTmp.text\"\n" +
                "\t\t\t}\n" +
                "\t\t}, 0]\n" +
                "\t},\n" +
                "\t\"producerVariableNameEn\": {\n" +
                "\t\t\"$arrayElemAt\": [{\n" +
                "\t\t\t\"$map\": {\n" +
                "\t\t\t\t\"input\": {\n" +
                "\t\t\t\t\t\"$filter\": {\n" +
                "\t\t\t\t\t\t\"input\": \"$observation.observedProperty.name\",\n" +
                "\t\t\t\t\t\t\"as\": \"item\",\n" +
                "\t\t\t\t\t\t\"cond\": {\n" +
                "\t\t\t\t\t\t\t\"$eq\": [\"$$item.lang\", \"en\"]\n" +
                "\t\t\t\t\t\t}\n" +
                "\t\t\t\t\t}\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t\"as\": \"variableTmp\",\n" +
                "\t\t\t\t\"in\": \"$$variableTmp.text\"\n" +
                "\t\t\t}\n" +
                "\t\t}, 0]\n" +
                "\t},\n" +
                "\t\"featureOfInterest\": \"$observation.featureOfInterest\"\n" +
                "}";
        AggregationOperation a1 = new GenericAggregationOperation("$addFields", addFieldsJson);

        /**
         * Group all the observation by producer, dataset, sampling feature and theiaVariable or producerVariableNameEn
         * when theiaVariable does not exist. The documentId of the observations grouped are push in an array The
         * observedPorperty of the observation grouped are pushed in an array in order to keep the information
         * theiaVariable / theiaCategories for each observation
         *
         * This group operation is complex and not supported by Spring data mongodb. AggregationOperation.class is
         * extended to support Aggregation operation building using Document.class
         */

        String groupJson = "{\n" +
                "\t\t\"_id\": {\n" +
                "\t\t\t\"producerId\": \"$producer.producerId\",\n" +
                "\t\t\t\"datasetId\": \"$dataset.datasetId\",\n" +
                "\t\t\t\"producerVariableNameEn\": {\n" +
                "\t\t\t\t\"$cond\": [{\n" +
                "\t\t\t\t\t\"$not\": [\"$theiaVariableUri\"]\n" +
                "\t\t\t\t}, \"$producerVariableNameEn\", null]\n" +
                "\t\t\t},\n" +
                "\t\t\t\"theiaVariableUri\": {\n" +
                "\t\t\t\t\"$cond\": [{\n" +
                "\t\t\t\t\t\"$not\": [\"$theiaVariableUri\"]\n" +
                "\t\t\t\t}, null, \"$theiaVariableUri\"]\n" +
                "\t\t\t},\n" +
                "\t\t\t\"featureOfInterest\": \"$featureOfInterest\"\n" +
                "\t\t},\n" +
                "\t\t\"producer\": {\n" +
                "\t\t\t\"$first\": \"$producer\"\n" +
                "\t\t},\n" +
                "\t\t\"dataset\": {\n" +
                "\t\t\t\"$first\": \"$dataset\"\n" +
                "\t\t},\n" +
                "\t\t\"observations\": {\n" +
                "\t\t\t\"$push\": \"$observation\"\n" +
                "\t\t},\n" +
                "\t\t\"theiaVariable\": {\n" +
                "\t\t    \"$first\":\"$theiaVariable\"\n" +
                "\t\t},\n" +
                "\t\t\"theiaVariableNameEn\": {\n" +
                "\t\t    \"$first\":\"$theiaVariableNameEn\"\n" +
                "\t\t},\n" +
                "\t\t\"producerVariableNameEn\": {\n" +
                "\t\t    \"$push\":\"$producerVariableNameEn\"\n" +
                "\t\t},\n" +
                "\t\t\"theiaCategories\":{\n" +
                "\t\t   \"$first\":\"$categoryHierarchy\"\n" +
                "\t\t},\n" +
                "\t\t\"featureOfInterest\": {\n" +
                "\t\t    \"$first\":\"$featureOfInterest\"\n" +
                "\t\t}\n" +
                "\t\t\n" +
                "\t}";
        AggregationOperation g1 = new GenericAggregationOperation("$group", groupJson);

        try( CloseableIterator<ObservationDocumentLite> obsLiteIterator = this.mongoDbUtils.aggregateToStream("observations", ObservationDocumentLite.class,m1, glu1, a1, g1) ) {
            while (obsLiteIterator.hasNext()) {
                this.obsLiteRepo.save(obsLiteIterator.next());
                long ramIntermediate = (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / (1024 * 1024);
                assertThat(ramIntermediate).isLessThan(200); // 200mo
                System.out.println(ramIntermediate);
            }
        } // resource auto closeable

    }
}
