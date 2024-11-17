package com.example.parcial;

import com.example.parcial.service.FabricaService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SpringBootApplication
@RestController
public class Parcial2Application {

	private final FabricaService fabricaService;

	public Parcial2Application(FabricaService fabricaService) {
		this.fabricaService = fabricaService;
	}

	public static void main(String[] args) {
		SpringApplication.run(Parcial2Application.class, args);
	}

	@Bean
	CommandLineRunner run(FabricaService fabricaService) {
		return args -> {
			// Configuración de la simulación
			int totalBolas = 100;  // Número de bolas a lanzar
			int niveles = 10;      // Número de niveles en el tablero de Galton
			double media = 5.0;    // Media de la distribución normal
			double desviacionEstandar = 2.0; // Desviación estándar de la distribución normal
			String nombreProduccion = "Producción Galton";

			// Generar nombres de los 10 contenedores
			List<String> nombresContenedores = IntStream.range(0, 10)
					.mapToObj(i -> "Contenedor-" + i)
					.collect(Collectors.toList());

			// Iniciar producción
			fabricaService.iniciarProduccion(totalBolas, niveles, media, desviacionEstandar, nombreProduccion)
					.doOnSuccess(maquina -> {
						System.out.println("Producción completada.");
						System.out.println("Distribución de componentes: " + maquina.getComponentes());
						System.out.println("Eficiencia de la máquina: " + maquina.getEficiencia());
					})
					.doOnError(error -> System.err.println("Error durante la producción: " + error.getMessage()))
					.subscribe();
		};
	}

	@GetMapping("/simularProduccion")
	public Mono<String> simularProduccion(
			@RequestParam int totalBolas,
			@RequestParam int niveles,
			@RequestParam double media,
			@RequestParam double desviacionEstandar,
			@RequestParam String nombreProduccion) {

		// Generar nombres de los 10 contenedores
		List<String> nombresContenedores = IntStream.range(0, 10)
				.mapToObj(i -> "Contenedor-" + i)
				.collect(Collectors.toList());

		// Iniciar producción reactiva
		return fabricaService.iniciarProduccion(totalBolas, niveles, media, desviacionEstandar, nombreProduccion)
				.map(maquina -> {
					StringBuilder resultado = new StringBuilder();
					resultado.append("Producción completada.\n");
					resultado.append("Distribución de componentes: ").append(maquina.getComponentes()).append("\n");
					resultado.append("Eficiencia de la máquina: ").append(maquina.getEficiencia()).append("\n");
					return resultado.toString();
				})
				.onErrorResume(error -> Mono.just("Error durante la producción: " + error.getMessage()));
	}
}
