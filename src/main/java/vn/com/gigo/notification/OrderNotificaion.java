package vn.com.gigo.notification;

import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import vn.com.gigo.exception.ResourceNotFoundException;

@Service
public class OrderNotificaion implements Sender {
	public static final Map<Long, Map<Long,SseEmitter>> emitters = new HashMap<>();
	private final String EVENT_NAME = "LIST_ORDERS_UPDATE";
	private Notification notification;

	public Notification getNotification() {
		return notification;
	}

	public void setNotification(Notification notification) {
		this.notification = notification;
		System.out.println("RECEIVER ID: "+notification.getReceiverId());
		sendNotification();
	}

	public static Map<Long, Map<Long, SseEmitter>> getEmitters() {
		return emitters;
	}
	
	public static  Map<Long, SseEmitter> getEmittersByStoreId(Long storeId){
		return emitters.get(storeId);
	}

	public SseEmitter addNewEmitter(Long storeId,Long employeeId) {
		if(emitters.get(storeId).get(employeeId) != null) {
			throw new DuplicateKeyException("Client has existed");
		}
		Map<Long, SseEmitter> storeEmitters = emitters.get(storeId);
		SseEmitter sseEmitter = new SseEmitter(Long.MAX_VALUE);
		if(storeEmitters != null) {
			storeEmitters.put(employeeId, sseEmitter);
		}
		else {
			Map<Long, SseEmitter> newStoreEmitters = new HashMap<>();
			newStoreEmitters.put(employeeId, sseEmitter);
			emitters.put(storeId, newStoreEmitters);
		}
		sendEvent(sseEmitter, EVENT_NAME, "CONNECTED");
		sseEmitter.onCompletion(() -> emitters.get(storeId).remove(employeeId));
		sseEmitter.onTimeout(() ->  emitters.get(storeId).remove(employeeId));
		sseEmitter.onError((e) -> {
			e.printStackTrace();
			emitters.get(storeId).remove(employeeId);
		});
		return sseEmitter;
	}

	public void sendEvent(SseEmitter emitter, String eventName, Object data) {
		if (emitter == null) {
			throw new ResourceNotFoundException("Not found emmitter");
		}
		try {
			emitter.send(SseEmitter.event().name(eventName).data(data, MediaType.APPLICATION_JSON));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void sendNotification() {
		Map<Long, SseEmitter> receivers = emitters.get(notification.getReceiverId());
		receivers.forEach((key,value)->{
			sendEvent(value, EVENT_NAME, notification.getContent());
		});
	}
}
