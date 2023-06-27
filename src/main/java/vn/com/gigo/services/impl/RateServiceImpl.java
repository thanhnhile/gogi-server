package vn.com.gigo.services.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.com.gigo.dtos.request.RateRequestDto;
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
	public Object add(RateRequestDto rateInputDto) {
		if(checkProductHasRated(rateInputDto.getProduct())) {
			return "The Product has rated by user";
		}
		Rate rateToAdd = rateMapper.rateInputDtoToRate(rateInputDto);
		Product product = productRepo.findOneById(rateInputDto.getProduct());
		String loggedUser = SecurityUtils.getLoggedUsername();
		Account user = accountRepo.findOneByUsername(loggedUser);
		rateToAdd.setProduct(product);
		rateToAdd.setUser(user);
		return rateMapper.rateToRateDto(rateRepo.save(rateToAdd));
	}
	
	private Boolean checkProductHasRated(Long productId) {
		@SuppressWarnings("unchecked")
		List<Long> ratedProduct = (List<Long>) getRatesByUsername();
		return ratedProduct.contains(productId);
		
	}

	@Override
	public Object getRatesByUsername() {
		String loggedUser = SecurityUtils.getLoggedUsername();
		return rateRepo.findProduct_IdByUser_Username(loggedUser);
		
	}

	@Override
	public Object getRatesByProductId(Long id) {
		return rateMapper.ratesToRateDtos(rateRepo.findByProductId(id));
	}

	@Override
	public Boolean checkExisted(Long id) {
		@SuppressWarnings("unchecked")
		List<Long> ratedProduct = (List<Long>) getRatesByUsername();
		return ratedProduct.contains(id);
	}
}
