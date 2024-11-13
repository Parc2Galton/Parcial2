package com.example.parcial.Ensamblaje;

import com.example.parcial.RabbitMQ.RabbitMQService;

import com.example.parcial.estaciones.EstacionTrabajoA;
import com.example.parcial.estaciones.EstacionTrabajoB;
import com.example.parcial.estaciones.EstacionTrabajoC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CoordinadorEstaciones {

    @Autowired
    private RabbitMQService rabbitMQService;

    @Autowired
    private RoundRobinScheduler scheduler;

    @Autowired
    private EstacionTrabajoA estacionTrabajoA;

    @Autowired
    private EstacionTrabajoB estacionTrabajoB;

    @Autowired
    private EstacionTrabajoC estacionTrabajoC;

    public Mono<String> coordinarProduccion() {
        int station = scheduler.getNextStation();
        switch (station) {
            case 0:
                return estacionTrabajoA.producirComponenteA();
            case 1:
                return estacionTrabajoB.producirComponenteB();
            case 2:
                return estacionTrabajoC.producirComponenteC();
            default:
                return Mono.error(new IllegalStateException("Invalid station index"));
        }
    }
}