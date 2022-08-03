package fr.theialand.insitu.dataportal.model.configuration;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObjectMapperConfiguration {

    @Bean("jsonSchemaObjectMapper")
    ObjectMapper objectMapperBean() {

        return JsonMapper.builder()
                .findAndAddModules()
                .addModule(new TheiaModule())

                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                .enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING)
                .build();

        // next are SERialisation features, unneeded for deserialisation
//                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
//                .disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
//                .serializationInclusion(JsonInclude.Include.NON_NULL)
//                .defaultDateFormat(new StdDateFormat().withColonInTimeZone(true))


    }
}
