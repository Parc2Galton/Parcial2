package com.example.parcial.estaciones;

import com.example.parcial.RabbitMQ.RabbitMQService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Component
public class EstacionTrabajoA {

    @Autowired
    private RabbitMQService rabbitMQService;

    public Mono<String> producirComponenteA() {
        return Mono.just("Componente A producido")
                .flatMap(componenteA -> rabbitMQService.sendMessage("queueA", componenteA).thenReturn(componenteA))
                .subscribeOn(Schedulers.parallel());
    }
}