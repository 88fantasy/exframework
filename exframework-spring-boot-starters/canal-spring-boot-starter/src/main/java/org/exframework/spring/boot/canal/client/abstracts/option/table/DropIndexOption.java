package org.exframework.spring.boot.canal.client.abstracts.option.table;

import com.alibaba.otter.canal.protocol.CanalEntry;
import org.exframework.spring.boot.canal.client.abstracts.option.AbstractDBOption;

/**
 * 刪除索引操作
 *
 * @author rwe
 * @date 2022/3/14 19:35
 */
public abstract class DropIndexOption extends AbstractDBOption {
	/**
	 * 刪除索引
	 *
	 */
	@Override
	protected void setEventType() {
		this.eventType = CanalEntry.EventType.DINDEX;
	}
}
