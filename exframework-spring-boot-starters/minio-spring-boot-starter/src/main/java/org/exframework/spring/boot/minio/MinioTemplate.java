package org.exframework.spring.boot.minio;

import io.minio.ListObjectsArgs;
import io.minio.Result;
import io.minio.errors.MinioException;
import io.minio.messages.Item;
import org.exframework.spring.boot.minio.client.MinioDeleteClient;
import org.exframework.spring.boot.minio.client.MinioDownLoadClient;
import org.exframework.spring.boot.minio.client.MinioUpLoadClient;
import org.exframework.spring.boot.minio.errors.MinioClientException;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * minio 客户端
 *
 * @author rwe
 * @see <a href="https://docs.min.io/docs/java-client-api-reference.html">official client</a>
 * <p>
 * Copyright @ 2021
 * @since 2021年6月17日
 */
public interface MinioTemplate extends MinioDeleteClient, MinioDownLoadClient, MinioUpLoadClient {

    default Map<String, Item> listObjects() throws MinioClientException, MinioException {
        return listObjects(null, 1000);
    }

    default Map<String, Item> listObjects(String path, int maxKeys) throws MinioClientException, MinioException {
        Map<String, Item> result = new ConcurrentHashMap<>();
        ListObjectsArgs.Builder builder = ListObjectsArgs.builder().bucket(getBucketName());
        if (maxKeys > 0) {
            builder.maxKeys(maxKeys);
        }
        Iterable<Result<Item>> items = getMinioClient().listObjects(builder.build());
        for (Result<Item> item : items) {
            try {
                Item i = item.get();
                result.put(i.objectName(), i);
            } catch (InvalidKeyException | IOException | NoSuchAlgorithmException e) {
                throw new MinioClientException(e);
            }
        }
        return result;
    }
}
