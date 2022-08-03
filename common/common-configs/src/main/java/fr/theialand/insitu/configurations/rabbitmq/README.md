Configuration class for RabbitMQ broker
=======================================

The class create two [AMQP direct exchanges](https://www.rabbitmq.com/tutorials/amqp-concepts.html#exchange-direct) and two [AMQP queues](https://www.rabbitmq.com/tutorials/amqp-concepts.html#queues). Exchanges are binded to queues according to routing key that correspond to the queues name.
One exchange and one queue are used to set communication between import-module application et PopulateGeonetwork application. The other exchange and the other queue are dead letter exchange and dead letter queue that are used to manage failed messages. 

Each application publishing or consuming message from RabbitMQ broker needs to have the following properties
```
amqp.queue.name=populate.geonetwork.queue
amqp.exchange.name=theia-in-situ
spring.rabbitmq.host=${RABBITMQ_URL}
spring.rabbitmq.password=${RABBITMQ_PASSWORD}
spring.rabbitmq.username=${RABBITMQ_USER}
```
Application that consume message listening to a AMQP queue needs to set additional properties to configure exponential backoff retry behaviour for failed message.
```
spring.rabbitmq.listener.simple.retry.enabled=true
spring.rabbitmq.listener.simple.retry.initial-interval=3s
spring.rabbitmq.listener.simple.retry.max-attempts=4
spring.rabbitmq.listener.simple.retry.max-interval=100s
spring.rabbitmq.listener.simple.retry.multiplier=3
```
With such configuration failed message will be retried 4 times 3s, 9s, 27s, and 81s after the first failed message. If the retried messages don't succeed the failed messgae is routed to the dead letter queue and could be processed later by the admin.
