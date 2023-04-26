package vn.com.gigo.services;

import vn.com.gigo.dtos.RateInputDto;

public interface RateService {
	Object add(RateInputDto rateInputDto);
	
	Object getRatesByUsername();
	
	Object getRatesByProductId(Long id);
	
	Boolean checkExisted(Long id);
}
