package com.example.parcial.service;

import com.example.parcial.domain.TableroGalton;
import com.example.parcial.domain.Contenedor;
import com.example.parcial.domain.Bola;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TableroGaltonService {

    @Autowired
    private BolaService bolaService;

    public Mono<TableroGalton> inicializarTablero(int niveles, List<Contenedor> contenedores) {
        // Crear una instancia de TableroGalton con un ID único y los niveles especificados
        TableroGalton tablero = new TableroGalton(
                UUID.randomUUID(),
                niveles,
                contenedores.stream().map(Contenedor::getIdContenedor).collect(Collectors.toList())
        );

        // Devolver el tablero inicializado envuelto en un Mono
        return Mono.just(tablero);
    }

    public Mono<Void> simularCaidaNormal(int totalBolas, int niveles, double media, double desviacionEstandar, List<Contenedor> contenedores) {
        return Flux.range(1, totalBolas)
                .flatMap(i -> bolaService.generarBola(niveles, media, desviacionEstandar, contenedores))
                .then();
    }

    private String calcularContenedorFinal(List<Integer> recorrido) {
        int contenedorIndex = recorrido.stream().mapToInt(Integer::intValue).sum();
        return "Contenedor-" + contenedorIndex;
    }
}
