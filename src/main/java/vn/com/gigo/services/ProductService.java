package vn.com.gigo.services;

import vn.com.gigo.dtos.ProductDto;

public interface ProductService {
	Object getAllPagnation(int offSet, int limit, String sortBy, Boolean asc);

	Object getById(Long id);

	Object add(ProductDto productDto);

	Object update(Long id, ProductDto productDto);
	
	Object getAllProductsByCategoryId(Long id,int offSet,int limit);
	
	Object searchByName(String search,int offSet,int limit);
}
