package com.laptrinhjavaweb.controller.admin.api;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.laptrinhjavaweb.model.NewsModel;
import com.laptrinhjavaweb.model.UserModel;
import com.laptrinhjavaweb.service.INewService;
import com.laptrinhjavaweb.utils.HttpUtil;
import com.laptrinhjavaweb.utils.SessionUtil;

@WebServlet(urlPatterns = "/api-admin-new")
public class NewAPI extends HttpServlet {

	@Inject
	private INewService newService;

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		NewsModel newModel = HttpUtil.of(req.getReader()).toModel(NewsModel.class);
		newModel.setCreatedBy(( (UserModel) SessionUtil.getInstance().getValue(req, "USERMODEL")).getUserName());
		newModel = newService.save(newModel);
		mapper.writeValue(resp.getOutputStream(), newModel);
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();

		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		NewsModel updateNews = HttpUtil.of(req.getReader()).toModel(NewsModel.class);
		updateNews.setCreatedBy(( (UserModel) SessionUtil.getInstance().getValue(req, "USERMODEL")).getUserName());
		updateNews = newService.update(updateNews);

		mapper.writeValue(resp.getOutputStream(), updateNews);
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();

		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		
		
		NewsModel newsModel = HttpUtil.of(req.getReader()).toModel(NewsModel.class);
		newService.delete(newsModel.getIds());
		mapper.writeValue(resp.getOutputStream(), "{}");
	}

	
}
