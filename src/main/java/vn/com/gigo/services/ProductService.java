package vn.com.gigo.services;
import vn.com.gigo.dtos.request.ProductRequestDto;

public interface ProductService {
	Object getAllPagnation(int offSet, int limit, String sortBy, Boolean asc);

	Object getById(Long id);

	Object add(ProductRequestDto productDto);

	Object update(Long id, ProductRequestDto productDto);
	
	Object getAllProductsByCategoryId(Long id,int offSet,int limit);
	
	Object searchByName(String search,int offSet,int limit);
	
	Object updateStatus(Long id);
	
	Object getAllProduct();
	
	Object search(String keyword);
	
	Object getProductsByCategoryId(Long id);
	
	Object getBestSeller();
	
	Object getProductsForYou();
	
	Object getCombo();
	
	Object getAvailable();
}
