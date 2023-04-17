package vn.com.gigo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import vn.com.gigo.notification.OrderNotificaion;

@RestController

public class OrderNotificationSSEController {
	@Autowired
	private OrderNotificaion orderNotificaion;
	
	@RequestMapping(value="/subscribe/stores/{storeId}/employees/{employeeId}",consumes=MediaType.ALL_VALUE)
	private SseEmitter subscribeOrderEvent(@PathVariable(value = "storeId") Long storeId,
			@PathVariable(value = "employeeId") Long employeeId) {
		return orderNotificaion.addNewEmitter(storeId,employeeId);
	}
}
