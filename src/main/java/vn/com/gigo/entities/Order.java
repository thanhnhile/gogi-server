package vn.com.gigo.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.sun.istack.Nullable;

@NamedNativeQuery(name = "Order.getOrdersByStoreId", query = "SELECT * FROM orders WHERE store_id=?1 ORDER BY id desc", resultClass=Order.class)
@NamedNativeQuery(name="Order.getOrdersByStoreId.count", query="SELECT count(*) FROM orders WHERE store_id=?1")
@Entity
@Table(name="orders")
@EntityListeners(AuditingEntityListener.class)
public class Order {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="int default 0")
	private int status; 
	
	private int orderType;
	
	@Column(columnDefinition="boolean default false")
	private Boolean pay;  //
	
	@Column(columnDefinition="double default 0")
	private double total;
	
	@OneToMany(mappedBy="order",cascade = CascadeType.ALL, orphanRemoval=true)
	private List<OrderDetail> detailList = new ArrayList<OrderDetail>();
	
	@Nullable
	@ManyToOne
	@JoinColumn(name="customer_id")
	private Customer customer;
	
	@Nullable
	@ManyToOne
	@JoinColumn(name="employee_id")
	private Employee employee;
	
	@ManyToOne
	@JoinColumn(name="store_id")
	private Store store;
	
	@CreationTimestamp
	private LocalDateTime createdDate;
	
	@Nullable
	@ManyToOne
	@JoinColumn(name="account",referencedColumnName = "username")
	private Account account;
	
	@Nullable
	@ManyToOne()
	@JoinColumn(name="voucher_id")
	private Voucher voucher;

	public Order() {
		super();
	}

	public Order(int status, int orderType, Boolean pay, double total, List<OrderDetail> detailList, Customer customer,
			Employee employee, Store store) {
		super();
		this.status = status;
		this.orderType = orderType;
		this.pay = pay;
		this.total = total;
		this.detailList = detailList;
		this.customer = customer;
		this.employee = employee;
		this.store = store;
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

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Boolean getPay() {
		return pay;
	}

	public void setPay(Boolean pay) {
		this.pay = pay;
	}

	public List<OrderDetail> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<OrderDetail> detailList) {
		this.detailList.clear();
		this.detailList.addAll(detailList);
			
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

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Voucher getVoucher() {
		return voucher;
	}

	public void setVoucher(Voucher voucher) {
		this.voucher = voucher;
	}
	
	
}
