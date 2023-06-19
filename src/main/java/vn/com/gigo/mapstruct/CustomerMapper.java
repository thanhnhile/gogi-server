package vn.com.gigo.mapstruct;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import vn.com.gigo.dtos.response.CustomerDto;
import vn.com.gigo.entities.Customer;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
	// ----------------------------Entity To DTO---------------------------
	@Mapping(source = "account.username", target = "accountUsername")
	CustomerDto customerToCustomerDto (Customer customer);
	
	List<CustomerDto> customersToCustomerDtos (List<Customer> list);

	// ---------------------------DTO To Entity----------------------
	@Mapping(source = "accountUsername", target = "account.username")
	Customer customerDtoToCustomer (CustomerDto customerDto);
}
