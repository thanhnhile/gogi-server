package vn.com.gigo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.com.gigo.dtos.DataResponse;
import vn.com.gigo.dtos.ProductDto;
import vn.com.gigo.services.impl.ProductServiceImpl;

@RestController
@RequestMapping("/products")
public class ProductController {
	@Autowired
	private ProductServiceImpl productService;
	
	@GetMapping
	private DataResponse getAllProductsPagination(
			@RequestParam(value="limit", defaultValue="9") int limit,
			@RequestParam(value="offSet", defaultValue="1") int offSet, @RequestParam(value="sortBy",defaultValue="id") String sortBy,
			@RequestParam(value="asc", defaultValue="true") Boolean asc) {
		return new DataResponse(productService.getAllPagnation(offSet, limit, sortBy, asc));
	}
	
	@GetMapping("/{id}")
	private DataResponse getById(@PathVariable(value="id") Long id) {
		return new DataResponse(productService.getById(id));
	}
	
	@PostMapping
	private DataResponse add(@RequestBody ProductDto productDto) {
		return new DataResponse(productService.add(productDto));
	}
	
	@PutMapping("/{id}")
	private DataResponse update(@PathVariable(value="id") Long id,@RequestBody ProductDto productDto) {
		return new DataResponse(productService.update(id, productDto));
	}
}
