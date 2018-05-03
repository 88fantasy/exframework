package com.gzmpc.metadata.sys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;

import com.gzmpc.utils.Const;

@Configuration
@PropertySource(value=Const.SYSTEM_PROPERTIES)
@Repository
public class SystemConst {

	@Autowired(required = false)
	@Value("${system.admin.user:supervisor}")
	public String ACCOUNT_ADMIN;
	
	@Autowired(required = false)
	@Value("${system.systable.default:webapp}")
	public String SYS_TABLE_CONFIG;
}
