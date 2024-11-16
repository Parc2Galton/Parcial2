package com.example.parcial.model;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BolaDTO {
    @NotNull
    private UUID idBola;

    @NotNull
    @Size(min = 1)
    private List<Integer> recorridoActual;

    @NotNull
    @Size(min = 1, max = 100)
    private String contenedorFinal;
}
