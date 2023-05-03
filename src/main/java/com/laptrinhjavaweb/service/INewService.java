package com.laptrinhjavaweb.service;

import java.util.List;

import com.laptrinhjavaweb.model.NewsModel;
import com.laptrinhjavaweb.paging.Pageble;

public interface INewService {
	List<NewsModel> findByCategoryId(Long id);
	NewsModel save (NewsModel newModel);
	NewsModel update(NewsModel updateNew);
	void delete(long[] ids);
	List<NewsModel> findAll(Pageble pageble);
	int getTotalItem();
	NewsModel findOne(long id);
}
