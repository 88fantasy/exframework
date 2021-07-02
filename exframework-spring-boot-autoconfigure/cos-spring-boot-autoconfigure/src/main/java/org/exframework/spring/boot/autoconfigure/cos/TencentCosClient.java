package org.exframework.spring.boot.autoconfigure.cos;

import com.qcloud.cos.transfer.TransferManager;

/**
 *
 * @author rwe
 * @since Jan 1, 2021
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
