package com.gzmpc.support.cos.client;

import java.io.File;
import com.qcloud.cos.exception.CosClientException;
import com.qcloud.cos.exception.CosServiceException;
import com.qcloud.cos.model.GetObjectRequest;
import com.qcloud.cos.transfer.Download;

public interface TencentCosDownLoadClient extends TencentCosClient {

	
	/**
	 * 下载图片
	 * 
	 * @param String source 图片路径+名称
	 * @param File 目标文件
	 * @return InputStream
	 * @throws InterruptedException 
	 * @throws CosClientException 
	 * @throws CosServiceException 
	 */
	default void download(String source, File file) throws CosServiceException, CosClientException, InterruptedException {
		GetObjectRequest getObjectRequest = new GetObjectRequest(getBucketName(), getKey(source));
		Download download = getCosTransferManager().download(getObjectRequest, file);
		download.waitForCompletion();
	}

	/**
	 * 下载图片到本地指定路径
	 * 
	 * @param String source 图片链接地址
	 * @param String fileSavePath 图片保存路径
	 * @param String fileSaveName 图片保存名称
	 * @throws InterruptedException 
	 * @throws CosClientException 
	 * @throws CosServiceException 
	 * 
	 */
	default void downFileToLocal(String source, String target) throws CosServiceException, CosClientException, InterruptedException {
		GetObjectRequest getObjectRequest = new GetObjectRequest(getBucketName(), getKey(source));
		Download download = getCosTransferManager().download(getObjectRequest, new File(target));
		download.waitForCompletion();
	}

}
