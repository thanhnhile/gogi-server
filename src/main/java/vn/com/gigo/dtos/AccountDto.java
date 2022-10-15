package vn.com.gigo.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AccountDto {
	@JsonProperty("username")
	private String username;

	@JsonProperty("password")
	private String password;

	public AccountDto() {
		super();
	}

	public AccountDto(String username, String password) {
		super();
		this.username = username;
		this.password = password;
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
	
	
}
