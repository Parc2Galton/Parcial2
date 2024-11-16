package com.example.parcial.service;

import com.example.parcial.domain.Fabrica;
import com.example.parcial.domain.Maquina;
import com.example.parcial.domain.Contenedor;
import com.example.parcial.domain.TableroGalton;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class FabricaService {

    private final ContenedorService contenedorService;
    private final TableroGaltonService tableroGaltonService;
    private final MaquinaService maquinaService;

    private static final int NUM_CONTENEDORES = 10;  // Número fijo de contenedores

    public FabricaService(ContenedorService contenedorService,
                          TableroGaltonService tableroGaltonService,
                          MaquinaService maquinaService) {
        this.contenedorService = contenedorService;
        this.tableroGaltonService = tableroGaltonService;
        this.maquinaService = maquinaService;
    }

    public Mono<Maquina> iniciarProduccion(int totalBolas, int niveles, double media, double desviacionEstandar, String nombreProduccion) {
        UUID idFabrica = UUID.randomUUID();
        LocalDateTime fechaProduccion = LocalDateTime.now();

        // Inicializar nombres de contenedores de forma fija
        List<String> nombresContenedores = IntStream.range(0, NUM_CONTENEDORES)
                .mapToObj(i -> "Contenedor-" + i)
                .collect(Collectors.toList());

        return contenedorService.inicializarContenedores(nombresContenedores)
                .collectList()
                .flatMap(contenedores -> tableroGaltonService.inicializarTablero(niveles, contenedores)
                        .flatMap(tablero -> simularProduccion(totalBolas, niveles, media, desviacionEstandar, contenedores)
                                .flatMap(distribucion -> ensamblarMaquina(distribucion, idFabrica, tablero.getIdTablero(), nombreProduccion, contenedores))
                        )
                );
    }

    private Mono<Map<String, Integer>> simularProduccion(int totalBolas, int niveles, double media, double desviacionEstandar, List<Contenedor> contenedores) {
        return tableroGaltonService.simularCaidaNormal(totalBolas, niveles, media, desviacionEstandar, contenedores)
                .thenMany(Flux.fromIterable(contenedores))
                .collect(Collectors.toMap(Contenedor::getNombre, Contenedor::getNumeroDeBolas));
    }

    private Mono<Maquina> ensamblarMaquina(Map<String, Integer> distribucion, UUID idFabrica, UUID idTablero, String nombreProduccion, List<Contenedor> contenedores) {
        return maquinaService.crearMaquina(distribucion)
                .flatMap(maquina -> guardarFabrica(idFabrica, idTablero, nombreProduccion, contenedores)
                        .then(Mono.just(maquina))
                );
    }

    private Mono<Void> guardarFabrica(UUID idFabrica, UUID idTablero, String nombreProduccion, List<Contenedor> contenedores) {
        List<UUID> idContenedores = contenedores.stream().map(Contenedor::getIdContenedor).toList();
        Fabrica fabrica = new Fabrica(idFabrica, idContenedores, idTablero, nombreProduccion, LocalDateTime.now());
        // Implementa aquí la lógica de persistencia si es necesario
        return Mono.empty(); // Placeholder si no se requiere persistencia
    }
}
