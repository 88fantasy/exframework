package com.gzmpc.tsf.support.cos.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.gzmpc.microservice.config.annotation.EnableConfig;
import com.gzmpc.microservice.config.annotation.ParamValue;
import com.gzmpc.support.cos.client.CosClient;

@Service
@EnableConfig
public class CosClientCloudBuilder {
	
	@ParamValue("tencentcloud.secret.id")
    private String secretId;
	
	@ParamValue("tencentcloud.secret.key")
    private String secretKey;

	@ParamValue("tencentcloud.cos.region")
    private String cosRegion;
	
	@Value("${tencentcloud.cos.bucket.name}")
	private String bucketName;
	
	@Value("${tencentcloud.cos.bucket.path}")
	private String path;
	
	public CosClient build() {
		return CosClient.init(secretId, secretKey, cosRegion, bucketName, path);
	}
	
}