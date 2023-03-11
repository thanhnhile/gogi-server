package vn.com.gigo.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.com.gigo.dtos.CustomerDto;
import vn.com.gigo.dtos.DataResponse;
import vn.com.gigo.services.impl.CustomerServiceImpl;

@RestController
@RequestMapping("/customers")
public class CustomerController {
	@Autowired
	private CustomerServiceImpl customerService;
	
	@GetMapping("/{id}")
	private DataResponse getCustomer(@PathVariable(value="id") Long id) {
		return new DataResponse(customerService.getCustomer(id));
	}
	
	@PostMapping()
	private DataResponse addCustomer (@RequestBody CustomerDto customerDto) {
		return new DataResponse(customerService.addCustomer(customerDto));
	}
	
	@PutMapping("/{id}")
	private DataResponse updateCustomer(@PathVariable(value="id") Long id, 
			@RequestBody CustomerDto customerDto) {
		return new DataResponse(customerService.updateCustomer(id, customerDto));
		
	}
	@PutMapping("account/{id}")
	private DataResponse updateDefaultCustomerInfo(@PathVariable(value="id") Long id) {
		return new DataResponse(customerService.updateDefaultCustomerInfo(id));
	}
	@DeleteMapping("/{id}")
	private DataResponse deleteCustomer (@PathVariable(value="id") Long id) {
		return new DataResponse(customerService.deleteCustomer(id));
	}
}
