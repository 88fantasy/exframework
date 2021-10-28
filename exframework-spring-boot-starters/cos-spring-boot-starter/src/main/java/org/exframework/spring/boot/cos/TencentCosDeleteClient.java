package org.exframework.spring.boot.cos;

import com.qcloud.cos.exception.CosClientException;
import com.qcloud.cos.exception.CosServiceException;


public interface TencentCosDeleteClient extends TencentCosClient {

	/**
	 * 删除文件
	 * @param targetUrl 文件路径
	 * @throws CosServiceException
	 * @throws CosClientException
	 */
	default void delFile(String targetUrl) throws CosServiceException, CosClientException {
		getCosTransferManager().getCOSClient().deleteObject(getBucketName(), getKey(targetUrl));
	}

}
