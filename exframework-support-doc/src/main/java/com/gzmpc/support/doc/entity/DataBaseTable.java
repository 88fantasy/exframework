package com.gzmpc.support.doc.entity;

import java.text.MessageFormat;
import java.util.List;

/**
* @author rwe
* @version 创建时间：2021年3月10日 下午2:24:40
* 数据表
*/

public class DataBaseTable {

	private String name;
	
	private String description;
	
	private List<DataBaseTableField> fields;

	public DataBaseTable(String name, String description, List<DataBaseTableField> fields) {
		this.name = name;
		this.description = description;
		this.fields = fields;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<DataBaseTableField> getFields() {
		return fields;
	}

	public void setFields(List<DataBaseTableField> fields) {
		this.fields = fields;
	}
	
	public String toMarkdown() {
		StringBuffer sb = new StringBuffer();
		sb.append("## "+this.name+"\n");
		sb.append("| 列名 | 类型 | 说明 | 必填 | 默认值 | \n");
		sb.append("| --- | ------- | ------- | --- |\n");
		for(DataBaseTableField field: fields) {
			String line = MessageFormat.format("| {0} | {1} | {2} | {3} | {4} |\n", field.getField(),field.getType().getTypeName(), field.getDescription(), (field.getNullable()? "-": "是"), field.getDefaultValue());
			sb.append(line);
		}
		return sb.toString();
	}
}
