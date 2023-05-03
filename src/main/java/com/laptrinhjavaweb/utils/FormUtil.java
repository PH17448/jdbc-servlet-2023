package com.laptrinhjavaweb.utils;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;

public class FormUtil {
	@SuppressWarnings("unchecked")
	public static <Entity> Entity toModel(Class<Entity> entity , HttpServletRequest request) {
		Entity object = null ;
		try {
			object = entity.newInstance();
			BeanUtils.populate(object,request.getParameterMap());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return object;
	}
}
