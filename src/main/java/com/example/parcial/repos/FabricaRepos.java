package com.example.parcial.repos;

import com.example.parcial.domain.Fabrica;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface FabricaRepos extends ReactiveMongoRepository <Fabrica, String> {
}
