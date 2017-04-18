package com.gzmpc.metadata.sys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;

import com.gzmpc.utils.Const;

@Configuration
@PropertySource(value=Const.SYSTEM_PROPERTIES)
@Repository
public class SystemConst {

	@Value("${system.admin.user:supervisor}")
	public String ACCOUNT_ADMIN;
}
