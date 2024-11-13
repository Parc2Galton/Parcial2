package com.example.parcial.estaciones;

import com.example.parcial.RabbitMQ.RabbitMQService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class EstacionTrabajoA {

    @Autowired
    private RabbitMQService rabbitMQService;

    public Mono<String> producirComponenteA() {
        return rabbitMQService.receiveMessage("queueA")
                .flatMap(componenteA -> Mono.just("Componente B producido")
                        .flatMap(componenteB -> rabbitMQService.sendMessage("queueB", componenteB).thenReturn(componenteB)));
    }
}