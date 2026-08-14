package com.example.demo.strategy;

public class SeasonalSaleStrategy implements DiscountStrategy {

	@Override
	public double apply(double price) {
		return price * 0.8; // 20% off
	}

	@Override
	public String name() {
		return "ส่วนลดเทศกาล (20%)";
	}
}
