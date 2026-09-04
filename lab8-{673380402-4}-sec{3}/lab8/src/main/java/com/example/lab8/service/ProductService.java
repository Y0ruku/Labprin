package com.example.lab8.service;

import com.example.lab8.model.Product;
import com.example.lab8.model.Review;
import com.example.lab8.repository.ProductRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {
	private final ProductRepository productRepository;

	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Transactional(readOnly = true)
	public List<Product> findAll() {
		return productRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Product findById(Long id) {
		return productRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("ไม่พบสินค้า id: " + id));
	}

	@Transactional
	public Product save(Product product) {
		prepareRelationships(product);
		return productRepository.save(product);
	}

	@Transactional
	public Product update(Long id, Product submitted) {
		Product existing = findById(id);
		existing.setName(submitted.getName());
		existing.setCategory(submitted.getCategory());
		existing.setBrand(submitted.getBrand());
		existing.setStock(submitted.getStock());
		existing.setPrice(submitted.getPrice());
		existing.setDiscountType(submitted.getDiscountType());
		if (submitted.getDetail() != null) {
			submitted.getDetail().setProduct(existing);
			existing.setDetail(submitted.getDetail());
		}
		return productRepository.save(existing);
	}

	@Transactional
	public void delete(Long id) {
		productRepository.deleteById(id);
	}

	private void prepareRelationships(Product product) {
		if (product.getDetail() != null) {
			product.getDetail().setProduct(product);
		}
		if (product.getReviews() != null) {
			product.getReviews().removeIf(this::isBlankReview);
			product.getReviews().forEach(review -> review.setProduct(product));
		}
	}

	private boolean isBlankReview(Review review) {
		return review == null || (review.getReviewer() == null && review.getComment() == null);
	}
}
