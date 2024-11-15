package com.example.parcial.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "contenedores")
public class Contenedor {
    @Id
    private UUID idContenedor;
    private int numeroDeBolas;
    private String nombre;
}
