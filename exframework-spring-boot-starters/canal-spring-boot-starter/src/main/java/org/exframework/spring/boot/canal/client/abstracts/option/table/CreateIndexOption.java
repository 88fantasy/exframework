package org.exframework.spring.boot.canal.client.abstracts.option.table;

import com.alibaba.otter.canal.protocol.CanalEntry;
import org.exframework.spring.boot.canal.client.abstracts.option.AbstractDBOption;

/**
 * 創建索引操作
 *
 * @author rwe
 * @date 2022/3/14 19:35
 */
public abstract class CreateIndexOption extends AbstractDBOption {
	/**
	 * 創建索引操作
	 *
	 */
	@Override
	protected void setEventType() {
		this.eventType = CanalEntry.EventType.CINDEX;
	}
}
