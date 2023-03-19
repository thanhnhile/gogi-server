package vn.com.gigo.services.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.com.gigo.dtos.RateInputDto;
import vn.com.gigo.entities.Account;
import vn.com.gigo.entities.Product;
import vn.com.gigo.entities.Rate;
import vn.com.gigo.mapstruct.RateMapper;
import vn.com.gigo.repositories.AccountRepository;
import vn.com.gigo.repositories.ProductRepository;
import vn.com.gigo.repositories.RateRepository;
import vn.com.gigo.security.SecurityUtils;
import vn.com.gigo.services.RateService;

@Service
public class RateServiceImpl implements RateService{
	@Autowired
	private RateRepository rateRepo;
	
	@Autowired
	private ProductRepository productRepo;
	
	@Autowired
	private AccountRepository accountRepo;
	
	@Autowired
	private RateMapper rateMapper;

	@Override
	public Object add(RateInputDto rateInputDto) {
		Rate rateToAdd = rateMapper.rateInputDtoToRate(rateInputDto);
		Product product = productRepo.findOneById(rateInputDto.getProduct());
		String loggedUser = SecurityUtils.getLoggedUsername();
		Account user = accountRepo.findOneByUsername(loggedUser);
		rateToAdd.setProduct(product);
		rateToAdd.setUser(user);
		return rateMapper.rateToRateDto(rateRepo.save(rateToAdd));
	}

	@Override
	public Object getRatesByUsername() {
		String loggedUser = SecurityUtils.getLoggedUsername();
		return rateRepo.findProduct_IdByUser_Username(loggedUser);
		
	}
}
