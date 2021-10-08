package org.exframework.portal.service.sys;

import org.exframework.portal.dao.PortalCoreDataItemDao;
import org.exframework.portal.metadata.di.DataItem;
import org.exframework.portal.metadata.di.DataItemExtend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author rwe Date: Dec 29, 2020
 *
 * Copyright @ 2020
 * 
 */
@Service
public class PortalCoreDataItemService {

	@Autowired
	PortalCoreDataItemDao portalCoreDataItemDao;

	public DataItem findDataItemByKey(String key) {
		return portalCoreDataItemDao.findByKey(key);
	}

	public DataItem findExtend(String objectCode, String key) {
		return portalCoreDataItemDao.findExtend(objectCode, key);
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
