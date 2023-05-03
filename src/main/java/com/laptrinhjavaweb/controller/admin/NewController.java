package com.laptrinhjavaweb.controller.admin;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.laptrinhjavaweb.constant.SystemConstant;
import com.laptrinhjavaweb.model.NewsModel;
import com.laptrinhjavaweb.paging.PageRequest;
import com.laptrinhjavaweb.paging.Pageble;
import com.laptrinhjavaweb.service.ICategoryService;
import com.laptrinhjavaweb.service.INewService;
import com.laptrinhjavaweb.sort.Sorter;
import com.laptrinhjavaweb.utils.FormUtil;
import com.laptrinhjavaweb.utils.MessageUtil;

@WebServlet(urlPatterns = { "/admin-new" })
public class NewController extends HttpServlet {

	@Inject
	private INewService newService;

	@Inject
	private ICategoryService categoryService;
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		NewsModel model = FormUtil.toModel(NewsModel.class, request);
		String views = "";
		if (model.getType().equals(SystemConstant.LIST)) {
			Pageble pageble = new PageRequest(model.getPage(), model.getMaxPageItem(),
					new Sorter(model.getSortName(), model.getSortBy()));
			model.setListResult(newService.findAll(pageble));
			model.setTotalItem(newService.getTotalItem());
			model.setTotalPage((int) Math.ceil((double)model.getTotalItem() / (double)model.getMaxPageItem()));
			views = "/views/admin/new/list.jsp";
		}else if(model.getType().equals(SystemConstant.EDIT)) {
			if(model.getId() != null) {
				model = newService.findOne(model.getId());
			}
			request.setAttribute("categories", categoryService.findAll());
			views = "/views/admin/new/edit.jsp";
		}
		MessageUtil.getMessage(request,"message");
		
		request.setAttribute(SystemConstant.MODEL, model);
		RequestDispatcher rd = request.getRequestDispatcher(views);
		rd.forward(request, response);
//		Pageble pageble = new PageRequest(model.getPage(), model.getMaxPageItem(),
//				new Sorter(model.getSortName(), model.getSortBy()));
//    	model.setListResult(newService.findAll(pageble));
//		model.setTotalItem(newService.getTotalItem());
//		model.setTotalPage((int) Math.ceil(model.getTotalItem() / model.getMaxPageItem()));
//		request.setAttribute(SystemConstant.MODEL, model);
// 		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/new/list.jsp");
// 		rd.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}
}
