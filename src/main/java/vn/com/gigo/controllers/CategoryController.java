package vn.com.gigo.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.com.gigo.dtos.DataResponse;
import vn.com.gigo.dtos.response.CategoryDto;
import vn.com.gigo.services.impl.CategoryServiceImpl;

@RestController
@RequestMapping("/categories")
public class CategoryController {
	
	@Autowired
	private CategoryServiceImpl categoryService;
	
	@GetMapping
	private DataResponse getAll() {
		return new DataResponse(categoryService.getAll());
	}
	
	@GetMapping("/available")
	private DataResponse getAvailable() {
		return new DataResponse(categoryService.getAvailable());
	}
	
	@GetMapping("/{id}")
	private DataResponse getById(@PathVariable(value="id") Long id) {
		return new DataResponse(categoryService.getById(id));
	}
	
	@PostMapping()
	private DataResponse add(@RequestBody CategoryDto categoryDto) {
		return new DataResponse(categoryService.add(categoryDto));
	}
	
	@PutMapping("/{id}")
	private DataResponse update(@PathVariable(value="id") Long id, @RequestBody CategoryDto categoryDto) {
		return new DataResponse(categoryService.update(id, categoryDto)); 
	}
	
	@PutMapping("/update/status/{id}")
	private DataResponse updateStatus(@PathVariable(value="id") Long id) {
		return new DataResponse(categoryService.updateStatus(id)); 
	}
}
