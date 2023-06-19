package vn.com.gigo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.com.gigo.dtos.DataResponse;
import vn.com.gigo.dtos.request.RateRequestDto;
import vn.com.gigo.services.impl.RateServiceImpl;

@RestController
@RequestMapping("/rates")
public class RateController {
	@Autowired
	private RateServiceImpl rateService;
	
	@GetMapping("/username")
	private DataResponse getRateByUsername() {
		return new DataResponse(rateService.getRatesByUsername());
	}
	
	@PostMapping
	private DataResponse add(@RequestBody RateRequestDto rateInputDto) {
		return new DataResponse(rateService.add(rateInputDto));
	}
	
	@GetMapping("/product/{id}")
	private DataResponse getRateByProductId(@PathVariable(value="id") Long id) {
		return new DataResponse(rateService.getRatesByProductId(id));
	}
	
	@GetMapping("/username/{id}")
	private DataResponse checkExisted(@PathVariable(value="id") Long id) {
		return new DataResponse(rateService.checkExisted(id));
	}
}
