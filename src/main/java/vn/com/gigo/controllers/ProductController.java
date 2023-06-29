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
import vn.com.gigo.dtos.request.ProductRequestDto;
import vn.com.gigo.services.impl.ProductServiceImpl;
import vn.com.gigo.utils.DefaultRequestParam;

@RestController
@RequestMapping("/products")
public class ProductController {
	@Autowired
	private ProductServiceImpl productService;
	
	@GetMapping()
	private DataResponse getAllProductsPagination(
			@RequestParam(value="limit", defaultValue=DefaultRequestParam.LIMIT) int limit,
			@RequestParam(value="offSet", defaultValue=DefaultRequestParam.OFFSET) int offSet, @RequestParam(value="sortBy",defaultValue="id") String sortBy,
			@RequestParam(value="asc", defaultValue="true") Boolean asc) {
		return new DataResponse(productService.getAllPagnation(offSet, limit, sortBy, asc));
	}
	
	@GetMapping("/{id}")
	private DataResponse getById(@PathVariable(value="id") Long id) {
		return new DataResponse(productService.getById(id));
	}
	
	@GetMapping("/category/{category_id}")
	private DataResponse getAllProductsByCatetoryId(
			@PathVariable(value="category_id") Long categoryId,
			@RequestParam(value="limit", defaultValue=DefaultRequestParam.LIMIT) int limit,
			@RequestParam(value="offSet",defaultValue=DefaultRequestParam.OFFSET) int offSet){
		return new DataResponse(productService.getAllProductsByCategoryId(categoryId, offSet, limit));
	}
	
	@GetMapping("/search")
	private DataResponse searchByName(@RequestParam(value = "s") String search,
			@RequestParam(value = "limit", defaultValue = DefaultRequestParam.LIMIT) int limit,
			@RequestParam(value = "offSet", defaultValue = DefaultRequestParam.OFFSET) int offSet) {
		return new DataResponse(productService.searchByName(search, offSet, limit));
	}
	
	@PostMapping()
	private DataResponse add(@RequestBody ProductRequestDto productDto) {
		return new DataResponse(productService.add(productDto));
	}
	
	@PutMapping("/{id}")
	private DataResponse update(@PathVariable(value="id") Long id,@RequestBody ProductRequestDto productDto) {
		return new DataResponse(productService.update(id, productDto));
	}
	
	@PutMapping("/update/status/{id}")
	private DataResponse updateStatus(@PathVariable(value="id") Long id) {
		return new DataResponse(productService.updateStatus(id));
	}
	
	@GetMapping("/all")
	private DataResponse getAllProducts() {
		return new DataResponse(productService.getAllProduct());
	}
	
	@GetMapping("/available")
	private DataResponse getAvailableProducts() {
		return new DataResponse(productService.getAvailable());
	}
	
	//app
	@GetMapping("/searchKeyword")
	private DataResponse search(@RequestParam(value = "k") String keyword) {
		return new DataResponse(productService.search(keyword));
	}
	@GetMapping("/categoryId/{category_id}")
	private DataResponse getProductsByCatetoryId(@PathVariable(value="category_id") Long categoryId){
		return new DataResponse(productService.getProductsByCategoryId(categoryId));
	}
	
	@GetMapping("/bestSeller")
	private DataResponse getBestSeller(){
		return new DataResponse(productService.getBestSeller());
	}
	
	@GetMapping("/forYou")
	private DataResponse getProductsForYou(){
		return new DataResponse(productService.getProductsForYou());
	}
	
	@GetMapping("/combo")
	private DataResponse getCombo(){
		return new DataResponse(productService.getCombo());
	}
}
