package vn.com.gigo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.com.gigo.dtos.AccountDto;
import vn.com.gigo.dtos.AccountTokenResponse;
import vn.com.gigo.dtos.DataResponse;
import vn.com.gigo.entities.Account;
import vn.com.gigo.security.JwtTokenUtil;
import vn.com.gigo.services.impl.AccountServiceImpl;

@RestController
@RequestMapping("/accounts")
public class AccountController {
	@Autowired
	private AccountServiceImpl accountImpl;

	@Autowired
	AuthenticationManager authManager;

	@Autowired
	JwtTokenUtil jwtUtil;

	@PostMapping("/login")
	public DataResponse login(@RequestBody AccountDto accountDto) {
		Authentication authentication = authManager.authenticate(
				new UsernamePasswordAuthenticationToken(accountDto.getUsername(), accountDto.getPassword()));

		Account account = (Account) authentication.getPrincipal();
		String accessToken = jwtUtil.generateAccessToken(account);
		AccountTokenResponse response = new AccountTokenResponse(account.getUsername(), accessToken);

		return new DataResponse(response);

	}

	@GetMapping()
	public DataResponse getAccount() {
		return new DataResponse(accountImpl.getAllAccount());
	}

	@GetMapping("/{id}")
	public DataResponse getAccount(@PathVariable(value = "id") Long id) {
		return new DataResponse(accountImpl.getAccount(id));
	}

	@PostMapping()
	public DataResponse addAccount(@RequestBody AccountDto accountDto) {
		return new DataResponse(accountImpl.addAccount(accountDto));
	}

	@DeleteMapping("/{id}")
	public void deleteAccount(@PathVariable(value = "id") Long id) {
		accountImpl.deleteAccount(id);
	}

	@PutMapping("/{id}")
	public DataResponse updateAccount(@PathVariable(value = "id") Long id, @RequestBody AccountDto accountDto) {
		return new DataResponse(accountImpl.updateAccount(id, accountDto));
	}
}