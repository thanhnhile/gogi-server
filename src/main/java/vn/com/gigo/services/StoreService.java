package vn.com.gigo.services;

import vn.com.gigo.dtos.response.StoreDto;

public interface StoreService {
	Object getAllStore();

	Object getAllStorePagnation(int offSet, int pageSize);

	Object getStore(Long id);

	Object addStore(StoreDto storeDto);

	void deleteStore(Long id);

	Object updateStore(Long id, StoreDto storeDto);

	Object getStoreByAddress(Long provinceId,Long districtId);
}
