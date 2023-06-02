package vn.com.gigo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.com.gigo.dtos.DataResponse;
import vn.com.gigo.services.impl.StatisticsServiceImpl;

@RestController
@RequestMapping("/statistics")
public class StatisticsController {
	@Autowired
	StatisticsServiceImpl serviceImpl;
	
	@GetMapping("/admin")
	private DataResponse getAdminStatistics() {
		return new DataResponse(serviceImpl.getAdmindStatistics());
	}
}
