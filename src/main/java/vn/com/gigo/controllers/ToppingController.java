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
import vn.com.gigo.dtos.response.ToppingDto;
import vn.com.gigo.services.impl.ToppingServiceImpl;

@RestController
@RequestMapping("/toppings")
public class ToppingController {
	
	@Autowired
	private ToppingServiceImpl toppingService;
	
	@GetMapping
	private DataResponse getAll() {
		return new DataResponse(toppingService.getAll());
	}
	
	@GetMapping("/available")
	private DataResponse getAvailable() {
		return new DataResponse(toppingService.getAvailable());
	}
	
	@GetMapping("/{id}")
	private DataResponse getById(@PathVariable(value="id") Long id) {
		return new DataResponse(toppingService.getById(id));
	}
	
	@PostMapping("/add")
	private DataResponse add(@RequestBody ToppingDto toppingDto) {
		return new DataResponse(toppingService.add(toppingDto));
	}
	
	@PutMapping("/update/{id}")
	private DataResponse update(@PathVariable(value="id") Long id, @RequestBody ToppingDto toppingDto) {
		return new DataResponse(toppingService.update(id, toppingDto)); 
	}
	
	@PutMapping("/delete/{id}")
	private DataResponse updateStatus(@PathVariable(value="id") Long id) {
		return new DataResponse(toppingService.updateStatus(id)); 
	}
}
