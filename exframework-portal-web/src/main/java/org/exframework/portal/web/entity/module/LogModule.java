package org.exframework.portal.web.entity.module;

import org.exframework.portal.metadata.module.IModuleService;
import org.exframework.portal.metadata.module.ModuleEntity;

/**
 * 日志模块
 * @author rwe
 * @since Jan 13, 2021
 *
 * Copyright @ 2021 
 * 
 */
@ModuleEntity( value = "log", name = "日志模块", hovRef = {"moduleHov"}, dataItemRef = {"loggerId", "content","sourceId", "param", "accountName"})
public class LogModule implements IModuleService {

}