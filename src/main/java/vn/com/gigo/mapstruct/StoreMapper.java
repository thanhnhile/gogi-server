package vn.com.gigo.mapstruct;

import java.util.List;

import org.mapstruct.Mapper;

import vn.com.gigo.dtos.response.StoreDto;
import vn.com.gigo.entities.Store;

@Mapper(componentModel = "spring")
public interface StoreMapper {
	// ----------------------------Entity To DTO---------------------------

	StoreDto storeToStoreDto(Store store);

	List<StoreDto> storesToStoreDtos(List<Store> stores);

	// ---------------------------DTO To Entity----------------------

	Store storeDtoToStore(StoreDto storeDto);
}
