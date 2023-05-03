package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.model.UserModel;

public interface IUserService {
	UserModel findByUserNameAndPasswordAndStatus(String username , String password ,Integer status);
}
