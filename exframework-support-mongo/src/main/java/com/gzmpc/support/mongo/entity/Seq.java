package com.gzmpc.support.mongo.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author rwe
 * @version 创建时间：May 31, 2020 12:23:23 PM 类说明
 */

@Document(collection = "sequence")
public class Seq {

	/**
	 * 主键
	 */
	@Id
	private String id;

	/**
	 * 集合名称
	 */
	@Field
	private String collName;

	/**
	 *  序列值
	 */
	@Field
	private Long seqId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCollName() {
		return collName;
	}

	public void setCollName(String collName) {
		this.collName = collName;
	}

	public Long getSeqId() {
		return seqId;
	}

	public void setSeqId(Long seqId) {
		this.seqId = seqId;
	}
	
	
}
