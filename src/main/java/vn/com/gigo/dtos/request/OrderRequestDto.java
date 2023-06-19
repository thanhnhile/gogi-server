package vn.com.gigo.dtos.request;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import vn.com.gigo.dtos.response.CustomerDto;

public class OrderRequestDto {
	
	@JsonProperty("orderType")
	private String orderType;

	@JsonProperty("pay")
	private Boolean pay;

	@JsonProperty("total")
	private int total;

	@JsonProperty("details")
	private List<OrderDetailRequestDto> detailList;

	@JsonProperty("customer")
	private CustomerDto customer;

	@JsonProperty("store_id")
	private Long store;
	
	@JsonProperty("account_username")
	private String accountUsername;
	
	@JsonProperty("voucher_id")
	private Long voucher;

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public Boolean getPay() {
		return pay;
	}

	public void setPay(Boolean pay) {
		this.pay = pay;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<OrderDetailRequestDto> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<OrderDetailRequestDto> detailList) {
		this.detailList = detailList;
	}


	public CustomerDto getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerDto customer) {
		this.customer = customer;
	}

	public Long getStore() {
		return store;
	}

	public void setStore(Long store) {
		this.store = store;
	}

	public String getAccountUsername() {
		return accountUsername;
	}

	public void setAccountUsername(String accountUsername) {
		this.accountUsername = accountUsername;
	}

	public Long getVoucher() {
		return voucher;
	}

	public void setVoucher(Long voucher) {
		this.voucher = voucher;
	}

}
