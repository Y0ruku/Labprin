package com.example.coffeeshop.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class Coffee {
    private Long id;

    @NotBlank(message = "name must not be blank")
    private String name;

    @Min(value = 0, message = "price must be non-negative")
    private double price;

    public Coffee() {
    }

    public Coffee(Long id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
