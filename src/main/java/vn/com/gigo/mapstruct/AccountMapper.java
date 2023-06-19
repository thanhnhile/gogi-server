package vn.com.gigo.mapstruct;

import java.util.List;

import org.mapstruct.Mapper;

import vn.com.gigo.dtos.request.AccountDto;
import vn.com.gigo.dtos.request.AccountNoPassDto;
import vn.com.gigo.entities.Account;

@Mapper(componentModel = "spring")
public interface AccountMapper {
	// ----------------------------Entity To DTO---------------------------

	AccountDto accountToAccountDto(Account account);

	List<AccountDto> accountsToAccountDtos(List<Account> accounts);

	AccountNoPassDto accountToAccountNoPassDto(Account account);

	List<AccountNoPassDto> accountsToAccountNoPassDtos(List<Account> accounts);

	// ---------------------------DTO To Entity----------------------

	Account accountDtoToAccount(AccountDto accountDto);

}
