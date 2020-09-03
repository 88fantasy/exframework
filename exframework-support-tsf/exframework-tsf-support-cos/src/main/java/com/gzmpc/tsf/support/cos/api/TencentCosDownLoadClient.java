package com.gzmpc.tsf.support.cos.api;

import java.io.File;
import java.io.InputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.model.COSObject;
import com.qcloud.cos.model.COSObjectInputStream;
import com.qcloud.cos.model.GetObjectRequest;
import com.qcloud.cos.model.ObjectMetadata;

@Component
public class TencentCosDownLoadClient {
	private Log log = LogFactory.getLog(TencentCosDownLoadClient.class.getName());

	@Autowired
	private COSClient cosClient;
	
	@Value("${tencentcloud.cos.bucket.name}")
	private String bucketName;
	/**
	 * 下载图片
	 * 
	 * @param String fileUrl 图片路径+名称
	 * @return InputStream
	 */
	public InputStream downFile(String fileUrl) {
		COSObjectInputStream cosObjectInput = null;
		try {
			GetObjectRequest getObjectRequest = new GetObjectRequest(bucketName, fileUrl);
			COSObject cosObject = cosClient.getObject(getObjectRequest);
			cosObjectInput = cosObject.getObjectContent();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("com.gzmpc.support.tencentCOS.api.TencentCosDownLoadClient-001-error "+e.getMessage());
			log.error("com.gzmpc.support.tencentCOS.api.TencentCosDownLoadClient-001-error "+e.getMessage());
		}
		return cosObjectInput;
	}

	/**
	 * 下载图片到本地指定路径
	 * 
	 * @param String fileUrl 图片链接地址
	 * @param String fileSavePath 图片保存路径
	 * @param String fileSaveName 图片保存名称
	 * 
	 */
	public void downFileToLocal(String fileUrl, String fileSavePath, String fileSaveName) {
		ObjectMetadata downObjectMeta = null;
		GetObjectRequest getObjectRequest = null;
		try {
			Long times = System.currentTimeMillis();
			String outputFilePath = fileSavePath + "/" + fileSaveName;// 保存文件到本地
			File downFile = new File(outputFilePath);
			getObjectRequest = new GetObjectRequest(bucketName, fileUrl);
			downObjectMeta = cosClient.getObject(getObjectRequest, downFile);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("com.gzmpc.support.tencentCOS.api.TencentCosDownLoadClient-002-error "+e.getMessage());
			log.error("com.gzmpc.support.tencentCOS.api.TencentCosDownLoadClient-002-error "+e.getMessage());
		} 
	}

}
