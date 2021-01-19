package com.gzmpc.support.cos.client;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.util.List;

import org.springframework.util.StringUtils;

import com.qcloud.cos.exception.CosClientException;
import com.qcloud.cos.exception.CosServiceException;
import com.qcloud.cos.model.COSObjectSummary;
import com.qcloud.cos.model.ListObjectsRequest;
import com.qcloud.cos.model.ObjectListing;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.UploadResult;
import com.qcloud.cos.transfer.TransferManager;
import com.qcloud.cos.transfer.Upload;

public interface TencentCosUpLoadClient  extends TencentCosClient {
	
	/**
	 * 以文件流的形式上传文件
	 * 
	 * @param FileInputStream   fileInputStream 文件输入流
	 * @param String targetUrl 上传的路径(含文件名)
	 * @param ObjectMetadata objectMetadata 设置文件头的信息
	 * @return com.qcloud.cos.model.UploadResult
	 * @throws InterruptedException 
	 * @throws CosClientException 
	 * @throws CosServiceException 
	 */
	default UploadResult upload(FileInputStream fileInputStream, String targetUrl, ObjectMetadata objectMetadata) throws CosServiceException, CosClientException, InterruptedException {
		PutObjectRequest request = new PutObjectRequest(getBucketName(), targetUrl, fileInputStream, objectMetadata);
		return upload(request, -1, true);
	}
	
	
	/**
	 * 上传文件
	 * 
	 * @param File   file 上传的文件
	 * @param String targetUrl 上传的路径(含文件名)
	 * @param ObjectMetadata objectMetadata 设置文件头的信息
	 * @return com.qcloud.cos.model.UploadResult
	 * @throws InterruptedException 
	 * @throws CosClientException 
	 * @throws CosServiceException 
	 */
	default UploadResult upload(File file, String targetUrl,  ObjectMetadata objectMetadata) throws CosServiceException, CosClientException, InterruptedException {
		PutObjectRequest request = new PutObjectRequest(getBucketName(), targetUrl, file).withMetadata(objectMetadata);
		return upload(request, -1, true);
	}
	
	/**
	 * 以文件流的形式上传文件
	 * 
	 * @param FileInputStream   fileInputStream 文件输入流
	 * @param String targetUrl 上传的路径(含文件名)
	 * @param ObjectMetadata objectMetadata 设置文件头的信息
	 * @param expirationTime 资源过期时间
	 * @return com.qcloud.cos.model.UploadResult
	 * @throws InterruptedException 
	 * @throws CosClientException 
	 * @throws CosServiceException 
	 */
	default UploadResult upload(FileInputStream fileInputStream, String targetUrl, ObjectMetadata objectMetadata, long expirationTime) throws CosServiceException, CosClientException, InterruptedException {
		PutObjectRequest request = new PutObjectRequest(getBucketName(), targetUrl, fileInputStream, objectMetadata);
		return upload(request, expirationTime, true);
	}
	
	
	/**
	 * 上传文件
	 * 
	 * @param File   file 上传的文件
	 * @param String targetUrl 上传的路径(含文件名)
	 * @param ObjectMetadata objectMetadata 设置文件头的信息
	 * @param expirationTime 资源过期时间
	 * @return com.qcloud.cos.model.UploadResult
	 * @throws InterruptedException 
	 * @throws CosClientException 
	 * @throws CosServiceException 
	 */
	default UploadResult upload(File file, String targetUrl,  ObjectMetadata objectMetadata, long expirationTime) throws CosServiceException, CosClientException, InterruptedException {
		PutObjectRequest request = new PutObjectRequest(getBucketName(), targetUrl, file).withMetadata(objectMetadata);
		return upload(request, expirationTime, true);
	}
	
	/**
	 * 以文件流的形式上传文件
	 * 
	 * @param FileInputStream   fileInputStream 文件输入流
	 * @param String targetUrl 上传的路径(含文件名)
	 * @param ObjectMetadata objectMetadata 设置文件头的信息
	 * @param expirationTime 资源过期时间
	 * @param boolean replace 是否覆盖
	 * @return com.qcloud.cos.model.UploadResult
	 * @throws InterruptedException 
	 * @throws CosClientException 
	 * @throws CosServiceException 
	 */
	default UploadResult upload(FileInputStream fileInputStream, String targetUrl, ObjectMetadata objectMetadata, long expirationTime, boolean replace) throws CosServiceException, CosClientException, InterruptedException {
		PutObjectRequest request = new PutObjectRequest(getBucketName(), targetUrl, fileInputStream, objectMetadata);
		return upload(request, expirationTime, replace);
	}
	
	
	/**
	 * 上传文件
	 * 
	 * @param File   file 上传的文件
	 * @param String targetUrl 上传的路径(含文件名)
	 * @param ObjectMetadata objectMetadata 设置文件头的信息
	 * @param long expirationTime 资源过期时间
	 * @param boolean replace 是否覆盖
	 * @return com.qcloud.cos.model.UploadResult
	 * @throws InterruptedException 
	 * @throws CosClientException 
	 * @throws CosServiceException 
	 */
	default UploadResult upload(File file, String targetUrl,  ObjectMetadata objectMetadata, long expirationTime, boolean replace) throws CosServiceException, CosClientException, InterruptedException {
		PutObjectRequest request = new PutObjectRequest(getBucketName(), targetUrl, file).withMetadata(objectMetadata);
		return upload(request, expirationTime, replace);
	}
	
	default UploadResult upload(PutObjectRequest request, long expirationTime, boolean replace) throws CosServiceException, CosClientException, InterruptedException {
		String bucketName = request.getBucketName();
		String requestKey = getKey(request.getKey());
		TransferManager cosTransferManager = getCosTransferManager();
		if(!StringUtils.hasLength(bucketName) ) {
			throw new CosClientException("bucketName不能为null");
		}
		if(!replace) {
			ListObjectsRequest listObjectsRequest = new ListObjectsRequest();
			// 设置bucket名称
			listObjectsRequest.setBucketName(bucketName);
			// prefix表示列出的object的key以prefix开始
			listObjectsRequest.setPrefix(requestKey);
			// deliter表示分隔符, 设置为/表示列出当前目录下的object, 设置为空表示列出所有的object
			listObjectsRequest.setDelimiter("/");
			// 设置最大遍历出多少个对象, 一次listobject最大支持1000
			listObjectsRequest.setMaxKeys(1000);
			ObjectListing objectListing = null;
			do {
			   objectListing = cosTransferManager.getCOSClient().listObjects(listObjectsRequest);
			    // object summary表示所有列出的object列表
			   List<COSObjectSummary> cosObjectSummaries = objectListing.getObjectSummaries();
			   for (COSObjectSummary cosObjectSummary : cosObjectSummaries) {
			       // 文件的路径key
			       String key = cosObjectSummary.getKey();
			       if(key.equals(requestKey)) {
			    	   UploadResult result = new UploadResult();
			    	   result.setBucketName(bucketName);
			    	   result.setKey(key);
			    	   result.setETag(cosObjectSummary.getETag());
			    	   return result;
			       }
			   }
			    String nextMarker = objectListing.getNextMarker();
			   listObjectsRequest.setMarker(nextMarker);
			} while (objectListing.isTruncated());
		}
		
		Upload upload = cosTransferManager.upload(request);
		UploadResult result = upload.waitForUploadResult();
		if(expirationTime > 0) {
			cosTransferManager.getCOSClient().generatePresignedUrl(bucketName, requestKey, new Date(System.currentTimeMillis() + (expirationTime * 1000)));
		}
		return result;
	}
}
