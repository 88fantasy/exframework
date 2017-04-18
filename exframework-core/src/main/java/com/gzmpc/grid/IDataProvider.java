package com.gzmpc.grid;

import java.util.List;
import java.util.Map;

import com.gzmpc.exception.InitException;
import com.gzmpc.exception.ProcessException;
import com.gzmpc.metadata.sys.Account;

public interface IDataProvider {
	public Map<String, Object> getJsonData(String gridcode, Map<String, Object> params, Account account) throws InitException,ProcessException;

	public List<Map<String, Object>> retGridInfo(String gridcode, Account account, Map<String,Object> params) ;// 获取表格信息

	/*
	 * 返回生成的文件路径
	 */
	public String doDownLoad(String gridcode, Map<String, Object> params, Account account) throws InitException,ProcessException ;
}
