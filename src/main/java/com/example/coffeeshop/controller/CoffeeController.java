package com.example.coffeeshop.controller;

import com.example.coffeeshop.model.Coffee;
import com.example.coffeeshop.service.CoffeeService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/coffees")
public class CoffeeController {
    private final CoffeeService coffeeService;

    public CoffeeController(CoffeeService coffeeService) {
        this.coffeeService = coffeeService;
    }

    @GetMapping(produces = "application/json")
    public List<Coffee> list() {
        return coffeeService.findAll();
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Coffee> get(@PathVariable Long id) {
        return coffeeService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/search", produces = "application/json")
    public ResponseEntity<List<Coffee>> search(@RequestParam(name = "name", required = false) String name) {
        List<Coffee> results = coffeeService.findByName(name);
        if (results.isEmpty()) {
            return ResponseEntity.ok(results);
        }
        return ResponseEntity.ok(results);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Coffee> create(@jakarta.validation.Valid @RequestBody Coffee coffee) {
        Coffee created = coffeeService.create(coffee);
        URI location = URI.create(String.format("/coffees/%d", created.getId()));
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(location);
        return new ResponseEntity<>(created, headers, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Coffee> update(@PathVariable Long id, @jakarta.validation.Valid @RequestBody Coffee coffee) {
        return coffeeService.update(id, coffee)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean removed = coffeeService.delete(id);
        if (removed) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
