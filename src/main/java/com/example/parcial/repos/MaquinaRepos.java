package com.example.parcial.repos;

import com.example.parcial.domain.Maquina;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface MaquinaRepos extends ReactiveMongoRepository <Maquina, String> {
}
