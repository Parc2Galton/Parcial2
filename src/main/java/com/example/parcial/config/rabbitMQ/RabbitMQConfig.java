package com.example.parcial.config.rabbitMQ;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String EXCHANGE_NAME = "galton.exchange";
    public static final String QUEUE_NAME = "galton.queue";
    public static final String ROUTING_KEY = "galton.routingKey";

    @Bean
    public Queue galtonQueue() {
        return QueueBuilder.durable(QUEUE_NAME).build();
    }

    @Bean
    public DirectExchange galtonExchange() {
        return new DirectExchange(EXCHANGE_NAME);
    }

    @Bean
    public Binding galtonBinding(Queue galtonQueue, DirectExchange galtonExchange) {
        return BindingBuilder.bind(galtonQueue).to(galtonExchange).with(ROUTING_KEY);
    }
}
