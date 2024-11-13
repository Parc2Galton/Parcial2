package com.example.parcial.RabbitMQ;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class RabbitMQService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public Mono<Void> sendMessage(String queueName, String message) {
        return Mono.fromRunnable(() -> rabbitTemplate.convertAndSend(queueName, message));
    }

    public Mono<String> receiveMessage(String queueName) {
        return Mono.fromCallable(() -> (String) rabbitTemplate.receiveAndConvert(queueName));
    }
}