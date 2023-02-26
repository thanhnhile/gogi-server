package vn.com.gigo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.com.gigo.dtos.DataResponse;
import vn.com.gigo.dtos.RateInputDto;
import vn.com.gigo.services.impl.RateServiceImpl;

@RestController
@RequestMapping("/rates")
public class RateController {
	@Autowired
	private RateServiceImpl rateService;
	
	@PostMapping
	private DataResponse add(@RequestBody RateInputDto rateInputDto) {
		return new DataResponse(rateService.add(rateInputDto));
	}
}
