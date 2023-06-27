package vn.com.gigo.dtos.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductDetailDto {
	@JsonProperty("product")
	ProductDto productDto;
	
	@JsonProperty("rates")
	private List<RateDto> rates;

	public ProductDto getProductDto() {
		return productDto;
	}

	public void setProductDto(ProductDto productDto) {
		this.productDto = productDto;
	}

	public List<RateDto> getRates() {
		return rates;
	}

	public void setRates(List<RateDto> rates) {
		this.rates = rates;
	}
}
