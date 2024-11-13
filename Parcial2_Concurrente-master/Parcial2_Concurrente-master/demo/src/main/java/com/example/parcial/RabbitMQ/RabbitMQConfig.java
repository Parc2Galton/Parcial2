package com.example.parcial.RabbitMQ;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue queueA() {
        return new Queue("queueA", false);
    }
    @Bean
    public Queue queueB() {
        return new Queue("queueB", false);
    }

    @Bean
    public Queue queueC() {
        return new Queue("queueC", false);
    }
}