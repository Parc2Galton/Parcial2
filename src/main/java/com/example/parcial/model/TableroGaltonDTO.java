package com.example.parcial.model;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TableroGaltonDTO {

    @NotNull
    private UUID idTablero;

    @Positive
    private int numeroDeNiveles;

    @NotNull
    @Size(min = 1)
    private List<UUID> idContenedores;
}
