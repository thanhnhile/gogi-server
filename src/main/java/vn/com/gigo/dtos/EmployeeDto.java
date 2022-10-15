package vn.com.gigo.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EmployeeDto {
	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("store")
	private StoreDto store;
	
	@JsonProperty("account")
	private AccountDto account;
	
	@JsonProperty("name")
	private String name;

	public EmployeeDto() {
		super();
	}

	public EmployeeDto(Long id, StoreDto store, AccountDto account, String name) {
		super();
		this.id = id;
		this.store = store;
		this.account = account;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public StoreDto getStore() {
		return store;
	}

	public void setStore(StoreDto store) {
		this.store = store;
	}

	public AccountDto getAccount() {
		return account;
	}

	public void setAccount(AccountDto account) {
		this.account = account;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

}
