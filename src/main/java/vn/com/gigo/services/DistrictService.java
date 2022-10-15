package vn.com.gigo.services;

import vn.com.gigo.dtos.DistrictDto;

public interface DistrictService {
	Object getAllDistrict();

	Object getDistrict(Long id);

	Object addDistrict(DistrictDto DistrictDto);

	void deleteDistrict(Long id);

	Object updateDistrict(Long id, DistrictDto DistrictDto);
}
