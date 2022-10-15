package vn.com.gigo.services.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import vn.com.gigo.dtos.AccountDto;
import vn.com.gigo.dtos.AccountNoPassDto;
import vn.com.gigo.entities.Account;
import vn.com.gigo.entities.Role;
import vn.com.gigo.exception.AccountException;
import vn.com.gigo.mapstruct.AccountMapper;
import vn.com.gigo.repositories.AccountRepository;
import vn.com.gigo.services.AccountService;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AccountRepository accountRepo;
	@Autowired
	private AccountMapper accountMapper;

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
		Account account = accountMapper.accountDtoToAccount(accountDto);
		String rawPassword = account.getPassword();
		String encodedPassword = passwordEncoder.encode(rawPassword);// thuat toan ma hoa BCrypt
		account.setPassword(encodedPassword);
		accountRepo.save(account);
		return accountDto;
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

}
