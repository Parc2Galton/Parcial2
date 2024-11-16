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
@Document(collection = "tableros_galton")
public class TableroGalton {
    @Id
    private UUID idTablero;
    private int numeroDeNiveles;
    private List<UUID> idContenedores;
}
