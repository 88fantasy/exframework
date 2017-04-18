package com.gzmpc.metadata.toolbar.impl;

import com.gzmpc.metadata.sys.Account;
import com.gzmpc.metadata.toolbar.ButtonContentBase;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class TextButtonContent extends ButtonContentBase {

	public boolean displayed(Account account) {
		return true;
	}

	public String retTarget() {
		String target = this.getToolbutton().getTexthandler();
		return (target == null ? "" : target);
	}

	public String retShortCutKey() {
		String shortcutkey = this.getToolbutton().getShortcutkey();
		return (shortcutkey == null ? "" : shortcutkey);
	}

	public String retText() {
		return this.getToolbutton().getTitle();
	}

	public String retIconcls() {
		String iconcls = this.getToolbutton().getIconcls();
		return (iconcls == null ? "" : iconcls);
	}

	public String retTooltip() {
		String tooltip = this.getToolbutton().getTooltip();
		return (tooltip == null ? "" : tooltip);
	}

}