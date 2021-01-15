package com.gzmpc.portal.service.sys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gzmpc.portal.dao.DataItemDao;
import com.gzmpc.portal.metadata.di.DataItem;
import com.gzmpc.portal.metadata.di.DataItemExtend;

/**
 *
 * Author: rwe Date: Dec 29, 2020
 *
 * Copyright @ 2020
 * 
 */
@Service
public class DataItemService {

	@Autowired
	DataItemDao dataItemDao;

	public DataItem findDataItemByKey(String key) {
		return dataItemDao.findByKey(key);
	}

	public DataItem findExtend(String objectCode, String key) {
		return dataItemDao.findExtend(objectCode, key);
//		DataItem result = null;
//		if (diexts != null) {
//			Map<String,DataItem> d =  diexts.get(objectcode);
//			if(d!=null){
//				DataItem di =  d.get(dtcode);
//				if(di!=null){
//					result = di;
//				}
//			}
//		}
//		return result;
	}

	public DataItem findDataItem(String objectCode, String key) {
		DataItem result = findExtend(objectCode, key);
		if (result == null) {
			result = findDataItemByKey(key);
		}
		return result;
	}

	public DataItem findDataItemWithSpliter(String code) {
		int index = code.lastIndexOf(DataItemExtend.SPLITER);
		if(index == -1) {
			return findDataItemByKey(code);
		}
		else {
			String key = code.substring(0,index);
			String objectCode = code.substring(index+1);
			return findExtend(objectCode, key);
		}
	}
}
