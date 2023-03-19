package vn.com.gigo.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.sun.istack.Nullable;

@Entity
@Table(name="customers")
public class Customer {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private String phoneNumber;
	
	private String address;
	
	private Long provinceId;
	
	private Long districtId;
	
	@Column(columnDefinition="boolean default false")
	private Boolean isDefault;
	
	@ManyToOne
	@JoinColumn(name="account",referencedColumnName = "username")
	@Nullable
	private Account account;
	
	@OneToMany(mappedBy="customer")
	private List<Order> orderList;

	public Customer() {
		super();
	}

	

	public Customer(String name, String phoneNumber, String address, Long province_id, Long district_id,
			List<Order> orderList) {
		super();
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.provinceId = province_id;
		this.districtId = district_id;
		this.orderList = orderList;
	}



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



	public Account getAccount() {
		return account;
	}


	public Boolean getIsDefault() {
		return isDefault;
	}



	public void setIsDefault(Boolean isDefault) {
		this.isDefault = isDefault;
	}



	public void setAccount(Account account) {
		this.account = account;
	}



	public List<Order> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<Order> orderList) {
		this.orderList = orderList;
	}
	
	
}
