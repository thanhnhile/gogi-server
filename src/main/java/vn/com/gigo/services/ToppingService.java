package vn.com.gigo.services;

import java.util.Set;

import vn.com.gigo.dtos.ToppingDto;
import vn.com.gigo.entities.Topping;

public interface ToppingService {
	Object getAll();

	Object getById(Long id);

	Object getByOrderDetailId(Long id);
	
	Object add(ToppingDto toppingDto);

	Object update(Long id, ToppingDto toppingDto);

	Object updateStatus(Long id);
	
	Set<Topping> saveOrderDetaiToppings(Set<ToppingDto> toppingDtos);
}
