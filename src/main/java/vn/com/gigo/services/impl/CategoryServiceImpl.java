package vn.com.gigo.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.com.gigo.dtos.response.CategoryDto;
import vn.com.gigo.entities.Category;
import vn.com.gigo.exception.ResourceNotFoundException;
import vn.com.gigo.mapstruct.CategoryMapper;
import vn.com.gigo.repositories.CategoryRepository;
import vn.com.gigo.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	CategoryRepository categoryRepo;
	
	@Autowired
	CategoryMapper mapper;

	@Override
	public Object getAll() {
		return mapper.categorysToCategoryDtos(categoryRepo.findAll());
	}

	@Override
	public Object getById(Long id) {
		Category category = categoryRepo.findById(id).orElseGet(null);
		if (category != null) {
			return mapper.categoryToCategoryDto(category);
		}
		throw new ResourceNotFoundException("Category with id " + id + " doest not exist");
	}

	@Override
	public Object add(CategoryDto categoryDto) {
		Category cateToAdd = mapper.categoryDtoToCategory(categoryDto);
		if(cateToAdd.getStatus() == null) {
			cateToAdd.setStatus(true);
		}
		return mapper.categoryToCategoryDto(categoryRepo.save(cateToAdd));
	}

	@Override
	public Object update(Long id, CategoryDto categoryDto) {
		Optional<Category> categoryOptional = categoryRepo.findById(id);
		if(categoryOptional.isPresent()) {
			Category category = categoryOptional.get();
			category.setName(categoryDto.getName());
			if(categoryDto.getStatus() != null) {
				category.setStatus(categoryDto.getStatus());
			}
			return mapper.categoryToCategoryDto(categoryRepo.save(category));
		}
		else return null;
	}

	@Override
	public Object updateStatus(Long id) {
		Optional<Category> categoryOptional = categoryRepo.findById(id);
		if(categoryOptional.isPresent()) {
			Category category = categoryOptional.get();
			category.setStatus(false);
			return mapper.categoryToCategoryDto(categoryRepo.save(category));
		}
		else return null;
	}

	@Override
	public Object getAvailable() {
		return mapper.categorysToCategoryDtos(categoryRepo.getAvailableCategory());
	}
	
}
