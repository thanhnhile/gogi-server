package vn.com.gigo.services;

import vn.com.gigo.dtos.request.RateRequestDto;

public interface RateService {
	Object add(RateRequestDto rateInputDto);
	
	Object getRatesByUsername();
	
	Object getRatesByProductId(Long id);
	
	Boolean checkExisted(Long id);
}
