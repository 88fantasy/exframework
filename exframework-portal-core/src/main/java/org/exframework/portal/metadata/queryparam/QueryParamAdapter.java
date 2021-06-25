package org.exframework.portal.metadata.queryparam;

/**
 * 查询适配器
 * 
 */

public enum QueryParamAdapter {

	STRING("s", "QPIString"),
	STRING_UPPER("su", "QPIStringUpper"),
	STRING_LOWER("sl", "QPIStringLower"),
	NUMBER("n", "QPINumber"),
	HOV("h", "QPIHov"),
	DATE("dt", "QPIDate"),
	DDL("ddl", "QPIDdl")
	
	;
	private String type;

	private String bean;

	private QueryParamAdapter(String type, String bean) {
		this.type = type;
		this.bean = bean;
	}

	public String getType() {
		return type;
	}

	public String getBean() {
		return bean;
	}

	
}
