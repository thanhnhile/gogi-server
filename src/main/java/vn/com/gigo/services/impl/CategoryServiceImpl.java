package vn.com.gigo.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.com.gigo.dtos.CategoryDto;
import vn.com.gigo.entities.Category;
import vn.com.gigo.mapstruct.CategoryMapper;
import vn.com.gigo.repositories.CategoryRepository;
import vn.com.gigo.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	CategoryRepository repo;
	
	@Autowired
	CategoryMapper mapper;

	@Override
	public Object getAll() {
		return mapper.categorysToCategoryDtos(repo.findAll());
	}

	@Override
	public Object getById(Long id) {
		Optional<Category> category = repo.findById(id);
		return mapper.categoryToCategoryDto(category.get());
	}

	@Override
	public Object add(CategoryDto categoryDto) {
		Category cateToAdd = mapper.categoryDtoToCategory(categoryDto);
		if(cateToAdd.getStatus() == null) {
			cateToAdd.setStatus(true);
		}
		return mapper.categoryToCategoryDto(repo.save(cateToAdd));
	}

	@Override
	public Object update(Long id, CategoryDto categoryDto) {
		Optional<Category> categoryOptional = repo.findById(id);
		if(categoryOptional.isPresent()) {
			Category category = categoryOptional.get();
			category.setName(categoryDto.getName());
			if(categoryDto.getStatus() == null) {
				category.setStatus(true);
			}
			else {
				category.setStatus(categoryDto.getStatus());
			}
			return mapper.categoryToCategoryDto(repo.save(category));
		}
		else return null;
	}
	
}
