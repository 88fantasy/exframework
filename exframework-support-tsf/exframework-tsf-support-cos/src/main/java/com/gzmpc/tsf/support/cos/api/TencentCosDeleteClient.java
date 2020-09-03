package com.gzmpc.tsf.support.cos.api;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.qcloud.cos.COSClient;

@Component
public class TencentCosDeleteClient {
	private Log log = LogFactory.getLog(TencentCosDeleteClient.class.getName());

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
			e.printStackTrace();
			System.out.println("com.gzmpc.support.tencentCOS.api.TencentCosDeleteClient-001-error "+e.getMessage());
			log.error("com.gzmpc.support.tencentCOS.api.TencentCosDeleteClient-001-error "+e.getMessage());
		}
	}

}
