package vn.com.gigo.services;

import vn.com.gigo.dtos.ToppingDto;

public interface ToppingService {
	Object getAll();

	Object getById(Long id);

	Object getByOrderDetailId(Long id);
	
	Object add(ToppingDto toppingDto);

	Object update(Long id, ToppingDto toppingDto);

	Object updateStatus(Long id);
}
