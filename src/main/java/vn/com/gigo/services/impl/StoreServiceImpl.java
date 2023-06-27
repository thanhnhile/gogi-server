package vn.com.gigo.services.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import vn.com.gigo.dtos.PagingDto;
import vn.com.gigo.dtos.response.StoreDto;
import vn.com.gigo.entities.Store;
import vn.com.gigo.mapstruct.StoreMapper;
import vn.com.gigo.repositories.StoreRepository;
import vn.com.gigo.services.StoreService;

@Service
@Transactional
public class StoreServiceImpl implements StoreService{

	@Autowired
	private StoreRepository storeRepo;
	
	
	@Autowired
	private StoreMapper storeMapper;

	@Autowired
	public StoreServiceImpl(StoreMapper storeMapper, StoreRepository storeRepo) {
		this.storeMapper = storeMapper;
		this.storeRepo = storeRepo;
	}
	@Override
	public Object getAllStore() {
		return storeMapper.storesToStoreDtos(storeRepo.findAll());
	}

	@Override
	public Object getAllStorePagnation(int offSet, int pageSize) {
		Pageable pageable = PageRequest.of(offSet - 1, pageSize);
		Page<Store> page = storeRepo.findAll(pageable);
		PagingDto response = new PagingDto();
		response.setCurrentPage(offSet);
		response.setTotalElements(page.getTotalElements());
		response.setTotalPages(page.getTotalPages());
		List<StoreDto> listStoreDtos = storeMapper.storesToStoreDtos(page.getContent());
		response.setContent(listStoreDtos);

		return page;
	}

	@Override
	public Object getStore(Long id) {
		return storeMapper.storeToStoreDto(storeRepo.findOneById(id));
	}

	@Override
	public Object addStore(StoreDto storeDto) {
		Store store = storeRepo.save(storeMapper.storeDtoToStore(storeDto));
		return storeMapper.storeToStoreDto(storeRepo.save(store));
	}

	@Override
	public void deleteStore(Long id) {
		storeRepo.delete(storeRepo.findOneById(id));
		
	}

	@Override
	public Object updateStore(Long id, StoreDto storeDto) {
		Optional<Store> store = storeRepo.findById(id);
		if(store.isPresent()) {
			storeDto.setId(id);
			Store storeToUpdate = storeMapper.storeDtoToStore(storeDto);
			return storeMapper.storeToStoreDto(storeRepo.save(storeToUpdate));
		}
		else 
			return null;
	}
	@Override
	public Object getStoreByAddress(Long provinceId, Long districtId) {
		if(districtId != -1)
			return storeMapper.storesToStoreDtos(storeRepo.findByAddress(provinceId, districtId));
		else return storeMapper.storesToStoreDtos(storeRepo.findOneByProvinceId(provinceId));
	}



}
