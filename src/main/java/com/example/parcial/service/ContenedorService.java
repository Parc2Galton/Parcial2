package com.example.parcial.service;

import com.example.parcial.domain.Contenedor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.UUID;

@Service
public class ContenedorService {

    public Flux<Contenedor> inicializarContenedores(List<String> nombresContenedores) {
        return Flux.fromIterable(nombresContenedores)
                .map(nombre -> new Contenedor(UUID.randomUUID(), 0, nombre));
    }
}
