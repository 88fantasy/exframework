package com.gzmpc.web.ui;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gzmpc.exception.NotFoundException;
import com.gzmpc.metadata.OperatorPool;
import com.gzmpc.metadata.sys.Account;
import com.gzmpc.permission.PermissionSupport;

@Service
public class DdlService {
	@Autowired
	OperatorPool operatorPool;
	
	@Autowired
	PermissionSupport permissions;
	

	public Map<String, String> get(String ddlkey,Account account) throws NotFoundException{
		Map<String, String> ddl = operatorPool.retDtDictionary(ddlkey);
		if (ddl != null) {
			Map<String,String> result = new ConcurrentHashMap<String,String>(ddl);
			for(String key : ddl.keySet()) {
				//由于字典不限权限比较多,故采用排除算法
				if(permissions.hasRight(account, "ddl-"+ddlkey+"-"+key)) {
					result.remove(key);
				}
			}
			return result;
		} else {
			throw new NotFoundException("找不到ddlkey为[" + ddlkey + "]的字典");
		}
	}
}
