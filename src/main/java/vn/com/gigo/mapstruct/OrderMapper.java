package vn.com.gigo.mapstruct;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import vn.com.gigo.dtos.OrderDetailDto;
import vn.com.gigo.dtos.OrderDetailResponseDto;
import vn.com.gigo.dtos.OrderDto;
import vn.com.gigo.dtos.OrderInputDto;
import vn.com.gigo.entities.Order;
import vn.com.gigo.entities.OrderDetail;

@Mapper(componentModel = "spring")
public interface OrderMapper {
	// ---------------------------Order detail----------------------
	// OrderDetail
	@Mapping(source = "product.id", target = "product")
	OrderDetailDto detailToDetailDto(OrderDetail orderDetail);

	List<OrderDetail> detailsToDetailDtos(List<OrderDetail> list);

	// OrderDetailDto
	@Mapping(source = "product", target = "product.id")
	OrderDetail detailDtoToDetail(OrderDetailDto orderDetailDto);

	List<OrderDetail> detailDtosToDetail(List<OrderDetailDto> list);
	
	//OrderDetailResponseDto
	@Mapping(source = "product.id", target = "productId")
	@Mapping(source = "product.imgURL", target = "imgURL")
	@Mapping(source = "product.name", target = "productName")
	OrderDetailResponseDto detailToDetailResponseDto(OrderDetail orderDetail);

	List<OrderDetailResponseDto> detailsToDetailResponseDtos(List<OrderDetail> list);
	
	

	// --------------------------OrderInputDto------------------------------
	//Order
//	@Mapping(source="customer", target="customer.id")
	@Mapping(source = "customer.accountUsername", target = "customer.account.username")
	@Mapping(source = "accountUsername", target = "account.username")
	@Mapping(source="store", target="store.id")
	@Mapping(source="employee", target="employee.id")
	Order orderInputDtoToOrder(OrderInputDto orderInputDto);

	// --------------------------Order------------------------------
	// Order
	@Mapping(source="employee.name", target="employee")
	@Mapping(source = "account.username", target = "accountUsername")
	OrderDto orderToOrderDto(Order order);

	List<OrderDto> ordersToOrderDtos(List<Order> list);

	// OrderDto
	@Mapping(source="employee", target="employee.name")
	@Mapping(source = "accountUsername", target = "account.username")
	Order orderDtoToOrder(OrderDto orderDto);

	List<Order> orderDtosToOrders(List<OrderDto> list);
}