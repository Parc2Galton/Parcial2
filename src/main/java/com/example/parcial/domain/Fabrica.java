package com.example.parcial.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "fabricas")
public class Fabrica {
    @Id
    private UUID idFabrica;
    private List<UUID> idContenedores;
    private UUID idTableroGalton;
    private String nombreProduccion;
    private LocalDateTime fechaProduccion;
}
