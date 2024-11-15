package com.example.parcial.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "bolas")
public class Bola {
    @Id
    private UUID idBola;
    private List<Integer> recorridoActual;
    private String contenedorFinal;
}
