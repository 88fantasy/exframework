package org.exframework.support.mongo.init;


import java.lang.reflect.Field;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import org.exframework.support.mongo.annotation.AutoIncLong;
import org.exframework.support.mongo.annotation.AutoIncUUID;
import org.exframework.support.mongo.entity.Seq;

/**
* @author rwe
* @version 创建时间：May 31, 2020 12:02:24 PM
* 保存id自增监听器
*/

@Component
public class SaveEventIdGeneratorListener extends AbstractMongoEventListener<Object> {

	@Autowired
	private MongoTemplate mongo;
 
	@Override
	public void onBeforeConvert(BeforeConvertEvent<Object> event) {
		super.onBeforeConvert(event);
		Object source = event.getSource();
		if (source != null) {
			ReflectionUtils.doWithFields(source.getClass(), new ReflectionUtils.FieldCallback() {
				@Override
				public void doWith(Field field)
						throws IllegalArgumentException, IllegalAccessException {
					ReflectionUtils.makeAccessible(field);
					Object value = field.get(source);
					// 如果字段添加了我们自定义注解
					if (field.isAnnotationPresent(AutoIncLong.class) && field.getType() == Long.class) {
						if( value == null || 0l == (Long)value) {
							field.set(source, getNextId(source.getClass().getSimpleName()));
						}
					}
					else if(field.isAnnotationPresent(AutoIncUUID.class) && field.getType() == String.class) {
						if( value == null || "".equals(value)) {
							field.set(source, getNextUUID(source.getClass().getSimpleName()));
						}
					}
				}
				
			});
		}
	}
 
	/**
	 * 获取下一个自增ID
	 * 
	 * @param collName
	 *            集合（这里用类名，就唯一性来说最好还是存放长类名）名称
	 * @return 序列值
	 */
	private Long getNextId(String collName) {
		Query query = new Query(Criteria.where("collName").is(collName));
		Update update = new Update();
		update.inc("seqId", 1);
		FindAndModifyOptions options = new FindAndModifyOptions();
		options.upsert(true);
		options.returnNew(true);
		Seq seq = mongo.findAndModify(query, update, options, Seq.class);
		return seq.getSeqId();
	}
	
	/**
	 * 获取下一个自增ID
	 * 
	 * @param collName
	 *            集合（这里用类名，就唯一性来说最好还是存放长类名）名称
	 * @return 序列值
	 */
	private String getNextUUID(String collName) {
		ThreadLocalRandom random = ThreadLocalRandom.current();
		return new UUID(random.nextLong(), random.nextLong()).toString().replaceAll("-","");
	}
}
