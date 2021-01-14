package com.gzmpc.portal.service.sys;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.gzmpc.portal.dao.DictionaryDao;
import com.gzmpc.portal.metadata.sys.Account;
import com.gzmpc.support.common.annotation.BuildComponent;
import com.gzmpc.support.common.build.Buildable;
import com.gzmpc.support.common.exception.BuildException;

@Service
@BuildComponent
public class DdlService implements Buildable {

	private Logger log = LoggerFactory.getLogger(DdlService.class.getName());

	@Autowired
	DictionaryDao dictionaryDao;

	@Autowired
	PermissionService permissionService;

	@Autowired
	AccountService accountService;

	public Collection<String> getAllKeys() {
		return dictionaryDao.allKeys();
	}

	public Map<String, String> get(Account account, String ddlkey) {
		Map<String, String> ddl = get(ddlkey);
		Map<String, String> result = new ConcurrentHashMap<String, String>(ddl);
		for (String key : ddl.keySet()) {
			// 由于字典不限权限比较多,故采用排除算法
			if (permissionService.hasRight(account, "ddl-" + ddlkey + "-" + key)) {
				result.remove(key);
			}
		}
		return result;
	}

	public Map<String, String> get(String ddlkey) {
		return dictionaryDao.findMapByKey(ddlkey);
	}

	public String getValue(String ddlKey, String itemKey) {
		return get(ddlKey).get(itemKey);
	}

	public boolean saveDictionary(String code, String name, Map<String, String> value) {
		return dictionaryDao.saveDictionary(code, name, value);
	}

	@Override
	public void build(ApplicationContext ac) throws BuildException {
		
	}
}
