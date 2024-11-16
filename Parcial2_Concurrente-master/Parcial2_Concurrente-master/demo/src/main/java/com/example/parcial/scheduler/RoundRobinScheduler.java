package com.example.parcial.scheduler;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.concurrent.atomic.AtomicInteger;

@Service
public class RoundRobinScheduler {

    private final AtomicInteger counter = new AtomicInteger(0);

    public Mono<Integer> getNextStation() {
        return Mono.just(counter.getAndIncrement() % 3);
    }
}