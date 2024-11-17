package com.example.parcial.service;

import com.example.parcial.domain.Bola;
import com.example.parcial.domain.Contenedor;
import com.example.parcial.service.rabbitMQService.RabbitMQService;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Random;
import java.util.UUID;

public class BolaService {

    private final Random random = new Random();
    private final RabbitMQService rabbitMQService;

    public BolaService(RabbitMQService rabbitMQService) {
        this.rabbitMQService = rabbitMQService;
    }


    public Mono<Bola> generarBola(int niveles, double media, double desviacionEstandar, List<Contenedor> contenedores) {
        if (contenedores.isEmpty()) {
            return Mono.error(new IllegalArgumentException("La lista de contenedores no puede estar vacía."));
        }

        // Calcular posición final de la bola basada en una distribución normal
        int posicionFinal = (int) Math.round(media + desviacionEstandar * random.nextGaussian());
        posicionFinal = Math.max(0, Math.min(posicionFinal, contenedores.size() - 1)); // Limitar el índice

        // Obtener el contenedor correspondiente
        Contenedor contenedor = contenedores.get(posicionFinal);

        // Crear la bola
        Bola bola = new Bola(UUID.randomUUID(), List.of(posicionFinal), contenedor.getNombre());

        // Incrementar el número de bolas en el contenedor
        contenedor.setNumeroDeBolas(contenedor.getNumeroDeBolas() + 1);

        // Notificar la asignación de la bola al contenedor
        rabbitMQService.notifyBallAddedToContainer(contenedor.getNombre(), bola.getIdBola().toString());

        return Mono.just(bola);
    }
}
