package vn.com.gigo.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.com.gigo.dtos.DataResponse;
import vn.com.gigo.dtos.request.OrderRequestDto;
import vn.com.gigo.services.impl.OrderServiceImpl;
import vn.com.gigo.utils.OrderStatus;

@RestController
@RequestMapping("/orders")
public class OrderController {
	@Autowired
	OrderServiceImpl orderService;
	
	@GetMapping("/all")
	private DataResponse getAllOrders() {
		return new DataResponse(orderService.getAllOrders());
	}

	@GetMapping("detail/{id}")
	private DataResponse getOrderDetail(@PathVariable(value = "id") Long id) {
		return new DataResponse(orderService.getOrderDetail(id));
	}
	@GetMapping("/{id}")
	private DataResponse getOrder(@PathVariable(value = "id") Long id) {
		return new DataResponse(orderService.getOrder(id));
	}

	@GetMapping()
	private DataResponse getAllOrdersByStoreId(
			@RequestParam(value="store_id") Long storeId) {
		return new DataResponse(orderService.getAllOrdersByStoreId(storeId));
	}
	
	@GetMapping("/history")
	private DataResponse getAllOrdersByAccountUsername() {
		return new DataResponse(orderService.getAllOrdersByAccountUsername());
	}
	
	@PostMapping
	private DataResponse addOrder(@RequestBody OrderRequestDto orderInputDto) {
		orderService.addNewOrder(orderInputDto);
		DataResponse response = new DataResponse("Order is created");
		return response;
	}

	@DeleteMapping("/{id}")
	private DataResponse deleteOrder(@PathVariable(value = "id") Long id) {
		return new DataResponse(orderService.deleteOrder(id));
	}
	
	
	@PutMapping("/update/delivering/{id}")
	private DataResponse updateDelivering(@PathVariable(value="id") Long id) {
		return new DataResponse(orderService.updateOrderStatus(id, OrderStatus.DELIVERING.getValue()));
	}
	
	@PutMapping("/update/success/{id}")
	private DataResponse updateSucess(@PathVariable(value="id") Long id) {
		return new DataResponse(orderService.updateOrderStatus(id, OrderStatus.SUCCESS.getValue()));
	}
	
	@PutMapping("/update/cancel/{id}")
	private DataResponse updateCancel(@PathVariable(value="id") Long id) {
		return new DataResponse(orderService.updateOrderStatus(id, OrderStatus.CANCELED.getValue()));
	}
	
	//Update pay status
	@PutMapping("/pay/{id}")
	private DataResponse updatePayStatus(@PathVariable(value="id") Long id) {
		return new DataResponse(orderService.updatePayStatus(id));
	}

}
