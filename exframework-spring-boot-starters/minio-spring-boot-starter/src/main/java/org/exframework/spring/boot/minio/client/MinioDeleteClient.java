package org.exframework.spring.boot.minio.client;

import io.minio.RemoveObjectArgs;
import io.minio.RemoveObjectsArgs;
import io.minio.Result;
import io.minio.errors.MinioException;
import io.minio.messages.DeleteError;
import io.minio.messages.DeleteObject;
import org.exframework.spring.boot.minio.errors.MinioClientException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public interface MinioDeleteClient extends CommonClient {
    Logger logger = LoggerFactory.getLogger(MinioDeleteClient.class);

    /**
     * 删除文件
     *
     * @param targetUrl 文件路径
     * @throws MinioException
     * @throws IOException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     */
    default void delFile(String targetUrl) throws MinioException, MinioClientException {
        try {
            getMinioClient().removeObject(RemoveObjectArgs.builder()
                    .bucket(getBucketName())
                    .object(targetUrl)
                    .build());
        } catch (InvalidKeyException | IOException | NoSuchAlgorithmException e) {
            throw new MinioClientException(e);
        }
    }

    /**
     * 删除文件(不用 catch)
     *
     * @param targetUrl
     */
    default void delFileQuietly(String targetUrl) {
        try {
            delFile(targetUrl);
        } catch (Exception e) {
            logger.error("删除文件出现错误:" + e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 删除多个文件
     *
     * @param targets
     */
    default Iterable<Result<DeleteError>> delFiles(String... targets) {
        if (targets != null && targets.length > 0) {
            return getMinioClient().removeObjects(RemoveObjectsArgs.builder()
                    .bucket(getBucketName())
                    .objects(Stream.of(targets).map(s -> new DeleteObject(s)).collect(Collectors.toList()))
                    .build());
        }
        return Collections.emptyList();
    }
}
