package vn.com.gigo.mapstruct;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import vn.com.gigo.dto.ProductDto;
import vn.com.gigo.entity.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {
	ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class );
	// ----------------------------Entity To DTO---------------------------
	ProductDto productToProductDto(Product product);
	
	List<ProductDto> productsToProductDtos(List<Product> list);
	
	// ---------------------------DTO To Entity---------------------------
	Product productDtoToProduct(ProductDto dto);
	
	List<Product>productDtosToProducts(List<ProductDto> list);
}
