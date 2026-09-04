package com.example.lab8.strategy;

public class DiscountContext {
	private final DiscountStrategy strategy;

	public DiscountContext(String discountType) {
		strategy = switch (discountType == null ? "NONE" : discountType) {
			case "MEMBER" -> new MemberDiscountStrategy();
			case "SEASONAL" -> new SeasonalSaleStrategy();
			default -> new NoDiscountStrategy();
		};
	}

	public double calculate(Double price) {
		return strategy.apply(price == null ? 0.0 : price);
	}
}
