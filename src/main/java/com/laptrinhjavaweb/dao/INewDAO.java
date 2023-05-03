package com.laptrinhjavaweb.dao;

import java.util.List;

import com.laptrinhjavaweb.model.NewsModel;
import com.laptrinhjavaweb.paging.Pageble;

public interface INewDAO extends IGenericDAO<NewsModel>  {
	List<NewsModel> findByCategoryId(Long id);
	Long save(NewsModel newModel);
	NewsModel findOne(Long id);
	void update(NewsModel updateNews) ;
	void delete(Long id);
	List<NewsModel> findAll(Pageble pageble);
	int getTotalItem();
}
