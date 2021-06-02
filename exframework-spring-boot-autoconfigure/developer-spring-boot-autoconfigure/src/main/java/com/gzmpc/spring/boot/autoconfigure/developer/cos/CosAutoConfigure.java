package com.gzmpc.spring.boot.autoconfigure.developer.cos;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.util.StringUtils;

import com.gzmpc.spring.boot.autoconfigure.cos.CosClient;
import com.gzmpc.spring.boot.autoconfigure.cos.CosProperties;

/**
 *
 * Author: rwe Date: 2021年5月20日
 *
 * Copyright @ 2021
 * 
 */
@Configuration
@EnableConfigurationProperties(value = CosProperties.class)
public class CosAutoConfigure {

	private Logger log = LoggerFactory.getLogger(CosAutoConfigure.class);

	@Autowired
	private CosProperties cosProperties;

	@Primary
	@Bean
	public CosClient exframeCosClient() {
		String secretId = cosProperties.getSecret().getId(), secretKey = cosProperties.getSecret().getKey(), cosRegion = cosProperties.getCos().getRegion(),
				bucketName = cosProperties.getCos().getBucket().getName(),
				path = cosProperties.getCos().getBucket().getPath();

		String developer = cosProperties.getDeverloper();
		if (StringUtils.hasLength(developer)) {

		}

		log.warn("如非使用默认配置secretId，请自行配置tencentcloud.secret.id");
		log.warn("如非使用默认配置secretKey，请自行配置tencentcloud.secret.key");
		log.warn("如非使用默认配置cosRegion，请自行配置tencentcloud.cos.region");
		if (!StringUtils.hasLength(secretId)) {
			log.error("secretId不能为null，配置tencentcloud.secret.id");
		}
		if (!StringUtils.hasLength(secretKey)) {
			log.error("secretKey不能为null，配置tencentcloud.secret.key");
		}
		if (!StringUtils.hasLength(cosRegion)) {
			log.error("cosRegion不能为null，配置tencentcloud.cos.region");
		}
		if (StringUtils.hasLength(secretId) && StringUtils.hasLength(secretKey) && StringUtils.hasLength(cosRegion)
				&& StringUtils.hasLength(bucketName) && StringUtils.hasLength(path)) {
			return CosClient.init(secretId, secretKey, cosRegion, bucketName, path);
		} else {
			log.error("cosClient 配置失败");
			return null;
		}
	}
}
