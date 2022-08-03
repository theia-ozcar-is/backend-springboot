package fr.theialand.insitu.dataportal.repository.mongo.configuration;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.mongodb.client.model.geojson.Geometry;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.dataset.Contact;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.geojsonproperties.Properties;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.observation.SamplingFeature;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.observation.Sensor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.core.geo.GeoJson;
import org.springframework.data.mongodb.core.geo.GeoJsonModule;

@Configuration
public class JacksonConfiguration {
    @Primary
    @Bean("mongoRepositoryObjectMapper")
    @JsonDeserialize()
    ObjectMapper mongoObjectMapper() {
        // Automatically register JavaTimeModule
        ObjectMapper objectMapper = JsonMapper.builder()
                .findAndAddModules()
                .build();
        objectMapper.registerModule(new GeoJsonModule());
		SimpleModule simpleModule = new SimpleModule();
		simpleModule.addDeserializer(Geometry.class,new MongoGeometryDeserializer());
        simpleModule.addDeserializer(GeoJson.class ,new SpringDataMongoGeoJsonDeserializer());
        simpleModule.addDeserializer(Contact.class,new ContactsDeserializer());
        simpleModule.addDeserializer(Sensor.class, new SensorCustomDeserializer());
        simpleModule.addDeserializer(Properties.class, new FeatureOfInterestPropertiesDeserializer());
        simpleModule.addDeserializer(SamplingFeature.class,new SamplingFeatureDeserializer());
        simpleModule.addSerializer(Geometry.class, new MongoGeometrySerializer());
		objectMapper.registerModule(simpleModule);
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        // StdDateFormat is ISO8601 since jackson 2.9
        objectMapper.setDateFormat(new StdDateFormat().withColonInTimeZone(true));
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS,false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return objectMapper;
    }
}
