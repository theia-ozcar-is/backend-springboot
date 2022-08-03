package fr.theialand.insitu.configurations.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    private static Logger logger = LoggerFactory.getLogger(RabbitMQConfig.class);

    @Value("${amqp.queue.name}")
    private String queueName;

    @Value("${amqp.exchange.name}")
    private String exchangeName;


    /**
     * Creation of queues and exchange
     * @return Declarable
     */
    @Bean
    public Declarables createQueues() {
        String deadLetterExchange = exchangeName + ".dlx";
        String deadLetterQueue = queueName + ".dlq";
        Declarables declarables = new Declarables(
                new DirectExchange(exchangeName),
                new DirectExchange(deadLetterExchange),
                QueueBuilder.durable(queueName)
                        .withArgument("x-dead-letter-exchange", deadLetterExchange)
                        .withArgument("x-dead-letter-routing-key", deadLetterQueue)
                        .build(),
                QueueBuilder.durable(deadLetterQueue)
                        .build(),
                new Binding(queueName, Binding.DestinationType.QUEUE, exchangeName, queueName, null),
                new Binding(deadLetterQueue, Binding.DestinationType.QUEUE, deadLetterExchange, deadLetterQueue, null)
        );
        return declarables;
    }



}
