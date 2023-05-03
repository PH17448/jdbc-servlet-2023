package com.laptrinhjavaweb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.laptrinhjavaweb.model.CategoryModel;

public class CategoryMapper implements RowMapper<CategoryModel> {

	public CategoryModel MapRow(ResultSet result) {
		try {
			CategoryModel category = new CategoryModel();
			category.setId(result.getLong("id"));
			category.setName(result.getString("name"));
			category.setCode(result.getString("code"));
			return category ;
		}catch(SQLException e) {
			return null ;
		}
	}

}
