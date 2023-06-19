package vn.com.gigo.services;

import vn.com.gigo.dtos.request.OrderDetailRequestDto;

public interface OrderDetailService {
	Object getOrderDetail(Long id);
	
	Object addOrderDetail(OrderDetailRequestDto orderDetailDto);
}
