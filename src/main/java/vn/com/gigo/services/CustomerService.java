package vn.com.gigo.services;

import vn.com.gigo.dtos.response.CustomerDto;

public interface CustomerService {
	Object getCustomer(Long id);
	
	Object addCustomer(CustomerDto customerDto);
	
	Object updateCustomer(Long id,CustomerDto customerDto);
	
	Object deleteCustomer(Long id);
}
