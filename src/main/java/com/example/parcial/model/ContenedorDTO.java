package com.example.parcial.model;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ContenedorDTO {
    @NotNull
    private UUID idContenedor;

    @PositiveOrZero
    private int numeroDeBolas;

    @NotNull
    @Size(min = 1, max = 100)
    private String nombre;
}
