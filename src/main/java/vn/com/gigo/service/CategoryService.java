package vn.com.gigo.service;

import vn.com.gigo.dto.CategoryDto;

public interface CategoryService {
	Object getAll();
	
	Object getById(Long id);
	
	Object add(CategoryDto categoryDto);
	
	Object update(Long id,CategoryDto categoryDto);
}
