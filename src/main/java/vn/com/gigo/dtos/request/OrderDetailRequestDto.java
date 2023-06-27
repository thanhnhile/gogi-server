package vn.com.gigo.dtos.request;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

import vn.com.gigo.dtos.response.ToppingDto;

public class OrderDetailRequestDto {
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
	private Long product;
	
	@JsonProperty("toppings")
	private Set<ToppingDto> toppings;

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

	public Long getProduct() {
		return product;
	}

	public void setProduct(Long product) {
		this.product = product;
	}

	public Set<ToppingDto> getToppings() {
		return toppings;
	}

	public void setToppings(Set<ToppingDto> toppings) {
		this.toppings = toppings;
	}
}
