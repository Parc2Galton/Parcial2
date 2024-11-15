package com.example.parcial.repos;

import com.example.parcial.domain.Contenedor;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ContenedorRepos extends ReactiveMongoRepository <Contenedor, String> {
}
