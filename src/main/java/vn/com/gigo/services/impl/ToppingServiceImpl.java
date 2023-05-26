package vn.com.gigo.services.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.com.gigo.dtos.ToppingDto;
import vn.com.gigo.entities.Product;
import vn.com.gigo.entities.Topping;
import vn.com.gigo.mapstruct.ToppingMapper;
import vn.com.gigo.repositories.ToppingRepository;
import vn.com.gigo.services.ToppingService;

@Service
@Transactional
public class ToppingServiceImpl implements ToppingService{
	
	@Autowired
	ToppingRepository toppingRepo;
	
	@Autowired
	ToppingMapper mapper;

	@Override
	public Object getAll() {
		return mapper.toppingsToToppingDtos(toppingRepo.findAll());
	}

	@Override
	public Object getById(Long id) {
		Optional<Topping> topping = toppingRepo.findById(id);
		return mapper.toppingToToppingDto(topping.get());
	}

	@Override
	public Object add(ToppingDto toppingDto) {
		Topping toppingToAdd = mapper.toppingDtoToTopping(toppingDto);
		if(toppingToAdd.getStatus() == null) {
			toppingToAdd.setStatus(true);
		}
		return mapper.toppingToToppingDto(toppingRepo.save(toppingToAdd));
	}

	@Override
	public Object update(Long id, ToppingDto toppingDto) {
		Optional<Topping> toppingOptional = toppingRepo.findById(id);
		if(toppingOptional.isPresent()) {
			Topping topping = toppingOptional.get();
			topping.setName(toppingDto.getName());
			if(toppingDto.getStatus() != null) {
				topping.setStatus(toppingDto.getStatus());
			}
			return mapper.toppingToToppingDto(toppingRepo.save(topping));
		}
		else return null;
	}

	@Override
	public Object updateStatus(Long id) {
		Optional<Topping> toppingOptional = toppingRepo.findById(id);
		if(toppingOptional.isPresent()) {
			Topping topping = toppingOptional.get();
			topping.setStatus(false);
			return mapper.toppingToToppingDto(toppingRepo.save(topping));
		}
		else return null;
	}

	@Override
	public Object getByOrderDetailId(Long id) {
		List<Topping> Toppinglist = toppingRepo.getToppingByOrderDetailId(id);
		return mapper.toppingsToToppingDtos(Toppinglist);
	}
	
}
