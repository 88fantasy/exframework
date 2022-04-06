package org.exframework.spring.boot.canal.client.core;

/**
 * Canal 的一些信息
 *
 * @author rwe
 * @date 2022/3/14 19:35
 */
public class CanalMsg{
	
	/**
	 * 指令
	 */
	private String destination;
	/**
	 * 数据库实例名称
	 */
	private String schemaName;
	/**
	 * 数据库表名称
	 */
	private String tableName;

	public String getDestination() {
		return destination;
	}

	public CanalMsg setDestination(String destination) {
		this.destination = destination;
		return this;
	}

	public String getSchemaName() {
		return schemaName;
	}

	public CanalMsg setSchemaName(String schemaName) {
		this.schemaName = schemaName;
		return this;
	}

	public String getTableName() {
		return tableName;
	}

	public CanalMsg setTableName(String tableName) {
		this.tableName = tableName;
		return this;
	}
}
