package vn.com.gigo.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderDetailResponseDto {
	@JsonProperty("detail_id")
	private Long id;
	
	@JsonProperty("price")
	private Double price;
	
	@JsonProperty("quantity")
	private int quantity;
	
	@JsonProperty("size")
	private String size;
	
	@JsonProperty("product_id")
	private Long product;
	
	@JsonProperty("imgURL")
	private String imgURL;

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

	public Long getProduct() {
		return product;
	}

	public void setProduct(Long product) {
		this.product = product;
	}
	

}
