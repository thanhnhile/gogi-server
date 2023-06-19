package vn.com.gigo.entities;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="vouchers")
public class Voucher {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false)
	private String name;
	
	@Column(nullable=false)
	private Double value;
	
	private LocalDateTime startDate;
	
	private LocalDateTime endDate;
	
	@Column(nullable=false)
	private String code;
	
	private Double minimumOrderValue;
	
	private Double maximumDiscountAmount;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "roles")
	private Set<Account> accounts = new HashSet<>();

	@OneToMany(mappedBy="voucher", fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	private List<Order> orderList;
	
	public Voucher() {
		super();
	}

	
	public Voucher(String name, Double value, LocalDateTime startDate, LocalDateTime endDate, String code, Double minimumOrderValue,
			Double maximumDiscountAmount, Set<Account> accounts) {
		super();
		this.name = name;
		this.value = value;
		this.startDate = startDate;
		this.endDate = endDate;
		this.code = code;
		this.minimumOrderValue = minimumOrderValue;
		this.maximumDiscountAmount = maximumDiscountAmount;
		this.accounts = accounts;
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

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDateTime endDate) {
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


	public Set<Account> getAccounts() {
		return accounts;
	}


	public void setAccounts(Set<Account> accounts) {
		this.accounts = accounts;
	}


	public List<Order> getOrderList() {
		return orderList;
	}


	public void setOrderList(List<Order> orderList) {
		this.orderList = orderList;
	}
	
	
}
