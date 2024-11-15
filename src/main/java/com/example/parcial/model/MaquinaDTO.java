package com.example.parcial.model;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;

import java.util.Map;
import java.util.UUID;

public class MaquinaDTO {
    @NotNull
    private UUID idMaquina;

    @NotNull
    private Map<String, Integer> componentes;

    private double eficiencia;
}
