package vn.com.gigo.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.sun.istack.Nullable;

@Entity
@Table(name="orders")
@EntityListeners(AuditingEntityListener.class)
public class Order {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="boolean default true")
	private Boolean status;
	
	private String orderType;
	
	@Column(columnDefinition="boolean default false")
	private Boolean pay;
	
	@Column(columnDefinition="int default 0")
	private int total;
	
	@OneToMany(mappedBy="order")
	private List<OrderDetail> detailList;
	
	@Nullable
	@ManyToOne
	@JoinColumn(name="customer_id")
	private Customer customer;
	
	@ManyToOne
	@JoinColumn(name="employee_id")
	private Employee employee;
	
	@ManyToOne
	@JoinColumn(name="store_id")
	private Store store;
	
	@CreationTimestamp
	private Date createdDate;

	public Order() {
		super();
	}

	public Order(Boolean status, String orderType, Boolean pay, int total, List<OrderDetail> detailList,
			Customer customer, Employee employee, Store store, Date createdDate) {
		super();
		this.status = status;
		this.orderType = orderType;
		this.pay = pay;
		this.total = total;
		this.detailList = detailList;
		this.customer = customer;
		this.employee = employee;
		this.store = store;
		this.createdDate = createdDate;
	}

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

	public List<OrderDetail> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<OrderDetail> detailList) {
		this.detailList = detailList;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	
}

