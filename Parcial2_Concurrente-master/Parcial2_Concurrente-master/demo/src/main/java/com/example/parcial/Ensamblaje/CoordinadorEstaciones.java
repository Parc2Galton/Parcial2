package com.example.parcial.Ensamblaje;

import com.example.parcial.estaciones.EstacionTrabajoA;
import com.example.parcial.estaciones.EstacionTrabajoB;
import com.example.parcial.estaciones.EstacionTrabajoC;
import com.example.parcial.scheduler.RoundRobinScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Component
public class CoordinadorEstaciones {

    @Autowired
    private EstacionTrabajoA estacionTrabajoA;

    @Autowired
    private EstacionTrabajoB estacionTrabajoB;

    @Autowired
    private EstacionTrabajoC estacionTrabajoC;

    @Autowired
    private RoundRobinScheduler schedulerService;

    public Mono<String> coordinarProduccion() {
        return schedulerService.getNextStation()
                .flatMap(station -> {
                    switch (station) {
                        case 0:
                            return estacionTrabajoA.producirComponenteA();
                        case 1:
                            return estacionTrabajoB.producirComponenteB();
                        case 2:
                            return estacionTrabajoC.producirComponenteC();
                        default:
                            return Mono.error(new IllegalStateException("Invalid station"));
                    }
                })
                .subscribeOn(Schedulers.parallel());
    }
}