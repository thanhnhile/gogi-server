package vn.com.gigo.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@Table(name="rates")
@EntityListeners(AuditingEntityListener.class)
public class Rate {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private int point;
	
	@Lob 
	@Column(name="content",length=200)
	private String content;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private Account user;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="product_id")
	private Product product;
	
	@CreationTimestamp
	//@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime createdDate;

	
	public Rate() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Rate(int point, String content, Account user, Product product, LocalDateTime createdDate) {
		super();
		this.point = point;
		this.content = content;
		this.user = user;
		this.product = product;
		this.createdDate = createdDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Account getUser() {
		return user;
	}

	public void setUser(Account user) {
		this.user = user;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}
	
	
}
