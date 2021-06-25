package org.exframework.support.mongo.util;

import java.util.List;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import org.exframework.support.common.entity.Page;
import org.exframework.support.common.entity.PageModel;
import org.exframework.support.common.entity.Pager;

public class MongoUtils {

	public static <T> PageModel<T> queryListByPage(MongoTemplate template, Query query, Page page, Class<T> entityClass) {
		if(page == null) {
			page = Page.DEFAULT;
		}
		long total = template.count(query, entityClass);
		Pager pager = new Pager(total, page);
		query.skip(page.skip()).limit(page.getPageSize().intValue());
		List<T> list = template.find(query, entityClass);
		PageModel<T> model = new PageModel<T>(pager, list);
		return model;
	}
}
