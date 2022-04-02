package org.exframework.spring.boot.canal.client.core;

import com.alibaba.otter.canal.protocol.CanalEntry;
import org.exframework.spring.boot.canal.client.abstracts.option.AbstractDBOption;
import org.exframework.spring.boot.canal.client.interfaces.CanalEventListener;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 处理 Canal 监听器
 *
 * @author rwe
 * @date 2022/3/14 19:35
 */
@SuppressWarnings("all")
public class DealCanalEventListener implements CanalEventListener {
	
	/**
	 * 頭結點
	 */
	private AbstractDBOption header;
	
	/**
	 * 默認構造方法，必須傳入鏈路
	 *
	 * @param dbOptions
	 * @return
	 */
	public DealCanalEventListener(AbstractDBOption... dbOptions) {
		AbstractDBOption tmp = null;
		for (AbstractDBOption dbOption : dbOptions) {
			if (tmp != null) {
				tmp.setNext(dbOption);
			} else {
				this.header = dbOption;
			}
			tmp = dbOption;
		}
		
	}
	
	public DealCanalEventListener(List<AbstractDBOption> dbOptions) {
		if (CollectionUtils.isEmpty(dbOptions)) {
			return;
		}
		AbstractDBOption tmp = null;
		for (AbstractDBOption dbOption : dbOptions) {
			if (tmp != null) {
				tmp.setNext(dbOption);
			} else {
				this.header = dbOption;
			}
			tmp = dbOption;
		}
	}
	
	/**
	 * 处理数据库的操作
	 *
	 * @param destination
	 * @param schemaName
	 * @param tableName
	 * @param rowChange
	 * @return
	 * @author rwe
	 * @time 2018/5/29 09:43
	 * 
	 */
	@Override
	public void onEvent(String destination, String schemaName, String tableName, CanalEntry.RowChange rowChange) {
		this.header.doChain(destination, schemaName, tableName, rowChange);
	}
	
	
}
