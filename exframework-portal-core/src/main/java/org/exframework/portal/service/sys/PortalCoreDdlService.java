package org.exframework.portal.service.sys;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import org.exframework.portal.dao.PortalCoreDictionaryDao;
import org.exframework.portal.metadata.dict.DictionaryItem;
import org.exframework.portal.metadata.sys.Account;
import org.exframework.support.common.annotation.BuildComponent;
import org.exframework.support.common.build.Buildable;
import org.exframework.support.common.exception.BuildException;

@Service
@BuildComponent
public class PortalCoreDdlService implements Buildable {

	private Logger log = LoggerFactory.getLogger(PortalCoreDdlService.class.getName());
	
	private Map<String,DictionaryItem> localItems = new ConcurrentHashMap<>();

	@Autowired
	PortalCoreDictionaryDao portalCoreDictionaryDao;

	@Autowired
	PortalCorePermissionService portalCorePermissionService;

	@Autowired
	PortalCoreAccountService portalCoreAccountService;
	
	@Autowired
	ApplicationContext applicationContext;

	public Collection<String> getAllKeys() {
		return portalCoreDictionaryDao.allKeys();
	}

	public Map<String, String> get(Account account, String ddlkey) {
		Map<String, String> ddl = get(ddlkey);
		Map<String, String> result = new ConcurrentHashMap<String, String>(ddl);
		for (String key : ddl.keySet()) {
			// 由于字典不限权限比较多,故采用排除算法
			if (portalCorePermissionService.hasRight(account, "ddl-" + ddlkey + "-" + key)) {
				result.remove(key);
			}
		}
		return result;
	}

	public Map<String, String> get(String ddlkey) {
		//优先查找代码字典
		if(localItems.containsKey(ddlkey)) {
			return localItems.get(ddlkey).getValue();
		}
		else {
			return portalCoreDictionaryDao.findMapByKey(ddlkey);
		}
	}
	
	public Map<String, Map<String, String>> many(String[] ddlkeys) {
		Map<String, Map<String, String>> dicts = new ConcurrentHashMap<>();
		if(ddlkeys != null && ddlkeys.length > 0) {
			for(String key : ddlkeys) {
				dicts.put(key, get(key));
			}
		}
		return dicts;
	}

	public String getValue(String ddlKey, String itemKey) {
		return get(ddlKey).get(itemKey);
	}

	public boolean saveDictionary(String code, String name, Map<String, String> value) {
		return portalCoreDictionaryDao.saveDictionary(code, name, value, false);
	}
	
	public boolean saveDictionary(String code, String name, Map<String, String> value, boolean local) {
		if(local) {
			DictionaryItem item = new DictionaryItem();
			item.setCode(code);
			item.setName(name);
			item.setValue(value);
			item.setLocal(local);
			localItems.put(code, item);
		}
		return portalCoreDictionaryDao.saveDictionary(code, name, value, local);
	}

	@Override
	public void build(ApplicationContext ac) throws BuildException {
		
	}
}
