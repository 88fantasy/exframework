package org.exframework.alibaba.ms.quickstart.springboot.test;

import java.net.URL;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import org.exframework.alibaba.ms.quickstart.springboot.application.ProviderApplication;


/**
 * @author rwe
 * @version 创建时间：Jan 20, 2020 12:07:32 AM 类说明
 */

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ProviderApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProviderApplicationTest {
	private static final Logger logger = LoggerFactory.getLogger(ProviderApplicationTest.class);
	
	@LocalServerPort
	private int port;

	private URL base;
	

	@BeforeEach
	public void setUp() throws Exception {
		String url = String.format("http://localhost:%d/", port);
		System.out.println(String.format("port is : [%d]", port));
		this.base = new URL(url);
	}

	/**
	 * 向"/test"地址发送请求，并打印返回结果
	 * 
	 * @throws Exception
	 */
	@Test
	public void test() throws Exception {
		
	}
}
