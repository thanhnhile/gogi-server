package vn.com.gigo.entities;

import java.util.ArrayList;
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
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@NamedNativeQuery(name = "Product.getAllProductsByCategoryId", query = "SELECT * FROM products WHERE category_id = ?1", resultClass = Product.class)
@NamedNativeQuery(name = "Product.getAllProductsByCategoryId.count", query = "SELECT count(*) FROM products WHERE category_id = ?1")

@NamedNativeQuery(name = "Product.searchByName", query = "SELECT * FROM products WHERE products.name LIKE %:search%", resultClass = Product.class)
@NamedNativeQuery(name = "Product.searchByName.count", query = "SELECT count(*) FROM products WHERE name LIKE %:search%")

@NamedEntityGraph(name = "product-entity-graph", attributeNodes = { @NamedAttributeNode("category"), @NamedAttributeNode("rates")  })
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
	@Column(name = "description", length = 512)
	private String description;

	@Column(columnDefinition = "boolean default true")
	private Boolean status;

	private String imgURL;

	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;

	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	private List<Rate> rates = new ArrayList<Rate>();

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "products")
	private Set<Account> accounts = new HashSet<>();
	
	@Column(columnDefinition = "boolean default true")
	private Boolean hasTopping;

	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Product(String name, Double price, Double discount, String description, Boolean status, String imgURL, Boolean hasTopping) {
		super();
		this.name = name;
		this.price = price;
		this.discount = discount;
		this.description = description;
		this.status = status;
		this.imgURL = imgURL;
		this.hasTopping = hasTopping;
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

	public List<Rate> getRates() {
		return rates;
	}

	public void setRates(List<Rate> rates) {
		this.rates = rates;
	}

	public Set<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(Set<Account> accounts) {
		this.accounts = accounts;
	}

	public Boolean getHasTopping() {
		return hasTopping;
	}

	public void setHasTopping(Boolean hasTopping) {
		this.hasTopping = hasTopping;
	}
	
	

}
