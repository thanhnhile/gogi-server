package vn.com.gigo.dtos.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderDetailDto {
	@JsonProperty("detail_id")
	private Long id;
	
	@JsonProperty("price")
	private Double price;
	
	@JsonProperty("quantity")
	private int quantity;
	
	@JsonProperty("size")
	private String size;
	
	@JsonProperty("sugar")
	private String sugar;
	
	@JsonProperty("iced")
	private String iced;
	
	@JsonProperty("product_id")
	private Long productId;
	
	@JsonProperty("product_name")
	private String productName;
	
	@JsonProperty("img_url")
	private String imgURL;

	@JsonProperty("toppings")
	private List<ToppingDto> toppings;
	
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
	

	public String getImgURL() {
		return imgURL;
	}

	public void setImgURL(String imgURL) {
		this.imgURL = imgURL;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
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

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public List<ToppingDto> getToppings() {
		return toppings;
	}

	public void setToppings(List<ToppingDto> toppings) {
		this.toppings = toppings;
	}
	
}
