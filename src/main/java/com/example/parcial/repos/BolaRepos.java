package com.example.parcial.repos;

import com.example.parcial.domain.Bola;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface BolaRepos extends ReactiveMongoRepository <Bola, String> {
}
