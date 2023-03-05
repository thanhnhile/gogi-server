package vn.com.gigo.services;

import vn.com.gigo.dtos.AccountDto;

public interface AccountService {
	Object getAccount(Long id);
	
	Object getCustomerInfoByUserName(String username);

	Object getAllAccount();

	Object addAccount(AccountDto accountDto);

	void deleteAccount(Long id);

	Object updateAccount(Long id, AccountDto accountDto);
	
	Object addRoleEmployee(String username);
	
	Object removeRoleEmployee(Long id);
	
	String updateToken(String email);
	
	Object getByToken(String token);
	
	Object resetPassword(AccountDto accountDto, String token);
}
