package org.exframework.spring.boot.autoconfigure.cos;

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
	 * @return Download
	 * @throws InterruptedException 
	 * @throws CosClientException 
	 * @throws CosServiceException 
	 */
	default Download download(String source, File file) throws CosServiceException, CosClientException, InterruptedException {
		GetObjectRequest getObjectRequest = new GetObjectRequest(getBucketName(), getKey(source));
		Download download = getCosTransferManager().download(getObjectRequest, file);
		download.waitForCompletion();
		return download;
	}

	/**
	 * 下载图片到本地指定路径
	 * 
	 * @param source 云端路径(key)
	 * @param target 目标本地路径
	 * @return download
	 * @throws CosServiceException
	 * @throws CosClientException
	 * @throws InterruptedException
	 */
	default Download downFileToLocal(String source, String target) throws CosServiceException, CosClientException, InterruptedException {
		GetObjectRequest getObjectRequest = new GetObjectRequest(getBucketName(), getKey(source));
		Download download = getCosTransferManager().download(getObjectRequest, new File(target));
		download.waitForCompletion();
		return download;
	}

}
