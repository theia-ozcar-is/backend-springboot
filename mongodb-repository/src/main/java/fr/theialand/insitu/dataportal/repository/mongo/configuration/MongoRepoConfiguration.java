package fr.theialand.insitu.dataportal.repository.mongo.configuration;

import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

@Configuration
@ComponentScan("fr.theialand.insitu.dataportal.repository.mongo")
public class MongoRepoConfiguration {

    @Value("${spring.data.mongodb.uri}")
    private String connectionString;

    @Value("${spring.data.mongodb.database}")
    private String database;

    /**
     * Use standard MongoTemplate,
     * that is , WITH geojson standard coders
     * @return bean that will be used everywhere else, and by repos
     */
    @Bean
    public MongoTemplate mongoTemplate() {
        MongoDatabaseFactory facto = new SimpleMongoClientDatabaseFactory(MongoClients.create(connectionString), database);
        return new MongoTemplate( facto );
    }
}
