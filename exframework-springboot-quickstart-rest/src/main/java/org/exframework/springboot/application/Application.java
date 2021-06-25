package org.exframework.springboot.application;

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
                "org.exframework.*"
        }
)
public class Application extends SpringBootServletInitializer {

	public static void main(String[] args) {
		new Application().configure(new SpringApplicationBuilder(Application.class))
				.run(args);
	}

}
