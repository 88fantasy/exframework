package com.gzmpc.portal.grid;

import java.util.List;
import java.util.Map;

import com.gzmpc.portal.exception.InitException;
import com.gzmpc.portal.metadata.sys.Account;
import com.gzmpc.support.common.exception.BuildException;

public interface IDataProvider {
	public Map<String, Object> getJsonData(String gridcode, Map<String, Object> params, Account account) throws InitException,BuildException;

	public List<Map<String, Object>> retGridInfo(String gridcode, Account account, Map<String,Object> params) ;// 获取表格信息

	/*
	 * 返回生成的文件路径
	 */
	public String doDownLoad(String gridcode, Map<String, Object> params, Account account) throws InitException,BuildException ;
}
