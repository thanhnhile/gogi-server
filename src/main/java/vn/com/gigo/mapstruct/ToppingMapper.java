package vn.com.gigo.mapstruct;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import vn.com.gigo.dtos.response.ToppingDto;
import vn.com.gigo.entities.Topping;
@Mapper(componentModel = "spring")
public interface ToppingMapper {
	ToppingMapper INSTANCE = Mappers.getMapper(ToppingMapper.class);

	// ----------------------------Entity To DTO---------------------------
	ToppingDto toppingToToppingDto(Topping topping);

	List<ToppingDto> toppingsToToppingDtos(List<Topping> list);

	// ---------------------------DTO To Entity----------------------
	Topping toppingDtoToTopping(ToppingDto toppingDto);

	List<Topping> toppingDtosToToppings (List<ToppingDto> toppingDto);
}
