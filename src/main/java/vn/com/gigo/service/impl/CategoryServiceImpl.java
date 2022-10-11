package vn.com.gigo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.com.gigo.dto.CategoryDto;
import vn.com.gigo.mapstruct.CategoryMapper;
import vn.com.gigo.repository.CategoryRepository;
import vn.com.gigo.service.CategoryService;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object add(CategoryDto categoryDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object update(Long id, CategoryDto categoryDto) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
