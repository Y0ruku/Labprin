package com.example.lab8.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String category;
	private String brand;
	private Integer stock;
	private Double price;
	private String discountType;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	private ProductDetail detail;

	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Review> reviews = new ArrayList<>();

	public Product() {
	}

	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	public String getCategory() { return category; }
	public void setCategory(String category) { this.category = category; }
	public String getBrand() { return brand; }
	public void setBrand(String brand) { this.brand = brand; }
	public Integer getStock() { return stock; }
	public void setStock(Integer stock) { this.stock = stock; }
	public Double getPrice() { return price; }
	public void setPrice(Double price) { this.price = price; }
	public String getDiscountType() { return discountType; }
	public void setDiscountType(String discountType) { this.discountType = discountType; }
	public ProductDetail getDetail() { return detail; }
	public void setDetail(ProductDetail detail) { this.detail = detail; }
	public List<Review> getReviews() { return reviews; }
	public void setReviews(List<Review> reviews) { this.reviews = reviews; }

	public Double getDiscountedPrice() {
		return new com.example.lab8.strategy.DiscountContext(discountType).calculate(price);
	}
}
