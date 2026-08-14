package com.example.demo.strategy;

public class DiscountContext {

	public static double applyDiscount(Double price, String discountType) {
		if (price == null) return 0.0;
		if (discountType == null) discountType = "NONE";
		switch (discountType) {
			case "STUDENT":
				return new StudentDiscountStrategy().apply(price);
			case "SEASONAL":
				return new SeasonalSaleStrategy().apply(price);
			default:
				return new NoDiscountStrategy().apply(price);
		}
	}

	public static String getDiscountName(String discountType) {
		if (discountType == null) return "ราคาปกติ";
		switch (discountType) {
			case "STUDENT":
				return new StudentDiscountStrategy().name();
			case "SEASONAL":
				return new SeasonalSaleStrategy().name();
			default:
				return new NoDiscountStrategy().name();
		}
	}
}
