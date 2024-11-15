package com.example.parcial;

import com.example.parcial.Ensamblaje.CoordinadorEstaciones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@SpringBootApplication
@RestController
public class Parcial2Application implements CommandLineRunner {

    @Autowired
    private CoordinadorEstaciones coordinadorEstaciones;

    public static void main(String[] args) {
        SpringApplication.run(Parcial2Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        coordinarProduccion();
    }

    private void coordinarProduccion() {
        Mono<String> resultado = coordinadorEstaciones.coordinarProduccion();
        resultado.subscribe(
            success -> System.out.println("Producción exitosa: " + success),
            error -> System.err.println("Error en la producción: " + error.getMessage())
        );
    }
}