package vn.com.gigo.services.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import vn.com.gigo.controllers.SSEController;
import vn.com.gigo.exception.ResourceNotFoundException;
import vn.com.gigo.mapstruct.OrderMapper;
import vn.com.gigo.repositories.OrderRepository;
import vn.com.gigo.services.SSEService;
import vn.com.gigo.utils.OrderStatus;

@Service
public class SSEServiceImpl implements SSEService{
	@Autowired
	private OrderRepository orderRepo;
	
	@Autowired
	private OrderMapper orderMapper;
	
	

	@Override
	public void sendEvent(SseEmitter emitter, String eventName, Object data) {
		if(emitter == null) {
			return;
//			throw new ResourceNotFoundException("Not found client");
		}
		try {
			emitter.send(SseEmitter.event().name(eventName).data(data,MediaType.APPLICATION_JSON));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void sendNewOrders(Long storeId) {
		System.out.println(SSEController.storeEmitters.size());
		SseEmitter emitter = SSEController.storeEmitters.get(storeId);
		sendEvent(emitter, "newOrders", orderMapper.ordersToOrderDtos(orderRepo.findByStore_IdAndStatus(storeId,OrderStatus.IN_PROGRESS.getValue())));
		
	}

//	@Override
//	public SseEmitter subscribeNewOrderEvent(Long id) {
//		SseEmitter sseEmitter = new SseEmitter(Long.MAX_VALUE);
//		storeEmitters.put(id, sseEmitter);
//
//		sseEmitter.onCompletion(() -> storeEmitters.remove(id));
//		sseEmitter.onTimeout(() -> storeEmitters.remove(id));
//		sseEmitter.onError((e) -> {
//			e.printStackTrace();
//			storeEmitters.remove(id);
//		});
//		return sseEmitter;
//	}

	

}
