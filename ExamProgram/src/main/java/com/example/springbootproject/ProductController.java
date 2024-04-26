package com.example.springbootproject;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/product")
public class ProductController {

	@Autowired
	private ProductRepo productRepo;
	
	@PostMapping("/save")
	public Product saveProduct(@RequestBody Product product) {
		return productRepo.save(product);
	}
	
	@PutMapping("/{id}")
	public Product updateProduct(@PathVariable Integer id, @RequestBody Product product) {
		product.setId(id);
		return productRepo.save(product);
	}
	
	@DeleteMapping("/{id}")
	public void deleteProduct(@PathVariable Integer id) {
		productRepo.deleteById(id);
	}
	
	@GetMapping("/{id}")
	public Optional<Product> getById(@PathVariable Integer id) {
		return productRepo.findById(id);
	}
	
	@GetMapping("/page")
	public Page<Product> getByPage(@RequestParam int page, @RequestParam int size) {
		Pageable pageable = PageRequest.of(page, size);
		return productRepo.findAll(pageable);
	}
	
	@GetMapping("/sort")
	public List<Product> getBySort(@RequestParam String sortby) {
		return productRepo.findAll(Sort.by(sortby));
	}
	
}
