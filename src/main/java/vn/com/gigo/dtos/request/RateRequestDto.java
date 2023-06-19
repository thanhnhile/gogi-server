package vn.com.gigo.dtos.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RateRequestDto {
	@JsonProperty("point")
	private int point;
	
	@JsonProperty("content")
	private String content;
	
	@JsonProperty("product_id")
	private Long product;

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

	public Long getProduct() {
		return product;
	}

	public void setProduct(Long product) {
		this.product = product;
	}
	
	
}
