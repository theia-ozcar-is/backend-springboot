/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.theialand.insitu.dataportal.repository.mongo.repository;

import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationOptions;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.util.CloseableIterator;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * Implementation of the interface defining custom methods. The repository infrastructure tries to autodetect custom
 * implementations by looking up classes in the package we found a repository using the naming conventions appending the
 * namespace element's attribute repository-impl-postfix to the classname. This suffix defaults to Impl. Then, Spring
 * pick up the custom bean by name rather than creating an instance.
 */
@Component
public class MongoDbUtils {

    //Indicate that mongoTemplate must be injected by Spring IoC

    private final MongoTemplate mongoTemplate;

    @Autowired
    public MongoDbUtils(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    private  <T> AggregationResults<T> aggregateWithDiskUse(String collection, Class<T> type, boolean allowDiskUse, AggregationOperation... operations) {
        if(allowDiskUse){
            AggregationOptions options = AggregationOptions.builder().allowDiskUse(true).build();
            return mongoTemplate.aggregate(Aggregation.newAggregation(operations).withOptions(options), collection, type);
        } else {
            return mongoTemplate.aggregate(Aggregation.newAggregation(operations), collection, type);
        }
    }

    /**
     * By using a Stream, we avoid loading up huge liste of big object into memeory
     * to be used with a while hasnext()
     * using diskUsage = true and a batch size of 100 ( by default, batchsize is infintie ? or 20 ? to be confirmed )
     * The batchsize is used for internal communication between spring-d-mdb and mongo server.
     * it has an impact, even on a Stream. removing it makes an OOM.
     */
    public <T> CloseableIterator<T> aggregateToStream(String collection, Class<T> type, AggregationOperation... operations) {
        // about the OOM when dealing with big lists an batchSize:
        // https://stackoverflow.com/questions/40510855/streaming-the-result-of-an-aggregate-operation-using-spring-data-mongodb
        AggregationOptions options = AggregationOptions.builder().allowDiskUse(true).cursorBatchSize(100).build();
        return mongoTemplate.aggregateStream(Aggregation.newAggregation(operations).withOptions(options), collection, type);
    }

    public <T> T aggregateToUnique(String collection, Class<T> type, boolean allowDiskUse, AggregationOperation... operations) {
        return this.aggregateWithDiskUse(collection,type,allowDiskUse,operations).getUniqueMappedResult();
    }

    public <T> List<T> aggregateToList(String collection, Class<T> type, boolean allowDiskUse, AggregationOperation... operations) {
        return this.aggregateWithDiskUse(collection,type,allowDiskUse,operations).getMappedResults();
    }

    public <T> List<T> findAll(String collection,Class<T> type) {
        return mongoTemplate.findAll(type, collection);
    }

    public <T> Page<T> aggregateToPage(String collection, Class<T> type, boolean allowDiskUse, Pageable pageable, int resultSize, AggregationOperation... operations) {
         return new PageImpl<>(this.aggregateWithDiskUse(collection,type,allowDiskUse,operations).getMappedResults(), pageable, resultSize);
    }

    public int count(String collection, boolean allowDiskUse, AggregationOperation... operations) {
        AggregationOperation[] aggregationOperations  = Arrays.copyOf(operations, operations.length+1);
        aggregationOperations[operations.length] = Aggregation.count().as("count");
        return this.aggregateWithDiskUse(collection,Document.class,allowDiskUse,aggregationOperations).getUniqueMappedResult().getInteger("count");
    }

    public UpdateResult updateFirst(Query query, Update update, String collection) {
        return mongoTemplate.updateFirst(query, update , collection);
    }

    public UpdateResult updateMulti(Query query, Update update, String collection) {
        return mongoTemplate.updateMulti(query, update , collection);
    }

    public void remove(Query query, String collection) {
        this.mongoTemplate.remove(query,collection);
    }

    public void upsert(Query query, Update update, String collection) {
        this.mongoTemplate.upsert(query, update, collection);
    }

    public void dropCollection(String collection) {
        this.mongoTemplate.dropCollection(collection);
    }

    public MongoTemplate getMongoTemplate() {
        return mongoTemplate;
    }
}
