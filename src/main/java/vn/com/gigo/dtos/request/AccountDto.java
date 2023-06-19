package vn.com.gigo.dtos.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AccountDto {
	@JsonProperty("username")
	private String username;

	@JsonProperty("password")
	private String password;

	@JsonProperty("email")
	private String email;
	
	@JsonProperty("token")
	private String token;
	
	public AccountDto() {
		super();
	}

	public AccountDto(String username, String password, String email, String token) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.token = token;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
}
