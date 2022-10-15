package vn.com.gigo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.com.gigo.dtos.DataResponse;
import vn.com.gigo.dtos.DistrictDto;
import vn.com.gigo.services.impl.DistrictServiceImpl;

@RestController
@RequestMapping("/districts")
public class DistrictController {
	@Autowired
	private DistrictServiceImpl districtImpl;

	@GetMapping()
	public DataResponse getAllDistrict() {
		return  new DataResponse(districtImpl.getAllDistrict());
	}

	@GetMapping("/{id}")
	public DataResponse getDistrict(@PathVariable(value = "id") Long id) {
		 return new DataResponse(districtImpl.getDistrict(id));
	}

	@PostMapping()
	public DataResponse addDistrict(@RequestBody DistrictDto districtDto) {
		return new DataResponse(districtImpl.addDistrict(districtDto));
	}

	@DeleteMapping("/{id}")
	public void deleteDistrict(@PathVariable(value = "id") Long id) {
		 districtImpl.deleteDistrict(id);
	}

	@PutMapping("/{id}")
	public DataResponse updateDistrict(@PathVariable(value = "id") Long id, @RequestBody DistrictDto districtDto) {
		return new DataResponse(districtImpl.updateDistrict(id, districtDto));
	}

}
