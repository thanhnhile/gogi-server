package vn.com.gigo.mapstruct;

import java.util.List;

import org.mapstruct.Mapper;

import vn.com.gigo.dtos.CustomerDto;
import vn.com.gigo.entities.Customer;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
	// ----------------------------Entity To DTO---------------------------
	CustomerDto customerToCustomerDto (Customer customer);
	
	List<CustomerDto> customersToCustomerDtos (List<Customer> list);

	// ---------------------------DTO To Entity----------------------
	Customer customerDtoToCustomer (CustomerDto customerDto);
}
