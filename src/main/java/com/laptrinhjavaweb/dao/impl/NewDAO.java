package com.laptrinhjavaweb.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.laptrinhjavaweb.dao.INewDAO;
import com.laptrinhjavaweb.mapper.NewMapper;
import com.laptrinhjavaweb.model.NewsModel;
import com.laptrinhjavaweb.paging.Pageble;

public class NewDAO extends AbstractDAO<NewsModel> implements INewDAO {
	
	
	public List<NewsModel> findByCategoryId(Long id) {
		String sql = "SELECT * FROM news WHERE categoryid = ?";
		return query(sql, new NewMapper(),id);
	}

	@Override
	public Long save(NewsModel newModel) {
		StringBuilder sql = new StringBuilder("INSERT INTO news(title,content,");
		sql.append(" categoryid,thumbnail,shortdescription,createdDate,createBy)");
		sql.append(" VALUES(?,?,?,?,?,?,?)");
		return insert(sql.toString(), newModel.getTitle(),
				 newModel.getContent(),newModel.getCategoryId(),newModel.getThumbnail(),
				newModel.getShortDescription(),newModel.getCreatedDate(),newModel.getCreatedBy() 
				);
	}

	@Override
	public NewsModel findOne(Long id) {
		String sql = "SELECT * FROM news WHERE id = ?";
		List<NewsModel> list = query(sql, new NewMapper(),id);
		return list.isEmpty() ? null : list.get(0) ;
	}

	@Override
	public void update(NewsModel updateNew) {
		StringBuilder sql = new StringBuilder("UPDATE news SET title = ?, thumbnail = ?,");
		sql.append(" shortdescription = ?, content = ?, categoryid = ?,");
		sql.append(" createdDate = ?, createBy = ?, modifiedDate = ?, modifiedBy = ? WHERE id = ?");
		update(sql.toString(), updateNew.getTitle(), updateNew.getThumbnail(), updateNew.getShortDescription(),
				updateNew.getContent(), updateNew.getCategoryId(),updateNew.getCreatedDate(), 
				updateNew.getCreatedBy(), updateNew.getModifiedDate(), 
				updateNew.getModifiedBy()
				, updateNew.getId());
	}

	@Override
	public void delete(Long id) {
		String sql = "DELETE FROM news WHERE id = ?";
		update(sql,id);
	}

	@Override
	public List<NewsModel> findAll(Pageble pageble) {
//		String sql = "SELECT * FROM news LIMIT ?, ?";
		StringBuilder sql = new StringBuilder("SELECT * FROM news");
		
		if(pageble.getSorter() != null &&
				StringUtils.isNotBlank(pageble.getSorter().getSortName())
				&& StringUtils.isNotBlank(pageble.getSorter().getSortBy())
				) {
			sql.append(" ORDER BY "+ pageble.getSorter().getSortName() 
					+" "+pageble.getSorter().getSortBy()+"");
		}
		if(pageble.getOffset() != null && pageble.getLimit() != null) {
			sql.append(" LIMIT "+pageble.getOffset()+", "+pageble.getLimit()+"");
		}
		System.out.println(query(sql.toString(),new NewMapper()));
		return query(sql.toString(),new NewMapper());
	}

	@Override
	public int getTotalItem() {
		String sql = "SELECT count(id) FROM news";
		return count(sql);
	}

}
