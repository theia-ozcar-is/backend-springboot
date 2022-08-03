package fr.theialand.insitu.dataportal.populategeonetwork;

import fr.theialand.insitu.dataportal.etl.EtlService;
import fr.theialand.insitu.dataportal.etl.exception.EtlException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.messaging.handler.annotation.Payload;

import java.util.Date;

@SpringBootApplication(scanBasePackages = {
        "fr.theialand.insitu.configurations.rabbitmq",
        "fr.theialand.insitu.dataportal.etl",
        "fr.theialand.insitu.dataportal.api",
        "fr.theialand.insitu.dataportal.repository.mongo"})

@EnableMongoRepositories("fr.theialand.insitu.dataportal.repository.mongo")
public class PopulateGeonetworkApp {

    private static Logger logger = LoggerFactory.getLogger(PopulateGeonetworkApp.class);

    public static void main(String[] args) {
        SpringApplication.run(PopulateGeonetworkApp.class, args);
    }

    private final EtlService etlService;

    @Autowired
    public PopulateGeonetworkApp(EtlService etlService) {
        this.etlService = etlService;
    }

    /**
     * AMQP listener that will listen for amqp.queue.name messages. The message contains the producerId and trigger the
     * creation and the import of ISO19139 files in the geonetwork.
     * This RabbitMQ listener is configured to retry several time the import before to throw an exception in case of a
     * geonetwork failure. If the failure persist the message is stored in a dead letter queue and can be processed later.
     * The retry configuration is stored in application.properties
     * @param payload payload containing the producerId
     * @return true if the import in geonetwork is successful
     * @throws Exception is thrown if the import in geonetwork failed
     */
    @RabbitListener(queues = "${amqp.queue.name}" )
    public void process(@Payload String payload) throws Exception {
        logger.info("Processing message from non-blocking-queue: {}", new Date() + ": " + payload);
        try {
        etlService.updateAllDatasetsFromProducer(payload);
        } catch (Exception e) {
            logger.error("Error inserting dataset entries in Geonetwork - Check the geonetwork server");
            throw new Exception("Error inserting dataset entries in Geonetwork");
        }
    }
}
