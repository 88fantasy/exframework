package org.exframework.backend.quickstart;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.exframework.portal.metadata.entity.EntityScan;
import org.exframework.support.jdbc.annotation.TableEntityScan;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
* @author rwe
* @version 创建时间：2018年4月19日 上午11:39:20
* 类说明
*/

@SpringBootApplication(
	scanBasePackages = {
            "org.exframework.*", "com.gitee.sunchenbin.mybatis.actable.manager.*"
    },
	exclude = DruidDataSourceAutoConfigure.class
)
@MapperScan(basePackages = {"org.exframework.portal.jdbc.mapper","com.gitee.sunchenbin.mybatis.actable.dao.*"})
@EntityScan({"org.exframework.portal.metadata","org.exframework.portal.web.entity.*", "org.exframework.portal.admin.entity.*"})
@TableEntityScan({"org.exframework.portal.jdbc.entity"})
public class Application extends SpringBootServletInitializer {

	public static void main(String[] args) {
		new Application().configure(new SpringApplicationBuilder(Application.class))
				.run(args);
	}

}
