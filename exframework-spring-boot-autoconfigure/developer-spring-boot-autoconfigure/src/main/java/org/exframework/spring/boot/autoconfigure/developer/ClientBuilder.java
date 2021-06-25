package org.exframework.spring.boot.autoconfigure.developer;

import com.dtflys.forest.config.ForestConfiguration;

/**
* @author rwe
* @version 创建时间：Oct 19, 2020 10:41:04 PM
* 客户端创建者
*/

public class ClientBuilder {

	public static ConfigClient configClient() {
		return ForestConfiguration.configuration().createInstance(ConfigClient.class);
	}
}
