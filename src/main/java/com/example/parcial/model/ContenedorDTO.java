package com.example.parcial.model;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContenedorDTO {
    @NotNull
    private UUID idContenedor;

    @PositiveOrZero
    private int numeroDeBolas;

    @NotNull
    @Size(min = 1, max = 100)
    private String nombre;
}
