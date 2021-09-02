package org.exframework.spring.boot.autoconfigure.minio.client;

import io.minio.MinioClient;
import io.minio.StatObjectArgs;
import io.minio.StatObjectResponse;
import io.minio.errors.MinioException;
import org.exframework.spring.boot.autoconfigure.minio.errors.MinioClientException;
import org.exframework.spring.boot.autoconfigure.minio.model.DownloadResult;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author rwe
 * @since Jan 1, 2021
 *
 * Copyright @ 2021 
 * 
 */
public interface CommonClient {
	

	MinioClient getMinioClient();
	
	String getBucketName();

	String getPath();

	default String getKey(String target) {
		String path = getPath();
		return !StringUtils.hasText(path)? target :  path + "/" + target ;
	}

	default DownloadResult statObject(String key) throws MinioException, MinioClientException {
		try {
			StatObjectResponse response = getMinioClient().statObject(StatObjectArgs.builder().bucket(getBucketName()).object(getKey(key)).build());
			return new DownloadResult(response.headers(),response.bucket(), response.region(), response.object());
		} catch (InvalidKeyException | IOException | NoSuchAlgorithmException e) {
			throw new MinioClientException(e);
		}
	}
}
