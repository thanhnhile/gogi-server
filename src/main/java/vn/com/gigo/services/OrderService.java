package vn.com.gigo.services;
import java.util.List;

import vn.com.gigo.dtos.request.OrderDetailRequestDto;
import vn.com.gigo.dtos.request.OrderRequestDto;

public interface OrderService {
	Object getAllOrders();
	
	Object getOrder(Long id);
	
	Object addOrder (OrderRequestDto orderInputDto);
	
	Object deleteOrder(Long id);
	
	Object getAllOrdersByStoreId(Long storeId);
	
	Object getAllOrdersByAccountUsername();
	
	Object updateOrderStatus(Long id,int status);
	
	Object updatePayStatus(Long id);
	
	Object updateOrderDetail(Long id, List<OrderDetailRequestDto> orderDetailDtos);
}
