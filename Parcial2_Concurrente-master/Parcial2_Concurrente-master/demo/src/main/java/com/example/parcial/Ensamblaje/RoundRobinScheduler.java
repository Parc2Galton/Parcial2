package com.example.parcial.Ensamblaje;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
public class RoundRobinScheduler {

    private final AtomicInteger counter = new AtomicInteger(0);
    private final int numberOfStations;

    public RoundRobinScheduler(@Value("${scheduler.numberOfStations}") int numberOfStations) {
        this.numberOfStations = numberOfStations;
    }

    public int getNextStation() {
        return counter.getAndIncrement() % numberOfStations;
    }
}