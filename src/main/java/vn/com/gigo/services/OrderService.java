package vn.com.gigo.services;
import vn.com.gigo.dtos.OrderInputDto;

public interface OrderService {
	Object getOrder(Long id);
	
	Object addOrder (OrderInputDto orderInputDto);
	
	Object deleteOrder(Long id);
	
	//pending
	Object updateOrder(Long id,OrderInputDto orderInputDto);
	
	Object getAllOrdersByStoreId(Long storeId,int offSet, int limit);
}
