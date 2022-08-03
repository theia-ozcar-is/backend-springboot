package fr.theialand.insitu.dataportal.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.web.client.RestTemplateAutoConfiguration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@ImportAutoConfiguration(RestTemplateAutoConfiguration.class)
@Configuration
public class RestClientConfig {

    @Bean
    protected static RestTemplate createRestTemplateClient(@Autowired RestTemplateBuilder restBuilder ) {

        return restBuilder.build();
    }

}
