package vn.com.gigo.services;
import java.util.List;

import vn.com.gigo.dtos.OrderDetailDto;
import vn.com.gigo.dtos.OrderInputDto;

public interface OrderService {
	Object getOrder(Long id);
	
	Object addOrder (OrderInputDto orderInputDto);
	
	Object deleteOrder(Long id);
	
	Object getAllOrdersByStoreId(Long storeId,int offSet, int limit);
	
	Object updateOrderStatus(Long id,int status);
	
	Object updatePayStatus(Long id);
	
	Object updateOrderDetail(Long id, List<OrderDetailDto> orderDetailDtos);
}
