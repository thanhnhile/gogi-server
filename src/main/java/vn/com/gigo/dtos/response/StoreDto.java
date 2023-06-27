package vn.com.gigo.dtos.response;

import com.fasterxml.jackson.annotation.JsonProperty;


public class StoreDto {
	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("storeName")
	private String storeName;
	
	@JsonProperty("provinceId")
	private Long provinceId;
	
	@JsonProperty("districtId")
	private Long districtId;
	
	@JsonProperty("address")
	private String address;

	public StoreDto() {
		super();
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	

	public Long getProvinceId() {
		return provinceId;
	}


	public void setProvinceId(Long provinceId) {
		this.provinceId = provinceId;
	}


	public Long getDistrictId() {
		return districtId;
	}


	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}


	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	
}
