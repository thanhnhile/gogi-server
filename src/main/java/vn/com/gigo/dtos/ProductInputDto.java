package vn.com.gigo.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductInputDto {
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("category")
	private CategoryDto category;
	
	@JsonProperty("price")
	private Double price;
	
	@JsonProperty("discount")
	private Double discount;
	
	@JsonProperty("description")
	private String description;
	
	@JsonProperty("status")
	private Boolean status;
	
	@JsonProperty("img_url")
	private String imgURL;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CategoryDto getCategory() {
		return category;
	}

	public void setCategory(CategoryDto category) {
		this.category = category;
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
	
}