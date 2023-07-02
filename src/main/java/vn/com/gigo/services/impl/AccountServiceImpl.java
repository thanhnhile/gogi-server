package vn.com.gigo.services.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import vn.com.gigo.dtos.request.AccountDto;
import vn.com.gigo.dtos.request.AccountNoPassDto;
import vn.com.gigo.dtos.response.EmployeeDto;
import vn.com.gigo.entities.Account;
import vn.com.gigo.entities.Customer;
import vn.com.gigo.entities.Product;
import vn.com.gigo.entities.Role;
import vn.com.gigo.exception.AccountException;
import vn.com.gigo.exception.DuplicateValueInResourceException;
import vn.com.gigo.exception.ResourceNotFoundException;
import vn.com.gigo.mapstruct.AccountMapper;
import vn.com.gigo.mapstruct.CustomerMapper;
import vn.com.gigo.mapstruct.EmployeeMapper;
import vn.com.gigo.mapstruct.custom.ProductCustomMapper;
import vn.com.gigo.repositories.AccountRepository;
import vn.com.gigo.repositories.CustomerRepository;
import vn.com.gigo.repositories.EmployeeRepository;
import vn.com.gigo.repositories.ProductRepository;
import vn.com.gigo.repositories.RoleRepository;
import vn.com.gigo.security.SecurityUtils;
import vn.com.gigo.services.AccountService;
import vn.com.gigo.utils.RoleType;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AccountRepository accountRepo;

	@Autowired
	private ProductRepository productRepo;

	@Autowired
	private CustomerRepository customerRepo;

	@Autowired
	private AccountMapper accountMapper;
	
	@Autowired
	private ProductCustomMapper customMapper;

	@Autowired
	private CustomerMapper customerMapper;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private EmployeeRepository employeeRepo;

	@Autowired
	private EmployeeMapper employeeMapper;
	

	@Autowired
	public AccountServiceImpl(AccountMapper accountMapper, AccountRepository accountRepo) {
		this.accountMapper = accountMapper;
		this.accountRepo = accountRepo;
	}

	@Override
	public Object getAccount(Long id) {
		Account account = accountRepo.findOneById(id);
		if (account == null)
			throw new AccountException(id);

		Set<String> rolenames = new HashSet<String>();
		for (Role role : account.getRoles()) {
			rolenames.add(role.getName());
		}
		AccountNoPassDto accountNoPassDto = accountMapper.accountToAccountNoPassDto(account);
		accountNoPassDto.setRole(rolenames);
		return accountNoPassDto;
	}

	@Override
	public Object getAllAccount() {
		List<Account> accounts = new ArrayList<>();
		accountRepo.findAll().forEach(accounts::add);
		List<AccountNoPassDto> accountNoPassDto = accountMapper.accountsToAccountNoPassDtos(accounts);
		for (AccountNoPassDto accountDto : accountNoPassDto) {
			Set<String> rolenames = new HashSet<String>();
			for (Account account : accounts) {
				if (accountDto.getUsername().equals(account.getUsername())) {
					for (Role role : account.getRoles()) {
						rolenames.add(role.getName());
					}
				}

			}
			accountDto.setRole(rolenames);
		}
		return accountNoPassDto;
	}

	@Override
	public Object addAccount(AccountDto accountDto) {
		if (checkExistedAccount(accountDto.getUsername())) {
			throw new DuplicateValueInResourceException("Số điện thoại đã tồn tại");
		}
		if(checkExistedEmail(accountDto.getEmail())) {
			throw new DuplicateValueInResourceException("Email đã tồn tại");
		}
		Account account = accountMapper.accountDtoToAccount(accountDto);
		String rawPassword = account.getPassword();
		String encodedPassword = passwordEncoder.encode(rawPassword);// thuat toan ma hoa BCrypt
		account.setPassword(encodedPassword);
		account.setCustomerList(null);
		Role roleUser = roleRepository.findOneById(RoleType.ROLE_USER.getValue());
		account.getRoles().add(roleUser);
		accountRepo.save(account);
		//Add voucher for new account
		//voucherService.addVoucherToAccount(accountDto.getUsername(), Contanst.VOUCHER_FOR_NEW_ACCOUNT_ID);
		return accountDto;
	}

	private Boolean checkExistedAccount(String username) {
		Account account = accountRepo.findOneByUsername(username);
		if (account != null) {
			return true;
		}
		return false;
	}
	
	private Boolean checkExistedEmail(String email) {
		Account account = accountRepo.findByEmail(email);
		if (account != null) {
			return true;
		}
		return false;
	}
	
	

	@Override
	public void deleteAccount(Long id) {
		accountRepo.delete(accountRepo.findOneById(id));

	}

	@Override
	public Object updateAccount(Long id, AccountDto accountDto) {
		Account accountNew = accountMapper.accountDtoToAccount(accountDto);
		Account accountOld = accountRepo.findOneById(id);
		String rawPassword = accountNew.getPassword();
		String encodedPassword = passwordEncoder.encode(rawPassword);
		accountOld.setPassword(encodedPassword);
		return accountRepo.save(accountOld);
	}

	@Override
	public Object getCustomerInfoByUserName() {
		String username = SecurityUtils.getLoggedUsername();
		List<Customer> listCustomerOfAccount = customerRepo.findAllByAccount_Username(username);
		return customerMapper.customersToCustomerDtos(listCustomerOfAccount);
	}

	@Override
	public Object addRoleEmployee(String username) {
		// TODO Auto-generated method stub
		Account account = accountRepo.findOneByUsername(username);
		if (account != null) {
			Role roleEmployee = roleRepository.findOneById(RoleType.ROLE_EMPLOYEE.getValue());
			account.getRoles().add(roleEmployee);
			return accountMapper.accountToAccountDto(accountRepo.save(account));
		} else
			throw new ResourceNotFoundException("Account with username " + username + " does not exist");
	}

	@Override
	public Object removeRoleEmployee(Long id) {
		EmployeeDto employee = employeeMapper.employeeToEmployeeDto(employeeRepo.findOneById(id));
		Account account = accountRepo.findOneByUsername(employee.getAccount());
		if (account != null) {
			Role roleEmployee = roleRepository.findOneById(RoleType.ROLE_EMPLOYEE.getValue());
			account.getRoles().remove(roleEmployee);
			return accountMapper.accountToAccountDto(accountRepo.save(account));
		} else
			throw new ResourceNotFoundException("Account with id " + id + " does not exist");
	}

	@Override
	public Object getCustomerInfoDefault() {
		String username = SecurityUtils.getLoggedUsername();
		return customerMapper.customerToCustomerDto(customerRepo.getCustomerInfoDefaultByUsername(username));
	}

	@Override
	public Object updateDefaultCustomerInfo(Long id) {
		String loggedUsername = SecurityUtils.getLoggedUsername();
		Optional<Customer> customerOptional = customerRepo.findById(id);

		if (customerOptional.isPresent()) {
			Customer customerToUpdate = customerOptional.get();
			List<Customer> listCustomer = customerRepo.findAllByAccount_Username(loggedUsername);
			if (listCustomer.size() <= 0) {
				customerToUpdate.setIsDefault(true);
				return customerMapper.customerToCustomerDto(customerRepo.save(customerToUpdate));
			} else {
				if (listCustomer.contains(customerToUpdate)) {
					Customer oldDefault = customerRepo.getCustomerInfoDefaultByUsername(loggedUsername);
					if(oldDefault != null) {
						oldDefault.setIsDefault(false);
						customerRepo.save(oldDefault);
					}
					customerToUpdate.setIsDefault(true);
					return customerMapper.customerToCustomerDto(customerRepo.save(customerToUpdate));
				} else
					throw new ResourceNotFoundException("Customer with id " + id
							+ " does not exist in list customer address of account " + loggedUsername);

			}

		}
		throw new ResourceNotFoundException("Customer with id " + id + " does not exist");
	}

	@Override
	public String updateToken(String email) {
		Account account = accountRepo.findByEmail(email);
		if (account != null) {
			account.setToken(UUID.randomUUID().toString());
			// return accountMapper.accountToAccountDto(accountRepo.save(account));
			Account newAccount = accountRepo.save(account);
			return newAccount.getToken();
		}
		return null;
	}

	@Override
	public Object getByToken(String token) {
		Account account = accountRepo.findByToken(token);
		return accountMapper.accountToAccountDto(account);
	}

	@Override
	public Object resetPassword(String token, AccountDto accountDto) {
		Account accountNew = accountMapper.accountDtoToAccount(accountDto);
		Account accountOld = accountRepo.findByToken(token);
		String rawPassword = accountNew.getPassword();
		String encodedPassword = passwordEncoder.encode(rawPassword);
		accountOld.setPassword(encodedPassword);
		return accountMapper.accountToAccountDto(accountRepo.save(accountOld));
	}

	@Override
	public Object likeProduct(Long id) {
		String username = SecurityUtils.getLoggedUsername();
		Account account = accountRepo.findOneByUsername(username);
		if (account != null) {
			Product product = productRepo.findOneById(id);
			account.getProducts().add(product);
			return accountMapper.accountToAccountDto(accountRepo.save(account));
		}
		else throw new ResourceNotFoundException("Account does not exist");
	}

	@Override
	public Object unlikeProduct(Long id) {
		String username = SecurityUtils.getLoggedUsername();
		Account account = accountRepo.findOneByUsername(username);
		if (account != null) {
			Product product = productRepo.findOneById(id);
			account.getProducts().remove(product);
			return accountMapper.accountToAccountDto(accountRepo.save(account));
		}
		else throw new ResourceNotFoundException("Account does not exist");
	}

	@Override
	public Object getAllProductsLiked() {
		String username = SecurityUtils.getLoggedUsername();
		Account account = accountRepo.findOneByUsername(username);
		List<Product> listProductsLiked = productRepo.getAllProductsLiked(account.getId());
		return customMapper.mapToProductDtos(listProductsLiked);
	}

	@Override
	public Object getAvailableAccount() {
		return accountMapper.accountsToAccountDtos(accountRepo.getAvailableAccount());
	}

}
