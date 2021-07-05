package org.exframework.portal.dao;

import java.util.Collection;
import java.util.List;

import org.exframework.support.common.entity.FilterCondition;
import org.exframework.portal.metadata.di.DataItem;
import org.exframework.portal.metadata.module.Module;
import org.exframework.portal.metadata.module.ModuleBase;
import org.exframework.support.common.entity.Page;
import org.exframework.support.common.entity.PageModel;

/**
 *
 * @author rwe
 * @since Dec 29, 2020
 *
 * Copyright @ 2020 
 * 
 */
public interface PortalCoreModuleDao extends PortalCoreMetaDao<ModuleBase> {
	
	Collection<DataItem> findDataItemByEntity(ModuleBase entity);
	
	Collection<String> findPermissionKeyByEntity(ModuleBase entity);
	
	Collection<String> findHovKeyByEntity(ModuleBase entity);
	
	PageModel<ModuleBase> query(Collection<FilterCondition> params, Page page);
	
	PageModel<ModuleBase> query(Collection<FilterCondition> params, Page page, Collection<String> sorts);
	
	List<ModuleBase> list(Collection<FilterCondition> params);
	
	boolean insertModule(Module module); 
	
	boolean updateModule(Module module);
}
