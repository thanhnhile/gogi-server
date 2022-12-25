package vn.com.gigo.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name = "stores")
public class Store {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "store_id")
	private Long id;

	@Column(nullable = false,unique = true)
	private String storeName;
	
	private Long provinceId;
	
	private Long districtId;
	@Column
	private String address;
	
	@OneToMany(mappedBy = "store") 
	private List<Order> orders;
	
	@OneToMany(mappedBy = "store") 

	private List<Employee> employees;

	public Store() {
		super();
	}

	

	public Store(String storeName, Long provinceId, Long districtId, String address, List<Order> orders,
			List<Employee> employees) {
		super();
		this.storeName = storeName;
		this.provinceId = provinceId;
		this.districtId = districtId;
		this.address = address;
		this.orders = orders;
		this.employees = employees;
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

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
	
	
}
