package fr.theialand.insitu.variablecategoriescollectionpopulate;


import fr.theialand.insitu.dataportal.api.sparql.query.RDFQueryClient;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.entity.TheiaCategory;
import fr.theialand.insitu.dataportal.repository.mongo.repository.VariableCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.List;

@SpringBootApplication
@ComponentScan(basePackages = {"fr.theialand.insitu.dataportal.api.sparql.query","fr.theialand.insitu.dataportal.repository.mongo"})
@EnableMongoRepositories("fr.theialand.insitu.dataportal.repository.mongo")
public class VariableCategoriesCollectionPopulateApplication implements ApplicationRunner {

	@Autowired
	VariableCategoryRepository repository;

	@Autowired
	RDFQueryClient rdfQueryClient;

	public static void main(String[] args) {
		SpringApplication.run(VariableCategoriesCollectionPopulateApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) {
		repository.deleteAll();
		List<TheiaCategory> vcs = rdfQueryClient.getAllVariableCategories();
		vcs.forEach(vc -> {
					repository.insert(vc);
				}
		);
	}
}