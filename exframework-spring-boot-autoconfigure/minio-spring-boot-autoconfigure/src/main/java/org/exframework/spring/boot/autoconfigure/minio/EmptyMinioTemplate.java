package org.exframework.spring.boot.autoconfigure.minio;

import io.minio.MinioClient;

/**
 *
 * @author rwe
 * @since 2021年6月17日
 *
 * Copyright @ 2021 
 * 
 */
public class EmptyMinioTemplate implements MinioTemplate {


    @Override
    public MinioClient getMinioClient() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getBucketName() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getPath() {
        throw new UnsupportedOperationException();
    }
}
