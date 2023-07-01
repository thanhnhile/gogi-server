package vn.com.gigo.controllers;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import vn.com.gigo.dtos.DataResponse;
import vn.com.gigo.dtos.request.AccountDto;
import vn.com.gigo.dtos.response.AccountTokenResponse;
import vn.com.gigo.dtos.response.TokenRefreshDto;
import vn.com.gigo.entities.Account;
import vn.com.gigo.entities.RefreshToken;
import vn.com.gigo.security.JwtTokenUtil;
import vn.com.gigo.services.impl.AccountServiceImpl;
import vn.com.gigo.services.impl.RefreshTokenServiceImpl;

@RestController
public class AuthController {
	private static final String REFRESH_TOKEN_COOKIE = "refresh_token";
	@Autowired
	private AccountServiceImpl accountImpl;

	@Autowired
	AuthenticationManager authManager;

	@Autowired
	JwtTokenUtil jwtUtil;

	@Autowired
	private RefreshTokenServiceImpl refreshTokenImpl;

	@PostMapping("/auth")
	public DataResponse login(@RequestBody AccountDto accountDto, HttpServletResponse response) {
		Authentication authentication = authManager.authenticate(
				new UsernamePasswordAuthenticationToken(accountDto.getUsername(), accountDto.getPassword()));

		Account account = (Account) authentication.getPrincipal();
		String accessToken = jwtUtil.generateAccessToken(account);
		String refreshToken = refreshTokenImpl.createRefreshToken(account.getId()).getToken();
		// store refresh token
		setRefreshToken(response, refreshToken);
		AccountTokenResponse authResponse = new AccountTokenResponse(account.getUsername(), accessToken,
				account.getAuthorities());
		return new DataResponse(authResponse);

	}

	@PostMapping("/register")
	public DataResponse addAccount(@RequestBody AccountDto accountDto) {
		return new DataResponse(accountImpl.addAccount(accountDto));
	}

	@GetMapping("/refresh")
	public DataResponse refreshToken(HttpServletRequest request, HttpServletResponse response) {
		String requestRefreshToken = getRefreshToken(request);
		RefreshToken refreshToken = refreshTokenImpl.findByToken(requestRefreshToken);
		refreshTokenImpl.verifyExpiration(refreshToken);
		String accessToken = jwtUtil.generateAccessToken(refreshToken.getAccount());
		String newRefreshToken = refreshTokenImpl.createRefreshToken(refreshToken.getAccount().getId()).getToken();
		TokenRefreshDto accessTokenRes = new TokenRefreshDto(accessToken);
		setRefreshToken(response, newRefreshToken);
		return new DataResponse(accessTokenRes);
	}

	private String getRefreshToken(HttpServletRequest req) {
		Cookie[] cookies = req.getCookies();
		if (cookies == null || cookies.length == 0)
			return null;
		for (Cookie cookie : req.getCookies()) {
			if (cookie.getName().equals(REFRESH_TOKEN_COOKIE))
				return cookie.getValue();
		}
		return null;
	}
	
	private void setRefreshToken (HttpServletResponse response, String refreshToken) {
		Cookie refreshTokenCookie = new Cookie(REFRESH_TOKEN_COOKIE, refreshToken);
		refreshTokenCookie.setHttpOnly(true);
		refreshTokenCookie.setSecure(true);
		refreshTokenCookie.setMaxAge(Math.toIntExact(7 * 24 * 60 * 60));
		refreshTokenCookie.setPath("/");
		response.addCookie(refreshTokenCookie);
	}

}
