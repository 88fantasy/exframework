package com.gzmpc.tsf.support.cos.api;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.gzmpc.tsf.support.cos.exception.TencentCOSException;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Component
public class TencentCosUpLoadClient {
	private Log log = LogFactory.getLog(TencentCosUpLoadClient.class.getName());
	@Autowired
	private COSClient cosClient;
	
	@Value("${tencentcloud.cos.bucket.name}")
	private String bucketName;
	
	@Value("${tencentcloud.cos.bucket.url}")
	private String bucketNameUrl;
	
	/**
	 * 以文件流的形式上传文件
	 * 
	 * @param FileInputStream   fileInputStream 文件输入流
	 * @param ObjectMetadata objectMetadata 设置文件头的信息
	 * @param String storePath 上传的路径
	 * @param String fileName 文件名
	 * @return String
	 */
	public String uploadFile(FileInputStream fileInputStream, ObjectMetadata objectMetadata,String fileName,String storePath) {
		String fileStore = null;
		if(StringUtils.isBlank(storePath)) {
			fileStore = fileName;
		}else {
			fileStore = storePath+"/"+fileName;
		}
		try {
			// 设置输入流长度为500
			objectMetadata.setContentLength(fileInputStream.read());
			// 设置 Content type, 默认是 application/octet-stream
			PutObjectResult putObjectResult = cosClient.putObject(bucketName, fileStore, fileInputStream, objectMetadata);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("com.gzmpc.support.tencentCOS.api.TencentCosUpLoadClient-001-error "+e.getMessage());
            System.out.println("com.gzmpc.support.tencentCOS.api.TencentCosUpLoadClient-001-error "+e.getMessage());
			return null;
		}
    
		return bucketNameUrl+"/"+fileStore ;
	}
	
	
	/**
	 * 上传文件
	 * 
	 * @param File   file 上传的文件
	 * @param String storePath 上传的路径
	 * @param String fileName 文件名
	 * @param ObjectMetadata objectMetadata 设置文件头的信息
	 * @return String
	 */
	public String uploadFile(File file,String storePath, String fileName,  ObjectMetadata objectMetadata) {
		String fileStore = null;
		if(StringUtils.isBlank(storePath)) {
			fileStore = fileName;
		}else {
			fileStore = storePath+"/"+fileName;
		}
		try {
			PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, fileStore, file);
			// 设置自定义属性(如 content-type, content-disposition 等)
			if(objectMetadata != null) {
			  putObjectRequest.setMetadata(objectMetadata);
			}
			PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("com.gzmpc.support.tencentCOS.api.TencentCosUpLoadClient-002-error "+e.getMessage());
            System.out.println("com.gzmpc.support.tencentCOS.api.TencentCosUpLoadClient-002-error "+e.getMessage());
			return null;
		} 
		return bucketNameUrl+"/"+fileStore;
	}
	
	/**
	 * 上传文件
	 * 
	 * @param File   file 上传的文件
	 * @param String storePath 上传的路径
	 * @param String fileName 文件名
	 * @param Integer expirationTime 图片过期时间
	 * @return String
	 */
	public String uploadFile(File file,String storePath, String fileName, Integer expirationTime) {
		String fileStore = null;
		if(StringUtils.isBlank(storePath)) {
			fileStore = fileName;
		}else {
			fileStore = storePath+"/"+fileName;
		}
		try {
			PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, fileStore, file);
			PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("com.gzmpc.support.tencentCOS.api.TencentCosUpLoadClient-003-error "+e.getMessage());
            System.out.println("com.gzmpc.support.tencentCOS.api.TencentCosUpLoadClient-003-error "+e.getMessage());
			return null;
		} finally {
			Date expiration = new Date(new Date().getTime() + expirationTime);
			URL url = cosClient.generatePresignedUrl(bucketName, fileName, expiration);
			fileStore = url.toString();
		}
		
		return fileStore ;
	}
	
	
	/**
	 * 上传文件
	 * 
	 * @param File   file 上传的文件
	 * @param String storePath 上传的路径
	 * @param String fileName 文件名
	 * @return String
	 */
	public String uploadFile(File file, String storePath, String fileName) {
		String fileStore = null;
		if(StringUtils.isBlank(storePath)) {
			fileStore = fileName;
		}else {
			fileStore = storePath+"/"+fileName;
		}
		try {
			PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, fileStore, file);
			PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);
		} catch (Exception e) {
			e.printStackTrace();
            System.out.println("com.gzmpc.support.tencentCOS.api.TencentCosUpLoadClient-004-error "+e.getMessage());
			log.error("com.gzmpc.support.tencentCOS.api.TencentCosUpLoadClient-004-error "+e.getMessage());
           return null;
		} 
		return bucketNameUrl+"/"+fileStore;
	}
	
	/**
	 * 上传文件
	 * 
	 * @param File   file 上传的文件
	 * @param String storePath 上传的路径
	 * @param String fileName 文件名
	 * @param String bucketName 桶名称
	 * @param String bucketNameUrl 桶路径
	 * @return String
	 * @throws TencentCOSException 
	 */
	public String uploadFile(File file, String storePath,String fileName,String bucketName,String bucketNameUrl) throws TencentCOSException {
		if(StringUtils.isBlank(bucketName) ) {
			throw new TencentCOSException("bucketName不能为null");
		}
		if(StringUtils.isBlank(bucketNameUrl) ) {
			throw new TencentCOSException("bucketNameUrl不能为null");
		}
		String fileStore = null;
		if(StringUtils.isBlank(storePath)) {
			fileStore = fileName;
		}else {
			fileStore = storePath+"/"+fileName;
		}
		try {
			
			PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, fileStore, file);
			PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("com.gzmpc.support.tencentCOS.api.TencentCosUpLoadClient-005-error "+e.getMessage());
			log.error("com.gzmpc.support.tencentCOS.api.TencentCosUpLoadClient-005-error "+e.getMessage());
			return null;
		} 
		return bucketNameUrl+"/"+fileStore;
	}
}
