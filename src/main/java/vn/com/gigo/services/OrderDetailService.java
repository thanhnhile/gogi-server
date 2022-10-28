package vn.com.gigo.services;

import vn.com.gigo.dtos.OrderDetailDto;

public interface OrderDetailService {
	Object getOrderDetail(Long id);
	
	Object addOrderDetail(OrderDetailDto orderDetailDto);
}
