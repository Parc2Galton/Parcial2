package com.example.parcial.estaciones;

import com.example.parcial.RabbitMQ.RabbitMQService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Component
public class EstacionTrabajoC {

    @Autowired
    private RabbitMQService rabbitMQService;

    public Mono<String> producirComponenteC() {
        return rabbitMQService.receiveMessage("queueB")
                .flatMap(componenteB -> Mono.just("Componente C producido")
                        .flatMap(componenteC -> rabbitMQService.sendMessage("queueC", componenteC).thenReturn(componenteC)))
                .subscribeOn(Schedulers.parallel());
    }
}