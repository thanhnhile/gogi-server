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
import vn.com.gigo.dtos.response.StoreDto;
import vn.com.gigo.services.impl.StoreServiceImpl;


@RestController
@RequestMapping("/stores")
public class StoreController {
	@Autowired
	private StoreServiceImpl storeImpl;

	@GetMapping()
	public DataResponse getAllStore() {
		return  new DataResponse(storeImpl.getAllStore());
	}

	@GetMapping("/{offset}/{pageSize}")
	public DataResponse getAllStore(@PathVariable(value = "offset") int offSet,
			@PathVariable(value = "pageSize") int pageSize) {
		return new DataResponse(storeImpl.getAllStorePagnation(offSet, pageSize));
	}

	@GetMapping("/{id}")
	public DataResponse getStore(@PathVariable(value = "id") Long id) {
		 return new DataResponse(storeImpl.getStore(id));
	}

	@PostMapping()
	public DataResponse addStore(@RequestBody StoreDto storeDto) {
		return new DataResponse(storeImpl.addStore(storeDto));
	}

	@DeleteMapping("/{id}")
	public void deleteStore(@PathVariable(value = "id") Long id) {
		 storeImpl.deleteStore(id);
	}

	@PutMapping("/{id}")
	public DataResponse updateStore(@PathVariable(value = "id") Long id, @RequestBody StoreDto storeDto) {
		return new DataResponse(storeImpl.updateStore(id, storeDto));
	}

	@GetMapping("/address/{province_id}/{district_id}")
	public DataResponse getStoreByProductId(@PathVariable(value = "province_id") Long provinceId,@PathVariable(value = "district_id") Long districtId){
		return new DataResponse(storeImpl.getStoreByAddress(provinceId, districtId));
	}
}
