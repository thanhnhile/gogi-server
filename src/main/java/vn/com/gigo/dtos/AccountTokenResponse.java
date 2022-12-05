package vn.com.gigo.dtos;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;

public class AccountTokenResponse {
	private String username;
	private String accessToken;
	private Collection<? extends GrantedAuthority> roles;

	public AccountTokenResponse() { }

	
	public Collection<? extends GrantedAuthority> getRoles() {
		return roles;
	}


	public AccountTokenResponse(String username, String accessToken) {
		super();
		this.username = username;
		this.accessToken = accessToken;
	}


	public AccountTokenResponse(String username, String accessToken, Collection<? extends GrantedAuthority> collection) {
		super();
		this.username = username;
		this.accessToken = accessToken;
		this.roles = collection;
	}


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
}
