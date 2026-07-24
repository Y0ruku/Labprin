package com.example.coffeeshop.service;

import com.example.coffeeshop.model.Coffee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class CoffeeService {
    private final List<Coffee> coffees = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    public CoffeeService() {
        // seed data to match the exercise spec: id=1 Espresso 45.0, id=2 Latte 55.0
        coffees.add(new Coffee(idCounter.getAndIncrement(), "Espresso", 45.0));
        coffees.add(new Coffee(idCounter.getAndIncrement(), "Latte", 55.0));
    }

    public List<Coffee> findAll() {
        return new ArrayList<>(coffees);
    }

    public Optional<Coffee> findById(Long id) {
        return coffees.stream().filter(c -> c.getId().equals(id)).findFirst();
    }

    public List<Coffee> findByName(String name) {
        if (name == null || name.isBlank()) return new ArrayList<>();
        String q = name.trim().toLowerCase();
        return coffees.stream()
                .filter(c -> c.getName() != null && c.getName().toLowerCase().contains(q))
                .collect(Collectors.toList());
    }

    public Coffee create(Coffee coffee) {
        coffee.setId(idCounter.getAndIncrement());
        coffees.add(coffee);
        return coffee;
    }

    public Optional<Coffee> update(Long id, Coffee coffee) {
        for (int i = 0; i < coffees.size(); i++) {
            if (coffees.get(i).getId().equals(id)) {
                coffee.setId(id);
                coffees.set(i, coffee);
                return Optional.of(coffee);
            }
        }
        return Optional.empty();
    }

    public boolean delete(Long id) {
        return coffees.removeIf(c -> c.getId().equals(id));
    }
}
