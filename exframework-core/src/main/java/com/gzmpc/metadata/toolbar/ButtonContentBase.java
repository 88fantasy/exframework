package com.gzmpc.metadata.toolbar;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gzmpc.metadata.OperatorPool;
import com.gzmpc.metadata.sys.Account;
import com.gzmpc.utils.Const;

@Component
@Scope(value = org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public abstract class ButtonContentBase {
	private Log log = LogFactory.getLog(ButtonContentBase.class.getName());
	
	@Autowired
	OperatorPool operatorPool;

	private ToolButton tb ;
	
	public abstract boolean displayed(Account account);

	public ToolButton getToolbutton() {
		return tb;
	}
	
	public void setToolbutton(ToolButton tb) {
		this.tb = tb;
	}
	
	public String commonRetText() {
		StringBuffer value = new StringBuffer();
		try {
			String text = tb.getTitle();

			if (text == null)
				text = "";
			value.append(text);
			String shortCutKey = tb.getShortcutkey();
			if (!"".equals(shortCutKey)) {
				Map<String, String> map = operatorPool.retDtDictionary(Const.DT_SHORT_CUT_KEY);
				String temp = map.get(shortCutKey);
				if (temp != null && !"".equals(temp)) {
					value.append("(");
					value.append(temp);
					value.append(")");
				}
			}
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
		}
		return value.toString();
	}
}
