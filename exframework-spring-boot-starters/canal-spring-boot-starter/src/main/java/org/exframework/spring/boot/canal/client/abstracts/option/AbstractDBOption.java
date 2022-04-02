package org.exframework.spring.boot.canal.client.abstracts.option;

import com.alibaba.otter.canal.protocol.CanalEntry;
import org.exframework.spring.boot.canal.client.interfaces.IDBOption;

/**
 * 数据库操作抽象类
 *
 * @author rwe
 * @date 2022/3/14 19:35
 */
public abstract class AbstractDBOption implements IDBOption {
	
	/**
	 * 操作类型
	 */
	protected CanalEntry.EventType eventType;
	/**
	 * 下一个节点
	 */
	protected AbstractDBOption next;
	
	
	
	/**
	 * 默认构造方法
	 *
	 * @return
	 * 
	 */
	public AbstractDBOption() {
		this.setEventType();
	}
	
	/**
	 * 进行类型设置
	 *
	 */
	protected abstract void setEventType();
	
	
	/**
	 * 设置下一个节点
	 *
	 * @param next
	 * @return
	 * 
	 */
	public void setNext(AbstractDBOption next) {
		this.next = next;
	}
	
	
	
	/**
	 * 责任链处理
	 *
	 * @param destination
	 * @param schemaName
	 * @param tableName
	 * @param rowChange
	 * @return
	 * 
	 */
	public void doChain(String destination, String schemaName, String tableName, CanalEntry.RowChange rowChange) {
		if (this.eventType.equals(rowChange.getEventType())) {
			this.doOption(destination, schemaName, tableName, rowChange);
		} else {
			if(this.next==null){
				return;
			}
			this.next.doChain(destination, schemaName, tableName,rowChange);
		}
	}
	
	
}
