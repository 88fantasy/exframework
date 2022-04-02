package org.exframework.spring.boot.canal.client.interfaces;

import com.alibaba.otter.canal.protocol.CanalEntry;

/**
 * 数据库操作接口层
 *
 * @author rwe
 * @date 2022/3/14 19:35
 */
@FunctionalInterface
public interface IDBOption {
	
	/**
	 * 操作
	 *
	 * @param destination 指令
	 * @param schemaName  实例名称
	 * @param tableName   表名称
	 * @param rowChange     数据
	 * @return
	 * 
	 */
	void doOption(String destination, String schemaName, String tableName, CanalEntry.RowChange rowChange);
}
