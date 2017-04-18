package com.gzmpc.metadata.toolbar.impl;

import com.gzmpc.exception.NotFoundException;
import com.gzmpc.metadata.sys.Account;
import com.gzmpc.metadata.sys.Module;
import com.gzmpc.metadata.toolbar.ButtonContentBase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gzmpc.permission.PermissionSupport;
import com.gzmpc.sys.ModuleService;

@Component
@Scope(value = org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ModuleButtonContent extends ButtonContentBase {
	
	private Log log = LogFactory.getLog(ModuleButtonContent.class.getName());
	
	private Module module;
	
	@Autowired
	PermissionSupport permissions;
	
	@Autowired
	ModuleService moduleService;
	

	public boolean displayed(Account account) {
		String moduleid = this.getToolbutton().getModuleId();
		if (moduleid == null || "".equals(moduleid)) {
			Exception e = new NotFoundException("菜单栏模块配置出错,没有配置模块ID");
			log.error(e.getMessage(),e);
			return false;
		}
		return permissions.hasRight(account, moduleid);
	}

	// 通过模块ID找到对应的入口
	public String retTarget() {
		String result = new String("");
		try {
			result = newInstance().getModuleEntry();
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
		return result;

	}

	public String retShortCutKey() {
		String shortcutkey = this.getToolbutton().getShortcutkey();
		return (shortcutkey == null ? "" : shortcutkey);
	}

	// 通过模块ID找到对应的多语言配置
	public String retText() {
		String result = new String("");
		try {
			result = newInstance().getModuleName();
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
		return result;

	}

	public String retIconcls() {
		String iconcls = this.getToolbutton().getIconcls();
		return (iconcls == null ? "" : iconcls);
	}

	public String retTooltip() {
		String tooltip = this.getToolbutton().getTooltip();
		return (tooltip == null ? "" : tooltip);
	}

	private Module newInstance() throws Exception {
		if (module == null) {

			if (this.getToolbutton().getModuleId() == null || "".equals(this.getToolbutton().getModuleId()))
				throw new RuntimeException("菜单栏模块配置出错,没有配置模块ID");

			module = moduleService.getModule(this.getToolbutton().getModuleId());
		}
		return module;
	}

}