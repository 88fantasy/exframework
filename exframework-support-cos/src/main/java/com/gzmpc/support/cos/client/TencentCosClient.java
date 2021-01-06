package com.gzmpc.support.cos.client;

import com.qcloud.cos.transfer.TransferManager;

/**
 *
 * Author: rwe
 * Date: Jan 1, 2021
 *
 * Copyright @ 2021 
 * 
 */
public interface TencentCosClient {
	

	TransferManager getCosTransferManager();
	
	String getBucketName();
	
	String getPath();

	default String getKey(String target) {
		return getPath() == null? target :  getPath() + "/" + target ;
	}
}