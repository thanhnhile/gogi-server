package vn.com.gigo.dtos.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductDto {
	@JsonProperty("id")
	private Long id;
	
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
	
	@JsonProperty("avgPoint")
	private Double avgPoint;
	
	@JsonProperty("hasTopping")
	private Boolean hasTopping;

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

	public Double getAvgPoint() {
		return avgPoint;
	}

	public void setAvgPoint(Double avgPoint) {
		this.avgPoint = avgPoint;
	}

	public Boolean getHasTopping() {
		return hasTopping;
	}

	public void setHasTopping(Boolean hasTopping) {
		this.hasTopping = hasTopping;
	}

}

