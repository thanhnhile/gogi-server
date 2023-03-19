package vn.com.gigo.mapstruct;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import vn.com.gigo.dtos.RateDto;
import vn.com.gigo.dtos.RateInputDto;
import vn.com.gigo.entities.Rate;

@Mapper(componentModel = "spring")
public interface RateMapper {
	//RateInputDto
	@Mapping(source="product", target="product.id")
	Rate rateInputDtoToRate (RateInputDto dto);
	//RateDto
	// ----------------------------Entity To DTO---------------------------
	@Mapping(source="user.username", target="user")
	RateDto rateToRateDto(Rate rate);
	List<RateDto> ratesToRateDtos(List<Rate> rates);
	// ---------------------------DTO To Entity----------------------------
	@Mapping(source="user", target="user.username")
	Rate rateDtoToRate(RateDto dto);
}
