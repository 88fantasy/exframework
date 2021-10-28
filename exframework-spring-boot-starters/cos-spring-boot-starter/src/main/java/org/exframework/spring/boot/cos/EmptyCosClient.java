package org.exframework.spring.boot.cos;

import com.qcloud.cos.transfer.TransferManager;

/**
 *
 * @author rwe
 * @since 2021年6月17日
 *
 * Copyright @ 2021 
 * 
 */
public class EmptyCosClient implements CosClient {

	@Override
	public TransferManager getCosTransferManager() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getBucketName() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getPath() {
		throw new UnsupportedOperationException();
	}

}
