package com.example.lab8.strategy;

public class SeasonalSaleStrategy implements DiscountStrategy {
	@Override
	public double apply(double price) { return price * 0.80; }
}
