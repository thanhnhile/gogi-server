package vn.com.gigo.services.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.com.gigo.dtos.DistrictDto;
import vn.com.gigo.entities.District;
import vn.com.gigo.mapstruct.DistrictMapper;
import vn.com.gigo.repositories.DistrictRepository;
import vn.com.gigo.services.DistrictService;

@Service
@Transactional
public class DistrictServiceImpl implements DistrictService{

	@Autowired
	private DistrictRepository districtRepo;
	@Autowired
	private DistrictMapper districtMapper;

	@Autowired
	public DistrictServiceImpl(DistrictMapper districtMapper, DistrictRepository districtRepo) {
		this.districtMapper = districtMapper;
		this.districtRepo = districtRepo;
	}
	@Override
	public Object getAllDistrict() {
		return districtMapper.districtsToDistrictDtos(districtRepo.findAll());
	}

	@Override
	public Object getDistrict(Long id) {
		return districtMapper.districtToDistrictDto(districtRepo.findOneById(id));
	}

	@Override
	public Object addDistrict(DistrictDto districtDto) {
		District district = districtMapper.districtDtoToDistrict(districtDto);
		return districtMapper.districtToDistrictDto(districtRepo.save(district));
	}

	@Override
	public void deleteDistrict(Long id) {
		districtRepo.delete(districtRepo.findOneById(id));
	}

	@Override
	public Object updateDistrict(Long id, DistrictDto districtDto) {
		Optional<District> district = districtRepo.findById(id);
		if(district.isPresent()) {
			districtDto.setId(id);
			District update = districtMapper.districtDtoToDistrict(districtDto);
			return districtMapper.districtToDistrictDto(update);
		}
		else 
			return null;
	}

}
