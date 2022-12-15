package vn.com.gigo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

@NamedNativeQuery(name = "Product.getAllProductsByCategoryId", query = "SELECT * FROM products WHERE category_id = ?1", resultClass = Product.class)
@NamedNativeQuery(name = "Product.getAllProductsByCategoryId.count", query = "SELECT count(*) FROM products WHERE category_id = ?1")

@NamedNativeQuery(name="Product.searchByName",query="SELECT * FROM products WHERE products.name LIKE %:search%",resultClass=Product.class)
@NamedNativeQuery(name="Product.searchByName.count",query="SELECT count(*) FROM products WHERE name LIKE %:search%")
@Entity
@Table(name = "products")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String name;

	private Double price;

	private Double discount;
	
	@Lob 
	@Column(name="description", length=512)
	private String description;

	@Column(columnDefinition = "boolean default true")
	private Boolean status;

	private String imgURL;

	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;

	
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Product(String name, Double price, Double discount, String description, Boolean status, String imgURL) {
		super();
		this.name = name;
		this.price = price;
		this.discount = discount;
		this.description = description;
		this.status = status;
		this.imgURL = imgURL;
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

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getImgURL() {
		return imgURL;
	}

	public void setImgURL(String imgURL) {
		this.imgURL = imgURL;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}
