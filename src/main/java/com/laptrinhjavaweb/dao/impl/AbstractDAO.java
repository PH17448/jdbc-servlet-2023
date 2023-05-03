package com.laptrinhjavaweb.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.laptrinhjavaweb.dao.IGenericDAO;
import com.laptrinhjavaweb.mapper.RowMapper;

public class AbstractDAO<EntityKey> implements IGenericDAO<EntityKey> {
	
	ResourceBundle resource = ResourceBundle.getBundle("db");
	
	public  Connection getConnection() {
		try {
//			Class.forName("com.mysql.cj.jdbc.Driver");
//			String url = "jdbc:mysql://localhost:3306/newservletjdbc2023";
//			String username = "root";
//			String password = "123456";
			Class.forName(resource.getString("driverName"));
			String url = resource.getString("url");
			String username = resource.getString("username");
			String password = resource.getString("password");
			return DriverManager.getConnection(url,username, password);
		}catch(ClassNotFoundException | SQLException e ) {
			return null ;
		}
	}

	public  List<EntityKey> query(String sql, 
			RowMapper<EntityKey> rowMapper, Object... paramater) {
		List<EntityKey> list = new ArrayList<EntityKey>();
		ResultSet resultSet = null ;
		Connection conn = null;
		PreparedStatement pstm = null ;
		try {
			conn = getConnection();
			pstm = conn.prepareStatement(sql);
			setParameter(pstm,paramater);
			resultSet = pstm.executeQuery();
			while(resultSet.next()) {
				list.add(rowMapper.MapRow(resultSet));
			}
			return list;
		}catch(SQLException e) {
			return null;
		}finally {
			try {
				if(conn != null) {
					conn.close();
				}else if(pstm != null) {
					pstm.close();
				}else if(resultSet != null) {
					resultSet.close();
				}
			}catch(SQLException e2) {
				e2.printStackTrace();
			}
		}
		
	}

	@SuppressWarnings("unused")
	private void setParameter(PreparedStatement pstm, Object... paramater) {
		try {
			for (int i = 0; i < paramater.length; i++) {
				Object object = paramater[i];
				int index = i+1 ;
				if(object instanceof Long) {
					pstm.setLong(index, (Long) object);
				}else if(object instanceof String) {
					pstm.setString(index, (String) object ); 
				}else if(object instanceof Integer) {
					pstm.setInt(index, (Integer) object);
				}else if(object instanceof Timestamp) {
					pstm.setTimestamp(index, (Timestamp) object);
				}else if(object == null) {
					pstm.setNull(index,Types.NULL);
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(String sql, Object... parameter) {
		Connection connection = null;
		PreparedStatement statement  =null;
		try {
			connection = getConnection();
			connection.setAutoCommit(false);
			statement = connection.prepareStatement(sql);
			setParameter(statement, parameter);
			statement.executeUpdate();
			connection.commit();
		}catch(SQLException e) {
			if(connection != null) {
				try {
					connection.rollback();
				}catch(SQLException e1) {
					e1.printStackTrace();
				}
			}
		}finally {
			try {
				if(connection != null) {
					connection.close();
				}else if(statement != null) {
					statement.close();
				}
			}catch(SQLException e2) {
				e2.printStackTrace();
			}
		}
	}

	@Override
	public Long insert(String sql, Object... parameter) {
		Connection connection = null;
		ResultSet resultSet = null ;
		PreparedStatement statement  =null;
		try {
			Long id = null ;
			connection = getConnection();
			connection.setAutoCommit(false);
			statement = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS	);
			setParameter(statement, parameter);
			statement.executeUpdate();
			resultSet = statement.getGeneratedKeys();
			if(resultSet.next()) {
				id = resultSet.getLong(1);
			}
			connection.commit();
			return id ;
		}catch(SQLException e) {
			if(connection != null) {
				try {
					connection.rollback();
				}catch(SQLException e1) {
					e1.printStackTrace();
				}
			}
		}finally {
			try {
				if(connection != null) {
					connection.close();
				}else if(statement != null) {
					statement.close();
				}else if(resultSet != null) {
					resultSet.close();
				}
			}catch(SQLException e2) {
				e2.printStackTrace();
			}
		}
		return null ;
	}

	@Override
	public int count(String sql, Object... parameter) {
		ResultSet resultSet = null ;
		Connection connection = null;
		PreparedStatement statement = null ;
		try {
			int count = 0 ;
			connection = getConnection();
			statement = connection.prepareStatement(sql);
			setParameter(statement,parameter);
			resultSet = statement.executeQuery();
			while(resultSet.next()) {
				count = resultSet.getInt(1);
			}
			return count;
		}catch(SQLException e) {
			return 0;
		}finally {
			try {
				if(connection != null) {
					connection.close();
				}else if(statement != null) {
					statement.close();
				}else if(resultSet != null) {
					resultSet.close();
				}
			}catch(SQLException e2) {
				e2.printStackTrace();
			}
		}
	}

	
	
	

	
	
}
