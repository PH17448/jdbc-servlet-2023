package com.laptrinhjavaweb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.laptrinhjavaweb.model.NewsModel;

public class NewMapper implements RowMapper<NewsModel> {

	public NewsModel MapRow(ResultSet result) {
		try {
			NewsModel news = new NewsModel();
			news.setId(result.getLong("id"));
			news.setThumbnail(result.getString("thumbnail"));
			news.setTitle(result.getString("title"));
			news.setContent(result.getString("content"));
			news.setShortDescription(result.getString("shortdescription"));
			news.setCreatedDate(result.getTimestamp("createdDate"));
			news.setCreatedBy(result.getString("createBy"));
			news.setCategoryId(result.getLong("categoryid"));
			if(result.getTimestamp("modifiedDate") != null) {
				news.setModifiedDate(result.getTimestamp("modifiedDate"));
			}
			if(result.getString("modifiedBy") != null) {
				news.setModifiedBy(result.getString("modifiedBy"));
			}
			return news ;
		}catch(SQLException e) {
			return null ;
		}
	}
	
}
