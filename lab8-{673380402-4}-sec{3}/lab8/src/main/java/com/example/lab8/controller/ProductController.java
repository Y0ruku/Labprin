package com.example.lab8.controller;

import com.example.lab8.model.Product;
import com.example.lab8.model.ProductDetail;
import com.example.lab8.model.Review;
import com.example.lab8.service.ProductService;
import java.util.ArrayList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/products")
public class ProductController {
	private final ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping
	public String list(Model model) {
		model.addAttribute("products", productService.findAll());
		return "products/list";
	}

	@GetMapping("/add")
	public String addForm(Model model) {
		Product product = new Product();
		product.setDetail(new ProductDetail());
		product.setReviews(new ArrayList<>());
		product.getReviews().add(new Review());
		model.addAttribute("product", product);
		return "products/add";
	}

	@PostMapping("/save")
	public String save(Product product, RedirectAttributes redirectAttributes) {
		productService.save(product);
		redirectAttributes.addFlashAttribute("message", "บันทึกสินค้าเรียบร้อยแล้ว");
		return "redirect:/products";
	}

	@GetMapping("/edit/{id}")
	public String editForm(@PathVariable Long id, Model model) {
		model.addAttribute("product", productService.findById(id));
		return "products/edit";
	}

	@PostMapping("/update/{id}")
	public String update(@PathVariable Long id, Product product,
						 RedirectAttributes redirectAttributes) {
		productService.update(id, product);
		redirectAttributes.addFlashAttribute("message", "แก้ไขสินค้าเรียบร้อยแล้ว");
		return "redirect:/products";
	}

	@GetMapping("/delete/{id}")
	public String deleteForm(@PathVariable Long id, Model model) {
		model.addAttribute("product", productService.findById(id));
		return "products/delete";
	}

	@PostMapping("/delete/{id}")
	public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
		productService.delete(id);
		redirectAttributes.addFlashAttribute("message", "ลบสินค้าเรียบร้อยแล้ว");
		return "redirect:/products";
	}
}
