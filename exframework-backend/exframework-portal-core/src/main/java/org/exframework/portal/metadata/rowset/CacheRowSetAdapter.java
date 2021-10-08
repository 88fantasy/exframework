package org.exframework.portal.metadata.rowset;

import org.exframework.support.common.util.SpringContextUtils;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.stereotype.Component;

import javax.sql.rowset.CachedRowSet;
import java.sql.SQLException;

/**
* @author rwe
* @version 创建时间：Apr 4, 2020 2:53:45 PM
* 数据库分页器适配类
*/

@Component
public class CacheRowSetAdapter {
	
	public final static String DEFAULT = "mysqlDbType";
	
	public CachedRowSet retSet() throws SQLException {
		return retSet(DEFAULT);
	}
	
	public CachedRowSet retSet(String dbType) throws SQLException {
		CachedRowSet instance = SpringContextUtils.getBeanById(dbType, CachedRowSet.class);
		if(instance == null) {
			throw new NoSuchBeanDefinitionException("未定义数据库类型["+dbType+"]的分页类:"+dbType);
		}
		return instance;
	}
}
