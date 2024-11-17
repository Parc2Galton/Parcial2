package com.example.parcial.service;

import com.example.parcial.domain.TableroGalton;
import com.example.parcial.domain.Contenedor;
import com.example.parcial.domain.Bola;
import com.example.parcial.service.rabbitMQService.RabbitMQService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TableroGaltonService {

    private final BolaService bolaService;
    private final RabbitMQService rabbitMQService;

    public TableroGaltonService(BolaService bolaService, RabbitMQService rabbitMQService) {
        this.bolaService = bolaService;
        this.rabbitMQService = rabbitMQService;
    }


    public Mono<TableroGalton> inicializarTablero(int niveles, List<Contenedor> contenedores) {
        TableroGalton tablero = new TableroGalton(
                UUID.randomUUID(),
                niveles,
                contenedores.stream().map(Contenedor::getIdContenedor).collect(Collectors.toList())
        );
        return Mono.just(tablero);
    }

    public Flux<Bola> simularCaidaNormal(int totalBolas, int niveles, double media, double desviacionEstandar, List<Contenedor> contenedores) {
        return Flux.range(1, totalBolas)
                .flatMap(i -> bolaService.generarBola(niveles, media, desviacionEstandar, contenedores))
                .doOnNext(bola -> {
                    // Notificar que se agregó una bola a un contenedor
                    rabbitMQService.notifyBallAddedToContainer(
                            bola.getContenedorFinal(),
                            bola.getIdBola().toString()
                    );
                });
    }
}
