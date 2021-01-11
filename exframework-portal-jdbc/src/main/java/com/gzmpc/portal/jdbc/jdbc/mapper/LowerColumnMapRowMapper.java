package com.gzmpc.portal.jdbc.jdbc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.jdbc.core.ColumnMapRowMapper;

public class LowerColumnMapRowMapper extends ColumnMapRowMapper {

	@Override
	public Map<String, Object> mapRow(ResultSet rs, int rowNum) throws SQLException {
		Map<String, Object> mapOfColValues = super.mapRow(rs, rowNum);
		Map<String, Object> result = new LinkedHashMap<String, Object>();
		for(String key:mapOfColValues.keySet()) {
			result.put(key.toLowerCase(), mapOfColValues.get(key));
		}
		return result;
	}
}
