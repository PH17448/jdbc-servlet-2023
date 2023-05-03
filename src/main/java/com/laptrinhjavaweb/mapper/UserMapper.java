package com.laptrinhjavaweb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.laptrinhjavaweb.model.RoleModel;
import com.laptrinhjavaweb.model.UserModel;

public class UserMapper implements RowMapper<UserModel> {

	public UserModel MapRow(ResultSet result) {
		try {
			UserModel users = new UserModel();
			users.setId(result.getLong("id"));
			users.setUserName(result.getString("username"));
			users.setPassword(result.getString("password"));
			users.setFullName(result.getString("fullname"));
			users.setStatus(result.getInt("status"));
			try {
				RoleModel role = new RoleModel();
				role.setCode(result.getString("code"));
				role.setName(result.getString("name"));
				users.setRole(role);
			} catch (Exception e) {
				System.out.print(e.getMessage());
			}
			return users ;
		}catch(SQLException e) {
			return null ;
		}
	}
	
}
