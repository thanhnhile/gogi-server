package vn.com.gigo.services;

import vn.com.gigo.dtos.response.CategoryDto;

public interface CategoryService {
	Object getAll();
	
	Object getById(Long id);
	
	Object add(CategoryDto categoryDto);
	
	Object update(Long id,CategoryDto categoryDto);
	
	Object updateStatus(Long id);
	
	Object getAvailable();
}
