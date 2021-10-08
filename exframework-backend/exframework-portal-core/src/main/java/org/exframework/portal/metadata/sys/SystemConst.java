package org.exframework.portal.metadata.sys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SystemConst {

	@Autowired(required = false)
	@Value("${system.admin.user:supervisor}")
	public String ACCOUNT_ADMIN;
	
}
