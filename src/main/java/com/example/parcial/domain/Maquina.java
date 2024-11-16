package com.example.parcial.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "maquinas")
public class Maquina {
    @Id
    private UUID idMaquina;
    private Map<String, Integer> componentes;
    private double eficiencia;
}

