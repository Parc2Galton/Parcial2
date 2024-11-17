package com.example.parcial.service;

import com.example.parcial.domain.Maquina;
import com.example.parcial.service.rabbitMQService.RabbitMQService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.UUID;

@Service
public class MaquinaService {

    private final RabbitMQService rabbitMQService;

    public MaquinaService(RabbitMQService rabbitMQService) {
        this.rabbitMQService = rabbitMQService;
    }


    public Mono<Maquina> crearMaquina(Map<String, Integer> distribucion) {
        Maquina maquina = new Maquina(UUID.randomUUID(), distribucion, calcularEficiencia(distribucion));

        // Notificar la creación de la máquina
        return Mono.just(maquina)
                .doOnSuccess(createdMachine -> rabbitMQService.notifyMachineAssembled(createdMachine.getIdMaquina().toString()));
    }


    private double calcularEficiencia(Map<String, Integer> distribucion) {
        int totalComponentes = distribucion.values().stream().mapToInt(Integer::intValue).sum();
        return totalComponentes > 0 ? 1.0 : 0.0; // Eficiencia básica calculada.
    }
}
