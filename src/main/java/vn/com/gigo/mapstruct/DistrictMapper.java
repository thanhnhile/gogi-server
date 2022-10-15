package vn.com.gigo.mapstruct;

import java.util.List;

import org.mapstruct.Mapper;

import vn.com.gigo.dtos.DistrictDto;
import vn.com.gigo.entities.District;

@Mapper(componentModel = "spring")
public interface DistrictMapper {
	// ----------------------------Entity To DTO---------------------------

	DistrictDto districtToDistrictDto(District district);

	List<DistrictDto> districtsToDistrictDtos(List<District> districts);

	// ---------------------------DTO To Entity----------------------

	District districtDtoToDistrict(DistrictDto districtDto);
}
