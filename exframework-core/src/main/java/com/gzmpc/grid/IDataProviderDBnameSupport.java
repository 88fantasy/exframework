package com.gzmpc.grid;

import java.util.Map;

/**
 * 表格每次查询或者下载时会判断是否需要调用此接口方法,返回表格的DBname
 */
public interface IDataProviderDBnameSupport {
	public String retDbname(Map<String,Object> params);
}
