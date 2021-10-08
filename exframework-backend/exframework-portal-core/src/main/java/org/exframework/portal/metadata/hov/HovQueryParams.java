package org.exframework.portal.metadata.hov;
/**
 * 查询条件
 * @author rwe
 * @since Jan 14, 2021
 *
 * Copyright @ 2021 
 * 
 */
public class HovQueryParams {
	
	/**
	 * 提交字段
	 */
	private String key;
	
	/**
	 * 数据字段
	 */
	private String dataIndex;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getDataIndex() {
		return dataIndex;
	}

	public void setDataIndex(String dataIndex) {
		this.dataIndex = dataIndex;
	}
	
}