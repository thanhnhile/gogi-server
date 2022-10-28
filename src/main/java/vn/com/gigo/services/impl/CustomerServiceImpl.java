package vn.com.gigo.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.com.gigo.dtos.CustomerDto;
import vn.com.gigo.entities.Customer;
import vn.com.gigo.entities.District;
import vn.com.gigo.mapstruct.CustomerMapper;
import vn.com.gigo.repositories.CustomerRepository;
import vn.com.gigo.repositories.DistrictRepository;
import vn.com.gigo.services.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	private CustomerRepository customerRepo;
	
	@Autowired
	private DistrictRepository districtRepo;
	
	@Autowired
	private CustomerMapper mapper;

	@Override
	public Object getCustomer(Long id) {
		return mapper.customerToCustomerDto(customerRepo.getReferenceById(id));
	}

	@Override
	public Object addCustomer(CustomerDto customerDto) {
		Customer customerToAdd = mapper.customerDtoToCustomer(customerDto);
		District district = districtRepo.findOneById(customerDto.getDistrict().getId());
		customerToAdd.setDistrict(district);
		return mapper.customerToCustomerDto(customerRepo.save(customerToAdd));
	}

	@Override
	public Object updateCustomer(Long id, CustomerDto customerDto) {
		Optional<Customer> customerOptional = customerRepo.findById(id);
		if(customerOptional.isPresent()) {
			customerDto.setId(id);
			Customer customer = mapper.customerDtoToCustomer(customerDto);
			return mapper.customerToCustomerDto(customerRepo.save(customer));
		}
		return "Not found customer with id"+id;
	}

	@Override
	public Object deleteCustomer(Long id) {
		Optional<Customer> customerOptional = customerRepo.findById(id);
		if(customerOptional.isPresent()) {
			customerRepo.delete(customerOptional.get());
			return "Deleted";
		}
		return "Not found customer with id "+id;
	}

}
