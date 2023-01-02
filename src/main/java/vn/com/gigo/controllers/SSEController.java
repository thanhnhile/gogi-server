package vn.com.gigo.controllers;


import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import vn.com.gigo.exception.ResourceNotFoundException;
import vn.com.gigo.services.impl.SSEServiceImpl;

@RestController
public class SSEController {
	public static final Map<Long, SseEmitter> storeEmitters = new HashMap<>();
	
	@Autowired
	SSEServiceImpl serviceImpl;
	

	@RequestMapping(value="/subscribe/{storeId}",consumes=MediaType.ALL_VALUE)
	private SseEmitter subscribeOrderEvent(@PathVariable(value = "storeId") Long storeId) {
		SseEmitter sseEmitter = new SseEmitter(Long.MAX_VALUE);
		storeEmitters.put(storeId, sseEmitter);
		
		serviceImpl.sendNewOrders(storeId);
		sseEmitter.onCompletion(() -> storeEmitters.remove(storeId));
		sseEmitter.onTimeout(() -> storeEmitters.remove(storeId));
		sseEmitter.onError((e) -> {
			e.printStackTrace();
			storeEmitters.remove(storeId);
		});
		return sseEmitter;
	}
	
////	@GetMapping("/send/{storeId}")
//	private void dispatch(@PathVariable(value="storeId")Long storeId) {
//		SseEmitter sseEmitter = emitters.get(storeId);
//		if(sseEmitter== null) {
//			throw new ResourceNotFoundException("Not found client");
//		}
//		try {
//			sseEmitter.send(SseEmitter.event().name("newOrders").data(orderServiceImpl.getAllOrdersByStoreId(storeId),MediaType.APPLICATION_JSON));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
	

}
