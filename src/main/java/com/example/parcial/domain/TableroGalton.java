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
@Document(collection = "tableros_galton")
public class TableroGalton {
    @Id
    private UUID idTablero;
    private int numeroDeNiveles;
    private List<UUID> idContenedores;
}
