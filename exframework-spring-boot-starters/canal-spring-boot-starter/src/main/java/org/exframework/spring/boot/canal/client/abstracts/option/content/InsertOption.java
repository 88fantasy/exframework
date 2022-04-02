package org.exframework.spring.boot.canal.client.abstracts.option.content;

import com.alibaba.otter.canal.protocol.CanalEntry;
import org.exframework.spring.boot.canal.client.abstracts.option.AbstractDBOption;

/**
 * 新增数据
 *
 * @author rwe
 * @date 2022/3/14 19:35
 */

public abstract class InsertOption extends AbstractDBOption {
	
	
	/**
	 * 设置新增操作
	 *
	 * @param
	 * @return
	 * 
	 */
	@Override
	protected void setEventType() {
		this.eventType = CanalEntry.EventType.INSERT;
	}
}