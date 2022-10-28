package vn.com.gigo.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderDetailDto {
	@JsonProperty("detail_id")
	private Long id;
	
	@JsonProperty("price")
	private Double price;
	
	@JsonProperty("quantity")
	private int quantity;
	
	@JsonProperty("product_id")
	private Long product;

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

	public Long getProduct() {
		return product;
	}

	public void setProduct(Long product) {
		this.product = product;
	}
	
	
}
