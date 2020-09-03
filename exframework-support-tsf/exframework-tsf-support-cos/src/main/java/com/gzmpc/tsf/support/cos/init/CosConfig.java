package com.gzmpc.tsf.support.cos.init;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.region.Region;
import org.apache.commons.lang.StringUtils;

@Configuration
public class CosConfig {
	
	private Log log = LogFactory.getLog(CosConfig.class.getName());
	@Value("${tencentcloud.secret.id}")
    private String secretId;
	@Value("${tencentcloud.secret.key}")
    private String secretKey;
	@Value("${tencentcloud.cos.region}")
	private String cosRegion;
	
	@Bean
	public COSClient getCosClient() {
		log.warn("如非使用默认配置secretId，请自行配置tencentcloud.secret.id");
		log.warn("如非使用默认配置secretKey，请自行配置tencentcloud.secret.key");
		log.warn("如非使用默认配置cosRegion，请自行配置tencentcloud.cos.region");
		if(StringUtils.isBlank(secretId)) {
			log.error("secretId不能为null，配置tencentcloud.secret.id");
			return null;
		}
		if(StringUtils.isBlank(secretKey)) {
			log.error("secretKey不能为null，配置tencentcloud.secret.key");
			return null;
		}
		if(StringUtils.isBlank(cosRegion)) {
			log.error("cosRegion不能为null，配置tencentcloud.cos.region");
			return null;
		}
		
		COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
		Region region = new Region(cosRegion);
		ClientConfig clientConfig = new ClientConfig(region);
		// 3 生成 cos 客户端。
        COSClient cosClient = new COSClient(cred, clientConfig);
		return cosClient; 
		
	}
}
