package org.exframework.spring.boot.autoconfigure.minio;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.errors.MinioException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * COS 客户端
 *
 * @author rwe
 * @since Jan 1, 2021
 * <p>
 * Copyright @ 2021
 */

public class DefaultMinioTemplate implements MinioTemplate {

    public static Logger logger = LoggerFactory.getLogger(DefaultMinioTemplate.class);

    private MinioClient minioClient;

    private String bucketName;

    private String path;

    @Override
    public MinioClient getMinioClient() {
        return minioClient;
    }

    public DefaultMinioTemplate setMinioClient(MinioClient minioClient) {
        this.minioClient = minioClient;
        return this;
    }

    @Override
    public String getBucketName() {
        return bucketName;
    }

    @Override
    public String getPath() {
        return path;
    }

    public DefaultMinioTemplate setPath(String path) {
        this.path = path;
        return this;
    }

    public DefaultMinioTemplate setBucketName(String bucketName) {
        this.bucketName = bucketName;
        return this;
    }

    public static final class Builder {

        private String endpoint;

        private String bucket;

        private String region;

        private String path;

        private String access;

        private String secret;

        public static Builder builder() {
            return new Builder();
        }

        public Builder endpoint(String endpoint) {
            this.endpoint = endpoint;
            return this;
        }

        public Builder bucket(String bucket) {
            this.bucket = bucket;
            return this;
        }

        public Builder path(String path) {
            this.path = path;
            return this;
        }

        public Builder region(String region) {
            this.region = region;
            return this;
        }

        public Builder access(String access) {
            this.access = access;
            return this;
        }

        public Builder secret(String secret) {
            this.secret = secret;
            return this;
        }

        public MinioTemplate build() throws MinioException, IOException, NoSuchAlgorithmException, InvalidKeyException {
            DefaultMinioTemplate template = new DefaultMinioTemplate();
            MinioClient.Builder builder = MinioClient.builder()
                    .endpoint(endpoint)
                    .credentials(access, secret);
            if (StringUtils.hasText(region)) {
                builder.region(region);
            }
            MinioClient minioClient = builder.build();
            if (StringUtils.hasText(bucket)) {
                boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucket).build());
                if (!found) {
                    logger.error("存储桶[" + bucket + "]不存在将会自动创建");
                    minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucket).build());
                }
                template.setBucketName(bucket);
            } else {
                logger.error("没有指定存储桶需要手动设置");
            }
            template.setPath(path);
            template.setMinioClient(minioClient);
            return template;
        }
    }
}
