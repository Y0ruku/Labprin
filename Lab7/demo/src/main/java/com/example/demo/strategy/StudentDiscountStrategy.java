package com.example.demo.strategy;

public class StudentDiscountStrategy implements DiscountStrategy {

	@Override
	public double apply(double price) {
		return price * 0.9; // 10% off
	}

	@Override
	public String name() {
		return "ส่วนลดนักศึกษา (10%)";
	}
}
