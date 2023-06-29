package vn.com.gigo.mapstruct;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import vn.com.gigo.dtos.request.OrderDetailRequestDto;
import vn.com.gigo.dtos.request.OrderRequestDto;
import vn.com.gigo.dtos.response.OrderDetailDto;
import vn.com.gigo.dtos.response.OrderDto;
import vn.com.gigo.entities.Order;
import vn.com.gigo.entities.OrderDetail;

@Mapper(componentModel = "spring")
public interface OrderMapper {
	/** OrderDetail */
	// ----------------------------Entity To DTO---------------------------
	// OrderDetailDto
	@Mapping(source = "product.id", target = "productId")
	@Mapping(source = "product.imgURL", target = "imgURL")
	@Mapping(source = "product.name", target = "productName")
	OrderDetailDto detailToDetailResponseDto(OrderDetail orderDetail);

	List<OrderDetailDto> detailsToDetailResponseDtos(List<OrderDetail> list);

	// ---------------------------DTO To Entity----------------------------
	// OrderDetailRequestDto
	@Mapping(source = "product", target = "product.id")
	OrderDetail detailDtoToDetail(OrderDetailRequestDto orderDetailDto);

	List<OrderDetail> detailDtosToDetail(List<OrderDetailRequestDto> list);

	/**Order*/
	// ----------------------------Entity To DTO---------------------------
	@Mapping(source = "employee.name", target = "employee")
	@Mapping(source = "account.username", target = "accountUsername")
	OrderDto orderToOrderDto(Order order);

	List<OrderDto> ordersToOrderDtos(List<Order> list);
	
	// ---------------------------DTO To Entity----------------------------
	//OrderRequestDto
	@Mapping(source = "customer.accountUsername", target = "customer.account.username")
	@Mapping(source = "accountUsername", target = "account.username")
	@Mapping(source = "store", target = "store.id")
	@Mapping(source = "voucher", target = "voucher.id")
	Order orderInputDtoToOrder(OrderRequestDto orderInputDto);
	
}