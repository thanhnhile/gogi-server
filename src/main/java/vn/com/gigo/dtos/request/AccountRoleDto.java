package vn.com.gigo.dtos.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AccountRoleDto {
	@JsonProperty("account_id")
	private Long account_id;
	@JsonProperty("role_id")
	private Integer role_id;
	public AccountRoleDto() {
		super();
	}
	public AccountRoleDto(Long account_id, Integer role_id) {
		super();
		this.account_id = account_id;
		this.role_id = role_id;
	}
	public Long getAccount_id() {
		return account_id;
	}
	public void setAccount_id(Long account_id) {
		this.account_id = account_id;
	}
	public Integer getRole_id() {
		return role_id;
	}
	public void setRole_id(Integer role_id) {
		this.role_id = role_id;
	}
	
}
