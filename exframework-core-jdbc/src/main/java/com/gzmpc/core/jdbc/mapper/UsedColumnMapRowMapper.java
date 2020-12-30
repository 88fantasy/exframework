package com.gzmpc.core.jdbc.mapper;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang3.time.FastDateFormat;
import org.springframework.jdbc.core.ColumnMapRowMapper;

public class UsedColumnMapRowMapper extends ColumnMapRowMapper {

	@Override
	public Map<String, Object> mapRow(ResultSet rs, int rowNum) throws SQLException {
		ResultSetMetaData rsmd = rs.getMetaData();
		int columnCount = rsmd.getColumnCount();
		Map<String, Object> mapOfColValues = new LinkedHashMap<String,Object>(columnCount);
		for (int i = 1; i <= columnCount; i++) {
			String key = rsmd.getColumnLabel(i).toLowerCase();
			Object obj = getColumnValue(rs, i);
			int type = rsmd.getColumnType(i);
			int size = rsmd.getScale(i);
			if (obj == null || "".equals(obj)) {
			      obj =  "";
		    }
			else if (type == Types.DATE || type == Types.TIME || type == Types.TIMESTAMP) {
		      String value = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss").format(obj);
		      if (value.endsWith("00:00:00")) {
		        value = value.substring(0, 10);
		      }
		      obj = value;
		    }
			else if (type == Types.NUMERIC && obj instanceof BigDecimal) {
				BigDecimal b = (BigDecimal)obj;
				b.setScale(size, RoundingMode.HALF_UP);
				obj = b.toString();
		    }
			mapOfColValues.put(key, obj);
		}
		return mapOfColValues;
	}


}
