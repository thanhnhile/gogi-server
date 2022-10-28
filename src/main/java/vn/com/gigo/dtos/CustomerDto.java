package vn.com.gigo.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomerDto {
	@JsonProperty("id")
	private Long id;

	@JsonProperty("name")
	private String name;
	
	@JsonProperty("phone")
	private String phoneNumber;

	@JsonProperty("address")
	private String address;
	
	@JsonProperty("district")
	private DistrictDto district;

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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public DistrictDto getDistrict() {
		return district;
	}

	public void setDistrict(DistrictDto district) {
		this.district = district;
	}
	
	
}
