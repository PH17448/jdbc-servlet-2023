package com.laptrinhjavaweb.dao;

import java.util.List;

import com.laptrinhjavaweb.model.CategoryModel;

public interface ICategoryDAO extends IGenericDAO<CategoryModel> {
	public List<CategoryModel> findAll();
	public CategoryModel findOne(Long id);
	public CategoryModel findOneByCode(String categoryCode);
}
