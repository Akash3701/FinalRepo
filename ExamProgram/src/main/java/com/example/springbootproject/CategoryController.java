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
@RequestMapping("/api/category")
public class CategoryController {

	@Autowired
	private CategoryRepo categoryRepo;
	
	@PostMapping("/save")
	public Category savecategory(@RequestBody Category category) {
		return categoryRepo.save(category);
	}
	
	@PutMapping("/{id}")
	public Category updatecategory(@PathVariable Integer id, @RequestBody Category category) {
		category.setId(id);
		return categoryRepo.save(category);
	}
	
	@DeleteMapping("/{id}")
	public void deletecategory(@PathVariable Integer id) {
		categoryRepo.deleteById(id);
	}
	
	@GetMapping("/{id}")
	public Optional<Category> getById(@PathVariable Integer id) {
		return categoryRepo.findById(id);
	}
	
	@GetMapping("/page")
	public Page<Category> getByPage(@RequestParam int page, @RequestParam int size) {
		Pageable pageable = PageRequest.of(page, size);
		return categoryRepo.findAll(pageable);
	}
	
	@GetMapping("/sort")
	public List<Category> getBySort(@RequestParam String sortby) {
		return categoryRepo.findAll(Sort.by(sortby));
	}
	
}
