package vn.com.gigo.mapstruct.custom;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import vn.com.gigo.dtos.response.ProductDetailDto;
import vn.com.gigo.dtos.response.ProductDto;
import vn.com.gigo.dtos.response.RateDto;
import vn.com.gigo.entities.Product;
import vn.com.gigo.entities.Rate;
import vn.com.gigo.mapstruct.ProductMapper;

@Component
public class ProductCustomMapper {
	@Autowired
	private ProductMapper productMapper;
	
	private Double calculateAvgRating (List<Rate> rates) {
		if(rates.size() <=0)
			return Double.valueOf(0);
		int sum = 0;
		for(Rate rate:rates) {
			sum += rate.getPoint();
		}
		String avgPoint = String.format("%.1f",Double.valueOf(sum)/rates.size());
		return Double.valueOf(avgPoint );
	}
	public ProductDetailDto mapProductDetailDto(Product product) {
		if(product == null)
			return null;
		ProductDetailDto productDetailDto = new ProductDetailDto();
		ProductDto productDto = mapToProductDto(product);
		List<RateDto> rateDtos = productMapper.ratesToRateDtos(product.getRates());
		productDetailDto.setProductDto(productDto);
		productDetailDto.setRates(rateDtos);
		return productDetailDto;
	}
	
	public ProductDto mapToProductDto(Product product) {
		if(product == null)
			return null;
		ProductDto productDto = productMapper.productToProductDto(product);
		productDto.setAvgPoint(calculateAvgRating(product.getRates()));
		return productDto;
	}
	
	public List<ProductDto> mapToProductDtos (List<Product> list){
		 if ( list == null ) {
	            return null;
	        }
		 List<ProductDto> dtos = new ArrayList<ProductDto>();
		 for(Product product:list) {
			 dtos.add(mapToProductDto(product));
		 }
		 return dtos;
	}
}
