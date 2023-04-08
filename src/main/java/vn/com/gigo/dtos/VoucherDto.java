package vn.com.gigo.dtos;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VoucherDto {
	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("value")
	private Double value;
	
	@JsonProperty("startDate")
	private Date startDate;
	
	@JsonProperty("endDate")
	private Date endDate;
	
	@JsonProperty("code")
	private String code;
	
	@JsonProperty("minimumOrderValue")
	private Double minimumOrderValue;
	
	@JsonProperty("maximumDiscountAmount")
	private Double maximumDiscountAmount;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Double getMinimumOrderValue() {
		return minimumOrderValue;
	}

	public void setMinimumOrderValue(Double minimumOrderValue) {
		this.minimumOrderValue = minimumOrderValue;
	}

	public Double getMaximumDiscountAmount() {
		return maximumDiscountAmount;
	}

	public void setMaximumDiscountAmount(Double maximumDiscountAmount) {
		this.maximumDiscountAmount = maximumDiscountAmount;
	}

	
	
}
