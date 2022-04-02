package org.exframework.spring.boot.canal.client.interfaces;

import com.alibaba.otter.canal.protocol.CanalEntry;

/**
 * canal 的事件接口层（表数据改变）
 *
 * @author rwe
 * @date 2022/3/14 19:35
 */
@FunctionalInterface
public interface CanalEventListener {
	
	
	/**
	 * 处理事件
	 *
	 * @param destination 指令
	 * @param schemaName  库实例
	 * @param tableName   表名
	 * @param rowChange   詳細參數
	 * @return
	 * 
	 */
	void onEvent(String destination, String schemaName, String tableName, CanalEntry.RowChange rowChange);
	
}
