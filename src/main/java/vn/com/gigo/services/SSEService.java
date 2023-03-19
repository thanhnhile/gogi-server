package vn.com.gigo.services;


import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public interface SSEService {
	
	//SseEmitter subscribeNewOrderEvent(Long id);
	
	void sendEvent(SseEmitter emitter,String eventName,Object data);
	

	void sendNewOrders(Long storeId);
}
