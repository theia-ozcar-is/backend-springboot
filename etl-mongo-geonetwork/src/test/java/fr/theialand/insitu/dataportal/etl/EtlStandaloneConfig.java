package fr.theialand.insitu.dataportal.etl;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * As it has been decided that the ETL won't be an APP but a Module,
 * for testing we need to configure these
 */
@SpringBootConfiguration
@ComponentScan("fr.theialand.insitu.dataportal") // to access beans from outside
@EnableAutoConfiguration // to access MongoTemplate, auto-beans from Spring
@EnableMongoRepositories( "fr.theialand.insitu.dataportal.repository.mongo") // Repos are NOT scanned by CompScan
public class EtlStandaloneConfig {
}
