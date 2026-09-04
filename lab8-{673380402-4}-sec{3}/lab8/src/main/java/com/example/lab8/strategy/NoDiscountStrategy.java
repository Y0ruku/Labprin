package com.example.lab8.strategy;

public class NoDiscountStrategy implements DiscountStrategy {
	@Override
	public double apply(double price) { return price; }
}
