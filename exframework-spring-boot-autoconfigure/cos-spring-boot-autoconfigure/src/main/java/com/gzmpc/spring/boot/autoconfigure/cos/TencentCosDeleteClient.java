package com.gzmpc.spring.boot.autoconfigure.cos;

import com.qcloud.cos.exception.CosClientException;
import com.qcloud.cos.exception.CosServiceException;


public interface TencentCosDeleteClient extends TencentCosClient {
	
	/**
	 * @param String 桶的名称
	 * @param String 存储桶中文件的路径 
	 */
	default void delFile(String targetUrl) throws CosServiceException, CosClientException {
		getCosTransferManager().getCOSClient().deleteObject(getBucketName(), getKey(targetUrl));
	}

}
