package vn.com.gigo.services.impl;

import org.springframework.beans.factory.annotation.Autowired;

import vn.com.gigo.dtos.OrderInputDto;

public class AddOrder implements Runnable{
	@Autowired
	OrderServiceImpl orderServices;
	
	private OrderInputDto orderInputDto;
	
	public AddOrder(OrderInputDto orderInputDto) {
		super();
		this.orderInputDto = orderInputDto;
	}

	@Override
	public void run() {
		orderServices.addOrder(orderInputDto);
	}

}
