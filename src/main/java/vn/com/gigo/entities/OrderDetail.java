package vn.com.gigo.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "order_details")
public class OrderDetail {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private Double price;
	
	private String size;

	@Column(columnDefinition="int default 0")
	private int quantity;
	
	private String sugar;
	
	private String iced;
	
	@OneToOne
	@JoinColumn(name = "product_id")
	private Product product;
	
	@ManyToOne()
	@JoinColumn(name="order_id")
	private Order order;
	
	@ManyToMany
	@JoinTable(name = "orderDetails_toppings", joinColumns = @JoinColumn(name = "orderDetail_id"), inverseJoinColumns = @JoinColumn(name = "topping_id"))
	private Set<Topping> toppings = new HashSet<>();


	public OrderDetail() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public OrderDetail(Double price, String size, int quantity, String sugar, String iced, Set<Topping> toppings) {
		super();
		this.price = price;
		this.size = size;
		this.quantity = quantity;
		this.sugar = sugar;
		this.iced = iced;
		this.toppings = toppings;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public Order getOrder() {
		return order;
	}

	public String getSugar() {
		return sugar;
	}

	public void setSugar(String sugar) {
		this.sugar = sugar;
	}

	public String getIced() {
		return iced;
	}

	public void setIced(String iced) {
		this.iced = iced;
	}

	public Set<Topping> getToppings() {
		return toppings;
	}

	public void setToppings(Set<Topping> toppings) {
		this.toppings = toppings;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
	

}
