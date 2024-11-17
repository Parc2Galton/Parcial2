package com.example.parcial.service;

import com.example.parcial.domain.Contenedor;
import com.example.parcial.service.rabbitMQService.RabbitMQService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.UUID;

@Service
public class ContenedorService {

    private final RabbitMQService rabbitMQService;

    public ContenedorService(RabbitMQService rabbitMQService) {
        this.rabbitMQService = rabbitMQService;
    }

    /**
     * Inicializa una lista de contenedores con nombres únicos.
     *
     * @param nombresContenedores Lista de nombres para los contenedores.
     * @return Flujo reactivo que emite los contenedores inicializados.
     */
    public Flux<Contenedor> inicializarContenedores(List<String> nombresContenedores) {
        return Flux.fromIterable(nombresContenedores)
                .map(nombre -> {
                    // Crear un nuevo contenedor con un ID único
                    Contenedor contenedor = new Contenedor(UUID.randomUUID(), 0, nombre);

                    // Notificar la inicialización del contenedor
                    rabbitMQService.notifyContainerInitialized(
                            contenedor.getNombre(),
                            contenedor.getIdContenedor().toString()
                    );

                    return contenedor;
                });
    }
}
