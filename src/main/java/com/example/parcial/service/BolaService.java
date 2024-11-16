package com.example.parcial.service;

import com.example.parcial.domain.Bola;
import com.example.parcial.domain.Contenedor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class BolaService {

    private final Random random = new Random();

    public Mono<Bola> generarBola(int niveles, double media, double desviacionEstandar, List<Contenedor> contenedores) {
        // Calcular posición final de la bola basada en una distribución normal
        int posicionFinal = (int) Math.round(media + desviacionEstandar * random.nextGaussian());

        // Limitar la posición final a los índices de los contenedores (0 a NUM_CONTENEDORES - 1)
        posicionFinal = Math.max(0, Math.min(posicionFinal, contenedores.size() - 1));

        // Actualizar el contenedor correspondiente con la bola
        Contenedor contenedor = contenedores.get(posicionFinal);
        contenedor.setNumeroDeBolas(contenedor.getNumeroDeBolas() + 1);

        // Crear una instancia de Bola para almacenar los datos de trayectoria (opcional)
        Bola bola = new Bola(UUID.randomUUID(), List.of(posicionFinal), contenedor.getNombre());

        return Mono.just(bola);
    }

    private String calcularContenedorFinal(List<Integer> recorrido) {
        int contenedorIndex = recorrido.stream().mapToInt(Integer::intValue).sum();
        return "Contenedor-" + contenedorIndex;
    }

}
