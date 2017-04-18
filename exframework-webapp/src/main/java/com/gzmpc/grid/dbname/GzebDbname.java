package com.gzmpc.grid.dbname;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.gzmpc.grid.IDataProviderDBnameSupport;

/**
* @author rwe
* @version 创建时间：2017年2月10日 下午2:51:12
* 类说明
*/

@Component("gzebDbname")
public class GzebDbname implements IDataProviderDBnameSupport {

	@Override
	public String retDbname(Map<String, Object> params) {
		return "ebDao";
	}

}
