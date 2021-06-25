package org.exframework.support.wechat.client;

import com.dtflys.forest.config.ForestConfiguration;

/**
* @author rwe
* @version 创建时间：Oct 19, 2020 10:41:04 PM
* 客户端创建者
*/

public class ClientBuilder {

	public static ComClient comClient() {
		return ForestConfiguration.configuration().createInstance(ComClient.class);
	}
}
