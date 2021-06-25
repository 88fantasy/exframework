package org.exframework.support.cos.client;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.region.Region;
import com.qcloud.cos.transfer.TransferManager;

/**
 * COS 客户端
 * Author: rwe
 * Date: Jan 1, 2021
 *
 * Copyright @ 2021 
 * 
 */

public class CosClient implements TencentCosDeleteClient, TencentCosDownLoadClient, TencentCosUpLoadClient {

	TransferManager cosTransferManager;
	
	String bucketName;
	
	String path;
	

	public void setCosTransferManager(TransferManager cosTransferManager) {
		this.cosTransferManager = cosTransferManager;
	}


	public void setBucketName(String bucketName) {
		this.bucketName = bucketName;
	}


	public void setPath(String path) {
		this.path = path;
	}


	@Override
	public TransferManager getCosTransferManager() {
		return cosTransferManager;
	}


	@Override
	public String getBucketName() {
		return bucketName;
	}


	@Override
	public String getPath() {
		return path;
	}
	
	public static CosClient init(String secretId, String secretKey, String cosRegion, String bucketName, String path) {
		CosClient client = new CosClient();
		COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
		Region region = new Region(cosRegion);
		ClientConfig clientConfig = new ClientConfig(region);
        COSClient cosClient = new COSClient(cred, clientConfig);
        TransferManager tm = new TransferManager(cosClient);
        client.setCosTransferManager(tm); 
        client.setBucketName(bucketName);
        client.setPath(path);
        return client;
	}

}
