package com.example.parcial.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "bolas")
public class Bola {
    @Id
    private UUID idBola;
    private List<Integer> recorridoActual;
    private String contenedorFinal;
}
