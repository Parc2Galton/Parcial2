package com.example.parcial.model;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import lombok.*;

import java.util.Map;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MaquinaDTO {
    @NotNull
    private UUID idMaquina;

    @NotNull
    private Map<String, Integer> componentes;

    private double eficiencia;
}
