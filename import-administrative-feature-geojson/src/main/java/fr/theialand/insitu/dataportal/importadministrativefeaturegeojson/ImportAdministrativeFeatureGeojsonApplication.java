package fr.theialand.insitu.dataportal.importadministrativefeaturegeojson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.entity.AdministrativeFeature;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.geojsonproperties.AdministrativeFeatureProperties;
import fr.theialand.insitu.dataportal.repository.mongo.repository.AdministrativeFeatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
@ComponentScan("fr.theialand.insitu.dataportal")
@EnableMongoRepositories("fr.theialand.insitu.dataportal.repository.mongo")
public class ImportAdministrativeFeatureGeojsonApplication implements CommandLineRunner {
	private AdministrativeFeatureRepository administrativeFeatureRepository;
	private ObjectMapper objectMapper;

	@Autowired
	public ImportAdministrativeFeatureGeojsonApplication(AdministrativeFeatureRepository administrativeFeatureRepository,
														 @Qualifier("mongoObjectMapper") // doesn't matter, we stop at the lowlevel *Node.
														 ObjectMapper objectMapper) {
		this.administrativeFeatureRepository = administrativeFeatureRepository;
		this.objectMapper = objectMapper;
	}

	public static void main(String[] args) {
		SpringApplication.run(ImportAdministrativeFeatureGeojsonApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		File folder = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX+"administrative-geojson");
		List<File> geojsons = Arrays.stream(folder.listFiles()).filter(file -> file.getName().substring(file.getName().lastIndexOf(".") + 1).equals("geojson")).collect(Collectors.toList());
		List<AdministrativeFeature> featureList = new ArrayList<>();
		geojsons.forEach(geojsonFile -> {
			int i =0;
			String adminLevel2NameEn = null;
			List<AdministrativeFeature> producerFeature = new ArrayList<>();
			JsonNode jsonNode = null;
			try {
				jsonNode = objectMapper.readTree(geojsonFile);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			for (JsonNode feature : jsonNode.get("features")) {
				try {
					if (!feature.get("geometry").isNull() && !feature.get("geometry").get("type").isNull()) {

						AdministrativeFeature adminFeature = objectMapper.treeToValue(feature, AdministrativeFeature.class);
						if (adminFeature.getProperties().getAdmin_level() == 2) {
							adminLevel2NameEn = adminFeature.getProperties().getName_en();
						}
						producerFeature.add(adminFeature);
					}
				} catch (JsonProcessingException e) {
					throw new RuntimeException(e);
				}
			}
			for (AdministrativeFeature feature : producerFeature) {
					AdministrativeFeatureProperties properties = feature.getProperties();
					properties.setAdmin_level2_parent_name_en(adminLevel2NameEn);
					feature.setProperties(properties);
			}
			featureList.addAll(producerFeature);
		});
		administrativeFeatureRepository.deleteAll();
		administrativeFeatureRepository.saveAll(featureList);
	}
}
