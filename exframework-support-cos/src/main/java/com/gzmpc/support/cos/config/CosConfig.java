package com.gzmpc.support.cos.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import com.gzmpc.support.cos.client.CosClient;
import com.gzmpc.support.cos.constants.CosConstants;

@Configuration
public class CosConfig {

	private Log log = LogFactory.getLog(CosConfig.class.getName());

	@Value("${tencentcloud.secret.id}")
	private String secretId;
	
	@Value("${tencentcloud.secret.key}")
	private String secretKey;
	
	@Value("${tencentcloud.cos.region}")
	private String cosRegion;
	
	@Value(CosConstants.BUCKET_KEY)
	private String bucketName;
	
	@Value(CosConstants.PATH_KEY)
	private String path;

	@Bean
	@ConditionalOnMissingBean( name = {"cloudCosClient", "exframeCosClient"})
	public CosClient exframeCosClient() {
		log.warn("如非使用默认配置secretId，请自行配置tencentcloud.secret.id");
		log.warn("如非使用默认配置secretKey，请自行配置tencentcloud.secret.key");
		log.warn("如非使用默认配置cosRegion，请自行配置tencentcloud.cos.region");
		if (!StringUtils.hasLength(secretId)) {
			log.error("secretId不能为null，配置tencentcloud.secret.id");
			return null;
		}
		if (!StringUtils.hasLength(secretKey)) {
			log.error("secretKey不能为null，配置tencentcloud.secret.key");
			return null;
		}
		if (!StringUtils.hasLength(cosRegion)) {
			log.error("cosRegion不能为null，配置tencentcloud.cos.region");
			return null;
		}
		return CosClient.init(secretId, secretKey, cosRegion, bucketName, path);

	}
}
