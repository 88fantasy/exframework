package com.gzmpc.portal.grid;

import java.sql.Connection;
import java.util.Map;

import com.gzmpc.portal.metadata.sys.Account;

/***
 * 数据表格查询数据后进行一些特殊处理,可用于改善一些sql难以实现的算法和对数据再加工
 */
public interface IDataAfterQuery {
	
	public void afterQuery(Connection con, Account account, Map<String, Object> params, Map<String,Object> data);

}
