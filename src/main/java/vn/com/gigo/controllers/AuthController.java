package vn.com.gigo.controllers;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import vn.com.gigo.dtos.AccountDto;
import vn.com.gigo.dtos.AccountTokenResponse;
import vn.com.gigo.dtos.DataResponse;
import vn.com.gigo.entities.Account;
import vn.com.gigo.security.JwtTokenUtil;
import vn.com.gigo.services.impl.AccountServiceImpl;

@RestController
public class AuthController {
	@Autowired
	private AccountServiceImpl accountImpl;

	@Autowired
	AuthenticationManager authManager;

	@Autowired
	JwtTokenUtil jwtUtil;
	
	@PostMapping("/auth")
	public DataResponse login(@RequestBody AccountDto accountDto) {
		Authentication authentication = authManager.authenticate(
				new UsernamePasswordAuthenticationToken(accountDto.getUsername(), accountDto.getPassword()));

		Account account = (Account) authentication.getPrincipal();
		String accessToken = jwtUtil.generateAccessToken(account);
		AccountTokenResponse response = new AccountTokenResponse(account.getUsername(), accessToken,account.getAuthorities());

		return new DataResponse(response);

	}
	
	@PostMapping("/register")
	public DataResponse addAccount(@RequestBody AccountDto accountDto) {
		return new DataResponse(accountImpl.addAccount(accountDto));
	}
}
