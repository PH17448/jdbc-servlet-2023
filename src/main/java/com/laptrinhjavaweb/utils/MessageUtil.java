package com.laptrinhjavaweb.utils;

import javax.servlet.http.HttpServletRequest;

import com.laptrinhjavaweb.constant.SystemConstant;

public class MessageUtil {
	public static void getMessage(HttpServletRequest request,String paramater) {
		if(request.getParameter(paramater) != null) {
			String messageResponse = "";
			String alert = "";
			String message = request.getParameter(paramater);
			if(message.equals(SystemConstant.INSERT_SUCCESS)) {
				messageResponse = "Insert Success !";
				alert = "success";
			}else if(message.equals(SystemConstant.UPDATE_SUCCESS)) {
				messageResponse ="Update Success !";
				alert = "success";
			}else if(message.equals(SystemConstant.DELETE_SUCCESS)) {
				messageResponse = "Delete success !";
				alert ="success";
			}else if(message.equals(SystemConstant.ERROR_SYSTEM)) {
				messageResponse = "Error System !" ;
				alert = "danger";
			}
			request.setAttribute("messageResponse",messageResponse);
			request.setAttribute("alert",alert);
		}
	}
}
