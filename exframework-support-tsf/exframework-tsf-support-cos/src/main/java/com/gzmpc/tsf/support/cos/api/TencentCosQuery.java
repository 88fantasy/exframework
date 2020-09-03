package com.gzmpc.tsf.support.cos.api;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.exception.CosClientException;
import com.qcloud.cos.exception.CosServiceException;
import com.qcloud.cos.model.COSObjectSummary;
import com.qcloud.cos.model.ListObjectsRequest;
import com.qcloud.cos.model.ObjectListing;

@Component
public class TencentCosQuery {
	private Log log = LogFactory.getLog(TencentCosQuery.class.getName());

	@Autowired
	private COSClient cosClient;
	
	@Value("${tencentcloud.cos.bucket.name}")
	private String bucketName;
	
	public void getAllFile() {
		try {
//          桶容器的名称（需要改1）
			ListObjectsRequest listObjectsRequest = new ListObjectsRequest();
			// 设置 bucket 名称
			listObjectsRequest.setBucketName(bucketName);
			// prefix 表示列出的 object 的 key 以 prefix 开始
			listObjectsRequest.setPrefix("");
			// 设置最大遍历出多少个对象, 一次 listobject 最大支持1000
			listObjectsRequest.setMaxKeys(1000);
			listObjectsRequest.setDelimiter("/");
			ObjectListing objectListing = cosClient.listObjects(listObjectsRequest);
			for (COSObjectSummary cosObjectSummary : objectListing.getObjectSummaries()) {
				// 对象的路径 key
				String key = cosObjectSummary.getKey();
				// 对象的 etag
				String etag = cosObjectSummary.getETag();
				// 对象的长度
				long fileSize = cosObjectSummary.getSize();
				// 对象的存储类型
				String storageClass = cosObjectSummary.getStorageClass();
				System.out.println(
						"key:" + key + "; etag:" + etag + "; fileSize:" + fileSize + ";storageClass:" + storageClass);
			}
		} catch (CosServiceException serverException) {
			serverException.printStackTrace();
			System.out.println("com.gzmpc.support.tencentCOS.api.TencentCosQuery-001-error "+serverException.getMessage());
			log.error("com.gzmpc.support.tencentCOS.api.TencentCosQuery-001-error "+serverException.getMessage());
		} catch (CosClientException clientException) {
			clientException.printStackTrace();
			System.out.println("com.gzmpc.support.tencentCOS.api.TencentCosQuery-002-error "+clientException.getMessage());
			log.error("com.gzmpc.support.tencentCOS.api.TencentCosQuery-002-error "+clientException.getMessage());
		}

	}

}
