package vn.com.gigo.dtos;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderInputDto {
	
	@JsonProperty("orderType")
	private String orderType;

	@JsonProperty("pay")
	private Boolean pay;

	@JsonProperty("total")
	private int total;

	@JsonProperty("details")
	private List<OrderDetailDto> detailList;

	@JsonProperty("customer_id")
	private Long customer;

	@JsonProperty("employee_id")
	private Long employee;

	@JsonProperty("store_id")
	private Long store;

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

	public Long getCustomer() {
		return customer;
	}

	public void setCustomer(Long customer) {
		this.customer = customer;
	}

	public Long getEmployee() {
		return employee;
	}

	public void setEmployee(Long employee) {
		this.employee = employee;
	}

	public Long getStore() {
		return store;
	}

	public void setStore(Long store) {
		this.store = store;
	}
	
	

}
