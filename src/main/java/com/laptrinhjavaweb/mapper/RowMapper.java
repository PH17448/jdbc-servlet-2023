package com.laptrinhjavaweb.mapper;

import java.sql.ResultSet;

public interface RowMapper<EntityKey> {
	EntityKey MapRow(ResultSet result);
}
