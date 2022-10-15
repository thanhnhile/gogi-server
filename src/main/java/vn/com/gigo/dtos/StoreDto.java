package vn.com.gigo.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;


public class StoreDto {
	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("storeName")
	private String storeName;
	
	@JsonProperty("district")
	private DistrictDto district;
	
	@JsonProperty("address")
	private String address;

	public StoreDto() {
		super();
	}

	public StoreDto(Long id, String storeName, DistrictDto district, String address) {
		super();
		this.id = id;
		this.storeName = storeName;
		this.district = district;
		this.address = address;
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

	public DistrictDto getDistrict() {
		return district;
	}

	public void setDistrict(DistrictDto district) {
		this.district = district;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	
}
