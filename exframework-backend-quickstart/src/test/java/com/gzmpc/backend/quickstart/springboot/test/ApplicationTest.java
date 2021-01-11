package com.gzmpc.backend.quickstart.springboot.test;

import java.net.URL;

import javax.annotation.Resource;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gzmpc.backend.quickstart.springboot.application.Application;
import com.gzmpc.portal.jdbc.entity.AccountDO;
import com.gzmpc.portal.jdbc.mapper.AccountMapper;
import com.gzmpc.portal.metadata.sys.Account.AccountStatusTypeEnum;

import static org.assertj.core.api.Assertions.assertThat;
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

	@BeforeEach
	public void setUp() throws Exception {
		String url = String.format("http://localhost:%d/", port);
		System.out.println(String.format("port is : [%d]", port));
		this.base = new URL(url);
	}

	@Resource
    private AccountMapper mapper;
	/**
	 *  测试帐号
	 * 
	 * @throws Exception
	 */
	@Test
	public void  user() throws Exception {
		AccountDO account = new AccountDO();
		account.setAccountId("testaccount");
		account.setAccountStatus(AccountStatusTypeEnum.VALID);
		assertThat(mapper.insert(account)).isGreaterThan(0);
        // 成功直接拿回写的 ID
        assertThat(account.getAccountId()).isNotNull();
        assertThat(mapper.delete(new QueryWrapper<AccountDO>()
                .lambda().eq(AccountDO::getAccountId, "testaccount"))).isGreaterThan(0);
	}
}
