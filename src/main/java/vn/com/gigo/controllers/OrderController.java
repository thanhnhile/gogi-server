package vn.com.gigo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.com.gigo.dtos.DataResponse;
import vn.com.gigo.dtos.OrderInputDto;
import vn.com.gigo.services.impl.OrderServiceImpl;

@RestController
@RequestMapping("/orders")
public class OrderController {
	@Autowired
	OrderServiceImpl orderService;

	@GetMapping("/{id}")
	private DataResponse getOrder(@PathVariable(value = "id") Long id) {
		return new DataResponse(orderService.getOrder(id));
	}

	@GetMapping("/store/{storeId}")
	private DataResponse getAllOrdersByStoreId(@RequestParam(value = "limit", defaultValue = "9") int limit,
			@RequestParam(value = "offSet", defaultValue = "1") int offSet,
			@PathVariable(value = "storeId") Long storeId) {
		return new DataResponse(orderService.getAllOrdersByStoreId(storeId, offSet, limit));
	}

	@PostMapping
	private DataResponse addOrder(@RequestBody OrderInputDto orderInputDto) {
		return new DataResponse(orderService.addOrder(orderInputDto));
	}

	@DeleteMapping("/{id}")
	private DataResponse deleteOrder(@PathVariable(value = "id") Long id) {
		return new DataResponse(orderService.deleteOrder(id));
	}

}
