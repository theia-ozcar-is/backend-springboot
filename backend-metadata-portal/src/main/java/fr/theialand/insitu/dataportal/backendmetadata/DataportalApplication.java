package fr.theialand.insitu.dataportal.backendmetadata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Annotation enabling three annotations - @EnableAutoConfiguration: enable Spring Boot’s auto-fr.theialand.insitu.dataportal.backendassociation.configuration mechanism
 * depending on the jar in the classpath - @ComponentScan: enable @Component scan on the package where the application
 * is located. Each @Component will wired in Spring IOC container. @ComponentScan also include @Configuration class. -
 *
 * @Configuration: allow to register extra beans in the context or import additional fr.theialand.insitu.dataportal.backendassociation.configuration classes
 */
@SpringBootApplication
@ComponentScan("fr.theialand.insitu.dataportal")
@EnableMongoRepositories("fr.theialand.insitu.dataportal.repository.mongo")
public class DataportalApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(DataportalApplication.class, args);
    }

}
