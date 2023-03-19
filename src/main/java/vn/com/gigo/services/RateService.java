package vn.com.gigo.services;

import vn.com.gigo.dtos.RateInputDto;

public interface RateService {
	Object add(RateInputDto rateInputDto);
	
	Object getRatesByUsername();
}
