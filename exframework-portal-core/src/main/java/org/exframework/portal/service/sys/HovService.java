package org.exframework.portal.service.sys;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.exframework.portal.dao.HovDao;
import org.exframework.portal.metadata.hov.Hov;

/**
 * 参照业务类
 * Author: rwe
 * Date: Dec 29, 2020
 *
 * Copyright @ 2020 
 * 
 */
@Service
public class HovService {

	@Autowired
	HovDao hovDao;
	
	@Autowired
	DataItemService dataItemService;
	
	public Hov findByKey(String code) {
		return hovDao.findByKey(code);
	}
}
