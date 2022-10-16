package vn.com.gigo.dtos;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderDto {
	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("status")
	private Boolean status;
	
	@JsonProperty("orderType")
	private String orderType;
	
	@JsonProperty("pay")
	private Boolean pay;
	
	@JsonProperty("total")
	private int total;
	
	@JsonProperty("details")
	private List<OrderDetailDto> detailList;
	
	@JsonProperty("customer")
	private CustomerDto customer;
	
	@JsonProperty("employee_name")
	private String employee;
	
	@JsonProperty("store")
	private StoreDto store;
	
	@JsonProperty("createdDate")
	private Date createdDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

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

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	
}	
