package org.exframework.portal.grid;

import org.exframework.portal.exception.InitException;
import org.exframework.portal.metadata.sys.Account;
import org.exframework.support.common.exception.BuildException;

import java.util.List;
import java.util.Map;

public interface IDataProvider {
	public Map<String, Object> getJsonData(String gridcode, Map<String, Object> params, Account account) throws InitException,BuildException;

	public List<Map<String, Object>> retGridInfo(String gridcode, Account account, Map<String,Object> params) ;// 获取表格信息

	/*
	 * 返回生成的文件路径
	 */
	public String doDownLoad(String gridcode, Map<String, Object> params, Account account) throws InitException,BuildException ;
}
