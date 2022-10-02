package vn.com.gigo.entity;

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
	
//	@ManyToOne
//	@JoinColumn(name="employee_id")
//	private Employee employee;
	
//	@ManyToOne
//	@JoinColumn(name="store_id")
//	private Store store;
	
	@CreationTimestamp
	private Date createdDate;
}

