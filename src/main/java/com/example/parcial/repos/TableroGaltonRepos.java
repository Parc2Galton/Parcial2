package com.example.parcial.repos;

import com.example.parcial.domain.TableroGalton;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface TableroGaltonRepos extends ReactiveMongoRepository <TableroGalton, String> {
}
