package com.example.parcial.model;
import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;


import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FabricaDTO {
    @NotNull
    private UUID idFabrica;

    @Size(min = 1)
    private List<UUID> idContenedores;

    @NotNull
    private UUID idTableroGalton;

    @NotNull
    @Size(min = 3, max = 100)
    private String nombreProduccion;
}

