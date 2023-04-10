package vn.com.gigo.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.com.gigo.dtos.CustomerDto;
import vn.com.gigo.dtos.OrderDetailDto;
import vn.com.gigo.dtos.OrderInputDto;
import vn.com.gigo.entities.Account;
import vn.com.gigo.entities.Customer;
import vn.com.gigo.entities.Employee;
import vn.com.gigo.entities.Order;
import vn.com.gigo.entities.OrderDetail;
import vn.com.gigo.entities.Voucher;
import vn.com.gigo.exception.ResourceNotFoundException;
import vn.com.gigo.mapstruct.CustomerMapper;
import vn.com.gigo.mapstruct.OrderMapper;
import vn.com.gigo.notification.Notification;
import vn.com.gigo.notification.OrderNotificaion;
import vn.com.gigo.repositories.AccountRepository;
import vn.com.gigo.repositories.CustomerRepository;
import vn.com.gigo.repositories.EmployeeRepository;
import vn.com.gigo.repositories.OrderDetailRepository;
import vn.com.gigo.repositories.OrderRepository;
import vn.com.gigo.repositories.StoreRepository;
import vn.com.gigo.repositories.VoucherRepository;
import vn.com.gigo.security.SecurityUtils;
import vn.com.gigo.services.OrderService;
import vn.com.gigo.utils.OrderStatus;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepo;

	@Autowired
	private OrderDetailRepository orderDetailRepo;

	@Autowired
	private AccountRepository accountRepo;

	@Autowired
	private CustomerServiceImpl customerImpl;
	
	@Autowired
	private CustomerRepository customerRepo;

	@Autowired
	private EmployeeRepository employeeRepo;

	@Autowired
	private StoreRepository storeRepo;
	
	@Autowired
	private VoucherRepository voucherRepo;

	@Autowired
	private OrderMapper mapper;

	@Autowired
	private CustomerMapper customerMapper;
	
	@Autowired
	private OrderNotificaion orderNotification;

	@Override
	public Object getOrder(Long id) {
		return mapper.orderToOrderDto(orderRepo.getReferenceById(id));
	}

	@Override
	public Object addOrder(OrderInputDto orderInputDto) {
		Order orderToAdd = mapper.orderInputDtoToOrder(orderInputDto);
		orderToAdd.setStore(storeRepo.getReferenceById(orderInputDto.getStore()));
		CustomerDto customerDto = orderInputDto.getCustomer();
		Account account = null;
		if(customerDto != null) {
			Customer customer;
			if(customerDto.getId() != null) {
				customer = customerRepo.findById(customerDto.getId()).orElse(null);
			}
			else {
				customer = customerMapper
						.customerDtoToCustomer((CustomerDto) customerImpl.addCustomer(customerDto));
			}
			orderToAdd.setCustomer(customer);
		}
		if (orderToAdd.getPay() == null) {
			orderToAdd.setPay(false);
		}
		if (orderInputDto.getAccountUsername() != null) {
			account = accountRepo.findOneByUsername(orderInputDto.getAccountUsername());
			orderToAdd.setAccount(account);
		}
		else orderToAdd.setAccount(null);
		if(orderInputDto.getVoucher() != null ) {
			Voucher voucher = voucherRepo.findOneById(orderInputDto.getVoucher());
			if(account != null) {
				account.getVouchers().add(voucher);
				
			}
			orderToAdd.setVoucher(voucher);
		}
		else orderToAdd.setVoucher(null);

		// set order status
		orderToAdd.setStatus(OrderStatus.IN_PROGRESS.getValue());
		// save order detail
		List<OrderDetailDto> detailDtos = orderInputDto.getDetailList();
		// save order
		Order newOrder = orderRepo.save(orderToAdd);
		List<OrderDetail> details = new ArrayList<OrderDetail>();
		for (OrderDetailDto detailDto : detailDtos) {
			OrderDetail orderDetail = mapper.detailDtoToDetail(detailDto);
			orderDetail.setOrder(newOrder);
			orderDetailRepo.save(orderDetail);
			details.add(orderDetail);
		}
		//sendNotification(orderToAdd.getStore().getId());
		return mapper.orderToOrderDto(newOrder);
	}
	
//	private void sendNotification(Long storeId) {
//		Object content = getAllOrdersByStoreId(storeId);
//		Notification notification = new Notification(storeId, content);
//		orderNotification.setNotification(notification);
//	}

	@Override
	public Object deleteOrder(Long id) {
		Optional<Order> orderOptional = orderRepo.findById(id);
		if (orderOptional.isPresent()) {
			orderRepo.deleteById(id);
			return "Deleted";
		}
		throw new ResourceNotFoundException("Not found order with id " + id);
	}

	@Override
	public Object getAllOrdersByStoreId(Long storeId) {
		List<Order> list = orderRepo.getOrdersByStoreId(storeId);
		return mapper.ordersToOrderDtos(list);
	}

	@Override
	public Object updateOrderStatus(Long id, int status) {
		String loggedUser = SecurityUtils.getLoggedUsername();
		Employee employee = employeeRepo.findByAccount_Username(loggedUser);
		Optional<Order> orderOptional = orderRepo.findById(id);
		if (orderOptional.isPresent()) {
			Order orderToUpdate = orderOptional.get();
			switch (status) {
			case 1:
				orderToUpdate.setStatus(status);
				orderToUpdate.setEmployee(employee);
				break;
			case 2:
				orderToUpdate.setStatus(status);
				orderToUpdate.setPay(true);
				break;
			case 3:
				orderToUpdate.setStatus(status);
				orderToUpdate.setEmployee(employee);
				//sseService.sendNewOrders(orderToUpdate.getStore().getId());
				break;
			default:throw new ResourceNotFoundException("Not found order status id " + id);
			}
			//sendNotification(orderToUpdate.getStore().getId());
			return mapper.orderToOrderDto(orderRepo.save(orderToUpdate));
		} else
			throw new ResourceNotFoundException("Not found order with id " + id);
	}

	@Override
	public Object updatePayStatus(Long id) {
		Optional<Order> orderOptional = orderRepo.findById(id);
		if (orderOptional.isPresent()) {
			Order orderToUpdate = orderOptional.get();
			orderToUpdate.setPay(true);
			return mapper.orderToOrderDto(orderRepo.save(orderToUpdate));
		} else
			throw new ResourceNotFoundException("Not found order with id " + id);
	}

	@Override
	public Object updateOrderDetail(Long id, List<OrderDetailDto> detailDtos) {
		Optional<Order> orderOptional = orderRepo.findById(id);
		if (orderOptional.isPresent()) {
			Order orderToUpdate = orderOptional.get();
			Double total = 0.0;
			List<OrderDetail> newDetails = new ArrayList<OrderDetail>();
			for (OrderDetailDto detailDto : detailDtos) {
				OrderDetail orderDetail = mapper.detailDtoToDetail(detailDto);
				orderDetail.setOrder(orderToUpdate);
				orderDetailRepo.save(orderDetail);
				newDetails.add(orderDetail);
				total += detailDto.getPrice() * detailDto.getQuantity();
			}
			orderToUpdate.setDetailList(newDetails);
			orderToUpdate.setTotal(total);
			return mapper.orderToOrderDto(orderRepo.save(orderToUpdate));
		} else
			throw new ResourceNotFoundException("Not found order with id " + id);
	}

	@Override
	public Object getAllOrdersByAccountUsername(String username) {
		return mapper.ordersToOrderDtos(orderRepo.getOrdersByAccountUsername(username));
	}

	@Override
	public Object getAllOrders() {
		return mapper.ordersToOrderDtos(orderRepo.findAll());
	}

}
