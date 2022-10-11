package vn.com.gigo.mapstruct;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import vn.com.gigo.dto.CategoryDto;
import vn.com.gigo.entity.Category;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

	CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

	// ----------------------------Entity To DTO---------------------------
	// Category
	CategoryDto categoryToCategoryDto(Category category);

	List<CategoryDto> categorysToCategoryDtos(List<Category> category);

	// ---------------------------DTO To Entity----------------------
	// Category
	Category categoryDtoToCategory(CategoryDto categoryDto);

}
