package com.gzmpc.portal.web.entity.module;

import com.gzmpc.portal.metadata.module.IModuleService;
import com.gzmpc.portal.metadata.module.ModuleEntity;

/**
 * 日志模块
 * Author: rwe
 * Date: Jan 13, 2021
 *
 * Copyright @ 2021 
 * 
 */
@ModuleEntity( value = "log", name = "日志模块", hovRef = {"moduleHov"}, dataItemRef = {"loggerId", "content","sourceId", "param", "accountName"})
public class LogModule implements IModuleService {

}