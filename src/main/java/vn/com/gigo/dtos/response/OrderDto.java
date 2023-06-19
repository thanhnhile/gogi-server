package vn.com.gigo.dtos.response;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;


public class OrderDto {
	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("status")
	private int status;
	
	@JsonProperty("orderType")
	private int orderType;
	
	@JsonProperty("pay")
	private Boolean pay;
	
	@JsonProperty("total")
	private double total;
	
	@JsonProperty("details")
	private List<OrderDetailDto> detailList;
	
	@JsonProperty("customer")
	private CustomerDto customer;
	
	@JsonProperty("employee_name")
	private String employee;
	
	@JsonProperty("store")
	private StoreDto store;
	
	@JsonProperty("createdDate")
	private LocalDateTime createdDate;
	
	@JsonProperty("account_username")
	private String accountUsername;
	
	@JsonProperty("voucher")
	private VoucherDto voucher;

	public String getAccountUsername() {
		return accountUsername;
	}
	

	public void setAccountUsername(String accountUsername) {
		this.accountUsername = accountUsername;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getOrderType() {
		return orderType;
	}

	public void setOrderType(int orderType) {
		this.orderType = orderType;
	}

	public Boolean getPay() {
		return pay;
	}

	public void setPay(Boolean pay) {
		this.pay = pay;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}



	
	public List<OrderDetailDto> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<OrderDetailDto> detailList) {
		this.detailList = detailList;
	}

	public CustomerDto getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerDto customer) {
		this.customer = customer;
	}

	public String getEmployee() {
		return employee;
	}

	public void setEmployee(String employee) {
		this.employee = employee;
	}

	public StoreDto getStore() {
		return store;
	}

	public void setStore(StoreDto store) {
		this.store = store;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}


	public VoucherDto getVoucher() {
		return voucher;
	}


	public void setVoucher(VoucherDto voucher) {
		this.voucher = voucher;
	}

}	
