package com.gzmpc.backend.quickstart.springboot.test;

import java.net.URL;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.gzmpc.backend.quickstart.springboot.application.Application;

/**
 * @author rwe
 * @version 创建时间：Jan 20, 2020 12:07:32 AM 类说明
 */

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApplicationTest {
	@LocalServerPort
	private int port;

	private URL base;

	@Autowired
	private TestRestTemplate restTemplate;

	@BeforeAll
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
	public void test1() throws Exception {

//		ResponseEntity<String> response = this.restTemplate.getForEntity(this.base.toString() + "/hello", String.class,
//				"");
//		System.out.println(String.format("测试结果为：%s", response.getBody()));
	}
}
