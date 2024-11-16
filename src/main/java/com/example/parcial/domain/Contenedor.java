package com.example.parcial.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "contenedores")
public class Contenedor {
    @Id
    private UUID idContenedor;
    private int numeroDeBolas;
    private String nombre;
}
