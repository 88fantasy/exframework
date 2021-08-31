package org.exframework.spring.boot.autoconfigure.minio.errors;

/**
 * 本地错误类
 *
 * @author rwe
 * @date 2021/8/26 19:29
 **/
public class MinioClientException extends Exception{

    public MinioClientException() {
    }

    public MinioClientException(String message) {
        super(message);
    }

    public MinioClientException(String message, Throwable cause) {
        super(message, cause);
    }

    public MinioClientException(Throwable cause) {
        super(cause);
    }

    public MinioClientException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
