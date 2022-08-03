package fr.theialand.insitu.dataportal.backendassociation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"fr.theialand.insitu"},
		excludeFilters = {
				@ComponentScan.Filter(type = FilterType.REGEX, pattern = "fr.theialand.insitu.dataportal.api.geonetwork.*"),
				@ComponentScan.Filter(type = FilterType.REGEX, pattern = "fr.theialand.insitu.dataportal.etl.*"),
				@ComponentScan.Filter(type = FilterType.REGEX, pattern = "fr.theialand.insitu.dataportal.api.sparql.update.*"),
				@ComponentScan.Filter(type = FilterType.REGEX, pattern = "fr.theialand.insitu.dataportal.model.*")
		})
@EnableMongoRepositories("fr.theialand.insitu.dataportal.repository.mongo")
public class BackendSpringbootAssociationVariableApplication {
    
	public static void main(String[] args) {
		SpringApplication.run(BackendSpringbootAssociationVariableApplication.class, args);
	}

}
