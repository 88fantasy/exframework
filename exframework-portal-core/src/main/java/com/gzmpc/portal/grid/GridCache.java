package com.gzmpc.portal.grid;

import java.util.List;
import java.util.Map;

public class GridCache {

	private List<Map<String,Object>> fields; // header,fieldname,fieldtype,data,precision

	public List<Map<String, Object>> getFields() {
		return fields;
	}

	public void setFields(List<Map<String, Object>> fields) {
		this.fields = fields;
	}

	
}
