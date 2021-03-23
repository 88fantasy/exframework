package com.gzmpc.support.doc.service;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.gzmpc.support.doc.annotation.DataBaseGen;
import com.gzmpc.support.doc.entity.DataBaseTable;
import com.gzmpc.support.doc.entity.DataBaseTableSource;

/**
 * 数据库文档
 * Author: rwe
 * Date: 2021年3月10日
 *
 * Copyright @ 2021 
 * 
 */
@Service
public class DataBaseService {
	
	@Autowired
	private ApplicationContext applicationContext;

	public String markdown() {
		StringBuffer sb = new StringBuffer();
		List<DataBaseTable> tables = new ArrayList<DataBaseTable>();
		Map<String, Object> gens = applicationContext.getBeansWithAnnotation(DataBaseGen.class);
		for (Entry<String, Object> gen : gens.entrySet()) {
			Object g = gen.getValue();
			Class<?> gc = g.getClass();
			if(DataBaseTableSource.class.isAssignableFrom(gc)) {
				DataBaseTableSource source = (DataBaseTableSource) g;
				tables.addAll(source.getTables());
			}	
		}
		
		sb.append("## 表汇总 \n");
		sb.append("| 表名 | 功能说明 |\n");
		sb.append("| -- | -------- |\n");
		tables.stream().forEach(table -> {
			sb.append(MessageFormat.format("| {0} | {1} |\n", table.getName(), table.getDescription()));
		});
		sb.append("\n\n");
		tables.stream().forEach(table -> {
			sb.append(table.toMarkdown());
		});

		return sb.toString();
	}
	
	
	
	
}
