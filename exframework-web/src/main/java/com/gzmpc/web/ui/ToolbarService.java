package com.gzmpc.web.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gzmpc.exception.NotFoundException;
import com.gzmpc.metadata.MetaData;
import com.gzmpc.metadata.OperatorPool;
import com.gzmpc.metadata.sys.Account;
import com.gzmpc.metadata.toolbar.ButtonContentBase;
import com.gzmpc.metadata.toolbar.ToolBar;
import com.gzmpc.metadata.toolbar.ToolButton;
import com.gzmpc.util.MapUtil;
import com.gzmpc.utils.Const;

@Service
public class ToolbarService {
	@Autowired
	OperatorPool operatorPool;

	@Autowired
	MetaData metaData;

	public ToolButton[] get(String menu,Account account) throws NotFoundException {
		ToolBar toolbar = metaData.findToolBarByCode(menu);
		if (toolbar == null) {
			throw new NotFoundException("根据菜单编码:" + menu + "找不到对应的菜单定义");
		}

		ToolButton[] tbs = toolbar.getToolButtons();
		if (tbs == null) {
			throw new NotFoundException("菜单栏没有配置对应的操作！");
		}
		List<ToolButton> list = new ArrayList<ToolButton>();
		for (int i = 0,length = tbs.length; i < length; i++) {
			ToolButton tb = tbs[i];
			ButtonContentBase button = operatorPool.retButtonBase(tb);
			if (button.displayed(account)) {
				list.add(button.getToolbutton());
			}
		}
		return list.toArray(new ToolButton[list.size()]);
	}
	
	@SuppressWarnings("unchecked")
	public Map<String,String>[] translateToMap(ToolButton[] tbs) {
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		for (int i = 0,length = tbs.length; i < length; i++) {
			ToolButton tb = tbs[i];
			Map<String,String> map = new ConcurrentHashMap<String,String>();
			map.put(Const.MENU_ID, String.valueOf(tb.getButtonid()));
			MapUtil.putIfNotNull(map, Const.MENU_TEXT, tb.getTitle());
			MapUtil.putIfNotNull(map, Const.MENU_TARGET, tb.getTexthandler());
			MapUtil.putIfNotNull(map, Const.MENU_ICONCLS, tb.getIconcls());
			MapUtil.putIfNotNull(map, Const.MENU_SHORTCUTKEY, tb.getShortcutkey());
			MapUtil.putIfNotNull(map, Const.MENU_TOOLTIP, tb.getTooltip());
			list.add(map);
		}
		return list.toArray(new ConcurrentHashMap[list.size()]);
	}
}
