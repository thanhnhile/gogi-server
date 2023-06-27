package vn.com.gigo.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.com.gigo.dtos.response.CustomerDto;
import vn.com.gigo.entities.Account;
import vn.com.gigo.entities.Customer;
import vn.com.gigo.exception.ResourceNotFoundException;
import vn.com.gigo.mapstruct.CustomerMapper;
import vn.com.gigo.repositories.AccountRepository;
import vn.com.gigo.repositories.CustomerRepository;
import vn.com.gigo.services.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	private CustomerRepository customerRepo;

	@Autowired
	private AccountRepository accountRepo;

	@Autowired
	private CustomerMapper mapper;

	@Override
	public Object getCustomer(Long id) {
		return mapper.customerToCustomerDto(customerRepo.getReferenceById(id));
	}

	@Override
	public Object addCustomer(CustomerDto customerDto) {
		Customer customerToAdd = mapper.customerDtoToCustomer(customerDto);
		if (customerDto.getAccountUsername() == null) {
			customerToAdd.setAccount(null);
			return mapper.customerToCustomerDto(customerRepo.save(customerToAdd));
		} else {
			Account account = accountRepo.findOneByUsername(customerDto.getAccountUsername());
			if (account == null) {
				throw new ResourceNotFoundException(
						"Account with username " + customerDto.getAccountUsername() + " does not exist");
			}
			customerToAdd.setAccount(account);
			customerToAdd.setIsDefault(false);
			Customer newCustomer = customerRepo.save(customerToAdd);
			account.addCustomer(newCustomer);
			accountRepo.save(account);
			return mapper.customerToCustomerDto(newCustomer);
		}
	}

	@Override
	public Object updateCustomer(Long id, CustomerDto customerDto) {
		Optional<Customer> customerOptional = customerRepo.findById(id);
		if (customerOptional.isPresent()) {
			customerDto.setId(id);
			Account account = accountRepo.findOneByUsername(customerDto.getAccountUsername());
			Customer customer = mapper.customerDtoToCustomer(customerDto);
			customer.setAccount(account);
			Customer newCustomer = customerRepo.save(customer);
			return mapper.customerToCustomerDto(customerRepo.save(newCustomer));
		}
		throw new ResourceNotFoundException("Customer with id " + id + " does not exist");
	}

	@Override
	public Object deleteCustomer(Long id) {
		Optional<Customer> customerOptional = customerRepo.findById(id);
		if (customerOptional.isPresent()) {
			Customer customerToDelete = customerOptional.get();
			customerToDelete.getAccount().getCustomerList().remove(customerToDelete);
			customerRepo.delete(customerOptional.get());
			return "Deleted";
		}
		throw new ResourceNotFoundException("Customer with id " + id + " does not exist");
	}
	
	
	
}
