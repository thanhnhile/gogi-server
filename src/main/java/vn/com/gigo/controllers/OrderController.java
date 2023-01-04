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
import vn.com.gigo.dtos.OrderInputDto;
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

	@GetMapping("/{id}")
	private DataResponse getOrder(@PathVariable(value = "id") Long id) {
		return new DataResponse(orderService.getOrder(id));
	}

	@GetMapping()
	private DataResponse getAllOrdersByStoreId(
			@RequestParam(value="store_id") Long storeId) {
		return new DataResponse(orderService.getAllOrdersByStoreId(storeId));
	}
	
	@GetMapping("/account/{username}")
	private DataResponse getAllOrdersByAccountUsername(@PathVariable (value="username") String username) {
		return new DataResponse(orderService.getAllOrdersByAccountUsername(username));
	}
	

	@PostMapping
	private DataResponse addOrder(@RequestBody OrderInputDto orderInputDto) {
		return new DataResponse(orderService.addOrder(orderInputDto));
	}

	@DeleteMapping("/{id}")
	private DataResponse deleteOrder(@PathVariable(value = "id") Long id) {
		return new DataResponse(orderService.deleteOrder(id));
	}
	
//	@PutMapping("/update/{id}")
//	private DataResponse updateOrder(@PathVariable(value="id") Long id, @RequestBody List<OrderDetailDto> detailDtos) {
//		return new DataResponse(orderService.updateOrderDetail(id, detailDtos));
//	}
	//Update order status
	
//	@PutMapping("/update/in_progress/{id}")
//	private DataResponse updateInprogress(@PathVariable(value="id") Long id) {
//		return new DataResponse(orderService.updateOrderStatus(id, OrderStatus.IN_PROGRESS.getValue()));
//	}
	
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
