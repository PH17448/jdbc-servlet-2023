package com.laptrinhjavaweb.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.inject.Inject;

import com.laptrinhjavaweb.dao.ICategoryDAO;
import com.laptrinhjavaweb.dao.INewDAO;
import com.laptrinhjavaweb.model.CategoryModel;
import com.laptrinhjavaweb.model.NewsModel;
import com.laptrinhjavaweb.paging.Pageble;
import com.laptrinhjavaweb.service.INewService;

public class NewService implements INewService {
	
	@Inject
	private INewDAO newsDao ;
	
	
	
	@Inject ICategoryDAO categoryDAO;
	
	public List<NewsModel> findByCategoryId(Long id) {
		return newsDao.findByCategoryId(id);
	}

	@Override
	public NewsModel save(NewsModel newModel) {
		newModel.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		CategoryModel categoryModel = categoryDAO.findOneByCode(newModel.getCategoryCode());
		newModel.setCategoryId(categoryModel.getId());
		Long newId = newsDao.save(newModel);
		return newsDao.findOne(newId);
	}

	@Override
	public NewsModel update(NewsModel updateNew) {
		NewsModel oldNewsModel = newsDao.findOne(updateNew.getId()) ;
		updateNew.setCreatedDate(oldNewsModel.getCreatedDate());
		updateNew.setCreatedBy(oldNewsModel.getCreatedBy());
		updateNew.setModifiedDate(new Timestamp(System.currentTimeMillis()));
		CategoryModel categoryModel = categoryDAO.findOneByCode(updateNew.getCategoryCode());
		updateNew.setCategoryId(categoryModel.getId());
		newsDao.update(updateNew);
		return newsDao.findOne(updateNew.getId());
	}

	@Override
	public void delete(long[] ids) {
		for (long id : ids) {
			newsDao.delete(id);
		}
	}

	@Override
	public List<NewsModel> findAll(Pageble pageble) {
		return newsDao.findAll(pageble);
	}

	@Override
	public int getTotalItem() {
		return newsDao.getTotalItem();
	}

	@Override
	public NewsModel findOne(long id) {
		NewsModel newsModel = newsDao.findOne(id);
		CategoryModel categoryModel = categoryDAO.findOne(newsModel.getCategoryId());
		newsModel.setCategoryCode(categoryModel.getCode());
		return newsModel;
	}
	
}
