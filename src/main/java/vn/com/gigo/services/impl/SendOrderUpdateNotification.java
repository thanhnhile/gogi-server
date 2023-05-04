package vn.com.gigo.services.impl;

import org.springframework.beans.factory.annotation.Autowired;

import vn.com.gigo.mapstruct.OrderMapper;
import vn.com.gigo.notification.Notification;
import vn.com.gigo.notification.OrderNotificaion;
import vn.com.gigo.repositories.OrderRepository;

public class SendOrderUpdateNotification implements  Runnable{
	
	@Autowired
	private OrderNotificaion orderNotification;
	
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	OrderMapper orderMapper;
	
	private Long storeId;
	
	
	public SendOrderUpdateNotification(Long storeId) {
		super();
		this.storeId = storeId;
	}

	@Override
	public void run() {
		Object content = orderMapper.ordersToOrderDtos(orderRepository.getOrdersByStoreId(this.storeId));
		Notification notification = new Notification(storeId, content);
		orderNotification.setNotification(notification);
	}
}
