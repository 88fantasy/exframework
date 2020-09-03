package com.gzmpc.tsf.support.cos.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.qcloud.cos.COSClient;

@Component
public class TencentCosDeleteClient {
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private COSClient cosClient;
	
	@Value("${tencentcloud.cos.bucket.name}")
	private String bucketName;
	
	/**
	 * @param String 桶的名称
	 * @param String 存储桶中文件的路径 
	 */
	public void delFile(String fileUrl) {
		try {
			// 指定要删除的 bucket 和路径
			cosClient.deleteObject(bucketName, fileUrl);
		} catch (Exception e) {
			LOG.error("com.gzmpc.support.tencentCOS.api.TencentCosDeleteClient-001-error "+e.getMessage(), e);
		}
	}

}
