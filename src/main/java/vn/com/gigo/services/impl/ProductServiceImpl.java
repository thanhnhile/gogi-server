package vn.com.gigo.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import vn.com.gigo.dtos.PagingDto;
import vn.com.gigo.dtos.ProductDto;
import vn.com.gigo.entities.Category;
import vn.com.gigo.entities.Product;
import vn.com.gigo.mapstruct.ProductMapper;
import vn.com.gigo.repositories.CategoryRepository;
import vn.com.gigo.repositories.ProductRepository;
import vn.com.gigo.services.ProductService;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductRepository productRepo;
	
	@Autowired
	private CategoryRepository categoryRepo;
	
	@Autowired
	private ProductMapper mapper;

	@Override
	public Object getById(Long id) {
		return mapper.productToProductDto(productRepo.getReferenceById(id));
	}

	@Override
	public Object add(ProductDto productDto) {
		if(productDto.getDiscount() == null) {
			productDto.setDiscount(0.0);
		}
		if(productDto.getStatus() == null) {
			productDto.setStatus(true);
		}
		Category category = categoryRepo.getReferenceById(productDto.getCategory().getId());
		Product productToAdd = mapper.productDtoToProduct(productDto);
		productToAdd.setCategory(category);
		return mapper.productToProductDto(productRepo.save(productToAdd));
	}

	@Override
	public Object update(Long id, ProductDto productDto) {
		Optional<Product> productOptional = productRepo.findById(id);
		if(productOptional.isPresent()) {
			productDto.setId(id);
			Product productToUpdate = mapper.productDtoToProduct(productDto);
			return mapper.productToProductDto(productToUpdate);
		}
		else return null;
	}

	@Override
	public Object getAllPagnation(int offSet, int limit, String sortBy, Boolean asc) {
		Pageable pageable;
		if(asc) {
			pageable = PageRequest.of(offSet-1, limit, Sort.by(sortBy).ascending());
		}
		else {
			pageable = PageRequest.of(offSet-1, limit, Sort.by(sortBy).descending());
		}
		Page<Product> pageProduct = productRepo.findAll(pageable);
		PagingDto response = new PagingDto();
		response.setContent(mapper.productsToProductDtos(pageProduct.getContent()));
		response.setTotalElements(pageProduct.getTotalElements());
		response.setTotalPages(pageProduct.getTotalPages());
		response.setCurrentPage(offSet);
		return response;
	}



}
