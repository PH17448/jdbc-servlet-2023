package com.laptrinhjavaweb.dao.impl;

import java.util.List;

import com.laptrinhjavaweb.dao.IUserDAO;
import com.laptrinhjavaweb.mapper.UserMapper;
import com.laptrinhjavaweb.model.UserModel;

public class UserDAO extends AbstractDAO<UserModel> implements IUserDAO {

	@Override
	public UserModel findByUserNameAndPasswordAndStatus(String username, String password, Integer status) {
		StringBuilder sql = new StringBuilder("SELECT * FROM user AS u");
		sql.append(" INNER JOIN role AS r ON r.id = u.roleid");
		sql.append(" WHERE username = ? AND password = ? AND status = ?");
		
		List<UserModel> list = query(sql.toString(), new UserMapper() ,username,password,status);
		return list.isEmpty() ? null : list.get(0);
	}
	
}
