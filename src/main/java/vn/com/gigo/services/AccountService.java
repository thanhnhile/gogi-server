package vn.com.gigo.services;

import vn.com.gigo.dtos.request.AccountDto;

public interface AccountService {
	Object getAvailableAccount();
	
	Object getAccount(Long id);
	
	Object getCustomerInfoByUserName();

	Object getAllAccount();

	Object addAccount(AccountDto accountDto);

	void deleteAccount(Long id);

	Object updateAccount(Long id, AccountDto accountDto);
	
	Object addRoleEmployee(String username);
	
	Object removeRoleEmployee(Long id);
	
	String updateToken(String email);
	
	Object getByToken(String token);
	
	Object resetPassword(String token, AccountDto accountDto);

	Object getCustomerInfoDefault();

	Object updateDefaultCustomerInfo(Long id);
	
	Object likeProduct(Long id);
	
	Object unlikeProduct(Long id);
	
	Object getAllProductsLiked();
}
