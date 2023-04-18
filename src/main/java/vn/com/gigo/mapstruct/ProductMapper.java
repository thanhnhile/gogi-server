package vn.com.gigo.mapstruct;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import vn.com.gigo.dtos.ProductDto;
import vn.com.gigo.dtos.ProductInputDto;
import vn.com.gigo.dtos.RateDto;
import vn.com.gigo.entities.Product;
import vn.com.gigo.entities.Rate;

@Mapper(componentModel = "spring")
public interface ProductMapper {
	ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

	/**Rate*/
	// ----------------------------Entity To DTO---------------------------
	@Mapping(source = "user.username", target = "user")
	RateDto rateToRateDto(Rate rate);

	List<RateDto> ratesToRateDtos(List<Rate> rates);

	// ---------------------------DTO To Entity----------------------------
	@Mapping(source = "user", target = "user.username")
	Rate rateDtoToRate(RateDto dto);
	
	/**ProductInputDto*/
	Product productInputDtoToProduct(ProductInputDto dto);

	/**ProductDto*/
	// ----------------------------Entity To DTO---------------------------
	ProductDto productToProductDto(Product product);

	List<ProductDto> productsToProductDtos(List<Product> list);

	// ---------------------------DTO To Entity---------------------------
	Product productDtoToProduct(ProductDto dto);

	List<Product> productDtosToProducts(List<ProductDto> list);
}
