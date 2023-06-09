package com.laptrinhjavaweb.dao.impl;

import java.util.List;

import com.laptrinhjavaweb.dao.ICategoryDAO;
import com.laptrinhjavaweb.mapper.CategoryMapper;
import com.laptrinhjavaweb.model.CategoryModel;

public class CategoryDAO extends AbstractDAO<CategoryModel> implements ICategoryDAO {

	
	@Override
	public List<CategoryModel> findAll() {
		String sql = "SELECT * FROM category";
		return query(sql, new CategoryMapper());
	}

	@Override
	public CategoryModel findOne(Long id) {
		String sql = "SELECT * FROM category WHERE id=?";
		List<CategoryModel> list = query(sql, new CategoryMapper(), id);
		return list.isEmpty() ? null : list.get(0);
	}

	@Override
	public CategoryModel findOneByCode(String categoryCode) {
		String sql = "SELECT * FROM category WHERE code=?";
		List<CategoryModel> list = query(sql, new CategoryMapper(), categoryCode);
		return list.isEmpty() ? null : list.get(0);
	}

}
