package vn.com.gigo.dtos.request;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AccountNoPassDto {
	@JsonProperty("username")
	private String username;
	@JsonProperty("role")
	private Set<String> role;

	public AccountNoPassDto() {
	}

	public AccountNoPassDto(String username, Set<String> role) {
		super();
		this.username = username;
		this.role = role;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Set<String> getRole() {
		return role;
	}

	public void setRole(Set<String> role) {
		this.role = role;
	}
}
