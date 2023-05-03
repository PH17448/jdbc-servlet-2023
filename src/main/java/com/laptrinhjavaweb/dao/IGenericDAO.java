package com.laptrinhjavaweb.dao;

import java.util.List;

import com.laptrinhjavaweb.mapper.RowMapper;

public interface IGenericDAO<EntityKey> {
	 public  List<EntityKey> query(String sql,RowMapper<EntityKey> rowMapper ,Object... paramater);
	 public void update(String sql , Object... parameter);
	 public Long insert(String sql ,Object... parameter );
	 int count(String sql,Object... parameter);
}
