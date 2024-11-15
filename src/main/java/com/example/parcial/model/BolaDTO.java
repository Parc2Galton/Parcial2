package com.example.parcial.model;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
