package com.gzmpc.support.jdbc.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.BeanUtils;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gzmpc.support.jdbc.exception.MoreThanOneExcetpion;

/**
 *
 * Author: rwe
 * Date: Jan 6, 2021
 *
 * Copyright @ 2021 
 * 
 */
public interface IBaseService<T> extends IService<T> {
	
	DataSourceTransactionManager getDataSourceTransactionManager();
	
	TransactionDefinition getTransactionDefinition();

	default boolean saveBatch(Collection<T> entityList, int batchSize) {
		DataSourceTransactionManager manager = getDataSourceTransactionManager();
		TransactionStatus transactionStatus = manager.getTransaction(getTransactionDefinition());
		int count = 0;
		for(T entity : entityList) {
			try {
				saveOrUpdate(entity);
				count++;
				if(count == batchSize) {
					manager.commit(transactionStatus);
					count = 0;
				}
			}catch(Exception e) {
				manager.rollback(transactionStatus);
				return false;
			}
		}
		manager.commit(transactionStatus);
		return true;
	}

//	default boolean saveOrUpdateBatch(Collection<T> entityList, int batchSize) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	default boolean updateBatchById(Collection<T> entityList, int batchSize) {
//		// TODO Auto-generated method stub
//		return false;
//	}

	default T getOne(Wrapper<T> queryWrapper, boolean throwEx) {
		List<T> t = getBaseMapper().selectList(queryWrapper);
		if(t.size() > 0) {
			if(throwEx) {
				throw new MoreThanOneExcetpion("多于一条记录");
			}
			else {
				return t.get(0);
			}
		}
		else if ( t.size() == 1){
			return t.get(0);
		}
		else {
			return null;
		}
	}

	default Map<String, Object> getMap(Wrapper<T> queryWrapper) {
		T t = getBaseMapper().selectOne(queryWrapper);
		return BeanUtils.beanToMap(t);
	}

}
