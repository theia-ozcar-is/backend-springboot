package fr.theialand.insitu.dataportal.importmetadata;

import fr.theialand.insitu.dataportal.api.geonetwork.GeonetworkClientImpl;
import fr.theialand.insitu.dataportal.etl.EtlServiceImpl;
import fr.theialand.insitu.dataportal.importmetadata.Exceptions.ImportException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Entrypoint of the Import App,
 * See @{@link ImportRunner} for the next step
 */
@SpringBootApplication
@ComponentScan(basePackages = {"fr.theialand.insitu"},
        excludeFilters = {
            @ComponentScan.Filter(type = FilterType.REGEX, pattern = "fr.theialand.insitu.dataportal.api.geonetwork.*"),
            @ComponentScan.Filter(type = FilterType.REGEX, pattern = "fr.theialand.insitu.dataportal.etl.*"),
            @ComponentScan.Filter(type = FilterType.REGEX, pattern = "fr.theialand.insitu.dataportal.api.sparql.update.*")
        })
@EnableMongoRepositories("fr.theialand.insitu.dataportal.repository.mongo")
public class ImportMetadataApplication {

    private static final Logger logger = LoggerFactory.getLogger(ImportMetadataApplication.class);

    public static void main(String[] args) {
        logger.info("Starting Import App");
        SpringApplication.run(ImportMetadataApplication.class, args).close();
    }
}
